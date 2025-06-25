import { TripResponsePart } from "./trip-response";

export interface Transfer {
    isNight: boolean
}

export interface TripWithTransfer {
    trip: TripResponsePart,
    transfer: Transfer | undefined
}