export interface TripRequest {
	departureId: number
	destinationId: number
	travelDayStartTime: string
	maxTravelTimePerDayInHours: number
}
