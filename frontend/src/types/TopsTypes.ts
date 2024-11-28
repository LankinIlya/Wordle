
export interface UserRating {
    login: string,
    wins: number,
    games: number
}

export interface TopsDto {
    topWins: UserRating[],
    topRatio: UserRating[],
}