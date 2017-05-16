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
//import * as _ from 'lodash';
var FooterComponent = (function () {
    function FooterComponent() {
        this.rawJson = [
            {
                "dealerCode": "59655 unnecessary key",
                "dealerName": "STERLING HEIGHTS DODGE CHRYSLER JEEP, INC.",
                "name": "James Michel",
                "certType": "JEEP    ",
                "cert": "1 unnecessary key",
                "points": 400,
                "totalPoints": "400 unnecessary key",
                "sid": "S01972B"
            },
            {
                "dealerCode": "59655",
                "dealerName": "STERLING HEIGHTS DODGE CHRYSLER JEEP, INC.",
                "name": "Charles Prohaska",
                "certType": "JEEP    ",
                "cert": 1,
                "points": 400,
                "totalPoints": 400,
                "sid": "S27423I"
            },
            {
                "dealerCode": "59655",
                "dealerName": "STERLING HEIGHTS DODGE CHRYSLER JEEP, INC.",
                "name": "James Michel",
                "certType": "RAM     ",
                "cert": 1,
                "points": 400,
                "totalPoints": 400,
                "sid": "S01972B"
            },
            {
                "dealerCode": "59655",
                "dealerName": "STERLING HEIGHTS DODGE CHRYSLER JEEP, INC.",
                "name": "Charles Prohaska",
                "certType": "RAM     ",
                "cert": 1,
                "points": 400,
                "totalPoints": 400,
                "sid": "S27423I"
            },
            {
                "dealerCode": "59655",
                "dealerName": "STERLING HEIGHTS DODGE CHRYSLER JEEP, INC.",
                "name": "James Michel",
                "certType": "JEEP    ",
                "cert": 1,
                "points": 400,
                "totalPoints": 400,
                "sid": "S01972B"
            },
            {
                "dealerCode": "59655",
                "dealerName": "STERLING HEIGHTS DODGE CHRYSLER JEEP, INC.",
                "name": "Charles Prohaska",
                "certType": "JEEP    ",
                "cert": 1,
                "points": 400,
                "totalPoints": 400,
                "sid": "S27423I"
            },
            {
                "dealerCode": "59655",
                "dealerName": "STERLING HEIGHTS DODGE CHRYSLER JEEP, INC.",
                "name": "James Michel",
                "certType": "RAM     ",
                "cert": 1,
                "points": 400,
                "totalPoints": 400,
                "sid": "S01972B"
            },
            {
                "dealerCode": "59655",
                "dealerName": "STERLING HEIGHTS DODGE CHRYSLER JEEP, INC.",
                "name": "Charles Prohaska",
                "certType": "RAM     ",
                "cert": 1,
                "points": 400,
                "totalPoints": 400,
                "sid": "S27423I"
            },
            {
                "dealerCode": "51255",
                "dealerName": "CANTON MI",
                "name": "Toni",
                "certType": "JEEP    ",
                "cert": 1,
                "points": 200,
                "totalPoints": 400,
                "sid": "S01972B"
            },
            {
                "dealerCode": "51255",
                "dealerName": "CANTON MI",
                "name": "Romi",
                "certType": "JEEP    ",
                "cert": 1,
                "points": 200,
                "totalPoints": 400,
                "sid": "S27423I"
            },
            {
                "dealerCode": "51255",
                "dealerName": "CANTON MI",
                "name": "Toni",
                "certType": "RAM     ",
                "cert": 1,
                "points": 200,
                "totalPoints": 400,
                "sid": "S01972B"
            },
            {
                "dealerCode": "51255",
                "dealerName": "CANTON MI",
                "name": "Romi",
                "certType": "RAM     ",
                "cert": 1,
                "points": 200,
                "totalPoints": 400,
                "sid": "S27423I"
            },
            {
                "dealerCode": "51255",
                "dealerName": "CANTON MI",
                "name": "Toni",
                "certType": "JEEP    ",
                "cert": 1,
                "points": 200,
                "totalPoints": 400,
                "sid": "S01972B"
            },
            {
                "dealerCode": "51255",
                "dealerName": "CANTON MI",
                "name": "Romi",
                "certType": "JEEP    ",
                "cert": 1,
                "points": 200,
                "totalPoints": 400,
                "sid": "S27423I"
            },
            {
                "dealerCode": "51255",
                "dealerName": "CANTON MI",
                "name": "Toni",
                "certType": "RAM     ",
                "cert": 1,
                "points": 200,
                "totalPoints": 400,
                "sid": "S01972B"
            },
            {
                "dealerCode": "51255",
                "dealerName": "Last CANTON MI",
                "name": "Romi",
                "certType": "RAM     ",
                "cert": 1,
                "points": 200,
                "totalPoints": 400,
                "sid": "S27423I"
            }
        ];
        this.cleanJson = {
            "headers": [],
            "data": [
                {
                    "data": [],
                    "innerData": {
                        "headers": [],
                        "data": []
                    }
                },
                {
                    "data": [],
                    "innerData": {
                        "headers": [],
                        "data": []
                    }
                }
            ]
        };
    }
    FooterComponent.prototype.ngOnInit = function () {
        this.constructJson();
    };
    FooterComponent.prototype.constructJson = function () {
        var headerArray = new Array();
        var participantArray = new Array();
        var pointArray = new Array();
        var totalArray = new Array();
        // var headerArray = new Array();
        var headers = [];
        var headerObj;
        var header;
        for (var i = 0; i < this.rawJson.length; i++) {
            var headerObject = this.rawJson[i]["certType"];
            var participantObject = this.rawJson[i]["name"];
            var pointObject = this.rawJson[i]["points"];
            headerArray.push(headerObject);
            participantArray.push(participantObject);
            pointArray.push(pointObject);
            var a = new Set(headerArray);
        }
        // console.log("rawJsonObject::::::::::: " + a);
        // console.log("rawJsonObject::::::::::: " + participantArray)
        // console.log("headerObj::::::::::::::: " + pointArray)
        // console.log("rawJsonObject::::::::::: " + this.transform(participantArray));
    };
    FooterComponent.prototype.transform = function (value) {
        //     if (value !== undefined && value !== null) {
        //         return _.uniqBy(value, 'name');
        //     }
        //     return value;
        // }
    };
    return FooterComponent;
}());
FooterComponent = __decorate([
    core_1.Pipe({
        name: 'unique',
        pure: false
    }),
    core_1.Component({
        moduleId: module.id,
        selector: "app-footer",
        templateUrl: "./footer.html"
    }),
    __metadata("design:paramtypes", [])
], FooterComponent);
exports.FooterComponent = FooterComponent;
//# sourceMappingURL=footer.component.js.map