import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../service/product.service';
import {Product} from '../product';
import {AuthenticationService} from '../../service/authentication.service';
import {Router} from '@angular/router';
import {SearchQuery} from '../search-product/search-query';

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {
  products: Product[];
  listProductComponent = this;
  constructor(private productDataService: ProductService,
    private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit() {
    this.productDataService.getAllProduct()
      .subscribe(products => {
        this.products = products;
        this.products.push(this.products[0]);
        this.products.push(this.products[0]);
        this.products.push(this.products[0]);
        this.products.push(this.products[0]);
        this.products.push(this.products[0]);
      });
  }

  searchProduct(search: SearchQuery, listProductCompoent: ListProductComponent) {
    // console.log(listProductCompoent);
    listProductCompoent.productDataService.searchProduct(search).subscribe(products => listProductCompoent.products = products);
  }

  viewDetail(product) {
    this.router.navigate(['detail/'+ product.id]);
  }

  truncateDesc(desc: string) {
    if(desc.length > 50) {
      return desc.substring(0, 47) + '...';
    }
    return desc;
  }

}
