import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../service/product.service';
import {Product} from '../product';
import {ActivatedRoute, Params} from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-detail-product',
  templateUrl: './detail-product.component.html',
  styleUrls: ['./detail-product.component.css']
})
export class DetailProductComponent implements OnInit {
  product: Product;
  constructor(private productDataService: ProductService, private route : ActivatedRoute, private location: Location) { }

  ngOnInit() {
    this.route.params.switchMap((params: Params) => this.productDataService.getProduct(+params['id'])).subscribe((product: Product) => {
      if(product) {
        this.product = product;
        this.product.description = 'Lorem ipsum dolor sit amet, vel libero, quisque suscipit nonummy nulla velit vitae proin, quam sed purus adipiscing, mi adipiscing consequat a molestie et. Cursus facilisis, sed etiam euismod eleifend dictum molestie, libero non, mollis ac sed, viverra eu tempor ut auctor et. Eros velit ante ligula interdum sit sed. Tempus placerat et est amet purus purus. Nulla tempor aliquam ante. Dolor pellentesque, quis penatibus congue, elit justo velit odio pede, suscipit nulla. Quis quam at ligula convallis fames cursus, aliqua sed id aliquet nam imperdiet magna, eros pellentesque a sapien nec ligula in, leo penatibus placerat dui fermentum tincidunt diam.Lorem ipsum dolor sit amet, vel libero, quisque suscipit nonummy nulla velit vitae proin, quam sed purus adipiscing, mi adipiscing consequat a molestie et. Cursus facilisis, sed etiam euismod eleifend dictum molestie, libero non, mollis ac sed, viverra eu tempor ut auctor et. Eros velit ante ligula interdum sit sed. Tempus placerat et est amet purus purus. Nulla tempor aliquam ante. Dolor pellentesque, quis penatibus congue, elit justo velit odio pede, suscipit nulla. Quis quam at ligula convallis fames cursus, aliqua sed id aliquet nam imperdiet magna, eros pellentesque a sapien nec ligula in, leo penatibus placerat dui fermentum tincidunt diam.Lorem ipsum dolor sit amet, vel libero, quisque suscipit nonummy nulla velit vitae proin, quam sed purus adipiscing, mi adipiscing consequat a molestie et. Cursus facilisis, sed etiam euismod eleifend dictum molestie, libero non, mollis ac sed, viverra eu tempor ut auctor et. Eros velit ante ligula interdum sit sed. Tempus placerat et est amet purus purus. Nulla tempor aliquam ante. Dolor pellentesque, quis penatibus congue, elit justo velit odio pede, suscipit nulla. Quis quam at ligula convallis fames cursus, aliqua sed id aliquet nam imperdiet magna, eros pellentesque a sapien nec ligula in, leo penatibus placerat dui fermentum tincidunt diam.Lorem ipsum dolor sit amet, vel libero, quisque suscipit nonummy nulla velit vitae proin, quam sed purus adipiscing, mi adipiscing consequat a molestie et. Cursus facilisis, sed etiam euismod eleifend dictum molestie, libero non, mollis ac sed, viverra eu tempor ut auctor et. Eros velit ante ligula interdum sit sed. Tempus placerat et est amet purus purus. Nulla tempor aliquam ante. Dolor pellentesque, quis penatibus congue, elit justo velit odio pede, suscipit nulla. Quis quam at ligula convallis fames cursus, aliqua sed id aliquet nam imperdiet magna, eros pellentesque a sapien nec ligula in, leo penatibus placerat dui fermentum tincidunt diam.Lorem ipsum dolor sit amet, vel libero, quisque suscipit nonummy nulla velit vitae proin, quam sed purus adipiscing, mi adipiscing consequat a molestie et. Cursus facilisis, sed etiam euismod eleifend dictum molestie, libero non, mollis ac sed, viverra eu tempor ut auctor et. Eros velit ante ligula interdum sit sed. Tempus placerat et est amet purus purus. Nulla tempor aliquam ante. Dolor pellentesque, quis penatibus congue, elit justo velit odio pede, suscipit nulla. Quis quam at ligula convallis fames cursus, aliqua sed id aliquet nam imperdiet magna, eros pellentesque a sapien nec ligula in, leo penatibus placerat dui fermentum tincidunt diam.Lorem ipsum dolor sit amet, vel libero, quisque suscipit nonummy nulla velit vitae proin, quam sed purus adipiscing, mi adipiscing consequat a molestie et. Cursus facilisis, sed etiam euismod eleifend dictum molestie, libero non, mollis ac sed, viverra eu tempor ut auctor et. Eros velit ante ligula interdum sit sed. Tempus placerat et est amet purus purus. Nulla tempor aliquam ante. Dolor pellentesque, quis penatibus congue, elit justo velit odio pede, suscipit nulla. Quis quam at ligula convallis fames cursus, aliqua sed id aliquet nam imperdiet magna, eros pellentesque a sapien nec ligula in, leo penatibus placerat dui fermentum tincidunt diam.';
      }
    })
  }

  back() {
    this.location.back();
  }

}
