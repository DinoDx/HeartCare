import React, {useEffect} from "react";
import '../css/style.css';
import { useState, useRef } from "react";
import axios from "axios";
import { ReactSession }  from 'react-client-session';
import jwt from "jwt-decode"


import {useNavigate} from "react-router";


function LoginForm(){
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const nav = useNavigate();
    const ref = useRef(0);
    
    const rimanda = () => {
        let tokenInizio = localStorage.getItem("token");
        if(tokenInizio == null) {
            return;
        }

        let ruolo = jwt(tokenInizio).ruolo;
        if(ruolo == "PAZIENTE") {
            nav("/HomePaziente")
        }
        else if(ruolo == "MEDICO") {
            nav("/HomeMedico");
        }

    }

    useEffect( () => {
        document.getElementById("spanErrore").style.display = "none";
        rimanda();
    })

    const aggiornaEmail = (event) => {
        setEmail(event.target.value);
    }

    const aggiornaPassword = (event) => {
        setPassword(event.target.value);
    }

    const handleSubmit = (event) => {
        event.preventDefault()

        axios.post('http://localhost:8080/auth/login', {
            email: email,
            password: password
        })
            .then((response) => {
                localStorage.setItem('token', response.data.token);
                if(jwt(response.data.token).ruolo == "PAZIENTE"){
                    nav("/HomePaziente")
                }
                else{
                    nav("/HomeMedico");
                }
            }, (error) => {
                document.getElementById("spanErrore").style.display = "block";
                console.log(error);
            });
    }


    const sendToRegistrazione = () => {
        nav("/Registrazione")
    }

    return (
        <div className="contenitoreForm">
            <input type="text" placeholder=" E-mail" className="formEditText" onChange={aggiornaEmail}/>
            <input type="password" placeholder=" Password" className="formEditText" onChange={aggiornaPassword}/>
            <span className="errore" id="spanErrore" >Controlla i dati inseriti</span>
            <span className="formLink">Ho dimenticato la password</span>
            <button className="formButton" onClick={handleSubmit}>Accedi</button>
            <span onClick={sendToRegistrazione} className="formLink centerFlexItem">Non hai un account? Registrati</span>
        </div>
    );
}
export default LoginForm;