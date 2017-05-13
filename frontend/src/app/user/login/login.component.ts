import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../../service/authentication.service';
import {ActivatedRoute, Router} from '@angular/router';

declare var Materialize: any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string = '';
  password : string = '';

  constructor(private router: Router, private authenticationService: AuthenticationService) {}

  ngOnInit() {
    if(this.authenticationService.isLogin()) {
      this.router.navigate(['']);
    }
  }

  login() {
    this.authenticationService.login(this.username, this.password).subscribe(result => {
        if(result) {
          this.router.navigate(['list']).then($ => window.location.reload());
        }
      }, (error) => {
        if(error === 'Unauthorized') {
          Materialize.toast('Username or Password is incorrect', 4000)
        }
        else {
          Materialize.toast(error, 4000)
        }
      });
  }


}
