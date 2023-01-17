import React from "react";
import PropTypes from "prop-types";
import { BiPlusCircle, BiPlusMedical } from "react-icons/bi";
import "../css/note-style.css";
function NoteContainer(props) {
  return (
    <div className="container-note">
      <div className="intestazione-note">
        <div className="div-intestazione-sx">
          <span className="testo-intestazione-note">Note</span>
        </div>
        <div className="container-icona">
          <BiPlusCircle className="icona-aggiunta-nota" />
        </div>
      </div>
      <div className="singola-nota-container">
        <div className="nota-div">
          <span className="autore-nota">Mario Rossi</span>
          <span className="corpo-nota">
            Ciao dottor Lambiase, ti voglio bene
          </span>
        </div>
        <hr className="linea-tra-note"></hr>

        <div className="nota-div">
          <span className="autore-nota">Mario Rossi</span>
          <span className="corpo-nota">
            Ciao dottor Lambiase, ti voglio bene
          </span>
        </div>
        <hr className="linea-tra-note"></hr>

        <div className="nota-div">
          <span className="autore-nota">Mario Rossi</span>
          <span className="corpo-nota">
            Ciao dottor Lambiase, ti voglio bene
          </span>
        </div>
        <hr className="linea-tra-note"></hr>

        <div className="nota-div">
          <span className="autore-nota">Mario Rossi</span>
          <span className="corpo-nota">
            Ciao dottor Lambiase, ti voglio bene
          </span>
        </div>
        <hr className="linea-tra-note"></hr>
      </div>
    </div>
  );
}

export default NoteContainer;
