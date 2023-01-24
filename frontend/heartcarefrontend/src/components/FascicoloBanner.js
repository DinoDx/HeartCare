import React from "react";
import "../css/Fascicolo.css"
import {useState,useEffect} from "react";
import axios from "axios";

export function FascicoloBanner(props){
    const [Misurazioni,setMisurazioni] = useState([{}]);
    console.log(props.categoria);
    
    useEffect(() => {
        const fetchData = async() => {
            const response = await axios.post('http://localhost:8080/getMisurazioneCategoria?categoria='+props.categoria+'&id=1');
            response.data.forEach( m=> {
                delete m.id;
                delete m.dataMisurazione;
                delete m.paziente;
                delete m.dispositivoMedico;
            })
            setMisurazioni(response.data);
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