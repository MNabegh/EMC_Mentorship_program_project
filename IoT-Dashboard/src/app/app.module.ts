import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';

import { AppComponent } from './app.component';
import { CarDetailsComponent } from './car-details/car-details.component';
import { HttpClientModule }    from '@angular/common/http';
import { MessagesComponent } from './messages/messages.component';
import { ClarityModule } from 'clarity-angular';

@NgModule({
  declarations: [
    AppComponent,
    CarDetailsComponent,
    MessagesComponent
  ],
  imports: [
    BrowserModule,
    LeafletModule.forRoot(),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
