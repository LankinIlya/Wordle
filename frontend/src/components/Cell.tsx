import React from "react";

type CellProps = {
    id: number,
    // key: string,
    letter: string | null,
    result: number,
    isWaitingForInput: boolean,
    onClick: () => void,
    inputtingRow: boolean
};

type CellState = {

};

export class Cell extends React.Component<CellProps, CellState> {
    render() {
        return (
            <div className={"cell_container"}>
                {/*<div className={"cell"}></div>*/}
                <div className={"cell"}
                     data-result={this.props.result}
                     data-inputting={this.props.isWaitingForInput}
                     data-inputtingrow={this.props.inputtingRow}
                     onClick={(e) => this.props.onClick()}>
                    <div className={"cell-letter"}>
                        {this.props.letter}
                    </div>
                </div>
            </div>

        );
    }
}
