import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserCabinetComponent } from "./user-cabinet/user-cabinet.component";
import { MainPageComponent } from "./main-page/main-page.component";

const routes: Routes = [
  {path: "", redirectTo: "home", pathMatch: "full"},
  {path: "home", component: MainPageComponent},
  {path: "cabinet", component: UserCabinetComponent}
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class AppRoutingModule {
}


