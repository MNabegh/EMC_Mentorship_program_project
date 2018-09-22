export class CarDetails
{
  vin: string;
  engine_RUNTIME: string;
  dtc_NUMBER: string;
  timestamp: string;
  ord: number;
  altitude: number;
  barometric_PRESSURE:number;
  engine_COOLANT_TEMP:number;
	ambient_AIR_TEMP: number;
  engine_RPM: number;
  intake_MANIFOLD_PRESSURE: number;
  air_INTAKE_TEMP: number;
	speed: number;
  latitude: number;
  longitude: number;
  fuel_LEVEL: number;
  engine_LOAD: number;
  maf: number;
	short_TERM_FUEL_TRIM_BANK_1: number;
  throttle_POS: number;
}

getLatitude(): number
{
  return this.latitude;
}

getLongitude(): number
{
  return this.longitude;
}
//{"vin":"control1","ord":593,"timestamp":"2018-8-21 18:56:4","altitude":38,
//"barometric_PRESSURE":100,"engine_COOLANT_TEMP":90,"ambient_AIR_TEMP":4,"engine_RPM":764,
//"intake_MANIFOLD_PRESSURE":59,"air_INTAKE_TEMP":35,"speed":9,"latitude":29.941322,
//"longitude":32.615276,"fuel_LEVEL":51.0,"engine_LOAD":31.4,"maf":3.94,"short_TERM_FUEL_TRIM_BANK_1":4.7,
//"throttle_POS":20.4,"engine_RUNTIME":"01:32:20","dtc_NUMBER":"MIL is OFF0 codes"}
