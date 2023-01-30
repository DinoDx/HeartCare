import React from "react";
import "../css/style.css";
import "../css/PazientiCss.css";
import ListaPazienti from "../components/ListaPazienti";
import HamburgerMenu from "../components/HamburgerMenu";
import { Chart } from "react-google-charts";
function Pazienti() {
  return (
    <div className="contenitorePazientiContent">
        <div className="searchbar">
            <input id="search" type="text" placeholder=" 🔍Cerca Paziente..." />
        </div>
        <span className="bentornato">Bentornato, {utente.cognome}  👋🏻</span>
        <span className="iTuoiPazienti">I tuoi pazienti: </span>
        <ListaPazienti />
    </div>
  );
}

export default Pazienti;
