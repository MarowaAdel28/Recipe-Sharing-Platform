import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-app-rate',
  templateUrl: './app-rate.component.html',
  styleUrls: ['./app-rate.component.css']
})
export class AppRateComponent {
  @Input() rate: number;
  filledStars: number;
  emptyStars: number;

  constructor() {}

  ngOnInit() {
    this.filledStars = Math.floor(this.rate);
    this.emptyStars = 5 - Math.ceil(this.rate);
  }

  protected readonly Array = Array;
}
