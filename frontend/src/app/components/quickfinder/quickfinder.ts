import {Component, inject} from '@angular/core';

import {DBButton, DBCard, DBInput} from '@db-ux/ngx-core-components';
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {debounceTime, map, switchMap} from 'rxjs';
import {StationsService} from '../../services/stations.service';
import {KeyValue} from '../../models/commons';

@Component({
	selector: 'app-quick-finder',
	imports: [DBButton, DBInput, DBCard, FormsModule, ReactiveFormsModule],
	standalone: true,
	templateUrl: './quickfinder.html',
	styleUrl: './quickfinder.css',
})
export class QuickfinderComponent {
	private router = inject(Router);
	private formBuilder = inject(FormBuilder);
	private stationsService = inject(StationsService);

	form = this.formBuilder.group({
		from: this.formBuilder.control("", [Validators.required, Validators.minLength(1)]),
		to: this.formBuilder.control("", [Validators.required, Validators.minLength(1)]),
		maxTravelTime: this.formBuilder.control(1, [Validators.required, Validators.min(0)])
	})
	fromResult: KeyValue[] = []
	toResult: KeyValue[] = []

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

	public onSubmit() {
		if (this.form.valid) {


			const data = this.form.value
			this.router.navigate(['/your-trip']);
		}
	}

	// inputChange(event: Event) {
	// 	console.log(event)
	// }
}
