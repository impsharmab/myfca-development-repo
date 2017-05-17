import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { ProfileService } from '../../services/profile-service/profile.service'
import { ProfileData } from './profile.interface'
@Component({
    moduleId: module.id,
    selector: 'app-profile',
    templateUrl: 'profile.html',

})
export class ProfileComponent implements OnInit {
    @Input() enablewelcomeprompt: any;
    private profiledata: ProfileData;
    private profileChangeData: any;
    private _password: any;
    private successPasswordChangeMessage: string = "";
    private passwordNotMatched: string = "";
    private errorPassWordChange: string = "";
    private successProfileChangeMessage: string = "";
    private errorProfileChangeMessage: string = "";

    constructor(private profileService: ProfileService, private router: Router, ) { }

    ngOnInit() {
        this.profiledata = {
            "email": "",
            "name": "",
            "password1": "",
            "password2": ""
        }
        this.getProfileData();
    }

    private goBack() {
        sessionStorage.setItem("showWelcomePopup", "false");
        let dashboardUrl = ["/myfcadashboard"];
        this.router.navigate(dashboardUrl);
    }
    private getProfileData() {
        this.profileService.getProfileData().subscribe(
            (profiledata) => {
                this.profiledata = (profiledata)
            },
            (error) => {
            }
        )
    }

    private changeProfileData() {
        this.profileService.changeProfileData(this.profiledata.name, this.profiledata.email).subscribe(
            (profileChangeData) => {
                this.profileChangeData = (profileChangeData);
                this.successProfileChangeMessage = "Your Profile Settings are Updated";
            },
            (error) => {
                this.errorProfileChangeMessage = "Error in profile change";
            }
        )
    }
    private changeUserPassword() {
        if (this.profiledata.password1.trim() !== this.profiledata.password2.trim()) {
            this.passwordNotMatched = "Password does not match, please enter the same password";
            return;
        }
        this.profileService.changeUserPassword(this.profiledata.password1).subscribe(
            (password) => {
                this._password = (password);
                this.successPasswordChangeMessage = "Your Profile Password is Updated";
            },
            (error) => {
                this.errorPassWordChange = "Error in password change";

            }
        )
    }

}