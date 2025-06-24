import { Component, inject } from '@angular/core';

import { DBButton, DBInput, DBCard } from '@db-ux/ngx-core-components';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
	selector: 'app-root',
	imports: [DBButton, DBInput, DBCard, FormsModule],
	standalone: true,
	templateUrl: './quickfinder.html',
	styleUrl: './quickfinder.css',
})
export class QuickfinderComponent {
	private router = inject(Router);

	public onSubmit() {
		// Navigate to another page
		this.router.navigate(['/your-trip']);
	}
}
