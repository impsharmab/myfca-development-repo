import { Component, OnInit, Input, ViewChild, TemplateRef, OnDestroy } from '@angular/core';
import { DashboardBodyService } from '../../services/dashboard-body-services/dashboard-body.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
const Highcharts = require('highcharts');

import { NgbdModalContent } from './dashboard-body.component.modal'
// const Highcharts3d = require('highcharts/highcharts-3d.src');
// Highcharts3d(Highcharts)
require('highcharts/modules/exporting')(Highcharts);
require('highcharts/modules/drilldown')(Highcharts);
require('highcharts/modules/no-data-to-display')(Highcharts);
// require('../../resources/js/data.js')(Highcharts);

@Component({
  moduleId: module.id,
  selector: "app-content",
  templateUrl: "./dashboard-body.html"
})

export class DashboardBodyComponent implements OnInit, OnDestroy {
  @Input() data: any;
  @ViewChild("content") private model: TemplateRef<any>;
  @ViewChild("topModel") private topModel: TemplateRef<any>;
  @ViewChild("statisticModel") private statisticModel: TemplateRef<any>;

  tilesArray: any;
  contentBody: any = {};
  tableData: any = {
    "buttonName": "",
    "title": "",
    "tableData": []
  };
  private unitAndAverage = {}
  private showPieButton = {}
  private bc: any
  private totalCount: any = 0;
  private avarage: any = 0;
  private pieButtons: any = {};
  private chartRawData: any = {};
  private unit: any = "";
  drillDown(e: any, chart: any, id: any) {
    var obj = this.unitAndAverage[id]
    this.drillUptotalCount = 0;
    for (var i = 0; i < e.seriesOptions.data.length; i++) {
      this.totalCount = this.totalCount + e.seriesOptions.data[i][1];
    }

    if (obj.unit == "$" && obj.avarage == false) {
      chart.setTitle(null, { text: "Total " + obj.unit + Math.round(this.totalCount).toLocaleString() + "<br>" + e.point.name });
    } else if (obj.unit == "$" && obj.avarage == true) {
      chart.setTitle(null, { text: "Average " + obj.unit + Math.round(this.totalCount).toLocaleString() + "<br>" + e.point.name });
    } else if (obj.unit == "%") {
      chart.setTitle(null, { text: "Total " + Math.round(this.totalCount).toLocaleString() + obj.unit + "<br>" + e.point.name });
    } else {
      chart.setTitle(null, { text: "Total " + obj.unit + Math.round(this.totalCount).toLocaleString() + "<br>" + e.point.name });

    }
    // chart.setTitle(null, { text: "Total " + obj.unit + Math.round(this.totalCount).toLocaleString() + "<br>" + e.point.name });

  }
  private drillUptotalCount: any = 0;
  drillUp(e: any, chart: any, id: any) {
    //chart.setTitle(null,{ text:  e.point.name });
    var obj = this.unitAndAverage[id];
    for (var i = 0; i < e.seriesOptions.data.length; i++) {
      this.drillUptotalCount = this.drillUptotalCount + e.seriesOptions.data[i].y;
    }

    if (obj.unit == "$" && obj.avarage == false) {
      chart.setTitle(null, { text: "Total " + obj.unit + Math.round(this.drillUptotalCount).toLocaleString() });
    } else if (obj.unit == "$" && obj.avarage == true) {

      chart.setTitle(null, { text: "Average " + obj.unit + Math.round(this.drillUptotalCount).toLocaleString() });
    } else if (obj.unit == "%") {
      chart.setTitle(null, { text: "Total " + Math.round(this.drillUptotalCount).toLocaleString() + obj.unit });
    } else {
      chart.setTitle(null, { text: "Total " + obj.unit + Math.round(this.drillUptotalCount).toLocaleString() });

    }
    // chart.setTitle(null, { text: "Total " + obj.unit + Math.round(this.drillUptotalCount).toLocaleString() });
    this.totalCount = 0;

  }
  ngOnInit() {
    this.data = JSON.parse(sessionStorage.getItem("CurrentUser"))
    this.modalService.open(this.model, { size: "lg" });
  }
  ngOnDestroy() {
    this.tilesArray = [];
    this.contentBody = {};
    this.tableData = {
      "buttonName": "",
      "title": "",
      "tableData": []
    };
    this.unitAndAverage = {}
    this.showPieButton = {}
    this.bc
    this.totalCount = 0;
    this.avarage = 0;
    this.pieButtons = {};
    this.chartRawData = {};
  }
  constructor(private service: DashboardBodyService, private modalService: NgbModal) {
    this.initializeContent();
  }
  reload() {
    this.tilesArray = [];
    this.contentBody = {};
    this.tableData = {
      "buttonName": "",
      "title": "",
      "tableData": []
    };
    this.unitAndAverage = {}
    this.showPieButton = {}
    this.bc
    this.totalCount = 0;
    this.avarage = 0;
    this.pieButtons = {};
    this.chartRawData = {};
    this.initializeContent();
  }
  initializeContent() {
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
  notEmptyBadge(data: any): boolean {
    try {
      if ((data).length > 0) {
        return true;
      }
      else {
        return false;
      }
    } catch (e) {
      return false;
    }
  }

  errorInArray(data: any): boolean {
    try {
      if ((data).length < 1 || typeof data == undefined || !(data instanceof Array)) {
        return true;
      }
      else {
        return false;
      }
    } catch (e) {
      return false;
    }
  }
  errorInObject(data: any): boolean {
    try {
      if (data === undefined || data === null || JSON.stringify(data) === '{}') {
        return true;
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
        this.chartRawData[obj.id] = chartData;
        this.constructChartJson(obj, chartData)
      })
  }
  private charTypeJSON: any = {};
  constructChartJson(obj: any, chartData: any) {
    this.charTypeJSON[obj.id] = chartData.type;
    var chartObj = this.getChartJSONObject(obj, chartData);
    this.contentBody[obj.id] = chartObj;
    //console.log("this.chartObjects.customer_first :" + this.button)
  }
  private chartData: any;
  getChartJSONObject(obj: any, chartData: any): any {
    var unit;// = this.unit;
    if (chartData.unit == "$") {
      unit = chartData.unit;
    }
    this.chartData = chartData;

    this.unitAndAverage[obj.id] = { unit: chartData.unit, avarage: chartData.avarage };
    this.showPieButton[obj.id] = chartData.customer_first;
    // this.bc = this.bc[obj.id] = { data: chartData.data };
    // alert("unit " + unit);
    // console.log("this.chartObjects.customer_first :" + chartData.data)
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
        categories: [],
        labels: {
          style: {
            fontSize: '7.5px'
            //fontFamily: 'Verdana, sans-serif',

          }
        }
      },
      yAxis: {
        min: 0,
        title: {
          text: chartData.yaxisTitle
        },
        stackLabels: {
          enabled: true,
          style: {
            fontSize: 10,
            //color: 'gray'
          }
        }
      },
      // tooltip: {
      //   pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{unit}</b><b>{point.y}</b> <br/>',
      //   shared: true
      // },

      plotOptions: {

      },
      series: [
        // { visible:false }
      ],
      drilldown: {}

    }; Highcharts.setOptions({
      lang: {
        thousandsSep: ',',
        drillUpText: '◁ Back'
      }
    });
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
      case "pie":
        chartObj.chart.type = "pie"
        // chartObj.plotOptions["series"] = {


