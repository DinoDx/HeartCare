import React from "react";
import '../css/style.css';
import { useState } from "react";
import axios from "axios";
import { ReactSession }  from 'react-client-session';




function RegistrazioneForm(){
    const [nome,setNome] = useState("");
    const [cognome, setCognome] = useState("");
    const [nTelefono, setNtelefono] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [confermaPassword, setConfermaPassword] = useState("");
    const [genere, setGenere] = useState("");
    const [dataNascita, setDataNascita] = useState("");
    const [codiceFiscale, setCodiceFiscale] = useState("");

    const aggiornaNome = (event) => {
        setNome(event.target.value);
    }

    const aggiornaCognome = (event) => {
        setCognome(event.target.value);
    }

    const aggiornaEmail = (event) => {
        setEmail(event.target.value);
    }

    const aggiornaNtelefono = (event) => {
        setNtelefono(event.target.value);
    }

    const aggiornaPassword = (event) => {
        setPassword(event.target.value);
    }

    const aggiornaConfermaPassword = (event) => {
        setConfermaPassword(event.target.value);
    }

    const aggiornaGenere = (event) => {
        setGenere(event.target.value);
    }

    const aggiornaDataNascita = (event) => {
        setDataNascita(event.target.value);
    }

    const aggiornaCodiceFiscale = (event) => {
        setCodiceFiscale(event.target.value);
    }
    


    const handleSubmit = (event) => {
        event.preventDefault()

        axios.post('http://localhost:8080/registrazione', {
            nome: nome,
            cognome: cognome,
            password: password,
            email:email,
            numeroTelefono: nTelefono,
            confermaPassword: confermaPassword,
            dataDiNascita: dataNascita,
            genere:genere,
            codiceFiscale: codiceFiscale
        })
            .then((response) => {
                console.log(ReactSession.set("utenteLoggato"));
                console.log(response);
            }, (error) => {
                console.log(error);
            });
    }


    return (
        <div className="contenitoreForm">
            <input type="text" placeholder="Mario" className="formEditText" onChange={aggiornaNome}/>
            <input type="text" placeholder="Rossi" className="formEditText" onChange={aggiornaCognome}/>
            <input type="text" placeholder="333333333" className="formEditText" onChange={aggiornaNtelefono}/>
            <input type="text" placeholder="M|F" className="formEditText" onChange={aggiornaGenere}/>
            <input type="date" placeholder="Data Nascita" className="formEditText" onChange={aggiornaDataNascita}/>
            <input type="text" placeholder="Codice Fiscale" className="formEditText" onChange={aggiornaCodiceFiscale}/>
            <input type="text" placeholder="email@example.ti" className="formEditText" onChange={aggiornaEmail}/>
            <input type="password" placeholder="Password" className="formEditText" onChange={aggiornaPassword}/>
            <input type="password" placeholder=" Password" className="formEditText" onChange={aggiornaConfermaPassword}/>
            <button className="formButton" onClick={handleSubmit}>Registrati</button>
            
        </div>
    );
}
export default RegistrazioneForm;