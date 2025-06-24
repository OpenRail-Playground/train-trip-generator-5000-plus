export interface AutocompletionResponsePart {
	id: number
	name: string


}

export interface AutocompletionResponse {
	stationRecommendations: AutocompletionResponsePart[]
}
