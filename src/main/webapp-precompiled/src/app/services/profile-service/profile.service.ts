import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { Observable } from 'rxjs/Observable'
import './../rxjs-operators';

@Injectable()
export class ProfileService {
    constructor(private http: Http, private cookieService: CookieService) { }

    getProfileData() {
        //  var getProfileServiceUrl: string = 'https://test.myfcarewards.com/myfcarewards/UserProfile/Profile/';
        var getProfileServiceUrl: string = './UserProfile/Profile/';
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;

        var headers = new Headers();
        headers.append('Authorization', validToken);
        headers.append("Cache-Control", "no-cache");
       // headers.append("Cache-Control", "no-store");
        return this.http.get(getProfileServiceUrl, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    changeProfileData(name: string, email: string) {
        //  var getProfileServiceUrl: string = 'https://test.myfcarewards.com/myfcarewards/UserProfile/Profile/';
        var getProfileServiceUrl: string = './UserProfile/Profile/';

        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var body = { "name": name, "email": email };
        var headers = new Headers();
        headers.append('Authorization', validToken);
        headers.append("Cache-Control", "no-cache");
      //  headers.append("Cache-Control", "no-store");
        return this.http.post(getProfileServiceUrl, body, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    changeUserPassword(password: string) {
        //var getPasswordServiceUrl: string = 'https://test.myfcarewards.com/myfcarewards/UserProfile/Password/';
        var getPasswordServiceUrl: string = './UserProfile/Password/';
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var body = { "item": password };
        var headers = new Headers();
        headers.append('Authorization', validToken);
        headers.append("Cache-Control", "no-cache");
       // headers.append("Cache-Control", "no-store");
        return this.http.post(getPasswordServiceUrl, body, { headers })
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