import React from "react";
import LoginForm from "../components/LoginForm";
import imagePath from "../images/LogoHeartCare.png";

function Login() {

    return (
        <div className="contenitoreLogin">
            <img src={imagePath} className="logo"/>
            <LoginForm/>
        </div>
    );
}

export default Login;
