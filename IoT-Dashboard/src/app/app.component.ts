import { Component, OnInit } from '@angular/core';
import { icon, latLng, marker, polyline, tileLayer } from 'leaflet';
import {CarService} from './car.service';
import {Coordinates} from './coordinates';
import { interval } from 'rxjs';
import {CarDetails} from './carDetails';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  implements OnInit {
  title = 'IoT-Dashboard';
  // Define our base layers so we can reference them multiple times
  streetMaps = tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    detectRetina: true,
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
  });

  // Marker for the top of Mt. Ranier
  summit = marker([ 30.044281, 31.340002 ], {
    icon: icon({
      iconSize: [ 25, 41 ],
      iconAnchor: [ 13, 41 ],
      iconUrl: 'leaflet/marker-icon.png',
      shadowUrl: 'leaflet/marker-shadow.png'
    })
  });

  options = {
   layers: [ this.streetMaps, this.summit],
   zoom: 7,
   center: latLng([ 30.044281, 31.340002 ])
 };

 ngOnInit() {
   this.getCoordinates();
 }

 constructor(private carService: CarService) { }

getCoordinates(): void
{
  this.carService.getCarDetails()
    .subscribe((details: CarDetails) =>{
    this.summit.setLatLng([details.latitude, details.longitude];
    this.options = {
        layers: [ this.streetMaps, this.summit],
        zoom: 7,
        center: latLng([ details.latitude, details.longitude ])
      };
      console.log(this.summit.getLatLng());
    }));

}

}
