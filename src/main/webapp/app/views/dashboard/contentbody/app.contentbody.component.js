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
var app_component_service_1 = require("../../../app.component.service");
var ng_bootstrap_1 = require("@ng-bootstrap/ng-bootstrap");
var Highcharts = require('highcharts');
var Highcharts3d = require('highcharts/highcharts-3d.src');
Highcharts3d(Highcharts);
require('./js/exporting.js')(Highcharts);
require('./js/drilldown.js')(Highcharts);
// require('./js/data.js')(Highcharts);
Highcharts.setOptions({
    colors: ['#058DC7', '#50B432', '#ED561B']
});
var ContentSection = (function () {
    function ContentSection(service, modalService) {
        var _this = this;
        this.service = service;
        this.modalService = modalService;
        this.contentBody = {};
        this.tableData = { "buttonName": "",
            "title": "",
            "tableData": []
        };
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
    ContentSection.prototype.ngOnInit = function () {
        this.modalService.open(this.model);
    };
    ContentSection.prototype.openDataTable = function (data) {
        this.tableData = data;
        this.modalService.open(this.topModel);
    };
    ContentSection.prototype.chunk = function (arr, size) {
        var newArr = [];
        for (var i = 0; i < arr.length; i += size) {
            newArr.push(arr.slice(i, i + size));
        }
        return newArr;
    };
    ContentSection.prototype.notEmpty = function (dataObj) {
        try {
            console.log(dataObj);
            if (dataObj.datatable.buttonName === undefined) {
                return false;
            }
            else {
                return true;
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
    ContentSection.prototype.getTileJson = function (id) {
        var _this = this;
        this.contentBody[id] = [];
        this.service.getTilteJson().subscribe(function (resUserData) {
            _this.contentBody[id] = resUserData;
        });
    };
    ContentSection.prototype.getChartJson = function (obj) {
        var _this = this;
        this.service.getChartJson(obj.id).subscribe(function (chartData) {
            // this.contentBody[id] = chartData;
            _this.constructChartJson(obj, chartData);
        });
    };
    ContentSection.prototype.constructChartJson = function (obj, chartData) {
        var chartObj = this.getChartJSONObject(obj, chartData);
        this.contentBody[obj.id] = chartObj;
    };
    ContentSection.prototype.getChartJSONObject = function (obj, chartData) {
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
            plotOptions: {},
            series: []
        };
        switch (chartData.type) {
            case "column":
                chartObj.chart.type = "column";
                chartObj.plotOptions["column"] = {
                    pointPadding: 0.2,
                    borderWidth: 0
                };
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
                chartObj.plotOptions["series"] = {};
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
                        }
                        else {
                            seriesJson[innerDataObj.name].push(innerDataObj.value);
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
                chartObj.chart.type = "column";
                chartObj.plotOptions["column"] = {
                    pointPadding: 0.2,
                    borderWidth: 0
                };
                var categories = [];
                var seriesJson = {};
                for (var i = 0; i < chartData.data.length; i++) {
                    var dataObj = chartData.data[i];
                    categories.push(dataObj.name);
                    for (var j = 0; j < dataObj.data.length; j++) {
                        var innerDataObj = dataObj.data[j];
                        if (seriesJson[innerDataObj.name] === undefined) {
                            seriesJson[innerDataObj.name] = [innerDataObj.value];
                        }
                        else {
                            seriesJson[innerDataObj.name].push(innerDataObj.value);
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
                chartObj.chart.type = "column";
                chartObj.plotOptions["column"] = {
                    stacking: 'normal',
                    dataLabels: {
                        enabled: false,
                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
                    }
                };
                var categories = [];
                var seriesJson = {};
                for (var i = 0; i < chartData.data.length; i++) {
                    var dataObj = chartData.data[i];
                    categories.push(dataObj.name);
                    for (var j = 0; j < dataObj.data.length; j++) {
                        var innerDataObj = dataObj.data[j];
                        if (seriesJson[innerDataObj.name] === undefined) {
                            seriesJson[innerDataObj.name] = [innerDataObj.value];
                        }
                        else {
                            seriesJson[innerDataObj.name].push(innerDataObj.value);
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
    };
    return ContentSection;
}());
__decorate([
    core_1.ViewChild("content"),
    __metadata("design:type", core_1.TemplateRef)
], ContentSection.prototype, "model", void 0);
__decorate([
    core_1.ViewChild("topModel"),
    __metadata("design:type", core_1.TemplateRef)
], ContentSection.prototype, "topModel", void 0);
ContentSection = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: "app-content",
        templateUrl: "./content-bootstrap.html"
    }),
    __metadata("design:paramtypes", [app_component_service_1.MyFcaService, ng_bootstrap_1.NgbModal])
], ContentSection);
exports.ContentSection = ContentSection;
//# sourceMappingURL=app.contentbody.component.js.map