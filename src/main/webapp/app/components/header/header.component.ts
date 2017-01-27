import { Component, OnInit, Input } from '@angular/core';
import { Http } from '@angular/http';
import { HeaderService } from '../../services/header-services/header.service';

@Component({
    moduleId: module.id,
    selector: "app-header",
    templateUrl: "./header.html"   
    // styleUrls: ["./css/carosuel.css", "./css/scrolling-nav.css"] ?
})

export class HeaderComponent implements OnInit {
    @Input() data: any;
    private banners: any = new Array;
    constructor(private http: Http, private headerService: HeaderService) {
        this.getBanners();
        //console.log("banners: "+this.banners)
    }
    ngOnInit() {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"))
        //    document.getElementById("profileModel").click();
    }

    getBanners() {
        this.headerService.getBanners().subscribe(
            (banners) => {
                this.banners = banners;

                console.log("banners1 : " + banners.fileName)

            }
        )
    }


}

