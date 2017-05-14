import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListProductComponent} from './list-product/list-product.component';
import {DetailProductComponent} from './detail-product/detail-product.component';
import {ManageProductComponent} from './manage-product/manage-product.component';

const productRoutes: Routes = [
  {
    path: 'list',
    component: ListProductComponent
  },
  {
    path: 'detail/:id',
    component: DetailProductComponent
  },
  {
    path: 'manage-product',
    component: ManageProductComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(productRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class ProductRoutingModule {}
