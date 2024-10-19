import {configureStore, createStore} from "@reduxjs/toolkit";
import {userReducer} from "./UserReducer";
import {gameReducer} from "./GameReducer";
import {TypedUseSelectorHook, useDispatch, useSelector} from "react-redux";
import {rootReducer} from "./RootReducer";

// export const store = configureStore({
//     reducer: {
//         user: userReducer
//         // game: gameReducer
//     },
// })

export const store = createStore(rootReducer, {})

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch

export const useAppDispatch: () => typeof store.dispatch = useDispatch;
// export const useAppSelector: () => TypedUseSelectorHook<ReturnType<typeof store.getState>> = useSelector;