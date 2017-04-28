import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../../services/profile-service/profile.service'
import { ProfileData } from './profile.interface'
@Component({
    moduleId: module.id,

    selector: 'app-profile',
    templateUrl: 'profile.html',

})
export class ProfileComponent implements OnInit {
    private profiledata: ProfileData;
    private _password: any;
    private successPasswordChangeMessage: string = "";
    private passwordNotMatched: string = "";
    private errorPassWordChange: string = "";
    ngOnInit() {
        this.profiledata = {
            "email": "",
            "name": "",
            "password1": "",
            "password2": ""
        }
        this.getProfileData();
    }
    constructor(private profileService: ProfileService) { }

    private getProfileData() {
        //debugger
        this.profileService.getProfileData().subscribe(
            (profiledata) => {
                this.profiledata = (profiledata)
                // alert(profiledata.name)
                // alert(profiledata.email)
            },
            (error) => {

            }
        )

    }

    private postProfileData() {

        this.profileService.postProfileData(this.profiledata.name, this.profiledata.email).subscribe(

            (error) => {

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
                this.successPasswordChangeMessage = "Your profile settings are updated";
            },
            (error) => {
                this.errorPassWordChange = "Error in password change";

            }
        )
    }

}