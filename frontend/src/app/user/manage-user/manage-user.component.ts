import { Component, OnInit } from '@angular/core';
import {User} from '../user';

@Component({
  selector: 'app-manage-user',
  templateUrl: './manage-user.component.html',
  styleUrls: ['./manage-user.component.css']
})
export class ManageUserComponent implements OnInit {
  userForm: User = new User();

  constructor() { }

  ngOnInit() {
  }

}
