import exp from "node:constants";

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
    error: string | null,
    currentInputCell: {
        i: number,
        j: number
    }
}

export interface GameDto {
    words: String[],
    results: number[][],
    isActive: boolean,
    isWon: boolean
}

export enum GameActionTypes {
    ADD_TRY = "ADD_TRY",
    RESTART_GAME = "RESTART_GAME",
    FINISH_GAME = "FINISH_GAME",
    SET_LETTER = "SET_LETTER",
    SEND_WORD = "SEND_WORD",
    BACK_SPACE = "BACK_SPACE",
    FOCUS_CELL = "FOCUS_CELL"
}

interface AddTryAction {
    type: GameActionTypes.ADD_TRY,
    payload: CellInfo[]
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

export type GameAction = AddTryAction
                         | RestartGameAction
                         | FinishGameAction
                         | SetLetterAction
                         | SendWordAction
                         | BackSpaceAction
                         | FocusCellAction

export const addTry = (word: CellInfo[]) : AddTryAction => ({
    type: GameActionTypes.ADD_TRY,
    payload: word
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
