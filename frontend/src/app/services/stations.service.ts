import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {AutocompletionResponse} from '../models/stations-autocomplete-response';

@Injectable({
	providedIn: 'root'
})
export class StationsService {

	private httpClient = inject(HttpClient)

	public getAutocomplete(input: string | null) {
		return this.httpClient.post<AutocompletionResponse>(environment.baseUrl + "/stations/autocomplete", {stationName: input})
	}

}
