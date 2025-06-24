import { Component, inject } from '@angular/core';

import { QuickfinderComponent } from '../quickfinder';
import { Router } from '@angular/router';

@Component({
	selector: 'app-root',
	imports: [QuickfinderComponent],
	standalone: true,
	templateUrl: './home.html',
	styleUrl: './home.css',
})
export class HomeComponent {
	private router = inject(Router);
}
