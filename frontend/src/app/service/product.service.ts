import { Injectable } from '@angular/core';
import {Http, Headers, Response, RequestOptions, URLSearchParams} from '@angular/http';
import 'rxjs';
import {wooConfig} from '../woo.config';
import {SearchQuery} from '../product/search-product/search-query';
import {Product} from '../product/product';
import {Observable} from 'rxjs/Observable';
import {AuthenticationService} from './authentication.service';

@Injectable()
export class ProductService {

  constructor(private http: Http, private authenticationService : AuthenticationService) {}

  getAllProduct() {
    return this.http.get(wooConfig.serverPath + 'product')
      .map(res => res.json());
  }

  getProduct(id: number) {
    return this.http.get(wooConfig.serverPath + 'product/' + id)
      .map(res => res.json());
  }

  searchProduct(search: SearchQuery) {
    return this.http.post(wooConfig.serverPath + 'product/search', search)
      .map(res => res.json());
  }

  addProduct(product: Product, file: any) {
    let formData = new FormData();
    formData.append('file', file);
    let header = new Headers({'Authorization': 'Bearer ' + this.authenticationService.getToken()});
    return this.http.post(wooConfig.serverPath + 'product/image', formData, {headers : header})
      .flatMap(filename => {
        product.picture = filename.text();
        let headers = this.authenticationService.headers;
        let options = new RequestOptions({headers: headers, method: 'post'});
        let body = JSON.stringify(product);
        return this.http.post(wooConfig.serverPath + 'product', body, options)
          .map(res => {
            return res.json();
          })
          .catch((error: any) => {
          return Observable.throw(new Error(error.status));
          });
      });
  }

  deleteProduct(id: number) {
    return this.http.delete(wooConfig.serverPath + 'product/' + id, {headers: this.authenticationService.headers});
  }

}
