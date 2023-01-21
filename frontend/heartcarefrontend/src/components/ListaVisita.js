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

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const response = await axios.post("http://localhost:8080/Schedule");
        setData(response.data);
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
