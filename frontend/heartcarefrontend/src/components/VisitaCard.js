import React from "react";
import { useRef } from "react";
import PropTypes from "prop-types";
import { BiCalendar } from "react-icons/bi";
import { AiOutlineClockCircle } from "react-icons/ai";
import { SlArrowDown, SlEnvelopeOpen } from "react-icons/sl";
import { MdOutlinePlace } from "react-icons/md";

import "../css/visita-style.css";

function VisitaCard(props) {
  const nome = props.nome;
  const containerVisita = useRef();
  const infoVisita = useRef();
  const bottoneFascicolo = useRef();

  return (
    <div className="container-visita" ref={containerVisita}>
      <span className="titolo-visita">Visita di controllo</span>
      <hr className="linea-visita"></hr>

      <div className="soggetto-visita-container">
        <span className="nome-soggetto">{nome}</span>
        <div className="container-ottieni-info">
          <SlArrowDown className="ottieni-info" />
        </div>
      </div>

      <div className="infoVisita" ref={infoVisita}>
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
          <span className="testo-voce-visita">
            Studio Medico Olivia Gargiulo
          </span>
        </div>
      </div>

      <div className="footer-visita" ref={bottoneFascicolo}>
        <button className="button-visualizza-fascicolo">
          Fascicolo Paziente
        </button>
      </div>
    </div>
  );
}

export default VisitaCard;
