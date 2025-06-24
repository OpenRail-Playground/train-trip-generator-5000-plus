import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';

import { DBBrand, DBHeader } from '@db-ux/ngx-core-components';

@Component({
	selector: 'app-root',
	imports: [DBHeader, DBBrand, RouterOutlet],
	standalone: true,
	templateUrl: './app.html',
	styleUrl: './app.css',
})
export class App {
	constructor() {}

	protected title = 'Train Trip Generator 5000+';
}
