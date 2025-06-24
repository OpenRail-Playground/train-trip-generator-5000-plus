import { Component } from '@angular/core';

import {
  DBButton,
  DBInput,
  DBCard,
} from '@db-ux/ngx-core-components';

@Component({
  selector: 'app-root',
  imports: [
    DBButton,
    DBInput,
    DBCard,
  ],
  standalone: true,
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class HomeComponent {

}
