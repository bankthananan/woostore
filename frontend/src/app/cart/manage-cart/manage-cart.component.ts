import { Component, OnInit } from '@angular/core';
import {CartService} from '../../service/cart.service';
import {Cart} from '../cart';
import {Item} from '../item';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../service/authentication.service';
import {User} from '../../user/user';
import {Transaction} from '../../transaction/transaction';
import {TransactionService} from "app/service/transaction.service";
import {Product} from '../../product/product';

@Component({
  selector: 'app-manage-cart',
  templateUrl: './manage-cart.component.html',
  styleUrls: ['./manage-cart.component.css']
})
export class ManageCartComponent implements OnInit {

  user: User;
  cart: Cart = this.cartService.getCart();

  constructor(private cartService: CartService, private transactionService: TransactionService, private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit() {
    this.user = this.authenticationService.getUser();
  }

  updateCart(): void {
    this.cartService.updateCart(this.cart);
  }

  viewDetail(item: Item) {
    this.router.navigate(['detail/'+ item.product.id]);
  }

  deleteItem(itemTarget: Item) {
    this.cart.items = this.cart.items.filter((item) => item.product.id != itemTarget.product.id);
    this.updateCart();
  }

  isLogin(): boolean {
    return this.authenticationService.isLogin();
  }

  getTotalPrice(): number {
    let totalPrice: number = 0;
    this.cart.items.forEach((item) => totalPrice += item.product.price * item.quantity);
    return totalPrice;
  }

  resetCart(): void {
    this.cartService.resetCart();
    this.cart = this.cartService.getCart();
  }

  hasItem(): boolean {
    return this.cartService.countItem() > 0;
  }

  confirmPurchase(): void {
    const transaction: Transaction = new Transaction();
    transaction.items = this.cart.items;
    transaction.owner = this.authenticationService.getUser();
    this.transactionService.addTransaction(transaction).subscribe(transaction => console.log(transaction));
    this.resetCart();
  }

}
