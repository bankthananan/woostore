import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {TransactionService} from '../../service/transaction.service';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Transaction} from '../transaction';
import {WooPayment} from '../woo-payment';
import {TransactionStatus} from '../transaction-status';
import {WooPaymentType} from '../woo-payment-type';

@Component({
  selector: 'app-detail-transaction',
  templateUrl: './detail-transaction.component.html',
  styleUrls: ['./detail-transaction.component.css']
})
export class DetailTransactionComponent implements OnInit {

  @ViewChild('inputPicture') inputPicture: ElementRef;

  transaction: Transaction;

  constructor(
    private transactionService: TransactionService,
    private route : ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.route.params.switchMap((params: Params) => this.transactionService.getTransaction(+params['id'])).subscribe((transaction: Transaction) => {
      if(transaction) {
        this.transaction = transaction;
      }
    })
  }

  getTransaction() {
    this.transactionService.getTransaction(this.transaction.id).subscribe((transaction: Transaction) => {
      if (transaction) {
        this.transaction = transaction;
      }
    });
  }

  payByWireTransfer(): void {
    let inputPictureEl: HTMLInputElement = this.inputPicture.nativeElement;
    if(inputPictureEl.files.length > 0) {
      this.transactionService.payByWireTransfer(this.transaction, inputPictureEl.files.item(0)).subscribe((transaction) => {
        console.log(transaction);
        this.getTransaction();
      });
    }
  }


  payByPayPal() {
    this.transactionService.payByPayPal(this.transaction).subscribe((url) => {
      window.location.href = url;
    });
  }

  isPayPal(transaction: Transaction) {
    return this.isPaid(transaction) && transaction.wooPayment.wooPaymentType === WooPaymentType.PAYPAL;
  }

  isWireTransfer(transaction: Transaction): boolean {
    return this.isPaid(transaction) && transaction.wooPayment.wooPaymentType === WooPaymentType.WIRE_TRANSFER ;
  }

  isPaid(transaction: Transaction): boolean {
    return transaction.status === TransactionStatus.PAID;
  }

}
