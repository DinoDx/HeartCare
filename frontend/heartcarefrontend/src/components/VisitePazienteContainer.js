import React from "react";
import PropTypes from "prop-types";
import { BiPlusCircle, BiPlusMedical } from "react-icons/bi";
import "../css/note-style.css";
function NoteContainer(props) {
  return (
    <div className="container-note marginSotto">
      <div className="intestazione-note">
        <div className="div-intestazione-sx">
          <span className="testo-intestazione-note">Visite</span>
        </div>
      </div>
      <div className="singola-nota-container">
        <div className="nota-div">
          <span className="autore-nota">Visita di controllo</span>
          <span className="corpo-nota">
            Ruggi d'Aragona, 18:00, 25-11-2022
          </span>
        </div>
        <hr className="linea-tra-note"></hr>

        <div className="nota-div">
          <span className="autore-nota">Visita di controllo</span>
          <span className="corpo-nota">
            Ruggi d'Aragona, 18:00, 25-11-2022
          </span>
        </div>
        <hr className="linea-tra-note"></hr>

      </div>
    </div>
  );
}

export default NoteContainer;
