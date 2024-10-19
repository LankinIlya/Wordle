import React, {Dispatch, KeyboardEvent, useRef} from "react";
import {Cell} from "./Cell";
import {RootState} from "../store/store";
import {connect, ConnectedProps} from "react-redux";
import {
    addTry,
    backSpace, CellInfo,
    finishGame, focusCell,
    GameAction,
    GameState, NUMBER_OF_LETTERS,
    restartGame,
    sendWord,
    setLetter
} from "../types/GameTypes";
import {isLetter} from "../utils/letters";
import {div} from "../utils/math";

const mapStateToProps = (state: RootState) => {
    return {
        game: state.game,
        grid: state.game.grid,
        tries: state.game.tries,
        isFinished: state.game.isFinished,
        error: state.game.error,
        currentInputCell: state.game.currentInputCell
    }
}

const mapDispatch = (dispatch : Dispatch<GameAction>) => ({
    setLetter: (letter: string) => dispatch(setLetter(letter)),
    backSpace: () => dispatch(backSpace()),
    sendWord: () => dispatch(sendWord()),
    finishGame: () => dispatch(finishGame()),
    restartGame: () => dispatch(restartGame()),
    addTry: (word: CellInfo[]) => dispatch(addTry(word)),
    focusCell: (i: number, j: number) => dispatch(focusCell(i, j)),
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
    }
    componentDidMount() {
        this.state.divRef.current.focus()
    }

    componentDidUpdate() {
        this.state.divRef.current.focus()
    }

    render() {
        return (
            <div ref={this.state.divRef}
                 className={"wordle-grid"}
                 tabIndex={0}
                 onKeyDown={ (event) =>
                     this.handleKeyEvent(event)
                }
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
                                          == this.props.tries }
                                      onClick={() =>
                                          this.props.focusCell(
                                              div(el.id, NUMBER_OF_LETTERS)
                                              , el.id % NUMBER_OF_LETTERS)
                                      }
                                />)
                        }
                    </div>))}
            </div>
        );
    }

    handleKeyEvent(event: KeyboardEvent<HTMLDivElement>) {
        const code = event.code;
        switch (code) {
            case 'Backspace':
                this.props.backSpace();
                break;
            case 'Enter':

                break;
            case 'ArrowLeft':
                this.props.focusCell(
                    this.props.currentInputCell.i
                    , this.props.currentInputCell.j - 1 );
                break;
            case 'ArrowRight':
                this.props.focusCell(
                    this.props.currentInputCell.i
                    , this.props.currentInputCell.j + 1 );
                break;
            default:
                if(isLetter(event.key)) {
                    this.props.setLetter(event.key.toUpperCase());
                }

                break;
        }

    }

}

export default connector(Game)