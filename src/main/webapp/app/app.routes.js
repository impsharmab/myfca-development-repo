"use strict";
var router_1 = require("@angular/router");
var login_component_1 = require("./login.component");
var app_contentbody_component_1 = require("./ts/contentbody/app.contentbody.component");
var appRoutes = [
    { path: "app.component", component: app_contentbody_component_1.ContentSection },
    { path: "login", component: login_component_1.LoginComponent },
];
exports.appRoutingProviders = [];
exports.routing = router_1.RouterModule.forRoot(appRoutes);
//# sourceMappingURL=app.routes.js.map