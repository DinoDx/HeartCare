import React from "react";
import "../css/style.css";
import "../css/PazientiCss.css";
import axios from "axios";
import {useState, useEffect} from "react";
import CardPaziente from "./CardPaziente";
import CardUtente from "./CardUtente";
import CardAdminMedico from "./CardAdminMedico";

function ListaUtenti() {

    const [loading, setLoading] = useState(true);
    const [data, setData] = useState([]);
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
            const response = await fetch("http://localhost:8080/getTuttiUtenti",{
                method : "POST",
                headers : config,
            }).then(response => response.json());
            setData(response);
        } catch (error) {
            console.error(error.message);
        }
        setLoading(false);
    }

    useEffect(() => {
        fetchData();
    }, []);



    return(

        <div>

        <div className="contenitoreCardPazienti">
            {data.map(function(medico, idx){
                if(medico.ruolo == "PAZIENTE")
                return (
                    <CardUtente key={idx} idUtente={medico.id} nomeUtente={medico.nome} cognomeUtente={medico.cognome} dataNascita={medico.dataDiNascita} genere={medico.genere} numero={medico.numeroTelefono} email={medico.email}/>
                )
            })}

        </div>

    <div className="contenitoreCardPazienti">
        {data.map(function(medico, idx){
            if(medico.ruolo == "MEDICO")
                return (
                    <CardAdminMedico key={idx} idUtente={medico.id} nomeUtente={medico.nome} cognomeUtente={medico.cognome} dataNascita={medico.dataDiNascita} genere={medico.genere} numero={medico.numeroTelefono} email={medico.email}/>
                )
        })}
    </div>

        </div>

    );
}

export default ListaUtenti;