import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AboutComponent} from "./about/about.component";
import {ItemListComponent} from "./items/itemList.component";

const routes: Routes = [
  {
    path: "about",
    component: AboutComponent
  },
  {
    path: '',
    component: ItemListComponent
  },
  {
    path: "**",
    redirectTo: '/'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
