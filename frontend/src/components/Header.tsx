import React from "react";

type HeaderProps = {
    onClickLogin: () => void,
    onClickRegistration: () => void,
    onClickPlay: () => void
};

type HeaderState = {

};

export class Header extends React.Component<HeaderProps, HeaderState> {
    render() {
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
}
