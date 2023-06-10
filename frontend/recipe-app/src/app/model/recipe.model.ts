import { Ingredient } from "./ingredient.model";

export class Recipe {
  constructor(
  category: string,
  ingredients: Ingredient[],
  steps: string[],
  preparingTime: string,
  numberOfPersons: number
  ){}
}
