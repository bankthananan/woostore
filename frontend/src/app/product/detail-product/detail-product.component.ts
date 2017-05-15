import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../service/product.service';
import {Product} from '../product';
import {ActivatedRoute, Params} from '@angular/router';
import { Location } from '@angular/common';
import {CartService} from '../../service/cart.service';

declare var Materialize;

@Component({
  selector: 'app-detail-product',
  templateUrl: './detail-product.component.html',
  styleUrls: ['./detail-product.component.css']
})
export class DetailProductComponent implements OnInit {
  product: Product;
  constructor(
    private productDataService: ProductService,
    private route : ActivatedRoute,
    private location: Location,
    private cartService: CartService
  ) {}

  ngOnInit() {
    this.route.params.switchMap((params: Params) => this.productDataService.getProduct(+params['id'])).subscribe((product: Product) => {
      if(product) {
        this.product = product;
      }
    })
  }

  addToCart(product: Product): void {
    this.cartService.addProduct(product, 1);
    Materialize.toast('Add ' + product.name + ' To Shopping Cart Complete !!', 4000);
  }

  back() {
    this.location.back();
  }

}
