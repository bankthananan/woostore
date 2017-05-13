import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../service/product.service';
import {Product} from '../product';
import {AuthenticationService} from '../../service/authentication.service';
import {Router} from '@angular/router';
import {TransactionService} from "app/service/transaction.service";
import {Transaction} from '../../transaction/transaction';

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {
  products: Product[];

  constructor(private productDataService: ProductService,
    private authenticationService: AuthenticationService, private router: Router, private transactionService: TransactionService) { }

  ngOnInit() {
    // console.log(this.authenticationService.isLogin());
    // console.log(this.authenticationService.hasRole('admin'));
    this.transactionService.getAllTransaction().subscribe((transactions: Transaction[]) => console.log(transactions));
    this.productDataService.getAllProduct()
      .subscribe(products => {
        this.products = products
        this.products.push(this.products[0]);
        this.products.push(this.products[0]);
        this.products.push(this.products[0]);
        this.products.push(this.products[0]);
      });
  }

  viewDetail(product) {
    this.router.navigate(['detail/'+ product.id]);
  }

}
