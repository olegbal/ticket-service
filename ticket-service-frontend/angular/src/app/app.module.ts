import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { RegistrationComponent } from './auth/registration/registration.component';
import { AppRoutingModule } from "./app-routing.module";
import { AuthRougingModule } from "./auth/auth-routing.module";


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthRougingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
