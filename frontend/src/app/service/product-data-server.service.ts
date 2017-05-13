import { Injectable } from '@angular/core';
import {Http, Headers, Response, RequestOptions, URLSearchParams} from '@angular/http';
import 'rxjs';

@Injectable()
export class ProductDataServerService {
  serverPath: string = 'http://localhost:8080/';
  constructor(private http: Http) {}

  getAllProduct() {
    return this.http.get(this.serverPath + 'product')
      .map(res => res.json());
  }

  getProduct(id: number) {
    return this.http.get(this.serverPath + 'product/' + id)
      .map(res => res.json());
  }

}
