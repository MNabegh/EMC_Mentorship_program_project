import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';
import {Coordinates} from './coordinates';


@Injectable({
  providedIn: 'root'
})
export class CarService
{
  private carDetailsUrl = 'http://localhost:8080/carRecord?vin=';
  vin = 'control2';


  constructor(private http: HttpClient,
  private messageService: MessageService){ }

  getCarDetails()
  {
    return this.http.get(this.carDetailsUrl+this.vin)
      .pipe(
      catchError(this.handleError('getCarDetails'))
      );
  }

  setVin(vin: string): void
  {
    this.vin = vin;
  }

  getVin(): Observable<string>
  {
    return of(this.vin);
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
private log(message: string) {
  this.messageService.add(`CarService: ${message}`);
}


}
