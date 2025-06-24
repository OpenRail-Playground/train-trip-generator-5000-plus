import {Component, inject, input} from '@angular/core';

import {DBIcon, DBCard, DBButton} from '@db-ux/ngx-core-components';
import {TrainMapComponent} from '../../components/train-map/train-map';
import {RoutesService} from '../../services/routes.service';
import {ActivatedRoute} from '@angular/router';
import {map, switchMap} from 'rxjs';
import {TripRequest} from '../../models/trip-request';

@Component({
	selector: 'app-your-trip-page',
	imports: [DBIcon, DBCard, DBButton, TrainMapComponent],
	standalone: true,
	templateUrl: './your-trip.html',
	styleUrl: './your-trip.css',
})
export class YourTripComponent {

	private routesService = inject(RoutesService);
	private activatedRoute = inject(ActivatedRoute);


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
				console.log(e)
			})


	}
}
