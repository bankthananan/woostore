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
import {TransactionService} from './service/transaction.service';
import { ManageUserComponent } from './user/manage-user/manage-user.component';
import { ManageProductComponent } from './product/manage-product/manage-product.component';
import { ManageTransactionComponent } from './transaction/manage-transaction/manage-transaction.component';
import {UserRoutingModule} from './user/user-routing.module';
import {TransactionRoutingModule} from './transaction/transaction-routing.module';
import { ManageCartComponent } from './cart/manage-cart/manage-cart.component';
import {CartRoutingModule} from './cart/cart-routing.module';
import {CartService} from './service/cart.service';
import { DetailTransactionComponent } from './transaction/detail-transaction/detail-transaction.component';

@NgModule({
  declarations: [
    AppComponent,
    ListProductComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    DetailProductComponent,
    SearchProductComponent,
    ManageUserComponent,
    ManageProductComponent,
    ManageTransactionComponent,
    ManageCartComponent,
    DetailTransactionComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    MaterializeModule,
    ProductRoutingModule,
    UserRoutingModule,
    TransactionRoutingModule,
    CartRoutingModule,
    AppRoutingModule
  ],
  providers: [
    ProductService,
    TransactionService,
    AuthenticationService,
    CartService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
