import {CellInfo, GameAction, GameActionTypes, GameState, NUMBER_OF_LETTERS, NUMBER_OF_WORDS} from "../types/GameTypes";

const createInitialState = () => {
    let res: GameState = {
        grid: new Array(NUMBER_OF_WORDS),
        tries: 0,
        isFinished: false,
        isWon: false,
        isLoading: false,
        error: null,
        currentInputCell: {
            i: 0,
            j: 0
        }
    };


    for(let i = 0; i < NUMBER_OF_WORDS; i++) {
        res.grid[i] = new Array(NUMBER_OF_LETTERS);
        for(let j = 0; j < NUMBER_OF_LETTERS; j++) {
            res.grid[i][j] = {
                id: i * NUMBER_OF_LETTERS + j,
                letter: null,//'A',
                result: -1,//j-1
                isSent: false,
                isWaitingForInput: false
            };
        }
        res.grid[0][0].isWaitingForInput = true;
    }

    return res;
}

const initialState : GameState = createInitialState()



export function gameReducer(state = initialState, action: GameAction) {
    let res: GameState;
    let grid: CellInfo[][];
    let {i, j} = state.currentInputCell;
    console.log(action.type);
    console.log(action.payload);
    switch (action.type) {
        case GameActionTypes.ADD_TRY:
            if( state.isFinished
                || state.error !== null
                || action.payload === null )
                return {...state, error: "error"};
            res = state;
            let flag = false;
            for(let i = 0; i < NUMBER_OF_LETTERS; i++) {
                const rs = action.payload[i].result
                res.grid[state.tries][i] =
                    {
                        ...state.grid[state.tries][i],
                        result: rs
                    }
                if(rs !== 2) {
                    flag = true;
                }
            }

            res.grid[res.currentInputCell.i][res.currentInputCell.j].isWaitingForInput = false;
            res.currentInputCell = { i: res.tries, j: 0 }
            if(res.currentInputCell.i < NUMBER_OF_WORDS)
                res.grid[res.currentInputCell.i][res.currentInputCell.j].isWaitingForInput = true;

            res.tries++;
            if(!flag || res.tries === NUMBER_OF_WORDS) {
                res.isFinished = true;
            }

            return res;

        case GameActionTypes.FINISH_GAME:
            return {...state, isFinished: true};
        case GameActionTypes.RESTART_GAME:
            return initialState;
        case GameActionTypes.SET_LETTER:
            if(j >= NUMBER_OF_LETTERS) {
                return state;
            }
            if(state.grid[i][j].isSent) {
                return {...state, error: "error"};
            }

            grid = state.grid
            grid[i][j].letter = action.payload.letter;
            grid[i][j].isWaitingForInput = false;
            if(j + 1 < NUMBER_OF_LETTERS)
                j++;
            if(j < NUMBER_OF_LETTERS)
                grid[i][j].isWaitingForInput = true;
            return {
                ...state,
                grid: grid,
                currentInputCell: { i: i, j: j }
            }
        case GameActionTypes.SEND_WORD:
            grid = state.grid;
            for(let i = 0; i < NUMBER_OF_LETTERS; i++) {
                grid[state.tries][i] =
                    {
                        ...state.grid[state.tries][i],
                        isSent: true,
                        isWaitingForInput: false
                    }
            }
            return { ...state, grid: grid };
        case GameActionTypes.BACK_SPACE:
            grid = state.grid;
            if(j >= NUMBER_OF_LETTERS) {
                j = NUMBER_OF_LETTERS - 1;
                grid[i][j].letter = null;
                grid[i][j].isWaitingForInput = true;
            } else {
                if(grid[i][j].letter === null) {
                    if(j > 0) {
                        grid[i][j].isWaitingForInput = false;
                        j--;
                        grid[i][j].isWaitingForInput = true;
                        grid[i][j].letter = null;
                    }
                } else  {
                    grid[i][j].letter = null;
                    if(j > 0) {
                        grid[i][j].isWaitingForInput = false;
                        j--;
                        grid[i][j].isWaitingForInput = true;
                    }
                }
            }

            return {...state, grid: grid, currentInputCell: {i: i, j: j}};
        case GameActionTypes.FOCUS_CELL:
            grid = state.grid;
            if(action.payload.i == i && action.payload.j >= 0 && action.payload.j < NUMBER_OF_LETTERS) {
                grid[i][j].isWaitingForInput = false;
                j = action.payload.j;
                grid[i][j].isWaitingForInput = true;
                return {...state, grid: grid, currentInputCell: {i: i, j: j}};
            } else {
                return state;
            }
        case GameActionTypes.LOAD_GAME:
            const {id, words, results, isActive, isWon} = action.payload;
            grid = state.grid;
            const n = words.length;
            for(let i = 0; i < n; i++) {
                for(let j = 0; j < NUMBER_OF_LETTERS; j++) {
                    grid[i][j].letter = words[i][j];
                    grid[i][j].result = results[i][j];
                    grid[i][j].isWaitingForInput = false;
                }
            }

            for(let i = n; i < NUMBER_OF_WORDS; i++) {
                for(let j = 0; j < NUMBER_OF_LETTERS; j++) {
                    grid[i][j].letter = null;
                    grid[i][j].result = -1;
                    grid[i][j].isWaitingForInput = false;
                }
            }


            grid[n][0].isWaitingForInput = true;

            return {
                ...state,
                grid: grid,
                tries: n,
                isFinished: !isActive,
                isWon: isWon,
                isLoading: false,
                error: false,
                currentInputCell: {
                    i: n,
                    j: 0
                }
            }
        default:
            return state;
    }
}