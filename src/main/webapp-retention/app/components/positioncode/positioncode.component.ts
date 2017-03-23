import { Component, OnInit ,Output,EventEmitter } from '@angular/core';
//import {AbstractControl,FORM_DIRECTIVES } from '@angular/common';
import { Code } from './code.interface';
import { PositionCodeService } from '../../services/positioncode-services/positioncode.service'
import { DashboardBodyService } from '../../services/dashboard-body-services/dashboard-body.service'

@Component({
    selector: 'position-code',
    templateUrl: 'app/components/positioncode/positioncode.html',
    // directives:[FORM_DIRECTIVES]
})
export class PositionCodeComponent implements OnInit {
    @Output("onSubmit") submitEvent: EventEmitter<any> = new EventEmitter<any>();
    
    @Output("onCancel") cancelEvent: EventEmitter<any> = new EventEmitter<any>();
    private code: Code;
    private pcode: any = [];
    private dcode: any = [];
    private codeData: any = { "selectedPositionCode": "", "selectedDealerCode": "" };

    private poscodes: any = JSON.parse(sessionStorage.getItem("CurrentUser")).positionCode;
    private delcodes: any = JSON.parse(sessionStorage.getItem("CurrentUser")).dealerCode;

    constructor(private positionCodeService: PositionCodeService, private dashboardBodyService: DashboardBodyService) { }

    ngOnInit() {
        //this.code.dealerCode=selectedPositionCode;

        this.code = {
            selectedPositionCode: '',
            selectedDealerCode: ''
        }
        this.code = this.positionCodeService.getCodeData()
        this.pcode = this.poscodes;
        this.dcode = this.delcodes;
        // this.defaultPositionDealerCode();
    }

    // defaultPositionDealerCode(): any {
    //     if (this.pcode.length == 0 || this.dcode.length == 0) {
    //         this.codeData["selectedPositionCode"] = 0;
    //         this.codeData["selectedDealerCode"] = 0;
    //     }
    //     else if (this.pcode.length > 0 || this.dcode.length >0) {
    //         this.code.positionCode=this.pcode[0];
    //         this.code.dealerCode=this.dcode[0];
    //         this.codeData["selectedPositionCode"] = this.pcode[0];
    //         this.codeData["selectedDealerCode"] = this.dcode[0];
    //     }        
    //     this.positionCodeService.setCodeData(this.codeData);
    // }


    private selectPositionCode(poscode?: any) {
        //this.positionCodeService.setCodeData(this.code);

    }
    private submitClick() {
        alert("Test");
        this.positionCodeService.setCodeData(this.code);
        this.submitEvent.emit("");
    }
    private cancelClick() {
        this.cancelEvent.emit("");
    }

    private selectDealerCode(delcode?: any) {
        this.positionCodeService.setCodeData(this.code);

    }

    // private submitSelectedCodes(){
    //  this.dashboardBodyService.getNumberOfTiltes();
    //this.dashboardBodyService.getTilteJson();
    //this.dashboardBodyService.getChartJson();
    // }
}