import React, {useEffect, useState} from "react";
import HamburgerMenu from "../components/HamburgerMenu";
import ListaVisita from "../components/ListaVisita";
import "../css/scheduleStyle.css";
import Modal from "react-responsive-modal";
import { BiPlusCircle, BiPlusMedical } from "react-icons/bi";
import jwt from "jwt-decode";
import pathGif from "../images/Ripple-1s-200px_1.gif";

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
  const onCloseModalRiepilogo = () => setOpenRiepilogoVisita(false);
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
      const response = await fetch("http://localhost:8080/crea", {
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
        <div className="searchbar">
          <input id="search" type="text" placeholder=" 🔍Cerca Paziente..." />
        </div>
        <span className="bentornato">Bentornato, {utente["cognome"]} 👋🏻</span>
        <span className="iTuoiPazienti">Le tue visite: </span>
        <button onClick={onOpenModal}> + </button>
        <Modal open={open} onClose={onCloseModal}>
          <h2>Aggiunta visita</h2>
          <div>
            <select onChange={ e => aggiornaPazienteSelect(e)}>
              <option value="" disabled selected>Scegli paziente</option>
              {pazienti.map( paz => {
                return(
                    <option value={paz["id"]}>{paz["nome"]} {paz["cognome"]}</option>
                )
              })}
            </select>
          </div>
          <div>
            <select onChange={e => aggiornaIndirizzoSelect(e)}>
              <option value="" disabled selected>Scegli indirizzo</option>
              {indirizzi.map(ind => {
                return(
                    <option value={ind["id"]}>{ind["via"]} {ind["nCivico"]}, {ind["citta"]}({ind["provincia"]}), {ind["cap"]} </option>
                )
              })}
            </select>
          </div>
          <div>
            <span>Aggiungi data</span><br/>
            <input id ="calendar" type="date" min={new Date().getFullYear()} onChange={e => aggiornaDataSelect(e)}/>
          </div>
          <button onClick={()=>{onOpenModalRiepilogo(); onCloseModal()}}>Aggiungi</button>
        </Modal>

        <Modal open={openRiepilogoVisita} onClose={onCloseModalRiepilogo}>
          <img src={pathGif} id="img"/>
          <div id="dati" style={{display:"none"}}>ciao mario la visita sarà il 21</div>
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
