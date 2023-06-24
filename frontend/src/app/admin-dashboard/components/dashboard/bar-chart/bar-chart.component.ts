import { Component, OnInit} from '@angular/core';
import{FormGroup,FormControl } from '@angular/forms';
import { ApiService } from 'src/app/service/api.service';
import { Chart } from 'chart.js/auto';

@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.css'],

})


export class BarChartComponent implements OnInit {
  type="Age"
  result: any;
  count:any = [];
  chart: any = [];
  
 gender(){
    let genders = ["Female", "Male"];
    this.service.barChartDataGender().then((res: any) => {
    this.result=res
    this.count[0] = this.result.femalesCount;
    this.count[1] = this.result.malesCount;
    this.count[2] = 0;

  this.chart = new Chart('myChart', {
        type: 'bar',
        data: {
          labels:genders,
          datasets: [
            {
              data:this.count,
              borderColor: '#FCBA02',
              label: 'Count',
              backgroundColor: [
                'rgba(102, 0, 204)',
                'rgba( 51, 0, 102)'
              ],
            }]}});
          })
          this.chart.destroy();

 }
 
 age(){
  let age = ["Young Adults 17-30", "Middle Age 31-45", "Old 46 <"];
  this.service.barChartDataAge().then((res: any) => {
  this.result=res
  this.count[0] = this.result.youngAdults;
  this.count[1] = this.result.old;
  this.count[2] = this.result.middleAged;
  this.count[3] = 0;

this.chart = new Chart('myChart', {
      type: 'bar',
      data: {
        labels:age,
        datasets: [
          {
            data:this.count,
            borderColor: '#FCBA02',
            label: 'Count',
            backgroundColor: [
              'rgb(255, 153, 204)',
              'rgb(245, 88, 166)',
              'rgb(246, 29, 137)',
            ],
          }]}});
        })
        this.chart.destroy();

}

  userForm = new FormGroup({
    type: new FormControl(),
  });

  constructor(private service:ApiService) {
  }
  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.age()
  }
}





