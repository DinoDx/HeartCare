import React from "react";
import "../css/style.css";
import "../css/home-main-content.css";
import "../css/homeMedico_style.css";
import "../css/HomePaziente.css";
import { Navigate } from "react-router-dom";
import {Chart} from "react-google-charts";

function HomePaziente() {

    const data = [
        [
            "Data misurazione",
            "Troponina Cardiaca",
            "Creatin Kinasi",
            "Mioglobina",
        ],
        [1, 37.8, 80.8, 41.8],
        [2, 30.9, 69.5, 32.4],
        [3, 25.4, 57, 25.7],
        [4, 11.7, 18.8, 10.5],
        [5, 11.9, 17.6, 10.4],
        [6, 8.8, 13.6, 7.7],
        [7, 7.6, 12.3, 9.6],
        [8, 12.3, 29.2, 10.6],
        [9, 16.9, 42.9, 14.8],
        [10, 12.8, 30.9, 11.6],
        [11, 5.3, 7.9, 4.7],
        [12, 6.6, 8.4, 5.2],
        [13, 4.8, 6.3, 3.6],
        [14, 4.2, 6.2, 3.4],
    ];

    return !localStorage.getItem("utente") ? (
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
                <div className="contenitoreGrafico">
                    <span className="titoloGrafico">Andamento misurazioni passate:</span>
                    <Chart
                        className="grafico"
                        chartType="Line"
                        width="100%"
                        height="400px"
                        data={data}
                    />
                    <select value="Tipo Misurazione" className="selectMisurazione">
                        <option>Misurazione Enzimi Cardiaci</option>
                        <option>Misurazione Glicemica</option>
                        <option>Misurazione Pressione</option>
                    </select>
                </div>
                <div className="containerBottoni">
                    <button className="bottoneHomePaziente">Avvio Misurazione</button>
                    <button className="bottoneHomePaziente">Avvio Predizione</button>
                </div>

            </div>
            <div className="contenitoreDestra">
                note
                prossime visite
            </div>
        </div>
    );
}

export default HomePaziente;
