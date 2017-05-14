import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ProductService} from '../../service/product.service';
import {Product} from '../product';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-manage-product',
  templateUrl: './manage-product.component.html',
  styleUrls: ['./manage-product.component.css']
})
export class ManageProductComponent implements OnInit {
  @ViewChild('inputPicture') inputPicture: ElementRef;

  productForm: Product = new Product();
  products: Product[];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.getAllProduct().subscribe(products => this.products = products);
  }

  edit(product: Product): void {
    this.productForm = Object.assign(new Product(), product);
  }

  getAllProduct() {
    this.productService.getAllProduct().subscribe(products => this.products = products);
  }

  onSubmit(f: NgForm) {
    let inputPictureEl: HTMLInputElement = this.inputPicture.nativeElement;
    this.productService.addProduct(this.productForm, inputPictureEl.files.item(0)).subscribe(() => {
      this.getAllProduct();
      f.resetForm();
    });
  }

  delete(product: Product) {
    this.productService.deleteProduct(product.id).subscribe(() => this.getAllProduct());
  }

}
