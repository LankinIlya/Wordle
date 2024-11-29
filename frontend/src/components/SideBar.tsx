import React from "react";
import {baseUrl} from "../index";
import {TopsDto, UserRating} from "../types/TopsTypes";
import axios from "axios-typescript";
import {KeyboardButton} from "./KeyboardButton";
import {getFromCookies} from "../utils/getFromCookies";
type SideBarProps = {
    login: string | undefined,
    gameId: number | undefined,
    finished: boolean | null
};

type SideBarState = {
    tops: TopsDto | null,
    key: string
};

export class SideBar extends React.Component<SideBarProps, SideBarState> {

    constructor(props : SideBarProps) {
        super(props);
        this.state = {
            tops: null,
            key: this.calc_key()
        };
        this.ask();
    }

    ask() {
        this.setState({
            tops: null,
            key: this.calc_key()
        });
        axios.request<TopsDto>({
            url: baseUrl + 'game/get_tops',
            method: "GET",
            withCredentials: true
        }).then((response) => {
            const res = JSON.parse(response.data.toString());
            console.log(res);
            this.setState({
                tops:res,
                key: this.calc_key()
            })
        });
    }

    calc_key() {
        return `sidebar_${
            this.props.login}_${
            this.props.gameId}_${
            this.props.finished}`;
    }

    render() {
        if(this.state.key != this.calc_key()) {
            this.ask();
        }
        if (this.state.tops === null) {
            return (
                <div className={"sidebar"}>Loading...</div>
            );
        }
        return (
            <div className={"sidebar"}>
                <table>
                    <tr>
                        <th>User</th>
                        <th>Wins</th>
                    </tr>

                    {this.state.tops.topWins.map((el: UserRating, j: number) =>
                        (<tr>
                            <td className=
                                    {el.login ===
                                    this.props.login ? "my_rating" : ""}>
                                {el.login}</td>
                            <td className=
                                    {el.login ===
                                    this.props.login ? "my_rating" : ""}>
                                {el.wins}</td>

                        </tr>))
                    }


                </table>

                <table>
                    <tr>
                        <th>User</th>
                        <th>Ratio</th>
                    </tr>

                    {this.state.tops.topRatio.map((el: UserRating, j: number) =>
                        (<tr>
                            <td className=
                                    {el.login ===
                                    this.props.login ? "my_rating" : ""}>
                                {el.login}</td>
                            <td className=
                                    {el.login ===
                                    this.props.login ? "my_rating" : ""}>
                                {(el.games === 0 ? 0 :
                                el.wins / el.games).toFixed(2)}

                                {/*{el.games}*/}
                            </td>
                        </tr>))
                    }


                </table>

            </div>
        );

    }
}
