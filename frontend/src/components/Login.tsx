import React from "react";
import {getLoginFromCookies} from "../utils/getLogin";

type LoginProps = {

};

type LoginState = {

};

export class Login extends React.Component<LoginProps, LoginState> {
    constructor(props: LoginProps) {
        super(props);
        this.buttonClick = this.buttonClick.bind(this);
    }

    buttonClick() {
        const loginValue = (document
        .getElementById("username") as HTMLInputElement).value;

        fetch('http://localhost:8080/loginPage', {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json',
              },
              body: JSON.stringify({
                login: loginValue,
                password: (document
                .getElementById("password") as HTMLInputElement).value
              })
            })
              .then(response => response.text())
              .then(result => {
                if(result !== "not ok") {
                    document.cookie = "login=" + loginValue;
                    document.cookie = "jwt=" + result;
                    alert("Successful login\nCurrent login: "
                    + getLoginFromCookies());
                }
                else {
                    alert("Wrong login or password\nCurrent login: "
                    + getLoginFromCookies());
                }
              })
    }

    checkLogin() {
        fetch("http://localhost:8080/checkLogin", {
                    method: "POST",
                    credentials: 'include',
                    headers: {
                        'Cookie': document.cookie
                    }
                })
                .then((response: Response) => response.text())
                .then((result: string) => {
                                    alert(result);
                })
    }

    render() {
        return (<div>
                    <input type="text" id="username"
                    name="username" placeholder="Логин" /><br />

                    <input type="password" id="password" name="password"
                    placeholder="Пароль" /><br />

                    <button type="button" onClick={this.buttonClick}
                    id="regButton">Войти</button>

                    <button onClick={this.checkLogin}>check login</button>
                </div>
        );
    }
}
