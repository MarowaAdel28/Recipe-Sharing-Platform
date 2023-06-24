
import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js/auto'
import { ApiService } from 'src/app/service/api.service';
import { subMonths , addMonths} from 'date-fns';

@Component({
  selector: 'app-line-chart',
  templateUrl: './line-chart.component.html',
  styleUrls: ['./line-chart.component.css']
})

export class LineChartComponent implements OnInit {
  result: any;
  count: any = [];
  date: any= [];
  chart: any = [];

  constructor(private service: ApiService) {
  }
  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.service.lineChartData().then((res) => {
      this.result = res;
      this.createTimeStrings();

      this.count[0] = this.result.earlier;
      this.count[1] = this.result.firstSixMonths;
      this.count[2] = this.result.secondSixMonths;
      this.count[3] = this.result.thirdSixMonths;

      console.log(this.count)

      this.chart = new Chart('chart', {
        type: 'line',
        data: {
          labels: this.date,
          datasets: [
            {
              data: this.count,
              borderColor: '#FCBA02',
              fill: false,
              label: 'number of registration',
              backgroundColor: '#FCBA02',
            },
          ],
        },
      });
    });

  }
  createTimeStrings() {
    let currDate = addMonths(new Date(), 1);

    let thirdSixMonth = subMonths(currDate, 6);
    let thirdSixMonthStr = thirdSixMonth.getMonth()+ "/"+ thirdSixMonth.getFullYear();

    let thirdSixMonthAdd = subMonths(currDate, 5);
    let thirdSixMonthAddStr = thirdSixMonthAdd.getMonth()+ "/"+ thirdSixMonthAdd.getFullYear();

    let secondSixMonth = subMonths(thirdSixMonth, 6);
    let secondSixMonthStr =  secondSixMonth.getMonth()+ "/" + secondSixMonth.getFullYear();

    let secondSixMonthAdd = subMonths(thirdSixMonth, 5);
    let secondSixMonthAddStr =  secondSixMonthAdd.getMonth()+ "/"+ secondSixMonthAdd.getFullYear();

    let firstSixMonth = subMonths(secondSixMonth, 6);
    let firstSixMonthStr = firstSixMonth.getMonth()+ "/"+ firstSixMonth.getFullYear();



    this.date[0] = "Earlier";
    this.date[1] = firstSixMonthStr + " - " + secondSixMonthStr;
    this.date[2] = secondSixMonthAddStr + " - " + thirdSixMonthStr;
    this.date[3] = thirdSixMonthAddStr + " - " + currDate.getMonth() + "/" + currDate.getFullYear();

  }
}








