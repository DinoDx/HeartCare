import React from "react";
import "../css/cardPaziente.css";
import { BiUser } from "react-icons/bi";
import { BsCalendarEvent } from "react-icons/bs";
import { BsGenderFemale, BsGenderMale } from "react-icons/bs";
import { IoCallOutline } from "react-icons/io5";
import { SlArrowDown } from "react-icons/sl";
import {useRef} from 'react';

function VisitaCard(props) {

    const container = useRef();
    const linea = useRef();
    const informazioniPaziente = useRef();
    const bottoniPaziente = useRef();

    let iconaGenere = < BsGenderMale/>;

    // flag per capire se deve o meno ri-renderizzare le card
    let stopDoingThis = 0;
    const handleResize = () => {

        if(window.innerWidth > 767 && stopDoingThis == 0){
            forzaAperturaCard();
            stopDoingThis = 1;
        }

        if(window.innerWidth <= 767){
            stopDoingThis = 0;
        }

    }
    React.useEffect(() => {
        window.addEventListener("resize", handleResize, false);
    }, []);


    const mostraInformazioni = () => {
        linea.current.style.display = "flex";
        informazioniPaziente.current.style.display = "flex";
        bottoniPaziente.current.style.display = "flex";
    }
    // apre la card quando
    const forzaAperturaCard = () => {
        mostraInformazioni();
        container.current.style.height = "250px";
        container.current.style.flexDirection = "column";
        container.current.style.justifyContent = "normal";
    }

    const forzaChiusuraCard = () => {
        container.current.style.height = "50px";
        container.current.style.flexDirection = "row";
        container.current.style.justifyContent = "space-between";
    }

    const mostraInfoAggiuntive = () => {

        if(container.current.style.height != "250px"){
            cambiaDimensioneCard();
            mostraInformazioni();
        }else{
            cambiaDimensioneCard();
            nascondiInfoAggiuntive();
            forzaChiusuraCard();
        }
    }

    const nascondiInfoAggiuntive = () =>{
        linea.current.style.display = "none";
        informazioniPaziente.current.style.display = "none";
        bottoniPaziente.current.style.display = "none";
    }


    const cambiaDimensioneCard = () => {
        // se aperta, chiudila
        if (container.current.style.height == "250px") {
            forzaChiusuraCard();
        } else {  // se chiusa, aprila
            forzaAperturaCard();
        }
    }

    return(
        <div className={props.classe} ref={container}>
            <BiUser className="iconaPaziente"/>
            <span className="nomePaziente">{props.nomePaziente} {props.cognomePaziente}</span>
            <SlArrowDown className="altreInfoPaziente"  onClick={mostraInfoAggiuntive}/>
            <hr className="linea-visita"  ref={linea} ></hr>
            <div className="contenitoreInformazioniPazienteCard" ref={informazioniPaziente}>
                <div className="informazionePaziente">
                    <BsCalendarEvent/> <span>{props.dataVisita}</span>
                </div>
                <div className="informazionePaziente">
                    {iconaGenere} <span>{props.genere}</span>
                </div>
                <div className="informazionePaziente">
                    <IoCallOutline/> <span>{props.numero}</span>
                </div>
            </div>

            <Modal open={open} onClose={onCloseModal} center>
                <h2>Fascicolo Sanitario del Paziente {props.nomePaziente} {props.cognomePaziente}</h2>
                <div className="contenitoreMisurazioni">
                    {Categorie.map( cat => {
                        return(
                            <div>
                                <h2>{cat}</h2>
                                <div className="bloccoMisurazione">
                                    {returnByCategoria(cat).map( mis => {
                                        return (
                                            <div className="contenitoreMisurazione">
                                                <div className="contenitoreIcona"><FaNotesMedical className="iconaMisurazione"/></div>
                                                <div className="contenitoreDati">
                                                    {
                                                        Object.entries(mis["misurazione"]).map(chiave => {
                                                                if (chiave[0] != "id") {
                                                                    return(
                                                                        <div>
                                                                            <span className="nomeCampo">{chiave[0]} :</span> <span className="valoreCampo">{chiave[1]}</span>
                                                                        </div>
                                                                    )
                                                                }
                                                            }
                                                        )
                                                    }
                                                </div>
                                            </div>
                                        )
                                    })}
                                </div>
                            </div>
                        )
                    })}
                </div>
            </Modal>

            <div className="contenitorePulsantiPazienteCard" ref={bottoniPaziente}>
                <button className="buttonVisualizzaFascicolo" onClick={onOpenModal}>
                    Fascicolo Paziente
                </button>
            </div>
        </div>
    );
}
export default VisitaCard;
