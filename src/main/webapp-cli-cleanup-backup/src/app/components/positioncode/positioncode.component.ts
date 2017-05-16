import { Component, OnInit, Output, EventEmitter } from '@angular/core';

import { Code } from './code.interface';
import { PositionCodeService } from '../../services/positioncode-services/positioncode.service'
import { CookieService } from 'angular2-cookie/services/cookies.service';

@Component({
    selector: 'position-code',
    templateUrl: './positioncode.html',

})
export class PositionCodeComponent implements OnInit {

    constructor(private positionCodeService: PositionCodeService,       
        private cookieService: CookieService) { }

    @Output("onSubmit") submitEvent: EventEmitter<any> = new EventEmitter<any>();
    @Output("onCancel") cancelEvent: EventEmitter<any> = new EventEmitter<any>();
    private code: Code;
    private pcode: any = [];
    private dcode: any = [];
    private codeData: any = { "selectedPositionCode": "", "selectedDealerCode": "" };

    private poscodes: any = JSON.parse(sessionStorage.getItem("CurrentUser")).positionCode;
    private delcodes: any = JSON.parse(sessionStorage.getItem("CurrentUser")).dealerCode;
    
    ngOnInit() {
       this.code = {
            selectedPositionCode: '',
            selectedDealerCode: ''
        }
        this.code = this.positionCodeService.getCodeData()
        this.pcode = this.poscodes;
        this.dcode = this.delcodes;
       
    }
    private selectPositionCode(poscode?: any) {
       
    }
    private submitClick() {
        this.positionCodeService.setCodeData(this.code);
        this.submitEvent.emit("");
    }
    private cancelClick() {
        this.cancelEvent.emit("");
    }

    private selectDealerCode(delcode?: any) {
        this.positionCodeService.setCodeData(this.code);

    }
}