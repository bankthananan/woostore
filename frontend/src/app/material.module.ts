import {MdButtonModule, MdCheckboxModule, MdSidenavModule} from '@angular/material';
import {NgModule} from '@angular/core';

const modules = [
  MdButtonModule,
  MdCheckboxModule,
  MdSidenavModule
];

@NgModule({
  imports: modules,
  exports: modules,
})
export class MaterialModule { }
