import React, {useEffect, useState} from "react";
import HamburgerMenu from "../components/HamburgerMenu";
import ListaVisita from "../components/ListaVisita";
import "../css/scheduleStyle.css";
import Modal from "react-responsive-modal";
import { BiPlusCircle, BiPlusMedical } from "react-icons/bi";
import jwt from "jwt-decode";
import pathGif from "../images/Ripple-1s-200px_1.gif";
import { FaRegHospital } from "react-icons/fa";

function Schedules() {
  const [open, setOpen] = useState(false);
  const [openRiepilogoVisita, setOpenRiepilogoVisita] = useState(false);
  const [pazienti,setPazienti] = useState([{}]);
  const [indirizzi,setIndirizzi] = useState([{}]);
  const [utente,setUtente] = useState({});
  const [pazienteSelect,setPazienteSelect] = useState();
  const [indirizzoSelect,setindirizzoSelect] = useState();
  const [dataSelect,setDataSelect] = useState();
  const onOpenModal = () => setOpen(true);
  const onCloseModal = () => setOpen(false);
  const onOpenModalRiepilogo = () => setOpenRiepilogoVisita(true);
  const onCloseModalRiepilogo = () => {
    setOpenRiepilogoVisita(false)
    document.location.reload();
  };
  const token = localStorage.getItem("token");

  let config = {
    Accept: "application/json",
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Headers": "*",
    withCredentials: true,
    Authorization: `Bearer ${token}`,
    "Content-Type" : "application/json"
  };

  useEffect( () => {
    const fetchPazienti = async () => {
      try {
        const response = await fetch("http://localhost:8080/getPazientiByMedico/" + jwt(token).id, {
          method: "GET",
          headers: config,
        }).then(response => response.json());
        setPazienti(response)
      } catch (error) {
        console.error(error.message);
      }
    }
    const fetchIndirizzi = async () => {
      try {
        const response = await fetch("http://localhost:8080/getIndirizzi", {
          method: "GET",
          headers: config,
        }).then(response => response.json());
        setIndirizzi(response)
      } catch (error) {
        console.error(error.message);
      }
    }
    const fetchHome = async () => {
      try {
        const response = await fetch("http://localhost:8080/Home/"+jwt(token).id, {
          method : "POST",
          headers : config,
        }).then(response => response.json());
        setUtente(response);

      } catch (error) {
        console.error(error.message);
      }
    };
    fetchPazienti();
    fetchIndirizzi();
    fetchHome();
  },[])

  useEffect(() => {
    if(utente.length > 0) {

    }
  }, [utente])

  const aggiornaPazienteSelect = (event) => {
    setPazienteSelect(event.target.value);
  }

  const aggiornaIndirizzoSelect = (event) => {
    setindirizzoSelect(event.target.value);
  }

  const aggiornaDataSelect = (event) => {
    setDataSelect(event.target.value);
  }

  const handlerOnClick = async() =>{
    try{
      const response = await fetch("http://localhost:8080/visite/crea", {
        method : "POST",
        headers : config,
        body : JSON.stringify({
          paziente : pazienteSelect,
          data : dataSelect,
          indirizzo : indirizzoSelect
        })
      }).then(response => response.json());
      setUtente(response);

    }catch (error) {
      console.error(error.message);
    }
  }

  return (
      <div className="contenitoreScheduleContent">
        {
          (utente["sesso"] === "M") ? <span className="bentornato">Bentornato, Dr. {utente["cognome"]} 👋🏻</span> : <span className="bentornato">Bentornata, Drs. {utente["cognome"]} 👋🏻</span>
        }
        <span className="iTuoiPazienti">Le tue visite in programma: </span>
        <button onClick={onOpenModal} className="bottoneAggiungiVisita"> + </button>
        <Modal open={open} onClose={onCloseModal}>
          <h2>Programma una nuova visita</h2>
          <div className="containerInputAggiungiVisita">

            <div className="containerSingoloInputAggiungiVisita">
              <span className="labelInputAggiungiVisita">Scegli il paziente</span>
              <select onChange={ e => aggiornaPazienteSelect(e)}  className="selectPaziente">
                <option value="" disabled selected>Scegli paziente</option>
                {pazienti.map( paz => {
                  return(
                      <option value={paz["id"]}>{paz["nome"]} {paz["cognome"]}</option>
                  )
                })}
              </select>
            </div>
            <div className="containerSingoloInputAggiungiVisita">
              <span className="labelInputAggiungiVisita">Scegli l'indirizzo in cui si svolgerà la visita</span>
              <select onChange={e => aggiornaIndirizzoSelect(e)}  className="selectPaziente">
                <option value="" disabled selected >Scegli indirizzo</option>
                {indirizzi.map(ind => {
                  return(
                      <option value={ind["id"]}>{ind["via"]} {ind["nCivico"]}, {ind["citta"]}({ind["provincia"]}), {ind["cap"]} </option>
                  )
                })}
              </select>
            </div>
            <div className="containerSingoloInputAggiungiVisita">
              <span className="labelInputAggiungiVisita">Scegli la data della visita</span>
              <input id ="calendar" className="dataPicker" type="date" min={new Date().getFullYear()} onChange={e => aggiornaDataSelect(e)}/>
            </div>
            <div className="containerSingoloInputAggiungiVisita">
              <button className="aggiungiBottoneVisita" onClick={()=>{onOpenModalRiepilogo(); onCloseModal(); handlerOnClick()}}>Aggiungi</button>
            </div>
          </div>
        </Modal>

        <Modal open={openRiepilogoVisita} onClose={onCloseModalRiepilogo}>
          <img src={pathGif} id="img"/>
          <div id="dati" className="contenitoreDatiVisita" style={{display:"none"}}>
            <h2>Visita creata correttamente</h2>
            <div className="divContieneIDati">
              <div className="centerItem">
                <FaRegHospital className="iconaVisita"/>
              </div>
              <div>
                <span className="grassetto">Data della visita:</span> <span className="datoVisita">{dataSelect}</span>
              </div>
              <div>
                <span className="grassetto">Luogo della visita:</span> <span className="datoVisita">{indirizzoSelect}</span>
              </div>
            </div>


          </div>
          <span style={{ display: "none" }}>
                        {
                          setTimeout(() => {
                            document.getElementById("img").style.display = "none";
                            document.getElementById("dati").style.display = "block";
                          }, 2000)
                        }
          </span>
        </Modal>

        <div className="contenitoreCardPazienti">
          <ListaVisita classe="cardPaziente"/>
        </div>
      </div>
  );
}

export default Schedules;
