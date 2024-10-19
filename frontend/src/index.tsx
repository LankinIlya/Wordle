import React, {KeyboardEvent} from 'react';
import ReactDOM from 'react-dom/client';
import './style.css';
import { Provider } from 'react-redux';
import { store } from './store/store'
import App from "./App";


const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

function handleKeyEvent(event: KeyboardEvent<HTMLDivElement>) {
    console.log(event.key);
}

root.render(
    <React.StrictMode>
        <Provider store={store}>
            <App />
        </Provider>
    </React.StrictMode>
);