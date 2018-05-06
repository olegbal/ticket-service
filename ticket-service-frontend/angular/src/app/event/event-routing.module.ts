import { NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";
import { EventsListComponent } from "./event-list/events-list.component";
import { EventDetailsComponent } from "./event-details/event-details.component";
import { EventHelper } from "./eventhelper";
import { EventTicketsOverviewComponent } from "./event-tickets-overview/event-tickets-overview.component";

const routes: Routes = [
  {path: EventHelper.eventUrl, component: EventsListComponent},
  {
    path: EventHelper.routeEventDetails, component: EventDetailsComponent,
    children: [
      {path: 'tickets-overview', component: EventTicketsOverviewComponent}
    ]
  }
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class EventRougingModule {
}
