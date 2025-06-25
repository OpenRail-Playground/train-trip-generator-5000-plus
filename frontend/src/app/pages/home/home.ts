import {Component, inject} from '@angular/core';

import {DBSection} from '@db-ux/ngx-core-components';
import {Router} from '@angular/router';
import {QuickFinderComponent} from '../../components/quick-finder/quick-finder';
import {TripRequest} from '../../models/trip-request';

@Component({
	selector: 'app-home-page',
	imports: [QuickFinderComponent, DBSection],
	standalone: true,
	templateUrl: './home.html',
	styleUrl: './home.css',
})
export class HomeComponent {
	private router = inject(Router);


	public onSubmit(requestData: TripRequest) {
		this.router.navigate(['/your-trip', requestData]);
	}
}
