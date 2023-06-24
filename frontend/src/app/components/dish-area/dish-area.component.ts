import {Component} from '@angular/core';
import {CategoryService} from "../../services/category/category.service";
import {CategoryModel} from "../../models/category-model";

@Component({
  selector: 'app-dish-area',
  templateUrl: './dish-area.component.html',
  styleUrls: ['./dish-area.component.css']
})
export class DishAreaComponent {

  categories: CategoryModel[];

  constructor(private categoryService: CategoryService) {
  }

  ngOnInit() {
    this.categoryService.getTop3().subscribe((categories) => {
        this.categories = categories;
      },
      (error) => {
        console.error('Failed to load categories', error);
      });
  }
}
