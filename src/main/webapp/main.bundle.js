webpackJsonp([1,4],{

/***/ 378:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var AdminRootPageComponent = (function () {
    function AdminRootPageComponent() {
    }
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Object)
    ], AdminRootPageComponent.prototype, "data", void 0);
    AdminRootPageComponent = __decorate([
        core_1.Component({
            selector: "",
            template: __webpack_require__(625)
        }), 
        __metadata('design:paramtypes', [])
    ], AdminRootPageComponent);
    return AdminRootPageComponent;
}());
exports.AdminRootPageComponent = AdminRootPageComponent;
//# sourceMappingURL=admin-rootpage.component.js.map

/***/ }),

/***/ 379:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var ng_bootstrap_1 = __webpack_require__(141);
var router_1 = __webpack_require__(55);
var dashboard_body_service_1 = __webpack_require__(385);
var cookies_service_1 = __webpack_require__(20);
var Highcharts = __webpack_require__(405);
__webpack_require__(621)(Highcharts);
__webpack_require__(620)(Highcharts);
__webpack_require__(622)(Highcharts);
var DashboardBodyComponent = (function () {
    function DashboardBodyComponent(service, modalService, cookieService, router) {
        this.service = service;
        this.modalService = modalService;
        this.cookieService = cookieService;
        this.router = router;
        this.contentBody = {};
        this.tableData = {
            "buttonName": "",
            "title": "",
            "tableData": []
        };
        this.unitAndAverage = {};
        this.showPieButton = {};
        this.totalCount = 0;
        this.avarage = 0;
        this.pieButtons = {};
        this.chartRawData = {};
        this.unit = "";
        this.drilldownAverageCount = 0;
        this.printButtonName = {};
        this.drillUptotalCount = 0;
        this.drillupAverageCount = 0;
        this.charTypeJSON = {};
        this.chartObjects = {};
        this.statisticModelData = {};
        Highcharts.setOptions({
            lang: {
                thousandsSep: ',',
                drillUpText: '◁ Back'
            }
        });
        Highcharts.wrap(Highcharts, 'numberFormat', function (proceed) {
            var ret = proceed.apply(0, [].slice.call(arguments, 1));
            return ret.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        });
        this.initializeContent();
    }
    DashboardBodyComponent.prototype.showWelcomeModal = function () {
        sessionStorage.setItem("showWelcomePopup", "false");
    };
    DashboardBodyComponent.prototype.ngOnInit = function () {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"));
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
    };
    DashboardBodyComponent.prototype.numberWithPercentage = function (x) {
        return (x).toFixed(1);
    };
    DashboardBodyComponent.prototype.ngOnDestroy = function () {
        this.tilesArray = [];
        this.contentBody = {};
        this.tableData = {
            "buttonName": "",
            "title": "",
            "tableData": []
        };
        this.unitAndAverage = {};
        this.showPieButton = {};
        this.bc;
        this.totalCount = 0;
        this.avarage = 0;
        this.pieButtons = {};
        this.chartRawData = {};
    };
    DashboardBodyComponent.prototype.drillDown = function (e, chart, id) {
        var obj = this.unitAndAverage[id];
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
                }];
        }
        if (obj.avarage) {
            this.totalCount = this.totalCount / this.drilldownAverageCount;
        }
        if (obj.unit == "$" && obj.avarage == false) {
            chart.setTitle(null, { text: "Total " + obj.unit + Math.floor(this.totalCount).toLocaleString() + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id]) + "<br>" + (e.point.series.type === "pie" ? e.point.name : "") });
        }
        else if (obj.unit == "$" && obj.avarage == true) {
            chart.setTitle(null, { text: "Average " + obj.unit + Math.floor(this.totalCount).toLocaleString() + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id]) + "<br>" + (e.point.series.type === "pie" ? e.point.name : "") });
        }
        else if (obj.unit == "%" && obj.avarage == false) {
            chart.setTitle(null, { text: "Total " + this.numberWithPercentage(this.totalCount).toLocaleString() + obj.unit + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id]) + "<br>" + (e.point.series.type === "pie" ? e.point.name : "") });
        }
        else if (obj.unit == "%" && obj.avarage == true) {
            chart.setTitle(null, { text: "Average " + this.numberWithPercentage(e.point.y).toLocaleString() + obj.unit + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id]) + "<br>" + (e.point.series.type === "pie" ? e.point.name : "") });
        }
        else {
            chart.setTitle(null, { text: "Total " + obj.unit + Math.floor(this.totalCount).toLocaleString() + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id]) + "<br>" + (e.point.series.type === "pie" ? e.point.name : "") });
        }
        this.drillUptotalCount = 0;
        this.drilldownAverageCount = 0;
    };
    DashboardBodyComponent.prototype.drillUp = function (e, chart, id) {
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
                }];
        }
        if (obj.avarage) {
            this.drillUptotalCount = this.drillUptotalCount / this.drillupAverageCount;
        }
        if (obj.unit == "$" && obj.avarage == false) {
            chart.setTitle(null, {
                text: "Total " + obj.unit + Math.floor(this.drillUptotalCount).toLocaleString() + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id])
            });
        }
        else if (obj.unit == "$" && obj.avarage == true) {
            chart.setTitle(null, {
                text: "Average " + obj.unit + Math.floor(this.drillUptotalCount).toLocaleString() + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id])
            });
        }
        else if (obj.unit == "%" && obj.avarage == false) {
            chart.setTitle(null, {
                text: "Total " + this.numberWithPercentage(this.drillUptotalCount).toLocaleString() + obj.unit + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id])
            });
        }
        else if (obj.unit == "%" && obj.avarage == true) {
            chart.setTitle(null, {
                text: "Average " + this.numberWithPercentage(this.drillUptotalCount).toLocaleString() + obj.unit + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id])
            });
        }
        else {
            chart.setTitle(null, {
                text: "Total " + obj.unit + Math.floor(this.drillUptotalCount).toLocaleString() + "<br>" + (this.printButtonName[id] === undefined ? "" : this.printButtonName[id])
            });
        }
        this.totalCount = 0;
        this.drilldownAverageCount = 0;
    };
    DashboardBodyComponent.prototype.reload = function () {
        this.tilesArray = [];
        this.contentBody = {};
        this.tableData = {
            "buttonName": "",
            "title": "",
            "tableData": []
        };
        this.unitAndAverage = {};
        this.showPieButton = {};
        this.bc;
        this.totalCount = 0;
        this.avarage = 0;
        this.pieButtons = {};
        this.chartRawData = {};
        this.initializeContent();
    };
    DashboardBodyComponent.prototype.initializeContent = function () {
        var _this = this;
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
    };
    DashboardBodyComponent.prototype.openDataTable = function (data) {
        this.tableData = data.data;
        this.modalService.open(this.topModel, { size: "lg" });
    };
    DashboardBodyComponent.prototype.chunk = function (arr, size) {
        var newArr = [];
        for (var i = 0; i < arr.length; i += size) {
            newArr.push(arr.slice(i, i + size));
        }
        return newArr;
    };
    DashboardBodyComponent.prototype.notEmpty = function (dataObj) {
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
    DashboardBodyComponent.prototype.isEmpty = function (data) {
        try {
            if (data.length == 0) {
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
    DashboardBodyComponent.prototype.openProgramSite = function (url) {
        window.open(url);
    };
    DashboardBodyComponent.prototype.notEmptyBadge = function (data) {
        try {
            if ((data).length > 0) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (e) {
            return false;
        }
    };
    DashboardBodyComponent.prototype.emptyBadge = function (data) {
        try {
            if ((data).length == 0 || (data).length == 1) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (e) {
            return false;
        }
    };
    DashboardBodyComponent.prototype.errorInArray = function (data) {
        try {
            if ((data).length < 1 || typeof data == undefined || !(data instanceof Array)) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (e) {
            return false;
        }
    };
    DashboardBodyComponent.prototype.errorInObject = function (data) {
        try {
            if (data === undefined || data === null || JSON.stringify(data) === '{}') {
                return true;
            }
            else {
                return false;
            }
        }
        catch (e) {
            return false;
        }
    };
    DashboardBodyComponent.prototype.chartType = function (id) {
        try {
            return this.charTypeJSON[id] === 'column' ? true : false;
        }
        catch (e) {
            return false;
        }
    };
    DashboardBodyComponent.prototype.isTopThree = function (dataObj) {
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
    DashboardBodyComponent.prototype.openProgramRules = function (url) {
        window.open(url);
    };
    DashboardBodyComponent.prototype.getTileJson = function (id) {
        var _this = this;
        this.contentBody[id] = [];
        this.service.getTilteJson(id).subscribe(function (resUserData) {
            _this.contentBody[id] = resUserData;
        });
    };
    DashboardBodyComponent.prototype.getChartJson = function (obj) {
        var _this = this;
        this.service.getChartJson(obj.id).subscribe(function (chartData) {
            _this.chartRawData[obj.id] = chartData;
            _this.constructChartJson(obj, chartData);
        });
    };
    DashboardBodyComponent.prototype.constructChartJson = function (obj, chartData) {
        this.charTypeJSON[obj.id] = chartData.type;
        var chartObj = this.getChartJSONObject(obj, chartData);
        this.contentBody[obj.id] = chartObj;
    };
    DashboardBodyComponent.prototype.getChartJSONObject = function (obj, chartData) {
        if (chartData.xaxisTitle == "") {
            chartData.xaxisTitle = chartData.yaxisTitle;
        }
        else if (chartData.yaxisTitle == "") {
            chartData.yaxisTitle = chartData.xaxisTitle;
        }
        var tileId = obj.id;
        var tooltip = "";
        var dataLabels = "";
        var stackLabels = "";
        if (chartData.unit == "%") {
            tooltip = '<span style="color:{series.color}">{series.name}</span> : <b>{point.y}</b> <b>' + chartData.unit + '</b><br/>';
            dataLabels = "{point.y}" + chartData.unit;
            stackLabels = '{total}' + chartData.unit;
        }
        else {
            tooltip = '<span style="color:{series.color}">{series.name}</span> : <b>' + chartData.unit + '</b><b>{point.y:.0f}</b> <br/>';
            dataLabels = chartData.unit + "{point.y:.0f}";
            stackLabels = chartData.unit + '{total:.0f}';
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
            plotOptions: {},
            series: [],
            drilldown: {},
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
                chartObj.chart.type = "pie";
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
                        var drillDownObj = {};
                        var __this = this;
                        drillDownObj.point = {
                            events: {
                                click: function () {
                                    if (this.x != undefined && this.name.length > 3 && this.name.length < 7 && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 20 || tileId == 22 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                                        var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                        window.open("./datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token);
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
                        };
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
                        });
                    }
                    else {
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
                };
                this.printButtonName[tileId] = this.pieButtons[tileId][0];
                if (chartDataValues.length > 0) {
                    if (chartData.avarage) {
                        total = total / avagerCount;
                    }
                    if (chartData.unit == "$" && chartData.avarage == false) {
                        chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + (this.printButtonName[tileId] === undefined ? "" : this.printButtonName[tileId]);
                    }
                    else if (chartData.unit == "$" && chartData.avarage == true) {
                        chartObj.subtitle.text = "Average " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + (this.printButtonName[tileId] === undefined ? "" : this.printButtonName[tileId]);
                    }
                    else if (chartData.unit == "%" && chartData.avarage == false) {
                        chartObj.subtitle.text = "Total " + this.numberWithPercentage(total).toLocaleString() + chartData.unit + "<br>" + (this.printButtonName[tileId] === undefined ? "" : this.printButtonName[tileId]);
                    }
                    else if (chartData.unit == "%" && chartData.avarage == true) {
                        chartObj.subtitle.text = "Average " + this.numberWithPercentage(total).toLocaleString() + chartData.unit + "<br>" + (this.printButtonName[tileId] === undefined ? "" : this.printButtonName[tileId]);
                    }
                    else {
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
                        };
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
                };
                chartObj.plotOptions["series"] = {
                    point: {
                        events: {
                            click: function (e, a, b) {
                                if (this.name == null) {
                                    return true;
                                }
                                if (this.name.length > 3 && this.name.length < 7 && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 20 || tileId == 22 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                                    var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                    window.open("./datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token);
                                }
                                return true;
                            }
                        }
                    },
                    events: {
                        legendItemClick: function (e) {
                            __this.lengendItemClick(e, this, tileId, chartData.retention);
                        }
                    }
                };
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
                        var drillDownObj = {};
                        var __this = this;
                        drillDownObj.point = {
                            events: {
                                click: function () {
                                    if (this.x != undefined && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 20 || tileId == 22 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                                        var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                        window.open("./datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token);
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
                        };
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
                        });
                    }
                    else {
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
                };
                if (chartData.averageLine) {
                    var averageLinetotal = total / avagerCount;
                    chartObj.yAxis["plotLines"] = [{
                            color: '#ff790c',
                            value: averageLinetotal,
                            width: '3',
                            zIndex: 2
                        }];
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
                    }
                    else if (chartData.unit == "$" && chartData.avarage == true) {
                        chartObj.subtitle.text = "Average " + chartData.unit + Math.floor(total).toLocaleString();
                    }
                    else if (chartData.unit == "%" && chartData.avarage == true) {
                        chartObj.subtitle.text = "Total " + this.numberWithPercentage(total).toLocaleString() + chartData.unit;
                    }
                    else if (chartData.unit == "%" && chartData.avarage == true) {
                        chartObj.subtitle.text = "Average " + this.numberWithPercentage(total).toLocaleString() + chartData.unit;
                    }
                    else {
                        chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString();
                    }
                }
                break;
            case "bar_compound":
                chartObj.chart["marginRight"] = 60;
                chartObj.chart.type = "bar";
                chartObj.plotOptions["series"] = {
                    point: {
                        events: {
                            click: function (e, a, b) {
                                if (this.name.length > 3 && this.name.length < 7 && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 20 || tileId == 22 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                                    var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                    window.open("./datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token);
                                }
                                return true;
                            }
                        }
                    },
                    events: {
                        legendItemClick: function (e) {
                            __this.lengendItemClick(e, this, tileId, chartData.retention);
                        }
                    },
                    borderWidth: 0,
                    dataLabels: {
                        enabled: false,
                        padding: 0,
                        crop: true
                    }
                };
                chartObj.plotOptions["series"]["stacking"] = "normal";
                delete chartObj.xAxis.categories;
                this.constructChartObject(chartData, chartObj, tileId);
                break;
            case "column_compound":
                chartObj.chart.type = "column";
                chartObj.plotOptions["series"] = {
                    point: {
                        events: {
                            click: function (e, a, b) {
                                if (this.name.length > 3 && this.name.length < 7 && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 20 || tileId == 22 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                                    var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                    window.open("./datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token);
                                }
                                return true;
                            }
                        }
                    },
                    events: {
                        legendItemClick: function (e) {
                            __this.lengendItemClick(e, this, tileId, chartData.retention);
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
                };
                delete chartObj.xAxis.categories;
                this.constructChartObject(chartData, chartObj, tileId);
                break;
            case "column_stack":
                chartObj.chart.type = "column";
                chartObj.plotOptions["column"] = {
                    point: {
                        events: {
                            click: function (e, a, b) {
                                if (this.name.length > 3 && this.name.length < 7 && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 20 || tileId == 22 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                                    var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                    window.open("./datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token);
                                }
                                return true;
                            }
                        }
                    },
                    events: {
                        legendItemClick: function (e) {
                            __this.lengendItemClick(e, this, tileId, chartData.retention);
                            if (chartData.retention) {
                                for (var i = 0; i < e.target.chart.series.length; i++) {
                                    var seriesObj = e.target.chart.series[i];
                                    seriesObj.setVisible(false);
                                }
                            }
                        }
                    },
                    stacking: 'normal',
                    dataLabels: {
                        format: dataLabels
                    }
                };
                delete chartObj.xAxis.categories;
                this.constructChartObject(chartData, chartObj, tileId);
                var total = 0;
                var avagerCount = 0;
                if (chartObj.series.length > 2) {
                    if (chartData.retention) {
                        for (var i = 0; i < chartObj.series.length; i++) {
                            var seriesObj = chartObj.series[i];
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
                        }
                        else if (chartData.unit == "$" && chartData.avarage == true) {
                            chartObj.subtitle.text = "Average " + chartData.unit + Math.floor(total).toLocaleString();
                        }
                        else if (chartData.unit == "%" && chartData.avarage == false) {
                            chartObj.subtitle.text = "Total " + this.numberWithPercentage(total).toLocaleString() + chartData.unit;
                        }
                        else if (chartData.unit == "%" && chartData.avarage == true) {
                            chartObj.subtitle.text = "Average " + this.numberWithPercentage(total).toLocaleString() + chartData.unit;
                        }
                        else {
                            chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString();
                        }
                    }
                }
                break;
        }
        return chartObj;
    };
    DashboardBodyComponent.prototype.chartChange = function (chartType, id) {
        var chartObject = this.contentBody[id];
        var tooltip = "";
        var dataLabels = "";
        var stackLabels = "";
        if (chartObject.unit == "%") {
            tooltip = '<span style="color:{series.color}">{series.name}</span> : <b>{point.y}</b> <b>' + chartObject.unit + '</b><br/>';
            dataLabels = "{point.y}" + chartObject.unit;
            stackLabels = '{total}' + chartObject.unit;
        }
        else {
            tooltip = '<span style="color:{series.color}">{series.name}</span> : <b>' + chartObject.unit + '</b><b>{point.y:.0f}</b> <br/>';
            dataLabels = chartObject.unit + "{point.y:.0f}";
            stackLabels = chartObject.unit + '{total:.0f}';
        }
        chartObject.chart.type = chartType;
        var __this = this;
        chartObject.chart.events = { drilldown: function (e, d) { __this.drillDown(e, this, id); }, drillup: function (e, d) { __this.drillUp(e, this, id); } };
        chartObject.chart.renderTo = this.chartObjects[id].container.id;
        if (chartType === "pie") {
            var pointFormat = "";
        }
        else {
            delete chartObject.tooltip;
        }
        this.chartObjects[id] = new Highcharts.Chart(chartObject);
    };
    DashboardBodyComponent.prototype.saveChartInstance = function (chartObj, id) {
        this.chartObjects[id] = chartObj;
    };
    DashboardBodyComponent.prototype.constructChartObject = function (chartData, chartObj, tileId) {
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
                    var drillDownObj = {};
                    var __this = this;
                    drillDownObj.point = {
                        events: {
                            click: function () {
                                if (this.x != undefined && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 20 || tileId == 22 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                                    var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                    window.open("./datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token);
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
                    };
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
                    });
                }
                else {
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
        };
        chartObj.series = seriesJsonObject;
        if (seriesJsonObject.length > 0) {
            if (chartData.avarage) {
                total = total / avagerCount;
            }
            if (chartData.unit == "$" && chartData.avarage == false) {
                chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString();
            }
            else if (chartData.unit == "$" && chartData.avarage == true) {
                chartObj.subtitle.text = "Average " + chartData.unit + Math.floor(total).toLocaleString();
            }
            else if (chartData.unit == "%" && chartData.avarage == false) {
                chartObj.subtitle.text = "Total " + this.numberWithPercentage(total).toLocaleString() + chartData.unit;
            }
            else if (chartData.unit == "%" && chartData.avarage == true) {
                chartObj.subtitle.text = "Average " + this.numberWithPercentage(total).toLocaleString() + chartData.unit;
            }
            else {
                chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString();
            }
        }
    };
    DashboardBodyComponent.prototype.chartSwitchNAT = function (id) {
        var chartObj = this.contentBody[id];
        chartObj.chart.type = "pie";
        var __this = this;
        chartObj.chart.events = { drilldown: function (e, d) { __this.drillDown(e, this, id); }, drillup: function (e, d) { __this.drillUp(e, this, id); } };
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
        };
        chartObj.tooltip = {
            pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.0f}</b> ({point.percentage:.0f}%)<br/>',
            shared: true
        };
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
                var drillDownObj = {};
                var __this = this;
                drillDownObj.point = {
                    events: {
                        click: function () {
                            if (this.x != undefined && this.name > 3 && (id == 9 || id == 10 || id == 11 || id == 12 || id == 13 || id == 19 || id == 20 || id == 22 || id == 23 || id == 31 || id == 32 || id == 33 || id == 36)) {
                                var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                window.open("./datatable?chartId=" + id + "&territory=" + this.name + "&token=" + token);
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
                };
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
                });
            }
            else {
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
        };
        if (chartData.avarage) {
            total = total / avagerCount;
        }
        if (chartData.unit == "$" && chartData.avarage == false) {
            chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + this.printButtonName[id];
        }
        else if (chartData.unit == "$" && chartData.avarage == true) {
            chartObj.subtitle.text = "Average " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + this.printButtonName[id];
        }
        else if (chartData.unit == "%" && chartData.avarage == false) {
            chartObj.subtitle.text = "Total " + this.numberWithPercentage(total).toLocaleString() + chartData.unit + "<br>" + this.printButtonName[id];
        }
        else if (chartData.unit == "%" && chartData.avarage == true) {
            chartObj.subtitle.text = "Average " + this.numberWithPercentage(total).toLocaleString() + chartData.unit + "<br>" + this.printButtonName[id];
        }
        else {
            chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + this.printButtonName[id];
        }
        this.chartObjects[id] = new Highcharts.Chart(chartObj);
    };
    DashboardBodyComponent.prototype.chartSwitch = function (buttonName, id) {
        this.printButtonName[id] = buttonName;
        if (buttonName === "NAT") {
            this.chartSwitchNAT(id);
            return;
        }
        var chartObject = this.contentBody[id];
        chartObject.chart.type = "pie";
        var __this = this;
        chartObject.chart.events = { drilldown: function (e, d) { __this.drillDown(e, this, id); }, drillup: function (e, d) { __this.drillUp(e, this, id); } };
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
        };
        chartObject.tooltip = {
            pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.0f}</b> ({point.percentage:.0f}%)<br/>',
            shared: true
        };
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
                var drillDownObj = {};
                var __this = this;
                drillDownObj.point = {
                    events: {
                        click: function () {
                            if (this.x != undefined && (id == 9 || id == 10 || id == 11 || id == 12 || id == 13 || id == 19 || id == 20 || id == 22 || id == 23 || id == 31 || id == 32 || id == 33 || id == 36)) {
                                var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                window.open("./datatable?chartId=" + id + "&territory=" + this.name + "&token=" + token);
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
                };
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
                        });
                        for (var k = 0; k < drilldownData[j].data.length; k++) {
                            var inObj = drilldownData[j].data[k];
                            drillDownObj.data.push([
                                inObj.name,
                                (inObj.value)]);
                        }
                    }
                }
                drilldownArray.push(drillDownObj);
            }
            else {
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
        };
        if (chartData.avarage) {
            total = total / avagerCount;
        }
        if (chartData.unit == "$" && chartData.avarage == false) {
            chartObject.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + this.printButtonName[id];
        }
        else if (chartData.unit == "$" && chartData.avarage == true) {
            chartObject.subtitle.text = "Average " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + this.printButtonName[id];
        }
        else if (chartData.unit == "%" && chartData.avarage == false) {
            chartObject.subtitle.text = "Total " + this.numberWithPercentage(total).toLocaleString() + chartData.unit + "<br>" + this.printButtonName[id];
        }
        else if (chartData.unit == "%" && chartData.avarage == true) {
            chartObject.subtitle.text = "Average " + this.numberWithPercentage(total).toLocaleString() + chartData.unit + "<br>" + this.printButtonName[id];
        }
        else {
            chartObject.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString() + "<br>" + this.printButtonName[id];
        }
        this.chartObjects[id] = new Highcharts.Chart(chartObject);
    };
    DashboardBodyComponent.prototype.lengendItemClick = function (event, clickedSeries, id, retention) {
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
                }
                else {
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
        }
        else if (obj.unit == "$" && obj.avarage == true) {
            event.target.chart.setTitle(null, { text: "Average " + obj.unit + Math.floor(total).toLocaleString() });
        }
        else if (obj.unit == "%" && obj.avarage == false) {
            event.target.chart.setTitle(null, { text: "Total " + this.numberWithPercentage(total).toLocaleString() + obj.unit });
        }
        else if (obj.unit == "%" && obj.avarage == true) {
            event.target.chart.setTitle(null, { text: "Average " + this.numberWithPercentage(total).toLocaleString() + obj.unit });
        }
        else {
            event.target.chart.setTitle(null, { text: "Total " + obj.unit + Math.floor(total).toLocaleString() });
        }
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Object)
    ], DashboardBodyComponent.prototype, "data", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Object)
    ], DashboardBodyComponent.prototype, "enablewelcomeprompt", void 0);
    __decorate([
        core_1.ViewChild("content"), 
        __metadata('design:type', (typeof (_a = typeof core_1.TemplateRef !== 'undefined' && core_1.TemplateRef) === 'function' && _a) || Object)
    ], DashboardBodyComponent.prototype, "model", void 0);
    __decorate([
        core_1.ViewChild("topModel"), 
        __metadata('design:type', (typeof (_b = typeof core_1.TemplateRef !== 'undefined' && core_1.TemplateRef) === 'function' && _b) || Object)
    ], DashboardBodyComponent.prototype, "topModel", void 0);
    __decorate([
        core_1.ViewChild("statisticModel"), 
        __metadata('design:type', (typeof (_c = typeof core_1.TemplateRef !== 'undefined' && core_1.TemplateRef) === 'function' && _c) || Object)
    ], DashboardBodyComponent.prototype, "statisticModel", void 0);
    DashboardBodyComponent = __decorate([
        core_1.Component({
            selector: "app-content",
            template: __webpack_require__(628),
            styleUrls: [],
            styles: ['button:focus { background-color:#025fb1; color: #fff; }']
        }), 
        __metadata('design:paramtypes', [(typeof (_d = typeof dashboard_body_service_1.DashboardBodyService !== 'undefined' && dashboard_body_service_1.DashboardBodyService) === 'function' && _d) || Object, (typeof (_e = typeof ng_bootstrap_1.NgbModal !== 'undefined' && ng_bootstrap_1.NgbModal) === 'function' && _e) || Object, (typeof (_f = typeof cookies_service_1.CookieService !== 'undefined' && cookies_service_1.CookieService) === 'function' && _f) || Object, (typeof (_g = typeof router_1.Router !== 'undefined' && router_1.Router) === 'function' && _g) || Object])
    ], DashboardBodyComponent);
    return DashboardBodyComponent;
    var _a, _b, _c, _d, _e, _f, _g;
}());
exports.DashboardBodyComponent = DashboardBodyComponent;
//# sourceMappingURL=dashboard-body.component.js.map

/***/ }),

/***/ 380:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var router_1 = __webpack_require__(55);
var cookies_service_1 = __webpack_require__(20);
var login_service_1 = __webpack_require__(387);
var LoginComponent = (function () {
    function LoginComponent(loginService, router, _compiler, activatedRoute, cookieService) {
        this.loginService = loginService;
        this.router = router;
        this._compiler = _compiler;
        this.activatedRoute = activatedRoute;
        this.cookieService = cookieService;
        this.userdata = {};
        this.ssouserdata = {};
        this.ssotoken = "";
        this.ssodealercode = "";
        this.ssopositioncode = "";
        this.loginFailed = "";
        this.loginErrorMessage = "";
        this.hideLoginPage = false;
        this._compiler.clearCache();
    }
    LoginComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.user = {
            username: '',
            password: ''
        };
        this.activatedRoute.queryParams.subscribe(function (params) {
            _this.ssotoken = params['token'];
            _this.ssodealercode = params['dc'];
            _this.ssopositioncode = params['pc'];
            if (_this.ssotoken !== "" && _this.ssotoken !== undefined) {
                _this.hideLoginPage = true;
                _this.ssologin(_this.ssotoken, _this.ssopositioncode, _this.ssodealercode);
            }
        });
        this.refreshLogin();
    };
    LoginComponent.prototype.ssologin = function (ssotoken, ssopositioncode, ssodealercode) {
        var _this = this;
        this.loginService.getSSOLoginResponse(this.ssotoken, this.ssopositioncode, this.ssodealercode).subscribe(function (resUserData) {
            _this.userdata = (resUserData);
            if (resUserData["token"].length > 0) {
                _this.loginService.setUserData(_this.userdata);
                var poscodes = _this.userdata.positionCode;
                var delcodes = _this.userdata.dealerCode;
                sessionStorage.setItem("selectedCodeData", JSON.stringify({
                    "selectedPositionCode": poscodes === undefined ? 0 : poscodes[0] === "" ? "0" : poscodes.length > 0 ? poscodes[0] : 0,
                    "selectedDealerCode": delcodes === undefined ? 0 : delcodes[0] === "" ? "0" : delcodes.length > 0 ? delcodes[0] : 0
                }));
                var url = ["myfcadashboard"];
                _this.router.navigate(url);
            }
            else {
                var url = ["login"];
                _this.router.navigate(url);
            }
        });
    };
    LoginComponent.prototype.refreshLogin = function () {
        var _this = this;
        sessionStorage.setItem("showWelcomePopup", "false");
        var user = this.cookieService.get("token");
        if (user !== undefined) {
            if (user !== undefined && user.length > 1) {
                this.hideLoginPage = true;
                this.loginService.getRefreshLoginResponse(user).subscribe(function (refreshTokenData) {
                    _this.refreshTokenData = (refreshTokenData);
                    if (refreshTokenData.token.length > 1) {
                        _this.loginService.setUserData(_this.refreshTokenData);
                        var poscodes = _this.refreshTokenData.positionCode;
                        var delcodes = _this.refreshTokenData.dealerCode;
                        sessionStorage.setItem("selectedCodeData", JSON.stringify({
                            "selectedPositionCode": poscodes === undefined ? 0 : poscodes[0] === "" ? "0" : poscodes.length > 0 ? poscodes[0] : 0,
                            "selectedDealerCode": delcodes === undefined ? 0 : delcodes[0] === "" ? "0" : delcodes.length > 0 ? delcodes[0] : 0
                        }));
                        var url = ["myfcadashboard"];
                        _this.router.navigate(url);
                    }
                    else {
                    }
                }, function (error) {
                    alert("error in refreshing");
                });
            }
        }
    };
    LoginComponent.prototype.login = function () {
        var _this = this;
        if (this.user.username.trim() === "" && this.user.password.trim() === "") {
            this.loginFailed = "Login Failed";
            this.loginErrorMessage = "Please enter your SID/TID and Password";
            return;
        }
        else if (this.user.username.trim() === "" && this.user.password.trim() !== null) {
            this.loginFailed = "Login Failed";
            this.loginErrorMessage = "Please enter your SID/TID";
            return;
        }
        else if (this.user.username.trim() !== null && this.user.password.trim() === "") {
            this.loginFailed = "Login Failed";
            this.loginErrorMessage = "Please enter your Password";
            return;
        }
        this.loginService.getLoginResponse(this.user.username, this.user.password).subscribe(function (resUserData) {
            _this.userdata = (resUserData);
            // console.log(resUserData)
            if (resUserData["token"].length > 0) {
                _this.loginService.setUserData(_this.userdata);
                var poscodes = _this.userdata.positionCode;
                var delcodes = _this.userdata.dealerCode;
                sessionStorage.setItem("selectedCodeData", JSON.stringify({
                    "selectedPositionCode": poscodes === undefined ? 0 : poscodes[0] === "" ? "0" : poscodes.length > 0 ? poscodes[0] : 0,
                    "selectedDealerCode": delcodes === undefined ? 0 : delcodes[0] === "" ? "0" : delcodes.length > 0 ? delcodes[0] : 0
                }));
                var url = ["myfcadashboard"];
                _this.router.navigate(url);
            }
        }, function (error) {
            _this.loginFailed = "Login Failed";
            _this.loginErrorMessage = "Please enter your valid SID/TID and password";
        });
    };
    LoginComponent = __decorate([
        core_1.Component({
            template: __webpack_require__(631),
        }), 
        __metadata('design:paramtypes', [(typeof (_a = typeof login_service_1.LoginService !== 'undefined' && login_service_1.LoginService) === 'function' && _a) || Object, (typeof (_b = typeof router_1.Router !== 'undefined' && router_1.Router) === 'function' && _b) || Object, (typeof (_c = typeof core_1.Compiler !== 'undefined' && core_1.Compiler) === 'function' && _c) || Object, (typeof (_d = typeof router_1.ActivatedRoute !== 'undefined' && router_1.ActivatedRoute) === 'function' && _d) || Object, (typeof (_e = typeof cookies_service_1.CookieService !== 'undefined' && cookies_service_1.CookieService) === 'function' && _e) || Object])
    ], LoginComponent);
    return LoginComponent;
    var _a, _b, _c, _d, _e;
}());
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login.component.js.map

