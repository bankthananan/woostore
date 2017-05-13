import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../service/product.service';
import {Product} from '../product';
import {ActivatedRoute, Params} from '@angular/router';

@Component({
  selector: 'app-detail-product',
  templateUrl: './detail-product.component.html',
  styleUrls: ['./detail-product.component.css']
})
export class DetailProductComponent implements OnInit {
  product: Product;
  constructor(private productDataService: ProductService, private route : ActivatedRoute) { }

  ngOnInit() {
    this.route.params.switchMap((params: Params) => this.productDataService.getProduct(+params['id'])).subscribe((product: Product) => {
      if(product) {
        this.product = product;
      }
    })
  }
}
