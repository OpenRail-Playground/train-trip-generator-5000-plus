import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-train-map',
  templateUrl: './train-map.html',
  styleUrls: ['./train-map.css']
})
export class TrainMapComponent implements AfterViewInit {
  @ViewChild('map', { static: true }) mapElement!: ElementRef<HTMLDivElement>;
  private map!: L.Map;

  ngAfterViewInit(): void {
    this.initMap();
  }

  private initMap(): void {
    // Coordinates for Copenhagen and Paris
    const copenhagen: L.LatLngExpression = [55.6761, 12.5683];
    const paris: L.LatLngExpression = [48.8566, 2.3522];

    // Initialize the map centered roughly in Western Europe
    this.map = L.map(this.mapElement.nativeElement).setView([52.0, 7.0], 5);

    // Add OpenStreetMap tiles
    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
      attribution: '© OpenStreetMap contributors'
    }).addTo(this.map);

    // Add markers
    L.marker(copenhagen).addTo(this.map).bindPopup('Copenhagen');
    L.marker(paris).addTo(this.map).bindPopup('Paris');

    // Draw train route polyline
    const trainRoute = L.polyline([copenhagen, paris], {
      color: 'blue',
      weight: 4,
      opacity: 0.7,
      dashArray: '10,10'
    }).addTo(this.map);

    // Fit map to route bounds
    this.map.fitBounds(trainRoute.getBounds());
  }
}
