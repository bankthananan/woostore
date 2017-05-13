import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../service/authentication.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string = '';
  password : string = '';
  error = ''

  constructor(private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.authenticationService.checkCustomerLogin()
      .subscribe( isLogin => {
        if(isLogin === true) {
          this.router.navigate(['list']);
        }
      });
  }

  login() {
    console.log('username =>'+ this.username + 'password =>'+ this.password);
    this.authenticationService.login(this.username, this.password)
      .subscribe(result => {
        if(result === true) {
          // login success
          this.router.navigate(['list']).then($ => window.location.reload());
        }else {
          // login failed
          this.error = 'Username or Password is incorrect';
        }
      }, (error) => {
        this.error = error;
      })
  }


}
