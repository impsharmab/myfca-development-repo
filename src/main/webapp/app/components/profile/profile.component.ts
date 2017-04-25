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
        debugger
        this.profileService.postProfileData(this.profiledata.name, this.profiledata.email).subscribe(

            (error) => {

            }
        )
    }
    private changeUserPassword() {
        debugger
        this.profileService.changeUserPassword(this.profiledata.password1).subscribe(

            (error) => {
                alert("error in pw change")

            }
        )
    }

}