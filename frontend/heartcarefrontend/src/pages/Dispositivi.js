import React, { useState, useEffect }  from "react";
import jwt from "jwt-decode";
import CardDispositivo from "../components/CardDispositivo";
import NoteContainer from "../components/NoteContainer";
import "../css/home-main-content.css";
import CardPaziente from "../components/CardPaziente";

export default function Dispositivi() {
  const [dispositivi, setDispositivi] = useState([]);
  const token = localStorage.getItem("token");

  //const [loading, setLoading] = useState(false);

  const aggiornaDispositivi = (disp) => {
    setDispositivi(disp);
  }

  const fetchDispositivi = async () => {
    if(token == null) {
      console.log("Il token è null quindi esco")
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
  
  /*
  2 useEffect per evitare di avere un array vuoto come variabile di stato.
  */
  useEffect( () => {
    fetchDispositivi();
  }, [])

  useEffect(() => {
    if(dispositivi.length > 0) {
      console.log(dispositivi)
    }
  }, [dispositivi])


  return (
    <div className="contenitoreMainContent-Home">
      <br></br>
      <br></br>
      <br></br>
      <br></br>

      <span className="testo-bentornat">I tuoi dispositivi 🩺</span>

      <div className="full-container">
        <div className="container-sinistra">
          <div className="banner">
            <div className="blocco-testo-banner">
              <span className="testo-banner">Dispositivi totali</span>
              <span className="testo-banner-numero">12</span>
            </div>
            <div className="blocco-testo-banner">
              <span className="testo-banner">Sfigmomanometri</span>
              <span className="testo-banner-numero">120</span>
            </div>
            <div className="blocco-testo-banner">
              <span className="testo-banner">Coagulometri</span>
              <span className="testo-banner-numero">120</span>
            </div>
          </div>

          <div className="visite-container">
            <br></br>
            <br></br>
            <div className="box-visite">
            {Object.keys(dispositivi).map(function(num, index){
                return (
                  <CardDispositivo key={index} 
                                  titolo={dispositivi[num]["categoria"]} 
                                  descrizione={dispositivi[num]["descrizione"]} />
                )
            })}
            </div>
          </div>
        </div>
        <div className="note-container">
          <NoteContainer />
        </div>
      </div>
    </div>
  );
}
