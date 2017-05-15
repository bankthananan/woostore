import { Component, OnInit } from '@angular/core';
import {Transaction} from '../transaction';
import {TransactionService} from '../../service/transaction.service';
import {Router} from '@angular/router';
import {TransactionStatus} from '../transaction-status';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-manage-transaction',
  templateUrl: './manage-transaction.component.html',
  styleUrls: ['./manage-transaction.component.css']
})
export class ManageTransactionComponent implements OnInit {

  transactions: Transaction[];
  viewStatus: TransactionStatus = null;
  searchDate: Date;

  constructor(private transactionService: TransactionService, private router: Router) { }

  ngOnInit() {
    this.getTransaction();
  }

  searchByDate(event) {
    const searchDate = event.target.value;
    if(searchDate == "") {
      this.searchDate = null;
    }
    else {
      this.searchDate = new Date(searchDate);
    }
    this.getTransaction();
  }

  getTransaction(): void {
    switch (this.viewStatus) {
      case TransactionStatus.PAID:
            this.transactionService.getPaidTransaction(this.searchDate).subscribe(transactions => this.transactions = transactions);
            break;
      case TransactionStatus.PENDING:
            this.transactionService.getPendingTransaction(this.searchDate).subscribe(transactions => this.transactions = transactions);
            break;
      default:
            this.transactionService.getAllTransaction(this.searchDate).subscribe(transactions => this.transactions = transactions);
    }
  }

  viewProductDetail(product) {
    this.router.navigate(['detail/'+ product.id]);
  }

  changeView(viewType: string): void {
    switch(viewType) {
      case 'paid':
        this.viewStatus = TransactionStatus.PAID;
        break;
      case 'pending':
        this.viewStatus = TransactionStatus.PENDING;
        break;
      default:
        this.viewStatus = null;
    }
    this.getTransaction();
  }

}
