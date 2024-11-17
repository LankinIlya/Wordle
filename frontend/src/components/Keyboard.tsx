import React, {KeyboardEvent} from "react";
import {KeyboardButton} from "./KeyboardButton";
import {RootState} from "../store/store";
import {connect, ConnectedProps} from "react-redux";
import {
    addTry,
    backSpace, CellInfo,
    finishGame, focusCell,
    GameAction, GameDto,
    GameState, loadGame, NUMBER_OF_LETTERS,
    restartGame,
    sendWord,
    setLetter, startLoading
} from "../types/GameTypes";
import {isLetter} from "../utils/letters";
import {div} from "../utils/math";
import axios from "axios-typescript";
import {TryWordDto, TryWordResponseDto} from "../types/TryWord";
import {getFromCookies} from "../utils/getFromCookies";
import {baseUrl} from "../index";
import {GameAnswer} from "./GameAnswer";

const keyboard = [['Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'],
              ['A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'],
                ['Z', 'X', 'C', 'V', 'B', 'N', 'M']];

interface KeyboardProps {
    handleBackspace: () => void,
    handleEnter: () => void,
    handleDefault: (Key: string) => void;
}

type KeyboardState = {

};

class Keyboard extends React.Component<KeyboardProps, KeyboardState> {
    render() {
        console.log(this.props);
        console.log("Keyboard render");
        console.log(document.cookie);
        return (
            <div className={"keyboard-grid"}>
                {keyboard.map((row: string[], i: number) =>
                    (<div className={"keyboard-row"} key={"keyboard-row_" + i}>
                        {row.map((el: string, j: number) =>
                                <KeyboardButton key={"keyboard_button_component_" + i + "_" + j}
                                      letter={el}
                                      onClick={() => this.props.handleDefault(el)}
                                />)
                        }
                    </div>))}
                <div className={"keyboard-row"}>
                    <KeyboardButton key={"keyboard_button_component_BackSpace"}
                          letter={"BackSpace"}
                          onClick={() => this.props.handleBackspace()}
                    />
                    <KeyboardButton key={"keyboard_button_component_Enter"}
                          letter={"Enter"}
                          onClick={() => this.props.handleEnter()}
                    />
                </div>
            </div>
        );
    }
}

export default Keyboard