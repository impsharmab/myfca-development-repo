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
    private changeUserPasswordMessage: string = "";  
    private profileChangeMessage: string = "";

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

    // private emailRegex(email) {
    //     var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    //     return re.test(email);
    // }

    private changeProfileData() { 
        if (this.profiledata.name === "" && this.profiledata.email === "") {
            this.profileChangeMessage = "Please enter your name and email id";
            return;
        } else if (this.profiledata.name === "" && this.profiledata.email !== "") {
            this.profileChangeMessage = "Please enter your name";
            return;
        } else if (this.profiledata.name !== "" && this.profiledata.email === "") {
            this.profileChangeMessage = "Please enter your email ID";
            return;
        }
        this.profileService.changeProfileData(this.profiledata.name.trim(), this.profiledata.email.trim()).subscribe(
            (profileChangeData) => {
                this.profileChangeData = (profileChangeData);
                this.profileChangeMessage = "Your Profile Settings are Updated";
            },
            (error) => {
                this.profileChangeMessage = "Error in profile change";
            }
        )
    }
    private changeUserPassword() {
        if (this.profiledata.password1 !== this.profiledata.password2) {
            this.changeUserPasswordMessage = "Password does not match, please enter the same Password";
            return;
        } else if (this.profiledata.password1.length < 0 || this.profiledata.password2.length < 0) {
            this.changeUserPasswordMessage = "Password is Required";
            return;
        }
        this.profileService.changeUserPassword(this.profiledata.password1.trim()).subscribe(
            (password) => {
                this._password = (password);
                this.changeUserPasswordMessage = "Your Profile Password is Updated";
            },
            (error) => {
                this.changeUserPasswordMessage = "Error in Password Change";

            }
        )
    }

}