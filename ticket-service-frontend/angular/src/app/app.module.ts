import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { RegistrationComponent } from './auth/registration/registration.component';
import { AppRoutingModule } from "./app-routing.module";
import { AuthRougingModule } from "./auth/auth-routing.module";
import { FormsModule } from "@angular/forms";
import { LoginService } from "./auth/login/login.service";
import { RegistrationService } from "./auth/registration/registration.service";
import { HttpClientModule } from "@angular/common/http";


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    AuthRougingModule,
    HttpClientModule
  ],
  providers: [LoginService, RegistrationService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
