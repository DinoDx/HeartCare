import React from "react";
import "../css/cardPaziente.css";
import { BiUser } from "react-icons/bi";
import { FaFileMedicalAlt } from "react-icons/fa";
import { MdOutlineNoteAlt } from "react-icons/md";

function CardPaziente(props) {
    return(
        <div className="cardPaziente">
            <BiUser className="iconaPaziente"/>
            <span className="nomePaziente">{props.nomePaziente} {props.cognomePaziente}</span>
            <hr className="linea-visita"></hr>
            <div className="contenitorePulsantiPazienteCard">
                <button className="buttonVisualizzaFascicolo">
                    Fascicolo Paziente
                </button>
                <button className="buttonVisualizzaFascicolo">
                    Invia una nota
                </button>
                <button className="buttonVisualizzaFascicolo">
                    Aggiungi visita
                </button>
                <button className="buttonVisualizzaFascicolo">
                    Info
                </button>
            </div>
        </div>
    );
}
export default CardPaziente;