/***/ }),

/***/ 381:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var ProfileRootPageComponent = (function () {
    function ProfileRootPageComponent() {
    }
    ProfileRootPageComponent = __decorate([
        core_1.Component({
            selector: '',
            template: __webpack_require__(633),
        }), 
        __metadata('design:paramtypes', [])
    ], ProfileRootPageComponent);
    return ProfileRootPageComponent;
}());
exports.ProfileRootPageComponent = ProfileRootPageComponent;
//# sourceMappingURL=profile-rootpage.component.js.map

/***/ }),

/***/ 382:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var dashboard_body_component_1 = __webpack_require__(379);
var RootPageComponent = (function () {
    function RootPageComponent() {
        this.sampleUsers = [];
        this.userdata = {};
    }
    RootPageComponent.prototype.ngOnInit = function () {
        this.userdata = JSON.parse(sessionStorage.getItem("CurrentUser"));
        this.tilesArray = JSON.parse(sessionStorage.getItem("tiles"));
    };
    RootPageComponent.prototype.onProfileChange = function () {
        this.bodyContent.reload();
    };
    __decorate([
        core_1.ViewChild("bodyContent"), 
        __metadata('design:type', (typeof (_a = typeof dashboard_body_component_1.DashboardBodyComponent !== 'undefined' && dashboard_body_component_1.DashboardBodyComponent) === 'function' && _a) || Object)
    ], RootPageComponent.prototype, "bodyContent", void 0);
    RootPageComponent = __decorate([
        core_1.Component({
            selector: '',
            template: __webpack_require__(635),
        }), 
        __metadata('design:paramtypes', [])
    ], RootPageComponent);
    return RootPageComponent;
    var _a;
}());
exports.RootPageComponent = RootPageComponent;
//# sourceMappingURL=root-page.component.js.map

/***/ }),

