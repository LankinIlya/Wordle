import React from "react";
import {getLoginFromCookies} from "../utils/getLogin";

type HeaderProps = {
    onClickLogin: () => void,
    onClickRegistration: () => void,
    onClickPlay: () => void,
    onClickLogout: () => void
};

type HeaderState = {
};

export class Header extends React.Component<HeaderProps, HeaderState> {
    render() {
        if(!getLoginFromCookies()) {
            return (<header>
                        <div>
                            <a onClick={this.props.onClickPlay}> Play </a>
                        </div>
                        <div>
                            <a onClick={this.props.onClickLogin}> Sign In </a>
                            <a onClick={this.props.onClickRegistration}> Sign Up </a>
                        </div>
                    </header>);
        }
        else {
            return (<header>
                        <div>
                            <a onClick={this.props.onClickPlay}> Play </a>
                        </div>
                        <div>
                            <a onClick={this.props.onClickLogin}> {getLoginFromCookies()} </a>
                            <a onClick={this.props.onClickLogout}> Logout </a>
                        </div>
                    </header>);
        }

    }
}
