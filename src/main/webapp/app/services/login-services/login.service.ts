import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Observable'
import './../rxjs-operators';

const sha256 = require('app/resources/js/sha256.js');
//const sha256=require('https://raw.githubusercontent.com/emn178/js-sha256/master/src/sha256.js');

@Injectable()
export class LoginService {
    private getLoginResponseUrl: string = './app/resources/json/token_response.json';
    private getUserServiceUrl: string = './app/resources/json/newUserDetail.json';
    private getBaseServiceUrl: string = 'services/userprofile';

    // /imiservices/login/token

    private userdata = {}
    constructor(private http: Http) { }
    setUserData(userdata: any) {
        sessionStorage.setItem("CurrentUser", JSON.stringify(userdata));
    }
    getUsersData() {
        return this.userdata;
    }

    //     let headers = new Headers({ 'Content-Type': 'application/json' });
    //     let options = new RequestOptions({ headers: headers });

    //     return this.http.post(this.heroesUrl, { name }, options)
    //                     .map(this.extractData)
    //                     .catch(this.handleError);
    //   }
    getLoginResponse(username, password): any {
        var q_password = sha256(password);
        //console.log("encrypted q_password: " + q_password)
        // console.log("encrypted q_password: " + q_password) 
        //  console.log("this.token :"+ this.validToken)       

        var url = "/imiservices/login/token/";
        var body = {"username": username, "password": password}; //"username:" + username +  "," + "password:" + password;
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');

        //     return this.http.post(this.getBaseServiceUrl, body, { headers: headers })
        //   //       return this.http.get(this.getLoginResponseUrl)
        //         .map((response: Response) =>
        //             response.json())
        //         .catch(this.handleError);
        // let headers = new Headers({ 'Content-Type': 'x-www-form-urlencoded' });
        //let options = new RequestOptions({ headers: headers });

        // return this.http.post(this.getBaseServiceUrl, body, { headers: headers })
        // return this.http.post(url, body, { headers:headers })

       // return this.http.post(url, body, { headers: headers })

             return this.http.get(this.getLoginResponseUrl)
            .map((response: Response) =>
                response.json())
            .catch(this.handleError);



    }
    private handleError(error: Response | any) {
        let errMsg: string = "";
        if (error instanceof Response) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        return Observable.throw(errMsg);
    }

}