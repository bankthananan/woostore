import { Injectable } from '@angular/core';
import {Http, Headers, Response, RequestOptions, URLSearchParams} from '@angular/http';
import 'rxjs';
import {wooConfig} from '../woo.config';
import {Observable} from 'rxjs/Observable';
import {Transaction} from '../transaction/transaction';
import {AuthenticationService} from "app/service/authentication.service";

@Injectable()
export class TransactionService {

  constructor(private http: Http, private authenticationService: AuthenticationService) {}

  getAllTransaction(date: Date): Observable<Transaction[]> {
    let url = wooConfig.serverPath + 'transaction';
    if(date) {
      url += '/' + date.getTime() / 1000;
    }
    return this.http.get(url).map(res => res.json());
  }

  getPendingTransaction(date: Date): Observable<Transaction[]> {
    let url = wooConfig.serverPath + 'transaction/pending';
    if(date) {
      url += '/' + date.getTime() / 1000;
    }
    return this.http.get(url).map(res => res.json());
  }

  getPaidTransaction(date: Date): Observable<Transaction[]> {
    let url = wooConfig.serverPath + 'transaction/paid';
    if(date) {
      url += '/' + date.getTime() / 1000;
    }
    return this.http.get(url).map(res => res.json());
  }

  addTransaction(transaction: Transaction): Observable<Transaction> {
    return this.http.post(wooConfig.serverPath + 'transaction', transaction, { headers: this.authenticationService.headers }).map(res => res.json());
  }

  // getProduct(id: number) {
  //   return this.http.get(wooConfig.serverPath + 'product/' + id)
  //     .map(res => res.json());
  // }

}
