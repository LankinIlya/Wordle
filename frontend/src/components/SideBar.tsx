import React from "react";
import {baseUrl} from "../index";
import {TopsDto, UserRating} from "../types/TopsTypes";
import axios from "axios-typescript";
import {KeyboardButton} from "./KeyboardButton";
import {getFromCookies} from "../utils/getFromCookies";
type SideBarProps = {

};

type SideBarState = {
    tops: TopsDto | null


};

export class SideBar extends React.Component<SideBarProps, SideBarState> {

    constructor(props : SideBarProps) {
        super(props);
        this.state = {tops:null}
        axios.request<TopsDto>({
            url: baseUrl + 'game/get_tops',
            method: "GET",
            withCredentials: true
        }).then((response) => {
            const res = JSON.parse(response.data.toString());
            console.log(res);
            this.setState({tops:res})
        });



    }

    render() {
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
                                    getFromCookies("login") ? "my_rating" : ""}>
                                {el.login}</td>
                            <td className=
                                    {el.login ===
                                    getFromCookies("login") ? "my_rating" : ""}>
                                {el.wins}</td>

                        </tr>))
                    }


                </table>

                <table>
                    <tr>
                        <th>User</th>
                        <th>Games</th>
                    </tr>

                    {this.state.tops.topRatio.map((el: UserRating, j: number) =>
                        (<tr>
                            <td className=
                                    {el.login ===
                                    getFromCookies("login") ? "my_rating" : ""}>
                                {el.login}</td>
                            <td className=
                                    {el.login ===
                                    getFromCookies("login") ? "my_rating" : ""}>
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
