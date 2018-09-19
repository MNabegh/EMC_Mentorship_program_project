import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CarService
{
  private carDetailsUrl = 'localhost:8080/carRecord';


  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }


  getCarDetails(): Observable<string>
  {
    return this.http.get<string>(this.carDetailsUrl);
  }

}
