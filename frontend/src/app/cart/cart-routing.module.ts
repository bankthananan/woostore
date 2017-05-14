import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ManageCartComponent} from './manage-cart/manage-cart.component';


const cartRoutes: Routes = [
  {
    path: 'manage-cart',
    component: ManageCartComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(cartRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class CartRoutingModule {}
