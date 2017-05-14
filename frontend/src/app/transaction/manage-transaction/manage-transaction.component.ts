import { Component, OnInit } from '@angular/core';
import {Transaction} from '../transaction';
import {TransactionService} from '../../service/transaction.service';

@Component({
  selector: 'app-manage-transaction',
  templateUrl: './manage-transaction.component.html',
  styleUrls: ['./manage-transaction.component.css']
})
export class ManageTransactionComponent implements OnInit {

  transactions: Transaction[];

  constructor(private transactionService: TransactionService) { }

  ngOnInit() {
    this.transactionService.getAllTransaction().subscribe((transactions: Transaction[]) => this.transactions = transactions);
  }

  truncateDesc(desc: string) {
    if(desc.length > 50) {
      return desc.substring(0, 47) + '...';
    }
    return desc;
  }

}
