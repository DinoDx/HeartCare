import React from "react";
import "../css/scheduleStyle.css";
import "../css/style.css";
import "../css/visita-style.css";
import axios from "axios";
import { useState, useEffect } from "react";
import VisitaCard from "./VisitaCard";

function ListaVisita() {
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

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const response = await fetch("http://localhost:8080/Schedule",{
          method : "POST",
          headers : config,
        }).then(response => response.json());
        setData(response);
        console.log(data);
      } catch (error) {
        console.error(error.message);
      }
      setLoading(false);
    };
    fetchData();
  }, []);

  return (
    <div className="container-visita">
      {/*{data.map(function(visita, idx){
                return (
                    //key={idx} idVisita={visita.id} dataVisita={visita.data} statoVisita={visita.stato_visita} idIndirizzo={visita.id_indirizzo}/>
                )
            })}*/}
      <VisitaCard />
    </div>
  );
}

export default ListaVisita;
