import React from "react";
import "../css/style.css";
import "../css/PazientiCss.css";
import axios from "axios";
import {useState, useEffect} from "react";
import CardPaziente from "./CardPaziente";

function ListaPazienti(){
    const [loading, setLoading] = useState(true);
    const [data, setData] = useState([])
    const token = localStorage.getItem("token");

    let config = {
        Accept: "application/json",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
        withCredentials: true,
        Authorization: `Bearer ${token}`,
        "Content-Type" : "application/json"
    };

    const fetchData = async () =>{
        setLoading(true);
        try {
            const response = await fetch("http://localhost:8080/getTuttiPazienti",{
                method : "POST",
                headers : config,
            }).then(response => response.json());
            setData(response);
            console.log(data);
        } catch (error) {
            console.error(error.message);
        }
        setLoading(false);
    }

    useEffect(() => {
        fetchData();
    }, []);

    return(
        <div className="contenitoreCardPazienti">
            {data.map(function(paziente, idx){
                return (
                    <CardPaziente key={idx} idPaziente={paziente.id} nomePaziente={paziente.nome} cognomePaziente={paziente.cognome} dataNascita={paziente.dataDiNascita} genere={paziente.genere} numero={paziente.numeroTelefono}/>
                )
            })}
        </div>
    );
}

export default ListaPazienti;