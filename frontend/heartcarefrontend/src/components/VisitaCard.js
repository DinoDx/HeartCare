import React from "react";
import PropTypes from "prop-types";
import { BiCalendar } from "react-icons/bi";
import { AiOutlineClockCircle } from "react-icons/ai";
import { MdOutlinePlace } from "react-icons/md";

import "../css/visita-style.css";

function VisitaCard(props) {
  return (
    <div className="container-visita">
      <span className="titolo-visita">Visita di controllo</span>
      <hr className="linea-visita"></hr>

      <div className="soggetto-visita-container">
        <span className="nome-soggetto">Mario Rossi</span>
      </div>

      <div className="voce-visita">
        <BiCalendar className="icona-visita-card" />
        <span className="testo-voce-visita">Mercoledì 23 Dicembre</span>
      </div>

      <div className="voce-visita">
        <AiOutlineClockCircle className="icona-visita-card" />
        <span className="testo-voce-visita">10:00 - 12:00</span>
      </div>

      <div className="voce-visita">
        <MdOutlinePlace className="icona-visita-card" />
        <span className="testo-voce-visita">Studio Medico Olivia Gargiulo</span>
      </div>

      <div className="footer-visita">
        <button className="button-visualizza-fascicolo">
          Fascicolo Paziente
        </button>
      </div>
    </div>
  );
}

export default VisitaCard;
