import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { RegistrationComponent } from './auth/registration/registration.component';
import { AppRoutingModule } from "./app-routing.module";
import { AuthRougingModule } from "./auth/auth-routing.module";
import { FormsModule } from "@angular/forms";
import { LoginService } from "./auth/login/login.service";
import { RegistrationService } from "./auth/registration/registration.service";
import { HttpClientModule } from "@angular/common/http";
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AccountEntryComponent } from './auth/account-entry/account-entry.component';
import { AccountEntryService } from "./auth/account-entry/account-entry.service";
import { CookieService } from "ngx-cookie-service";


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    HeaderComponent,
    FooterComponent,
    NavbarComponent,
    AccountEntryComponent,
  ],
  imports: [
    NgbModule.forRoot(),
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    AuthRougingModule,
    HttpClientModule
  ],
  providers: [
    LoginService,
    RegistrationService,
    AccountEntryService,
    CookieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
