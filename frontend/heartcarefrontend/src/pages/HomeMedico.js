import React from "react";
import RouterMedico from "../components/router-menu/RouterMedico";
import MainContent from "../components/MainContent";
import VisitaCard from "../components/VisitaCard";
import "../css/style.css";
import "../css/home-main-content.css";
import "../css/homeMedico_style.css";
import { useNavigate } from "react-router";

function HomeMedico() {
  //let nav = useNavigate();
  return (
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
              <VisitaCard />
              <VisitaCard />
              <VisitaCard />
              <VisitaCard />
              <VisitaCard />
            </div>
          </div>
        </div>
        <div className="note-container">
          <VisitaCard />
        </div>
      </div>
    </div>
  );
}

export default HomeMedico;
