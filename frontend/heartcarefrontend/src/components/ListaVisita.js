import React from "react";
import "../css/scheduleStyle.css";
import "../css/style.css";
import "../css/visita-style.css";
import axios from "axios";
import { useState, useEffect } from "react";
import VisitaCard from "./VisitaCard";

function ListaVisita(props) {
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

  const fetchData = async () => {
    setLoading(true);
    try {
      const response = await fetch("http://localhost:8080/visite/ottieni", {
        method : "POST",
        headers : config,
      }).then(response => response.json());
      setData(response);
    } catch (error) {
      console.error(error.message);
    }
    setLoading(false);
  };

  useEffect( () => {
    fetchData();
  }, [])

  useEffect(() => {
    if(data.length > 0) {
      
    }
  }, [data])

  return (
    <>
    {Object.keys(data).map(function(el, index){
                console.log(data[el]);
                return (
                  <VisitaCard classe={props.classe}
                              key={index}
                              id={data[el]["idPaziente"]}
                              dataVisita={data[el]["data"]} 
                              statoVisita={data[el]["statoVisita"]}
                              nomePaziente={data[el]["nomePaziente"]}
                              cognomePaziente={data[el]["cognomePaziente"]}
                              numero={data[el]["numeroTelefono"]}
                              genere={data[el]["genere"]}
                              comune = {data[el]["comune"]}
                              via = {data[el]["viaIndirizzo"]}
                              ncivico = {data[el]["ncivico"]}
                              provincia = {data[el]["provincia"]}

                  />
                )
    })}
    </>
  );
}

export default ListaVisita;
