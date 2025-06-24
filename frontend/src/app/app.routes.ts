import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home';
import { YourTripComponent } from './pages/your-trip/your-trip';

export const routes: Routes = [
	{ path: '', component: HomeComponent }, // Default route
	{ path: 'your-trip', component: YourTripComponent }, // /your-trip route
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule],
})
export class AppRoutingModule {}
