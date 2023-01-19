import React from "react";
import "../css/Fascicolo.css"
import {FascicoloBanner} from "../components/FascicoloBanner";
import {Grafico} from "../components/Grafico";


function Fascicolo(){
    return (
        <div className="contenitoreFascicoloContent">
            <div className="searchbar">
                <input id="search" type="text" placeholder=" 🔍Cerca Paziente..." />
            </div>
            <span>bentornato, Mario 👋🏻</span>
            <FascicoloBanner categoria="MISURATORE_DI_PRESSIONE" />
            <div google-chart chart="chartObject" ng-if="chartObject">
                <Grafico/>
            </div>
        </div>
    )
}

export default Fascicolo;