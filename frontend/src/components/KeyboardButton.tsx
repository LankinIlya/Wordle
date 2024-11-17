import React, {KeyboardEvent} from "react";

type KeyboardButtonProps = {
    letter: string,
    // result: number,
    onClick: () => void;
};

type KeyboardButtonState = {

};

export class KeyboardButton extends React.Component<KeyboardButtonProps, KeyboardButtonState> {
    render() {
        let className = "keyboard_button";
        if(this.props.letter === "BackSpace"){
            className = "keyboard_button_BackSpace"
        }
        if(this.props.letter === "Enter"){
            className = "keyboard_button_Enter"
        }
        return (

            <div className={className}
                 // data-result={this.props.result}
                 onClick={this.props.onClick}>
                {this.props.letter?.toUpperCase()}
            </div>

        );
    }
}
