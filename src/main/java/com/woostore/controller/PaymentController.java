package com.woostore.controller;

import javax.servlet.http.HttpServletRequest;

import com.paypal.api.payments.Transaction;
import com.sun.org.apache.regexp.internal.RE;
import com.woostore.entity.commerce.TransactionStatus;
import com.woostore.entity.commerce.payment.WooPayment;
import com.woostore.entity.commerce.payment.WooPaymentType;
import com.woostore.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.woostore.config.PaypalPaymentIntent;
import com.woostore.config.PaypalPaymentMethod;
import com.woostore.services.PaypalService;
import com.woostore.util.URLUtils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@RestController
@RequestMapping("/")
public class PaymentController {
	
	public static final String PAYPAL_SUCCESS_URL = "pay/success";
	public static final String PAYPAL_CANCEL_URL = "pay/cancel";
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PaypalService paypalService;

	@Autowired
	private TransactionRepository transactionRepository;
	
//	@RequestMapping(method = RequestMethod.GET)
//	public String index(){
//		return "index";
//	}

	String clientUrl;

	@RequestMapping(method = RequestMethod.POST, value = "pay")
	public String pay(HttpServletRequest request, @RequestBody com.woostore.entity.commerce.Transaction transaction){
		this.clientUrl = request.getHeader("referer");
		String cancelUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL + "/" + transaction.getId();
		String successUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_SUCCESS_URL + "/" + transaction.getId();
		try {
			Payment payment = paypalService.createPayment(
					transaction.getTotalPrice(),
					"USD", 
					PaypalPaymentMethod.paypal, 
					PaypalPaymentIntent.sale,
					"payment description", 
					cancelUrl, 
					successUrl);
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					return links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_URL + "/{id}")
	public  ResponseEntity<?> cancelPay(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", this.clientUrl);
		return new ResponseEntity<byte []>(null,headers,HttpStatus.FOUND);
	}

	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_SUCCESS_URL + "/{id}")
	public ResponseEntity<?> successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, @PathVariable("id") long id){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", this.clientUrl);
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				WooPayment wooPayment = new WooPayment();
				wooPayment.setWooPaymentType(WooPaymentType.PAYPAL);
				wooPayment.setPaypalPaymentID(paymentId + ":" + payerId);
				com.woostore.entity.commerce.Transaction transaction = transactionRepository.findOne(id);
				transaction.setWooPayment(wooPayment);
				transaction.setStatus(TransactionStatus.PAID);
				transactionRepository.save(transaction);
//				return "success" + id;
				return new ResponseEntity<byte []>(null,headers,HttpStatus.FOUND);
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
//		return "redirect:/";
		return new ResponseEntity<byte []>(null,headers,HttpStatus.FOUND);
	}
	
}
