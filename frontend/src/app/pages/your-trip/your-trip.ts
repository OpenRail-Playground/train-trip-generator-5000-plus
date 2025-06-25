import {Component, inject, ViewEncapsulation} from '@angular/core';

import {DBIcon, DBCard, DBButton} from '@db-ux/ngx-core-components';
import {TrainMapComponent} from '../../components/train-map/train-map';
import {RoutesService} from '../../services/routes.service';
import {ActivatedRoute} from '@angular/router';
import {map, switchMap} from 'rxjs';
import {TripRequest} from '../../models/trip-request';
import {TripResponse} from '../../models/trip-response';
import {DatePipe} from '@angular/common';
import { TripWithTransfer } from '../../models/trip-with-transfer';

@Component({
	selector: 'app-your-trip-page',
	imports: [DBIcon, DBCard, DBButton, TrainMapComponent, DatePipe],
	standalone: true,
	templateUrl: './your-trip.html',
	styleUrl: './your-trip.css',
	encapsulation: ViewEncapsulation.None,
})
export class YourTripComponent {

	private routesService = inject(RoutesService);
	private activatedRoute = inject(ActivatedRoute);

	response: TripResponse | undefined;
	tripsWithTransfer: TripWithTransfer[] | undefined;
	nrOfDays: number = 0;

	ngOnInit() {
		this.activatedRoute.paramMap
			.pipe(
				map(e => ({
					maxTravelTimePerDayInHours: Number(e.get("maxTravelTimePerDayInHours")),
					destinationId: Number(e.get("destinationId")),
					departureId: Number(e.get("departureId")),
					travelDayStartTime: e.get("travelDayStartTime"),
				} as TripRequest)),
				switchMap(e => this.routesService.getTrip(e))
			)
			.subscribe(e => {
				this.processTripReponse(e);
			})
	}

	processTripReponse(response: TripResponse): any  {
		this.response = response;

		let result: TripWithTransfer[] = [];
		let nrOfDays = 1;
		for (let i = 0; i < response.trips.length; i++){
			const trip1 = response.trips[i];
			const trip2 = i < response.trips.length - 1 ? response.trips[i+1] : undefined;

			if (trip2) {
				const isNight = new Date(trip1.arrivalTime).getDate() != new Date(trip2.arrivalTime).getDate();
				result.push({trip: trip1, transfer: {isNight: isNight}});
				if (isNight) {
					nrOfDays++;
				}
			} else {
				result.push({trip: trip1, transfer: undefined});
			}
		}

		this.tripsWithTransfer = result;
		this.nrOfDays = nrOfDays;
	}
}
