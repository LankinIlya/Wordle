import React from "react";
import {getLoginFromCookies} from "../utils/getLogin";

type RegistrationProps = {

};

type RegistrationState = {

};

export class Registration extends React.Component<RegistrationProps,
                                                RegistrationState> {
    constructor(props: RegistrationProps) {
        super(props);

        this.changePassword = this.changePassword.bind(this);
        this.changeLogin = this.changeLogin.bind(this);
        this.checkPasswordConfirmation =
            this.checkPasswordConfirmation.bind(this);
        this.buttonClick = this.buttonClick.bind(this);
    }
    checkDataValidity() {
        const incorrectLogin = document
        .getElementById("incorrectLogin") as HTMLElement;
        const incorrectPassword = document
        .getElementById("incorrectPassword") as HTMLElement;
        const incorrectConfirm = document
        .getElementById("incorrectConfirm") as HTMLElement;
        const loginInput = document
        .getElementById("username") as HTMLInputElement;
        const passwordInput = document
        .getElementById("password") as HTMLInputElement;
        const confirmPassword = document
        .getElementById("confirmPassword") as HTMLInputElement;

        return (loginInput.value !== "" && passwordInput.value !== ""
        && confirmPassword.value !== "" && incorrectLogin.hidden
        && incorrectPassword.hasAttribute("hidden")
        && incorrectConfirm.hasAttribute("hidden"));
    }
    changeLogin() {
        const incorrectLogin = document
        .getElementById("incorrectLogin") as HTMLElement;
        const loginInput = document.getElementById("username");
        const len = (loginInput as HTMLInputElement).value.length;

        if(len < 3 || len > 20) {
            incorrectLogin.removeAttribute("hidden");
        } else {
            incorrectLogin.setAttribute("hidden", "");
        }
    }
    checkPasswordConfirmation() {
        const passwordInput = document.getElementById("password");
        const confirmPassword = document.getElementById("confirmPassword");
        const incorrectConfirm = document
        .getElementById("incorrectConfirm") as HTMLElement;

        if((passwordInput as HTMLInputElement).value ===
            (confirmPassword as HTMLInputElement).value) {
            incorrectConfirm.setAttribute("hidden", "");
        } else {
            incorrectConfirm.removeAttribute("hidden");
        }
    }
    changePassword() {
        const incorrectPassword = document
        .getElementById("incorrectPassword") as HTMLElement;
        const passwordInput = document.getElementById("password");
        const len = (passwordInput as HTMLInputElement).value.length;

        if(len < 8) {
            incorrectPassword.removeAttribute("hidden");
        } else {
            incorrectPassword.setAttribute("hidden", "");
        }

        this.checkPasswordConfirmation();
    }
    buttonClick() {
        const loginValue = (document
        .getElementById("username") as HTMLInputElement).value;
        if(!this.checkDataValidity()) {
            alert("Data is invalid");
            return;
        }
        fetch('http://localhost:8080/new-user', {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json'
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
                    alert("Successful registration\nCurrent login: "
                    + getLoginFromCookies());
                }
                else {
                    alert("This user already exists\nCurrent login: "
                    + getLoginFromCookies());
                }
              })
    }
    render() {
        return (<div>
                    <input type="text" id="username"
                    placeholder="Логин"
                    onChange={this.changeLogin}/><br />

                    <div id="incorrectLogin" className="errorText"
                    hidden>Логин должен быть от 3 до 20 символов</div>

                    <input type="password" id="password"
                    onChange={this.changePassword} placeholder="Пароль" />
                    <br />

                    <div id="incorrectPassword" className="errorText"
                    hidden>Пароль должен быть от 8 символов</div>

                    <input type="password" id="confirmPassword"
                    onChange={this.checkPasswordConfirmation}
                    placeholder="Подтверждение пароля" /><br />

                    <div id="incorrectConfirm" className="errorText"
                    hidden>Пароли не совпадают</div>

                    <button type="button" onClick={this.buttonClick}
                    id="regButton">Зарегистрироваться</button>
                </div>
        );
    }
}
