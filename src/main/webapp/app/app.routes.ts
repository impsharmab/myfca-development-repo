
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ViewComponent } from './views/rootPage/app.view.component';
import { Login } from "./views/login/login.component";

const routes: Routes = [{ path: '', redirectTo: 'login', pathMatch: 'full' }, {
    path: "login",
    component: Login
}, {
    path: "myfcadashboard",
    component: ViewComponent
}]

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {

}
