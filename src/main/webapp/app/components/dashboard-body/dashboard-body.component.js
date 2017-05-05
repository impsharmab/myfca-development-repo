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
var dashboard_body_service_1 = require("../../services/dashboard-body-services/dashboard-body.service");
var ng_bootstrap_1 = require("@ng-bootstrap/ng-bootstrap");
var cookies_service_1 = require("angular2-cookie/services/cookies.service");
var Highcharts = require('highcharts');
// const Highcharts3d = require('highcharts/highcharts-3d.src');
// Highcharts3d(Highcharts)
require('highcharts/modules/exporting')(Highcharts);
require('highcharts/modules/drilldown')(Highcharts);
require('highcharts/modules/no-data-to-display')(Highcharts);
// require('../../resources/js/data.js')(Highcharts);
var DashboardBodyComponent = (function () {
    function DashboardBodyComponent(service, modalService, cookieService) {
        this.service = service;
        this.modalService = modalService;
        this.cookieService = cookieService;
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
            // console.log("ret.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ", ")" + ret.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","))
            return ret.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        });
        //number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        this.initializeContent();
    }
    DashboardBodyComponent.prototype.ngOnInit = function () {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"));
        //this.data = JSON.parse(this.cookieService.get("CurrentUser"))
        this.modalService.open(this.model, { size: "lg" });
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
        //  return Math.floor(x);
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
            chart.setTitle(null, { text: "Total " + obj.unit + Math.floor(this.totalCount).toLocaleString() + "<br>" + e.point.name });
        }
        else if (obj.unit == "$" && obj.avarage == true) {
            chart.setTitle(null, { text: "Average " + obj.unit + Math.floor(this.totalCount).toLocaleString() + "<br>" + e.point.name });
        }
        else if (obj.unit == "%" && obj.avarage == false) {
            chart.setTitle(null, { text: "Total " + this.numberWithPercentage(this.totalCount).toLocaleString() + obj.unit + "<br>" + e.point.name });
        }
        else if (obj.unit == "%" && obj.avarage == true) {
            chart.setTitle(null, { text: "Average " + this.numberWithPercentage(this.totalCount).toLocaleString() + obj.unit + "<br>" + e.point.name });
        }
        else {
            chart.setTitle(null, { text: "Total " + obj.unit + Math.floor(this.totalCount).toLocaleString() + "<br>" + e.point.name });
        }
        // chart.setTitle(null, { text: "Total " + obj.unit + Math.floor(this.totalCount).toLocaleString() + "<br>" + e.point.name });
        this.drillUptotalCount = 0;
        this.drilldownAverageCount = 0;
    };
    DashboardBodyComponent.prototype.drillUp = function (e, chart, id) {
        //chart.setTitle(null,{ text:  e.point.name });
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
            chart.setTitle(null, { text: "Total " + obj.unit + Math.floor(this.drillUptotalCount).toLocaleString() });
        }
        else if (obj.unit == "$" && obj.avarage == true) {
            chart.setTitle(null, { text: "Average " + obj.unit + Math.floor(this.drillUptotalCount).toLocaleString() });
        }
        else if (obj.unit == "%" && obj.avarage == false) {
            chart.setTitle(null, { text: "Total " + this.numberWithPercentage(this.drillUptotalCount).toLocaleString() + obj.unit });
        }
        else if (obj.unit == "%" && obj.avarage == true) {
            chart.setTitle(null, { text: "Average " + this.numberWithPercentage(this.drillUptotalCount).toLocaleString() + obj.unit });
        }
        else {
            chart.setTitle(null, { text: "Total " + obj.unit + Math.floor(this.drillUptotalCount).toLocaleString() });
        }
        // chart.setTitle(null, { text: "Total " + obj.unit + Math.floor(this.drillUptotalCount).toLocaleString() });
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
    // options: any;
    // getJSONObject(jsonString: string) {
    //   //  debugger;
    //   // console.log(jsonString)
    //   return JSON.parse(jsonString);
    // }
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
        //console.log("this.chartObjects.customer_first :" + this.button)
    };
    DashboardBodyComponent.prototype.getChartJSONObject = function (obj, chartData) {
        if (chartData.xaxisTitle == "") {
            chartData.xaxisTitle = chartData.yaxisTitle;
        }
        else if (chartData.yaxisTitle == "") {
            chartData.yaxisTitle = chartData.xaxisTitle;
        }
        // var unit;// = this.unit;
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
        // this.bc = this.bc[obj.id] = { data: chartData.data };
        // alert("unit " + unit);
        // console.log("this.chartObjects.customer_first :" + chartData.data)
        var chartObj = {
            chart: {
                //marginRight: 100,
                type: '',
                zoomType: 'x',
                // panning: true,
                // panKey: 'shift',
                resetZoomButton: {
                    position: {
                        align: 'right',
                        verticalAlign: 'top',
                        x: -10,
                        y: 10
                    },
                    relativeTo: 'chart'
                }
            },
            // colors: ['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9',
            //   '#f15c80', '#e4d354', '#2b908f', '#f45b5b', '#91e8e1'],
            // "colors": [   "#3498db", "#1abc9c", "#f39c12", "#d35400", "#f1c40f", "#2ecc71","#34495e","#e74c3c","#9b59b6"],
            //   "colors": ["#0d80c7", "#0c669e", "#12689b", "#36a0ba", "#6fdcc9", "#3498db", "#1abc9c", "#f39c12", "#d35400"],
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
            //   legend: {
            //     enabled: false
            // },
            xAxis: {
                min: 0,
                //minRange: 1,
                type: 'category',
                //max: 1000000,
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
                    //format: stackLabels,
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
            }
        };
        var __this = this;
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
                chartObj.chart.type = "pie";
                chartObj.plotOptions = {
                    pie: {
                        size: 200,
                        allowPointSelect: false,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            padding: 0,
                            allowOverlap: true,
                            overFlow: 'justify',
                            crop: true,
                            distance: 5,
                            // format: '<b>{point.name}</b>: <br>{point.y}<br>({point.percentage:.1f}) %',
                            //format: '<b>{point.name}</b>: <br>{point.y:.0f}',
                            format: '<b>{point.name}</b>: <br>{point.y:.0f}',
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
                            cursor: 'pointer',
                            events: {
                                click: function () {
                                    if (this.x != undefined && this.name.length > 3 && this.name.length < 7 && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                                        var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                        window.open("https://test.myfcarewards.com/myfcarewards/datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token);
                                    }
                                    // modal trigger
                                    // __this.service.getTableJson("").subscribe(
                                    //   (resUserData) => {
                                    //     __this.statisticModelData = resUserData;
                                    //     __this.modalService.open(__this.statisticModel, { windowClass: 'datatable' });
                                    //   })
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
                            pieButtons.indexOf(obj.name) > -1 ? "" : pieButtons.push(obj.name);
                            drillDownObj.data.push([
                                obj.name,
                                (obj.value)
                            ]);
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
                // chartObj.xAxis.categories = categories;
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
                            y: 0,
                            x: 0
                        }
                    }
                };
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
                    else if (chartData.unit == "%" && chartData.avarage == false) {
                        chartObj.subtitle.text = "Total " + this.numberWithPercentage(total).toLocaleString() + chartData.unit;
                    }
                    else if (chartData.unit == "%" && chartData.avarage == true) {
                        chartObj.subtitle.text = "Average " + this.numberWithPercentage(total).toLocaleString() + chartData.unit;
                    }
                    else {
                        chartObj.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString();
                    } // chartObj.yAxis.title.text = chartData.yaxisTitle;
                }
                break;
            case "column":
                chartObj.chart.type = "column",
                    //chartObj["legend"] = { enabled: false },
                    chartObj.plotOptions["column"] =
                        {
                            plotBorderWidth: 0,
                            allowPointSelect: true,
                            cursor: 'pointer',
                            size: '90%',
                            dataLabels: {
                                enabled: true,
                                padding: 0,
                                //              format: '<b>{point.name}</b>: <br>{point.y}<br>({point.percentage:.1f}) %',
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
                    allowPointSelect: true,
                    cursor: 'pointer',
                    size: '80%',
                    dataLabels: {
                        allowOverlap: true,
                        enabled: true,
                        padding: 0,
                        overFlow: 'justify',
                        crop: false,
                        // format: '<b>{point.name}</b>: <br>{point.y}<br>({point.percentage:.1f}) %',
                        //format: '<b>{point.name}</b>: <br>{point.y:.0f}',
                        format: '<b>{point.name}</b> <br>' + dataLabels,
                        tooltip: tooltip,
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
                            fontSize: '9px',
                            fontWeight: 'bold'
                        }
                    }
                };
                chartObj.plotOptions["series"] = {
                    point: {
                        cursor: 'pointer',
                        events: {
                            click: function (e, a, b) {
                                if (this.name.length > 3 && this.name.length < 7 && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                                    //alert(this.name)
                                    //alert(tileId)
                                    var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                    window.open("https://test.myfcarewards.com/myfcarewards/datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token);
                                }
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
                        var drillDownObj = {};
                        var __this = this;
                        drillDownObj.point = {
                            cursor: 'pointer',
                            events: {
                                click: function () {
                                    if (this.x != undefined && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                                        var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                        window.open("https://test.myfcarewards.com/myfcarewards/datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token);
                                    } // modal trigger
                                    // __this.service.getTableJson("").subscribe(
                                    //   (resUserData) => {
                                    //     __this.statisticModelData = resUserData;
                                    //     __this.modalService.open(__this.statisticModel, { windowClass: 'datatable' });
                                    //   })
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
                                (obj.value)
                            ]);
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
                // chartObj.xAxis.categories = categories;
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
                            y: 0,
                            x: 0
                        }
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
                // chartObj.yAxis.title.text = chartData.yaxisTitle;
                break;
            case "bar_compound":
                chartObj.chart["marginRight"] = 60;
                //marginRight: 100,
                chartObj.chart.type = "bar";
                chartObj.plotOptions["series"] = {
                    point: {
                        cursor: 'pointer',
                        events: {
                            click: function (e, a, b) {
                                if (this.name.length > 3 && this.name.length < 7 && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                                    //alert(this.name)
                                    //alert(tileId)
                                    var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                    window.open("https://test.myfcarewards.com/myfcarewards/datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token);
                                }
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
                        // format: dataLabels,
                        //overFlow: 'justify',
                        crop: true
                    }
                };
                chartObj.plotOptions["series"]["stacking"] = "normal";
                delete chartObj.xAxis.categories;
                //delete chartObj.yAxis;
                this.constructChartObject(chartData, chartObj, tileId);
                break;
            case "column_compound":
                chartObj.chart.type = "column";
                chartObj.plotOptions["series"] = {
                    point: {
                        cursor: 'pointer',
                        events: {
                            click: function (e, a, b) {
                                if (this.name.length > 3 && this.name.length < 7 && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                                    //alert(this.name)
                                    //alert(tileId)
                                    var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                    window.open("https://test.myfcarewards.com/myfcarewards/datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token);
                                }
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
                //delete chartObj.yAxis;
                this.constructChartObject(chartData, chartObj, tileId);
                break;
            case "column_stack":
                chartObj.chart.type = "column";
                chartObj.plotOptions["column"] = {
                    point: {
                        cursor: 'pointer',
                        events: {
                            click: function (e, a, b) {
                                if (this.name.length > 3 && this.name.length < 7 && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                                    //alert(this.name)
                                    //alert(tileId)
                                    var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                    window.open("https://test.myfcarewards.com/myfcarewards/datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token);
                                }
                            }
                        }
                    },
                    events: {
                        legendItemClick: function (e) {
                            __this.lengendItemClick(e, this, tileId, chartData.retention);
                            //to toggle the retention
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
                        //enabled: true,
                        format: dataLabels
                    }
                };
                delete chartObj.xAxis.categories;
                // delete chartObj.yAxis;
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
        // console.log(JSON.stringify(chartObject));
        // console.log(this.chartObjects[id].container.id);
        chartObject.chart.type = chartType;
        var __this = this;
        chartObject.chart.events = { drilldown: function (e, d) { __this.drillDown(e, this, id); }, drillup: function (e, d) { __this.drillUp(e, this, id); } };
        chartObject.chart.renderTo = this.chartObjects[id].container.id;
        chartObject.chart.plotOptions = {
            column: {
                pointPadding: 0.2,
                borderWidth: 0,
                dataLabels: {
                    allowOverlap: true,
                    padding: 0,
                    enabled: true,
                    crop: false,
                    overFlow: 'justify',
                    style: {
                        fontSize: '9px',
                        fontWeight: 'bold'
                    }
                }
            },
            pie: {
                plotBorderWidth: 0,
                allowPointSelect: true,
                cursor: 'pointer',
                size: '100%',
                dataLabels: {
                    enabled: true,
                    padding: 0,
                    allowOverlap: true,
                    //format: '<b>{point.name}</b>: <br>{point.y}<br>({point.percentage:.1f}) %',
                    //format: '<b>{point.name}</b>: <br>{point.y}',
                    crop: false,
                    overFlow: 'justify',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
                        fontSize: '9px',
                        fontWeight: 'bold'
                    }
                }
            }
        };
        if (chartType === "pie") {
            var pointFormat = "";
            // alert(chartObject.unit)
            // if (chartObject.unit == "%") {
            //   pointFormat = '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b>' + "%";
            // } else if(chartObject.unit == "$"){
            //   pointFormat = '<span style="color:{series.color}">{series.name}</span>:<b>'+'$'+'{point.y}</b>';
            // }
            // else{
            //   pointFormat = '<span style="color:{series.color}">{series.name}</span>:'+'hello'+'<b>{point.y}</b>';
            // }
            chartObject.tooltip = {
                //pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
                pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.0f}</b>',
                shared: false
            };
        }
        else {
            delete chartObject.tooltip;
        }
        this.chartObjects[id] = new Highcharts.Chart(chartObject);
        // this.contentBody[id]=chartObject;
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
            // categories.push(dataObj.name);
            for (var k = 0; k < dataObj.data.length; k++) {
                var innerDataObj = dataObj.data[k];
                avagerCount = avagerCount + 1;
                total = total + innerDataObj.value;
                var drilldownData = innerDataObj.data;
                if (drilldownData.length > 0) {
                    var drillDownObj = {};
                    var __this = this;
                    drillDownObj.point = {
                        cursor: 'pointer',
                        events: {
                            click: function () {
                                if (this.x != undefined && (tileId == 9 || tileId == 10 || tileId == 11 || tileId == 12 || tileId == 13 || tileId == 19 || tileId == 23 || tileId == 31 || tileId == 32 || tileId == 33 || tileId == 36)) {
                                    var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                    window.open("https://test.myfcarewards.com/myfcarewards/datatable?chartId=" + tileId + "&territory=" + this.name + "&token=" + token);
                                } // modal trigger
                                // __this.modalService.open(__this.tableContent, { size: "lg" });
                                // __this.service.getTableJson("").subscribe(
                                //   (resUserData) => {
                                //     __this.statisticModelData = resUserData;
                                //     __this.modalService.open(__this.statisticModel, { windowClass: 'datatable' });
                                //   })
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
                    y: 0,
                    x: 0
                }
            }
        };
        //  chartObj.xAxis.categories = categories;
        // for (var key in seriesJson) {
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
        // console.log(JSON.stringify(chartObject));
        // console.log(this.chartObjects[id].container.id);
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
                allowPointSelect: true,
                cursor: 'pointer',
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
                var drillDownObj = {};
                var __this = this;
                drillDownObj.point = {
                    cursor: 'pointer',
                    events: {
                        click: function () {
                            if (this.x != undefined && this.name > 3 && (id == 9 || id == 10 || id == 11 || id == 12 || id == 13 || id == 19 || id == 23 || id == 31 || id == 32 || id == 33 || id == 36)) {
                                var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                window.open("https://test.myfcarewards.com/myfcarewards/datatable?chartId=" + id + "&territory=" + this.name + "&token=" + token);
                            }
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
                        (obj.value)
                    ]);
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
        // chartObj.xAxis.categories = categories;
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
                    y: 0,
                    x: 0
                }
            }
        };
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
        // {series.name}
        this.chartObjects[id] = new Highcharts.Chart(chartObj);
    };
    DashboardBodyComponent.prototype.chartSwitch = function (buttonName, id) {
        if (buttonName === "NAT") {
            this.chartSwitchNAT(id);
            return;
        }
        var chartObject = this.contentBody[id];
        // console.log(JSON.stringify(chartObject));
        // console.log(this.chartObjects[id].container.id);
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
                allowPointSelect: true,
                cursor: 'pointer',
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
            // categories.push(dataObj.name);
            avagerCount = avagerCount + 1;
            // total = total + dataObj.value;
            var drilldownData = dataObj.data;
            if (drilldownData.length > 0) {
                var drillDownObj = {};
                var __this = this;
                drillDownObj.point = {
                    cursor: 'pointer',
                    events: {
                        click: function () {
                            if (this.x != undefined && (id == 9 || id == 10 || id == 11 || id == 12 || id == 13 || id == 19 || id == 23 || id == 31 || id == 32 || id == 33 || id == 36)) {
                                var token = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
                                window.open("https://test.myfcarewards.com/myfcarewards/datatable?chartId=" + id + "&territory=" + this.name + "&token=" + token);
                            }
                            // modal trigger
                            // __this.service.getTableJson("").subscribe(
                            //   (resUserData) => {
                            //     __this.statisticModelData = resUserData;
                            //     __this.modalService.open(__this.statisticModel, { windowClass: 'datatable' });
                            //   })
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
                                (inObj.value)
                            ]);
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
        // chartObj.xAxis.categories = categories;
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
                    y: 0,
                    x: 0
                }
            }
        };
        if (chartData.avarage) {
            total = total / avagerCount;
        }
        if (chartData.unit == "$" && chartData.avarage == false) {
            chartObject.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString();
        }
        else if (chartData.unit == "$" && chartData.avarage == true) {
            chartObject.subtitle.text = "Average " + chartData.unit + Math.floor(total).toLocaleString();
        }
        else if (chartData.unit == "%" && chartData.avarage == false) {
            chartObject.subtitle.text = "Total " + this.numberWithPercentage(total).toLocaleString() + chartData.unit;
        }
        else if (chartData.unit == "%" && chartData.avarage == true) {
            chartObject.subtitle.text = "Average " + this.numberWithPercentage(total).toLocaleString() + chartData.unit;
        }
        else {
            chartObject.subtitle.text = "Total " + chartData.unit + Math.floor(total).toLocaleString();
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
                    //if (!clickedSeries.visible) {
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
    return DashboardBodyComponent;
}());
__decorate([
    core_1.Input(),
    __metadata("design:type", Object)
], DashboardBodyComponent.prototype, "data", void 0);
__decorate([
    core_1.ViewChild("content"),
    __metadata("design:type", core_1.TemplateRef)
], DashboardBodyComponent.prototype, "model", void 0);
__decorate([
    core_1.ViewChild("topModel"),
    __metadata("design:type", core_1.TemplateRef)
], DashboardBodyComponent.prototype, "topModel", void 0);
__decorate([
    core_1.ViewChild("statisticModel"),
    __metadata("design:type", core_1.TemplateRef)
], DashboardBodyComponent.prototype, "statisticModel", void 0);
DashboardBodyComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: "app-content",
        templateUrl: "./dashboard-body.html",
        styles: ['button:focus { backgfloor:#025fb1; color: #fff; }']
    }),
    __metadata("design:paramtypes", [dashboard_body_service_1.DashboardBodyService, ng_bootstrap_1.NgbModal, cookies_service_1.CookieService])
], DashboardBodyComponent);
exports.DashboardBodyComponent = DashboardBodyComponent;
//# sourceMappingURL=dashboard-body.component.js.map