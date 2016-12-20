import {Component, OnInit, Input} from '@angular/core';
import {MyFcaService} from '../../app.component.service';
const Highcharts = require ('highcharts');

const Highcharts3d = require('highcharts/highcharts-3d.src');
Highcharts3d(Highcharts)
//require('/app/ts/contentbody/exporting.js')(Highcharts);
//require('/app/ts/contentbody/drilldown.js')(Highcharts);

Highcharts.setOptions({
    colors: ['#058DC7', '#50B432', '#ED561B']
});


@Component({
    moduleId: module.id,
    selector: "app-content",
    templateUrl: "./content_new.html"
})

export class ContentSection implements OnInit {

    @Input('tiles') tilesArray: any;

    ngOnInit() { }

    constructor(private service: MyFcaService) {}


}