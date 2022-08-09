import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from "@angular/core";
import { TableModel, TableItem, TableHeaderItem } from "carbon-components-angular";
import { Color, BaseChartDirective, Label } from "ng2-charts";
import * as pluginAnnotations from "chartjs-plugin-annotation";
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import * as pluginDataLabels from 'chartjs-plugin-datalabels';

@Component({
  selector: "goal-overview",
  templateUrl: "./goal-overview.component.html",
  styleUrls: ["./goal-overview.component.scss"]
})
export class GoalOverviewComponent implements OnInit {
  @Input() selectedGoal;
  @Input() columnsForTable;
  @Input() rowsForTable;
  @Input() selectedIndex;
  public deleteSelectedGoal: any;
  public deleteUserEnabled: boolean;

  @Output() closeContainer = new EventEmitter();
  public selectedGoalDetails: any;

  public addGoalPageEnabled: boolean;

  public lineChartData: ChartDataSets[] = [
    { data: [11500, 13000 ], label: 'Stock' },
    { data: [15500, 18000], label: 'Mutual funds' },
    { data: [16500, 21000], label: 'Fixed deposit' }
  ];

  public lineChartLabels: Label[] = [
    'Investemnt Amount', 'Current Amount'
  ];
  public lineChartOptions: ChartOptions & { annotation: any } = {
    responsive: true,
    scales: {
      // We use this empty structure as a placeholder for dynamic theming.
      xAxes: [{}],
      yAxes: [
        {
          id: "y-axis-0",
          position: "left"
        },
        {
          id: "y-axis-1",
          position: "right",
          gridLines: {
            color: "rgba(255,0,0,0.3)"
          },
          ticks: {
            fontColor: "red"
          }
        }
      ]
    },
    annotation: {
      annotations: [
        {
          type: "line",
          mode: "vertical",
          scaleID: "x-axis-0",
          value: "March",
          borderColor: "orange",
          borderWidth: 2,
          label: {
            enabled: true,
            fontColor: "orange",
            content: "LineAnno"
          }
        }
      ]
    }
  };
  public lineChartColors: Color[] = [
    {
      // grey
      backgroundColor: "rgba(148,159,177,0.2)",
      borderColor: "lightgreen",
      pointBackgroundColor: "rgba(148,159,177,1)",
      pointBorderColor: "#fff",
      pointHoverBackgroundColor: "#fff",
      pointHoverBorderColor: "rgba(148,159,177,0.8)"
    },
    {
      // dark grey
      backgroundColor: "rgba(77,83,96,0.2)",
      borderColor: "yellow",
      pointBackgroundColor: "rgba(77,83,96,1)",
      pointBorderColor: "#fff",
      pointHoverBackgroundColor: "#fff",
      pointHoverBorderColor: "rgba(77,83,96,1)"
    } ,
    {
      // dark grey
      backgroundColor: "rgba(77,83,96,0.2)",
      borderColor: "#5499c7",
      pointBackgroundColor: "rgba(77,83,96,1)",
      pointBorderColor: "#fff",
      pointHoverBackgroundColor: "#fff",
      pointHoverBorderColor: "rgba(77,83,96,1)"
    }
  ];

  public barChartColors = [
    {
      backgroundColor: '#5d6d7e',
      pointBackgroundColor: 'rgba(78, 180, 189, 1)',
      pointHoverBackgroundColor: 'rgba(151,187,205,1)',
      borderColor: 'rgba(0,0,0,0',
      pointBorderColor: '#fff',
      pointHoverBorderColor: 'rgba(151,187,205,1)'
    }, 
    {
      backgroundColor: '#aed6f1',
      pointBackgroundColor: 'rgba(78, 180, 189, 1)',
      pointHoverBackgroundColor: 'rgba(151,187,205,1)',
      borderColor: 'rgba(0,0,0,0',
      pointBorderColor: '#fff',
      pointHoverBorderColor: 'rgba(151,187,205,1)'
    }
  ];

  public lineChartLegend = true;
  public lineChartType = "line";
  public lineChartPlugins = [pluginAnnotations];

  // Bar chart
  public barChartOptions: ChartOptions = {
    responsive: true,
    // We use these empty structures as placeholders for dynamic theming.
    scales: { xAxes: [{}], yAxes: [{}] },
    plugins: {
      datalabels: {
        anchor: 'end',
        align: 'end',
      }
    }
  };
  public barChartLabels: Label[] = [ 'Stock', 'Mutual Fund', 'FIxed Deposit'];
  public barChartType: ChartType = 'bar';
  public barChartLegend = true;
  public barChartPlugins = [pluginDataLabels];

  public barChartData: ChartDataSets[] = [
    { data: [4000, 2000, 4000, 3000 ], label: 'Investment Value' },
    { data: [22000, 5000, 2000, 0], label: 'Current Value' }
  ];


  @ViewChild(BaseChartDirective, { static: true }) chart: BaseChartDirective;

  constructor() {}

  // this.chart.update();

  ngOnInit() {}

  

  closeBlock(message) {
    this.deleteUserEnabled = false;

    this.closeContainer.emit(message);
  }
  public addInvestment(goal, index) {
    this.addGoalPageEnabled = true;
    goal.name = "Goal #" + index + ":" + goal.name;
    this.selectedGoalDetails = goal;
  }
  public closeGoalOverView(event) {
    this.addGoalPageEnabled = false;
    this.deleteUserEnabled = false;
  }

  public deleteGoal(goal, index) {
    this.deleteSelectedGoal = goal;
    this.deleteUserEnabled = true; 
  }
}
