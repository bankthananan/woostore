import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import {UserAuth} from '../user-auth';
import {AuthenticationService} from '../../service/authentication.service';

@Component({
  selector: 'app-manage-user',
  templateUrl: './manage-user.component.html',
  styleUrls: ['./manage-user.component.css']
})
export class ManageUserComponent implements OnInit {
  userForm: User = new User();
  users: User[];

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.userForm.userAuth = new UserAuth();
    this.getAllUser();
  }

  edit(user: User): void {
    this.userForm = Object.assign(new User(), user);
  }

  reset() {
    this.userForm = new User();
    this.userForm.userAuth = new UserAuth();
  }

  getAllUser() {
    this.authenticationService.getAllUser().subscribe(users => this.users = users);
  }

}
