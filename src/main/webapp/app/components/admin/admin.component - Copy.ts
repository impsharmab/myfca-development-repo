import { Component, ElementRef, OnInit, Directive, Input,ViewChild } from '@angular/core';
import { Http, Response, Headers, RequestOptions, HttpModule } from '@angular/http';
import { AdminService } from '../../services/admin-services/admin.service';
import {Router} from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';

@Component({
    moduleId: module.id,
    selector: "app-admin",
    templateUrl: "./admin.html"
    //  templateUrl: "./admin1.html"
})

// @Directive({selector: '[collapse]'})
export class AdminComponent implements OnInit {
    @Input() data: any;
    @ViewChild('f') f: FormGroup;
    success:string;
    private banners: any = new Array;
    // public sid: any;
    public edited = false;
    private name: string;
    private items: Array<any>;
    public accordionData:Array<any>;
    private allSelected:any;
    private sid:number;
    private parentId:string;//to get accordion parent check box value
    private order:number;
    private getAdminResponseUrl: string = './app/resources/json/admin-data.json';
    private toggle:Boolean;
    selectedCheckBox = {};
    clickSuccessMessage = '';
    constructor(private http: Http, private adminService: AdminService, private elRef: ElementRef) {

    }
    ngOnInit() {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"));
       

    }

    onClickEvent(e) {
        // console.log(e);
        e.stopPropagation();
        // console.log(this.selectedCheckBox);
    }
    
    OnSave() {
        event.preventDefault();
        document.body.scrollTop = 0;
       this.clickSuccessMessage = "Success! Data saved successfully";
    }

    OnCancel(admin, e) {
        var c = confirm("Do you want to Cancel");
        
        if(c===true) {
            event.preventDefault();
            // window.location.href='http://localhost:3000/admin'
            this.f.reset();
            // this.
            console.log(admin);
            console.log(e);
        } else {
            event.preventDefault();
        }

    }

    getAccordionData() {
        this.accordionData = [
            {            
            "accordionTitle": "MSER",
            'order': "1",
                data: [
                    { "name": "mser-opt1", "title": "Total Dealers Enrolled",'checked':false },
                    { "name": "mser-opt2", "title": "Excellence Cards Awards MTD",'checked':false },
                    { "name": "mser-opt3", "title": "Excellence Cards Awards YTD",'checked':false },
                ]           
            },
            {            
            "accordionTitle": "MSER Graph",
            "order": "2",
                data: [
                    { "name": "mser-opt1", "title": "uConnect",'checked':false },
                    { "name": "mser-opt2", "title": "wiAdvisor",'checked':false },
                    { "name": "mser-opt3", "title": "Express Lane",'checked':false },
                    { "name": "mser-opt4", "title": "Parts Counter",'checked':false },
                    { "name": "mser-opt5", "title": "Magenti Marelli",'checked':false },
                    { "name": "mser-opt6", "title": "MVP",'checked':false },
                    { "name": "mser-opt7", "title": "Mopar Parts",'checked':false },
                ]           
            },
            {            
            "accordionTitle": "Top Tech",
            "order": "3",
                data: [
                    { "name": "mser-opt1", "title": "Total Dealers Enrolled",'checked':false },
                    { "name": "mser-opt2", "title": "Top Performers",'checked':false },
                    { "name": "mser-opt3", "title": "Count of Surveys QTD",'checked':false },
                    { "name": "mser-opt4", "title": "Average Quarterly Survey Score",'checked':false }
                ]           
            },
            {            
            "accordionTitle": "Top Advisor",
            "order": "4",
                data: [
                    { "name": "mser-opt1", "title": "Total Dealers Enrolled",'checked':false },
                    { "name": "mser-opt2", "title": "Count of Surveys QTD",'checked':false },
                    { "name": "mser-opt3", "title": "Incentive Eligible Advisors",'checked':false },
                    { "name": "mser-opt4", "title": "Average Quarterly Survey Score",'checked':false }
                ]           
            },
            {            
            "accordionTitle": "Top Tech/Advisor Graph",
            "order": "5",
                data: [
                    { "name": "mser-opt1", "title": "Total Dealers Enrolled",'checked':false },
                    { "name": "mser-opt2", "title": "Count of Surveys QTD",'checked':false },
                    { "name": "mser-opt3", "title": "Incentive Eligible Advisors",'checked':false },
                    { "name": "mser-opt4", "title": "Average Quarterly Survey Score",'checked':false }
                ]           
            },
            {            
            "accordionTitle": "Retention",
            "order": "6",
                data: [
                    { "name": "mser-opt1", "title": "Total Dealers Enrolled",'checked':false },
                    { "name": "mser-opt2", "title": "Count of Surveys QTD",'checked':false },
                    { "name": "mser-opt3", "title": "Incentive Eligible Advisors",'checked':false },
                    { "name": "mser-opt4", "title": "Average Quarterly Survey Score",'checked':false }
                ]           
            },
            {            
            "accordionTitle": "Retention Graph",
            "order": "7",
                data: [
                    { "name": "mser-opt1", "title": "Total Dealers Enrolled",'checked':false },
                    { "name": "mser-opt2", "title": "Count of Surveys QTD",'checked':false },
                    { "name": "mser-opt3", "title": "Incentive Eligible Advisors",'checked':false },
                    { "name": "mser-opt4", "title": "Average Quarterly Survey Score",'checked':false }
                ]           
            }
        ];
    }
    // toggleItem(chapter) {
    //     console.log(chapter);
    //     chapter.checked = !chapter.checked
    //     console.log(chapter);
    // }

