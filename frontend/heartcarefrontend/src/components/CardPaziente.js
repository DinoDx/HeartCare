import React from "react";
import "../css/cardPaziente.css";
import { BiUser } from "react-icons/bi";
import { BsCalendarEvent } from "react-icons/bs";
import { BsGenderFemale, BsGenderMale } from "react-icons/bs";
import { IoCallOutline } from "react-icons/io5";

function CardPaziente(props) {

    let iconaGenere;
    if (props.genere == 'M') {
        iconaGenere = < BsGenderMale/>;
    } else {
        iconaGenere = <BsGenderFemale/>;
    }

    return(
        <div className="cardPaziente">
            <BiUser className="iconaPaziente"/>
            <span className="nomePaziente">{props.nomePaziente} {props.cognomePaziente}</span>
            <hr className="linea-visita"></hr>
            <div className="contenitoreInformazioniPazienteCard">
                <div className="informazionePaziente">
                    <BsCalendarEvent/> <span>{props.dataNascita}</span>
                </div>
                <div className="informazionePaziente">
                    {iconaGenere} <span>{props.genere}</span>
                </div>
                <div className="informazionePaziente">
                    <IoCallOutline/> <span>{props.numero}</span>
                </div>
            </div>
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
            </div>
        </div>
    );
}
export default CardPaziente;
