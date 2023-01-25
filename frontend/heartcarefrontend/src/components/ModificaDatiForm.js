import React, { useEffect } from "react";
import "../css/styleModificaDatiForm.css";
import { useState } from "react";
import axios from "axios";
import { ReactSession } from "react-client-session";
import { getDefaultNormalizer } from "@testing-library/react";

function ModificaDatiForm() {
  const [loading, setLoading] = useState(true);
  const [data, setData] = useState([]);
  const token = localStorage.getItem("token");

  let config = {
    Accept: "application/json",
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Headers": "*",
    withCredentials: true,
    Authorization: `Bearer ${token}`,
  };
  const getDati = async () => {
    return await fetch("http://localhost:8080/utente/1", {
      method: "POST", // *GET, POST, PUT, DELETE, etc.
      mode: "cors", // no-cors, *cors, same-origin
      cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
      credentials: "same-origin", // include, *same-origin, omit
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      redirect: "follow", // manual, *follow, error
      referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
      //body: JSON.stringify(data), // body data type must match "Content-Type" header
    }).then((response) => response.json());
  };
  useEffect(() => {
    getDati().then((data) => console.log(data));
  });

  const [nome, setNome] = useState("");
  const [cognome, setCognome] = useState("");
  const [nTelefono, setNtelefono] = useState("");
  const [password, setPassword] = useState("");
  const [confermaPassword, setConfermaPassword] = useState("");
  const [nomeCaregiver, setNomeCaregiver] = useState("");
  const [cognomeCaregiver, setCognomeCaregiver] = useState("");
  const [emailCaregiver, setEmailCaregiver] = useState("");

  const aggiornaNome = (event) => {
    setNome(event.target.value);
  };

  const aggiornaCognome = (event) => {
    setCognome(event.target.value);
  };

  const aggiornaNtelefono = (event) => {
    setNtelefono(event.target.value);
  };

  const aggiornaPassword = (event) => {
    setPassword(event.target.value);
  };

  const aggiornaConfermaPassword = (event) => {
    setConfermaPassword(event.target.value);
  };

  const aggiornaNomeCaregiver = (event) => {
    setNomeCaregiver(event.target.value);
  };

  const aggiornaCognomeCaregiver = (event) => {
    setCognomeCaregiver(event.target.value);
  };

  const aggiornaEmailCaregiver = (event) => {
    setEmailCaregiver(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    axios
      .post("http://localhost:8080/modificaDatiUtente", {
        headers: {
          Authorization: token,
        },
        body: {
          nome: nome,
          cognome: cognome,
          password: password,
          numeroTelefono: nTelefono,
          confermaPassword: confermaPassword,
          nomeCaregiver: nomeCaregiver,
          cognomeCaregiver: cognomeCaregiver,
          emailCaregiver: emailCaregiver,
        },
      })
      .then(
        (response) => {
          console.log(ReactSession.set("utenteLoggato"));
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      );
  };

  return (
    <div className="contenitoreForm">
      <input
        type="text"
        placeholder={data.nome}
        className="formEditText"
        onChange={aggiornaNome}
      />
      <input
        type="text"
        placeholder={data.cognome}
        className="formEditText"
        onChange={aggiornaCognome}
      />
      <input
        type="text"
        placeholder={data.nTelefono}
        className="formEditText"
        onChange={aggiornaNtelefono}
      />
      <input
        type="password"
        placeholder={data.password}
        className="formEditText"
        onChange={aggiornaConfermaPassword}
      />
      <input
        type="password"
        placeholder=" Password"
        className="formEditText"
        onChange={aggiornaPassword}
      />
      <input
        type="text"
        placeholder="Nome Caregiver"
        value={data.nomeCaregiver}
        className="formEditText"
        onChange={aggiornaNomeCaregiver}
      />
      <input
        type="text"
        placeholder="Cognome Caregiver"
        value={data.cognomeCaregiver}
        className="formEditText"
        onChange={aggiornaCognomeCaregiver}
      />
      <input
        type="text"
        placeholder="Email Caregiver"
        value={data.emailCaregiver}
        className="formEditText"
        onChange={aggiornaEmailCaregiver}
      />
      <button className="formButton" onClick={handleSubmit}>
        Salva
      </button>
    </div>
  );
}

export default ModificaDatiForm;
