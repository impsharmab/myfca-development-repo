
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RootPageComponent } from '../root-page/root-page.component';
import { LoginComponent } from "../login/login.component";
import { AdminRootPageComponent } from "../admin/admin-rootpage.component";
import { ProfileRootPageComponent } from "../profile/profile-rootpage.component";

const routes: Routes = [
    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full' 
    },
    {
        path: "login",
        component: LoginComponent
    },
    { 
        path: "myfcadashboard",
        component: RootPageComponent
    },
    {
        path: "admin",
        component: AdminRootPageComponent
    },
    {
        path: "profile",
        component: ProfileRootPageComponent
    }
]

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {

}