/***/ 383:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var http_1 = __webpack_require__(27);
var cookies_service_1 = __webpack_require__(20);
var Observable_1 = __webpack_require__(1);
__webpack_require__(69);
var AdminService = (function () {
    function AdminService(http, cookieService) {
        this.http = http;
        this.cookieService = cookieService;
    }
    AdminService.prototype.setEmulateUserData = function (emulateuserData) {
        var adminToken = this.cookieService.get("token");
        this.cookieService.put("adminToken", adminToken);
        this.cookieService.put("token", emulateuserData.item);
    };
    AdminService.prototype.setEndEmulateUserData = function (endEmulateUserData) {
    };
    AdminService.prototype.getImageList = function () {
        //var getImageListUrl = "https://test.myfcarewards.com/myfcarewards/services/files/listFiles";
        var getImageListUrl = "./services/files/listFiles";
        return this.http.get(getImageListUrl)
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    AdminService.prototype.getPositionCode = function () {
        var getPositionCodeUrl = "./assets/json/positioncode-array.json";
        var headers = new http_1.Headers();
        headers.append('Authorization', "");
        return this.http.get(getPositionCodeUrl, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    AdminService.prototype.getRoles = function () {
        var getPositionCodeUrl = "./assets/json/admin-chooseview.json";
        var headers = new http_1.Headers();
        headers.append('Authorization', "");
        return this.http.get(getPositionCodeUrl, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    AdminService.prototype.getAdminData = function () {
        var adminService = "./assets/json/test-admin.json";
        var headers = new http_1.Headers();
        headers.append('Authorization', "");
        return this.http.get(adminService, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    AdminService.prototype.getEmulateUserData = function (sid) {
        // var getEmulateUserDataUrl = "https://test.myfcarewards.com/myfcarewards/services/admin/emulate/" + sid;
        var getEmulateUserDataUrl = "./services/admin/emulate/" + sid;
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', validToken);
        return this.http.get(getEmulateUserDataUrl, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    AdminService.prototype.addBanner = function (roleID, bc, orderBy, image) {
        // var getAddBannerUrl = "https://test.myfcarewards.com/myfcarewards/services/admin/banner/add/";
        var getAddBannerUrl = "./services/admin/banner/add/";
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var body = {
            "dashBoardBannersID": 0, "image": image, "roleID": roleID, "orderBy": orderBy, "businessCenter": bc,
            "link": "", "createdDate": null, "createdBy": "", "updatedDate": null, "updatedBy": "", "delFlag": ""
        };
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', validToken);
        headers.append("Cache-Control", "no-cache");
        headers.append("Cache-Control", "no-store");
        return this.http.post(getAddBannerUrl, body, { headers: headers })
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    AdminService.prototype.getAllBannerData = function () {
        var getAllBannerDataUrl = "./services/admin/banner/getAll/";
        // var getAllBannerDataUrl = "https://test.myfcarewards.com/myfcarewards/services/admin/banner/getAll/";
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', validToken);
        return this.http.get(getAllBannerDataUrl, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    AdminService.prototype.editBannerData = function (editBannerDataObj) {
        debugger;
        var editBannerDataUrl = "./services/admin/banner/update/";
        // var editBannerDataUrl = "https://test.myfcarewards.com/myfcarewards/services/admin/banner/update/";
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var body = editBannerDataObj;
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', validToken);
        return this.http.put(editBannerDataUrl, body, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    AdminService.prototype.deleteBannerData = function (dashBoardBannersID) {
        var deleteBannerDataUrl = "./services/admin/banner/delete/" + dashBoardBannersID;
        // var deleteBannerDataUrl = "https://test.myfcarewards.com/myfcarewards/services/admin/banner/delete/" + dashBoardBannersID;
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', validToken);
        return this.http.delete(deleteBannerDataUrl, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    AdminService.prototype.handleError = function (error) {
        var errMsg = "";
        if (error instanceof http_1.Response) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        return Observable_1.Observable.throw(errMsg);
    };
    AdminService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [(typeof (_a = typeof http_1.Http !== 'undefined' && http_1.Http) === 'function' && _a) || Object, (typeof (_b = typeof cookies_service_1.CookieService !== 'undefined' && cookies_service_1.CookieService) === 'function' && _b) || Object])
    ], AdminService);
    return AdminService;
    var _a, _b;
}());
exports.AdminService = AdminService;
//# sourceMappingURL=admin.service.js.map

/***/ }),

/***/ 384:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var http_1 = __webpack_require__(27);
var Observable_1 = __webpack_require__(1);
__webpack_require__(69);
var BannerService = (function () {
    function BannerService(http) {
        this.http = http;
    }
    BannerService.prototype.getBanners = function () {
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var positioncodes = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedPositionCode;
        var dealerlcodes = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedDealerCode;
        // var getBannersServiceUrl = "https://test.myfcarewards.com/myfcarewards/services/banners/" + positioncodes + "/" + dealerlcodes + "/"
        var getBannersServiceUrl = "./services/banners/" + positioncodes + "/" + dealerlcodes + "/";
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        return this.http.get(getBannersServiceUrl, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    BannerService.prototype.handleError = function (error) {
        var errMsg = "";
        if (error instanceof http_1.Response) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        return Observable_1.Observable.throw(errMsg);
    };
    BannerService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [(typeof (_a = typeof http_1.Http !== 'undefined' && http_1.Http) === 'function' && _a) || Object])
    ], BannerService);
    return BannerService;
    var _a;
}());
exports.BannerService = BannerService;
//# sourceMappingURL=banner.service.js.map

/***/ }),

/***/ 385:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var http_1 = __webpack_require__(27);
var cookies_service_1 = __webpack_require__(20);
var Observable_1 = __webpack_require__(1);
__webpack_require__(69);
var DashboardBodyService = (function () {
    function DashboardBodyService(http, cookieService) {
        this.http = http;
        this.cookieService = cookieService;
        this.baseUrl = "";
        //private baseUrl = "https://test.myfcarewards.com/myfcarewards/";
        this.tiles = new Array();
        this.userdata = {};
    }
    DashboardBodyService.prototype.setTiles = function (tiles) {
        sessionStorage.setItem("tiles", JSON.stringify(tiles));
    };
    DashboardBodyService.prototype.getTiles = function () {
        return this.tiles;
    };
    DashboardBodyService.prototype.setUserData = function (userdata) {
        this.cookieService.put("CurrentUser", JSON.stringify(userdata));
    };
    DashboardBodyService.prototype.getUsersData = function () {
        return this.userdata;
    };
    DashboardBodyService.prototype.getNumberOfTiltes = function () {
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var positioncodes = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedPositionCode;
        var dealerlcodes = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedDealerCode;
        var getNumberOfTilesServiceUrl = this.baseUrl + "services/notile/" + positioncodes + "/" + dealerlcodes;
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        // headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");
        return this.http.get(getNumberOfTilesServiceUrl, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    DashboardBodyService.prototype.getTilteJson = function (id) {
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var positioncodes = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedPositionCode;
        var dealerlcodes = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedDealerCode;
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        // headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");
        var tileService = this.baseUrl + "services/tile/" + id + "/" + positioncodes + "/" + dealerlcodes;
        return this.http.get(tileService, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    DashboardBodyService.prototype.getChartJson = function (id) {
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var positioncodes = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedPositionCode;
        var dealerlcodes = JSON.parse(sessionStorage.getItem("selectedCodeData")).selectedDealerCode;
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        // headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");       
        var chartService = this.baseUrl + "services/tile/" + id + "/" + positioncodes + "/" + dealerlcodes;
        return this.http.get(chartService, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    DashboardBodyService.prototype.handleError = function (error) {
        var errMsg = "";
        if (error instanceof http_1.Response) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        return Observable_1.Observable.throw(errMsg);
    };
    DashboardBodyService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [(typeof (_a = typeof http_1.Http !== 'undefined' && http_1.Http) === 'function' && _a) || Object, (typeof (_b = typeof cookies_service_1.CookieService !== 'undefined' && cookies_service_1.CookieService) === 'function' && _b) || Object])
    ], DashboardBodyService);
    return DashboardBodyService;
    var _a, _b;
}());
exports.DashboardBodyService = DashboardBodyService;
//# sourceMappingURL=dashboard-body.service.js.map

/***/ }),

/***/ 386:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var HeaderService = (function () {
    function HeaderService() {
    }
    HeaderService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [])
    ], HeaderService);
    return HeaderService;
}());
exports.HeaderService = HeaderService;
//# sourceMappingURL=header.service.js.map

/***/ }),

/***/ 387:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var http_1 = __webpack_require__(27);
var Observable_1 = __webpack_require__(1);
var cookies_service_1 = __webpack_require__(20);
__webpack_require__(69);
var LoginService = (function () {
    function LoginService(http, cookieService) {
        this.http = http;
        this.cookieService = cookieService;
        this.userdata = {};
    }
    LoginService.prototype.setUserData = function (userdata) {
        sessionStorage.setItem("CurrentUser", "");
        sessionStorage.removeItem('CurrentUser');
        sessionStorage.removeItem('selectedCodeData');
        sessionStorage.setItem("CurrentUser", JSON.stringify(userdata));
        this.cookieService.put("token", (userdata.token));
    };
    LoginService.prototype.getUsersData = function () {
        return this.userdata;
    };
    LoginService.prototype.getSSOLoginResponse = function (ssotoken, ssodealercode, ssopositioncode) {
        var url = "./login/token/" + ssotoken + "/" + ssodealercode + "/" + ssopositioncode;
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        headers.append("Cache-Control", "no-cache");
        headers.append("Cache-Control", "no-store");
        return this.http.get(url)
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    LoginService.prototype.getRefreshLoginResponse = function (token) {
        // var url = "https://test.myfcarewards.com/myfcarewards/login/tokenrefresh/";
        var url = "./login/tokenrefresh/";
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', token);
        // headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");
        return this.http.get(url, { headers: headers })
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    LoginService.prototype.getLoginResponse = function (username, password) {
        var url = "./login/token/";
        // var url = "https://test.myfcarewards.com/myfcarewards/login/token/";
        var body = { "username": username, "password": password };
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/json');
        // headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");
        return this.http.post(url, body, { headers: headers })
            .map(function (response) {
            return response.json();
        })
            .catch(this.handleError);
    };
    LoginService.prototype.handleError = function (error) {
        var errMsg = "";
        if (error instanceof http_1.Response) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        return Observable_1.Observable.throw(errMsg);
    };
    LoginService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [(typeof (_a = typeof http_1.Http !== 'undefined' && http_1.Http) === 'function' && _a) || Object, (typeof (_b = typeof cookies_service_1.CookieService !== 'undefined' && cookies_service_1.CookieService) === 'function' && _b) || Object])
    ], LoginService);
    return LoginService;
    var _a, _b;
}());
exports.LoginService = LoginService;
//# sourceMappingURL=login.service.js.map

/***/ }),

/***/ 388:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var http_1 = __webpack_require__(27);
var cookies_service_1 = __webpack_require__(20);
__webpack_require__(69);
var PositionCodeService = (function () {
    function PositionCodeService(http, cookieService) {
        this.http = http;
        this.cookieService = cookieService;
    }
    PositionCodeService.prototype.setCodeData = function (codeData) {
        this.selectedCodeData = sessionStorage.setItem("selectedCodeData", JSON.stringify(codeData));
        //this.selectedCodeData = this.cookieService.put("selectedCodeData", JSON.stringify(codeData))
    };
    PositionCodeService.prototype.getCodeData = function () {
        return JSON.parse(sessionStorage.getItem("selectedCodeData"));
        //return JSON.parse(this.cookieService.get("selectedCodeData")); 
    };
    PositionCodeService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [(typeof (_a = typeof http_1.Http !== 'undefined' && http_1.Http) === 'function' && _a) || Object, (typeof (_b = typeof cookies_service_1.CookieService !== 'undefined' && cookies_service_1.CookieService) === 'function' && _b) || Object])
    ], PositionCodeService);
    return PositionCodeService;
    var _a, _b;
}());
exports.PositionCodeService = PositionCodeService;
//# sourceMappingURL=positioncode.service.js.map

/***/ }),

/***/ 389:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var http_1 = __webpack_require__(27);
var cookies_service_1 = __webpack_require__(20);
var Observable_1 = __webpack_require__(1);
__webpack_require__(69);
var ProfileService = (function () {
    function ProfileService(http, cookieService) {
        this.http = http;
        this.cookieService = cookieService;
    }
    ProfileService.prototype.getProfileData = function () {
        //  var getProfileServiceUrl: string = 'https://test.myfcarewards.com/myfcarewards/UserProfile/Profile/';
        var getProfileServiceUrl = './UserProfile/Profile/';
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        // var validToken: any = JSON.parse(this.cookieService.get("CurrentUser")).token;
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        return this.http.get(getProfileServiceUrl, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    ProfileService.prototype.changeProfileData = function (name, email) {
        //  var getProfileServiceUrl: string = 'https://test.myfcarewards.com/myfcarewards/UserProfile/Profile/';
        var getProfileServiceUrl = './UserProfile/Profile/';
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        //var validToken: any = JSON.parse(this.cookieService.get("CurrentUser")).token;
        var body = { "name": name, "email": email };
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        return this.http.post(getProfileServiceUrl, body, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    ProfileService.prototype.changeUserPassword = function (password) {
        //var getPasswordServiceUrl: string = 'https://test.myfcarewards.com/myfcarewards/UserProfile/Password/';
        var getPasswordServiceUrl = './UserProfile/Password/';
        var validToken = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var body = { "item": password };
        var headers = new http_1.Headers();
        headers.append('Authorization', validToken);
        return this.http.post(getPasswordServiceUrl, body, { headers: headers })
            .map(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    ProfileService.prototype.handleError = function (error) {
        var errMsg = "";
        if (error instanceof http_1.Response) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        return Observable_1.Observable.throw(errMsg);
    };
    ProfileService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [(typeof (_a = typeof http_1.Http !== 'undefined' && http_1.Http) === 'function' && _a) || Object, (typeof (_b = typeof cookies_service_1.CookieService !== 'undefined' && cookies_service_1.CookieService) === 'function' && _b) || Object])
    ], ProfileService);
    return ProfileService;
    var _a, _b;
}());
exports.ProfileService = ProfileService;
//# sourceMappingURL=profile.service.js.map

/***/ }),

/***/ 423:
/***/ (function(module, exports) {

function webpackEmptyContext(req) {
	throw new Error("Cannot find module '" + req + "'.");
}
webpackEmptyContext.keys = function() { return []; };
webpackEmptyContext.resolve = webpackEmptyContext;
module.exports = webpackEmptyContext;
webpackEmptyContext.id = 423;


/***/ }),

/***/ 424:
/***/ (function(module, exports, __webpack_require__) {

"use strict";

var core_1 = __webpack_require__(0);
var platform_browser_dynamic_1 = __webpack_require__(510);
var app_module_1 = __webpack_require__(543);
var environment_1 = __webpack_require__(553);
if (environment_1.environment.production) {
    core_1.enableProdMode();
}
platform_browser_dynamic_1.platformBrowserDynamic().bootstrapModule(app_module_1.AppModule);
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 542:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var router_1 = __webpack_require__(55);
var http_1 = __webpack_require__(27);
var AppComponent = (function () {
    function AppComponent(router, http) {
        this.router = router;
        this.http = http;
    }
    AppComponent = __decorate([
        core_1.Component({
            selector: 'my-app',
            template: __webpack_require__(624),
            styles: [__webpack_require__(617)]
        }), 
        __metadata('design:paramtypes', [(typeof (_a = typeof router_1.Router !== 'undefined' && router_1.Router) === 'function' && _a) || Object, (typeof (_b = typeof http_1.Http !== 'undefined' && http_1.Http) === 'function' && _b) || Object])
    ], AppComponent);
    return AppComponent;
    var _a, _b;
}());
exports.AppComponent = AppComponent;
//# sourceMappingURL=app.component.js.map

/***/ }),

/***/ 543:
/***/ (function(module, exports, __webpack_require__) {

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
//importing default angular dependencies 
var core_1 = __webpack_require__(0);
var forms_1 = __webpack_require__(43);
var platform_browser_1 = __webpack_require__(138);
var http_1 = __webpack_require__(27);
var angular2_highcharts_1 = __webpack_require__(563);
var ng_bootstrap_1 = __webpack_require__(141);
var ng_bootstrap_2 = __webpack_require__(141);
//importing components
var app_component_1 = __webpack_require__(542);
var root_page_component_1 = __webpack_require__(382);
var app_routes_1 = __webpack_require__(550);
var header_component_1 = __webpack_require__(547);
var footer_component_1 = __webpack_require__(546);
var dashboard_body_component_1 = __webpack_require__(379);
var login_component_1 = __webpack_require__(380);
var banner_component_1 = __webpack_require__(545);
var admin_component_1 = __webpack_require__(544);
var admin_rootpage_component_1 = __webpack_require__(378);
var table_component_1 = __webpack_require__(551);
var positioncode_component_1 = __webpack_require__(548);
var profile_rootpage_component_1 = __webpack_require__(381);
var profile_component_1 = __webpack_require__(549);
//importing services
var dashboard_body_service_1 = __webpack_require__(385);
var footer_service_1 = __webpack_require__(552);
var header_service_1 = __webpack_require__(386);
var login_service_1 = __webpack_require__(387);
var banner_service_1 = __webpack_require__(384);
var admin_service_1 = __webpack_require__(383);
var positioncode_service_1 = __webpack_require__(388);
var profile_service_1 = __webpack_require__(389);
var cookies_service_1 = __webpack_require__(20);
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            imports: [
                platform_browser_1.BrowserModule,
                http_1.HttpModule,
                forms_1.FormsModule,
                angular2_highcharts_1.ChartModule,
                forms_1.ReactiveFormsModule,
                app_routes_1.AppRoutingModule,
                ng_bootstrap_1.NgbModule.forRoot()
            ],
            providers: [
                dashboard_body_service_1.DashboardBodyService,
                footer_service_1.FooterService,
                header_service_1.HeaderService,
                login_service_1.LoginService,
                ng_bootstrap_2.NgbActiveModal,
                banner_service_1.BannerService,
                admin_service_1.AdminService,
                positioncode_service_1.PositionCodeService,
                profile_service_1.ProfileService,
                cookies_service_1.CookieService
            ],
            declarations: [
                app_component_1.AppComponent,
                login_component_1.LoginComponent,
                header_component_1.HeaderComponent,
                footer_component_1.FooterComponent,
                dashboard_body_component_1.DashboardBodyComponent,
                root_page_component_1.RootPageComponent,
                banner_component_1.BannerComponent,
                admin_component_1.AdminComponent,
                admin_rootpage_component_1.AdminRootPageComponent,
                table_component_1.TableComponent,
                positioncode_component_1.PositionCodeComponent,
                profile_rootpage_component_1.ProfileRootPageComponent,
                profile_component_1.ProfileComponent
            ],
            bootstrap: [app_component_1.AppComponent]
        }), 
        __metadata('design:paramtypes', [])
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ 544:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var router_1 = __webpack_require__(55);
var admin_service_1 = __webpack_require__(383);
var cookies_service_1 = __webpack_require__(20);
var AdminComponent = (function () {
    function AdminComponent(adminService, cookieService, router) {
        this.adminService = adminService;
        this.cookieService = cookieService;
        this.router = router;
        this.errorUploadImageMessage = "";
        this.imagelist = [];
        this.projects = [];
        this.allBannerTableData = [];
        this.editBannerDatum = {};
        this.deleteBannerDatum = {};
    }
    AdminComponent.prototype.ngOnInit = function () {
        var self = this;
        this.emulateuser = {
            sid: ''
        };
        this.getAllBannerData();
        this.uploadImage = {
            dashBoardBannersID: 0,
            image: "",
            roleId: 0,
            selectedRoleId: [],
            orderBy: 0,
            bc: [],
            link: "",
            createdDate: new Date,
            createdBy: "",
            updatedDate: new Date,
            updatedBy: "",
            delFlag: ""
        };
        this.getPositionCode();
        this.getRoles();
        this.getAdminData();
        this.getImageList(self);
        $('#accordion').collapse({
            toggle: false
        });
        $(function () {
            var availablePCs = ["Executive", "BC", "District Manager", "Dealer", "Manager", "Participant"];
            var availableBCs = ["CA", "DN", "GL", "MA", "MW", "NE", "SE", "SW", "WE"];
            $("#position-code-filter-input").autocomplete({
                source: availablePCs
            });
            $("#position-code-filter-input-2").autocomplete({
                source: availablePCs
            });
            $("#business-center-input").autocomplete({
                source: availableBCs
            });
            $("#business-center-filter-input").autocomplete({
                source: availableBCs
            });
            var id = $('#roleId').magicSuggest({
                allowFreeEntries: false,
                valueField: 'roleid',
                displayField: 'name',
                data: [{ roleid: 1, name: "Executive" }, { roleid: 12, name: "BC" }, { roleid: 11, name: "District Manager" }, { roleid: 10, name: "Dealer" }, { roleid: 5, name: "Manager" }, { roleid: 9, name: "Participant" }]
            });
            $(id).on('selectionchange', function (e, m) {
                self.setRole(this.getValue());
            });
            var bc = $('#bc').magicSuggest({
                allowFreeEntries: false,
                data: ["NAT", "CA", "DN", "GL", "MA", "MW", "NE", "SE", "SW", "WE"]
            });
            $(bc).on('selectionchange', function (e, m) {
                self.setBC(this.getValue());
            });
        });
    };
    AdminComponent.prototype.getAllBannerData = function () {
        var _this = this;
        this.adminService.getAllBannerData().subscribe(function (allBannerTableData) {
            _this.allBannerTableData = allBannerTableData;
        });
    };
    AdminComponent.prototype.testMethod = function () {
        for (var k = 0; k < this.imagelist.length; k++) {
            this.projects.push({
                value: this.imagelist[k],
                label: this.imagelist[k],
                icon: this.imagelist[k]
            });
        }
        $("#project").autocomplete({
            allowFreeEntries: false,
            minLength: 0,
            source: this.projects,
            focus: function (event, ui) {
                $("#project").val(ui.item.label);
                return false;
            },
            select: function (event, ui) {
                $("#project").val(ui.item.label);
                $("#project-id").val(ui.item.value);
                $("#project-icon").attr("src", "./services/loadrsc?id=" + ui.item.icon);
                return false;
            }
        })
            .autocomplete("instance")._renderItem = function (ul, item) {
            return $("<li>")
                .append("<div>" + item.label + "</div>")
                .appendTo(ul);
        };
    };
    AdminComponent.prototype.getImageList = function (self) {
        var _this = this;
        this.adminService.getImageList().subscribe(function (imagelist) {
            _this.imagelist = imagelist;
            self.testMethod();
        });
    };
    AdminComponent.prototype.setRole = function (b) {
        var a = [];
        if (!Array.isArray(b)) {
            a.push(b);
        }
        else {
            a = b;
        }
        this.uploadImage.selectedRoleId = a;
    };
    AdminComponent.prototype.setBC = function (b) {
        var a = [];
        if (!Array.isArray(b)) {
            a.push(b);
        }
        else {
            a = b;
        }
        this.uploadImage.bc = a;
    };
    AdminComponent.prototype.getPositionCode = function () {
        var _this = this;
        this.adminService.getPositionCode().subscribe(function (positioncode) {
            _this.positioncode = positioncode;
        });
    };
    AdminComponent.prototype.getRoles = function () {
        var _this = this;
        this.adminService.getRoles().subscribe(function (roles) {
            _this.roles = roles;
        });
    };
    AdminComponent.prototype.getAdminData = function () {
        var _this = this;
        this.adminService.getAdminData().subscribe(function (adminData) {
            _this.adminData = adminData.permissions;
        });
    };
    AdminComponent.prototype.emulateUser = function () {
        var _this = this;
        this.adminService.getEmulateUserData(this.emulateuser.sid).subscribe(function (emulateUserData) {
            _this.emulateUserData = emulateUserData;
            debugger;
            if (emulateUserData["item"].length > 0) {
                var adminToken = _this.cookieService.get("token");
                _this.cookieService.put("adminToken", adminToken);
                _this.cookieService.put("token", emulateUserData.item);
                var url = ["login"];
                _this.router.navigate(url);
            }
        });
    };
    AdminComponent.prototype.endEmulateUser = function () {
        this.cookieService.get("adminToken");
        this.adminService.setEndEmulateUserData(this.endEmulateUserData);
        var poscodes = this.emulateUserData.positionCode;
        var delcodes = this.emulateUserData.dealerCode;
        sessionStorage.setItem("selectedCodeData", JSON.stringify({
            "selectedPositionCode": poscodes === undefined ? 0 : poscodes[0] === "" ? "0" : poscodes.length > 0 ? poscodes[0] : 0,
            "selectedDealerCode": delcodes === undefined ? 0 : delcodes[0] === "" ? "0" : delcodes.length > 0 ? delcodes[0] : 0
        }));
        var url = ["myfcadashboard"];
        this.router.navigate(url);
    };
    AdminComponent.prototype.addBannerImage = function () {
        var _this = this;
        for (var i = 0; i < this.uploadImage.bc.length; i++) {
            for (var j = 0; j < this.uploadImage.selectedRoleId.length; j++) {
                this.adminService.addBanner(this.uploadImage.selectedRoleId[j], this.uploadImage.bc[i], this.uploadImage.orderBy, this.uploadImage.image).subscribe(function (addBannerData) {
                    _this.addBannerData = addBannerData;
                }, function (error) {
                    alert("Error in uploading images");
                    _this.errorUploadImageMessage = "Error in uploading images";
                });
            }
        }
    };
    AdminComponent.prototype.editBannerData = function (editBannerObj) {
        var _this = this;
        debugger;
        this.adminService.editBannerData(editBannerObj).subscribe(function (editBannerDatum) {
            _this.editBannerDatum = editBannerDatum;
        });
    };
    AdminComponent.prototype.deleteBannerData = function (dashBoardBannersID) {
        this.adminService.deleteBannerData(dashBoardBannersID);
    };
    AdminComponent = __decorate([
        core_1.Component({
            selector: "app-admin",
            template: __webpack_require__(626),
            styles: [__webpack_require__(618), __webpack_require__(619)]
        }), 
        __metadata('design:paramtypes', [(typeof (_a = typeof admin_service_1.AdminService !== 'undefined' && admin_service_1.AdminService) === 'function' && _a) || Object, (typeof (_b = typeof cookies_service_1.CookieService !== 'undefined' && cookies_service_1.CookieService) === 'function' && _b) || Object, (typeof (_c = typeof router_1.Router !== 'undefined' && router_1.Router) === 'function' && _c) || Object])
    ], AdminComponent);
    return AdminComponent;
    var _a, _b, _c;
}());
exports.AdminComponent = AdminComponent;
//# sourceMappingURL=admin.component.js.map

/***/ }),

