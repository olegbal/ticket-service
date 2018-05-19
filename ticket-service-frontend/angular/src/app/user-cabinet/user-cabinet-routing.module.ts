import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {UserCabinetComponent} from "./user-cabinet.component";
import {UserOrderDetailsComponent} from "./user-order-details/user-order-details.component";

const routes: Routes = [
  {path: "cabinet", component: UserCabinetComponent},
  {path: "cabinet/orders/:orderId", component: UserOrderDetailsComponent}
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class UserCabinetRoutingModule {
}
