import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../service/authentication.service';
import {Router} from '@angular/router';
import {User} from '../user/user';
import {CartService} from '../service/cart.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  user: User;

  cartAmount: number = this.cartService.countItem();

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private cartService: CartService
  ) {}

  ngOnInit() {
    if(this.isLogin()) {
      this.user = this.authenticationService.getUser();
    }
    this.cartService.registerNavComponent(this);
  }

  isLogin(): boolean {
    return this.authenticationService.isLogin();
  }

  hasRole(role: string): boolean {
    return this.authenticationService.hasRole(role);
  }

  logout(): void {
    this.authenticationService.logout();
    this.router.navigate(['']).then($ => window.location.reload());;
  }
}
