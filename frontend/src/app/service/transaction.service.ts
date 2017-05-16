import { Injectable } from '@angular/core';
import {Http, Headers, Response, RequestOptions, URLSearchParams} from '@angular/http';
import 'rxjs';
import {wooConfig} from '../woo.config';
import {Observable} from 'rxjs/Observable';
import {Transaction} from '../transaction/transaction';
import {AuthenticationService} from "app/service/authentication.service";
import {WooPayment} from '../transaction/woo-payment';
import {WooPaymentType} from '../transaction/woo-payment-type';
import {TransactionStatus} from '../transaction/transaction-status';

@Injectable()
export class TransactionService {

  constructor(private http: Http, private authenticationService: AuthenticationService) {}

  getAllTransaction(date: Date): Observable<Transaction[]> {
    let url = wooConfig.serverPath + 'transactions';
    if(date) {
      url += '/' + date.getTime() / 1000;
    }
    return this.http.get(url, {headers: this.authenticationService.headers}).map(res => res.json());
  }

  getPendingTransaction(date: Date): Observable<Transaction[]> {
    let url = wooConfig.serverPath + 'transactions/pending';
    if(date) {
      url += '/' + date.getTime() / 1000;
    }
    return this.http.get(url, {headers: this.authenticationService.headers}).map(res => res.json());
  }

  getPaidTransaction(date: Date): Observable<Transaction[]> {
    let url = wooConfig.serverPath + 'transactions/paid';
    if(date) {
      url += '/' + date.getTime() / 1000;
    }
    return this.http.get(url, {headers: this.authenticationService.headers}).map(res => res.json());
  }

  addTransaction(transaction: Transaction): Observable<Transaction> {
    return this.http.post(wooConfig.serverPath + 'transaction', transaction, { headers: this.authenticationService.headers }).map(res => res.json());
  }

  getTransaction(id: number): Observable<Transaction> {
    return this.http.get(wooConfig.serverPath + 'transaction/' + id, {headers: this.authenticationService.headers}).map(res => res.json());
  }

  payByWireTransfer(transaction: Transaction, file: any) {
    let formData = new FormData();
    formData.append('file', file);
    let header = new Headers({'Authorization': 'Bearer ' + this.authenticationService.getToken()});
    return this.http.post(wooConfig.serverPath + 'transaction/payment/image', formData, {headers : header})
      .flatMap(filename => {
        const wooPayment: WooPayment = new WooPayment();
        wooPayment.fileName = filename.text();
        wooPayment.wooPaymentType = WooPaymentType.WIRE_TRANSFER;
        transaction.wooPayment = wooPayment;
        transaction.status = TransactionStatus.PAID;
        let headers = this.authenticationService.headers;
        let options = new RequestOptions({headers: headers, method: 'post'});
        let body = JSON.stringify(transaction);
        return this.http.post(wooConfig.serverPath + 'transaction', body, options)
          .map(res => {
            return res.json();
          })
          .catch((error: any) => {
            return Observable.throw(new Error(error.status));
          });
      });
  }

  payByPayPal(transaction: Transaction): Observable<string> {
    let headers = this.authenticationService.headers;
    let options = new RequestOptions({headers: headers, method: 'post'});
    let body = JSON.stringify(transaction);
    return this.http.post(wooConfig.serverPath + 'pay', body, options).map(res => res.text());
  }

  getTransactionByUser() {
    return this.http.get(wooConfig.serverPath + 'transaction/owner/' + this.authenticationService.getUser().id, {headers: this.authenticationService.headers}).map(res => res.json());
  }

  // getProduct(id: number) {
  //   return this.http.get(wooConfig.serverPath + 'product/' + id)
  //     .map(res => res.json());
  // }

}
