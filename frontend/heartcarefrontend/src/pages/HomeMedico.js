import React, { useEffect, useState } from "react";
import RouterMedico from "../components/router-menu/RouterMedico";
import MainContent from "../components/MainContent";
import VisitaCard from "../components/VisitaCard";
import NoteContainer from "../components/NoteContainer";
import "../css/style.css";
import "../css/home-main-content.css";
import "../css/homeMedico_style.css";
import { useNavigate } from "react-router";
import { Navigate, Outlet } from "react-router-dom";
import AppShell from "../AppShell";
import Menu from "../components/Menu";
import { fetchEventSource } from "@microsoft/fetch-event-source";

function HomeMedico() {
  let nav = useNavigate();
  useEffect(() => {
    const sseForLastMessage = new EventSource(
      `http://localhost:8080/comunicazione/invioNotifica`,
      {
        withCredentials: true,
      }
    );
    sseForLastMessage.onopen = (e) => {
      console.log("SSE For LastMessage Connected !");
    };
    sseForLastMessage.addEventListener("notifica-prova", (event) => {
      console.log("ciao");
    });
  });

  return !localStorage.getItem("utente") ? (
    <Navigate to={"/Login"} />
  ) : (
    <div className="contenitoreMainContent-Home">
      <div className="searchbar">
        <input id="search" type="text" placeholder=" 🔍 Cerca paziente..." />
      </div>

      <span className="testo-bentornat">Bentornato, Dr. Lambiase 👋🏻</span>

      <div className="full-container">
        <div className="container-sinistra">
          <div className="banner">
            <div className="blocco-testo-banner">
              <span className="testo-banner">Pazienti totali</span>
              <span className="testo-banner-numero">120</span>
            </div>

            <div className="blocco-testo-banner">
              <span className="testo-banner">Appuntamenti in programma</span>
              <span className="testo-banner-numero">12</span>
            </div>

            <div className="blocco-testo-banner">
              <span className="testo-banner">Nuove note</span>
              <span className="testo-banner-numero">21</span>
            </div>
          </div>

          <div className="visite-container">
            <span className="visite-in-programma">
              Le prossime visite in programma
            </span>

            <div className="box-visite">
              <VisitaCard nome="Gianluigi Buffon" />
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

export default HomeMedico;
