import React, {useEffect, useState} from "react";
import "../css/style.css";
import "../css/home-main-content.css";
import "../css/homeMedico_style.css";
import "../css/HomePaziente.css";
import { Navigate, useNavigate } from "react-router-dom";
import {Chart} from "react-google-charts";
import NoteContainer from "../components/NoteContainer";
import VisitePazienteContainer from "../components/VisitePazienteContainer";
import Grafico from "../components/Grafico";
import jwt from "jwt-decode"
import {BiPlusCircle} from "react-icons/bi";
import {Modal} from "react-responsive-modal";
import CardDispositivo from "../components/CardDispositivo";
import { CiMedicalCase } from "react-icons/ci";
import NoteContainerPaziente from "../components/NoteContainerPaziente";

function HomePaziente() {

    const [Categorie,setCategorie] = useState([]);
    const [Misurazioni,setMisurazioni] = useState([]);
    const token = localStorage.getItem("token");
    let config = {
        Accept: "application/json",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
        withCredentials: true,
        Authorization: `Bearer ${token}`,
        "Content-Type" : "application/json"
    };

    useEffect(() => {
        const getCategorie = async () =>{
            const response = await fetch("http://localhost:8080/getCategorie",{
                method : "POST",
                headers : config,
                body : JSON.stringify({
                    id : 1
                })
            }).then(response => {
                return response.json()
            })
            setCategorie(response);
        }

        const fetchData = async() => {
            const response = await fetch("http://localhost:8080/getAllMisurazioniByPaziente",{
                method : "POST",
                headers : config,
                body : JSON.stringify({
                    id: 1
                })
            }).then(response => {
                return response.json()
            })
            setMisurazioni(response);
        }
        getCategorie();
        fetchData();
    }, []);


    const [dispositivi, setDispositivi] = useState([]);

    //const [loading, setLoading] = useState(false);

    const aggiornaDispositivi = (disp) => {
        setDispositivi(disp);
    }

    let dispositivoIniziale;

    const fetchDispositivi = async () => {
        if(token == null) {
            return;
        }
        const idPaziente = jwt(token)["id"];
        return await fetch("http://localhost:8080/getDispositiviByUtente/"+idPaziente, {
            method: "POST",
            mode: "cors",
            cache: "no-cache",
            credentials: "same-origin",
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
            },
            redirect: "follow",
            referrerPolicy: "no-referrer",
        }).then((response) => {
            return response.json()
        }).then(data => {
            setDispositivi(data)
        });
    }

    const [dispositivoMisurazione, setDispositivoMisurazione] = useState();

    useEffect( () => {
        fetchDispositivi();
    }, [])


    const [open, setOpen] = useState(false);
    const [openRisultati, setOpenModalRisultati] = useState(false);

    const onOpenModal = () => setOpen(true);
    const onOpenModalRisultati = () => setOpenModalRisultati(true);
    const onCloseModal = () => setOpen(false);
    const onCloseModalRisultati = () => setOpenModalRisultati(false);

    const aggiornaDispositivoMisurazione = (event) => {
        setDispositivoMisurazione(event.target.value);
    }
    const avviaMisurazione = () =>{
        // fare la fetch per inviare dati
        console.log("stai avviando la misurazione con il dispositivo: "+dispositivoMisurazione)

    }

    return !localStorage.getItem("token") ? (
        <Navigate to={"/Login"} />
    ) : (
        <div className="contenitorePazientiContent contenitoreHomePaziente">
            <span className="testo-bentornat">Bentornato, Gianluigi Buffon👋🏻</span>

            <Modal open={open} onClose={onCloseModal} center>
                <h2>Scegli il dispositivo da cui avviare la misurazione</h2>
                <select className="selectDispositivo" onChange={(e) => aggiornaDispositivoMisurazione(e)} id="selectDispositivi">
                    <option value="" disabled selected>I tuoi dispositivi</option>
                    {Object.keys(dispositivi).map(function(num, index){
                        return (
                            <option value={dispositivi[num]["id"]}>{dispositivi[num]["categoria"]}</option>
                        )
                    })}
                </select>
                <br/>
                <button className="bottoneInviaNota" onClick={() => {onOpenModalRisultati(); onCloseModal(); avviaMisurazione();}}>Avvia Misurazione</button>
            </Modal>

            <Modal open={openRisultati} onClose={onCloseModalRisultati} center>
                <h2>Risultati della tua misurazione:</h2>
            </Modal>


            <div className="contenitoreSinistra">
                <div className="bannerNero">
                    <div className="informazioneBannerNero">
                        <span>Misurazioni effettuate</span>
                        <span className="valoreInformazioneBannerNero">120</span>
                    </div>
                    <div className="informazioneBannerNero">
                        <span>Note non lette</span>
                        <span className="valoreInformazioneBannerNero">1</span>
                    </div>
                    <div className="informazioneBannerNero">
                        <span>Visite in programma</span>
                        <span className="valoreInformazioneBannerNero">2</span>
                    </div>
                </div>
                <Grafico categorie={Categorie} misurazioni = {Misurazioni}/>
                <div className="containerBottoni">
                    <button className="bottoneHomePaziente" onClick={onOpenModal} >Avvio Misurazione</button>
                    <button className="bottoneHomePaziente">Avvio Predizione</button>
                </div>

            </div>
            <div className="contenitoreDestra">
                <NoteContainerPaziente/>
                <VisitePazienteContainer/>
            </div>
        </div>
    );
}

export default HomePaziente;
