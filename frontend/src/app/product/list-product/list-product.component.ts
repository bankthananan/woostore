import { Component, OnInit } from '@angular/core';
import {ProductDataServerService} from '../../service/product-data-server.service';
import {Product} from '../product';
import {AuthenticationService} from '../../service/authentication.service';

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {
  products: Product[];

  constructor(private productDataService: ProductDataServerService,
    private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.authenticationService.checkCustomerLogin()
      .subscribe( isLogin => {
        console.log('Login => ' +isLogin);
      })


    this.productDataService.getProductsData()
      .subscribe(products => {
        this.products = products
        this.products.push(this.products[0]);
        this.products.push(this.products[0]);
        this.products.push(this.products[0]);
        this.products.push(this.products[0]);
      });
  }

}
