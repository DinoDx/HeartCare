import React, { useEffect } from "react";
import '../css/styleModificaDatiForm.css';
import { useState } from "react";
import axios from "axios";
import { ReactSession }  from 'react-client-session';


function ModificaDatiForm(){
    const [loading, setLoading] = useState(true);
    const [data, setData] = useState([])
  
    useEffect(() => {
      const fetchData = async () =>{
        setLoading(true);
        try {
          const {data: response} = await axios.post('http://localhost:8080/utente/7');
          console.log(data.content)
          setData(response);
          
        } catch (error) {
          console.error(error.message);
        }
        setLoading(false);
      }
      fetchData();
    }, []);

    const [nome,setNome] = useState("");
    const [cognome, setCognome] = useState("");
    const [nTelefono, setNtelefono] = useState("");
    const [password, setPassword] = useState("");
    const [confermaPassword, setConfermaPassword] = useState("");
    const [nomeCaregiver, setNomeCaregiver] = useState("");
    const [cognomeCaregiver, setCognomeCaregiver] = useState("");
    const [emailCaregiver, setEmailCaregiver] = useState("");


    const aggiornaNome = (event) => {
        setNome(event.target.value);
    }

    const aggiornaCognome = (event) => {
        setCognome(event.target.value);
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

    const aggiornaNomeCaregiver = (event) => {
        setNomeCaregiver(event.target.value);
    }

    const aggiornaCognomeCaregiver = (event) => {
        setCognomeCaregiver(event.target.value);
    }

    const aggiornaEmailCaregiver = (event) => {
        setEmailCaregiver(event.target.value);
    }

    const handleSubmit = (event) => {
        event.preventDefault()


        axios.post('http://localhost:8080/modificaDatiUtente', {
            nome: nome,
            cognome: cognome,
            password: password,
            numeroTelefono: nTelefono,
            confermaPassword: confermaPassword,
            nomeCaregiver: nomeCaregiver,
            cognomeCaregiver: cognomeCaregiver,
            emailCaregiver: emailCaregiver
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
            <input type="text" placeholder={data.nome} className="formEditText" onChange={aggiornaNome}/>
            <input type="text" placeholder={data.cognome} className="formEditText" onChange={aggiornaCognome}/>
            <input type="text" placeholder={data.nTelefono} className="formEditText" onChange={aggiornaNtelefono}/>
            <input type="password" placeholder={data.password} className="formEditText" onChange={aggiornaConfermaPassword}/>
            <input type="password" placeholder=" Password" className="formEditText" onChange={aggiornaPassword}/>
            <input type="text" placeholder="Nome Caregiver" value={data.nomeCaregiver} className="formEditText" onChange={aggiornaNomeCaregiver}/>
            <input type="text" placeholder="Cognome Caregiver"  value={data.cognomeCaregiver} className="formEditText" onChange={aggiornaCognomeCaregiver}/>
            <input type="text" placeholder="Email Caregiver" value={data.emailCaregiver} className="formEditText" onChange={aggiornaEmailCaregiver}/>
            <button className="formButton" onClick={handleSubmit}>Salva</button>
        </div>
    );
}

export default ModificaDatiForm;