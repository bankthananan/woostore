import { Injectable } from '@angular/core';
import {Http, Headers, Response, RequestOptions, URLSearchParams} from '@angular/http';
import 'rxjs';

@Injectable()
export class ProductDataServerService {
  constructor(private http: Http) {}

  getProductsData() {
    return this.http.get('http://localhost:8080/product')
      .map(res => res.json());
  }

}
