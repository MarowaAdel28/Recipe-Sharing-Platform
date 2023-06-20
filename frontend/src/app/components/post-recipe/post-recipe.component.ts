import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators, FormArray} from '@angular/forms';
import {CategoryService} from '../../services/category/category.service';
import {RecipeService} from "../../services/recipe/recipe.service";
import {CategoryModel} from "../../models/category-model";

export interface Step {
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

  constructor(private formBuilder: FormBuilder, private categoryService:CategoryService, private recipeService:RecipeService) { }

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

  postData(recipe:any): void {
    this.recipeService.post(recipe)
      .subscribe(
        response => {
          console.log(response); // Do whatever you want with the data
        },
        error => {
          console.error(error);
        }
      );
  }

  adaptSteps(recipe:any){
    // const recipe = this.recipeForm.value;
    // Assuming you have the JSON object stored in a variable called 'recipe':
    // const stepsArray = recipe.steps.map(step => step.stepDescription);
    const stepsArray = recipe.steps.map((step: Step) => step.stepDescription);
console.log("--------------------------")
    console.log(stepsArray)
// Now you can assign the stepsArray to your form control or use it as needed.
//     this.recipeForm.patchValue({ steps: stepsArray });
// console.log(JSON.stringify(this.recipeForm.value))
    return stepsArray;
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

  // createStep(): FormArray<ɵElement<String, null>> {
  //   return this.formBuilder.array<String>();
  // }

  // createStep(): FormArray<ɵElement<string, null>> {
  //   return this.formBuilder.array<string>([''], Validators.required);
  // }

  // createStep(): FormArray<FormGroup> {
  //   const steps = this.recipeForm.value.steps.map((step: { stepDescription: string }) =>
  //     this.formBuilder.group({
  //       stepDescription: [step.stepDescription, Validators.required]
  //     })
  //   );
  //
  //   return this.formBuilder.array<FormGroup>(steps);
  // }



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
      recipe.steps=this.adaptSteps(this.recipeForm.value);
      recipe.user=1;
      // const recipeJson = JSON.stringify(recipe)
      console.log(JSON.stringify(recipe))
      this.postData(recipe);
      // this.postData();
    } else {
      // Handle form validation errors
    }

  }
}
