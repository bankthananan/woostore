import {Component, Input, OnInit} from '@angular/core';
import {SearchQuery} from './search-query';
import {ProductService} from '../../service/product.service';
import {Product} from '../product';

@Component({
  selector: 'app-search-product',
  templateUrl: './search-product.component.html',
  styleUrls: ['./search-product.component.css']
})
export class SearchProductComponent implements OnInit {
  search: SearchQuery = new SearchQuery();

  @Input('searchProduct') searchProduct;
  @Input('listProductComponent') listProductComponent;
  constructor(private productService: ProductService) { }

  ngOnInit() {
  }

  onSearch() {
    this.searchProduct(this.search, this.listProductComponent);
  }

}
