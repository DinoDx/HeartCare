import React from "react";
import PropTypes from "prop-types";
import { BiPlusCircle, BiPlusMedical } from "react-icons/bi";
import "../css/note-style.css";
import { useState, useEffect } from "react";
function NoteContainer(props) {
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
      console.log(data[0]["data"])
    }
  }, [data])


  return (
    <div className="container-note marginSotto">
      <div className="intestazione-note">
        <div className="div-intestazione-sx">
          <span className="testo-intestazione-note">Visite</span>
        </div>
      </div>
      {
         Object.keys(data).map(function(el, index) {
          return (
            <div className="singola-nota-container" key={index}>
              <div className="nota-div">
                <span className="autore-nota">Visita di controllo</span>
                <span className="corpo-nota">
                  {data[el]["viaIndirizzo"]}, {data[el]["data"]}
                </span>
              </div>
              <hr className="linea-tra-note"></hr>
            </div>
        )})}
    </div>
  );
}

export default NoteContainer;
