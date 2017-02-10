"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require("@angular/core");
var body_service_1 = require("../../services/body-services/body.service");
var ng_bootstrap_1 = require("@ng-bootstrap/ng-bootstrap");
var Highcharts = require('highcharts');
var Highcharts3d = require('highcharts/highcharts-3d.src');
Highcharts3d(Highcharts);
require('../../resources/js/exporting.js')(Highcharts);
require('../../resources/js/drilldown.js')(Highcharts);
// require('../../resources/js/data.js')(Highcharts);
var BodyComponent = (function () {
    function BodyComponent(service, modalService) {
        var _this = this;
        this.service = service;
        this.modalService = modalService;
        this.contentBody = {};
        this.tableData = {
            "buttonName": "",
            "title": "",
            "tableData": []
        };
        this.charTypeJSON = {};
        this.chartObjects = {};
        this.service.getNumberOfTiltes().subscribe(function (resUserData) {
            _this.tilesArray = _this.chunk(resUserData, 2);
            for (var i = 0; i < resUserData.length; i++) {
                var obj = resUserData[i];
                if (obj.type === "tile") {
                    _this.getTileJson(obj.id);
                }
                else {
                    _this.getChartJson(obj);
                }
            }
        });
    }
    BodyComponent.prototype.ngOnInit = function () {
        this.modalService.open(this.model, { size: "lg" });
    };
    BodyComponent.prototype.openDataTable = function (data) {
        this.tableData = data.data;
        this.modalService.open(this.topModel, { size: "lg" });
    };
    BodyComponent.prototype.chunk = function (arr, size) {
        var newArr = [];
        for (var i = 0; i < arr.length; i += size) {
            newArr.push(arr.slice(i, i + size));
        }
        return newArr;
    };
    BodyComponent.prototype.notEmpty = function (dataObj) {
        try {
            if (dataObj.buttonName !== undefined) {
                return dataObj.data.length > 0 ? true : false;
            }
            else if (dataObj.buttonName !== undefined) {
                return dataObj.data.length > 0 ? true : false;
            }
            else {
                return false;
            }
        }
        catch (e) {
            return false;
        }
    };
    BodyComponent.prototype.chartType = function (id) {
        try {
            return this.charTypeJSON[id] === 'column' ? true : false;
        }
        catch (e) {
            return false;
        }
    };
    BodyComponent.prototype.isTopThree = function (dataObj) {
        try {
            if (dataObj.datatable.buttonName === undefined) {
                return false;
            }
            else {
                return dataObj.datatable.tableData[0].data.length <= 3 ? true : false;
            }
        }
        catch (e) {
            return false;
        }
    };
    // options: any;
    // getJSONObject(jsonString: string) {
    //   //  debugger;
    //   // console.log(jsonString)
    //   return JSON.parse(jsonString);
    // }
    BodyComponent.prototype.getTileJson = function (id) {
        var _this = this;
        this.contentBody[id] = [];
        this.service.getTilteJson(id).subscribe(function (resUserData) {
            _this.contentBody[id] = resUserData;
        });
    };
    BodyComponent.prototype.getChartJson = function (obj) {
        var _this = this;
        this.service.getChartJson(obj.id).subscribe(function (chartData) {
            // this.contentBody[id] = chartData;
            _this.constructChartJson(obj, chartData);
        });
    };
    BodyComponent.prototype.constructChartJson = function (obj, chartData) {
        this.charTypeJSON[obj.id] = chartData.type;
        var chartObj = this.getChartJSONObject(obj, chartData);
        this.contentBody[obj.id] = chartObj;
    };
    BodyComponent.prototype.getChartJSONObject = function (obj, chartData) {
        var chartObj = {
            chart: {
                type: ''
            },
            colors: ['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9',
                '#f15c80', '#e4d354', '#2b908f', '#f45b5b', '#91e8e1'],
            title: {
                text: chartData.title
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
            tooltip: {
                //pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
                pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
                shared: true
            },
            plotOptions: {},
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
                chartObj.chart.type = "column";
                chartObj.plotOptions["series"] = {
                    pointPadding: 0.2,
                    borderWidth: 0,
                    dataLabels: {
                        enabled: true
                    }
                };
                chartObj.xAxis["type"] = 'category';
                delete chartObj.xAxis.categories;
                // chartObj.title.text = chartData.title;
                var categories = new Array();
                var chartDataValues = new Array();
                var drilldownArray = new Array();
                for (var i = 0; i < chartData.data.length; i++) {
                    var dataObj = chartData.data[i];
                    categories.push(dataObj.name);
                    var drilldownData = dataObj.data;
                    if (drilldownData.length > 0) {
                        var drillDownObj = {};
                        drillDownObj.name = dataObj.name;
                        drillDownObj.id = dataObj.name + i;
                        drillDownObj.data = new Array();
                        for (var j = 0; j < drilldownData.length; j++) {
                            var obj = drilldownData[j];
                            drillDownObj.data.push({
                                name: obj.name,
                                y: obj.value,
                                drilldown: null
                            });
                        }
                        drilldownArray.push(drillDownObj);
                        chartDataValues.push({
                            name: dataObj.name,
                            y: dataObj.value,
                            drilldown: drillDownObj.id
                        });
                    }
                    else {
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
                };
                // chartObj.yAxis.title.text = chartData.yaxisTitle;
                break;
            case "bar_compound":
                chartObj.chart.type = "bar";
                chartObj.plotOptions["series"] = {
                    borderWidth: 0,
                    dataLabels: {
                        enabled: false,
                    }
                };
                chartObj.plotOptions["series"]["stacking"] = "normal";
                delete chartObj.xAxis.categories;
                delete chartObj.yAxis;
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
                chartObj.chart.type = "column";
                chartObj.plotOptions["series"] = {
                    pointPadding: 0.2,
                    borderWidth: 0,
                    dataLabels: {
                        enabled: true
                    }
                };
                delete chartObj.xAxis.categories;
                delete chartObj.yAxis;
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
                chartObj.chart.type = "column";
                chartObj.plotOptions["column"] = {
                    stacking: 'normal',
                    dataLabels: {
                        enabled: false,
                    }
                };
                delete chartObj.xAxis.categories;
                delete chartObj.yAxis;
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
    };
    BodyComponent.prototype.chartChange = function (chartType, id) {
        var chartObject = this.contentBody[id];
        console.log(JSON.stringify(chartObject));
        console.log(this.chartObjects[id].container.id);
        chartObject.chart.type = chartType;
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
        };
        this.chartObjects[id] = new Highcharts.Chart(chartObject);
        // this.contentBody[id]=chartObject;
    };
    BodyComponent.prototype.saveChartInstance = function (chartObj, id) {
        this.chartObjects[id] = chartObj;
    };
    BodyComponent.prototype.constructChartObject = function (chartData, chartObj) {
        var categories = [];
        var seriesJsonObject = [];
        var drilldownArray = [];
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
                    var drillDownObj = {};
                    drillDownObj.point = {
                        events: {
                            click: function () {
                                if (this.x != undefined)
                                    // modal trigger
                                    alert(this.x);
                            }
                        }
                    };
                    drillDownObj.name = innerDataObj.name;
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
                    });
                }
                else {
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
        };
        //  chartObj.xAxis.categories = categories;
        // for (var key in seriesJson) {
        chartObj.series = seriesJsonObject;
        // }
    };
    return BodyComponent;
}());
__decorate([
    core_1.Input(),
    __metadata("design:type", Object)
], BodyComponent.prototype, "data", void 0);
__decorate([
    core_1.ViewChild("content"),
    __metadata("design:type", core_1.TemplateRef)
], BodyComponent.prototype, "model", void 0);
__decorate([
    core_1.ViewChild("topModel"),
    __metadata("design:type", core_1.TemplateRef)
], BodyComponent.prototype, "topModel", void 0);
BodyComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: "app-content",
        templateUrl: "./body.html"
    }),
    __metadata("design:paramtypes", [body_service_1.BodyService, ng_bootstrap_1.NgbModal])
], BodyComponent);
exports.BodyComponent = BodyComponent;
//# sourceMappingURL=body.component.js.map