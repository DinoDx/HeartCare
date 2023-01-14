import React from "react";
import "../css/style.css";
import "../css/PazientiCss.css";
import PazientiMedico from "../components/ListaPazienti";
import ListaPazienti from "../components/ListaPazienti";
import HamburgerMenu from "../components/HamburgerMenu";

function Pazienti() {
  return (
      <div className="contenitorePazientiContent">
          <div className="searchbar">
              <input id="search" type="text" placeholder=" 🔍Cerca Paziente..."/>
          </div>
          <span className="testosaluto">Bentornata,👋</span>

        <ListaPazienti />

      </div>
  );
}

export default Pazienti;
