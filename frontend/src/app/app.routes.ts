import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from '../components/home/home';
import {YourTripComponent} from '../components/your-trip/your-trip';

export const routes: Routes = [
  {path: '', component: HomeComponent},              // Default route
  {path: 'your-trip', component: YourTripComponent} // /your-trip route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
