import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'steps_pipe'
})
export class StepsPipe implements PipeTransform {

    transform(value: string): any {
      let arr = value.split("/");
      arr .pop();
       return arr;
      }
  }


