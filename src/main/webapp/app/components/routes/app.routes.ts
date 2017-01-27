
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RootPageComponent } from '../root-page/root-page.component';
import { LoginComponent } from "../login/login.component";

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
    }
]

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {

}
