import React, {useEffect} from "react";
import '../css/style.css';
import { useState } from "react";
import axios from "axios";
import { ReactSession }  from 'react-client-session';

import {useNavigate} from "react-router";


function LoginForm(){
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const nav = useNavigate();

    const [user, setUser] = useState();


    const aggiornaEmail = (event) => {
        setEmail(event.target.value);
    }

    const aggiornaPassword = (event) => {
        setPassword(event.target.value);
    }

    const handleSubmit = (event) => {
        event.preventDefault()


        axios.post('http://localhost:8080/login', {
            email: email,
            password: password
        })
            .then((response) => {
                console.log(ReactSession.set("utenteLoggato"));
                console.log(response);
                localStorage.setItem('utente', JSON.stringify(response.data));
                console.log(localStorage.getItem("utente"));
                localStorage.setItem('items', JSON.stringify(user));
                nav("/HomeMedico");
            }, (error) => {
                console.log(error);
            });
    }


    return (
        <div className="contenitoreForm">
            <input type="text" placeholder=" E-mail" className="formEditText" onChange={aggiornaEmail}/>
            <input type="password" placeholder=" Password" className="formEditText" onChange={aggiornaPassword}/>
            <span className="formLink">Ho dimenticato la password</span>
            <button className="formButton" onClick={handleSubmit}>Accedi</button>
            <span className="formLink centerFlexItem">Non hai un account? Registrati</span>
            <a href = "/Registrazione">Non hai un account? Registrati</a>
        </div>
    );
}
export default LoginForm;