import { Component, OnInit } from '@angular/core';
import {TransactionService} from '../../service/transaction.service';
import {Transaction} from '../transaction';

@Component({
  selector: 'app-list-transaction',
  templateUrl: './list-transaction.component.html',
  styleUrls: ['./list-transaction.component.css']
})
export class ListTransactionComponent implements OnInit {

  transactions: Transaction[];

  constructor(private transactionService: TransactionService) { }

  ngOnInit() {
    this.transactionService.getTransactionByUser().subscribe(transactions => this.transactions = transactions);
  }

}
