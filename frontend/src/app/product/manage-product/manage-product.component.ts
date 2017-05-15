import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ProductService} from '../../service/product.service';
import {Product} from '../product';
import {FormGroup, NgForm} from '@angular/forms';
import {CartService} from '../../service/cart.service';

@Component({
  selector: 'app-manage-product',
  templateUrl: './manage-product.component.html',
  styleUrls: ['./manage-product.component.css']
})
export class ManageProductComponent implements OnInit {
  @ViewChild('inputPicture') inputPicture: ElementRef;

  productForm: Product = new Product();
  products: Product[];

  constructor(private productService: ProductService, private cartService: CartService) { }

  ngOnInit() {
    this.productService.getAllProduct().subscribe(products => this.products = products);
  }

  edit(product: Product): void {
    this.productForm = Object.assign(new Product(), product);
  }

  getAllProduct() {
    this.productService.getAllProduct().subscribe(products => this.products = products);
  }

  refinePicture() {
    if(this.productForm.picture) {
      const picArray = this.productForm.picture.split("/");
      this.productForm.picture = picArray[picArray.length - 1];
    }
  }

  onSubmit(myForm: FormGroup) {
    let inputPictureEl: HTMLInputElement = this.inputPicture.nativeElement;
    if(inputPictureEl.files.length > 0) {
      this.productService.addProductWithPic(this.productForm, inputPictureEl.files.item(0)).subscribe((product) => {
        this.getAllProduct();
        myForm.reset();
        this.resetForm();
        this.cartService.updateProduct(product);
      });
    }
    else {
      this.refinePicture();
      this.productService.addProduct(this.productForm).subscribe((product) => {
        this.getAllProduct();
        myForm.reset();
        this.resetForm();
        this.cartService.updateProduct(product);
      });
    }
  }

  delete(product: Product) {
    this.productService.deleteProduct(product.id).subscribe(() => this.getAllProduct());
  }

  resetForm() {
    this.productForm = new Product();
  }

}
