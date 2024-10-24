import React, {KeyboardEvent} from 'react';
import ReactDOM from 'react-dom/client';
import './style.css';
import { Provider } from 'react-redux';
import { store } from './store/store'
import App from "./App";

export const baseUrl = 'http://localhost:8080/';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

// document.cookie.split(';').forEach(cookie => {
//     const eqPos = cookie.indexOf('=');
//     const name = eqPos > -1 ? cookie.substring(0, eqPos) : cookie;
//     document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:00 GMT';
// });

root.render(
    <React.StrictMode>
        <Provider store={store}>
            <App />
        </Provider>
    </React.StrictMode>
);