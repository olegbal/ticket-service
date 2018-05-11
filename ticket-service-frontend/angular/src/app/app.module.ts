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
import { EventsListComponent } from './event/event-list/events-list.component';
import { MainPageComponent } from './main-page/main-page.component';
import { EventService } from './event/event.service';
import { EventDetailsComponent } from './event/event-details/event-details.component';
import { EventRougingModule } from "./event/event-routing.module";
import { EventTicketsOverviewComponent } from './event/event-tickets-overview/event-tickets-overview.component';
import { EventBaseInfoComponent } from './event/event-base-info/event-base-info.component';
import { EventPlaceMapComponent } from './event/event-place-map/event-place-map.component';
import { EventFullDescriptionComponent } from './event/event-full-description/event-full-description.component';
import { TicketService } from './ticket/ticket.service';
import { OrderSuccessComponentComponent } from './static-pages/order-success-component/order-success-component.component';
import { StaticPagesRoutingModule } from "./static-pages/static-pages-routing.module";
import { OrderService } from './order.service';

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
    EventDetailsComponent,
    EventTicketsOverviewComponent,
    EventBaseInfoComponent,
    EventPlaceMapComponent,
    EventFullDescriptionComponent,
    OrderSuccessComponentComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    AuthRougingModule,
    EventRougingModule,
    StaticPagesRoutingModule,
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
    EventService,
    TicketService,
    OrderService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
