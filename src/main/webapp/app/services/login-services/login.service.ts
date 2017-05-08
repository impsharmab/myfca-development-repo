import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Observable'
import { CookieService } from 'angular2-cookie/services/cookies.service';
//import { CookieStorage } from 'cookie-storage';
import './../rxjs-operators';

//const cookieStorage = new CookieStorage();

@Injectable()
export class LoginService {
    private getLoginResponseUrl: string = './app/resources/json/token_response.json';

    private userdata: any = {}

    constructor(private http: Http, private cookieService: CookieService) { }
    setUserData(userdata: any) {
        sessionStorage.setItem("CurrentUser", "");
        sessionStorage.removeItem('CurrentUser');
        sessionStorage.removeItem('selectedCodeData');
        sessionStorage.setItem("CurrentUser", JSON.stringify(userdata));
        // this.cookieService.put("CurrentUser", "");
        // this.cookieService.remove('CurrentUser');
        // this.cookieService.remove('selectedCodeData');
        // this.cookieService.put("CurrentUser", JSON.stringify(userdata));
        // console.log(this.cookieService.get("CurrentUser"))

        
       // this.cookieService.removeAll();
        this.cookieService.put("token", (userdata.token));
        console.log(this.cookieService.get("token"))
       // alert("token" + this.cookieService.get("token"))
    }

    getUsersData() {
        return this.userdata;
    }
    getSSOLoginResponse(ssotoken, ssodealercode, ssopositioncode): any {
        var url = "./login/token/" + ssotoken + "/" + ssodealercode + "/" + ssopositioncode;
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append("Cache-Control", "no-cache");
        headers.append("Cache-Control", "no-store");
        return this.http.get(url)
            .map((response: Response) =>
                response.json())
            .catch(this.handleError);
    }

    getLoginResponse(username, password): any {
        var url = "./login/token/";
      // var url = "https://test.myfcarewards.com/myfcarewards/login/token/";
        var body = { "username": username, "password": password };
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        // headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");

        return this.http.post(url, body, { headers: headers })
            //     return this.http.get(this.getLoginResponseUrl) 
            .map((response: Response) =>
                response.json())
            .catch(this.handleError)

    }
    getRefreshLoginResponse(token) {
        debugger
        var url = "https://test.myfcarewards.com/myfcarewards/login/tokenrefresh/";
        //var url = "./login/tokenrefresh/";
       
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', token);

        // headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");

        return this.http.get(url, { headers})
            .map((response: Response) =>
                response.json())
            .catch(this.handleError)
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