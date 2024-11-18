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
        switch(this.props.letter) {
            case "BackSpace":
                className += " keyboard_button_BackSpace";
                break;
            case "Enter":
                className += " keyboard_button_Enter"
                break;
            case "Restart":
                className += " keyboard_button_Restart"
                break;
            default:
                className += " keyboard_button";
                break;
        }

        return (
            <button type={"submit"} className={className} onClick={this.props.onClick}>
                {this.props.letter?.toUpperCase()}
            </button>
            // <div className={className}
            //      // data-result={this.props.result}
            //      onClick={this.props.onClick}>
            //     {this.props.letter?.toUpperCase()}
            // </div>

        );
    }
}
