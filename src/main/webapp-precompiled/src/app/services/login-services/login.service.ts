import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Observable'
import { CookieService } from 'angular2-cookie/services/cookies.service';
import './../rxjs-operators';

@Injectable()
export class LoginService {
    private userdata: any = {}

    constructor(private http: Http, private cookieService: CookieService) { }
    setUserData(userdata: any) {
        sessionStorage.setItem("CurrentUser", "");
        sessionStorage.removeItem('CurrentUser');
        sessionStorage.removeItem('selectedCodeData');
        sessionStorage.setItem("CurrentUser", JSON.stringify(userdata));
        this.cookieService.put("token", (userdata.token));
    }
    getUsersData() {
        return this.userdata;
    }

    getSSOLoginResponse(ssotoken, ssodealercode, ssopositioncode): any {
        var url = "./login/token/" + ssotoken + "/" + ssodealercode + "/" + ssopositioncode;
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");
        return this.http.get(url)
            .map((response: Response) =>
                response.json())
            .catch(this.handleError);
    }

    getRefreshLoginResponse(token) {
        var url = "https://test.myfcarewards.com/login/tokenrefresh/";
        var url = "./login/tokenrefresh/";

        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', token);
        headers.append("Cache-Control", "no-cache");
        //  headers.append("Cache-Control", "no-store");
        return this.http.get(url, { headers })
            .map((response: Response) =>
                response.json())
            .catch(this.handleError)
    }

    getLoginResponse(username, password): any {
        var url = "./login/token/";
        // var url = "https://test.myfcarewards.com/login/token/";
        //var url = "https://www.myfcarewards.com/login/token/";

        var body = { "username": username, "password": password };
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append("Cache-Control", "no-cache");
        //  headers.append("Cache-Control", "no-store");
        return this.http.post(url, body, { headers: headers })
            .map((response: Response) =>
                response.json())
            .catch(this.handleError)

    }
    resetPassword(userId: string, emailId: string) {
        //var url = "https://test.myfcarewards.com/myfcarewards/UserProfile/ResetPassword";             
        var url = "./UserProfile/ResetPassword";

        var body = { "userId": userId, "email": emailId };
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");
        return this.http.post(url, body, { headers: headers })
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