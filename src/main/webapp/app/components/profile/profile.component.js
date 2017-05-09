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
var profile_service_1 = require("../../services/profile-service/profile.service");
var ProfileComponent = (function () {
    function ProfileComponent(profileService) {
        this.profileService = profileService;
        this.successPasswordChangeMessage = "";
        this.passwordNotMatched = "";
        this.errorPassWordChange = "";
        this.successProfileChangeMessage = "";
        this.errorProfileChangeMessage = "";
    }
    ProfileComponent.prototype.ngOnInit = function () {
        this.profiledata = {
            "email": "",
            "name": "",
            "password1": "",
            "password2": ""
        };
        this.getProfileData();
    };
    ProfileComponent.prototype.goBack = function () {
        window.history.back();
    };
    ProfileComponent.prototype.getProfileData = function () {
        var _this = this;
        //debugger
        this.profileService.getProfileData().subscribe(function (profiledata) {
            _this.profiledata = (profiledata);
            // alert(profiledata.name)
            // alert(profiledata.email)
        }, function (error) {
        });
    };
    ProfileComponent.prototype.changeProfileData = function () {
        var _this = this;
        this.profileService.changeProfileData(this.profiledata.name, this.profiledata.email).subscribe(function (profileChangeData) {
            _this.profileChangeData = (profileChangeData);
            _this.successProfileChangeMessage = "Your Profile Settings are Updated";
        }, function (error) {
            _this.errorProfileChangeMessage = "Error in profile change";
        });
    };
    ProfileComponent.prototype.changeUserPassword = function () {
        var _this = this;
        if (this.profiledata.password1.trim() !== this.profiledata.password2.trim()) {
            this.passwordNotMatched = "Password does not match, please enter the same password";
            return;
        }
        this.profileService.changeUserPassword(this.profiledata.password1).subscribe(function (password) {
            _this._password = (password);
            _this.successPasswordChangeMessage = "Your Profile Password is Updated";
        }, function (error) {
            _this.errorPassWordChange = "Error in password change";
        });
    };
    return ProfileComponent;
}());
__decorate([
    core_1.Input(),
    __metadata("design:type", Object)
], ProfileComponent.prototype, "enablewelcomeprompt", void 0);
ProfileComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'app-profile',
        templateUrl: 'profile.html',
    }),
    __metadata("design:paramtypes", [profile_service_1.ProfileService])
], ProfileComponent);
exports.ProfileComponent = ProfileComponent;
//# sourceMappingURL=profile.component.js.map