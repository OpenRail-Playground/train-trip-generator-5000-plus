
import { AfterViewInit, Component, ElementRef, Input, ViewChild } from '@angular/core';
import * as L from 'leaflet';
import { TripResponse, TripResponsePart } from '../../models/trip-response';

@Component({
	selector: 'app-train-map',
	templateUrl: './train-map.html',
	styleUrls: ['./train-map.css'],
})
export class TrainMapComponent implements AfterViewInit {

 	@ViewChild('map', { static: true }) mapElement!: ElementRef<HTMLDivElement>;
	private map!: L.Map;

	@Input() tripResponse: TripResponse | undefined;

	ngAfterViewInit(): void {
		this.initMap();
	}

	private initMap(): void {
		// Initialize the map centered roughly in Western Europe
		this.map = L.map(this.mapElement.nativeElement).setView([52.0, 7.0], 5);
		
		// Add OpenStreetMap tiles
		L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
			maxZoom: 19,
			attribution: '© OpenStreetMap contributors',
		}).addTo(this.map);
		
		if (!this.tripResponse || !this.tripResponse.trips) {
			return;
		}

		let points: any = [];
		for (let i = 0; i < this.tripResponse.trips.length; i++){
			// Add markers
			let part = this.tripResponse?.trips[i];

			let pt1: L.LatLngExpression = [part.departureStationLatitude, part.departureStationLongitude];
			L.marker(pt1).addTo(this.map).bindPopup(part.departureStationName);
			points.push(pt1);
			
			if (i == this.tripResponse.trips.length-1) {
				let pt2: L.LatLngExpression = [part.arrivalStationLatitude, part.arrivalStationLongitude];
				L.marker(pt2).addTo(this.map).bindPopup(part.departureStationName);
				points.push(pt2);
			}
		}

		// Draw train route polyline
		const trainRoute = L.polyline(points, {
			color: 'blue',
			weight: 4,
			opacity: 0.7,
			dashArray: '10,10',
		}).addTo(this.map);

		// Fit map to route bounds
		this.map.fitBounds(trainRoute.getBounds());
	}
}
