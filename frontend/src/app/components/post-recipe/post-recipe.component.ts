import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../../service/category/category.service';
import {FormBuilder, FormGroup, Validators, FormArray, ValidatorFn, AbstractControl} from '@angular/forms';
import {CategoryModel} from '../../models/category-model';
import {RecipeService} from "../../service/recipe/recipe.service";
import {NotificationService} from "../../service/notifications/notification.service";

interface Step {
  stepDescription: string;
}
@Component({
  selector: 'app-post-recipe',
  templateUrl: './post-recipe.component.html',
  styleUrls: ['./post-recipe.component.css']
})
export class PostRecipeComponent implements OnInit {

  recipeForm!: FormGroup;

  categoryModel: CategoryModel[];

  constructor(private formBuilder: FormBuilder, private categoryService:CategoryService,
              private recipeService:RecipeService, private notificationService:NotificationService) { }

  ngOnInit() {

    this.fetchData();
    this.recipeForm = this.formBuilder.group({
      recipeName: ['', Validators.required],
      category: [1, Validators.required],
      ingredients: this.formBuilder.array([this.createIngredient()]),
      steps: this.formBuilder.array([this.createStep()]),
      preparingTime: ['', Validators.required],
      numberOfPersons: ['', Validators.required],
    });
  }
  notAllowedSpecialCharacters(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const specialCharactersRegex = /[\\/]/;

      if (specialCharactersRegex.test(control.value)) {
        return { notallowed: true }; // Validation failed
      }

      return null; // Validation passed
    };
  }

  fetchData(): void {
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

  postData(recipe:any): void {
    this.recipeService.post(recipe)
      .subscribe(
        response => {
          console.log(response); // Do whatever you want with the data
          this.notificationService.showNotification("Adding new Recipe successfully!!");
          // this.showNotification();

          // this.openDialog();
        },
        error => {
          console.error(error);
          this.notificationService.showErrorNotification("Failed to add new recipe!")
        }
      );
  }

  adaptSteps(recipe:any){

    const stepsArray = recipe.steps.map((step: Step) => step.stepDescription);
    console.log("--------------------------")
    console.log(stepsArray)
    return stepsArray;
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
      ingredientName: ['', [Validators.required]],
      ingredientQuantity:['', [Validators.required]]
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

  submitForm() {
    console.log("call submit")
    if (this.recipeForm.valid) {
      console.log(this.recipeForm.value)
      const recipe = this.recipeForm.value;
      // console.log(recipe); // You can customize this logic to save the recipe data to your backend or perform any other actions
      recipe.steps=this.adaptSteps(recipe);
      recipe.user=10;
      console.log(JSON.stringify(recipe))
      this.postData(recipe);
    } else {
      // Handle form validation errors
      console.log(JSON.stringify(this.recipeForm.value))

      this.notificationService.showErrorNotification("Failed to validate recipe!")

    }

  }

  protected readonly CategoryModel = CategoryModel;
}
