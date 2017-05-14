import { Injectable } from '@angular/core';
import {Http, Headers, Response, RequestOptions, URLSearchParams} from '@angular/http';
import 'rxjs';
import {wooConfig} from '../woo.config';
import {Observable} from 'rxjs/Observable';
import {Transaction} from '../transaction/transaction';

@Injectable()
export class TransactionService {

  constructor(private http: Http) {}

  getAllTransaction(): Observable<Transaction[]> {
    return this.http.get(wooConfig.serverPath + 'transaction').map(res => res.json());
  }

  getPendingTransaction(): Observable<Transaction[]> {
    return this.http.get(wooConfig.serverPath + 'transaction/pending').map(res => res.json());
  }

  getPaidTransaction(): Observable<Transaction[]> {
    return this.http.get(wooConfig.serverPath + 'transaction/paid').map(res => res.json());
  }

  // getProduct(id: number) {
  //   return this.http.get(wooConfig.serverPath + 'product/' + id)
  //     .map(res => res.json());
  // }

}
