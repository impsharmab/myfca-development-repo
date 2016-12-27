import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';


import { User } from './user.interface';
import {MyFcaService} from '../../app.component.service';

@Component({
  moduleId:module.id,
  
  templateUrl: './login.html',
 // providers:[MyFcaService]
})
export class Login implements OnInit{
  form: FormGroup;
  private test:any;
  public user: User;
  sampleUsers=[];

  private tilesArray: any;
  private userdata: any={};
  private menu:any;
  private banners:any;
  constructor(private service: MyFcaService, private router: Router, private http:Http) {

  }  
   
    ngOnInit(){
    //   this.service.getNewServiceJSON().subscribe(
    //   (resUserData) => {
    //     this.userdata["userID"] = resUserData["userID"];
    //     this.userdata["name"] = resUserData["name"];
    //     this.userdata["email"] = resUserData["email"];
    //     this.userdata["access"] = resUserData["access"];
    //     this.service.setUserData(this.userdata);
    //     this.menu = resUserData["menus"];
    //     this.banners = resUserData["banners"]
    //     this.tilesArray = resUserData.dashboard[0]["tiles"];
    //     this.tilesArray.sort((one, two) => {
    //       return one.tileOrder - two.tileOrder;
    //     })
    //     this.service.setTiles(this.tilesArray);
    //   }
    // )
    /* Login Validation */
        this.user = {
            username: '',
            password: '',
        }
        /* Login Validation End */

        /** User Details
         * Get User details tiles for the dashboard
         */

    }
    log:boolean = false;

    private login() { //why login method is ! private???
         

        // Call the show method to display the widget.
        
        // let headers = new Headers({ 'Content-Type': 'application/json' });
        // let options = new RequestOptions({ headers: headers });
    
        //  this.http.post("app/userdetail.json", { username, password }, options)
        //             .catch(this.handleError);
         this.service.getNewServiceJSON(this.user.username,this.user.password).subscribe(
        (resUserData) => {
          alert(resUserData["userID"]);
          if(resUserData["error"] === "" &&  resUserData["error"] !== null)
          {
        this.userdata["userID"] = resUserData["userID"];
        this.userdata["name"] = resUserData["name"];
        this.userdata["email"] = resUserData["email"];
        this.userdata["access"] = resUserData["access"];
        this.service.setUserData(this.userdata);
        this.menu = resUserData["menus"];
        this.banners = resUserData["banners"]
       // if(resUserData.dashboard.length>0){
        this.tilesArray = resUserData.dashboard[0]["tiles"];
        this.tilesArray.sort((one, two) => {
          return one.tileOrder - two.tileOrder;
         
        }) 
        // }  
        //     else{
        //     this.tilesArray=new Array();
        // }    
        
        this.service.setTiles(this.tilesArray);
    //    this.log = true;
        let url = ["myfcadashboard"]
        this.router.navigate(url);
          }else{
              var msg = JSON.parse(resUserData["error"])["error"];
              alert(msg);
          }
})

    }

    //HTTP Response Error
    // private handleError (error: Response | any) {
    //     // In a real world app, we might use a remote logging infrastructure
    //     let errMsg: string;
    //     if (error instanceof Response) {
    //         const body = error.json() || '';
    //         const err = body.error || JSON.stringify(body);
    //         errMsg = `${error.status} - ${error.statusText || ''} ${err}`
    //     } else {
    //         errMsg = error.message ? error.message : error.toString();
    //     }
    //     console.error(errMsg);
    //     return error;
    // }
    

    getData(){};
    // getUsersDetail() {
    //     this.service.getUsers()
    //         .subscribe(
    //             resUserData => this.sampleUsers = resUserData

    //         )
    //        // console.log(this.sampleUsers)
    // }

    save(model: User, isValid: boolean) {
        // call API to save customer
        console.log(model, isValid);
    }

}