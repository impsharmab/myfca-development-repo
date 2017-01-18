"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("@angular/core");
var forms_1 = require("@angular/forms");
var platform_browser_1 = require("@angular/platform-browser");
var http_1 = require("@angular/http");
var angular2_highcharts_1 = require("angular2-highcharts");
var app_component_1 = require("./app.component");
var app_view_component_1 = require("./views/rootPage/app.view.component");
var app_routes_1 = require("./app.routes");
var app_component_service_1 = require("./app.component.service");
var app_header_component_1 = require("./views/dashboard/header/app.header.component");
var app_footer_component_1 = require("./views/dashboard/footer/app.footer.component");
var app_contentbody_component_1 = require("./views/dashboard/contentbody/app.contentbody.component");
var spinner_1 = require("./views/spinner/spinner");
var login_component_1 = require("./views/login/login.component");
var ng_bootstrap_1 = require("@ng-bootstrap/ng-bootstrap");
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    core_1.NgModule({
        imports: [platform_browser_1.BrowserModule, http_1.HttpModule, forms_1.FormsModule, angular2_highcharts_1.ChartModule, forms_1.ReactiveFormsModule, app_routes_1.AppRoutingModule, ng_bootstrap_1.NgbModule.forRoot()
        ],
        providers: [app_component_service_1.MyFcaService],
        declarations: [
            app_component_1.AppComponent,
            login_component_1.Login,
            app_header_component_1.HeaderSection,
            app_footer_component_1.FooterSection,
            app_contentbody_component_1.ContentSection,
            app_view_component_1.ViewComponent,
            spinner_1.SpinnerComponent
        ],
        bootstrap: [app_component_1.AppComponent]
    })
], AppModule);
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map