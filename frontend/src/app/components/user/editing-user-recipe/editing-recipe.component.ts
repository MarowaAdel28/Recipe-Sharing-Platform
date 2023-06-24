import {Component, OnInit} from '@angular/core';
import {NotificationService} from "../../../service/notifications/notification.service";
import {RecipeService} from "../../../service/recipe/recipe.service";
import {ActivatedRoute} from "@angular/router";
import {AbstractControl, FormArray, FormBuilder, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {CategoryModel} from "../../../models/category-model";
import {CategoryService} from "../../../service/category/category.service";

@Component({
  selector: 'app-editing-recipe',
  templateUrl: './editing-recipe.component.html',
  styleUrls: ['./editing-recipe.component.css']
})
export class EditingRecipeComponent implements OnInit{

  recipeForm!: FormGroup;

  categoryModel: CategoryModel[];

  recipe:any;

  constructor(private formBuilder: FormBuilder, private categoryService:CategoryService, private recipeService: RecipeService,
              private _activatedRoute:ActivatedRoute, private notificationService:NotificationService) {}

  ngOnInit() {
    this.fetchRecipeData();
    this.fetchCategoryData();
    this.recipeForm = this.formBuilder.group({
      recipeName: ['', Validators.required],
      category: [1, Validators.required],
      ingredients: this.formBuilder.array([this.createIngredient()]),
      steps: this.formBuilder.array([this.createStep()]),
      preparingTime: ['', Validators.required],
      numberOfPersons: ['', Validators.required],
    });
  }

  fetchRecipeData() {
    this._activatedRoute.paramMap.subscribe(parms => {
      let id = parms.get('id')

      if (typeof id === "string") {
        this.recipeService.getById(parseInt(id)).subscribe(response=>{
          this.recipe=response;
          console.log(this.recipe)
          this.displayRecipeData(response);
        }, error => {
          this.notificationService.showErrorNotification("Oops! something is wrong")
        })
      }
    })
  }
  displayRecipeData(recipeData:any) {
    this.recipeForm.patchValue({
      recipeName: recipeData.name,
      category: recipeData.category.id,
      ingredient: recipeData.recipeHasIngredientsList,
      steps: recipeData.steps,
      preparingTime: recipeData.preparingTime,
      numberOfPersons: recipeData.preparingTime
    });
    alert(this.recipeForm)
  }
  fetchCategoryData(): void {
    this.categoryService.getAll()
      .subscribe(
        response => {
          this.categoryModel = response;
          console.log(this.categoryModel); // Do whatever you want with the data
        },
        error => {
          this.notificationService.showErrorNotification("Oops!! something is wrong!!")
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
    console.log("create ingredient")
    return this.formBuilder.group({
      ingredientName: ['', [Validators.required,this.notAllowedSpecialCharacters()]],
      ingredientQuantity:['', [Validators.required,this.notAllowedSpecialCharacters()]]
    });
  }

  createStep(): FormGroup {
    return this.formBuilder.group({
      stepDescription: ['', [Validators.required,this.notAllowedSpecialCharacters()]]
    });
  }

  addIngredient() {
    console.log("add ingredient")
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
  notAllowedSpecialCharacters(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const specialCharactersRegex = /[!@#$%^&*()_+\-=\[\]{};:"\\|<>\/?]/;

      if (specialCharactersRegex.test(control.value)) {
        return { notallowed: true }; // Validation failed
      }

      return null; // Validation passed
    };
  }
}
