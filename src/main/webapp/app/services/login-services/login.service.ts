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
        sessionStorage.setItem("CurrentUser", "");
        sessionStorage.removeItem('CurrentUser');
        sessionStorage.removeItem('selectedCodeData');
        sessionStorage.setItem("CurrentUser", JSON.stringify(userdata));
    }
    getUsersData() {
        return this.userdata;
    }

    getLoginResponse(username, password): any {
          var url = "./login/token/";
        //var url = "http://localhost:8080/imiservices/login/token/";
        // var url = "http://172.24.16.75:9080/imiservices/login/token/";
        var body = { "username": username, "password": password };
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
          return this.http.post(url, body, { headers: headers })
        //return this.http.get(this.getLoginResponseUrl)
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