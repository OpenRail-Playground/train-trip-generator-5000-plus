import { Component } from '@angular/core';

import {
  DBButton,
  DBInput,
} from '@db-ux/ngx-core-components';

@Component({
  selector: 'app-root',
  imports: [
    DBButton,
    DBInput,
  ],
  standalone: true,
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class HomeComponent {

}
