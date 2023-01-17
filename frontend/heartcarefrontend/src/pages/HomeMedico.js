import React from "react";
import RouterMedico from "../components/router-menu/RouterMedico";
import MainContent from "../components/MainContent";
import VisitaCard from "../components/VisitaCard";
import NoteContainer from "../components/NoteContainer";
import "../css/style.css";
import "../css/home-main-content.css";
import "../css/homeMedico_style.css";
import logoPath from "../images/LogoHeartCare.png";
import { useNavigate } from "react-router";

function HomeMedico() {
  //let nav = useNavigate();
  return (
    <div className="contenitoreMainContent-Home">
      <div className="header-responsive">
        <img src={logoPath} className="logoMenu-main-content" />
      </div>
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
              Visite in programma oggi:
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
          <div className="note-header-container">
            <span className="note-header-responsive">Note</span>
          </div>
          <NoteContainer />
        </div>
      </div>
    </div>
  );
}

export default HomeMedico;
