import {Injectable, OnInit} from '@angular/core';
import {Http, Headers, Response, RequestOptions, URLSearchParams} from '@angular/http';
import 'rxjs';
import {Product} from '../product/product';
import {Cart} from '../cart/cart';
import {Item} from '../cart/item';
import {NavbarComponent} from '../navbar/navbar.component';

@Injectable()
export class CartService {

  constructor(private http: Http) {
    if(!this.getCart()) {
      this.resetCart();
    }
  }

  resetCart(): void {
    const cart: Cart = new Cart();
    cart.items = new Array();
    localStorage.setItem("cart", JSON.stringify(cart));
    this.update();
  }

  addProduct(product: Product, quantity: number): void {
    let itemTargetIndex: number = -1;
    const cart: Cart = this.getCart();
    console.log(cart);
    this.getCart().items.forEach((item, index) => {
      if(item.product.id === product.id) {
        itemTargetIndex = index;
      }
    });
    if(itemTargetIndex != -1) {
      cart.items[itemTargetIndex].quantity += quantity;
    }
    else {
      const item: Item = new Item();
      item.product = product;
      item.quantity = quantity;
      cart.items.push(item);
    }
    localStorage.setItem("cart", JSON.stringify(cart));
    this.update();
  }

  getCart(): Cart {
    return JSON.parse(localStorage.getItem("cart"));
  }

  countItem(): number {
    // let amount: number = 0;
    // this.getCart().items.forEach((item) => {
    //   amount += item.quantity;
    // });
    // return amount;
    return this.getCart().items.length;
  }

  update(): void {
    this.component.cartAmount = this.countItem();
  }

  component: NavbarComponent;

  registerNavComponent(component: NavbarComponent): void {
    this.component = component;
  }

  updateCart(cart: Cart): void {
    localStorage.setItem("cart", JSON.stringify(cart));
    this.update();
  }

  updateProduct(product: Product): void {
    const cart: Cart = this.getCart();
    cart.items.forEach((item) => {
      if(item.product.id === product.id) {
        item.product = product;
      }
    });
    this.updateCart(cart);
  }

}
