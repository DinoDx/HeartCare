import React from "react";
import "../css/Fascicolo.css"
import {useState,useEffect} from "react";

export function FascicoloBanner(props){
    const [Misurazioni,setMisurazioni] = useState([{}]);
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
       setMisurazioni(props.misurazioni);
    })

    const contaCategoria = (categoria) => {
        return Misurazioni.filter( mis =>
            mis["categoria"] == categoria).length
    }

    return(
        <div className="bannerFascicoloSanitarioElettronico">
            {props.categorie.map(cat => {
                return(
                    <div className="blocco-testo-banner" key={cat}>
                        <span className="testo-banner">{cat}</span>
                        <span className="testo-banner-numero">{contaCategoria(cat)}</span>
                    </div>
                )
            })
            }
        </div>

    );
}