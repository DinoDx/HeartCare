import React from "react";
import { CiMedicalCase } from "react-icons/ci";

import "../css/dispositivo-style.css";

function CardDispositivo(props) {
  return (
    <div className="container-visita">
      <span className="titolo-visita">Sfigmomanometro</span>
      <hr className="linea-visita"></hr>
      <div className="container-icona-dispositivo">
        <CiMedicalCase className="icona-dispositivo-card" />
      </div>
      <div className="container-descrizione-dispositivo">
        <div className="testo-descrizione-dispositivo">
          Dispositivo medico per analizzare la quantità di ossigeno nel sangue.
        </div>
      </div>
      <div className="footer-visita">
        <button className="button-visualizza-fascicolo">
          Avvio Monitoraggio
        </button>
      </div>
    </div>
  );
}

export default CardDispositivo;
