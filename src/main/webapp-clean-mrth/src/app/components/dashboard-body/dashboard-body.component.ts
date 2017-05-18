import { Component, OnInit, Input, ViewChild, TemplateRef, OnDestroy } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

import { DashboardBodyService } from '../../services/dashboard-body-services/dashboard-body.service';


declare var $: any;

declare var require;
const Highcharts = require('highcharts');
require('highcharts/modules/exporting')(Highcharts);
require('highcharts/modules/drilldown')(Highcharts);
require('highcharts/modules/no-data-to-display')(Highcharts);

@Component({
  moduleId: module.id,
  selector: "app-content",
  templateUrl: "./dashboard-body.html",
  styleUrls: [],
  styles: ['button:focus { background-color:#025fb1; color: #fff; }']
})

export class DashboardBodyComponent implements OnInit, OnDestroy {
  @Input() data: any;
  @Input() enablewelcomeprompt: any;
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
  private drilldownAverageCount = 0;
  private printButtonName: any = {};

  constructor(private service: DashboardBodyService,
    private modalService: NgbModal,

    private router: Router) {
    Highcharts.setOptions({
      lang: {
        thousandsSep: ',',
        drillUpText: '◁ Back'
      }
    });
    Highcharts.wrap(Highcharts, 'numberFormat', function (proceed) {
      var ret = proceed.apply(0, [].slice.call(arguments, 1));
      return ret.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
    });
    this.initializeContent();
  }

