import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AutocompletionResponse} from '../models/stations-autocomplete-response';
import {environment} from '../../environments/environment';
import {TripRequest} from '../models/trip-request';

@Injectable({
  providedIn: 'root'
})
export class RoutesService {

	private httpClient = inject(HttpClient)

	public getTrip(input: TripRequest) {
		return this.httpClient.post<AutocompletionResponse>(environment.baseUrl + "/routes/trip", input)
	}
}
