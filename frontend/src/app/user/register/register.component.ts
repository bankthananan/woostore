import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import {AuthenticationService} from '../../service/authentication.service';
import {UserAuth} from '../user-auth';

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
      }
    )
  }
}
