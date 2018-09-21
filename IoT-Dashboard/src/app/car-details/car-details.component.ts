import { Component, OnInit } from '@angular/core';
import {CarService} from '../car.service';
import { Observable} from 'rxjs'
import {CarDetails} from '../carDetails';

@Component({
  selector: 'app-car-details',
  templateUrl: './car-details.component.html',
  styleUrls: ['./car-details.component.css']
})
export class CarDetailsComponent implements OnInit {
  carDetails: CarDetails;
  bye: string;

  constructor(private carService: CarService) { }

  ngOnInit() {
    this.getCarDetails();
  }

  getCarDetails(): void
  {
    this.bye = 'bye';
    this.carService.getCarDetails()
      .subscribe((details: CarDetails) => this.carDetails = details);
  }

}
