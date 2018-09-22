import { Component, OnInit } from '@angular/core';
import {CarDetailsComponent} from '../car-details/car-details.component';
import { icon, latLng, marker, polyline, tileLayer } from 'leaflet';

@Component({
  selector: 'app-maps',
  templateUrl: './maps.component.html',
  styleUrls: ['./maps.component.css']
})
export class MapsComponent implements OnInit {


  constructor() { }

  ngOnInit() {
  }
  // Define our base layers so we can reference them multiple times
  streetMaps = tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    detectRetina: true,
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
  });
  wMaps = tileLayer('http://maps.wikimedia.org/osm-intl/{z}/{x}/{y}.png', {
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


}
