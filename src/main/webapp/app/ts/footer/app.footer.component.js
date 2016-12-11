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
var loc4 = window.location.pathname;
var nloc4 = loc4.slice(0, -10);
var FooterSection = (function () {
    function FooterSection() {
    }
    FooterSection.prototype.methodd = function () { console.log("loc44" + nloc4); };
    return FooterSection;
}());
FooterSection = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: "app-footer",
        // npm url  
        templateUrl: "./footer.html"
    }),
    __metadata("design:paramtypes", [])
], FooterSection);
exports.FooterSection = FooterSection;
//# sourceMappingURL=app.footer.component.js.map