    toggleAll(ev) {
        this.parentId = ev.target.value
        var dataLength = this.accordionData.length;
            if(this.parentId===this.parentId) {
                if(this.parentId === "1") {
                    var SubDataLeng = 3
                } 
                if(this.parentId === "2") {
                    var SubDataLeng = 7
                }
                if(this.parentId === "3") {
                    var SubDataLeng = 4
                }
                if(this.parentId === "4" || this.parentId === "5" || this.parentId === "6" || this.parentId === "7") {
                    var SubDataLeng = 4
                }
                if ($('input.header').is(':checked')) {
                    for(var j=0;j<=SubDataLeng; j++) {
                        if($(".card-block"+this.parentId+" input#mser-opt"+j).prop('checked', false)) {
                            $(".card-block"+this.parentId+" input#mser-opt"+j).prop('checked', true)
                        }
                    }               
                }  else {
                    for(var j=0;j<=SubDataLeng; j++) {
                        if($(".card-block"+this.parentId+" input#mser-opt"+j).prop('checked', true)) {
                            $(".card-block"+this.parentId+" input#mser-opt"+j).prop('checked', false)
                        }
                    }
                }           
            } 
     }   
    
    
     uncheckParent(ev) {
         this.parentId = ev.target.value
         var SubDataLeng;
         var j;
         if(this.parentId === "1") {
                SubDataLeng = 3
            } 
            if(this.parentId === "2") {
                SubDataLeng = 7
            }
            if(this.parentId === "3") {
                SubDataLeng = 4
            }
            if(this.parentId === "4" || this.parentId === "5" || this.parentId === "6" || this.parentId === "7") {
                SubDataLeng = 4
            }
         if(ev.currentTarget.checked==true){
            
             if(ev.target.value) {
                //   if($("#header"+ev.target.value).is(":checked")) {
                    $("#header"+ev.target.value).prop("checked", true);
                //   }
             } 
            
         } else {
            for(j=1;j<=SubDataLeng;j++) {
                if (!$("#mse-options"+j).is(":checked")) {
                   $("#header"+ev.target.value).prop("checked", true);
                } 
                // else {
                    // $("#header"+ev.target.value).prop("checked", false);
                //}
            }
        }
     }


    onBlurMethod(sid: number, body: Object) {
        console.log(this.sid);
        if(this.sid) {
            this.getAccordionData();
        }
        let bodyString = JSON.stringify(body); // Stringify payload
        let headers = new Headers({ 'Content-Type': 'application/json' }); // ... Set content type to JSON
        let options = new RequestOptions({ headers: headers }); // Create a request option

        this.edited = true;
        var adminData = "./app/resources/json/serviceJson/admin-data.json";
        var AdminDataThroughService = this.http.post(adminData, body, options)
            .map((response: Response) => response.json())
            .catch(this.handleError);
        return AdminDataThroughService;
    }


    getAdminData() {
        var adminData = "./app/resources/json/serviceJson/admin-data.json";
        var AdminDataThroughService = this.http.get(adminData)
            .map((response: Response) => response.json())
            .catch(this.handleError);
        return AdminDataThroughService;
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

