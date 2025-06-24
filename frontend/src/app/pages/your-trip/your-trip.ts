import { Component } from '@angular/core';

import { DBIcon, DBCard, DBButton } from '@db-ux/ngx-core-components';
import { TrainMapComponent } from '../../components/train-map/train-map';

@Component({
	selector: 'app-your-trip-page',
	imports: [DBIcon, DBCard, DBButton, TrainMapComponent],
	standalone: true,
	templateUrl: './your-trip.html',
	styleUrl: './your-trip.css',
})
export class YourTripComponent {}
