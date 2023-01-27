import React from "react";
import jwt from "jwt-decode";
import { useState, useEffect, useNavigate } from "react"
import { BiPlusCircle, BiPlusMedical } from "react-icons/bi";
import { fetchEventSource } from "@microsoft/fetch-event-source";
import 'react-responsive-modal/styles.css';
import { Modal } from 'react-responsive-modal';
import { EventSourcePolyfill } from 'event-source-polyfill';
import "../css/note-style.css";
function NoteContainer(props) {
    const token = localStorage.getItem("token");


    const handleSubmit = async (event) => {
        const eventSource = await new EventSourcePolyfill("http://localhost:8080/comunicazione/invioNota", {
            method: "GET",// *GET, POST, PUT, DELETE, etc.
            mode: "cors", // no-cors, *cors, same-origin
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "same-origin", // include, *same-origin, omit
            headers: {
                Authorization: `Bearer ${token}`,

                //'Content-Type': 'application/x-www-form-urlencoded',
            },

            withCredentials: true,
            redirect: "follow", // manual, *follow, error
            referrerPolicy: "no-referrer",

        });

        eventSource.onmessage = res => {
            console.log(res.data);
            eventSource.close();
        }
        eventSource.onerror = err => {
            console.log('EventSource error: ', err);
        };

    }

    const [pazienti, setPazienti] = useState([]);

    const id = jwt(token).id;

    const fetchPazienti = async () => {
        return await fetch("http://localhost:8080/getPazientiByMedico/" + id, {
            method: "GET", // *GET, POST, PUT, DELETE, etc.
            mode: "cors", // no-cors, *cors, same-origin
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "same-origin", // include, *same-origin, omit
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
                // 'Content-Type': 'application/x-www-form-urlencoded',
            }, redirect: "follow", // manual, *follow, error
            referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
            //body: JSON.stringify(data), // body data type must match "Content-Type" header
        }).then(async (response) => {
                response = await response.json()
                console.log(response)

                return response;
            }
        )
    };

    useState(() => {
        fetchPazienti().then((data) => setPazienti(data));
    });


    const [open, setOpen] = useState(false);

    const onOpenModal = () => setOpen(true);
    const onCloseModal = () => setOpen(false);


    return (
        <div className="container-note">
            <div className="intestazione-note">
                <div className="div-intestazione-sx">
                    <span className="testo-intestazione-note">Note</span>
                </div>
                <div className="container-icona">
                    <BiPlusCircle className="icona-aggiunta-nota" onClick={onOpenModal} />
                    <Modal open={open} onClose={onCloseModal} center>
                        <h2>Scegli il paziente</h2>
                        <select className="selectPaziente">
                            {
                                pazienti.map( (paziente) =>
                                    <option value={paziente.id}>{paziente.nome}</option>
                                )
                            };
                        </select>
                        <br/>
                        <h2>Testo della nota</h2>
                        <textarea className="textAreaTestoNote" type="text" placeholder=" Inserire qui la nota" cols={60} rows={20}></textarea>
                        <button className="bottoneInviaNota">Invia nota</button>
                    </Modal>
                </div>
            </div>
            <div className="singola-nota-container">
                <div className="nota-div">
                    <span className="autore-nota">Mario Rossi</span>
                    <span className="corpo-nota">
            Ciao dottor Lambiase, ti voglio bene
          </span>
                </div>
                <hr className="linea-tra-note"></hr>

                <div className="nota-div">
                    <span className="autore-nota">Mario Rossi</span>
                    <span className="corpo-nota">
            Ciao dottor Lambiase, ti voglio bene
          </span>
                </div>
                <hr className="linea-tra-note"></hr>

                <div className="nota-div">
                    <span className="autore-nota">Mario Rossi</span>
                    <span className="corpo-nota">
            Ciao dottor Lambiase, ti voglio bene
          </span>
                </div>
                <hr className="linea-tra-note"></hr>

                <div className="nota-div">
                    <span className="autore-nota">Mario Rossi</span>
                    <span className="corpo-nota">
            Ciao dottor Lambiase, ti voglio bene
          </span>
                </div>
                <hr className="linea-tra-note"></hr>
            </div>
        </div>
    );
}

export default NoteContainer;
