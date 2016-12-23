import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Observable'

import './rxjs-operators';
//var sha256=require('sha256.js');

@Injectable()
export class MyFcaService {
    private userDetailUrl = "/app/resources/json/userdetail.json";
    private titles = new Array();
    private userdata = {
    }

    constructor(private http: Http) { }
    setTiles(titles) {
        localStorage.setItem("titles", JSON.stringify(titles));
        //sessionStorage.setItem("titles", JSON.stringify(titles));
    }

    getTiles() {
        return this.titles;
    }
    getUsers() {
        var mytestUsers = this.http.get(this.userDetailUrl)
            .map((response: Response) => response.json())
            .catch(this.handleError);
        return mytestUsers;
    }

    setUserData(userdata: any) {
        localStorage.setItem("CurrentUser", JSON.stringify(userdata));
    }
    getUsersData() {
        // var myFcaUsers =  this.http.get(this.userDetailUrl)
        //   .map((response:Response) => response.json()) 
        //   .catch(this.handleError);
        //   return myFcaUsers;
        return this.userdata;
    }

    getNewServiceJSON(username, password): any {

        // var string = 'Test String';
        // var encodedString = btoa(string);
        // console.log(encodedString);
        // var decodedString = atob(encodedString);
        // console.log(decodedString);

        //  var x = sha256('hello');
        //  console.log(x);

     
    //   var daveService = "./app/resources/json/dave.json";
    //   var cleanDaveService = "./app/resources/json/cleanDave.json";      
    //   var mikeService = "./app/resources/json/mike.json";
        
       
      // var params= "id="+ username + "&key=" + password;
        var serviceurl = "services/userprofile?id="+ username + "&key=" + password;        
        var tileDataThroughService = this.http.post(serviceurl, {})
          //  var tileDataThroughService = this.http.get(daveService)  
            .map((response: Response) => response.json())
            .catch(this.handleError);
        return tileDataThroughService;
    }

    private extractData(res: Response) {
        let body = res.json();
        return body.data || {};
    }

    private handleError(error: Response | any) {
        // In a real world app, we might use a remote logging infrastructure
        let errMsg: string = "";
        // if (error instanceof Response) {
        //     const body = error.json() || '';
        //     const err = body.error || JSON.stringify(body);
        //     errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        // } else {
        //     errMsg = error.message ? error.message : error.toString();
        // }
        alert("Invalid credentials");
        return Observable.throw(errMsg);
    }

    getData() {
        let headersObj = new Headers();
        //headers.append('Accept', 'application/json');
        headersObj.append('Access-Control-Allow-Headers', 'Content-Type');
        headersObj.append('Access-Control-Allow-Methods', 'GET');
        headersObj.append('Access-Control-Allow-Origin', '*');
        this.http
            .get(`http://localhost:3000/app/json/jsonData.json`, {
                headers: headersObj
            })
            .map((response) => {

                console.log(response.json().data)
            })
            .catch((rr) => {
                alert();
                console.log(rr);
                return rr;
            }).subscribe(
            heroes => console.log(heroes),
            error => console.log(error));
    }
    


    //     getNewServiceJSON(username, password): any {
    //         var serviceurl = "services/userprofiletest?id=" + username + "&key=" + password;
    //         setTimeout(() => 
    // {
    //     var tileDataThroughService = this.http.get(serviceurl)
    //             .map((response: Response) => response.json())
    //             .catch(this.handleError);
    //    return tileDataThroughService;
    // },
    // 1000);
    //var userprofiletestServiceUrl = "app/resources/json/userprofiletest.json"

    // getTileDataThroughService(): any {
    //    // var newServiceUrl="/services/userprofiletest?id="+this.userdata.id+"&key="+this.userdata.key;
    //    // var userprofiletestServiceUrl = "./app/resources/json/userprofiletest.json";
    //     // var serviceUrl="/imiservices/services/tileslistbyrole?role="+this.userdata.access[0].roleID+"&id="+this.userdata.userID;
    //     var tileDataThroughService = this.http.get(newServiceUrl)
    //         .map((response: Response) => response.json())
    //         .catch(this.handleError);
    //     return tileDataThroughService;
    // }

    // <ngSubmit =getTileDataThroughService();> Login</>
}