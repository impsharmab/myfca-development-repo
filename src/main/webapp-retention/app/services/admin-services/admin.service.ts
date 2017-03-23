import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Observable'
import './../rxjs-operators';

@Injectable()
export class AdminService {

    constructor(private http: Http) { }

    getAdminData(FormData) {
        var headers = new Headers();
        headers.append('Authorization', "");

          var adminService = "./app/resources/json/admin-data.json";
       // var tileService = "services/tile/" + id;
        return this.http.get(adminService, { headers }) //headers should be in object
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