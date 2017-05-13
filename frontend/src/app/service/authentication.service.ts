import {Injectable, OnInit} from '@angular/core';
import {Headers, Http, Response} from '@angular/http';
import {Observable} from "rxjs/Rx";

@Injectable()
export class AuthenticationService {
  private serverUrl = 'http://localhost:8080/'
  public headers = new Headers({
    'Content-Type':'application/json',
    'Authorization': 'Bearer ' + this.getToken()
  });

  constructor(private http:Http) {
    this.checkCustomerLogin();
  }

  login(username:string, password:string): Observable<boolean>{
    let headers = new Headers({'Content-Type':'application/json'})
    return this.http.post(this.serverUrl + 'auth', JSON.stringify({username:username,password: password}),{headers: headers})
      .map((response:Response) => {

        // login successful if there's a jwt token in the response
        let token = response.json() && response.json().token;
        // if have token
        if (token){
          // store username and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser',JSON.stringify(response.json()));
          // return true to indicate successful login
          return true;

        }else {
          // return false to indicate failed login
          return false;
        }

      }).catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getToken():string{
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log("current user ===== "+ localStorage.getItem('currentUser'));
    var token = currentUser && currentUser.token;
    console.log(currentUser);
    return token ? token:"";
  }

  logout():void{
    // clear token remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }

  isCustomerLogin: boolean = false;
  checkCustomerLogin(): Observable<boolean> {
    return this.http.get(this.serverUrl + 'auth/customer', {headers: this.headers})
      .map((res: Response) => {
        if(res) {
          if(res.status === 200) {
            console.log("1");
            this.isCustomerLogin = true;
            return true;
          }
        }
        this.isCustomerLogin = false;
        return false;
      });
  }

}
