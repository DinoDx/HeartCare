import React from "react";
import RouterMedico from "../components/router-menu/RouterMedico";
import MainContent from "../components/MainContent";
import VisitaCard from "../components/VisitaCard";
import "../css/style.css";
import "../css/style_mainContent.css";
import "../css/homeMedico_style.css";
import { useNavigate } from "react-router";

function Home() {
  //let nav = useNavigate();
  return (
    <div className="contenitoreMainContent">
      <div className="searchbar">
        <input id="search" type="text" placeholder=" 🔍 Cerca paziente..." />
      </div>

      <span className="testo-bentornat">Bentornato, Dr. Lambiase 👋🏻</span>

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
        </div>
      </div>
    </div>
  );
}

export default Home;
