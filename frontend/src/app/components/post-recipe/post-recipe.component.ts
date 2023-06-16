import {Component, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import {CategoryService} from '../../services/category/category.service';
import {CategoryModelModule} from '../../models/category-model/category-model.module';
import {RecipeService} from "../../services/recipe/recipe.service";

@Component({
  selector: 'app-post-recipe',
  templateUrl: './post-recipe.component.html',
  styleUrls: ['./post-recipe.component.css']
})
export class PostRecipeComponent implements OnInit {

  recipeForm!: FormGroup;

  categoryModel: CategoryModelModule[];

  constructor(private formBuilder: FormBuilder, private categoryService:CategoryService, private recipeService:RecipeService) { }

  ngOnInit() {

    this.fetchData();
    this.recipeForm = this.formBuilder.group({
      category: ['', Validators.required],
      ingredients: this.formBuilder.array([this.createIngredient()]),
      steps: this.formBuilder.array([this.createStep()]),
      preparingTime: ['', Validators.required],
      numberOfPersons: ['', Validators.required]
    });
  }

  fetchData(): void {
    this.categoryService.getAll()
      .subscribe(
        response => {
          this.categoryModel = response;
          console.log(this.categoryModel); // Do whatever you want with the data
        },
        error => {
          console.error(error);
        }
      );
  }

  postData(): void {
    this.recipeService.post(this.recipeForm)
      .subscribe(
        response => {
          console.log(response); // Do whatever you want with the data
        },
        error => {
          console.error(error);
        }
      );
  }


  get ingredientControls() {
    return this.recipeForm.get('ingredients') as FormArray;
  }

  get stepControls() {
    return this.recipeForm.get('steps') as FormArray;
  }

  createIngredient(): FormGroup {
    return this.formBuilder.group({
      ingredientName: ['', Validators.required],
      ingredientQuantity:['', Validators.required]
    });
  }

  createStep(): FormGroup {
    return this.formBuilder.group({
      stepDescription: ['', Validators.required]
    });
  }

  addIngredient() {
    this.ingredientControls.push(this.createIngredient());
  }

  removeIngredient(index: number) {
    this.ingredientControls.removeAt(index);
  }

  addStep() {
    this.stepControls.push(this.createStep());
  }

  removeStep(index: number) {
    this.stepControls.removeAt(index);
  }

  submitForm() {
    if (this.recipeForm.valid) {
      const recipe = this.recipeForm.value;
      // console.log(recipe); // You can customize this logic to save the recipe data to your backend or perform any other actions
      console.log(JSON.stringify(recipe))
      // this.postData();
    } else {
      // Handle form validation errors
    }

  }
}
