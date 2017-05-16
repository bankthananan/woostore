import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import {UserAuth} from '../user-auth';
import {AuthenticationService} from '../../service/authentication.service';
import {FormGroup} from '@angular/forms';

@Component({
  selector: 'app-manage-user',
  templateUrl: './manage-user.component.html',
  styleUrls: ['./manage-user.component.css']
})
export class ManageUserComponent implements OnInit {
  userForm: User = new User();
  users: User[];

  search: string;

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

  onSubmit(myForm: FormGroup) {
    this.authenticationService.addStaff(this.userForm).subscribe((user) => {
      this.getAllUser();
      this.reset();
      myForm.reset();
    })
  }

  delete(user: User) {
    this.authenticationService.deleteUser(user.id).subscribe(() => this.getAllUser());
  }

  searchUser() {
    if(this.search != "") {
      this.authenticationService.searchUser(this.search).subscribe((users) => this.users = users);
    }
    else {
      this.getAllUser();
    }
  }

  getRoleString(user: User): string {
    return user.userAuth.authorities[user.userAuth.authorities.length - 1].name.toString().substring(5);
  }

  isAdmin(user: User): boolean {
    return this.authenticationService.hasRoleUser('admin', user);
  }

}
