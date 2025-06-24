import {Component, inject} from '@angular/core';

 import {Router} from '@angular/router';
import {QuickfinderComponent} from '../../components/quickfinder/quickfinder';

@Component({
	selector: 'app-home-page',
	imports: [QuickfinderComponent],
	standalone: true,
	templateUrl: './home.html',
	styleUrl: './home.css',
})
export class HomeComponent {
	private router = inject(Router);
}