/***/ 545:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var http_1 = __webpack_require__(27);
var banner_service_1 = __webpack_require__(384);
var BannerComponent = (function () {
    function BannerComponent(http, bannerService) {
        this.http = http;
        this.bannerService = bannerService;
        this.banners = new Array;
        this.getBanners();
    }
    BannerComponent.prototype.ngOnInit = function () {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"));
    };
    BannerComponent.prototype.getBanners = function () {
        var _this = this;
        this.bannerService.getBanners().subscribe(function (banners) {
            _this.banners = banners;
        });
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Object)
    ], BannerComponent.prototype, "data", void 0);
    BannerComponent = __decorate([
        core_1.Component({
            selector: "app-banner",
            template: __webpack_require__(627)
        }), 
        __metadata('design:paramtypes', [(typeof (_a = typeof http_1.Http !== 'undefined' && http_1.Http) === 'function' && _a) || Object, (typeof (_b = typeof banner_service_1.BannerService !== 'undefined' && banner_service_1.BannerService) === 'function' && _b) || Object])
    ], BannerComponent);
    return BannerComponent;
    var _a, _b;
}());
exports.BannerComponent = BannerComponent;
//# sourceMappingURL=banner.component.js.map

/***/ }),

/***/ 546:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var FooterComponent = (function () {
    function FooterComponent() {
    }
    FooterComponent = __decorate([
        core_1.Component({
            selector: "app-footer",
            template: __webpack_require__(629)
        }), 
        __metadata('design:paramtypes', [])
    ], FooterComponent);
    return FooterComponent;
}());
exports.FooterComponent = FooterComponent;
//# sourceMappingURL=footer.component.js.map

/***/ }),

/***/ 547:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var http_1 = __webpack_require__(27);
var header_service_1 = __webpack_require__(386);
var ng_bootstrap_1 = __webpack_require__(141);
var router_1 = __webpack_require__(55);
var cookies_service_1 = __webpack_require__(20);
var HeaderComponent = (function () {
    function HeaderComponent(activeModal, http, headerService, modalService, router, cookieService) {
        this.activeModal = activeModal;
        this.http = http;
        this.headerService = headerService;
        this.modalService = modalService;
        this.router = router;
        this.cookieService = cookieService;
        this.profileChange = new core_1.EventEmitter();
        this.banners = new Array;
        this.adminToken = "";
        this.poscodes = JSON.parse(sessionStorage.getItem("CurrentUser")).positionCode;
        this.delcodes = JSON.parse(sessionStorage.getItem("CurrentUser")).dealerCode;
        this.booleanAdmin = JSON.parse(sessionStorage.getItem("CurrentUser")).admin;
        this.booleanAdminToken = this.cookieService.get("adminToken");
    }
    HeaderComponent.prototype.positionCodeCancel = function () {
        this.positioncodeModal.close();
    };
    HeaderComponent.prototype.positionCodeSubmit = function (c) {
        c();
        this.profileChange.emit("");
    };
    HeaderComponent.prototype.ngOnInit = function () {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"));
        if (!this.enablePointer) {
            $(document).ready(function () {
                $("#enablePointer").css("cursor", "pointer");
                $("#enablePointer").css("text-decoration", "underline");
            });
        }
    };
    HeaderComponent.prototype.contactUs = function () {
        this.modalService.open(this.contactModal, { windowClass: 'contact-us' });
    };
    HeaderComponent.prototype.logout = function () {
        sessionStorage.removeItem('CurrentUser');
        sessionStorage.removeItem('selectedCodeData');
        sessionStorage.clear();
        this.cookieService.removeAll();
        // let loginUrl = ["login"]
        // this.router.navigate(loginUrl);
        window.open("https://dealerconnect.chrysler.com/login/login.html", '_self');
    };
    HeaderComponent.prototype.admin = function () {
        var adminUrl = ["admin"];
        this.router.navigate(adminUrl);
    };
    HeaderComponent.prototype.dashboardPage = function () {
        sessionStorage.setItem("showWelcomePopup", "false");
        var dashboardUrl = ["/myfcadashboard"];
        this.router.navigate(dashboardUrl);
    };
    HeaderComponent.prototype.dropdownPositionCode = function () {
        this.modalService.open(this.positioncodeModal, { windowClass: 'position-dealercode' });
    };
    HeaderComponent.prototype.profile = function () {
        var profileUrl = ["profile"];
        this.router.navigate(profileUrl);
    };
    HeaderComponent.prototype.endEmulation = function () {
        var adminToken = this.cookieService.get("adminToken");
        this.cookieService.remove("adminToken");
        this.cookieService.remove("token");
        this.cookieService.removeAll();
        sessionStorage.clear();
        window.sessionStorage.clear();
        //document.sessionStorage.clear();
        document;
        this.cookieService.put("token", adminToken);
        debugger;
        // this.cookieService.put(adminToken, "")
        // this.adminToken = adminToken;
        var url = ["login"];
        this.router.navigate(url);
        // this.booleanEndEmulation();
        // alert(this.booleanAdminToken)
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Object)
    ], HeaderComponent.prototype, "data", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Object)
    ], HeaderComponent.prototype, "retweetIconHide", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Object)
    ], HeaderComponent.prototype, "enablePointer", void 0);
    __decorate([
        core_1.Output("onProfileChange"), 
        __metadata('design:type', Object)
    ], HeaderComponent.prototype, "profileChange", void 0);
    __decorate([
        core_1.ViewChild("contactModal"), 
        __metadata('design:type', (typeof (_a = typeof core_1.TemplateRef !== 'undefined' && core_1.TemplateRef) === 'function' && _a) || Object)
    ], HeaderComponent.prototype, "contactModal", void 0);
    __decorate([
        core_1.ViewChild("positioncodeModal"), 
        __metadata('design:type', (typeof (_b = typeof ng_bootstrap_1.NgbModalRef !== 'undefined' && ng_bootstrap_1.NgbModalRef) === 'function' && _b) || Object)
    ], HeaderComponent.prototype, "positioncodeModal", void 0);
    HeaderComponent = __decorate([
        core_1.Component({
            selector: "app-header",
            template: __webpack_require__(630)
        }), 
        __metadata('design:paramtypes', [(typeof (_c = typeof ng_bootstrap_1.NgbActiveModal !== 'undefined' && ng_bootstrap_1.NgbActiveModal) === 'function' && _c) || Object, (typeof (_d = typeof http_1.Http !== 'undefined' && http_1.Http) === 'function' && _d) || Object, (typeof (_e = typeof header_service_1.HeaderService !== 'undefined' && header_service_1.HeaderService) === 'function' && _e) || Object, (typeof (_f = typeof ng_bootstrap_1.NgbModal !== 'undefined' && ng_bootstrap_1.NgbModal) === 'function' && _f) || Object, (typeof (_g = typeof router_1.Router !== 'undefined' && router_1.Router) === 'function' && _g) || Object, (typeof (_h = typeof cookies_service_1.CookieService !== 'undefined' && cookies_service_1.CookieService) === 'function' && _h) || Object])
    ], HeaderComponent);
    return HeaderComponent;
    var _a, _b, _c, _d, _e, _f, _g, _h;
}());
exports.HeaderComponent = HeaderComponent;
//# sourceMappingURL=header.component.js.map

/***/ }),

/***/ 548:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var positioncode_service_1 = __webpack_require__(388);
var cookies_service_1 = __webpack_require__(20);
var PositionCodeComponent = (function () {
    function PositionCodeComponent(positionCodeService, cookieService) {
        this.positionCodeService = positionCodeService;
        this.cookieService = cookieService;
        this.submitEvent = new core_1.EventEmitter();
        this.cancelEvent = new core_1.EventEmitter();
        this.pcode = [];
        this.dcode = [];
        this.codeData = { "selectedPositionCode": "", "selectedDealerCode": "" };
        this.poscodes = JSON.parse(sessionStorage.getItem("CurrentUser")).positionCode;
        this.delcodes = JSON.parse(sessionStorage.getItem("CurrentUser")).dealerCode;
    }
    PositionCodeComponent.prototype.ngOnInit = function () {
        this.code = {
            selectedPositionCode: '',
            selectedDealerCode: ''
        };
        this.code = this.positionCodeService.getCodeData();
        this.pcode = this.poscodes;
        this.dcode = this.delcodes;
    };
    PositionCodeComponent.prototype.selectPositionCode = function (poscode) {
    };
    PositionCodeComponent.prototype.submitClick = function () {
        this.positionCodeService.setCodeData(this.code);
        this.submitEvent.emit("");
    };
    PositionCodeComponent.prototype.cancelClick = function () {
        this.cancelEvent.emit("");
    };
    PositionCodeComponent.prototype.selectDealerCode = function (delcode) {
        this.positionCodeService.setCodeData(this.code);
    };
    __decorate([
        core_1.Output("onSubmit"), 
        __metadata('design:type', (typeof (_a = typeof core_1.EventEmitter !== 'undefined' && core_1.EventEmitter) === 'function' && _a) || Object)
    ], PositionCodeComponent.prototype, "submitEvent", void 0);
    __decorate([
        core_1.Output("onCancel"), 
        __metadata('design:type', (typeof (_b = typeof core_1.EventEmitter !== 'undefined' && core_1.EventEmitter) === 'function' && _b) || Object)
    ], PositionCodeComponent.prototype, "cancelEvent", void 0);
    PositionCodeComponent = __decorate([
        core_1.Component({
            selector: 'position-code',
            template: __webpack_require__(632),
        }), 
        __metadata('design:paramtypes', [(typeof (_c = typeof positioncode_service_1.PositionCodeService !== 'undefined' && positioncode_service_1.PositionCodeService) === 'function' && _c) || Object, (typeof (_d = typeof cookies_service_1.CookieService !== 'undefined' && cookies_service_1.CookieService) === 'function' && _d) || Object])
    ], PositionCodeComponent);
    return PositionCodeComponent;
    var _a, _b, _c, _d;
}());
exports.PositionCodeComponent = PositionCodeComponent;
//# sourceMappingURL=positioncode.component.js.map

/***/ }),

