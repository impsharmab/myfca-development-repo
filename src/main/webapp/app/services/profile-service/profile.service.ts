import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { Observable } from 'rxjs/Observable'
import './../rxjs-operators';

@Injectable()
export class ProfileService {
    private getProfileServiceUrl: string = 'https://test.myfcarewards.com/myfcarewards/UserProfile/Profile/';
    private getPasswordServiceUrl: string = 'https://test.myfcarewards.com/myfcarewards/UserProfile/Password/';
    constructor(private http: Http, private cookieService: CookieService) { }

    getProfileData() {

         var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token; 
       // var validToken: any = JSON.parse(this.cookieService.get("CurrentUser")).token;

        var headers = new Headers();
        headers.append('Authorization', validToken);
        return this.http.get(this.getProfileServiceUrl, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    postProfileData(name: string, email: string) {
        debugger
         var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        //var validToken: any = JSON.parse(this.cookieService.get("CurrentUser")).token;
        var body = { "name": name, "email": email };
        var headers = new Headers();
        headers.append('Authorization', validToken);
        return this.http.post(this.getProfileServiceUrl, body, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    changeUserPassword(password: string) {
        debugger
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
//        var validToken: any = JSON.parse(this.cookieService.get("CurrentUser")).token;
        var body = { "item": password };
        var headers = new Headers();
        headers.append('Authorization', validToken);
        return this.http.post(this.getPasswordServiceUrl, body, { headers })
            .map((response: Response) => response.json())
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