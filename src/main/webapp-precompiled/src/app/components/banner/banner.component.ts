import { Component, OnInit, Input } from '@angular/core';
import { Http } from '@angular/http';
import { BannerService } from '../../services/banner-services/banner.service';

@Component({
    moduleId: module.id,
    selector: "app-banner",
    templateUrl: "./banner.html"
    // styleUrls: ["./css/carosuel.css", "./css/scrolling-nav.css"] ?
})

export class BannerComponent implements OnInit {
    @Input() data: any;
    private banners: any = new Array;
    constructor(private http: Http, private bannerService: BannerService) {
        this.getBanners();
    }
    ngOnInit() {
        this.data = JSON.parse(sessionStorage.getItem("CurrentUser"))      
    }

    private getBanners() {
        this.bannerService.getBanners().subscribe(
            (banners) => {
                this.banners = banners;
            }
        )
    }
}

