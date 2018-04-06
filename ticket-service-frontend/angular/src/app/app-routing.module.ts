import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserCabinetComponent } from "./user-cabinet/user-cabinet.component";

const routes: Routes = [
  {path: "cabinet", component: UserCabinetComponent}
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class AppRoutingModule {
}


