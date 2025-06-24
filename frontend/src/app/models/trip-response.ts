export interface TripResponsePart {
	trainName: string
	departureStationName: string
	departureTime: Date
	departureStationLatitude: number
	departureStationLongitude: number 
	arrivalStationName: string
	arrivalTime: Date
	arrivalStationLatitude: number
	arrivalStationLongitude: number
}

export interface TripResponse {
	trips: TripResponsePart[]
}
