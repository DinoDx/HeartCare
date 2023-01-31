import React, {useState} from "react";
import "../css/cardUtente.css";
import { BiUser } from "react-icons/bi";
import { BsCalendarEvent } from "react-icons/bs";
import { BsGenderFemale, BsGenderMale } from "react-icons/bs";
import { IoCallOutline } from "react-icons/io5";
import { SlArrowDown } from "react-icons/sl";
import {useRef} from 'react';
import 'react-responsive-modal/styles.css';
import { Modal } from 'react-responsive-modal';
import '../css/style.css';
import jwt from "jwt-decode";

function CardAdminMedico(props) {

    const container = useRef();
    const linea = useRef();
    const informazioniUtente = useRef();
    const bottoniUtente = useRef();

    let iconaGenere;
    if (props.genere == 'M') {
        iconaGenere = < BsGenderMale/>;
    } else {
        iconaGenere = <BsGenderFemale/>;
    }

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
        informazioniUtente.current.style.display = "flex";
        bottoniUtente.current.style.display = "flex";
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
        informazioniUtente.current.style.display = "none";
        bottoniUtente.current.style.display = "none";
    }


    const cambiaDimensioneCard = () => {
        // se aperta, chiudila
        if (container.current.style.height == "250px") {
            forzaChiusuraCard();
        } else {  // se chiusa, aprila
            forzaAperturaCard();
        }
    }

    const [open, setOpen] = useState(false);

    const onOpenModal = () => setOpen(true);
    const onCloseModal = () => setOpen(false);
    const token = localStorage.getItem("token");
    const [password, setPassword] = useState("");
    const [emailUtente , setEmail] = useState("");
    const id = jwt(token).id;


    const rimuovi = async () => {
        return await fetch("http://localhost:8080/rimuoviUtente", {
            method: "POST", // *GET, POST, PUT, DELETE, etc.
            mode: "cors", // no-cors, *cors, same-origin
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "same-origin", // include, *same-origin, omit
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
                // 'Content-Type': 'application/x-www-form-urlencoded',
            }, body: JSON.stringify({
                password : password,
                email :props.email,
                id: id
            })
            ,redirect: "follow", // manual, *follow, error
            referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url

        })
    }


    const onChangePassword = (event) => {
        setPassword(event.target.value);
    }


    return(
        <div className="cardUtente" ref={container}>
            <BiUser className="iconaUtente"/>
            <span className="nomeUtente">{props.nomeUtente} {props.cognomeUtente} {props.email}</span>
            <SlArrowDown className="altreInfoUtente"  onClick={mostraInfoAggiuntive}/>
            <hr className="linea-visita"  ref={linea} ></hr>
            <div className="contenitoreInformazioniUtenteCard" ref={informazioniUtente}>
                <div className="informazioneUtente">
                    <BsCalendarEvent/> <span>{props.dataNascita}</span>
                </div>
                <div className="informazioneUtente">
                    {iconaGenere} <span>{props.genere}</span>
                </div>
                <div className="informazioneUtente">
                    <IoCallOutline/> <span>{props.numero}</span>
                </div>
            </div>
            <div className="contenitorePulsantiUtenteCard" ref={bottoniUtente}>
                <Modal open={open} onClose={onCloseModal} >
                    vuoi Eliminare il paziente {(props.email)} ?
                    Inserisci la tua password per confermare
                    <input type="password" placeholder="Password" className="formEditText" onChange={onChangePassword} />
                    <button className="button"  onClick={() => {rimuovi(); setOpen(false)}}> Conferma </button>
                </Modal>
                <button className="button" onClick={onOpenModal} >
                    Elimina
                </button>

            </div>
        </div>
    );
}
export default CardAdminMedico;
