import { Component, OnInit } from '@angular/core';
import {CarService} from '../car.service';

@Component({
  selector: 'app-car-details',
  templateUrl: './car-details.component.html',
  styleUrls: ['./car-details.component.css']
})
export class CarDetailsComponent implements OnInit {
  carDetails: string;

  constructor(private carService: CarService) { }

  ngOnInit() {
  }

  getCarDetails(): void
  {
    this.carService.getCarDetails()
      .subscribe(details => this.carDetails = details);
  }

}
