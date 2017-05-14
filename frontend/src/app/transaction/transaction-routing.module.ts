import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ManageTransactionComponent} from './manage-transaction/manage-transaction.component';


const transactionRoutes: Routes = [
  {
    path: 'manage-transaction',
    component: ManageTransactionComponent
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
