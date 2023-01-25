import React, { useEffect, useState } from "react";
import VisitaCard from "../components/VisitaCard";
import NoteContainer from "../components/NoteContainer";
import "../css/style.css";
import "../css/home-main-content.css";
import "../css/homeMedico_style.css";
import { useNavigate } from "react-router";
import { Navigate } from "react-router-dom";

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
  const [{}] = useState([{}]);

  let nav = useNavigate();
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

  

  return !localStorage.getItem("token") ? (
    <Navigate to={"/Login"} />
  ) : (
    <div className="contenitoreMainContent-Home">
      <div className="searchbar">
        <input id="search" type="text" placeholder=" 🔍 Cerca paziente..." />
      </div>

      <span className="testo-bentornat">Bentornato, Dr. Lambiase 👋🏻</span>

      <div className="full-container">
        <div className="container-sinistra">
          <div className="banner">
            <div className="blocco-testo-banner">
              <span className="testo-banner">Pazienti totali</span>
              <span className="testo-banner-numero">120</span>
            </div>

            <div className="blocco-testo-banner">
              <span className="testo-banner">Appuntamenti in programma</span>
              <span className="testo-banner-numero">12</span>
            </div>

            <div className="blocco-testo-banner">
              <span className="testo-banner">Nuove note</span>
              <span className="testo-banner-numero">21</span>
            </div>
          </div>

          <div className="visite-container">
            <span className="visite-in-programma">
              Le prossime visite in programma
            </span>

            <div className="box-visite">
              <VisitaCard nome="Gianluigi Buffon" />
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
