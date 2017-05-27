import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { Observable } from 'rxjs/Observable'
import './../rxjs-operators';

@Injectable()
export class AdminService {
    //private baseUrl = "";
        private baseUrl = "https://test.myfcarewards.com/myfcarewards/";

    constructor(private http: Http, private cookieService: CookieService) { }

    setEmulateUserData(emulateuserData) {
        var adminToken = this.cookieService.get("token");
        this.cookieService.put("adminToken", adminToken);
        this.cookieService.put("token", emulateuserData.item);
    }

    setEndEmulateUserData(endEmulateUserData) {

    }

    getImageList() {
        var getImageListUrl = this.baseUrl + "services/files/listFiles"
        return this.http.get(getImageListUrl)
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    getPositionCode() {
        var getPositionCodeUrl = "./assets/json/positioncode-array.json";
        var headers = new Headers();
        headers.append('Authorization', "");

        return this.http.get(getPositionCodeUrl, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }
    getRoles() {
        var getPositionCodeUrl = "./assets/json/admin-chooseview.json";
        var headers = new Headers();
        headers.append('Authorization', "");

        return this.http.get(getPositionCodeUrl, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    getAdminData() {
        var adminService = "./assets/json/test-admin.json";
        var headers = new Headers();
        headers.append('Authorization', "");

        return this.http.get(adminService, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    getEmulateUserData(sid: string) {
        var getEmulateUserDataUrl = this.baseUrl + "services/admin/emulate/" + sid;
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', validToken);
        return this.http.get(getEmulateUserDataUrl, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    addBanner(roleID, bc, orderBy, image) {
        var getAddBannerUrl = this.baseUrl + "services/admin/banner/add/";
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var body = {
            "dashBoardBannersID": 0, "image": image, "roleID": roleID, "orderBy": orderBy, "businessCenter": bc,
            "link": "", "createdDate": null, "createdBy": "", "updatedDate": null, "updatedBy": "", "delFlag": ""
        };
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', validToken);

        return this.http.post(getAddBannerUrl, body, { headers: headers })
            .map((response: Response) =>
                response.json())
            .catch(this.handleError)

    }
    getAllBannerData() {
        var getAllBannerDataUrl = this.baseUrl + "services/admin/banner/getAll/";
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', validToken);
        return this.http.get(getAllBannerDataUrl, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }
    editBannerData(editBannerDataObj: any) {
        var editBannerDataUrl = this.baseUrl + "services/admin/banner/update/";
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var body = editBannerDataObj;
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', validToken);
        return this.http.put(editBannerDataUrl, body, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    deleteBannerData(dashBoardBannersID: any) {
        var deleteBannerDataUrl = this.baseUrl + "services/admin/banner/delete/" + dashBoardBannersID;
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', validToken);

        return this.http.delete(deleteBannerDataUrl, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    getTileDataLocal() {
        var getTileDataLocalUrl = "./assets/json/admin-tile.json";
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', validToken);

        return this.http.get(getTileDataLocalUrl, { headers })
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }


    getTileDataResponse(positioncode) {
        var getTileDataResponseUrl = "./assets/json/admin-tile-response.json";
        var validToken: any = JSON.parse(sessionStorage.getItem("CurrentUser")).token;
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', validToken);

        return this.http.get(getTileDataResponseUrl)
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