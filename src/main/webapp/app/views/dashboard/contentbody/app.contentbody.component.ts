import { Component, OnInit, Input, ViewChild, TemplateRef } from '@angular/core';
import { MyFcaService } from '../../../app.component.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { NgbdModalContent } from './app.component.model'
const Highcharts = require('highcharts');

const Highcharts3d = require('highcharts/highcharts-3d.src');
Highcharts3d(Highcharts)
require('./js/exporting.js')(Highcharts);
require('./js/drilldown.js')(Highcharts);
// require('./js/data.js')(Highcharts);

Highcharts.setOptions({
  colors: ['#058DC7', '#50B432', '#ED561B']
});

@Component({
  moduleId: module.id,
  selector: "app-content",
  templateUrl: "./content-bootstrap.html"
})

export class ContentSection implements OnInit {
  @ViewChild("content") private model: TemplateRef<any>;
  @ViewChild("topModel") private topModel: TemplateRef<any>;


  tilesArray: any;
  contentBody: any = {};
  tableData: any = {"buttonName": "",
      "title": "",
         "tableData": []
  };
  ngOnInit() {
    this.modalService.open(this.model);
  }

  constructor(private service: MyFcaService, private modalService: NgbModal) {
    this.service.getNumberOfTiltes().subscribe(
      (resUserData) => {
        this.tilesArray = this.chunk(resUserData, 2);
        for (var i = 0; i < resUserData.length; i++) {
          var obj = resUserData[i];
          if (obj.type === "tile") {
            this.getTileJson(obj.id);
          } else {
            this.getChartJson(obj);
          }
        }
      })
  }
  openDataTable(data) {
    this.tableData = data;
    this.modalService.open(this.topModel);
  }
  chunk(arr, size): any {
    var newArr = [];
    for (var i = 0; i < arr.length; i += size) {
      newArr.push(arr.slice(i, i + size));
    }
    return newArr;
  }
  notEmpty(dataObj: any): boolean {
    try {
      //console.log(dataObj)
      if (dataObj.datatable.buttonName === undefined) {
        return false;
      } else {
        return dataObj.datatable.tableData[0].data.length > 3 ? true : false;
      }
    } catch (e) {
      return false;
    }
  }
  isTopThree(dataObj: any): boolean {
    try {
      if (dataObj.datatable.buttonName === undefined) {
        return false;
      } else {

        return dataObj.datatable.tableData[0].data.length <= 3 ? true : false;
      }
    } catch (e) {
      return false;
    }
  }
  // options: any;
  // getJSONObject(jsonString: string) {
  //   //  debugger;
  //   // console.log(jsonString)
  //   return JSON.parse(jsonString);
  // }

  getTileJson(id: string) {
    this.contentBody[id] = [];
    this.service.getTilteJson(id).subscribe(
      (resUserData) => {
        this.contentBody[id] = resUserData;
      })

  }
  getChartJson(obj: any) {
    this.service.getChartJson(obj.id).subscribe(
      (chartData) => {
        // this.contentBody[id] = chartData;
        this.constructChartJson(obj, chartData)
      })
  }

  constructChartJson(obj: any, chartData: any) {
    var chartObj = this.getChartJSONObject(obj, chartData);
    this.contentBody[obj.id] = chartObj;

  }
  getChartJSONObject(obj: any, chartData: any): any {
    var chartObj = {
      chart: {
        type: ''
      },
      title: {
        text: chartData.title
      },
      xAxis: {
        categories: []
      },
      yAxis: {
        min: 0,
        title: {
          text: chartData.yaxisTitle
        }
      },
      tooltip: {
        pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
        shared: true
      },
      plotOptions: {

      },
      series: []
    };
    switch (chartData.type) {
      case "column":
        chartObj.chart.type = "column"
        chartObj.plotOptions["column"] = {
          pointPadding: 0.2,
          borderWidth: 0
        }
        // chartObj.title.text = chartData.title;
        var categories = [];
        var chartDataValues = [];
        for (var i = 0; i < chartData.data.length; i++) {
          var dataObj = chartData.data[i];
          categories.push(dataObj.name);
          chartDataValues.push(dataObj.value);
        }
        chartObj.xAxis.categories = categories;
        chartObj.series = [{
          name: chartData.xaxisTitle,
          data: chartDataValues
        }];
        // chartObj.yAxis.title.text = chartData.yaxisTitle;
        break;
      case "bar":
        chartObj.chart.type = "bar";
        chartObj.plotOptions["series"] = {}
        chartObj.plotOptions["series"]["stacking"] = "normal";
        var categories = [];
        var seriesJson = {};
        for (var i = 0; i < chartData.data.length; i++) {
          var dataObj = chartData.data[i];
          categories.push(dataObj.name);
          for (var j = 0; j < dataObj.data.length; j++) {
            var innerDataObj = dataObj.data[j];
            if (seriesJson[innerDataObj.name] === undefined) {
              seriesJson[innerDataObj.name] = [innerDataObj.value];
            } else {
              seriesJson[innerDataObj.name].push(innerDataObj.value)
            }
          }
        }
        chartObj.xAxis.categories = categories;
        for (var key in seriesJson) {
          chartObj.series.push({
            name: key,
            data: seriesJson[key]
          });
        }

        break;
      case "column-compond":
        chartObj.chart.type = "column"
        chartObj.plotOptions["column"] = {
          pointPadding: 0.2,
          borderWidth: 0
        }

        var categories = [];
        var seriesJson = {};
        for (var i = 0; i < chartData.data.length; i++) {
          var dataObj = chartData.data[i];
          categories.push(dataObj.name);
          for (var j = 0; j < dataObj.data.length; j++) {
            var innerDataObj = dataObj.data[j];
            if (seriesJson[innerDataObj.name] === undefined) {
              seriesJson[innerDataObj.name] = [innerDataObj.value];
            } else {
              seriesJson[innerDataObj.name].push(innerDataObj.value)
            }
          }
        }
        chartObj.xAxis.categories = categories;
        for (var key in seriesJson) {
          chartObj.series.push({
            name: key,
            data: seriesJson[key]
          });
        }
        break;
      case "column-stack":
        chartObj.chart.type = "column"
        chartObj.plotOptions["column"] = {
          stacking: 'normal',
          dataLabels: {
            enabled: false,
            color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
          }
        }


        var categories = [];
        var seriesJson = {};
        for (var i = 0; i < chartData.data.length; i++) {
          var dataObj = chartData.data[i];
          categories.push(dataObj.name);
          for (var j = 0; j < dataObj.data.length; j++) {
            var innerDataObj = dataObj.data[j];
            if (seriesJson[innerDataObj.name] === undefined) {
              seriesJson[innerDataObj.name] = [innerDataObj.value];
            } else {
              seriesJson[innerDataObj.name].push(innerDataObj.value)
            }
          }
        }
        chartObj.xAxis.categories = categories;
        for (var key in seriesJson) {
          chartObj.series.push({
            name: key,
            data: seriesJson[key]
          });
        }
        break;
    }
    return chartObj;
  }
}