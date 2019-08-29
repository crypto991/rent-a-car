import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {AutoListComponent} from "./auto-list/auto-list.component";
import {AutoFormComponent} from "./auto-form/auto-form.component";
import {AutoSearchComponent} from "./auto-search/auto-search.component";
import {SearchresultComponent} from "./searchresult/searchresult.component";

const routes: Routes = [
  {path: 'home', component: AutoFormComponent},
  {path: 'home/:id', component: AutoFormComponent},
  {path: 'automobili', component: AutoListComponent },
  {path: 'search', component: AutoSearchComponent },
  {path: 'result', component: SearchresultComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
