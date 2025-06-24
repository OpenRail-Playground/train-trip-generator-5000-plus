import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import {
  DBButton,
  DBCard,
  DBInput,
  DBHeader,
  DBBrand,
  DBLink,
  DBNavigation,
  DBNavigationItem,
  NavigationContentDirective,
  NavigationDirective,
  SecondaryActionDirective,
  MetaNavigationDirective,
} from '@db-ux/ngx-core-components';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    DBButton,
    DBCard,
    DBInput,
    DBHeader,
    DBBrand,
    DBLink,
    DBNavigation,
    DBNavigationItem,
    NavigationContentDirective,
    NavigationDirective,
    SecondaryActionDirective,
    MetaNavigationDirective,
  ],
  standalone: true,
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected title = 'train-trip-generator-5000-plus-frontend';
}
