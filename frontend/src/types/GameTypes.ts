import exp from "node:constants";
import {TryWordResponseDto} from "./TryWord";

export const NUMBER_OF_LETTERS = 5;
export const NUMBER_OF_WORDS = 6;

export type CellInfo = {
    // key: string
    id: number,
    letter: string | null,
    result: number,
    isSent: boolean
    isWaitingForInput: boolean
}


export interface GameState {
    grid: CellInfo[][],
    tries: number,
    isFinished: boolean | null,
    isWon: boolean,
    isLoading: boolean,
    error: string | null,
    currentInputCell: {
        i: number,
        j: number
    },
    answer: string | null
}

export interface GameDto {
    id: number,
    words: String[],
    results: number[][],
    active: boolean,
    won: boolean,
    answer: string | null
}

export enum GameActionTypes {
    ADD_TRY = "ADD_TRY",
    RESTART_GAME = "RESTART_GAME",
    FINISH_GAME = "FINISH_GAME",
    SET_LETTER = "SET_LETTER",
    SEND_WORD = "SEND_WORD",
    BACK_SPACE = "BACK_SPACE",
    FOCUS_CELL = "FOCUS_CELL",
    LOAD_GAME = "LOAD_GAME",
    START_LOADING = "START_LOADING"
}

interface AddTryAction {
    type: GameActionTypes.ADD_TRY,
    payload: {
        result: number[],
        answer: string | null
    }
}

interface RestartGameAction {
    type: GameActionTypes.RESTART_GAME,
    payload: null
}

interface FinishGameAction {
    type: GameActionTypes.FINISH_GAME,
    payload: null
}

interface SetLetterAction {
    type: GameActionTypes.SET_LETTER,
    payload: {
        letter: string
    }
}

interface SendWordAction {
    type: GameActionTypes.SEND_WORD,
    payload: null
}

interface BackSpaceAction {
    type: GameActionTypes.BACK_SPACE,
    payload: null
}

interface FocusCellAction {
    type: GameActionTypes.FOCUS_CELL,
    payload: { i: number, j: number }
}

interface LoadGameAction {
    type: GameActionTypes.LOAD_GAME,
    payload: GameDto
}

interface StartLoadingAction {
    type: GameActionTypes.START_LOADING,
    payload: null
}

export type GameAction = AddTryAction
                         | RestartGameAction
                         | FinishGameAction
                         | SetLetterAction
                         | SendWordAction
                         | BackSpaceAction
                         | FocusCellAction
                         | LoadGameAction
                         | StartLoadingAction

export const addTry = (result: TryWordResponseDto) : AddTryAction => ({
    type: GameActionTypes.ADD_TRY,
    payload: {
        result: result.result,
        answer: result.answer
    }
})

export const restartGame = () : RestartGameAction => ({
    type: GameActionTypes.RESTART_GAME,
    payload: null
})

export const finishGame = () : FinishGameAction => ({
    type: GameActionTypes.FINISH_GAME,
    payload: null
})

export const setLetter = (letter: string) : SetLetterAction => {
    console.log("setLetter " + letter);
    return {
        type: GameActionTypes.SET_LETTER,
        payload: {
            letter: letter
        }
    }
}

export const sendWord = () : SendWordAction => ({
    type: GameActionTypes.SEND_WORD,
    payload: null
})

export const backSpace = () : BackSpaceAction => ({
    type: GameActionTypes.BACK_SPACE,
    payload: null
})


export const focusCell = (i: number, j: number) : FocusCellAction => ({
    type: GameActionTypes.FOCUS_CELL,
    payload: {i: i, j: j}
})

export const loadGame = (gameDto: GameDto) : LoadGameAction => ({
    type: GameActionTypes.LOAD_GAME,
    payload: gameDto
})

export const startLoading = () : StartLoadingAction => ({
    type: GameActionTypes.START_LOADING,
    payload: null
})
