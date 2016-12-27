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
var Highcharts = require('highcharts');
var Highcharts3d = require('highcharts/highcharts-3d.src');
Highcharts3d(Highcharts);
//require('/app/ts/contentbody/exporting.js')(Highcharts);
//require('/app/ts/contentbody/drilldown.js')(Highcharts);
Highcharts.setOptions({
    colors: ['#058DC7', '#50B432', '#ED561B']
});
var ContentSection = (function () {
    function ContentSection(service) {
        this.service = service;
    }
    ContentSection.prototype.ngOnInit = function () { };
    ContentSection.prototype.getJSONObject = function (jsonString) {
        //  debugger;
        console.log(jsonString);
        return JSON.parse(jsonString);
    };
    return ContentSection;
}());
__decorate([
    core_1.Input('tiles'),
    __metadata("design:type", Object)
], ContentSection.prototype, "tilesArray", void 0);
ContentSection = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: "app-content",
        templateUrl: "./content_new.html"
    }),
    __metadata("design:paramtypes", [app_component_service_1.MyFcaService])
], ContentSection);
exports.ContentSection = ContentSection;
//# sourceMappingURL=app.contentbody.component.js.map