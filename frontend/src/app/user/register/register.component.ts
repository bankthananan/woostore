import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import {AuthenticationService} from '../../service/authentication.service';
import {UserAuth} from '../user-auth';
import {Router} from '@angular/router';

declare var Materialize: any;

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  customer: User = new User();

  constructor(private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit() {
    this.customer.userAuth = new UserAuth();
  }

  addCustomer() {
    this.authenticationService.addCustomer(this.customer).subscribe(
      res => {
        console.log("register=>" + res);
        if(res) {
          this.authenticationService.login(this.customer.userAuth.username, this.customer.userAuth.password).subscribe((res) => {
            this.router.navigate(['']).then($ => window.location.reload());
          });
        }
      }, (error) => Materialize.toast(error, 4000)
    );
  }

}
