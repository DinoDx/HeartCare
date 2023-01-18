import React from "react";
import "../css/style.css";
import "../css/PazientiCss.css";
import ListaPazienti from "../components/ListaPazienti";
import HamburgerMenu from "../components/HamburgerMenu";

function Pazienti() {
  return (
    <div className="contenitorePazientiContent">
        <HamburgerMenu className="HamburgerMenu" nomeClasseMainContent="contenitorePazientiContent"/>
        <div className="searchbar">
            <input id="search" type="text" placeholder=" 🔍Cerca Paziente..." />
        </div>
        <span className="bentornato">Bentornato, Dr. Lambiase 👋🏻</span>
        <span className="iTuoiPazienti">I tuoi pazienti: </span>
        <ListaPazienti />
    </div>
  );
}

export default Pazienti;
