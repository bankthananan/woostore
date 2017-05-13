import { Injectable } from '@angular/core';
import {Http, Headers, Response, RequestOptions, URLSearchParams} from '@angular/http';
import 'rxjs';
import {wooConfig} from '../woo.config';

@Injectable()
export class ProductService {
  constructor(private http: Http) {}

  getAllProduct() {
    return this.http.get(wooConfig.serverPath + 'product')
      .map(res => res.json());
  }

  getProduct(id: number) {
    return this.http.get(wooConfig.serverPath + 'product/' + id)
      .map(res => res.json());
  }

}
