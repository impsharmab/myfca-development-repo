import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { Http } from '@angular/http';
import { MyFcaService } from './app.component.service';

@Component({
  moduleId: module.id,
  selector: 'my-app',
  templateUrl: './app.component.html',
  providers: [MyFcaService]
})
export class AppComponent {
  constructor(private service: MyFcaService, private router: Router, private http: Http) {
  }
}
