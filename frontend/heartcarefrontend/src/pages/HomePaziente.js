
import React, { useCallback, useEffect, useState, useRef, useUpdateEffect } from "react";
import "../css/style.css";
import "../css/home-main-content.css";
import "../css/homeMedico_style.css";
import "../css/HomePaziente.css";
import { Navigate, useNavigate } from "react-router-dom";
import { Chart } from "react-google-charts";
import NoteContainer from "../components/NoteContainer";
import VisitePazienteContainer from "../components/VisitePazienteContainer";
import SockJS from 'sockjs-client';
import { Stomp } from "@stomp/stompjs"
import addNotification from 'react-push-notification';
import Grafico from "../components/Grafico";
import jwt from "jwt-decode"
import { BiPlusCircle } from "react-icons/bi";
import { Modal } from "react-responsive-modal";
import CardDispositivo from "../components/CardDispositivo";
import { CiMedicalCase } from "react-icons/ci";
import NoteContainerPaziente from "../components/NoteContainerPaziente";
import gifPath from "../images/Bars-1s-200px.gif";

function HomePaziente() {

    const [Categorie, setCategorie] = useState([]);
    const [Misurazioni, setMisurazioni] = useState([]);
    const token = localStorage.getItem("token");
    const idPaziente = jwt(token)["id"];
    let config = {
        Accept: "application/json",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
        withCredentials: true,
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json"
    };

    useEffect(() => {
        const getCategorie = async () => {
            const response = await fetch("http://localhost:8080/getCategorie", {
                method: "POST",
                headers: config,
                body: JSON.stringify({
                    id: 1
                })
            }).then(response => {
                return response.json()
            })
            setCategorie(response);
        }

        const fetchData = async () => {
            const response = await fetch("http://localhost:8080/getAllMisurazioniByPaziente", {
                method: "POST",
                headers: config,
                body: JSON.stringify({
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



    const url = "http://localhost:8080/ws"
    const [stompClient, setStompClient] = useState(null);
    const [notifications, setNotifications] = useState("");

  
    const [aggiorna, setAggiorna] = useState(0);
    const isFirstRun = useRef(true);

    
    const fetchData = ()=>{

    const socket = new SockJS(url);
    const client = Stomp.over(socket);
        
        client.connect({}, (frame) => {
            console.log("ma perche");
            setStompClient(client);
            client.subscribe('/topic/notifica', (response) => {
                response = JSON.parse(response.body);
                if (response.idPaziente == idPaziente) {
                    console.log(response.messagio,"OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                    setNotifications((response.messagio));
                    setAggiorna(aggiorna+1);
                    console.log("notifica = ", response.messagio)                
                }
            });
        });
    
    };

    const speriamo = ()=>{addNotification({
        title: 'Notifica',
        subtitle: 'This is a subtitle',
        message: notifications,
        theme: 'red',
        native: true // when using native, your OS will handle theming.
    })
    }

    useEffect(()=>{
    if (isFirstRun.current) {
        console.log("merdosoooooooooooooooooooooooooooooooooooo")
      isFirstRun.current = false;
      return;
    }
            fetchData();
            console.log("CAZZONIIIIIIIIIIIIIIIIIII            ")
            speriamo()
        
    },[aggiorna]);



    const [dispositivi, setDispositivi] = useState([]);

    //const [loading, setLoading] = useState(false);

    const aggiornaDispositivi = (disp) => {
        setDispositivi(disp);
    }

    let dispositivoIniziale;

    const fetchDispositivi = async () => {
        if (token == null) {
            return;
        }

        return await fetch("http://localhost:8080/getDispositiviByUtente/" + idPaziente, {
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

    useEffect(() => {
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
        console.log(dispositivoMisurazione);
    }

    const [datiMisurazione, setDatiMisurazione] = useState({});
    const avviaMisurazione = async () => {
        // fare la fetch per inviare dati
        
        return await fetch("http://localhost:8080/avvioMisurazione", {
            method: "POST",// *GET, POST, PUT, DELETE, etc.
            mode: "cors", // no-cors, *cors, same-origin
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "same-origin", // include, *same-origin, omit
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                idDispositivo: dispositivoMisurazione
            }),
            withCredentials: true,
            redirect: "follow", // manual, *follow, error
            referrerPolicy: "no-referrer",
        }).then(async response => {
            response = await response.json();
            setDatiMisurazione(response);
        })
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
                    {Object.keys(dispositivi).map(function (num, index) {
                        return (
                            <option value={dispositivi[num]["id"]}>{dispositivi[num]["categoria"]}</option>
                        )
                    })}
                </select>
                <br />
                <button className="bottoneInviaNota" onClick={() => { onOpenModalRisultati(); onCloseModal(); avviaMisurazione(); }}>Avvia Misurazione</button>
            </Modal>

            <Modal open={openRisultati} onClose={()=> {onCloseModalRisultati()}} center>
                <h2>Risultati della tua misurazione:</h2>
                <div className="contenitoreDatiMisurazione">
                    <img src={gifPath} id="img" />
                    <div id="dati" style={{ display: "none" }}>
                        {Object.entries(datiMisurazione).map((dato) => {
                            // dato[0] contiene il nome della categoria, dato[1] il valore
                            const result = dato[0].replace(/([A-Z])/g, " $1");
                            const finalResult = result.charAt(0).toUpperCase() + result.slice(1);
                            if (finalResult != "Id")
                                return (
                                    <div className="singoloCampo">
                                        <span className="nomeValoreMisurazione">{finalResult}: </span>
                                        <span>{dato[1]}</span>
                                    </div>
                                )
                        })}
                    </div>
                    <span style={{ display: "none" }}>
                        {

                            setTimeout(() => {
                                document.getElementById("img").style.display = "none";
                                document.getElementById("dati").style.display = "block";
                            }, 2000)
                        }
                    </span>
                </div>
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
                <Grafico categorie={Categorie} misurazioni={Misurazioni} />
                <div className="containerBottoni">
                    <button className="bottoneHomePaziente" onClick={() => {onOpenModal();fetchData()}} >Avvio Misurazione</button>
                    <button className="bottoneHomePaziente">Avvio Predizione</button>
                </div>

            </div>
            <div className="contenitoreDestra">
                <NoteContainerPaziente />
                <VisitePazienteContainer />
            </div>
        </div>
    );
}

export default HomePaziente;