/***/ 549:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var router_1 = __webpack_require__(55);
var profile_service_1 = __webpack_require__(389);
var ProfileComponent = (function () {
    function ProfileComponent(profileService, router) {
        this.profileService = profileService;
        this.router = router;
        this.successPasswordChangeMessage = "";
        this.passwordNotMatched = "";
        this.errorPassWordChange = "";
        this.successProfileChangeMessage = "";
        this.errorProfileChangeMessage = "";
    }
    ProfileComponent.prototype.ngOnInit = function () {
        this.profiledata = {
            "email": "",
            "name": "",
            "password1": "",
            "password2": ""
        };
        this.getProfileData();
    };
    ProfileComponent.prototype.goBack = function () {
        sessionStorage.setItem("showWelcomePopup", "false");
        var dashboardUrl = ["/myfcadashboard"];
        this.router.navigate(dashboardUrl);
    };
    ProfileComponent.prototype.getProfileData = function () {
        var _this = this;
        this.profileService.getProfileData().subscribe(function (profiledata) {
            _this.profiledata = (profiledata);
        }, function (error) {
        });
    };
    ProfileComponent.prototype.changeProfileData = function () {
        var _this = this;
        this.profileService.changeProfileData(this.profiledata.name, this.profiledata.email).subscribe(function (profileChangeData) {
            _this.profileChangeData = (profileChangeData);
            _this.successProfileChangeMessage = "Your Profile Settings are Updated";
        }, function (error) {
            _this.errorProfileChangeMessage = "Error in profile change";
        });
    };
    ProfileComponent.prototype.changeUserPassword = function () {
        var _this = this;
        if (this.profiledata.password1.trim() !== this.profiledata.password2.trim()) {
            this.passwordNotMatched = "Password does not match, please enter the same password";
            return;
        }
        this.profileService.changeUserPassword(this.profiledata.password1).subscribe(function (password) {
            _this._password = (password);
            _this.successPasswordChangeMessage = "Your Profile Password is Updated";
        }, function (error) {
            _this.errorPassWordChange = "Error in password change";
        });
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Object)
    ], ProfileComponent.prototype, "enablewelcomeprompt", void 0);
    ProfileComponent = __decorate([
        core_1.Component({
            selector: 'app-profile',
            template: __webpack_require__(634),
        }), 
        __metadata('design:paramtypes', [(typeof (_a = typeof profile_service_1.ProfileService !== 'undefined' && profile_service_1.ProfileService) === 'function' && _a) || Object, (typeof (_b = typeof router_1.Router !== 'undefined' && router_1.Router) === 'function' && _b) || Object])
    ], ProfileComponent);
    return ProfileComponent;
    var _a, _b;
}());
exports.ProfileComponent = ProfileComponent;
//# sourceMappingURL=profile.component.js.map

/***/ }),

/***/ 550:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var router_1 = __webpack_require__(55);
var root_page_component_1 = __webpack_require__(382);
var login_component_1 = __webpack_require__(380);
var admin_rootpage_component_1 = __webpack_require__(378);
var profile_rootpage_component_1 = __webpack_require__(381);
var routes = [
    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },
    {
        path: "login",
        component: login_component_1.LoginComponent
    },
    {
        path: "myfcadashboard",
        component: root_page_component_1.RootPageComponent
    },
    {
        path: "admin",
        component: admin_rootpage_component_1.AdminRootPageComponent
    },
    {
        path: "profile",
        component: profile_rootpage_component_1.ProfileRootPageComponent
    }
];
var AppRoutingModule = (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = __decorate([
        core_1.NgModule({
            imports: [router_1.RouterModule.forRoot(routes)],
            exports: [router_1.RouterModule]
        }), 
        __metadata('design:paramtypes', [])
    ], AppRoutingModule);
    return AppRoutingModule;
}());
exports.AppRoutingModule = AppRoutingModule;
//# sourceMappingURL=app.routes.js.map

/***/ }),

/***/ 551:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var TableComponent = (function () {
    function TableComponent(element, renderer) {
        this.element = element;
        this.renderer = renderer;
        this.randomId = new Date().valueOf();
        var elementHtml = element.nativeElement;
        elementHtml.innerHTML = "<table id=\"" + this.randomId + "\"></table>\n                <div style=\"display:none\">\n                    <table id=\"detailsTable\">\n                    </table>\n                </div>";
    }
    TableComponent.prototype.ngOnDestroy = function () {
    };
    TableComponent.prototype.ngOnChanges = function () {
        function fnFormatDetails(table_id, html) {
            var sOut = "<table id=\"exampleTable_" + table_id + "\">";
            sOut += html;
            sOut += "</table>";
            return sOut;
        }
        var iTableCounter = 1;
        // var oTable;
        var oInnerTable;
        var detailsTableHtml;
        // you would probably be using templates here
        detailsTableHtml = $("#detailsTable").html();
        //Insert a 'details' column to the table
        var nCloneTh = document.createElement('th');
        var nCloneTd = document.createElement('td');
        nCloneTd.innerHTML = '<img src="https://i.imgur.com/SD7Dz.png">';
        nCloneTd.className = "center";
        $('#' + this.randomId + ' thead tr').each(function () {
            this.insertBefore(nCloneTh, this.childNodes[0]);
        });
        $('#' + this.randomId + ' tbody tr').each(function () {
            this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
        });
        //Initialse DataTables, with no sorting on the 'details' column
        var data = this.tableData.data === undefined ? [] : this.tableData.data;
        var head = this.tableData.headers === undefined ? [] : this.tableData.headers;
        var cloumns = [{ "title": "" }];
        var dataset = [];
        for (var i = 0; i < head.length; i++) {
            var obj = { "title": head[i] };
            cloumns.push(obj);
        }
        var innerData = {};
        for (var i = 0; i < data.length; i++) {
            var dataObj = data[i];
            innerData[i] = dataObj.innerData;
            dataset.push(dataObj.data);
        }
        var oTable = $('#' + this.randomId + '').dataTable({
            data: dataset,
            columns: cloumns,
            "bJQueryUI": true,
            // "aaData": newRowData,
            // "bPaginate": false,
            "destroy": true,
            "oLanguage": {
                "sInfo": "_TOTAL_ entries"
            },
        });
        $('#' + this.randomId + ' tbody td img').live('click', function () {
            var nTr = $(this).parents('tr')[0];
            var nTds = this;
            if (oTable.fnIsOpen(nTr)) {
                /* This row is already open - close it */
                this.src = "https://i.imgur.com/SD7Dz.png";
                oTable.fnClose(nTr);
            }
            else {
                /* Open this row */
                var rowIndex = oTable.fnGetPosition($(nTds).closest('tr')[0]);
                var data = innerData[rowIndex].data === undefined ? [] : innerData[rowIndex].data;
                var head = innerData[rowIndex].headers === undefined ? [] : innerData[rowIndex].headers;
                var cloumns = [];
                var dataset = [];
                for (var i = 0; i < head.length; i++) {
                    var obj = { "title": head[i] };
                    cloumns.push(obj);
                }
                //  var detailsRowData = newRowData[rowIndex].details;
                this.src = "https://i.imgur.com/d4ICC.png";
                oTable.fnOpen(nTr, fnFormatDetails(iTableCounter, detailsTableHtml), 'details');
                oInnerTable = $("#exampleTable_" + iTableCounter).dataTable({
                    data: data,
                    columns: cloumns,
                    "bJQueryUI": true,
                    "bFilter": false,
                    //     "aaData": detailsRowData,
                    "bSort": true,
                    "bPaginate": false,
                    "oLanguage": {
                        "sInfo": "_TOTAL_ entries"
                    },
                    "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                        /*
                      var imgLink = aData['pic'];
                      var imgTag = '<img width="100px" src="' + imgLink + '"/>';
                      $('td:eq(0)', nRow).html(imgTag);
                     return nRow;
                     */
                    }
                });
                iTableCounter = iTableCounter + 1;
            }
        });
        this.otable = oTable;
    };
    __decorate([
        core_1.Input("tableData"), 
        __metadata('design:type', Object)
    ], TableComponent.prototype, "tableData", void 0);
    TableComponent = __decorate([
        core_1.Component({
            selector: 'datatable',
            template: ""
        }), 
        __metadata('design:paramtypes', [(typeof (_a = typeof core_1.ElementRef !== 'undefined' && core_1.ElementRef) === 'function' && _a) || Object, (typeof (_b = typeof core_1.Renderer !== 'undefined' && core_1.Renderer) === 'function' && _b) || Object])
    ], TableComponent);
    return TableComponent;
    var _a, _b;
}());
exports.TableComponent = TableComponent;
//# sourceMappingURL=table.component.js.map

/***/ }),

/***/ 552:
/***/ (function(module, exports, __webpack_require__) {

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
var core_1 = __webpack_require__(0);
var FooterService = (function () {
    function FooterService() {
    }
    FooterService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [])
    ], FooterService);
    return FooterService;
}());
exports.FooterService = FooterService;
//# sourceMappingURL=footer.service.js.map

/***/ }),

/***/ 553:
/***/ (function(module, exports, __webpack_require__) {

"use strict";
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

exports.environment = {
    production: false
};
//# sourceMappingURL=environment.js.map

/***/ }),

/***/ 617:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(99)();
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 618:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(99)();
// imports


