import { Component, OnInit, Pipe, PipeTransform } from '@angular/core';
//import * as _ from 'lodash';

@Pipe({
    name: 'unique',
    pure: false
})

@Component({
    moduleId: module.id,
    selector: "app-footer",
    templateUrl: "./footer.html"

})
export class FooterComponent implements OnInit, PipeTransform {


    private rawJson: any = [
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
    private cleanJson: any = {
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

    constructor() { }

    ngOnInit() {
        this.constructJson();
    }



    constructJson() {

        var headerArray = new Array();
        var participantArray = new Array();
        var pointArray = new Array();
        var totalArray = new Array();
        // var headerArray = new Array();
        var headers = [];
        var headerObj: any;
        var header;
        for (var i = 0; i < this.rawJson.length; i++) {
            var headerObject = this.rawJson[i]["certType"];
            var participantObject = this.rawJson[i]["name"];
            var pointObject = this.rawJson[i]["points"];


            headerArray.push(headerObject);
            participantArray.push(participantObject);
            pointArray.push(pointObject);

            var a = new Set(headerArray)

            // var dealerName=rawJsonArray[i]["dealerName"];
            // var name = rawJsonArray[0].name;

        }


        console.log("rawJsonObject::::::::::: " + a);
        console.log("rawJsonObject::::::::::: " + participantArray)
        console.log("headerObj::::::::::::::: " + pointArray)

        console.log("rawJsonObject::::::::::: " + this.transform(participantArray));

    }
    transform(value: any): any {
        //     if (value !== undefined && value !== null) {
        //         return _.uniqBy(value, 'name');
        //     }
        //     return value;

        // }

    }
}