  showWelcomeModal() {
    sessionStorage.setItem("showWelcomePopup", "false");
  }
  ngOnInit() {
    this.data = JSON.parse(sessionStorage.getItem("CurrentUser"))

    var showWelcomePopup = sessionStorage.getItem("showWelcomePopup");

    if (showWelcomePopup == undefined) {
      this.modalService.open(this.model, { size: "lg" });
    }


    $(document).ready(function () {
      var elementHeights = $('.data - group').map(function () {
        return $(this).height();
      }).get();
      var maxHeight = Math.max.apply(null, elementHeights);
      $('.data-group').height(maxHeight);
    });

    function numberWithPercentage(x) {
      return (x).toFixed(1).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  }


  numberWithPercentage(x) {
    return (x).toFixed(1);
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

  drillDown(e: any, chart: any, id: any) {
    var obj = this.unitAndAverage[id]
    this.drillUptotalCount = 0;
    this.drillupAverageCount = 0;
    for (var i = 0; i < e.seriesOptions.data.length; i++) {
      this.totalCount = this.totalCount + e.seriesOptions.data[i][1];
      this.drilldownAverageCount = this.drilldownAverageCount + 1;
    }

    if (obj.averageLine) {
      var averageLinetotal = this.totalCount / this.drilldownAverageCount;
      chart.yAxis["plotLines"] = [{
        color: '#ff790c',
        value: averageLinetotal,
        width: '3',
        zIndex: 2
      }]

    }
    if (obj.avarage) {
      this.totalCount = this.totalCount / this.drilldownAverageCount;
    }
    if (obj.unit == "$" && obj.avarage == false) {
      chart.setTitle(null, { text: "Total " + obj.unit + Math.floor(this.totalCount).toLocaleString() + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id]) + "<br>" + (e.point.series.type === "pie" ? e.point.name : "") });
    } else if (obj.unit == "$" && obj.avarage == true) {
      chart.setTitle(null, { text: "Average " + obj.unit + Math.floor(this.totalCount).toLocaleString() + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id]) + "<br>" + (e.point.series.type === "pie" ? e.point.name : "") });
    } else if (obj.unit == "%" && obj.avarage == false) {
      chart.setTitle(null, { text: "Total " + this.numberWithPercentage(this.totalCount).toLocaleString() + obj.unit + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id]) + "<br>" + (e.point.series.type === "pie" ? e.point.name : "") });
    } else if (obj.unit == "%" && obj.avarage == true) {
      chart.setTitle(null, { text: "Average " + this.numberWithPercentage(e.point.y).toLocaleString() + obj.unit + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id]) + "<br>" + (e.point.series.type === "pie" ? e.point.name : "") });
    } else {
      chart.setTitle(null, { text: "Total " + obj.unit + Math.floor(this.totalCount).toLocaleString() + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id]) + "<br>" + (e.point.series.type === "pie" ? e.point.name : "") });

    }
    this.drillUptotalCount = 0;
    this.drilldownAverageCount = 0;
  }
  private drillUptotalCount: any = 0;
  private drillupAverageCount = 0;
  drillUp(e: any, chart: any, id: any) {
    var obj = this.unitAndAverage[id];
    for (var i = 0; i < e.seriesOptions.data.length; i++) {
      this.drillUptotalCount = this.drillUptotalCount + e.seriesOptions.data[i].y;
      this.drillupAverageCount = this.drillupAverageCount + 1;
    }
    if (obj.averageLine) {
      var averageLinetotal = this.drillUptotalCount / this.drillupAverageCount;
      chart.yAxis["plotLines"] = [{
        color: '#ff790c',
        value: averageLinetotal,
        width: '3',
        zIndex: 2
      }]

    }
    if (obj.avarage) {
      this.drillUptotalCount = this.drillUptotalCount / this.drillupAverageCount;
    }
    if (obj.unit == "$" && obj.avarage == false) {
      chart.setTitle(null, {
        text: "Total " + obj.unit + Math.floor(this.drillUptotalCount).toLocaleString() + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id])
      });
    } else if (obj.unit == "$" && obj.avarage == true) {
      chart.setTitle(null, {
        text: "Average " + obj.unit + Math.floor(this.drillUptotalCount).toLocaleString() + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id])
      });
    } else if (obj.unit == "%" && obj.avarage == false) {
      chart.setTitle(null, {
        text: "Total " + this.numberWithPercentage(this.drillUptotalCount).toLocaleString() + obj.unit + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id])
      });
    } else if (obj.unit == "%" && obj.avarage == true) {
      chart.setTitle(null, {
        text: "Average " + this.numberWithPercentage(this.drillUptotalCount).toLocaleString() + obj.unit + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id])
      });
    } else {
      chart.setTitle(null, {
        text: "Total " + obj.unit + Math.floor(this.drillUptotalCount).toLocaleString() + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id])
      });

    }
    this.totalCount = 0;
    this.drilldownAverageCount = 0;
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
  isEmpty(data) {
    try {
      if (data.length == 0) {
        return false;
      } else {
        return true;
      }
    } catch (e) {
      return false;
    }
  }
  openProgramRules(url: any) {
    window.open(url)

  }
  openProgramSite(url: any) {
    window.open(url)
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
  emptyBadge(data: any): boolean {
    try {
      if ((data).length == 0 || (data).length == 1) {
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
  }
  private chartData: any;
  getChartJSONObject(obj: any, chartData: any): any {
    if (chartData.xaxisTitle == "") {
      chartData.xaxisTitle = chartData.yaxisTitle
    } else if (chartData.yaxisTitle == "") {
      chartData.yaxisTitle = chartData.xaxisTitle
    }

    var tileId = obj.id;
    var tooltip: string = "";
    var dataLabels: string = "";
    var stackLabels: string = "";
    if (chartData.unit == "%") {
      tooltip = '<span style="color:{series.color}">{series.name}</span> : <b>{point.y}</b> <b>' + chartData.unit + '</b><br/>';
      dataLabels = "{point.y}" + chartData.unit
      stackLabels = '{total}' + chartData.unit
    } else {
      tooltip = '<span style="color:{series.color}">{series.name}</span> : <b>' + chartData.unit + '</b><b>{point.y:.0f}</b> <br/>';
      dataLabels = chartData.unit + "{point.y:.0f}"
      stackLabels = chartData.unit + '{total:.0f}'
    }
    this.chartData = chartData;

    this.unitAndAverage[obj.id] = { unit: chartData.unit, avarage: chartData.avarage, averageLine: chartData.averageLine };
    this.showPieButton[obj.id] = chartData.customer_first;

    var chartObj = {
      chart: {
        type: '',

      },
      colors: ['#7cb5ec', '#90ed7d', '#434348', '#f7a35c', '#8085e9',
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
        min: 0,
        type: 'category',
        categories: [],
        labels: {
          style: {
            fontSize: '7.5px'
          }
        },

      },
      yAxis: {
        min: 0,
        minRange: 1,
        allowDecimals: false,
        title: {
          text: chartData.yaxisTitle
        },
        stackLabels: {
          format: stackLabels,
          enabled: true,
          overFlow: 'none',
          crop: false,
          style: {
            fontSize: '9px',
            fontWeight: 'bold'

          }
        },

      },
      tooltip: {
        pointFormat: tooltip,
        shared: false
      },

      plotOptions: {
      },
      series: [

      ],
      drilldown: {

      },
      navigation: {
        buttonOptions: {
          align: 'left'
        }
      },
      responsive: {
        rules: [{
          condition: {
            maxWidth: 500
          },
          chartOptions: {
            legend: {
              align: 'center',
              verticalAlign: 'bottom',
              layout: 'horizontal'
            },
            yAxis: {
              labels: {
                align: 'left',
                x: 0,
                y: -5
              },
              title: {
                text: null
              }
            },
            subtitle: {
              text: null
            },
            credits: {
              enabled: false
            }
          }
        }]
      }

    };

    var __this = this;
    switch (chartData.type) {
      case "pie":
        chartObj.chart.type = "pie"
        chartObj.plotOptions = {
          pie: {
            size: '70%',
            allowPointSelect: false,
            cursor: 'pointer',
            dataLabels: {
              enabled: true,
              padding: 3,
              allowOverlap: false,
              overFlow: 'justify',
              crop: true,
              distance: 13,
              format: '<b>{point.name}</b>: {point.y:.0f}',
              style: {
                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
                width: '90px',
                fontSize: '8.5px'
              }
            }
          }

        },
          chartObj.xAxis["type"] = 'category';
        delete chartObj.xAxis.categories;
        var categories = new Array();
        var total = 0;
        var avagerCount = 0;
        var chartDataValues = new Array();
        var drilldownArray = new Array();
        var pieButtons = new Array();
        this.pieButtons[obj.id] = pieButtons;
        for (var i = 0; i < chartData.data.length; i++) {
          i == 0 && !chartData.cfdealDisMan ? pieButtons.push("NAT") : "";

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
                  if (this.x != undefined && this.name.length > 3 && this.name.length < 7 && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 20 || tileId == 22 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                    var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                    window.open("./datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token)
                  }
                  return true;
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
              if (!chartData.cfdealDisMan) {
                pieButtons.indexOf(obj.name) > -1 ? "" : pieButtons.push(obj.name);
              }

              drillDownObj.data.push([
                obj.name,
                (obj.value)]);
            }
            drilldownArray.push(drillDownObj);
            chartDataValues.push({
              name: dataObj.name,
              y: (dataObj.value),
              drilldown: drillDownObj.id
            })
          } else {
            chartDataValues.push({
              name: dataObj.name,
              y: (dataObj.value),
              drilldown: null
            });
          }
        }
        chartObj.series = [{
          name: chartData.xaxisTitle,
          data: chartDataValues
        }];
        chartObj.drilldown = {
          "series": drilldownArray,
          activeDataLabelStyle: {
            "textDecoration": "none"
          },
          drillUpButton: {
            relativeTo: 'spacingBox',
            position: {
              x: 0,
              y: 30

            },
          }
        }
        this.printButtonName[tileId] = this.pieButtons[tileId][0];
        if (chartDataValues.length > 0) {

          if (chartData.avarage) {
            total = total / avagerCount;
          }

          if (chartData.unit == "$" && chartData.avarage == false) {
            chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + (this.printButtonName[tileId] === undefined ? "" : this.printButtonName[tileId]);
          } else if (chartData.unit == "$" && chartData.avarage == true) {
            chartObj.subtitle.text = "Average " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + (this.printButtonName[tileId] === undefined ? "" : this.printButtonName[tileId]);
          } else if (chartData.unit == "%" && chartData.avarage == false) {
            chartObj.subtitle.text = "Total " + this.numberWithPercentage(total).toLocaleString() + chartData.unit + "<br>" + (this.printButtonName[tileId] === undefined ? "" : this.printButtonName[tileId]);
          } else if (chartData.unit == "%" && chartData.avarage == true) {
            chartObj.subtitle.text = "Average " + this.numberWithPercentage(total).toLocaleString() + chartData.unit + "<br>" + (this.printButtonName[tileId] === undefined ? "" : this.printButtonName[tileId]);
          } else {
            chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + (this.printButtonName[tileId] === undefined ? "" : this.printButtonName[tileId]);

          }
        }
        break;
      case "column":
        chartObj.chart.type = "column",
          chartObj.plotOptions["column"] =
          {
            plotBorderWidth: 0,
            allowPointSelect: false,
            size: '90%',
            tooltip: {
              pointFormat: tooltip
            },
            dataLabels: {
              enabled: true,
              padding: 0,
              format: dataLabels,
              allowOverlap: true,
              overFlow: 'justify',
              crop: false,
              rotation: -70,
              y: -15,

              style: {
                fontSize: '9px',
                fontWeight: 'bold'
              }
            }
          }
        chartObj.plotOptions["pie"] = {
          plotBorderWidth: 0,
          allowPointSelect: false,
          size: '80%',
          tooltip: {
            pointFormat: tooltip
          },
          dataLabels: {
            allowOverlap: true,
            enabled: true,
            padding: 0,
            overFlow: 'justify',
            crop: false,
            format: '<b>{point.name}</b> <br>' + dataLabels,

            style: {
              color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
              fontSize: '9px',
              fontWeight: 'bold'

            }
          }
        }
        chartObj.plotOptions["series"] = {
          point: {
            events: {
              click: function (e, a, b) {
                if (this.name == null) {
                  return true;
                }
                if (this.name.length > 3 && this.name.length < 7 && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 20 || tileId == 22 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                  var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                  window.open("./datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token)
                }
                return true;
              }
            }

          },
          events: {
            legendItemClick: function (e) {
              __this.lengendItemClick(e, this, tileId, chartData.retention)

            }
          }

        }

        chartObj.xAxis["type"] = 'category';
        delete chartObj.xAxis.categories;
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
                  if (this.x != undefined && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 20 || tileId == 22 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                    var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                    window.open("./datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token)
                  }
                  return true;
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
                (obj.value)]);
            }
            drilldownArray.push(drillDownObj);
            chartDataValues.push({
              name: dataObj.name,
              y: (dataObj.value),
              drilldown: drillDownObj.id
            })
          } else {
            chartDataValues.push({
              name: dataObj.name,
              y: (dataObj.value),
              drilldown: null
            });
          }
        }
        chartObj.series = [{
          name: chartData.xaxisTitle,
          data: chartDataValues
        }];
        chartObj.drilldown = {
          "series": drilldownArray,
          activeDataLabelStyle: {
            "textDecoration": "none"
          },
          drillUpButton: {
            relativeTo: 'spacingBox',
            position: {
              y: 30,
              x: 0
            },
          }
        }
        if (chartData.averageLine) {
          var averageLinetotal = total / avagerCount;
          chartObj.yAxis["plotLines"] = [{
            color: '#ff790c',
            value: averageLinetotal,
            width: '3',
            zIndex: 2
          }]

        }

        if (chartDataValues.length == 0) {
          chartObj.subtitle.text = "Total 0";
        }
        if (chartDataValues.length > 0) {
          if (chartData.avarage) {
            total = total / avagerCount;
          }
          if (chartData.unit == "$" && chartData.avarage == false) {
            chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString();
          } else if (chartData.unit == "$" && chartData.avarage == true) {
            chartObj.subtitle.text = "Average " + chartData.unit + Math.floor(total).toLocaleString();
          } else if (chartData.unit == "%" && chartData.avarage == true) {
            chartObj.subtitle.text = "Total " + this.numberWithPercentage(total).toLocaleString() + chartData.unit;
          } else if (chartData.unit == "%" && chartData.avarage == true) {
            chartObj.subtitle.text = "Average " + this.numberWithPercentage(total).toLocaleString() + chartData.unit;
          } else {
            chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString();
          }
        }
        break;
      case "bar_compound":
        chartObj.chart["marginRight"] = 60
        chartObj.chart.type = "bar";
        chartObj.plotOptions["series"] = {
          point: {
            events: {
              click: function (e, a, b) {
                if (this.name.length > 3 && this.name.length < 7 && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 20 || tileId == 22 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                  var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                  window.open("./datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token)
                }
                return true;
              }
            }
          },
          events: {
            legendItemClick: function (e) {
              __this.lengendItemClick(e, this, tileId, chartData.retention)
            }
          },
          borderWidth: 0,
          dataLabels: {
            enabled: false,
            padding: 0,
            crop: true
          }
        }
        chartObj.plotOptions["series"]["stacking"] = "normal";
        delete chartObj.xAxis.categories;
        this.constructChartObject(chartData, chartObj, tileId);
        break;
      case "column_compound":
        chartObj.chart.type = "column"
        chartObj.plotOptions["series"] = {
          point: {
            events: {
              click: function (e, a, b) {
                if (this.name.length > 3 && this.name.length < 7 && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 20 || tileId == 22 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                  var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                  window.open("./datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token)
                }
                return true;
              }
            }
          },
          events: {
            legendItemClick: function (e) {
              __this.lengendItemClick(e, this, tileId, chartData.retention)
            }
          },
          pointPadding: 0.2,
          borderWidth: 0,
          dataLabels: {
            enabled: true,
            padding: 0,
            format: dataLabels,
            allowOverlap: true,
            overFlow: 'justify',
            crop: false,
            rotation: -70,
            y: -15,
            style: {
              fontSize: '9px',
              fontWeight: 'bold'
            }
          }

        }

        delete chartObj.xAxis.categories;
        this.constructChartObject(chartData, chartObj, tileId);
        break;
      case "column_stack":
        chartObj.chart.type = "column"

        chartObj.plotOptions["column"] = {
          point: {
            events: {
              click: function (e, a, b) {
                if (this.name.length > 3 && this.name.length < 7 && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 20 || tileId == 22 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                  var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                  window.open("./datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token)
                }
                return true;
              }
            }
          },

          events: {
            legendItemClick: function (e) {
              __this.lengendItemClick(e, this, tileId, chartData.retention)
              if (chartData.retention) {
                for (var i = 0; i < e.target.chart.series.length; i++) {
                  var seriesObj = e.target.chart.series[i];
                  seriesObj.setVisible(false)

                }
              }
            }
          },
          stacking: 'normal',
          dataLabels: {
            format: dataLabels
          }

        }

        delete chartObj.xAxis.categories;
        this.constructChartObject(chartData, chartObj, tileId);
        var total = 0;
        var avagerCount = 0;
        if (chartObj.series.length > 2) {
          if (chartData.retention) {
            for (var i = 0; i < chartObj.series.length; i++) {
              var seriesObj: any = chartObj.series[i];
              i == 1 ? seriesObj.visible = true : seriesObj.visible = false;
              if (seriesObj.visible) {
                for (var k = 0; k < seriesObj.data.length; k++) {
                  total = total + seriesObj.data[k].y;
                  avagerCount = avagerCount + 1;

                }
              }
            }
            if (chartData.avarage) {
              total = total / avagerCount;
            }
            if (chartData.unit == "$" && chartData.avarage == false) {
              chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString();
            } else if (chartData.unit == "$" && chartData.avarage == true) {
              chartObj.subtitle.text = "Average " + chartData.unit + Math.floor(total).toLocaleString();
            } else if (chartData.unit == "%" && chartData.avarage == false) {
              chartObj.subtitle.text = "Total " + this.numberWithPercentage(total).toLocaleString() + chartData.unit;
            } else if (chartData.unit == "%" && chartData.avarage == true) {
              chartObj.subtitle.text = "Average " + this.numberWithPercentage(total).toLocaleString() + chartData.unit;
            } else {
              chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString();
            }
          }
        }

        break;
    }
    return chartObj;
  }

  public chartChange(chartType: string, id: any): void {
    var chartObject = this.contentBody[id];
    var tooltip: string = "";
    var dataLabels: string = "";
    var stackLabels: string = "";
    if (chartObject.unit == "%") {
      tooltip = '<span style="color:{series.color}">{series.name}</span> : <b>{point.y}</b> <b>' + chartObject.unit + '</b><br/>';
      dataLabels = "{point.y}" + chartObject.unit
      stackLabels = '{total}' + chartObject.unit
    } else {
      tooltip = '<span style="color:{series.color}">{series.name}</span> : <b>' + chartObject.unit + '</b><b>{point.y:.0f}</b> <br/>';
      dataLabels = chartObject.unit + "{point.y:.0f}"
      stackLabels = chartObject.unit + '{total:.0f}'
    }
    chartObject.chart.type = chartType
    var __this = this; chartObject.chart.events = { drilldown: function (e, d) { __this.drillDown(e, this, id); }, drillup: function (e, d) { __this.drillUp(e, this, id); } }
    chartObject.chart.renderTo = this.chartObjects[id].container.id;
    if (chartType === "pie") {

      var pointFormat: string = "";

    } else {
      delete chartObject.tooltip;
    }
    this.chartObjects[id] = new Highcharts.Chart(chartObject);
  }
  private chartObjects: any = {};
  public saveChartInstance(chartObj: any, id) {
    this.chartObjects[id] = chartObj;
  }
  private statisticModelData: any = {};
  private constructChartObject(chartData: any, chartObj: any, tileId: any) {

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
      for (var k = 0; k < dataObj.data.length; k++) {
        var innerDataObj = dataObj.data[k];
        avagerCount = avagerCount + 1;
        total = total + innerDataObj.value;

        var drilldownData = innerDataObj.data;
        if (drilldownData.length > 0) {

          var drillDownObj: any = {};
          var __this = this;
          drillDownObj.point = {
            events: {
              click: function () {
                if (this.x != undefined && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 20 || tileId == 22 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                  var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                  window.open("./datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token)
                }
                return true;
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
              (obj.value),

            ]);
          }
          drilldownArray.push(drillDownObj);
          seriesJson.push({
            name: innerDataObj.name,
            y: (innerDataObj.value),
            drilldown: drillDownObj.id
          })
        } else {
          seriesJson.push({
            name: innerDataObj.name,
            y: (innerDataObj.value),
            drilldown: null
          });
        }

      }
    }
    chartObj.drilldown = {
      "series": drilldownArray,
      activeDataLabelStyle: {
        "textDecoration": "none"
      },
      drillUpButton: {
        relativeTo: 'spacingBox',
        position: {
          y: 30,
          x: 0
        },
      }

    }
    chartObj.series = seriesJsonObject
    if (seriesJsonObject.length > 0) {
      if (chartData.avarage) {
        total = total / avagerCount;
      }
      if (chartData.unit == "$" && chartData.avarage == false) {
        chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString();
      } else if (chartData.unit == "$" && chartData.avarage == true) {
        chartObj.subtitle.text = "Average " + chartData.unit + Math.floor(total).toLocaleString();
      } else if (chartData.unit == "%" && chartData.avarage == false) {
        chartObj.subtitle.text = "Total " + this.numberWithPercentage(total).toLocaleString() + chartData.unit;
      } else if (chartData.unit == "%" && chartData.avarage == true) {
        chartObj.subtitle.text = "Average " + this.numberWithPercentage(total).toLocaleString() + chartData.unit;
      } else {
        chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString();
      }
    }
  }

  chartSwitchNAT(id: any) {
    var chartObj = this.contentBody[id];
    chartObj.chart.type = "pie";
    var __this = this; chartObj.chart.events = { drilldown: function (e, d) { __this.drillDown(e, this, id); }, drillup: function (e, d) { __this.drillUp(e, this, id); } }
    chartObj.chart.renderTo = this.chartObjects[id].container.id;
    chartObj.chart.plotOptions = {
      series: {
        pointPadding: 0.2,
        borderWidth: 0,
        dataLabels: {
          enabled: true,
          padding: 0,
        }
      },
      pie: {
        plotBorderWidth: 0,
        allowPointSelect: false,
        size: '100%',
        dataLabels: {
          enabled: true,
          padding: 0,
          format: '{point.name}: <b>{point.y:.0f}</b>',

        }
      }
    }

    chartObj.tooltip = {
      pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.0f}</b> ({point.percentage:.0f}%)<br/>',
      shared: true

    }
    var chartData = this.chartRawData[id];
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
              if (this.x != undefined && this.name > 3 && (id == 9 || id == 10 || id == 11 || id == 12 || id == 13 || id == 19 || id == 20 || id == 22 || id == 23 || id == 31 || id == 32 || id == 33 || id == 36)) {
                var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                window.open("./datatable?chartId=" + id + "&territory=" + this.name + "&token=" + token)
              }
              return true;
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
            (obj.value)]);
        }
        drilldownArray.push(drillDownObj);
        chartDataValues.push({
          name: dataObj.name,
          y: (dataObj.value),
          drilldown: drillDownObj.id
        })
      } else {
        chartDataValues.push({
          name: dataObj.name,
          y: (dataObj.value),
          drilldown: null
        });
      }
    }
    chartObj.series = [{
      name: chartData.xaxisTitle,
      data: chartDataValues
    }];
    chartObj.drilldown = {
      "series": drilldownArray,
      activeDataLabelStyle: {
        "textDecoration": "none"
      },
      drillUpButton: {
        relativeTo: 'spacingBox',
        position: {
          y: 30,
          x: 0
        },
      }
    }
    if (chartData.avarage) {
      total = total / avagerCount;
    }

    if (chartData.unit == "$" && chartData.avarage == false) {
      chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + this.printButtonName[id];
    } else if (chartData.unit == "$" && chartData.avarage == true) {
      chartObj.subtitle.text = "Average " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + this.printButtonName[id];
    } else if (chartData.unit == "%" && chartData.avarage == false) {
      chartObj.subtitle.text = "Total " + this.numberWithPercentage(total).toLocaleString() + chartData.unit + "<br>" + this.printButtonName[id];
    } else if (chartData.unit == "%" && chartData.avarage == true) {
      chartObj.subtitle.text = "Average " + this.numberWithPercentage(total).toLocaleString() + chartData.unit + "<br>" + this.printButtonName[id];
    } else {
      chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + this.printButtonName[id];
    }

    this.chartObjects[id] = new Highcharts.Chart(chartObj);



  }

  chartSwitch(buttonName: any, id: any) {
    this.printButtonName[id] = buttonName;
    if (buttonName === "NAT") {
      this.chartSwitchNAT(id);
      return;
    }
    var chartObject = this.contentBody[id];

    chartObject.chart.type = "pie"
    var __this = this; chartObject.chart.events = { drilldown: function (e, d) { __this.drillDown(e, this, id); }, drillup: function (e, d) { __this.drillUp(e, this, id); } }
    chartObject.chart.renderTo = this.chartObjects[id].container.id;
    chartObject.chart.plotOptions = {
      series: {
        pointPadding: 0.2,
        borderWidth: 0,
        dataLabels: {
          enabled: true,
          padding: 0,
        }
      },
      pie: {
        plotBorderWidth: 0,
        allowPointSelect: false,
        size: '100%',
        dataLabels: {
          enabled: true,
          padding: 0,
          format: '{point.name}: <b>{point.y:.0f}</b>'
        }
      }
    }

    chartObject.tooltip = {
      pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.0f}</b> ({point.percentage:.0f}%)<br/>',
      shared: true

    }
    var chartData = this.chartRawData[id];
    var drilldownArray = new Array();
    var total = 0;
    var chartDataValues = new Array();
    var avagerCount = 0;
    for (var i = 0; i < chartData.data.length; i++) {
      var dataObj = chartData.data[i];
      avagerCount = avagerCount + 1;
      var drilldownData = dataObj.data;
      if (drilldownData.length > 0) {

        var drillDownObj: any = {};
        var __this = this;
        drillDownObj.point = {
          events: {
            click: function () {
              if (this.x != undefined && (id == 9 || id == 10 || id == 11 || id == 12 || id == 13 || id == 19 || id == 20 || id == 22 || id == 23 || id == 31 || id == 32 || id == 33 || id == 36)) {
                var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                window.open("./datatable?chartId=" + id + "&territory=" + this.name + "&token=" + token)
              }

              return true;
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
              y: (obj.value),
              drilldown: drillDownObj.id
            })
            for (var k = 0; k < drilldownData[j].data.length; k++) {
              var inObj = drilldownData[j].data[k];
              drillDownObj.data.push([
                inObj.name,
                (inObj.value)]);
            }
          }

        }
        drilldownArray.push(drillDownObj);

      } else {
        chartDataValues.push({
          name: dataObj.name,
          y: (dataObj.value),
          drilldown: null
        });
      }
    }

    chartObject.series = [{
      name: chartData.xaxisTitle,
      data: chartDataValues
    }];
    chartObject.drilldown = {
      "series": drilldownArray,
      activeDataLabelStyle: {
        "textDecoration": "none"
      },
      drillUpButton: {
        relativeTo: 'spacingBox',
        position: {
          y: 30,
          x: 0
        },

      }
    }
    if (chartData.avarage) {
      total = total / avagerCount;
    }

    if (chartData.unit == "$" && chartData.avarage == false) {
      chartObject.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + this.printButtonName[id];
    } else if (chartData.unit == "$" && chartData.avarage == true) {
      chartObject.subtitle.text = "Average " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + this.printButtonName[id];
    } else if (chartData.unit == "%" && chartData.avarage == false) {
      chartObject.subtitle.text = "Total " + this.numberWithPercentage(total).toLocaleString() + chartData.unit + "<br>" + this.printButtonName[id];
    } else if (chartData.unit == "%" && chartData.avarage == true) {
      chartObject.subtitle.text = "Average " + this.numberWithPercentage(total).toLocaleString() + chartData.unit + "<br>" + this.printButtonName[id];
    } else {
      chartObject.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + this.printButtonName[id];

    }
    this.chartObjects[id] = new Highcharts.Chart(chartObject);

  }

  private lengendItemClick(event: any, clickedSeries: any, id: any, retention: any) {
    var obj = this.unitAndAverage[id];
    var total = 0;
    var seriesCount = 0;
    for (var i = 0; i < event.target.chart.series.length; i++) {
      var series = event.target.chart.series[i];
      if (!retention) {
        if (clickedSeries.name === series.name) {
          if (!clickedSeries.visible) {
            for (var j = 0; j < series.data.length; j++) {
              var value = series.data[j].y;
              total = total + value;
              seriesCount = seriesCount + 1;
            }
          }
        } else {
          if (series.visible) {
            for (var j = 0; j < series.data.length; j++) {
              var value = series.data[j].y;
              seriesCount = seriesCount + 1;
              total = total + value;
            }
          }
        }
      }
      else {
        if (clickedSeries.name === series.name) {

          for (var j = 0; j < series.data.length; j++) {
            var value = series.data[j].y;
            total = total + value;
            seriesCount = seriesCount + 1;
          }
        }
      }
    }
    if (obj.avarage) {
      total = total / seriesCount;
    }

    if (obj.unit == "$" && obj.avarage == false) {
      event.target.chart.setTitle(null, { text: "Total " + obj.unit + Math.floor(total).toLocaleString() });
    } else if (obj.unit == "$" && obj.avarage == true) {
      event.target.chart.setTitle(null, { text: "Average " + obj.unit + Math.floor(total).toLocaleString() });
    } else if (obj.unit == "%" && obj.avarage == false) {
      event.target.chart.setTitle(null, { text: "Total " + this.numberWithPercentage(total).toLocaleString() + obj.unit });
    } else if (obj.unit == "%" && obj.avarage == true) {
      event.target.chart.setTitle(null, { text: "Average " + this.numberWithPercentage(total).toLocaleString() + obj.unit });
    } else {
      event.target.chart.setTitle(null, { text: "Total " + obj.unit + Math.floor(total).toLocaleString() });

    }

  }
}