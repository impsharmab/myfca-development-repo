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
var forms_1 = require("@angular/forms");
var platform_browser_1 = require("@angular/platform-browser");
var http_1 = require("@angular/http");
var app_component_1 = require("./app.component");
var app_header_component_1 = require("./ts/header/app.header.component");
var app_footer_component_1 = require("./ts/footer/app.footer.component");
var app_contentbody_component_1 = require("./ts/contentbody/app.contentbody.component");
var navbar_component_1 = require("./ts/navbar/navbar.component");
var angular2_highcharts_1 = require("angular2-highcharts");
var app_component_service_1 = require("./app.component.service");
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    core_1.NgModule({
        imports: [platform_browser_1.BrowserModule, http_1.HttpModule, forms_1.FormsModule, angular2_highcharts_1.ChartModule],
        providers: [app_component_service_1.MyFcaService],
        declarations: [app_component_1.AppComponent, app_header_component_1.HeaderSection, app_footer_component_1.FooterSection, app_contentbody_component_1.ContentSection, navbar_component_1.NavImageComponent],
        bootstrap: [app_component_1.AppComponent]
    }),
    __metadata("design:paramtypes", [])
], AppModule);
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map