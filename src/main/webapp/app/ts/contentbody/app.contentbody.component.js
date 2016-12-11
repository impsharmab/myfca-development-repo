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
var app_component_service_1 = require("../../app.component.service");
var Highcharts = require('highcharts');
var Highcharts3d = require('highcharts/highcharts-3d.src');
Highcharts3d(Highcharts);
//require('/../../app/ts/contentbody/exporting.js')(Highcharts);
//require('/../../app/ts/contentbody/drilldown.js')(Highcharts);
Highcharts.setOptions({
    colors: ['#058DC7', '#50B432', '#ED561B']
});
var loc2 = window.location.pathname;
var nloc2 = loc2.slice(0, -10);
var ContentSection = (function () {
    function ContentSection(service) {
        this.service = service;
        this.loc = window.location.pathname;
        this.colOneArray = new Array();
        this.colTwoArray = new Array();
        console.log("zero " + this.tilesArray);
        console.log("one " + this.colOneArray);
        console.log("two " + this.colTwoArray);
    }
    ContentSection.prototype.ngOnInit = function () {
        // this.getContentData();
        //this.getHighchartData();
        // this.getHighchartDataThroughJson();
        //   this.getTileDataThroughService();
        // this.getUserProfileTestData();
        this.getTileDataThroughLocalJsonService();
    };
    //    getContentData() {
    //         this.service.getContentTileData()
    //             .subscribe(
    //                 (resUserData) => {this.tilesArray = resUserData 
    //                     var colOne=0,colTwo = 0;
    //                 for(var i=0;i<this.tilesArray.length;i++){
    //                     if(i%2==0){
    //                         this.colOneArray[colOne] = this.tilesArray[i];
    //                         colOne++;
    //                     }else{
    //                         this.colTwoArray[colTwo] = this.tilesArray[i];
    //                         colTwo++;
    //                     }
    //                 }   
    //             }
    //         ) 
    //     }  
    // getTileDataThroughService() {
    //      this.service.getTileDataThroughService()
    //          .subscribe(
    //              (resUserData) => {this.tilesArray = resUserData 
    //                  var colOne=0,colTwo = 0;
    //              for(var i=0;i<this.tilesArray.length;i++){
    //                  if(i%2==0){
    //                      this.colOneArray[colOne] = this.tilesArray[i];
    //                      colOne++;
    //                  }else{
    //                      this.colTwoArray[colTwo] = this.tilesArray[i];
    //                      colTwo++;
    //                  }
    //              }   
    //          }
    //      ) 
    //  }
    ContentSection.prototype.getTileDataThroughLocalJsonService = function () {
        var _this = this;
        this.service.getTileDataThroughLocalJsonService()
            .subscribe(function (resUserData) {
            _this.tilesArray = resUserData;
            var colOne = 0, colTwo = 0;
            for (var i = 0; i < _this.tilesArray.length; i++) {
                if (i % 2 == 0) {
                    _this.colOneArray[colOne] = _this.tilesArray[i];
                    colOne++;
                }
                else {
                    _this.colTwoArray[colTwo] = _this.tilesArray[i];
                    colTwo++;
                }
            }
        });
    };
    return ContentSection;
}());
ContentSection = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: "app-content",
        // npm url 
        //  templateUrl:"/app/ts/contentbody/newContent.html"
        templateUrl: "./newDesign.html"
    }),
    __metadata("design:paramtypes", [app_component_service_1.MyFcaService])
], ContentSection);
exports.ContentSection = ContentSection;
//# sourceMappingURL=app.contentbody.component.js.map