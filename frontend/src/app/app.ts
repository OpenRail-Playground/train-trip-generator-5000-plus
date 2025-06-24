import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';

import {
  DBHeader,
  DBBrand,
} from '@db-ux/ngx-core-components';

@Component({
  selector: 'app-root',
  imports: [
    DBHeader,
    DBBrand,
    RouterOutlet,
  ],
  standalone: true,
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  constructor(private router: Router) {}

  protected title = 'Train Trip Generator 5000+';

  onSubmit() {
    // Do your form processing here
    console.log('Form submitted!');

    // Navigate to another page
    this.router.navigate(['/your-trip']);
  }
}
