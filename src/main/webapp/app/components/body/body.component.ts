import { Component, OnInit, Input, ViewChild, TemplateRef } from '@angular/core';
import { BodyService } from '../../services/body-services/body.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
const Highcharts = require('highcharts');

import { NgbdModalContent } from './body.component.modal'
// const Highcharts3d = require('highcharts/highcharts-3d.src');
// Highcharts3d(Highcharts)
require('highcharts/modules/exporting')(Highcharts);
require('highcharts/modules/drilldown')(Highcharts);
// require('../../resources/js/data.js')(Highcharts);

@Component({
  moduleId: module.id,
  selector: "app-content",
  templateUrl: "./body.html"
})

export class BodyComponent implements OnInit {
  @Input() data: any;
  @ViewChild("content") private model: TemplateRef<any>;
  @ViewChild("topModel") private topModel: TemplateRef<any>;

  @ViewChild("tableContent") private tableContent: TemplateRef<any>;
  tilesArray: any;
  contentBody: any = {};
  tableData: any = {
    "buttonName": "",
    "title": "",
    "tableData": []
  };

  private totalCount: any = 0;
  drillDown(e: any, chart: any) {
    this.drillUptotalCount = 0;
    for (var i = 0; i < e.seriesOptions.data.length; i++) {
      this.totalCount = this.totalCount + e.seriesOptions.data[i][1];
    }
    chart.setTitle(null, { text: "Total " + this.totalCount });

  }
  private drillUptotalCount: any = 0;
  drillUp(e: any, chart: any) {
    //chart.setTitle(null,{ text:  e.point.name });
    for (var i = 0; i < e.seriesOptions.data.length; i++) {
      this.drillUptotalCount = this.drillUptotalCount + e.seriesOptions.data[i].y;
    }
    chart.setTitle(null, { text: "Total " + this.drillUptotalCount });
    this.totalCount = 0;

  }
  ngOnInit() {
    this.modalService.open(this.model, { size: "lg" });
  }
  constructor(private service: BodyService, private modalService: NgbModal) {
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
    this.tableData = data.data;
    this.modalService.open(this.topModel, { size: "lg" });
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
      if (dataObj.buttonName !== undefined) {
        return dataObj.data.length > 0 ? true : false;
      } else
        if (dataObj.buttonName !== undefined) {
          return dataObj.data.length > 0 ? true : false;
        }
        else {
          return false;
        }
    } catch (e) {
      return false;
    }
  }
  chartType(id: any): boolean {
    try {

      return this.charTypeJSON[id] === 'column' ? true : false;

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
  private charTypeJSON: any = {};
  constructChartJson(obj: any, chartData: any) {
    this.charTypeJSON[obj.id] = chartData.type;
    var chartObj = this.getChartJSONObject(obj, chartData);
    this.contentBody[obj.id] = chartObj;
  }
  getChartJSONObject(obj: any, chartData: any): any {
    var chartObj = {
      chart: {
        type: ''
      },
      colors: ['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9',
        '#f15c80', '#e4d354', '#2b908f', '#f45b5b', '#91e8e1'],
      title: {
        text: chartData.title
      }, subtitle: {
        text: ''
      },
      credits: {
        enabled: false
      },
      xAxis: {
        type: 'category',
        categories: []
      },
      yAxis: {
        min: 0,
        title: {
          text: chartData.yaxisTitle
        }
      },

      plotOptions: {

      },
      series: [],
      drilldown: {}
    };
    switch (chartData.type) {
      // case "column":
      //   chartObj.chart.type = "column"
      //   chartObj.plotOptions["series"] = {

      //     pointPadding: 0.2,
      //     borderWidth: 0,
      //     dataLabels: {
      //       enabled: true
      //     }

      //   }
      //   // chartObj.title.text = chartData.title;
      //   var categories = [];
      //   var chartDataValues = [];
      //   for (var i = 0; i < chartData.data.length; i++) {
      //     var dataObj = chartData.data[i];
      //     categories.push(dataObj.name);
      //     chartDataValues.push(dataObj.value);
      //   }
      //   chartObj.xAxis.categories = categories;
      //   chartObj.series = [{
      //     name: chartData.xaxisTitle,
      //     data: chartDataValues
      //   }];
      //   // chartObj.yAxis.title.text = chartData.yaxisTitle;
      //   break;
      case "column":
        chartObj.chart.type = "column"
        chartObj.plotOptions["series"] = {

          pointPadding: 0.2,
          borderWidth: 0,
          dataLabels: {
            enabled: true
          }

        }
        chartObj.xAxis["type"] = 'category';
        delete chartObj.xAxis.categories;
        // chartObj.title.text = chartData.title;
        var categories = new Array();
        var total = 0;
        var chartDataValues = new Array();
        var drilldownArray = new Array();
        for (var i = 0; i < chartData.data.length; i++) {
          var dataObj = chartData.data[i];
          categories.push(dataObj.name);
          total = total + dataObj.value;
          var drilldownData = dataObj.data;
          if (drilldownData.length > 0) {
            var drillDownObj: any = {};
            var __this = this;
            drillDownObj.point = {
              events: {
                click: function () {
                  if (this.x != undefined)
                    // modal trigger
                    __this.modalService.open(__this.tableContent, { size: "lg" });
                },
                drilldown: function () {
                  var chart = this;
                  chart.setTitle(null, {
                    text: "after drilldown subtitle"
                  });
                },
                drillup: function () {
                  var chart = this;
                  chart.setTitle(null, {
                    text: "after drillup subtitle"
                  });
                }
              }
            }
            drillDownObj.name = dataObj.name;
            drillDownObj.id = dataObj.name + i;
            drillDownObj.data = new Array();
            for (var j = 0; j < drilldownData.length; j++) {
              var obj = drilldownData[j];
              drillDownObj.data.push([
                obj.name,
                obj.value]);
            }
            drilldownArray.push(drillDownObj);
            chartDataValues.push({
              name: dataObj.name,
              y: dataObj.value,
              drilldown: drillDownObj.id
            })
          } else {
            chartDataValues.push({
              name: dataObj.name,
              y: dataObj.value,
              drilldown: null
            });
          }
        }
        // chartObj.xAxis.categories = categories;
        chartObj.series = [{
          name: chartData.xaxisTitle,
          data: chartDataValues
        }];
        chartObj.drilldown = {
          "series": drilldownArray
        }
        chartObj.subtitle.text = "Total " + total;
        // chartObj.yAxis.title.text = chartData.yaxisTitle;
        break;
      case "bar_compound":
        chartObj.chart.type = "bar";
        chartObj.plotOptions["series"] = {
          borderWidth: 0,
          dataLabels: {
            enabled: false,

          }
        }
        chartObj.plotOptions["series"]["stacking"] = "normal";
        delete chartObj.xAxis.categories;
        //delete chartObj.yAxis;
        this.constructChartObject(chartData, chartObj);
        //   var categories = [];
        //   var seriesJson = {};
        //   for (var i = 0; i < chartData.data.length; i++) {
        //     var dataObj = chartData.data[i];
        //     categories.push(dataObj.name);
        //     for (var j = 0; j < dataObj.data.length; j++) {
        //       var innerDataObj = dataObj.data[j];
        //       if (seriesJson[innerDataObj.name] === undefined) {
        //         seriesJson[innerDataObj.name] = [innerDataObj.value];
        //       } else {
        //         seriesJson[innerDataObj.name].push(innerDataObj.value)
        //       }
        //     }
        //   }
        // //  chartObj.xAxis.categories = categories;
        //   for (var key in seriesJson) {
        //     chartObj.series.push({
        //       name: key,
        //       data: seriesJson[key]
        //     });
        //   }

        break;
      case "column_compound":
        chartObj.chart.type = "column"
        chartObj.plotOptions["series"] = {

          pointPadding: 0.2,
          borderWidth: 0,
          dataLabels: {
            enabled: true
          }

        }
        delete chartObj.xAxis.categories;
        //delete chartObj.yAxis;
        this.constructChartObject(chartData, chartObj);
        // var categories = [];
        // var seriesJson = {};
        // for (var i = 0; i < chartData.data.length; i++) {
        //   var dataObj = chartData.data[i];
        //   categories.push(dataObj.name);
        //   for (var j = 0; j < dataObj.data.length; j++) {
        //     var innerDataObj = dataObj.data[j];
        //     if (seriesJson[innerDataObj.name] === undefined) {
        //       seriesJson[innerDataObj.name] = [innerDataObj.value];
        //     } else {
        //       seriesJson[innerDataObj.name].push(innerDataObj.value)
        //     }
        //   }
        // }
        // chartObj.xAxis.categories = categories;
        // for (var key in seriesJson) {
        //   chartObj.series.push({
        //     name: key,
        //     data: seriesJson[key]
        //   });
        // }
        break;
      case "column_stack":
        chartObj.chart.type = "column"
        chartObj.plotOptions["column"] = {
          stacking: 'normal',
          dataLabels: {
            enabled: false,

          }
        }
        delete chartObj.xAxis.categories;
        // delete chartObj.yAxis;
        this.constructChartObject(chartData, chartObj);
        // var categories = [];
        // var seriesJson = {};
        // for (var i = 0; i < chartData.data.length; i++) {
        //   var dataObj = chartData.data[i];
        //   categories.push(dataObj.name);
        //   for (var j = 0; j < dataObj.data.length; j++) {
        //     var innerDataObj = dataObj.data[j];
        //     if (seriesJson[innerDataObj.name] === undefined) {
        //       seriesJson[innerDataObj.name] = [innerDataObj.value];
        //     } else {
        //       seriesJson[innerDataObj.name].push(innerDataObj.value)
        //     }
        //   }
        // }
        // chartObj.xAxis.categories = categories;
        // for (var key in seriesJson) {
        //   chartObj.series.push({
        //     name: key,
        //     data: seriesJson[key]
        //   });
        // }
        break;
    }
    return chartObj;
  }

  public chartChange(chartType: string, id: any): void {

    var chartObject = this.contentBody[id];
    console.log(JSON.stringify(chartObject));
    console.log(this.chartObjects[id].container.id);
    chartObject.chart.type = chartType
    chartObject.chart.renderTo = this.chartObjects[id].container.id;
    chartObject.chart.plotOptions = {
      series: {
        pointPadding: 0.2,
        borderWidth: 0,
        dataLabels: {
          enabled: true
        }
      },
      pie: {
        plotBorderWidth: 0,
        allowPointSelect: true,
        cursor: 'pointer',
        size: '100%',
        dataLabels: {
          enabled: true,
          format: '{point.name}: <b>{point.y}</b>'
        }
      }
    }
    if (chartType === "pie") {
      chartObject.tooltip = {
        pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
        shared: true

      }
    } else {
      delete chartObject.tooltip;
    }
    this.chartObjects[id] = new Highcharts.Chart(chartObject);
    // this.contentBody[id]=chartObject;
  }
  private chartObjects: any = {};
  public saveChartInstance(chartObj: any, id) {
    this.chartObjects[id] = chartObj;
  }
  private constructChartObject(chartData: any, chartObj: any) {
    var categories = [];
    var seriesJsonObject = [];
    var drilldownArray = [];
    var total = 0;
    for (var i = 0; i < chartData.data.length; i++) {
      var dataObj = chartData.data[i];
      var seriesJson = [];
      var seriesObject = { name: "", data: [] };
      seriesObject.name = dataObj.name;
      seriesObject.data = seriesJson;
      seriesJsonObject.push(seriesObject);
      // categories.push(dataObj.name);
      for (var k = 0; k < dataObj.data.length; k++) {
        var innerDataObj = dataObj.data[k];
        total = total + innerDataObj.value;
        console.log(innerDataObj.name);
        //   var innerDataObject = {name:"",y:"",drilldown:""};

        //   seriesObject.data.push(innerDataObject);
        //   if (chartData.drilldownData[innerDataObj.name] === undefined) {
        //     innerDataObject.drilldown = null;
        //   } else {
        //      innerDataObject.drilldown = chartData.drilldownData[innerDataObj.name].name;
        //   }
        // }
        // var drilldownData = chartData.drilldownData[innerDataObj.name];

        var drilldownData = innerDataObj.data;
        if (drilldownData.length > 0) {
          // var drillDownObj: any = {};
          // drillDownObj.name = drilldownData.name;
          // drillDownObj.id = drilldownData.name;
          // drillDownObj.data = new Array();
          // for (var j = 0; j < drilldownData.data.length; j++) {
          //   var obj = drilldownData.data[j];
          //   drillDownObj.data.push({
          //     name: obj.name,
          //     y: obj.value,
          //     drilldown: null
          //   });
          // }
          var drillDownObj: any = {};
          var __this = this;
          drillDownObj.point = {
            events: {
              click: function () {
                if (this.x != undefined)
                  // modal trigger
                  __this.modalService.open(__this.tableContent, { size: "lg" });
              },
              drilldown: function () {
                var chart = this;
                chart.setTitle(null, {
                  text: "after drilldown subtitle"
                });
              },
              drillup: function () {
                var chart = this;
                chart.setTitle(null, {
                  text: "after drillup subtitle"
                });
              }
            }
          }
          drillDownObj.name = seriesObject.name;
          drillDownObj.id = innerDataObj.name + i;
          drillDownObj.data = new Array();
          for (var j = 0; j < drilldownData.length; j++) {
            var obj = drilldownData[j];
            drillDownObj.data.push([
              obj.name,
              obj.value,

            ]);
          }
          drilldownArray.push(drillDownObj);
          seriesJson.push({
            name: innerDataObj.name,
            y: innerDataObj.value,
            drilldown: drillDownObj.id
          })
        } else {
          seriesJson.push({
            name: innerDataObj.name,
            y: innerDataObj.value,
            drilldown: null
          });
        }

      }
    }
    chartObj.drilldown = {
      "series": drilldownArray
    }
    //  chartObj.xAxis.categories = categories;
    // for (var key in seriesJson) {
    chartObj.series = seriesJsonObject
    chartObj.subtitle.text = "Total " + total;
    // }
  }
}