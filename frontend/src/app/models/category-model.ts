export class RecipeModel{
  id:number;
  steps:string;
  preparingTime:string;
  persons:string;

  constructor(steps:string,preparingTime:string,persons:string) {
    this.persons = persons;
    this.preparingTime = preparingTime;
    this.steps = steps;
  }
}
