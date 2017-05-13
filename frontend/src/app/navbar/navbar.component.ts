import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../service/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  constructor(private router: Router, private authenticationService: AuthenticationService) {}
  // isLogin: boolean = this.authenticationService.isCustomerLogin;
  ngOnInit() {
    // console.log("2");
    // console.log("isLogin => "+ this.isLogin);
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['login']).then($=>window.location.reload());;
  }
}
