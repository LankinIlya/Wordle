
export interface UserState {
    user: string | null
}

export enum UserActionTypes {
    SET_USER = "SET_USER",
    SIGNOUT_USER = "SIGNOUT_USER"
}

interface SetUserAction {
    type: UserActionTypes.SET_USER,
    payload: string
}

interface SignOutUserAction {
    type: UserActionTypes.SIGNOUT_USER,
    payload: null
}

export type UserAction = SetUserAction | SignOutUserAction

// export interface UserAction{
//     type: string,
//     payload?: any
// }

//export type UserDispatch = (args: UserAction) => UserAction;

export const setUser = (login: string) : UserAction => {
    return {
        type: UserActionTypes.SET_USER,
        payload: login
    }
}