// module
exports.push([module.i, "/* Custom Stylesheet */\r\n\r\nbody{\r\n\tbackground-color:#f1f1f1!important;\r\n}\r\n\r\nnav .brand-logo {\r\n\tcolor: #444;\r\n\tdisplay: block;\r\n\tfloat: left;\r\n\twidth:400px;\r\n  \r\n}\r\n.nav{\r\n\tfloat:right;\r\n}\r\nnav .nav-item{\r\n\tfont-size:.75em;\r\n}\r\n.brand-logo img{\r\n\r\n  width:100%;\r\n  height:auto;\r\n}\r\n.navbar-header{\r\n\tfloat:right;\r\n\twidth:400px;\r\n\ttext-align:right;\r\n}\r\n.utility-nav {\r\n    padding: 6px;\r\n}\r\n.navbar-toggleable-md > .container {\r\n\r\n    display: block!important;\r\n  \r\n}\r\n/* Admin */\r\n#admin {\r\n    margin-top:150px;\r\n}\r\n.admin-right-col{\r\n    margin-top:3em;\r\n    font-size:0.8em;\r\n    color:#898989;\r\n}\r\n.admin-right-col h4{\r\n    font-weight:400;\r\n    \r\n    font-size:1.1em;\r\n}\r\n.admin-container h3{\r\n        font-size: 1.2em;\r\n        margin:1em 0;\r\n        color:#898989;\r\n        font-weight:400;\r\n}\r\n.admin-container{\r\n    background:#fff;\r\n    padding-top:2em;\r\n    padding-bottom:2em;\r\n}\r\n.box{\r\n    border: 3px solid#d7d7da;\r\n    padding: 10px;\r\n    margin: 30px 0 10px 0;\r\n}\r\n.box h4{\r\n        font-size: 16px;\r\n    margin-top: -22px;\r\n    background: #fff;\r\n    /*float: left;*/\r\n    padding: 0 7px;\r\n    color:#222;\r\n}\r\n.box label{\r\n    display:block;\r\n    float:left;\r\n    width:100%;\r\n        font-size: 0.8em;\r\n    color: #898989;\r\n    margin-top:10px;\r\n}\r\n.lbl-sm{\r\n        display:block;\r\n    float:left;\r\n    width:100%;\r\n        font-size: 0.8em;\r\n    color: #898989;\r\n    margin-top:10px;\r\n}\r\n.card-block .label-sm{\r\n    margin-top:0;\r\n}\r\nh1{\r\n    font-size:1.4em;\r\n}\r\n.admin-box{\r\n    background:#fff;\r\n    margin:1em 0;\r\n}\r\n#accordion{\r\n    padding:1em 0;\r\n}\r\n#accordion .card{\r\n    border-radius:0;\r\n}\r\n#accordion .card-header label{\r\n    float:left;\r\n    width:80%;\r\n}\r\n#accordion a.accordion-control{\r\n    background: #0275d8;\r\n    padding: 5px;\r\n    float: right;\r\nmargin: 5px;\r\n    width: 30px;\r\n    height: 30px;\r\n    line-height: 15px;\r\n    text-align: center;\r\n    color: #fff;\r\n    font-size: 26px;\r\n    text-decoration:none;\r\n}\r\n#accordion a.accordion-control:hover{\r\n    text-decoration:none;\r\n    background:#0067bf;\r\n}\r\n.card-header{\r\n    padding:0;\r\n}\r\n.card-block .checkbox{\r\n    padding:0.2em;\r\n}\r\n\r\n#project-icon {\r\n  height: auto;\r\n  max-width: 187px;\r\n  margin-top:10px;\r\n}\r\n\r\n#project-description {\r\n  margin: 0;\r\n  padding: 0;\r\n}\r\n\r\n.ui-autocomplete-input{\r\n  padding: 0px 5px;\r\n  font-size:14px;\r\n}\r\n/**/\r\n@media only screen and (max-width : 992px) {\r\n\r\n}\r\n\r\n@media only screen and (max-width : 600px) {\r\n\r\n}\r\n\r\n\r\n/* Menu System for Sull Browser */\r\n    /* full width horizontal submenus */\r\n#nav .menu_normal li {\r\n    position: static !Important;\r\n}\r\n\r\n#nav .menu_normal .sub-menu  {\r\n    left: 0 !Important;\r\n    right: 0 !Important;\r\n}\r\n\r\n#nav .menu_normal .sub-menu > li {\r\n    float: left !Important;\r\n}\r\n\r\n#nav .menu_normal .label_holder {\r\n    width: auto !Important;\r\n}\r\n\r\n\r\n    /* add vertical separator between submenu items */\r\n#nav .menu_normal .sub-menu > li {\r\n    border-left: 1px solid #4f4d49;\r\n}\r\n#nav .menu_normal .sub-menu > li:first-child {\r\n    border-left: 0;\r\n}\r\n\r\n#nav{\r\n\theight:165px;\r\n\t\r\n}\r\n\r\n.chart-controls{\r\n\tfloat: right;\r\n\theight: auto;\r\n\tpadding: 6px;\r\n\tposition: relative;\r\n\ttext-align: right\r\n}\r\n\r\n\r\n.navbar {\r\n\tcolor: #222;\r\n\tbackground-color: #fff;\r\n\twidth: 100%;\r\n\t/* height: 100px; */\r\n\tline-height: 56px;\r\n\tbox-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);\r\n}\r\n\r\n\r\n/* Footer */\r\n.extra-links {\r\n    background-color: #536ea4;\r\n    border: 1px solid #fff;\r\n    padding: 15px;\r\n}\r\n.extra-links ul {\r\n    margin: 6px;\r\n}\r\n.blue {\r\n    background-color: #6382c0 !important;\r\n}\r\n\r\nfooter.page-footer {\r\n    background-color: #ee6e73;\r\n    margin-top: 20px;\r\n    padding-top: 20px;\r\n}\r\n\r\nfooter.page-footer .footer-copyright {\r\n    background-color: rgba(51, 51, 51, 0.08);\r\n    color: rgba(255, 255, 255, 0.8);\r\n    height: 50px;\r\n    line-height: 50px;\r\n    overflow: hidden;\r\n}\r\n\r\n.white-text {\r\n    color: #ffffff !important;\r\n}\r\n.grey-text {\r\n    color: #9e9e9e !important;\r\n}\r\n\r\n/* Collections */\r\nul:not(.browser-default) {\r\n    list-style-type: none;\r\n    padding-left: 0;\r\n}\r\n.collection {\r\n  margin: 0.5rem 0 1rem 0;\r\n  border: 1px solid #e0e0e0;\r\n  border-radius: 2px;\r\n  overflow: hidden;\r\n  position: relative;\r\n  width: 95%;\r\n}\r\n\r\n.collection .collection-item {\r\n  background-color: #fff;\r\n  line-height: 1.5rem;\r\n  padding: 10px 20px;\r\n  margin: 0;\r\n  border-bottom: 1px solid #e0e0e0;\r\n}\r\n\r\n.collection .collection-item.avatar {\r\n  min-height: 84px;\r\n  padding-left: 72px;\r\n  position: relative;\r\n}\r\n\r\n.collection .collection-item.avatar .circle {\r\n  position: absolute;\r\n  width: 42px;\r\n  height: 42px;\r\n  overflow: hidden;\r\n  left: 15px;\r\n  display: inline-block;\r\n  vertical-align: middle;\r\n}\r\n\r\n.collection .collection-item.avatar i.circle {\r\n  font-size: 18px;\r\n  line-height: 42px;\r\n  color: #fff;\r\n  background-color: #999;\r\n  text-align: center;\r\n}\r\n\r\n.collection .collection-item.avatar .title {\r\n  font-size: 16px;\r\n}\r\n\r\n.collection .collection-item.avatar p {\r\n  margin: 0;\r\n}\r\n\r\n.collection .collection-item.avatar .secondary-content {\r\n  position: absolute;\r\n  top: 16px;\r\n  right: 16px;\r\n}\r\n\r\n.collection .collection-item:last-child {\r\n  border-bottom: none;\r\n}\r\n\r\n.collection .collection-item.active {\r\n  background-color: #26a69a;\r\n  color: #eafaf9;\r\n}\r\n\r\n.collection .collection-item.active .secondary-content {\r\n  color: #fff;\r\n}\r\n\r\n.collection a.collection-item {\r\n  display: block;\r\n  transition: .25s;\r\n  color: #26a69a;\r\n}\r\n\r\n.collection a.collection-item:not(.active):hover {\r\n  background-color: #ddd;\r\n}\r\n\r\n.collection.with-header .collection-header {\r\n  background-color: #fff;\r\n  border-bottom: 1px solid #e0e0e0;\r\n  padding: 20px 20px;\r\n}\r\n\r\n.collection-header h5{\r\n\tcolor:#5b88db !important;\r\n\tpadding: 10px 10px 10px 20px;\r\n}\r\n\r\n.collection.with-header .collection-item {\r\n  padding-left: 30px;\r\n}\r\n\r\n.collection.with-header .collection-item.avatar {\r\n  padding-left: 72px;\r\n}\r\n\r\n.glyphicon-envelope{\r\n\tcolor:#222;\r\n\tdisplay:block;\t\r\n}\r\n.h4, h4 {\r\n    font-size: 1.1em!important;\r\n\tmargin:4px;\r\n\tpadding:4px;\r\n\tcolor:#6382c0;\r\n}\r\n/* list styles */\r\n.list-group-item > .badge {\r\n    float: right;\r\n}\r\n.list-group-item > .itme-title {\r\n    float: left;\r\n}\r\n.list-group-item {\r\npadding: 0.45em;\r\n    display: block!important;\r\n}\r\n.badge {\r\n\tbackground-color: #6382c0;\r\n}\r\n\r\n\r\n\r\n/********************/\r\n\r\n.checkbox {\r\n  padding-left: 20px;\r\n  line-height:15px;\r\n  padding:0.75rem 1.25rem;\r\n  margin-left:10px;\r\n  \r\n}\r\n  .checkbox label {\r\n    display: inline-block;\r\n    position: relative;\r\n    padding-left: 5px; }\r\n    .checkbox label::before {\r\n      content: \"\";\r\n      display: inline-block;\r\n      position: absolute;\r\n      width: 17px;\r\n      height: 17px;\r\n      left: 0;\r\n      margin-left: -20px;\r\n      border: 1px solid #cccccc;\r\n      border-radius: 3px;\r\n      background-color: #fff;\r\n      transition: border 0.15s ease-in-out, color 0.15s ease-in-out; }\r\n    .checkbox label::after {\r\n      display: inline-block;\r\n      position: absolute;\r\n      width: 16px;\r\n      height: 16px;\r\n      left: 0;\r\n      top: 0;\r\n      margin-left: -20px;\r\n      padding-left: 3px;\r\n      padding-top: 1px;\r\n      font-size: 11px;\r\n      color: #555555; }\r\n  .checkbox input[type=\"checkbox\"] {\r\n    opacity: 0; }\r\n    .checkbox input[type=\"checkbox\"]:focus + label::before {\r\n      outline: thin dotted;\r\n      outline: 5px auto -webkit-focus-ring-color;\r\n      outline-offset: -2px; }\r\n    .checkbox input[type=\"checkbox\"]:checked + label::after {\r\n      font-family: 'FontAwesome';\r\n      content: \"\\F00C\"; }\r\n    .checkbox input[type=\"checkbox\"]:disabled + label {\r\n      opacity: 0.65; }\r\n      .checkbox input[type=\"checkbox\"]:disabled + label::before {\r\n        background-color: #eeeeee;\r\n        cursor: not-allowed; }\r\n  .checkbox.checkbox-circle label::before {\r\n    border-radius: 50%; }\r\n  .checkbox.checkbox-inline {\r\n    margin-top: 0; }\r\n\r\n.checkbox-primary input[type=\"checkbox\"]:checked + label::before {\r\n  background-color: #0275d8;\r\n  border-color: #0275d8; }\r\n.checkbox-primary input[type=\"checkbox\"]:checked + label::after {\r\n  color: #fff; }\r\n\r\n.checkbox-danger input[type=\"checkbox\"]:checked + label::before {\r\n  background-color: #d9534f;\r\n  border-color: #d9534f; }\r\n.checkbox-danger input[type=\"checkbox\"]:checked + label::after {\r\n  color: #fff; }\r\n\r\n.checkbox-info input[type=\"checkbox\"]:checked + label::before {\r\n  background-color: #5bc0de;\r\n  border-color: #5bc0de; }\r\n.checkbox-info input[type=\"checkbox\"]:checked + label::after {\r\n  color: #fff; }\r\n\r\n.checkbox-warning input[type=\"checkbox\"]:checked + label::before {\r\n  background-color: #f0ad4e;\r\n  border-color: #f0ad4e; }\r\n.checkbox-warning input[type=\"checkbox\"]:checked + label::after {\r\n  color: #fff; }\r\n\r\n.checkbox-success input[type=\"checkbox\"]:checked + label::before {\r\n  background-color: #5cb85c;\r\n  border-color: #5cb85c; }\r\n.checkbox-success input[type=\"checkbox\"]:checked + label::after {\r\n  color: #fff; }\r\n\r\n.radio {\r\n  padding-left: 20px; }\r\n  .radio label {\r\n    display: inline-block;\r\n    position: relative;\r\n    padding-left: 5px; }\r\n    .radio label::before {\r\n      content: \"\";\r\n      display: inline-block;\r\n      position: absolute;\r\n      width: 17px;\r\n      height: 17px;\r\n      left: 0;\r\n      margin-left: -20px;\r\n      border: 1px solid #cccccc;\r\n      border-radius: 50%;\r\n      background-color: #fff;\r\n      transition: border 0.15s ease-in-out; }\r\n    .radio label::after {\r\n      display: inline-block;\r\n      position: absolute;\r\n      content: \" \";\r\n      width: 11px;\r\n      height: 11px;\r\n      left: 3px;\r\n      top: 3px;\r\n      margin-left: -20px;\r\n      border-radius: 50%;\r\n      background-color: #555555;\r\n      -webkit-transform: scale(0, 0);\r\n      transform: scale(0, 0);\r\n      transition:-webkit-transform 0.1s cubic-bezier(0.8, -0.33, 0.2, 1.33);\r\n      transition:transform 0.1s cubic-bezier(0.8, -0.33, 0.2, 1.33);\r\n      transition: transform 0.1s cubic-bezier(0.8, -0.33, 0.2, 1.33), -webkit-transform 0.1s cubic-bezier(0.8, -0.33, 0.2, 1.33); }\r\n  .radio input[type=\"radio\"] {\r\n    opacity: 0; }\r\n    .radio input[type=\"radio\"]:focus + label::before {\r\n      outline: thin dotted;\r\n      outline: 5px auto -webkit-focus-ring-color;\r\n      outline-offset: -2px; }\r\n    .radio input[type=\"radio\"]:checked + label::after {\r\n      -webkit-transform: scale(1, 1);\r\n      transform: scale(1, 1); }\r\n    .radio input[type=\"radio\"]:disabled + label {\r\n      opacity: 0.65; }\r\n      .radio input[type=\"radio\"]:disabled + label::before {\r\n        cursor: not-allowed; }\r\n  .radio.radio-inline {\r\n    margin-top: 0; }\r\n\r\n.radio-primary input[type=\"radio\"] + label::after {\r\n  background-color: #428bca; }\r\n.radio-primary input[type=\"radio\"]:checked + label::before {\r\n  border-color: #428bca; }\r\n.radio-primary input[type=\"radio\"]:checked + label::after {\r\n  background-color: #428bca; }\r\n\r\n.radio-danger input[type=\"radio\"] + label::after {\r\n  background-color: #d9534f; }\r\n.radio-danger input[type=\"radio\"]:checked + label::before {\r\n  border-color: #d9534f; }\r\n.radio-danger input[type=\"radio\"]:checked + label::after {\r\n  background-color: #d9534f; }\r\n\r\n.radio-info input[type=\"radio\"] + label::after {\r\n  background-color: #5bc0de; }\r\n.radio-info input[type=\"radio\"]:checked + label::before {\r\n  border-color: #5bc0de; }\r\n.radio-info input[type=\"radio\"]:checked + label::after {\r\n  background-color: #5bc0de; }\r\n\r\n.radio-warning input[type=\"radio\"] + label::after {\r\n  background-color: #f0ad4e; }\r\n.radio-warning input[type=\"radio\"]:checked + label::before {\r\n  border-color: #f0ad4e; }\r\n.radio-warning input[type=\"radio\"]:checked + label::after {\r\n  background-color: #f0ad4e; }\r\n\r\n.radio-success input[type=\"radio\"] + label::after {\r\n  background-color: #5cb85c; }\r\n.radio-success input[type=\"radio\"]:checked + label::before {\r\n  border-color: #5cb85c; }\r\n.radio-success input[type=\"radio\"]:checked + label::after {\r\n  background-color: #5cb85c; }", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 619:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(99)();
// imports


// module
exports.push([module.i, ".ms-ctn{position:relative;padding:5px 12px;height:auto}.ms-inv{border:1px solid #c00}.ms-ctn-readonly{cursor:pointer}.ms-ctn-disabled{cursor:not-allowed;background-color:#eee}.ms-ctn-bootstrap-focus,.ms-ctn-bootstrap-focus .ms-res-ctn{border-color:rgba(82,168,236,0.8)!important;box-shadow:inset 0 1px 1px rgba(0,0,0,0.075),0 0 8px rgba(82,168,236,0.6)!important;border-bottom-left-radius:0;border-bottom-right-radius:0}.ms-ctn-focus{border-color:#66afe9;outline:0;box-shadow:inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)}.ms-ctn input{border:0;box-shadow:none;-webkit-transition:none;outline:0;display:block;padding:0;line-height:1.42857143;margin:1px 0;width:100%}.ms-ctn .ms-sel-ctn input{float:left}.ms-ctn-disabled input{cursor:not-allowed;background-color:#eee}.ms-ctn .ms-input-readonly{cursor:pointer}.ms-ctn .ms-empty-text{color:#DDD}.ms-ctn input:focus{border:0;box-shadow:none;-webkit-transition:none;background:#FFF}.ms-ctn input::-ms-clear{width:0;height:0}.ms-ctn .ms-trigger{top:0;width:25px;height:100%;position:absolute;right:0;background:transparent;border-left:1px solid #CCC;cursor:pointer}.ms-ctn .ms-trigger .ms-trigger-ico{display:inline-block;width:0;height:0;vertical-align:top;border-top:4px solid #333;border-right:4px solid transparent;border-left:4px solid transparent;content:\"\";margin-left:8px;margin-top:15px}.ms-ctn .ms-trigger:hover{background-color:#e6e6e6}.ms-ctn .ms-trigger:hover .ms-trigger-ico{background-position:0 -4px}.ms-ctn-disabled .ms-trigger{cursor:not-allowed;background-color:#eee}.ms-ctn-bootstrap-focus{border-bottom:1px solid #CCC}.ms-res-ctn{width:100%;display:block;overflow-y:auto}.ms-res-ctn .ms-res-group{line-height:23px;text-align:left;padding:2px 5px;font-weight:bold;border-bottom:1px dotted #CCC;border-top:1px solid #CCC;background:#f3edff;color:#333}.ms-res-ctn .ms-res-item{line-height:25px;text-align:left;padding:2px 5px;color:#666;cursor:pointer}.ms-res-ctn .ms-res-item-grouped{padding-left:15px}.ms-res-ctn .ms-res-odd{background:#fafafa}.ms-res-ctn .ms-res-item-active{background-color:#f5f5f5}.ms-res-ctn .ms-res-item-disabled{color:#CCC;cursor:default}.ms-sel-ctn{overflow:auto;line-height:18px;padding-right:25px}.ms-no-trigger .ms-sel-ctn{padding-right:0}.ms-sel-ctn .ms-sel-item{background:#f3f3f3;color:#999;float:left;font-size:12px;padding:3px 5px;border-radius:3px;border:1px solid #DDD;margin:3px 0 1px 0}.ms-sel-ctn .ms-sel-invalid{border-color:#f8a5a5!important;background:#fdf2f2!important}.ms-sel-ctn .ms-sel-item:hover{border:1px solid #BBB}.ms-ctn .ms-sel-item{background:#f3f3f3;color:#999;float:left;font-size:12px;padding:0 5px;border-radius:3px;border:1px solid #DDD;margin:1px 5px 1px 0}.ms-ctn .ms-sel-item:hover{border:1px solid transparent}.ms-ctn-focus .ms-sel-item:hover{border:1px solid #BBB}.ms-sel-ctn .ms-sel-text{background:#FFF;color:#666;padding-right:0;margin-left:0;font-size:14px;font-weight:normal}.ms-as-string .ms-sel-text{border-color:transparent}.ms-res-ctn .ms-res-item em{font-style:normal;background:#565656;color:#FFF}.ms-sel-ctn .ms-sel-text:hover{background:#FFF}.ms-sel-ctn .ms-sel-item-active{border:1px solid red;background:#757575}.ms-stacked .ms-sel-item{float:inherit}.ms-sel-ctn .ms-sel-item .ms-close-btn{width:7px;cursor:pointer;height:7px;float:right;margin:6px 2px 0 10px;background-image:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAcAAAAOCAYAAADjXQYbAAAACXBIWXMAAAsTAAALEwEAmpwYAAAKT2lDQ1BQaG90b3Nob3AgSUNDIHByb2ZpbGUAAHjanVNnVFPpFj333vRCS4iAlEtvUhUIIFJCi4AUkSYqIQkQSoghodkVUcERRUUEG8igiAOOjoCMFVEsDIoK2AfkIaKOg6OIisr74Xuja9a89+bN/rXXPues852zzwfACAyWSDNRNYAMqUIeEeCDx8TG4eQuQIEKJHAAEAizZCFz/SMBAPh+PDwrIsAHvgABeNMLCADATZvAMByH/w/qQplcAYCEAcB0kThLCIAUAEB6jkKmAEBGAYCdmCZTAKAEAGDLY2LjAFAtAGAnf+bTAICd+Jl7AQBblCEVAaCRACATZYhEAGg7AKzPVopFAFgwABRmS8Q5ANgtADBJV2ZIALC3AMDOEAuyAAgMADBRiIUpAAR7AGDIIyN4AISZABRG8lc88SuuEOcqAAB4mbI8uSQ5RYFbCC1xB1dXLh4ozkkXKxQ2YQJhmkAuwnmZGTKBNA/g88wAAKCRFRHgg/P9eM4Ors7ONo62Dl8t6r8G/yJiYuP+5c+rcEAAAOF0ftH+LC+zGoA7BoBt/qIl7gRoXgugdfeLZrIPQLUAoOnaV/Nw+H48PEWhkLnZ2eXk5NhKxEJbYcpXff5nwl/AV/1s+X48/Pf14L7iJIEyXYFHBPjgwsz0TKUcz5IJhGLc5o9H/LcL//wd0yLESWK5WCoU41EScY5EmozzMqUiiUKSKcUl0v9k4t8s+wM+3zUAsGo+AXuRLahdYwP2SycQWHTA4vcAAPK7b8HUKAgDgGiD4c93/+8//UegJQCAZkmScQAAXkQkLlTKsz/HCAAARKCBKrBBG/TBGCzABhzBBdzBC/xgNoRCJMTCQhBCCmSAHHJgKayCQiiGzbAdKmAv1EAdNMBRaIaTcA4uwlW4Dj1wD/phCJ7BKLyBCQRByAgTYSHaiAFiilgjjggXmYX4IcFIBBKLJCDJiBRRIkuRNUgxUopUIFVIHfI9cgI5h1xGupE7yAAygvyGvEcxlIGyUT3UDLVDuag3GoRGogvQZHQxmo8WoJvQcrQaPYw2oefQq2gP2o8+Q8cwwOgYBzPEbDAuxsNCsTgsCZNjy7EirAyrxhqwVqwDu4n1Y8+xdwQSgUXACTYEd0IgYR5BSFhMWE7YSKggHCQ0EdoJNwkDhFHCJyKTqEu0JroR+cQYYjIxh1hILCPWEo8TLxB7iEPENyQSiUMyJ7mQAkmxpFTSEtJG0m5SI+ksqZs0SBojk8naZGuyBzmULCAryIXkneTD5DPkG+Qh8lsKnWJAcaT4U+IoUspqShnlEOU05QZlmDJBVaOaUt2ooVQRNY9aQq2htlKvUYeoEzR1mjnNgxZJS6WtopXTGmgXaPdpr+h0uhHdlR5Ol9BX0svpR+iX6AP0dwwNhhWDx4hnKBmbGAcYZxl3GK+YTKYZ04sZx1QwNzHrmOeZD5lvVVgqtip8FZHKCpVKlSaVGyovVKmqpqreqgtV81XLVI+pXlN9rkZVM1PjqQnUlqtVqp1Q61MbU2epO6iHqmeob1Q/pH5Z/YkGWcNMw09DpFGgsV/jvMYgC2MZs3gsIWsNq4Z1gTXEJrHN2Xx2KruY/R27iz2qqaE5QzNKM1ezUvOUZj8H45hx+Jx0TgnnKKeX836K3hTvKeIpG6Y0TLkxZVxrqpaXllirSKtRq0frvTau7aedpr1Fu1n7gQ5Bx0onXCdHZ4/OBZ3nU9lT3acKpxZNPTr1ri6qa6UbobtEd79up+6Ynr5egJ5Mb6feeb3n+hx9L/1U/W36p/VHDFgGswwkBtsMzhg8xTVxbzwdL8fb8VFDXcNAQ6VhlWGX4YSRudE8o9VGjUYPjGnGXOMk423GbcajJgYmISZLTepN7ppSTbmmKaY7TDtMx83MzaLN1pk1mz0x1zLnm+eb15vft2BaeFostqi2uGVJsuRaplnutrxuhVo5WaVYVVpds0atna0l1rutu6cRp7lOk06rntZnw7Dxtsm2qbcZsOXYBtuutm22fWFnYhdnt8Wuw+6TvZN9un2N/T0HDYfZDqsdWh1+c7RyFDpWOt6azpzuP33F9JbpL2dYzxDP2DPjthPLKcRpnVOb00dnF2e5c4PziIuJS4LLLpc+Lpsbxt3IveRKdPVxXeF60vWdm7Obwu2o26/uNu5p7ofcn8w0nymeWTNz0MPIQ+BR5dE/C5+VMGvfrH5PQ0+BZ7XnIy9jL5FXrdewt6V3qvdh7xc+9j5yn+M+4zw33jLeWV/MN8C3yLfLT8Nvnl+F30N/I/9k/3r/0QCngCUBZwOJgUGBWwL7+Hp8Ib+OPzrbZfay2e1BjKC5QRVBj4KtguXBrSFoyOyQrSH355jOkc5pDoVQfujW0Adh5mGLw34MJ4WHhVeGP45wiFga0TGXNXfR3ENz30T6RJZE3ptnMU85ry1KNSo+qi5qPNo3ujS6P8YuZlnM1VidWElsSxw5LiquNm5svt/87fOH4p3iC+N7F5gvyF1weaHOwvSFpxapLhIsOpZATIhOOJTwQRAqqBaMJfITdyWOCnnCHcJnIi/RNtGI2ENcKh5O8kgqTXqS7JG8NXkkxTOlLOW5hCepkLxMDUzdmzqeFpp2IG0yPTq9MYOSkZBxQqohTZO2Z+pn5mZ2y6xlhbL+xW6Lty8elQfJa7OQrAVZLQq2QqboVFoo1yoHsmdlV2a/zYnKOZarnivN7cyzytuQN5zvn//tEsIS4ZK2pYZLVy0dWOa9rGo5sjxxedsK4xUFK4ZWBqw8uIq2Km3VT6vtV5eufr0mek1rgV7ByoLBtQFr6wtVCuWFfevc1+1dT1gvWd+1YfqGnRs+FYmKrhTbF5cVf9go3HjlG4dvyr+Z3JS0qavEuWTPZtJm6ebeLZ5bDpaql+aXDm4N2dq0Dd9WtO319kXbL5fNKNu7g7ZDuaO/PLi8ZafJzs07P1SkVPRU+lQ27tLdtWHX+G7R7ht7vPY07NXbW7z3/T7JvttVAVVN1WbVZftJ+7P3P66Jqun4lvttXa1ObXHtxwPSA/0HIw6217nU1R3SPVRSj9Yr60cOxx++/p3vdy0NNg1VjZzG4iNwRHnk6fcJ3/ceDTradox7rOEH0x92HWcdL2pCmvKaRptTmvtbYlu6T8w+0dbq3nr8R9sfD5w0PFl5SvNUyWna6YLTk2fyz4ydlZ19fi753GDborZ752PO32oPb++6EHTh0kX/i+c7vDvOXPK4dPKy2+UTV7hXmq86X23qdOo8/pPTT8e7nLuarrlca7nuer21e2b36RueN87d9L158Rb/1tWeOT3dvfN6b/fF9/XfFt1+cif9zsu72Xcn7q28T7xf9EDtQdlD3YfVP1v+3Njv3H9qwHeg89HcR/cGhYPP/pH1jw9DBY+Zj8uGDYbrnjg+OTniP3L96fynQ89kzyaeF/6i/suuFxYvfvjV69fO0ZjRoZfyl5O/bXyl/erA6xmv28bCxh6+yXgzMV70VvvtwXfcdx3vo98PT+R8IH8o/2j5sfVT0Kf7kxmTk/8EA5jz/GMzLdsAAAAEZ0FNQQAAsY58+1GTAAAAIGNIUk0AAHolAACAgwAA+f8AAIDpAAB1MAAA6mAAADqYAAAXb5JfxUYAAABSSURBVHjahI7BCQAwCAOTzpThHMHh3Kl9CVos9XckFwQAuPtGuWTWwMwaczKzyHsqg6+5JqMJr28BABHRwmTWQFJjTmYWOU1L4tdck9GE17dnALGAS+kAR/u2AAAAAElFTkSuQmCC);background-position:0 -7px}.ms-sel-ctn .ms-sel-item .ms-close-btn:hover{background-position:0 0}.ms-stacked .ms-sel-item .ms-close-btn{margin-left:0}.ms-helper{color:#AAA;font-size:10px;position:absolute;top:-17px;right:0}.ms-ctn.input-lg .ms-trigger .ms-trigger-ico{margin-top:17px}.ms-ctn.input-sm .ms-trigger .ms-trigger-ico{margin-top:13px}.ms-ctn.input-lg .ms-sel-ctn .ms-sel-item{padding-top:2px;padding-bottom:3px}.ms-ctn.input-sm .ms-sel-ctn{line-height:15px}.ms-ctn.input-sm .ms-sel-ctn .ms-sel-item{padding-top:1px;padding-bottom:1px;margin-top:0;margin-bottom:0}.ms-ctn.input-sm .ms-sel-ctn .ms-sel-item .ms-close-btn{margin-top:4px}.ms-ctn .ms-sel-ctn{margin-left:-7px}.ms-ctn .ms-trigger:hover{width:24px;right:1px;border-radius:0 3px 3px 0}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 624:
/***/ (function(module, exports) {

module.exports = "<router-outlet></router-outlet> "

/***/ }),

/***/ 625:
/***/ (function(module, exports) {

module.exports = "\n\n<app-header [data]=\"userdata\">header .... </app-header>\n<app-admin>Admin Page....</app-admin> \n<app-footer>footer ... </app-footer>"

/***/ }),

/***/ 626:
/***/ (function(module, exports) {

module.exports = "<section id=\"admin\" class=\"admin-section\">\r\n    <div class=\"container admin-container\">\r\n        <div class=\"row\">\r\n            <div class=\"col-sm-12 col-md-9\">\r\n                <div class=\"row\">\r\n                    <div class=\"col-md-8\">\r\n                        <h1>Admin</h1>\r\n                    </div>\r\n                </div>\r\n                <!--user emulation start-->\r\n                <div class=\"row\">\r\n\r\n                    <div class=\"col-sm-12 col-md-8\">\r\n                        <div class=\"box\">\r\n                            <h4>User Emulation</h4>\r\n                            <div class=\"row\">\r\n                                <div class=\"col-sm-12 col-md-6\">\r\n                                    <label>Select SID/TID</label>\r\n                                    <div class=\"position-code-filter\">\r\n                                        <input id=\"emulate-user-sid\" name=\"emulate-user-sid\" [(ngModel)]=\"emulateuser.sid\">\r\n                                        <br />\r\n                                        <br>\r\n                                        <button type=\"button\" class=\"btn btn-primary btn-sm\" (click)=\"emulateUser()\">Emulate User</button>\r\n                                    </div>\r\n                                </div>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n                <!--user emulation end-->\r\n\r\n                <!--Add Image to Banner start-->\r\n                <div class=\"row\">\r\n\r\n                    <div class=\"col-sm-12 col-md-8\">\r\n\r\n                        <div class=\"box\">\r\n                            <h4>Add Image to Banner</h4>\r\n                            <div class=\"row\">\r\n                                <div class=\"col-sm-12 col-md-6\">\r\n                                    <label>Select Role</label>\r\n                                    <!--<div id=\"positionCodeImage\" ></div>-->\r\n                                    <input id=\"roleId\" name=\"roleId\">\r\n\r\n                                    <label>Select a Business Center</label>\r\n                                    <!--<div id=\"businessCenterImage\"></div>-->\r\n                                    <input id=\"bc\" name=\"bc\">\r\n\r\n                                    <label>Enter an Order Number</label>\r\n                                    <input type=\"text\" name=\"orderBy\" [(ngModel)]=\"uploadImage.orderBy\">\r\n                                </div>\r\n                                <div class=\"col-sm-12 col-md-6\">\r\n                                    <label>Select an Image</label>\r\n                                    <input id=\"project\">\r\n                                    <input type=\"hidden\" id=\"project-id\" name=\"image\" [(ngModel)]=\"uploadImage.image\">\r\n                                    <img id=\"project-icon\" src=\"\" class=\"ui-state-default\" alt=\"\">\r\n                                    <p id=\"project-description\"></p>\r\n                                    <br />\r\n                                    <button type=\"button\" class=\"btn btn-primary btn-sm\" (click)=\"addBannerImage()\">Add to Banner</button>\r\n                                </div>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n\r\n                    <div class=\"col-sm-12 col-md-4\">\r\n                        <div class=\"box\">\r\n                            <h4>Add an Image</h4>\r\n                            <br />\r\n                            <span>File</span>\r\n                            <input type=\"file\" id=\"file\" name=\"file\" size=\"10\" />\r\n                            <label class=\"font-size:8px; clear:none;\">(File name) No File Chosen</label>\r\n                            <br>\r\n\r\n                            <input id=\"uploadbutton\" type=\"button\" value=\"Upload\" onclick=\"a();\" class=\"btn btn-primary btn-sm\" />\r\n\r\n\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n                <!--Add Image to Banner end-->\r\n      \r\n                <div class=\"row\">\r\n                    <div class=\"col-sm-12 col-md-12\">\r\n                        <div class=\"box\">\r\n                            <h4>Banner Configuration</h4>\r\n                            <br />\r\n                            <table id=\"example\" class=\"table display\" cellspacing=\"0\" width=\"100%\">\r\n                                <thead>\r\n                                    <tr>\r\n                                        <th>Roles</th>\r\n                                        <th>Business Center</th>\r\n                                        <th>Image Name</th>\r\n                                        <th>Order</th>\r\n                                        <!--<th>Edit</th>-->\r\n                                        <th>Delete</th>\r\n                                        \r\n                                    </tr>\r\n                                </thead>                                \r\n                                <tbody >\r\n                                    <tr *ngFor=\"let bannerData of allBannerTableData\">\r\n                                        <td>{{bannerData.roleID}}</td>\r\n                                        <td>{{bannerData.businessCenter}}</td>\r\n                                        <td>{{bannerData.image}}</td>\r\n                                        <td>{{bannerData.orderBy}}</td>                                        \r\n                                        <!--<td><button type=\"button\" class=\"btn btn-primary btn-sm\" (click)=\"editBannerData(bannerData)\"><i class=\"fa fa-edit\"></i></button></td>-->\r\n                                        <td><button type=\"button\" class=\"btn btn-primary btn-sm\" (click)=\"deleteBannerData(bannerData.dashBoardBannersID)\"><i class=\"fa fa-close\"></i></button></td>                                       \r\n                                    </tr>\r\n                                </tbody>\r\n                            </table>\r\n\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n\r\n                <!--Configuration Image to Banner end-->            \r\n            </div>\r\n           \r\n        </div>\r\n\r\n    </div>\r\n\r\n</section>\r\n<style type=\"text/css\">\r\n    .box label {\r\n        float: none;\r\n    }\r\n</style>"

/***/ }),

/***/ 627:
/***/ (function(module, exports) {

module.exports = "<ngb-carousel>\r\n    <template ngbSlide *ngFor=\"let banner of banners\">\r\n        <img src=\"services/loadrsc?id={{banner.fileName}}\" alt=\"{{banner.imageName}}\" style=\"width:100%\">\r\n    </template>\r\n</ngb-carousel>"

/***/ }),

/***/ 628:
/***/ (function(module, exports) {

module.exports = "<template #content let-c=\"close\" let-d=\"dismiss\" >\r\n  <div class=\"modal-header\">\r\n    <h4 class=\"modal-title\">Welcome, {{data.name}} </h4>\r\n    <button type=\"button\" class=\"close\" aria-label=\"Close\" (click)=\"d('Cross click')\">\r\n      <span aria-hidden=\"true\">&times;</span>\r\n    </button>\r\n  </div>\r\n  <div class=\"modal-body\">     \r\n    <p>The FCA Rewards Dashboard is here to provide you with a quick review of all information vital to your sales data</p>\r\n  </div>\r\n  <div class=\"modal-footer\">\r\n    <a class=\" modal-action modal-close waves-effect waves-light btn-flat my-btn-flat getstarted-button\" (click)=\"c('Close click'); showWelcomeModal()\">Get Started!</a>\r\n    \r\n  </div>\r\n</template>\r\n<template #statisticModel let-c=\"close\" let-d=\"dismiss\">\r\n  <div class=\"modal-header\">\r\n    <h4 class=\"modal-title\">Data Table </h4>\r\n\r\n    <button type=\"button\" class=\"close\" aria-label=\"Close\" (click)=\"d('Cross click')\">\r\n      <span aria-hidden=\"true\">&times;</span>\r\n    </button>\r\n  </div>\r\n  <div class=\"modal-body\">\r\n    <input class=\"export-to-excel\" type=\"button\" onclick=\"tableToExcel('dataTable', 'Top 10 Information')\" value=\"Export to Excel\">\r\n    <datatable id=\"dataTable\" [tableData]=\"statisticModelData\"></datatable>\r\n  </div>\r\n  <div class=\"modal-footer\">\r\n    <a class=\" modal-action modal-close waves-effect waves-light btn-flat my-btn-flat getstarted-button\" (click)=\"c('Close click')\">Ok</a>\r\n   \r\n  </div>\r\n</template>\r\n\r\n<template #topModel let-c=\"close\" let-d=\"dismiss\" ngbModalContainer>\r\n  <div class=\"modal-header\">\r\n   \r\n    <button type=\"button\" class=\"close\" aria-label=\"Close\" (click)=\"d('Cross click')\">\r\n      <span aria-hidden=\"true\">&times;</span>\r\n    </button>\r\n  </div>\r\n  <div class=\"modal-body\">\r\n    <input class=\"export-to-excel\" type=\"button\" onclick=\"tableToExcel('testTable', 'Top 10 Information')\" value=\"Export to Excel\" style=\"cursor:pointer\">\r\n        \r\n    <ngb-tabset>\r\n        \r\n      <ngb-tab *ngFor=\"let tableDataObj of tableData\" title={{tableDataObj.tabName}}>\r\n        <template ngbTabContent> \r\n                  \r\n          <h4 [innerHtml]=\"tableDataObj.tableName\"></h4>\r\n         \r\n          <table id=\"testTable\" class=\"table table-hover table-striped bdt\">\r\n            <thead>\r\n              <tr>\r\n                <th *ngFor=\"let thead of tableDataObj.tableHeader\"><span class=\"sort-element\">{{thead}}</span><span class=\"sort-icon fa\"></span></th>\r\n              </tr>\r\n            </thead>\r\n            <tbody>\r\n              <tr style=\"display: table-row;\" *ngFor=\"let tdata of tableDataObj.data\">\r\n                <td *ngFor=\"let tdData of tdata\">{{tdData}}</td>\r\n              </tr>\r\n            </tbody>\r\n          </table>\r\n\r\n        </template>\r\n      </ngb-tab>\r\n\r\n    </ngb-tabset>\r\n\r\n  </div>\r\n  <div class=\"modal-footer\">\r\n    <button type=\"button\" class=\"getstarted-button\" (click)=\"c('Close click')\">Close</button>\r\n  </div>\r\n</template>\r\n<template ngbModalContainer></template>\r\n\r\n<section id=\"intro\" class=\"intro-section\">\r\n  \r\n  <div class=\"container\">\r\n    <!--- Row START -->\r\n    <div *ngFor=\"let rowarray of tilesArray\" class=\"row\">\r\n      <div *ngFor=\"let tile of rowarray; let i = index;\" class=\"{{(rowarray.length%2==1) && (rowarray.length== i+1) ? 'col-sm-6 card':'col-sm card'}}\">\r\n        <div class=\"image-frame\">{{tile.tileName}}<img src=\"services/loadrsc?id={{tile.tileHeaderImage}}\"> </div>\r\n       \r\n        <div class=\"card-content\" *ngIf=\"tile.type == 'chart'\">\r\n         \r\n          <chart (drillup)=\"drillUp($event.originalEvent,$event.context,tile.id)\" (drilldown)=\"drillDown($event.originalEvent,$event.context,tile.id)\"\r\n            (load)=\"saveChartInstance($event.context,tile.id)\" [options]=\"contentBody[tile.id]\" class=\"highchart-size\"></chart>\r\n          <span *ngIf=\"showPieButton[tile.id]\" class=\"button-nav\">\r\n      \r\n       &nbsp; <button id=\"button \" style=\"margin-left:4px; font-size: 14px \" class=\"customer_btn\" *ngFor = \"let buttonName of pieButtons[tile.id] \"  (click)=\"chartSwitch(buttonName,tile.id)\">{{buttonName}}</button>\r\n      \r\n      </span>\r\n         \r\n        </div>\r\n\r\n        <div class=\"display-data-set \" *ngIf=\"tile.type=='tile' \">\r\n          <ul class=\"list-group \" *ngFor=\"let tdata of contentBody[tile.id].top3 \">\r\n            <h4>{{tdata.tableName}}</h4>\r\n            <li class=\"list-group-item \" *ngFor=\"let data of tdata.data \">\r\n              <span class=\"item-title \">{{data[0]}}</span>\r\n              <span class=\"badge \">{{data[1]}}</span>\r\n            </li>\r\n\r\n          </ul>\r\n          <p *ngIf='(tile.id !=14 \r\n          && tile.id !=15\r\n          && errorInArray(contentBody[tile.id].attribute)) \r\n          && (errorInObject(contentBody[tile.id].top10_advisors)) \r\n          && (errorInObject(contentBody[tile.id].top10_technicians)) \r\n          && (errorInArray(contentBody[tile.id].top3))' style=\"text-align: center;\">\r\n            No data to display. Please visit Program Rules below for more information on earning.</p>\r\n\r\n          <p *ngIf=\"((tile.id==14) \r\n          && (errorInArray(contentBody[tile.id].attribute)) \r\n          && (errorInObject(contentBody[tile.id].top10_advisors)) \r\n          && (errorInObject(contentBody[tile.id].top10_technicians)) \r\n          && (errorInArray(contentBody[tile.id].top3)) \r\n          || (tile.id==15)  \r\n          && (errorInArray(contentBody[tile.id].attribute)) \r\n          && (errorInObject(contentBody[tile.id].top10_advisors)) \r\n          && (errorInObject(contentBody[tile.id].top10_technicians)) \r\n          && (errorInArray(contentBody[tile.id].top3)))\" style=\"text-align: center;\">\r\n            Enrollment Period has ended. Please read the program rules below regarding future Enrollment.</p>\r\n\r\n          <div class=\"data-group\" *ngFor=\"let data of contentBody[tile.id].attribute \">\r\n            <p class=\"data-point\" *ngIf=\"emptyBadge(data.badgeUrl) \" [innerHtml]=\"data.value\"></p>\r\n            <p class=\"data-substitle\" *ngIf=\"emptyBadge(data.badgeUrl) \" [innerHtml]=\"data.name\"></p>\r\n            <p class=\"data-substitle\" *ngIf=\"notEmptyBadge(data.badgeUrl) \"><img src=\"services/loadrsc?id={{data.badgeUrl}} \"></p>\r\n            <p class=\"data-substitle\">{{data.badgeTitle}}</p>\r\n          </div>\r\n\r\n        </div>\r\n        \r\n        <button type=\"button \" class=\"btn btn-info btn-lg \" *ngIf=\"tile.type=='tile' && notEmpty(contentBody[tile.id].top10_advisors)\r\n              \" (click)=\"openDataTable(contentBody[tile.id].top10_advisors) \" style=\"cursor:pointer\" [innerHtml]= \"contentBody[tile.id].top10_advisors.buttonName\"></button><br>\r\n        <button type=\"button \" class=\"btn btn-info btn-lg \" *ngIf=\"tile.type=='tile' && notEmpty(contentBody[tile.id].top10_technicians) \"\r\n          (click)=\"openDataTable(contentBody[tile.id].top10_technicians) \" style=\"cursor:pointer\" [innerHtml]=\"contentBody[tile.id].top10_technicians.buttonName\"><i class=\"fa fa-star \" aria-hidden=\"true \" ></i></button>\r\n        <div style=\"margin-bottom: 45; margin-top: -80\" class=\"chart-controls \" *ngIf=\"tile.type=='chart' && chartType(tile.id) \">\r\n          <a (click)=\"chartChange( 'pie',tile.id) \" alt=\"View Pie Chart \"><i  class=\"fa fa-pie-chart \" aria-hidden=\"true \" style=\"cursor:pointer\"></i></a>\r\n          <a (click)=\"chartChange( 'column',tile.id) \" alt=\"View Column Chart \"><i  class=\"fa fa-bar-chart\r\n              \" aria-hidden=\"true \" style=\"cursor:pointer\"></i></a>\r\n        </div>\r\n        \r\n        <div class=\"card-action \">\r\n          <button *ngIf=\"isEmpty(tile.programRulesUrl)\" type=\"button \" class=\"btn btn-primary btn-sm \" style=\"cursor:pointer\" (click)=\"openProgramSite(tile.programRulesUrl)\"><i class=\"fa fa-check-square \" aria-hidden=\"true \"></i>\r\n                {{tile.programRules}} </button>\r\n          <button *ngIf=\"isEmpty(tile.programSiteUrl)\" type=\"button \" class=\"btn btn-primary btn-sm \" style=\"cursor:pointer\" (click)=\"openProgramSite(tile.programSiteUrl)\"><i class=\"fa fa-arrow-circle-right \" aria-hidden=\"true \"></i>\r\n                {{tile.programSite}} </button>\r\n          \r\n        </div>\r\n      </div>\r\n    </div>\r\n    <!--- Row END -->\r\n  </div>\r\n</section>\r\n\r\n<style>\r\n  .customer_btn {\r\n    color: black;\r\n    background-color: #e6f2ff;\r\n    border-color: #01549b;\r\n    MARGIN-BOTTOM: 4;\r\n  }\r\n</style>"

/***/ }),

/***/ 629:
/***/ (function(module, exports) {

module.exports = "<footer class=\"page-footer blue\">\r\n  <div class=\"container\">\r\n    <div class=\"row\">\r\n      <div class=\"col s12 m8\">\r\n        <h5 class=\"white-text\">My FCA Rewards</h5>       \r\n        <p class=\"white-text\">This Dashboard is designed to show a summary of your performance in programs in which you are enrolled by your Dealership.\r\n          Click on the individual tile to access the program rules, reports and all other pertinent information. If you have\r\n          questions or need assistance, please contact Program Headquarters at <span><a href=\"tel:2483530950\" style=\"text-decoration: underline; color:white\">P: 248.353.0950</a></span> or Email to\r\n          <span><a href=\"mailto:customerservice@imperialm.com\" style=\"text-decoration: underline; color:white\">customerservice@imperialm.com</a></span> or feel free to Chat! </p>\r\n\r\n\r\n      </div>\r\n      <div class=\"extra-links\">        \r\n        <div class=\"row\">\r\n          <div class=\"col-md-6 col-sm-6\">\r\n            <a class=\"white-text footer-logo\" href=\"https://www.rewardingexcellence.com/\" target=\"_blank\" alt=\"Rewarding Excellence\">\r\n            <img class=\"footer-logo-img\" src=\"services/loadrsc?id=RewardExcellence.jpg\" title=\"Rewarding Excellence\" /></a>\r\n          </div>\r\n          <div class=\"col-md-6 col-sm-6\">\r\n            <a class=\"white-text footer-logo\" href=\"https://fcarewardredemption.com/customer/account/login/\" target=\"_blank\" title=\"Reward Redemption\">\r\n            <img class=\"footer-logo-img\" src=\"services/loadrsc?id=MyRewards.jpg\" alt=\"Reward Redemption\" /></a>\r\n          </div>\r\n        </div>\r\n        <div class=\"row\">\r\n          <div class=\"col-md-6 col-sm-6\">\r\n            <a class=\"white-text footer-logo\" href=\"https://www.dealers-mopar.com/moparone/credentials/fca-dealer-connect-sso.jsp\" target=\"_blank\"\r\n              title=\"Dealers-MOPAR.com\"><img class=\"footer-logo-img\" src= \"services/loadrsc?id=DealerSite.jpg\"\r\n              alt=\"Dealers-MOPAR.com\" /></a>\r\n          </div>\r\n          <div class=\"col-md-6 col-sm-6\">\r\n            <a class=\"white-text footer-logo \" href=\"https://www.moparser.com/mser/home.do?a=true\" target=\"_blank\" title=\"MSER Payout Chart\">\r\n            <img class=\"footer-logo-img\" src=\"services/loadrsc?id=PayoutChart.jpg\" alt=\"MSER Payout Chart\" /></a>\r\n          </div>\r\n        </div>\r\n      </div>\r\n    </div>\r\n  </div>\r\n  <div class=\"footer-copyright white-text\">\r\n    <div class=\"container footerCopyright\">\r\n      &copy; 2017 FCA US LLC. All Rights Reserved. Chrysler, Dodge, Jeep, Ram, Mopar and SRT are registered trademarks of FCA US\r\n      LLC.\r\n      <br> &emsp; ALFA ROMEO and FIAT are registered trademarks of Fiat Group Marketing S.p.A., used with permission.\r\n      <span class=\"glyphicon glyphicon-envelope\"></span>\r\n    </div>\r\n  </div>\r\n</footer>\r\n\r\n<style>\r\n  .fix {\r\n    position: fixed;\r\n    bottom: 8px;\r\n    left: 88%;\r\n    z-index: 20000;\r\n  }\r\n\r\n  .footer-logo {\r\n    padding: 10px 20px;\r\n  }\r\n\r\n  .footer-logo-img {\r\n    padding: 10px\r\n  }\r\n  .right-logo-img {\r\n    padding: 0px\r\n  }\r\n</style>\r\n<img src=\"assets/images/footer/chat.png\" class=\"fix\" />"

/***/ }),

/***/ 630:
/***/ (function(module, exports) {

module.exports = "<template #contactModal let-c=\"close\" let-d=\"dismiss\">\r\n    <div class=\"modal-header\">\r\n        <h4 class=\"modal-title\"> </h4>\r\n        <button type=\"button\" class=\"close\" aria-label=\"Close\" (click)=\"d('Cross click')\">\r\n            <span aria-hidden=\"true\">&times;</span>\r\n        </button>\r\n    </div>\r\n    <div class=\"modal-body\">\r\n        <h4>Contact Us</h4>\r\n        <div class=\"contact-block\">\r\n            <a href=\"mailto:customerservice@imperialm.com\" style=\"text-decoration: underline;\">customerservice@imperialm.com</a>\r\n        </div>\r\n        <div class=\"contact-block\">\r\n            <a href=\"tel:2483530950\" style=\"text-decoration: underline;\">P: 248.353.0950</a>\r\n            <p>F: 248.353.3309</p>\r\n        </div>\r\n        <div class=\"contact-block\">\r\n            <p>21238 Bridge Street</p>\r\n            <p>Southfield, MI</p>\r\n            <p>48033</p>\r\n        </div>\r\n    </div>\r\n    <div class=\"modal-footer\">\r\n        <a class=\" modal-action modal-close waves-effect waves-light btn-flat my-btn-flat getstarted-button\" (click)=\"c('Close click')\">Ok</a>\r\n    </div>\r\n</template>\r\n<template ngbModalContainer></template>\r\n<template #positioncodeModal let-c=\"close\" let-d=\"dismiss\">\r\n    <div class=\"modal-header\">\r\n        <h4 class=\"modal-title\"> </h4>\r\n        <button type=\"button\" class=\"close\" aria-label=\"Close\" (click)=\"d('Cross click')\">\r\n            <span aria-hidden=\"true\">&times;</span>\r\n        </button>\r\n    </div>\r\n    <div class=\"modal-body\">\r\n        <position-code (onSubmit)=\"positionCodeSubmit(c)\" (onCancel)=\"c()\"></position-code>\r\n    </div>\r\n    <div class=\"modal-footer\">\r\n    </div>\r\n</template>\r\n\r\n<nav class=\"navbar navbar-toggleable-md navbar-inverse fixed-top \" role=\"navigation\">\r\n    <div class=\"container\">\r\n        <a class=\"brand-logo\"><img src=\"https://test.myfcarewards.com/myfcarewards/services/loadrsc?id=FCALogo_and_MyRewards.jpg\" /></a>\r\n        <div class=\"navbar-header\">\r\n            <ul class=\"sav\">\r\n                <li class=\"sav-item \">\r\n                    Welcome, <a id=\"enablePointer\" class=\"utility-nav active\" (click)=\"dashboardPage()\">{{data.name}}</a>\r\n                </li>\r\n            </ul>\r\n            <ul class=\"sav-utility\" style=\"margin-top: 5px\">\r\n                <li class=\"sav-item \">\r\n                    <a *ngIf=\"booleanAdminToken\" class=\" utility-nav\" (click)=\"endEmulation()\" style=\"cursor:pointer; color:red \">End Emulation</a>\r\n                </li>\r\n                <li class=\"sav-item \" [hidden]=\"retweetIconHide\">\r\n                    <a *ngIf=\"poscodes.length>1 || delcodes.length>1\" class=\"utility-nav active \" (click)=\"dropdownPositionCode()\" style=\"cursor:pointer\">\r\n                        <span><img src = \"services/loadrsc?id=retweet.png\"  alt=\"Retweet\"  width=\"25\" height=\"25\"></span></a>\r\n                </li>\r\n                <li class=\"sav-item\">\r\n                   \r\n                    <a *ngIf=\"booleanAdmin\" class=\" utility-nav active\" routerLink=\"/admin\" style=\"cursor:pointer\">Admin </a>\r\n                </li>\r\n                <li class=\"sav-item\">\r\n                    <a class=\" utility-nav\" (click)=\"contactUs()\" style=\"cursor:pointer\">Contact Us</a>\r\n                </li>\r\n                <li class=\"sav-item\">\r\n                    <a class=\" utility-nav\" (click)=\"profile()\" style=\"cursor:pointer\">Profile</a>\r\n                </li>\r\n                <li class=\"sav-item\">\r\n                    <a class=\" utility-nav disabled\" (click)=\"logout()\" style=\"cursor:pointer\">Logout</a>\r\n                </li>\r\n            </ul>\r\n        </div>\r\n    </div>\r\n    \r\n</nav>"

/***/ }),

/***/ 631:
/***/ (function(module, exports) {

module.exports = "<nav class=\"navbar navbar-toggleable-md navbar-inverse fixed-top \" role=\"navigation\">\r\n    <div class=\"container\">\r\n        <a class=\"brand-logo\" href=\"#\"><img src=\"services/loadrsc?id=FCALogo_and_MyRewards.jpg\"></a>\r\n        <div class=\"navbar-header\">\r\n        </div>\r\n    </div>\r\n</nav>\r\n<section class=\"intro-section\" id=\"intro\" [hidden]=\"hideLoginPage\">\r\n    <div class=\"container\">\r\n        <div class=\"row login\">\r\n            <div class=\"col-sm card login-frame\">\r\n                <div class=\"heading-line\">\r\n                    <h2>Welcome!</h2><br/>\r\n                </div>\r\n                <form class=\"pageForm\" #f=\"ngForm\" novalidate (ngSubmit)=\"login()\">\r\n                    <p style=\"color: red; margin-bottom: auto; margin-left: 205;\">{{loginFailed}} <br> {{loginErrorMessage}}</p>\r\n                    <label><b>SID/TID*</b></label>\r\n                    <input id=\"username\" pattern=\"[A-Z]{3}[0-9]{4}\" type=\"text\" name=\"username\" [(ngModel)]=\"user.username\" #username=\"ngModel\"\r\n                        placeholder=\"\">\r\n                    <br>\r\n                    <label><b>Password*</b></label>\r\n                    <input id=\"password\" pattern=\"[A-Z]{3}[0-9]{4}\" type=\"password\" name=\"password\" [(ngModel)]=\"user.password\" #password=\"ngModel\"\r\n                        placeholder=\"\"><br/>\r\n                    <button type=\"submit\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#updateProgramsModal\" style=\" margin-bottom: auto; margin-left: 205; margin-top: 5;\">Enter</button>\r\n                </form>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</section>\r\n<div style=\"display: flex; justify-content: center;\" [hidden]=\"!hideLoginPage\">\r\n    <img class=\"refreshGlyphImg\" src=\"assets/images/spinner/spinner.gif\" />\r\n</div>"

/***/ }),

/***/ 632:
/***/ (function(module, exports) {

module.exports = "<div class=\"container\">\r\n    <form #f=\"ngForm\" novalidate (ngSubmit)=\"submitSelectedCodes()\">\r\n        <div>\r\n            <label></label>\r\n            <div *ngIf=\"pcode.length>1\">\r\n                <p>Select your Position Code:\r\n                    <select [(ngModel)]=\"code.selectedPositionCode\" (change)=\"selectPositionCode(code.selectedPositionCode)\" [ngModelOptions]=\"{standalone: true}\">\r\n                <option *ngFor=\"let pcode of pcode\" [ngValue]=\"pcode\" >{{pcode}} </option>\r\n            </select>\r\n                </p>\r\n            </div>\r\n        </div>\r\n        <div>\r\n            <label></label>\r\n            <div *ngIf=\"dcode.length>1\">\r\n                <p>Select your Dealer Code:\r\n                    <select [(ngModel)]=\"code.selectedDealerCode\" (change)=\"selectDealerCode(code.selectedDealerCode)\" [ngModelOptions]=\"{standalone: true}\">\r\n                <option *ngFor=\"let dcode of dcode\" [ngValue]=\"dcode\" >{{dcode}}</option>\r\n            </select>\r\n                </p>\r\n            </div>\r\n        </div>\r\n        <button type=\"button\" (click)=\"submitClick()\" class=\"btn btn-primary btn-md\">Submit</button>\r\n        <button type=\"button\" (click)=\"cancelClick()\" class=\"btn btn-primary btn-md\">Cancel</button>\r\n    </form>\r\n</div>"

/***/ }),

/***/ 633:
/***/ (function(module, exports) {

module.exports = "\r\n<app-header retweetIconHide=true >header .... </app-header>\r\n<app-profile >profile ....</app-profile>\r\n<app-footer>footer ... </app-footer>   "

/***/ }),

/***/ 634:
/***/ (function(module, exports) {

module.exports = "<section class=\"intro-section admin-margin \" id=\"intro\">\r\n    <div class=\"page-title\">\r\n        <div class=\"pageTitle\">\r\n            <div class=\"container\">\r\n                <div class=\"row\">\r\n\r\n                    <div class=\"col-sm-12 col-md-12\">\r\n                        <h3 class=\"heading-xl profile\" style=\"color:white\">\r\n                            Update User Profile Page\r\n                        </h3>\r\n                    </div>\r\n\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n    <div class=\"col-sm-12 col-md-12 col-lg-12\">\r\n        <article class=\"card animated fadeInUp\">\r\n            <div class=\"card-block\">\r\n                <div class=\"content-row row\">\r\n                    <div class=\"col-sm-12 col-md-12 col-lg-12\">\r\n                        <button class=\"getstarted-button btn-sm\" style=\"margin: 10px; background-color: #536ea4!important;\" (click)=\"goBack()\"><i class=\"fa fa-arrow-circle-left\" ></i> Return to Dashboard</button>\r\n                        <form class=\"pageForm\" #changeInformation=\"ngForm\">\r\n                            <h6>Change your Information</h6>\r\n                            <p style=\"color:red; margin-left: 208\" id=\"pwsuccess\">{{successProfileChangeMessage}}</p>\r\n                            <p style=\"color:red; margin-left: 208\" id=\"pwnotmatch\">{{errorProfileChange}}</p>\r\n                            <label>Name:</label>\r\n                            <input id=\"username\" size=\"30\" name=\"username\" type=\"text\" [(ngModel)]=\"profiledata.name\"><br>\r\n                            <label>Email:</label>\r\n                            <input id=\"email\" size=\"30\" name=\"email\" type=\"text\" [(ngModel)]=\"profiledata.email\">\r\n                            <div class=\"form-actions\">\r\n                                <button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#updateProgramsModal\" (click)=\"changeProfileData()\"\r\n                                    style=\"cursor:pointer\">Update</button>\r\n                            </div>\r\n                        </form>\r\n                        <form class=\"pageForm\" id=\"changePassword\">\r\n                            <h6>Change your Password</h6>\r\n                            <p style=\"color:red; margin-left: 208\" id=\"pwsuccess\">{{successPasswordChangeMessage}}</p>\r\n                            <p style=\"color:red; margin-left: 208\" id=\"pwnotmatch\">{{passwordNotMatched}}</p>\r\n                            <p style=\"color:red; margin-left: 208\" id=\"pwnotmatch\">{{errorPassWordChange}}</p>\r\n                            <label>New Password:</label>\r\n                            <input id=\"passwordInput\" size=\"30\" name=\"password1\" type=\"password\" [(ngModel)]=\"profiledata.password1\">\r\n                            <br>\r\n                            <label>Confirm Password:</label>\r\n                            <input id=\"passwordInput\" size=\"30\" name=\"password2\" type=\"password\" [(ngModel)]=\"profiledata.password2\">\r\n                            <br/>\r\n                            <div class=\"form-actions\">\r\n                                <button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#updateProgramsModal\" (click)=\"changeUserPassword()\"\r\n                                    style=\"cursor:pointer\">Change</button>\r\n                            </div>\r\n                        </form>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </article>\r\n    </div>\r\n</section>"

/***/ }),

/***/ 635:
/***/ (function(module, exports) {

module.exports = "<title>MyFcaDashboard  </title>\n<app-header [data]=\"userdata\" (onProfileChange)=\"onProfileChange()\" enablePointer=false >header .... </app-header>\n<app-banner></app-banner>\n<app-content #bodyContent >content ....</app-content>\n<app-footer>footer ... </app-footer> "

/***/ }),

/***/ 675:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(424);


/***/ }),

/***/ 69:
/***/ (function(module, exports, __webpack_require__) {

"use strict";

__webpack_require__(641);
__webpack_require__(642);
__webpack_require__(650);
__webpack_require__(643);
__webpack_require__(644);
__webpack_require__(645);
__webpack_require__(407);
__webpack_require__(646);
__webpack_require__(648);
__webpack_require__(649);
//# sourceMappingURL=rxjs-operators.js.map

/***/ })

},[675]);
//# sourceMappingURL=main.bundle.js.map