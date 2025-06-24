import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {TripRequest} from '../models/trip-request';
import {TripResponse} from '../models/trip-response';

@Injectable({
  providedIn: 'root'
})
export class RoutesService {

	private httpClient = inject(HttpClient)

	public getTrip(input: TripRequest) {
		return this.httpClient.post<TripResponse>(environment.baseUrl + "/routes/trip", input)
	}
}
