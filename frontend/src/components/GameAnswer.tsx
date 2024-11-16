import React from "react";
import {Cell} from "./Cell";
import {NUMBER_OF_LETTERS, NUMBER_OF_WORDS} from "../types/GameTypes";

type GameAnswerProps = {
    isFinished: boolean | null,
    isWon: boolean,
    answer: string | null
};

type GameAnswerState = {

};

export class GameAnswer extends React.Component<GameAnswerProps, GameAnswerState> {
    render() {
        if(!this.props.isFinished ||
            this.props.isWon ||
            this.props.answer === null)
            return (<></>);

        let chars : string[] = []
        for(let i = 0; i < NUMBER_OF_LETTERS; i++)
            chars.push(this.props.answer[i])

        return (
            <div className="row">
                {
                    chars.map((char, i) =>
                        <Cell key={`answer_${i}`}
                              id={NUMBER_OF_WORDS * NUMBER_OF_LETTERS + i}
                              letter={char}
                              result={3} isWaitingForInput={false}
                              inputtingRow={false}
                              onClick={() => {}}
                        />
                    )
                }
            </div>
        );
    }
}
