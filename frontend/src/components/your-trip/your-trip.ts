import { Component } from '@angular/core';

import { DBIcon, DBCard } from '@db-ux/ngx-core-components';

@Component({
	selector: 'app-root',
	imports: [DBIcon, DBCard],
	standalone: true,
	templateUrl: './your-trip.html',
	styleUrl: './your-trip.css',
})
export class YourTripComponent {}
