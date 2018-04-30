import { NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { RegistrationComponent } from "./registration/registration.component";
import { AuthHelper } from "./authhelper";

const routes: Routes = [
  {path: AuthHelper.loginUrl, component: LoginComponent},
  {path: AuthHelper.registrationUrl, component: RegistrationComponent}
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class AuthRougingModule {
}
