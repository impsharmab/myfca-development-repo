"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
//importing default angular dependencies 
var core_1 = require("@angular/core");
var forms_1 = require("@angular/forms");
var platform_browser_1 = require("@angular/platform-browser");
var http_1 = require("@angular/http");
var angular2_highcharts_1 = require("angular2-highcharts");
var ng_bootstrap_1 = require("@ng-bootstrap/ng-bootstrap");
var ng_bootstrap_2 = require("@ng-bootstrap/ng-bootstrap");
//importing components
var app_component_1 = require("./app.component");
var root_page_component_1 = require("./components/root-page/root-page.component");
var app_routes_1 = require("./components/routes/app.routes");
var header_component_1 = require("./components/header/header.component");
var footer_component_1 = require("./components/footer/footer.component");
var body_component_1 = require("./components/body/body.component");
var spinner_component_1 = require("./components/spinner/spinner.component");
var login_component_1 = require("./components/login/login.component");
var body_component_modal_1 = require("./components/body/body.component.modal");
//importing services
var body_service_1 = require("./services/body-services/body.service");
var footer_service_1 = require("./services/footer-services/footer.service");
var header_service_1 = require("./services/header-services/header.service");
var login_service_1 = require("./services/login-services/login.service");
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
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
            body_service_1.BodyService,
            footer_service_1.FooterService,
            header_service_1.HeaderService,
            login_service_1.LoginService,
            ng_bootstrap_2.NgbActiveModal
        ],
        declarations: [
            app_component_1.AppComponent,
            login_component_1.LoginComponent,
            header_component_1.HeaderComponent,
            footer_component_1.FooterComponent,
            body_component_1.BodyComponent,
            root_page_component_1.RootPageComponent,
            spinner_component_1.SpinnerComponent,
            body_component_modal_1.NgbdModalContent
        ],
        bootstrap: [app_component_1.AppComponent]
    })
], AppModule);
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map