import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


import { AppComponent } from './app.component';
import { ListProductComponent } from './product/list-product/list-product.component';
import {ProductService} from './service/product.service';
import {MaterializeModule} from 'angular2-materialize/dist';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './user/login/login.component';
import {ProductRoutingModule} from './product/product-routing.module';
import {AppRoutingModule} from './app-routing.module';
import { RegisterComponent } from './user/register/register.component';
import {AuthenticationService} from './service/authentication.service';
import { DetailProductComponent } from './product/detail-product/detail-product.component';
import { SearchProductComponent } from './product/search-product/search-product.component';
import { ListTransactionComponent } from './admin/list-transaction/list-transaction.component';
import {TransactionService} from './service/transaction.service';
import { AddUserComponent } from './admin/add-user/add-user.component';

@NgModule({
  declarations: [
    AppComponent,
    ListProductComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    DetailProductComponent,
    SearchProductComponent,
    ListTransactionComponent,
    AddUserComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    MaterializeModule,
    ProductRoutingModule,
    AppRoutingModule
  ],
  providers: [ProductService, TransactionService, AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule {}
