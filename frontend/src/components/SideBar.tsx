import React from "react";
import {baseUrl} from "../index";
import {TopsDto} from "../types/TopsTypes";
import axios from "axios-typescript";
type SideBarProps = {

};

type SideBarState = {

};

export class SideBar extends React.Component<SideBarProps, SideBarState> {

    render() {
        axios.request<TopsDto>({
            url: baseUrl + 'game/get_tops',
            method: "GET",
            withCredentials: true
        }).then((response) => {
            const res = JSON.parse(response.data.toString());
            console.log(res);
        });




        return (
            <div className={"sidebar"}>Loading...</div>
        );
    }
}
