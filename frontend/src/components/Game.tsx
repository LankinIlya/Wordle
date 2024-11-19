import React, {Dispatch, KeyboardEvent, useRef} from "react";
import {Cell} from "./Cell";
import Keyboard from "./Keyboard";
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
import {KeyboardButton} from "./KeyboardButton";

const mapStateToProps = (state: RootState) => {
    return {
        grid: state.game.grid,
        tries: state.game.tries,
        isFinished: state.game.isFinished,
        isWon: state.game.isWon,
        isLoading: state.game.isLoading,
        error: state.game.error,
        currentInputCell: state.game.currentInputCell,
        answer: state.game.answer
    }
}

const mapDispatch = (dispatch : Dispatch<GameAction>) => ({
    setLetter: (letter: string) => dispatch(setLetter(letter)),
    backSpace: () => dispatch(backSpace()),
    sendWord: () => dispatch(sendWord()),
    finishGame: () => dispatch(finishGame()),
    restartGame: () => dispatch(restartGame()),
    addTry: (result: TryWordResponseDto) => dispatch(addTry(result)),
    focusCell: (i: number, j: number) => dispatch(focusCell(i, j)),
    loadGame: (gameDto: GameDto) => dispatch(loadGame(gameDto)),
    startLoading: () => dispatch(startLoading()),

});

const connector = connect(mapStateToProps, mapDispatch)

type PropsFromRedux = ConnectedProps<typeof connector>


interface GameProps extends PropsFromRedux {

}

type ComponentGameState = {
    divRef: any
};

class Game extends React.Component<GameProps, ComponentGameState> {
    constructor(props: GameProps) {
        super(props);

        this.state = {
            divRef: React.createRef()
        }

        console.log("Game constructor");

        this.getGameStateFromServer();
    }
    componentDidMount() {
        if(!this.props.isLoading)
            this.state.divRef.current.focus()
    }

    componentDidUpdate() {
        if(!this.props.isLoading)
            this.state.divRef.current.focus()
    }

    render() {
        console.log(this.props);
        console.log("Game render");
        console.log(document.cookie);
        if(this.props.isLoading) {
            return (
                <div className={"wordle-grid"}>
                    <h2>Loading...</h2>
                </div>
            )
        }

        return (
            <div ref={this.state.divRef}
                 className={"wordle-grid"}
                 tabIndex={0}
                 onKeyDown={ (event) =>
                     this.handleKeyEvent(event)
                }
                 onBlur={() => this.focusGame()}
            >
                {this.props.grid.map((row) =>
                    (<div className={"row"} key={"row_" + row[0].id}>
                        {row.map((el) =>
                                <Cell key={"cell_component_" + el.id}
                                      id={el.id}
                                      letter={el.letter}
                                      result={el.result}
                                      isWaitingForInput={el.isWaitingForInput}
                                      inputtingRow={
                                          div(el.id, NUMBER_OF_LETTERS)
                                          == this.props.currentInputCell.i }
                                      onClick={() =>
                                          this.props.focusCell(
                                              div(el.id, NUMBER_OF_LETTERS)
                                              , el.id % NUMBER_OF_LETTERS)
                                      }
                                />)
                        }
                    </div>))}
                <GameAnswer isFinished={this.props.isFinished}
                            isWon={this.props.isWon}
                            answer={this.props.answer}/>
                <Keyboard handleBackspace={() => this.handleBackspace()}
                          handleEnter={() => this.handleEnter()}
                          handleDefault={(e) => this.handleDefault(e)}
                          restart={() => this.getGameStateFromServer()}
                          isFinished={this.props.isFinished}
                          isWon={this.props.isWon}/>
            </div>
        );
    }

    getGameStateFromServer() {
        this.props.startLoading();

        axios.request<GameDto>({
            url: baseUrl + 'game/get_game',
            method: "GET",
            withCredentials: true
        }).then((response) => {
            const res = JSON.parse(response.data.toString());
            this.props.loadGame(res);
        });
    }

    getButtons() {
        if(this.props.isFinished) {
            return (
                <div>
                    <h3>{this.props.isWon ? "You won!" : "You lost("}</h3>
                    <KeyboardButton letter={"Restart"}
                                    onClick={
                                        () => this.getGameStateFromServer()
                                    }
                    />
                </div>
            )
        } else {
            return
        }

    }

    focusGame() {
        this.state.divRef.current.focus();
    }

    handleBackspace(){
        this.props.backSpace();
    }

    handleEnter(){
        const gameid = getFromCookies("game_id");
        let word = "";
        let flag = false;
        const move = this.props.tries;
        for(let i = 0; i < NUMBER_OF_LETTERS; i++) {
            if(isLetter(this.props.grid[move][i].letter)
                && !this.props.grid[move][i].isSent) {
                word += this.props.grid[move][i].letter;
            } else {
                flag = true;
                break;
            }
        }

        if(gameid && !flag) {
            this.props.sendWord();
            const data: TryWordDto = {
                gameId: +gameid,
                move: move,
                word: word.toLowerCase()
            }

            axios.request<TryWordResponseDto>({
                url: baseUrl + "game/try_word",
                method: "POST",
                withCredentials: true,
                headers: {
                    "Content-type": "application/json"
                },
                data: JSON.stringify(data),
            }).then((response) => {
                const res = JSON.parse(response.data.toString());
                console.log(res);
                this.props.addTry(res);
            });
        }
    }

    handleArrowLeft(){
        this.props.focusCell(this.props.currentInputCell.i,
                             this.props.currentInputCell.j - 1);
    }

    handleArrowRight(){
        this.props.focusCell(this.props.currentInputCell.i,
                             this.props.currentInputCell.j + 1);
    }

    handleDefault(Key: string){
        console.log(this);
        if(isLetter(Key)) {
            console.log("handle key event: default, letter = "
                        + Key.toUpperCase());
            this.props.setLetter(Key.toUpperCase());
        }
    }

    handleKeyEvent(event: KeyboardEvent<HTMLDivElement>) {
        const code = event.code;

        if(this.props.isFinished)
            return;

        switch (code) {
            case 'Backspace':
                this.handleBackspace();
                break;
            case 'Enter':
                this.handleEnter();
                break;
            case 'ArrowLeft':
                this.handleArrowLeft();
                break;
            case 'ArrowRight':
                this.handleArrowRight();
                break;
            default:
                this.handleDefault(event.key);
                break;
        }

    }
}

export default connector(Game)