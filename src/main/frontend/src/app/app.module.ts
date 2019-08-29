import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {AutoListComponent} from './auto-list/auto-list.component';
import {AutoFormComponent} from './auto-form/auto-form.component';
import {AppRoutingModule} from './app-routing.module';
import {MenuComponent} from './menu/menu.component';
import {FooterComponent} from './footer/footer.component';
import {AutoSearchComponent} from './auto-search/auto-search.component';
import {SearchresultComponent} from './searchresult/searchresult.component';
import {NgxPaginationModule} from "ngx-pagination";

@NgModule({
  declarations: [
    AppComponent,
    AutoListComponent,
    AutoFormComponent,
    MenuComponent,
    FooterComponent,
    AutoSearchComponent,
    SearchresultComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
