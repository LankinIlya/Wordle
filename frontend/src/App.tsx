import React from 'react';
import {Header} from './components/Header';
import {Game} from './components/Game';
import {Login} from './components/Login';
import {Registration} from './components/Registration';
import {SideBar} from "./components/SideBar";

enum Page { Game, Login, Registration}

type AppProps = {

};

type AppState = {
    page: Page
};

export class App extends React.Component<AppProps, AppState>{
    constructor(props: AppProps) {
        super(props);
        this.state = {
          page: Page.Game
        }
        this.onClickPlay = this.onClickPlay.bind(this);
        this.onClickLogin = this.onClickLogin.bind(this);
        this.onClickRegistration = this.onClickRegistration.bind(this);
    }
    render() {
        return (
            <div className="App">
                <Header onClickLogin={this.onClickLogin}
                      onClickPlay={this.onClickPlay}
                      onClickRegistration={this.onClickRegistration}
                />
                <div className={"container"}>
                    <div className={"content"}>
                        {this.renderContent()}
                    </div>
                    <SideBar />
                </div>

            </div>
        );
    }

    renderContent() {
        switch (+this.state.page) {
        case Page.Game:
            return <Game />;
        case Page.Login:
            return <Login />;
        case Page.Registration:
            return <Registration />;
        }
    }

    onClickPlay() {
        console.log("Play");
        this.setState({
            page: Page.Game
        });
    }

    onClickLogin() {
        console.log("Login");
        this.setState({
            page: Page.Login
        });
    }

    onClickRegistration() {
        console.log("Registration");
        this.setState({
            page: Page.Registration
        });
    }
}
