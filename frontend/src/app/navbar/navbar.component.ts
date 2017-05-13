import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../service/authentication.service';
import {Router} from '@angular/router';
import {User} from '../user/user';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  user: User;

  constructor(private router: Router, private authenticationService: AuthenticationService) {}

  ngOnInit() {
    if(this.isLogin()) {
      this.user = this.authenticationService.getUser();
    }
  }

  isLogin(): boolean {
    return this.authenticationService.isLogin();
  }

  logout(): void {
    this.authenticationService.logout();
    this.router.navigate(['']).then($ => window.location.reload());;
  }
}
