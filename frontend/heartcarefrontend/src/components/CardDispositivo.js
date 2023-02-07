import React from "react";
import { CiMedicalCase } from "react-icons/ci";

import "../css/dispositivo-style.css";
import { Modal } from 'react-responsive-modal';
import { useState } from 'react';

function CardDispositivo(props) {
    const [open, setOpen] = useState(false);

    const onOpenModal = () => setOpen(true);
    const onCloseModal = () => {
        setOpen(false)
        document.location.reload();
    };
    const token = localStorage.getItem("token");

    let config = {
        Accept: "application/json",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
        withCredentials: true,
        Authorization: `Bearer ${token}`,
        "Content-Type" : "application/json"
    };


  const eliminaDispositivo = () => {
      try {
          const response = fetch("http://localhost:8080/rimuoviDispositivo", {
              method : "POST",
              headers : config,
              body: JSON.stringify({
                  id: props.id
              })
          }).then(response => {
            onOpenModal();
          });
      } catch (error) {
          console.error(error.message);
      }
  }

  return (
    <div className="container-dispositivo">
      <Modal open={open} onClose={onCloseModal} center>
        <h2>Dispositivo eliminato correttamente</h2>
      </Modal>
      <span className="titolo-visita">{props.titolo}</span>
      <hr className="linea-visita"></hr>
      <div className="container-icona-dispositivo">
        <CiMedicalCase className="icona-dispositivo-card" />
      </div>
      <div className="container-descrizione-dispositivo">
        <div className="testo-descrizione-dispositivo">
          {props.descrizione}
        </div>
      </div>
      <div className="footer-visita">
        <button className="button-visualizza-fascicolo" onClick = {() => {eliminaDispositivo()}}>
          Elimina Dispositivo
        </button>
      </div>
    </div>
  );
}

export default CardDispositivo;
