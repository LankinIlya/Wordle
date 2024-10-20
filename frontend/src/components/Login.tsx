import React from "react";

type LoginProps = {

};

type LoginState = {

};

export class Login extends React.Component<LoginProps, LoginState> {
    buttonClick() {
        const loginValue = (document.getElementById("username") as HTMLInputElement).value;
        fetch('http://localhost:8080/loginPage', {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json'
              },
              body: JSON.stringify({
                login: loginValue,
                password: (document.getElementById("password") as HTMLInputElement).value
              })
            })
              .then(response => response.text())
              .then(result => {
                if(result === "ok") {
                    document.cookie = loginValue;
                    alert("Successful login\nCurrent login: "
                    + document.cookie);
                }
                else {
                    alert("Wrong login or password\nCurrent login: "
                    + document.cookie);
                }
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
                </div>
        );
    }
}
