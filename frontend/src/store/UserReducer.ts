import {UserState, UserAction, UserActionTypes} from "../types/UserTypes";
import {Action} from "redux";

const initialState : UserState = {
    user: 'Anonimus'
};

export function userReducer(state = initialState, action : UserAction) : UserState {
    // return {user: action.payload};
    // return state;
    switch (action.type) {
        case UserActionTypes.SET_USER:
            return {user: action.payload};
        case UserActionTypes.SIGNOUT_USER:
            return {user: null};
        default:
            return state;
    }
}