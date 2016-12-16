import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule} from '@angular/http';
import { Observable } from 'rxjs/Observable'

import './rxjs-operators';

@Injectable()
export class MyFcaService {
    private userDetailUrl = "/app/resources/json/userdetail.json"; // /imiservices/app/resources/json/userdetail.json"; 
    // private serviceUrl = "" //"/imiservices/services/tileslistbyrole?role=2&id=S12345";
    // private userprofiletestJsonUrl = "./app/resources/json/userprofiletest.json";
    // private userprofiletestServiceUrl = "./app/resources/json/userprofiletest.json" //imiservices/services/userprofiletest?id=S12345&key=password";
   // private newService = "/app/resources/json/userprofiletest.json";
    private titles = new Array();
    private userdata = {

    }

    constructor(private http: Http) {}
    setTiles(titles) {
        this.titles = titles;
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
        this.userdata = userdata;
    }
    getUsersData() {
        // var myFcaUsers =  this.http.get(this.userDetailUrl)
        //   .map((response:Response) => response.json()) 
        //   .catch(this.handleError);
        //   return myFcaUsers;
        return this.userdata;
    }

    getNewServiceJSON(username, password): any {
        var serviceurl = "http://localhost:9090/imiservices/services/userprofile?id=" + username + "&key=" + password;        

         var tileDataThroughService = this.http.post(serviceurl, {})
            .map((response: Response) => response.json())
            .catch(this.handleError);
            return tileDataThroughService;
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

    // <nfSubmit =getTileDataThroughService();> Login</>


    private extractData(res: Response) {
        let body = res.json();
        return body.data || {};
    }

    private handleError(error: Response | any) {
        // In a real world app, we might use a remote logging infrastructure
        let errMsg: string="";
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
}