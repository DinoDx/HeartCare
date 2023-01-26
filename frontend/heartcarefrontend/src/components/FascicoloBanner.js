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
    
    useEffect(() => {
        const fetchData = async() => {
            const response = await fetch("http://localhost:8080/getMisurazioneCategoria",{
                method : "POST",
                headers : config,
                body : JSON.stringify({
                    categoria : props.categoria,
                    id: 1
                })
            }).then(response => response.json());
            response.forEach( m=> {
                delete m.id;
                delete m.dataMisurazione;
                delete m.paziente;
                delete m.dispositivoMedico;
            })
            setMisurazioni(response);
        }
        fetchData();
    },[]);

    const average = (valu) => {
        return Misurazioni.map(a => a[valu]).reduce((a, b) => (a + b)) / Misurazioni.length;
    }

    return(
        <div className="banner">
            {Object.keys(Misurazioni[0]).map(valu => {
                return(
                    <div className="blocco-testo-banner" key={valu}>
                        <span className="testo-banner">{valu}</span>
                        <span className="testo-banner-numero">{average(valu)}</span>
                    </div>
                )
            })
            }
        </div>
    );
}