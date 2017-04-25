import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { Observable } from 'rxjs/Observable'
import './../rxjs-operators';

@Injectable()
export class AdminService {

    constructor(private http: Http) { }


    getPositionCode() {
        var headers = new Headers();
        headers.append('Authorization', "");
        var getPositionCodeUrl = "./app/components/admin/positioncode-array.json";
        return this.http.get(getPositionCodeUrl, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }
    getRoles() {
        var headers = new Headers();
        headers.append('Authorization', "");
        var getPositionCodeUrl = "./app/components/admin/admin-chooseview.json";
        return this.http.get(getPositionCodeUrl, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    getAdminData() {
        var headers = new Headers();
        headers.append('Authorization', "");
        var adminService = "./app/components/admin/test-admin.json";
        return this.http.get(adminService, { headers }) 
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