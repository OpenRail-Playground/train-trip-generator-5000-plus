import {Component, EventEmitter, inject, Output, ViewEncapsulation} from '@angular/core';

import {DBButton, DBCard, DBInput} from '@db-ux/ngx-core-components';
import {FormBuilder, FormControl, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {debounceTime, map, switchMap} from 'rxjs';
import {StationsService} from '../../services/stations.service';
import {KeyValue} from '../../models/commons';
import {RoutesService} from '../../services/routes.service';
import {TripRequest} from '../../models/trip-request';

@Component({
	selector: 'app-quick-finder',
	imports: [DBButton, DBInput, DBCard, FormsModule, ReactiveFormsModule],
	standalone: true,
	templateUrl: './quick-finder.html',
	styleUrl: './quick-finder.css',
	encapsulation: ViewEncapsulation.None,
})
export class QuickFinderComponent {
	private router = inject(Router);
	private formBuilder = inject(FormBuilder);
	private stationsService = inject(StationsService);
	private selectedDestinationId: number | undefined;
	private selectedDepartureId: number | undefined;

	form = this.formBuilder.group<{
		from: FormControl<string>,
		to: FormControl<string>,
		maxTravelTime: FormControl<number>,
	}>({
		from: this.formBuilder.nonNullable.control<string>("", [Validators.required, Validators.minLength(1)]),
		to: this.formBuilder.nonNullable.control<string>("", [Validators.required, Validators.minLength(1)]),
		maxTravelTime: this.formBuilder.nonNullable.control<number>(12, [Validators.required, Validators.min(0)])
	})
	fromResult: KeyValue[] = []
	toResult: KeyValue[] = []

	@Output()
	output = new EventEmitter<TripRequest>()

	ngOnInit() {

		this.form.get("from")?.valueChanges
			.pipe(
				debounceTime(300),
				switchMap(name => this.stationsService.getAutocomplete(name)),
				map(e => e.stationRecommendations.map(
					a => ({key: String(a.id), value: a.name})
				)),
			).subscribe(e => this.fromResult = e);

		this.form.get("to")?.valueChanges
			.pipe(
				debounceTime(300),
				switchMap(name => this.stationsService.getAutocomplete(name)),
				map(e => e.stationRecommendations.map(
					a => ({key: String(a.id), value: a.name})
				)),
			).subscribe(e => this.toResult = e);

	}


	changeDestination(event: null | string) {
		this.selectedDestinationId = Number(this.toResult.find(e => e.value === event)?.key);
	}

	changeDeparture(event: null | string) {
		this.selectedDepartureId = Number(this.fromResult.find(e => e.value === event)?.key);
	}

	onSubmit() {

		console.log(this.form.value, this.selectedDepartureId , this.selectedDestinationId )
		if (this.form.valid && this.selectedDepartureId && this.selectedDestinationId) {
			this.output.emit({
				destinationId: this.selectedDestinationId,
				departureId: this.selectedDepartureId,
				travelDayStartTime: "08:00",
				maxTravelTimePerDayInHours: this.form.value.maxTravelTime || 0
			})
		}
	}
}
