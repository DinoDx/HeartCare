import React from "react";
import CardDispositivo from "../components/CardDispositivo";
import NoteContainer from "../components/NoteContainer";
import "../css/home-main-content.css";

export default function Dispositivi() {
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
              <CardDispositivo />
              <CardDispositivo />
              <CardDispositivo />
              <CardDispositivo />
              <CardDispositivo />
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
