import { Component, OnInit } from '@angular/core';
import {CarService} from '../car.service';
import { Observable, of} from 'rxjs'
import {CarDetails} from '../carDetails';
import {Coordinates} from '../coordinates';

@Component({
  selector: 'app-car-details',
  templateUrl: './car-details.component.html',
  styleUrls: ['./car-details.component.css']
})
export class CarDetailsComponent implements OnInit {
  carDetails: CarDetails;
  bye: string;
  btn1Style: string;
  btn2Style: string;
  btn3Style: string;

  constructor(private carService: CarService) {
    this.btn1Style = "carButton";
    this.btn2Style = "carButton";
    this.btn3Style = "carButton";
  }

  ngOnInit() {
    this.getCarDetails();
  }

  getCarDetails(): void
  {
    this.bye = 'bye';
    this.carService.getCarDetails()
      .subscribe((details: CarDetails) => this.carDetails = details);
  }

  car1(): void
  {
    this.btn1Style = "carButtonSelected";
    this.btn2Style = "carButton";
    this.btn3Style = "carButton";
  }

  car2(): void
  {
    this.btn1Style = "carButton";
    this.btn2Style = "carButtonSelected";
    this.btn3Style = "carButton";
  }

  car3(): void
  {
    this.btn1Style = "carButton";
    this.btn2Style = "carButton";
    this.btn3Style = "carButtonSelected";
  }

}
