import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ManageTransactionComponent} from './manage-transaction/manage-transaction.component';
import {DetailTransactionComponent} from './detail-transaction/detail-transaction.component';


const transactionRoutes: Routes = [
  {
    path: 'manage-transaction',
    component: ManageTransactionComponent
  },
  {
    path: 'purchase/:id',
    component: DetailTransactionComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(transactionRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class TransactionRoutingModule {}
