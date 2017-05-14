import {Injectable, OnInit} from '@angular/core';
import {Headers, Http, Response} from '@angular/http';
import {Observable} from "rxjs/Rx";
import {User} from '../user/user';
import {Authority} from '../user/authority';
import {wooConfig} from '../woo.config';
import {AuthorityName} from '../user/authority-name';

@Injectable()
export class AuthenticationService {

  public headers = new Headers({
    'Content-Type':'application/json',
    'Authorization': 'Bearer ' + this.getToken()
  });

  constructor(private http: Http) {}

  login(username: string, password: string): Observable<boolean> {
    const headers = new Headers({'Content-Type':'application/json'})
    return this.http.post(wooConfig.serverPath + 'auth', JSON.stringify({ username: username, password: password }),{headers})
      .map((response: Response) => {

        // login successful if there's a jwt token in the response
        let token = response.json() && response.json().token;
        // if have token
        if (token){
          // store username and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(response.json()));
          // return true to indicate successful login
          return true;

        }
        else {
          // return false to indicate failed login
          return false;
        }

      }).catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getToken(): string {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const token = currentUser && currentUser.token;
    return token ? token : "";
  }

  getUser(): User {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    return currentUser ? currentUser.user : null;
  }

  logout(): void {
    // clear token remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }

  isLogin(): boolean {
    return this.getToken() !== "";
  }

  hasRole(role : string): boolean {
    let hasRole: boolean = false;
    const user: User = this.getUser();
    if(user) {
      role = role.toUpperCase();
      user.userAuth.authorities.forEach((authority: Authority) => {
        const roleAuthorityName : AuthorityName = <AuthorityName>AuthorityName[('ROLE_' + role)];
        if(AuthorityName[authority.name] === AuthorityName[roleAuthorityName]) {
          hasRole = true;
        }
      });
    }
    return hasRole;
  }


}
