import { Component, OnInit,ViewChild} from '@angular/core';
import {Chart} from 'chart.js'
import { ApiService } from 'src/app/Services/api.service';

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.css']
})
export class PieChartComponent implements OnInit {
  result: any;
  count: any = [];
  type: any = ["Waiting", "Accepted", "Rejected"];

  chart: any = [];

  constructor(private service:ApiService) {
  }

  ngOnInit(): void {
  }


  ngAfterViewInit()  {
    this.service.pieChartData().then((res) => {
      this.result = res;
      this.count[0] = this.result.waiting;
      this.count[1] = this.result.accepted;
      this.count[2] = this.result.rejected;
      

      this.chart = new Chart('canvas', {
        type: 'pie',
        data: {
          labels: this.type,
          datasets: [
            { 
              data: this.count,
              borderColor: '#bdf2d5',
              label: 'count',
              backgroundColor: [
                'rgb(255, 205, 86)',
                'rgb(54, 162, 235)',
                'rgb(255, 99, 132)'
              ],
            },
            
          ],
        },
      });
    });
  }
}
