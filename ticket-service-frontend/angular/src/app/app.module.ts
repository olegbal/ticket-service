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
import { HttpClient, HttpClientModule } from "@angular/common/http";
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './header/navbar/navbar.component';
import { AccountEntryComponent } from './header/account-entry/account-entry.component';
import { AccountEntryService } from "./header/account-entry/account-entry.service";
import { CookieService } from "ngx-cookie-service";
import { UserCabinetComponent } from './user-cabinet/user-cabinet.component';
import { CartEntryComponent } from './header/cart-entry/cart-entry.component';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { EventsListComponent } from './events-list/events-list.component';
import { MainPageComponent } from './main-page/main-page.component';
import { EventListService } from './events-list/event-list.service';

// AoT requires an exported function for factories
export function HttpLoaderFactory(httpClient: HttpClient) {
  return new TranslateHttpLoader(httpClient);
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    HeaderComponent,
    FooterComponent,
    NavbarComponent,
    AccountEntryComponent,
    UserCabinetComponent,
    CartEntryComponent,
    EventsListComponent,
    MainPageComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    AuthRougingModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  providers: [
    LoginService,
    RegistrationService,
    AccountEntryService,
    CookieService,
    EventListService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
