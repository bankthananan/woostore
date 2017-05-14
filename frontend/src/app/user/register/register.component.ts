import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import {AuthenticationService} from '../../service/authentication.service';
import {UserAuth} from '../user-auth';

declare var Materialize: any;

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  customer: User = new User();

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.customer.userAuth = new UserAuth();
  }

  addCustomer() {
    this.authenticationService.addCustomer(this.customer).subscribe(
      res => {
        console.log("register=>" + res);
        if(res) {
          this.authenticationService.login(res.user.userAuth.username, res.user.userAuth.password);
        }
      }, (error) => Materialize.toast(error, 4000)
    );
  }

}
