import { NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";
import { EventsListComponent } from "./event-list/events-list.component";
import { EventDetailsComponent } from "./event-details/event-details.component";

const routes: Routes = [
  {path: 'events', component: EventsListComponent},
  {path: 'events/:id', component: EventDetailsComponent}
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class AuthRougingModule {
}
