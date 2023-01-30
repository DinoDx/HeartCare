import React, { useEffect, useState } from "react";
import VisitaCard from "../components/VisitaCard";
import NoteContainer from "../components/NoteContainer";
import "../css/style.css";
import "../css/home-main-content.css";
import "../css/homeMedico_style.css";
import { useNavigate } from "react-router";
import { Navigate } from "react-router-dom";
import ListaVisita from "../components/ListaVisita";
import jwt from "jwt-decode"
import SockJS from 'sockjs-client';
import {Stomp} from "@stomp/stompjs"
import addNotification from 'react-push-notification';
import Notifications from 'react-push-notification';

function HomeMedico() {
  const [utente, setUtente] = useState([]);
  let nav = useNavigate();
  const token = localStorage.getItem("token");
  const url = "http://localhost:8080/ws"
  const [stompClient, setStompClient] = useState(null);
  const [notifications, setNotifications] = useState([]);
  
  addNotification({
    title: 'Notifica',
    subtitle: 'This is a subtitle',
    message: notifications,
    theme: 'red',
    native: true // when using native, your OS will handle theming.
})
  
  useEffect(() => {
    const socket = new SockJS(url);
    const client = Stomp.over(socket);
    client.connect({}, (frame) => {
      setStompClient(client);
      client.subscribe('/topic/notifica', (response) => {
        setNotifications((notifications) => [...notifications, response.body]);
        console.log("notifica = ", response.body)
        addNotification();
        
      });
    });

    return () => {
      if (stompClient) {
        stompClient.disconnect();
      }
    };
  }, [notifications]);



  let config = {
    Accept: "application/json",
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Headers": "*",
    withCredentials: true,
    Authorization: `Bearer ${token}`,
    "Content-Type" : "application/json"
  };

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

  useEffect( () => {
    fetchHome();
      }, [])

  useEffect(() => {
    if(utente.length > 0) {
      
    }
  }, [utente])

  return  (
    <div className="contenitoreMainContent-Home">
      <div className="searchbar">
        <input id="search" type="text" placeholder=" 🔍 Cerca paziente..." />
      </div>
      

      

      <span className="testo-bentornat">Bentornato, Dr. {utente.cognome}👋🏻</span>
      
      <div className="full-container">
        <div className="container-sinistra">
          <div className="banner">
            <div className="blocco-testo-banner">
              <span className="testo-banner" >Pazienti totali</span>
              <span className="testo-banner-numero">{utente.pazientiTotali}</span>
            </div>

            <div className="blocco-testo-banner">
              <span className="testo-banner">Appuntamenti in programma</span>
              <span className="testo-banner-numero">{utente.appuntamentiInProgramma}</span>
            </div>

            <div className="blocco-testo-banner">
              <span className="testo-banner">Nuove note</span>
              <span className="testo-banner-numero">{utente.numeroNote}</span>
            </div>
          </div>

          <div className="visite-container">
            <span className="visite-in-programma">
              Le prossime visite in programma
            </span>

            <div className="box-visite box-visiteHomeMedico">
              <ListaVisita classe="cardPazienteHomeMedico"/>
            </div>
          </div>
        </div>
        <div className="note-container" >
          <NoteContainer />
        </div>
      </div>
    </div>
  );
}

export default HomeMedico;
