import React from "react";
import jwt from "jwt-decode";
import { useState, useEffect, useNavigate } from "react"
import { BiPlusCircle, BiPlusMedical } from "react-icons/bi";
import 'react-responsive-modal/styles.css';
import { Modal } from 'react-responsive-modal';
import "../css/note-style.css";

function NoteContainer() {
    const token = localStorage.getItem("token");
    const [idDestinatario, setIdPaziente] = useState();
    const idMittente = jwt(token).id;

    const [note, setNote] = useState([]);


    const [listening, setListening] = useState(false);
    const [speriamo, setSperiamo] = useState([]);

    const fetchAllNote = async (event) => {
        return await fetch("http://localhost:8080/comunicazione/fetchTutteLeNote", {
            method: "POST",// *GET, POST, PUT, DELETE, etc.
            mode: "cors", // no-cors, *cors, same-origin
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "same-origin", // include, *same-origin, omit
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                idMittente: idMittente
            }),
        }).then(async response => {
            response = await response.json();
            setNote(response);
        })
    }

    useEffect(() => {
        fetchAllNote();
    }, []);


    const handleSubmit = async (event) => {
        return await fetch("http://localhost:8080/comunicazione/invioNota", {
            method: "POST",// *GET, POST, PUT, DELETE, etc.
            mode: "cors", // no-cors, *cors, same-origin
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "same-origin", // include, *same-origin, omit
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                idMittente: idMittente,
                idDestinatario: idDestinatario,
                nota: nota
            }),

            withCredentials: true,
            redirect: "follow", // manual, *follow, error
            referrerPolicy: "no-referrer",
        }).then(async response => {
            response = await response.json();
        })
    }

    const [pazienti, setPazienti] = useState([]);

    const fetchPazienti = async () => {
        return await fetch("http://localhost:8080/getPazientiByMedico/" + idMittente, {
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
            return response;
        }
        )
    };

    useState(() => {
        fetchPazienti().then((data) => setPazienti(data));
    });


    const [open, setOpen] = useState(false);

    const onOpenModal = () => {
        setOpen(true)
        setIdPaziente(pazienti[0].id);
    };
    const onCloseModal = () => setOpen(false);



    const onChangeHandler = (event) => {
        setIdPaziente(event.target.value);
    }

    const [nota, setNota] = useState("")

    const onNotaChange = (event) => {
        setNota(event.target.value);
    }

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
                        <select className="selectPaziente" onChange={onChangeHandler} >
                            {
                                pazienti.map((paziente) =>
                                    <option value={paziente.id}>{paziente.nome}</option>

                                )
                            };
                        </select>
                        <br />
                        <h2>Testo della nota</h2>
                        <textarea className="textAreaTestoNote" type="text" placeholder=" Inserire qui la nota" cols={60} rows={20} onChange={onNotaChange}></textarea>
                        <button className="bottoneInviaNota" onClick={() => { handleSubmit(); setOpen(false) }}>Invia nota</button>
                    </Modal>
                </div>
            </div>
            <div className="singola-nota-container">
                <div className="nota-div">
                    {note.map((nota) =>
                        <>
                            <span className="autore-nota" value={nota.nome}>{nota.nome}</span>
                            <span className="autore-nota" value={nota.nome}>{nota.messaggio}</span>
                        </>
                    )}
                </div>
                <hr className="linea-tra-note"></hr>
            </div>
        </div>
    );
}

export default NoteContainer;
