import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { Observable } from 'rxjs/Observable'
import './../rxjs-operators';

@Injectable()
export class AdminService {

    constructor(private http: Http, private cookieService: CookieService) { }


    setEmulateUserData(emulateuserData) {
        var adminToken = this.cookieService.get("token");
        this.cookieService.put("adminToken", adminToken);
        this.cookieService.put("token", emulateuserData.item);
        // sessionStorage.setItem("CurrentUser", "");
        // sessionStorage.removeItem('CurrentUser');
        // sessionStorage.removeItem('selectedCodeData');
        // sessionStorage.setItem("CurrentUser", JSON.stringify(emulateuserData));
    }

    setEndEmulateUserData(endEmulateUserData) {

    }

    getImageList() {
        var getImageListUrl = "https://test.myfcarewards.com/myfcarewards/services/files/listFiles";
        return this.http.get(getImageListUrl)
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    getPositionCode() {
        var getPositionCodeUrl = "./app/components/admin/positioncode-array.json";
        var headers = new Headers();
        headers.append('Authorization', "");

        return this.http.get(getPositionCodeUrl, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }
    getRoles() {
        var getPositionCodeUrl = "./app/components/admin/admin-chooseview.json";
        var headers = new Headers();
        headers.append('Authorization', "");

        return this.http.get(getPositionCodeUrl, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    getAdminData() {
        var adminService = "./app/components/admin/test-admin.json";
        var headers = new Headers();
        headers.append('Authorization', "");

        return this.http.get(adminService, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    getEmulateUserData(sid: string) {
        //var getEmulateUserDataUrl = "app/resources/json/emulate-user.response.json";
        // var getEmulateUserDataUrl = "service/Admin/?id="+sid;
        var getEmulateUserDataUrl = "https://test.myfcarewards.com/myfcarewards/services/admin/emulate/" + sid;

        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', validToken);
        return this.http.get(getEmulateUserDataUrl, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }
    addBanner(roleID, bc, orderBy, image) {
        debugger
        var getAddBannerUrl = "https://test.myfcarewards.com/myfcarewards/services/admin/banner/add/";
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var body = {
            "dashBoardBannersID": 0, "image": image, "roleID": roleID, "orderBy": orderBy, "businessCenter": bc,
            "link": "", "createdDate": null, "createdBy": "", "updatedDate": null, "updatedBy": "", "delFlag": ""
        };
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', 'validToken');
        // headers.append("Cache-Control", "no-cache");
        // headers.append("Cache-Control", "no-store");

        return this.http.post(getAddBannerUrl, body, { headers: headers })
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