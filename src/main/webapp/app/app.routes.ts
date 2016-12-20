import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

//import { LoginComponent }               from './ts/login/login.component';
import { ViewComponent } from './app.view.component';
import { ContentSection } from './ts/contentbody/app.contentbody.component'


const appRoutes: Routes = [{
    path: "myfcadashboard",
    component: ViewComponent
}, {
    path: "app.component",
    component: ContentSection
}

    //{
    //  path: "login",
    //component: LoginComponent
    // }, 
];

export const appRoutingProviders: any[] = [

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);