import { Component, OnInit, Input } from '@angular/core';
import { Http } from '@angular/http';
import { MyFcaService } from '../../../app.component.service';


@Component({
    moduleId: module.id,
    selector: "app-header",
    templateUrl: "./header-bootstrap.html",
    styleUrls: ["./css/carosuel.css", "./css/scrolling-nav.css"]
})

export class HeaderSection implements OnInit {
    @Input() data: any;
    private banners: any = new Array;
    constructor(private http: Http, private service: MyFcaService) {
        this.getBanners();
        //console.log("banners: "+this.banners)
    }
    ngOnInit() {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"))
        //    document.getElementById("profileModel").click();
    }

    getBanners() {
        this.service.getBanners().subscribe(
            (banners) => {
                this.banners = banners;

                console.log("banners1 : " + banners.fileName)
                
            }
        )
    }


}

