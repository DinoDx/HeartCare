import React from "react";
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

function HomePaziente() {


    return !localStorage.getItem("token") ? (
        <Navigate to={"/Login"} />
    ) : (
        <div className="contenitorePazientiContent contenitoreHomePaziente">
            <div className="searchbar">
                <input id="search" type="text" placeholder=" 🔍 Cerca dottore..." />
            </div>

            <span className="testo-bentornat">Bentornato, Gianluigi Buffon👋🏻</span>

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
                {/*<Grafico/>*/}
                <div className="containerBottoni">
                    <button className="bottoneHomePaziente">Avvio Misurazione</button>
                    <button className="bottoneHomePaziente">Avvio Predizione</button>
                </div>

            </div>
            <div className="contenitoreDestra">
                <NoteContainer/>
                <VisitePazienteContainer/>
            </div>
        </div>
    );
}

export default HomePaziente;