        //   pointPadding: 0.2,
        //   borderWidth: 0,
        //   dataLabels: {
        //     enabled: true
        //   }

        // },
        chartObj.plotOptions = {
          pie: {
            allowPointSelect: false,
            cursor: 'pointer',
            dataLabels: {
              enabled: true,
              format: '<b>{point.name}</b>: <br>{point.y}<br>({point.percentage:.1f}) %',
              style: {
                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
              }
            }
          }

        },
          chartObj.xAxis["type"] = 'category';
        delete chartObj.xAxis.categories;
        // chartObj.title.text = chartData.title;
        var categories = new Array();
        var total = 0;
        var avagerCount = 0;
        var chartDataValues = new Array();
        var drilldownArray = new Array();
        var pieButtons = new Array();
        this.pieButtons[obj.id] = pieButtons;
        for (var i = 0; i < chartData.data.length; i++) {
          i == 0 ? pieButtons.push("NAT") : "";
          var dataObj = chartData.data[i];
          categories.push(dataObj.name);
          avagerCount = avagerCount + 1;
          total = total + parseInt(dataObj.value);
          var drilldownData = dataObj.data;
          if (drilldownData.length > 0) {
            var drillDownObj: any = {};
            var __this = this;
            drillDownObj.point = {
              events: {
                click: function () {
                  if (this.x != undefined)
                    // modal trigger
                    __this.service.getTableJson("").subscribe(
                      (resUserData) => {
                        __this.statisticModelData = resUserData;
                        __this.modalService.open(__this.statisticModel, { windowClass: 'datatable' });
                      })
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
              pieButtons.indexOf(obj.name) > -1 ? "" : pieButtons.push(obj.name);
              drillDownObj.data.push([
                obj.name,
                Math.round(obj.value)]);
            }
            drilldownArray.push(drillDownObj);
            chartDataValues.push({
              name: dataObj.name,
              y: Math.round(dataObj.value),
              drilldown: drillDownObj.id
            })
          } else {
            chartDataValues.push({
              name: dataObj.name,
              y: Math.round(dataObj.value),
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
        if (chartDataValues.length > 0) {
          if (chartData.avarage) {
            total = total / avagerCount;
          }
          if (chartData.unit == "$" && chartData.avarage == false) {
            chartObj.subtitle.text = "Total " + chartData.unit + Math.round(total).toLocaleString();
          } else if (chartData.unit == "$" && chartData.avarage == true) {

            chartObj.subtitle.text = "Average " + chartData.unit + Math.round(total).toLocaleString();
          } else if (chartData.unit == "%") {
            chartObj.subtitle.text = "Total " + Math.round(total).toLocaleString() + chartData.unit;
          } else {
            chartObj.subtitle.text = "Total " + chartData.unit + Math.round(total).toLocaleString();

          } // chartObj.yAxis.title.text = chartData.yaxisTitle;
        }
        break;
      case "column":
        chartObj.chart.type = "column"
        chartObj.plotOptions["series"] = {

          pointPadding: 0.2,
          borderWidth: 0,
          dataLabels: {
            enabled: true,
            allowOverlap: true,
            overFlow: 'none',
            crop: false,
            rotation: -70,
            y: -25,
            style: {
              fontSize: '9px'
            },
          }

        }
        // chartObj.drilldown = {
        //   activeAxisLabelStyle: {
        //     textDecoration: 'none',
        //     fontStyle: 'italic',
        //     fontSize: 10
        //   },
        //   activeDataLabelStyle: {
        //     textDecoration: 'none',
        //     fontSize: 100
        //   },
        // }
        chartObj.xAxis["type"] = 'category';
        //chartObj.yAxis.stackLabels.style.fontSize= 100,
        delete chartObj.xAxis.categories;
        // chartObj.title.text = chartData.title;
        var categories = new Array();
        var total = 0;
        var avagerCount = 0;
        var chartDataValues = new Array();
        var drilldownArray = new Array();
        for (var i = 0; i < chartData.data.length; i++) {
          var dataObj = chartData.data[i];
          categories.push(dataObj.name);
          avagerCount = avagerCount + 1;
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
                    __this.service.getTableJson("").subscribe(
                      (resUserData) => {
                        __this.statisticModelData = resUserData;
                        __this.modalService.open(__this.statisticModel, { windowClass: 'datatable' });
                      })
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
                Math.round(obj.value)]);
            }
            drilldownArray.push(drillDownObj);
            chartDataValues.push({
              name: dataObj.name,
              y: Math.round(dataObj.value),
              drilldown: drillDownObj.id
            })
          } else {
            chartDataValues.push({
              name: dataObj.name,
              y: Math.round(dataObj.value),
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
        if (chartDataValues.length > 0) {
          if (chartData.avarage) {
            total = total / avagerCount;
          }
          if (chartData.unit == "$" && chartData.avarage == false) {
            chartObj.subtitle.text = "Total " + chartData.unit + Math.round(total).toLocaleString();
          } else if (chartData.unit == "$" && chartData.avarage == true) {
            chartObj.subtitle.text = "Average " + chartData.unit + Math.round(total).toLocaleString();
          } else if (chartData.unit == "%") {
            chartObj.subtitle.text = "Total " + Math.round(total).toLocaleString() + chartData.unit;
          } else {
            chartObj.subtitle.text = "Total " + chartData.unit + Math.round(total).toLocaleString();
          }
        }
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


        // if (this.chartData["retention"] == true) {
        //   // for (var i = 0; i < chartObj["series"].length; i++) {           
        //   alert(chartObj["series"]["data"])
        //   //alert( chartObj["series"]["visible"][i])//=false;

        //   //}
        // }
        // alert(this.chartData.type)
        //chartObj.series["visible"][2]=false;

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
    // console.log(JSON.stringify(chartObject));
    // console.log(this.chartObjects[id].container.id);
    chartObject.chart.type = chartType
    var __this = this; chartObject.chart.events = { drilldown: function (e, d) { __this.drillDown(e, this, id); }, drillup: function (e, d) { __this.drillUp(e, this, id); } }
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
  private statisticModelData: any = {};
  private constructChartObject(chartData: any, chartObj: any) {

    var categories = [];
    var seriesJsonObject = [];
    var drilldownArray = [];
    var avagerCount = 0;
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
        avagerCount = avagerCount + 1;
        total = total + innerDataObj.value;
        //  console.log(innerDataObj.name);
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
                  // __this.modalService.open(__this.tableContent, { size: "lg" });
                  __this.service.getTableJson("").subscribe(
                    (resUserData) => {
                      __this.statisticModelData = resUserData;
                      __this.modalService.open(__this.statisticModel, { windowClass: 'datatable' });
                    })

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
              Math.round(obj.value),

            ]);
          }
          drilldownArray.push(drillDownObj);
          seriesJson.push({
            name: innerDataObj.name,
            y: Math.round(innerDataObj.value),
            drilldown: drillDownObj.id
          })
        } else {
          seriesJson.push({
            name: innerDataObj.name,
            y: Math.round(innerDataObj.value),
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
    if (seriesJsonObject.length > 0) {
      if (chartData.avarage) {
        total = total / avagerCount;
      }
      if (chartData.unit == "$" && chartData.avarage == false) {
        chartObj.subtitle.text = "Total " + chartData.unit + Math.round(total).toLocaleString();
      } else if (chartData.unit == "$" && chartData.avarage == true) {
        chartObj.subtitle.text = "Average " + chartData.unit + Math.round(total).toLocaleString();
      } else if (chartData.unit == "%") {
        chartObj.subtitle.text = "Total " + Math.round(total).toLocaleString() + chartData.unit;
      } else {
        chartObj.subtitle.text = "Total " + chartData.unit + Math.round(total).toLocaleString();
      }
    }
  }

  chartSwitchNAT(id: any) {
    var chartObj = this.contentBody[id];
    // console.log(JSON.stringify(chartObject));
    // console.log(this.chartObjects[id].container.id);
    chartObj.chart.type = "pie";
    var __this = this; chartObj.chart.events = { drilldown: function (e, d) { __this.drillDown(e, this, id); }, drillup: function (e, d) { __this.drillUp(e, this, id); } }
    chartObj.chart.renderTo = this.chartObjects[id].container.id;
    chartObj.chart.plotOptions = {
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

    chartObj.tooltip = {
      pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
      shared: true

    }
    var chartData = this.chartRawData[id];

    // chartObj.title.text = chartData.title;
    var categories = new Array();
    var total = 0;
    var avagerCount = 0;
    var chartDataValues = new Array();
    var drilldownArray = new Array();


    for (var i = 0; i < chartData.data.length; i++) {
      var dataObj = chartData.data[i];
      categories.push(dataObj.name);
      avagerCount = avagerCount + 1;
      total = total + parseInt(dataObj.value);
      var drilldownData = dataObj.data;
      if (drilldownData.length > 0) {
        var drillDownObj: any = {};
        var __this = this;
        drillDownObj.point = {
          events: {
            click: function () {
              if (this.x != undefined)
                // modal trigger
                __this.service.getTableJson("").subscribe(
                  (resUserData) => {
                    __this.statisticModelData = resUserData;
                    __this.modalService.open(__this.statisticModel, { windowClass: 'datatable' });
                  })
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
            Math.round(obj.value)]);
        }
        drilldownArray.push(drillDownObj);
        chartDataValues.push({
          name: dataObj.name,
          y: Math.round(dataObj.value),
          drilldown: drillDownObj.id
        })
      } else {
        chartDataValues.push({
          name: dataObj.name,
          y: Math.round(dataObj.value),
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
    if (chartData.avarage) {
      total = total / avagerCount;
    }

    if (chartData.unit == "$" && chartData.avarage == false) {
      chartObj.subtitle.text = "Total " + chartData.unit + Math.round(total).toLocaleString() + "<br> <p>Hello1</p>";
    } else if (chartData.unit == "$" && chartData.avarage == true) {
      chartObj.subtitle.text = "Average " + chartData.unit + Math.round(total).toLocaleString() + "<br> <p>Hello2</p>";
    } else if (chartData.unit == "%") {
      chartObj.subtitle.text = "Total " + Math.round(total).toLocaleString() + chartData.unit;
    } else {
      chartObj.subtitle.text = "Total " + chartData.unit + Math.round(total).toLocaleString();
    }

    // {series.name}
    this.chartObjects[id] = new Highcharts.Chart(chartObj);



  }

  chartSwitch(buttonName: any, id: any) {
    if (buttonName === "NAT") {
      this.chartSwitchNAT(id);
      return;
    }
    var chartObject = this.contentBody[id];
    // console.log(JSON.stringify(chartObject));
    // console.log(this.chartObjects[id].container.id);
    chartObject.chart.type = "pie"
    var __this = this; chartObject.chart.events = { drilldown: function (e, d) { __this.drillDown(e, this, id); }, drillup: function (e, d) { __this.drillUp(e, this, id); } }
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

    chartObject.tooltip = {
      pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
      shared: true

    }
    var chartData = this.chartRawData[id];
    var drilldownArray = new Array();
    var total = 0;
    var chartDataValues = new Array();
    var avagerCount = 0;
    for (var i = 0; i < chartData.data.length; i++) {
      var dataObj = chartData.data[i];
      // categories.push(dataObj.name);
      avagerCount = avagerCount + 1;
      // total = total + dataObj.value;
      var drilldownData = dataObj.data;
      if (drilldownData.length > 0) {

        var drillDownObj: any = {};
        var __this = this;
        drillDownObj.point = {
          events: {
            click: function () {
              if (this.x != undefined)
                // modal trigger
                __this.service.getTableJson("").subscribe(
                  (resUserData) => {
                    __this.statisticModelData = resUserData;
                    __this.modalService.open(__this.statisticModel, { windowClass: 'datatable' });
                  })
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

          if (obj.name == buttonName) {
            total = total + parseInt(obj.value);
            chartDataValues.push({
              name: dataObj.name,
              y: Math.round(obj.value),
              drilldown: drillDownObj.id
            })
            for (var k = 0; k < drilldownData[j].data.length; k++) {
              var inObj = drilldownData[j].data[k];
              drillDownObj.data.push([
                inObj.name,
                Math.round(inObj.value)]);
            }
          }
          //  pieButtons.indexOf( obj.name)>-1? "" : pieButtons.push(obj.name);

        }
        drilldownArray.push(drillDownObj);

      } else {
        chartDataValues.push({
          name: dataObj.name,
          y: Math.round(dataObj.value),
          drilldown: null
        });
      }
    }
    // chartObj.xAxis.categories = categories;
    chartObject.series = [{
      name: chartData.xaxisTitle,
      data: chartDataValues
    }];
    chartObject.drilldown = {
      "series": drilldownArray
    }
    if (chartData.avarage) {
      total = total / avagerCount;
    }

    if (chartData.unit == "$" && chartData.avarage == false) {
      chartObject.subtitle.text = "Total " + chartData.unit + Math.round(total).toLocaleString();
    } else if (chartData.unit == "$" && chartData.avarage == true) {
      chartObject.subtitle.text = "Average " + chartData.unit + Math.round(total).toLocaleString();
    } else if (chartData.unit == "%") {
      chartObject.subtitle.text = "Total " + Math.round(total).toLocaleString() + chartData.unit;
    } else {
      chartObject.subtitle.text = "Total " + chartData.unit + Math.round(total).toLocaleString();
    }
    this.chartObjects[id] = new Highcharts.Chart(chartObject);

  }


}