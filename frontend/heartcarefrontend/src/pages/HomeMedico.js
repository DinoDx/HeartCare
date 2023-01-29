import React, { useEffect, useState } from "react";
import VisitaCard from "../components/VisitaCard";
import NoteContainer from "../components/NoteContainer";
import "../css/style.css";
import "../css/home-main-content.css";
import "../css/homeMedico_style.css";
import { useNavigate } from "react-router";
import { Navigate } from "react-router-dom";
import ListaVisita from "../components/ListaVisita";
import jwt from "jwt-decode"

function HomeMedico() {
  //mi occorre:
  /*
  1. sesso
  2. nome
  3. cognome
  4. n note
  5. n visite
  6. n pazienti
  7. tutte le visite
  8. tutte le note
  */
  const [utente, setUtente] = useState([]);
  let nav = useNavigate();
  const token = localStorage.getItem("token");

  let config = {
    Accept: "application/json",
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Headers": "*",
    withCredentials: true,
    Authorization: `Bearer ${token}`,
    "Content-Type" : "application/json"
  };

  const fetchHome = async () => {
    try {
      const response = await fetch("http://localhost:8080/Home/"+jwt(token).id, {
        method : "POST",
        headers : config,
      }).then(response => response.json());
      setUtente(response);
      console.log(utente)
    } catch (error) {
      console.error(error.message);
    }
  };

  useEffect( () => {
    fetchHome();
  }, [])

  useEffect(() => {
    if(utente.length > 0) {
      console.log(utente)
    }
  }, [utente])

  useEffect(() => {
    const eventSource = new EventSource(
      "http://localhost:8080/comunicazione/invioNotifica"
    );
    eventSource.onmessage = (event) => {
      console.log(eventSource.readyState);
      console.log(event.data);
      eventSource.close();
    };
    eventSource.onerror = (error) => {
      console.log("siamo in errore");
      console.error(error.type);
      eventSource.close();
    };
    return () => {
      eventSource.close();
    };
  }, []); 

  return  (
    <div className="contenitoreMainContent-Home">
      <div className="searchbar">
        <input id="search" type="text" placeholder=" 🔍 Cerca paziente..." />
      </div>

      <span className="testo-bentornat">Bentornato, Dr. {utente.cognome}👋🏻</span>

      <div className="full-container">
        <div className="container-sinistra">
          <div className="banner">
            <div className="blocco-testo-banner">
              <span className="testo-banner">Pazienti totali</span>
              <span className="testo-banner-numero">{utente.pazientiTotali}</span>
            </div>

            <div className="blocco-testo-banner">
              <span className="testo-banner">Appuntamenti in programma</span>
              <span className="testo-banner-numero">{utente.appuntamentiInProgramma}</span>
            </div>

            <div className="blocco-testo-banner">
              <span className="testo-banner">Nuove note</span>
              <span className="testo-banner-numero">{utente.numeroNote}</span>
            </div>
          </div>

          <div className="visite-container">
            <span className="visite-in-programma">
              Le prossime visite in programma
            </span>

            <div className="box-visite box-visiteHomeMedico">
              <ListaVisita classe="cardPazienteHomeMedico"/>
            </div>
          </div>
        </div>
        <div className="note-container">
          <NoteContainer />
        </div>
      </div>
    </div>
  );
}

export default HomeMedico;
