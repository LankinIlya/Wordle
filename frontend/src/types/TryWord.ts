export interface TryWordDto {
    gameId: number,
    move: number,
    word: String
}

export interface TryWordResponseDto {
    status: number,
    result: number[]
}