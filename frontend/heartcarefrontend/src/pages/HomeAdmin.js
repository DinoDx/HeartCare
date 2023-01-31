import React, { useEffect, useState } from "react";
import VisitaCard from "../components/VisitaCard";
import NoteContainer from "../components/NoteContainer";
import "../css/style.css";
import "../css/home-main-content.css";
import "../css/homeMedico_style.css";
import { useNavigate } from "react-router";
import { Navigate } from "react-router-dom";
import ListaVisita from "../components/ListaVisita";
import "../css/HomeAdmin.css"

import ListaUtenti from "../components/ListaUtenti";
import ListaPazienti from "../components/ListaPazienti";
import {Modal} from "react-responsive-modal";
import { useForm } from "react-hook-form";
import moment from "moment";
import '../css/RegistrazioneStyle.css';
function HomeAdmin(){


    return(
        <div className="contenitoreAdminContent">
        <div className="searchbar searchbarAdmin">
            <input id="search" type="text" placeholder=" 🔍 Cerca utente..." />
        </div>
            <span className="bentornat">Bentornato, Admin 👋🏻</span>
            <span className="iTuoiUtenti">Gli Utenti :</span>
            <ListaUtenti/>
        </div>
        );

}
export default HomeAdmin