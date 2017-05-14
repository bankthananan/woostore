import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../service/product.service';
import {Product} from '../product';

@Component({
  selector: 'app-manage-product',
  templateUrl: './manage-product.component.html',
  styleUrls: ['./manage-product.component.css']
})
export class ManageProductComponent implements OnInit {

  productForm: Product = new Product();
  products: Product[];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.getAllProduct().subscribe(products => this.products = products);
  }

  edit(product: Product): void {
    this.productForm = Object.assign(new Product(), product);
  }

}
