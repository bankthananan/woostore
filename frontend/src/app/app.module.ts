import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


import { AppComponent } from './app.component';
import { ListProductComponent } from './product/list-product/list-product.component';
import {ProductDataServerService} from './service/product-data-server.service';
import {MaterializeModule} from 'angular2-materialize/dist';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import {ProductRoutingModule} from './product/product-routing.module';
import {AppRoutingModule} from './app-routing.module';
import { AddProductComponent } from './product/add-product/add-product.component';
import { RegisterComponent } from './register/register.component';
import { AddAdminComponent } from './admin/add-admin/add-admin.component';
import {AuthenticationService} from './service/authentication.service';

@NgModule({
  declarations: [
    AppComponent,
    ListProductComponent,
    NavbarComponent,
    LoginComponent,
    AddProductComponent,
    RegisterComponent,
    AddAdminComponent
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
  providers: [ProductDataServerService, AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule {}
