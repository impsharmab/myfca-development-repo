import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';

import { User } from './user.interface';
import { MyFcaService } from '../../app.component.service';

@Component({
    moduleId: module.id,
    templateUrl: './new-loginForm.html',
})
export class Login implements OnInit {
    form: FormGroup;
    private test: any;
    public user: User;
    sampleUsers = [];
    private tilesArray: any;
    private userdata: any = {};
    private menu: any;
    private banners: any;
   

    constructor(private service: MyFcaService, private router: Router, private http: Http) {

    }

    ngOnInit() {
        this.user = {
            username: '',
            password: ''
        }
                
    }
    log: boolean = false;

    private login() {
        this.service.getNewServiceJSON(this.user.username, this.user.password).subscribe(

            (resUserData) => {
                

                alert(resUserData["userID"]);
                if (resUserData["error"] === "" && resUserData["error"] !== null) {
                    this.userdata["userID"] = resUserData["userID"];
                    this.userdata["name"] = resUserData["name"];
                    this.userdata["email"] = resUserData["email"];
                    this.userdata["access"] = resUserData["access"];
                    this.service.setUserData(this.userdata);
                    this.menu = resUserData["menus"];
                    this.banners = resUserData["banners"];                    
                    // if(resUserData.dashboard.length>0){
                    this.tilesArray = resUserData.dashboard[0]["tiles"];
                    this.tilesArray.sort((one, two) => {
                      
                        return one.tileOrder - two.tileOrder;
                    })
                    this.service.setTiles(this.tilesArray);
                    //    this.log = true;
                    let url = ["myfcadashboard"]
                    this.router.navigate(url);
                } else {
                    var msg = JSON.parse(resUserData["error"])["error"];
                    alert(msg);
                }
            }
            
            )

    }
    getData() { 
       
    };
    

    save(model: User, isValid: boolean) {
        // call API to save customer
        console.log(model, isValid);
    }

}