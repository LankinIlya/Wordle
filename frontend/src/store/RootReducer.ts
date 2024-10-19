
import { combineReducers } from 'redux';
import { gameReducer } from './GameReducer';
import { userReducer } from './UserReducer';

export const rootReducer = combineReducers({
    user: userReducer,
    game: gameReducer
});