import React from "react";
import "../css/Fascicolo.css"
import {FascicoloBanner} from "../components/FascicoloBanner";
import Grafico from "../components/Grafico";

function Fascicolo(){
    return (
        <div className="contenitoreFascicoloContent">
            <div className="searchbar">
                <input id="search" type="text" placeholder=" 🔍Cerca Paziente..." />
            </div>
            <span>bentornato, Mario 👋🏻</span>
            <FascicoloBanner categoria="Misuratore di pressione" />
        </div>
    )
}

export default Fascicolo