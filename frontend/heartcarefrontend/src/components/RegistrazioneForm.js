import React from "react";
import '../css/RegistrazioneStyle.css';
import { useState } from "react";
import { useEffect } from "react";

import { useForm } from "react-hook-form";
import axios from "axios";
import moment from 'moment';




function RegistrazioneForm() {
    const [nome, setNome] = useState("");
    const [cognome, setCognome] = useState("");
    const [nTelefono, setNtelefono] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [confermaPassword, setConfermaPassword] = useState("");
    const [genere, setGenere] = useState("");
    const [dataNascita, setDataNascita] = useState("");
    const [codiceFiscale, setCodiceFiscale] = useState("");

    const aggiornaNome = (event) => {
        setNome(event.target.value);
    }

    const aggiornaCognome = (event) => {
        setCognome(event.target.value);
    }

    const aggiornaEmail = (event) => {
        setEmail(event.target.value);
    }

    const aggiornaNtelefono = (event) => {
        setNtelefono(event.target.value);
    }

    const aggiornaPassword = (event) => {
        setPassword(event.target.value);
    }

    const aggiornaConfermaPassword = (event) => {
        setConfermaPassword(event.target.value);
    }

    const aggiornaGenere = (event) => {
        setGenere(event.target.value);
    }

    const aggiornaDataNascita = (event) => {
        setDataNascita(event.target.value);
    }

    const aggiornaCodiceFiscale = (event) => {

        setCodiceFiscale(event.target.value);
        console.log(event.target.value)
    }
    const [data, setData] = useState({})
    const [speriamo, setSperiamo] = useState({})
   /* const fetchCf = async (value) => {

        try {
            const data = await axios.post('http://localhost:8080/getByCodice', value, {
                headers: { "Content-Type": "text/plain" }
            })
            console.log(data.data.codiceFiscale)
            setData(data.data.codiceFiscale)
        } catch (error) {
            console.error(error.message);
        }

    }*/

    /*const fetchEmail = async (val) => {
        try {
            const pippo = await axios.post('http://localhost:8080/getByEmail', val, {
                headers: { "Content-Type": "text/plain" }

            })
            console.log(pippo.data.email)
            setSperiamo(pippo.data.email)
        } catch (er) {
            console.error(er.message);
        }

    }*/

    /*const fetchCf = async (value) => {
       const response = await axios.post("http://localhost:8080/getByCodice",{
            codiceFiscale: value
        })
        console.log(response.data);
         const data = response.data;
        console.log(JSON.parse(data.codiceFiscale)) 
        return JSON.parse(data.codiceFiscale);
    }*/


    const { register, formState: { errors }, handleSubmit } = useForm();
    const onSubmit = (data) => axios.post('http://localhost:8080/registrazione', {
        nome: data.nome,
        cognome: data.cognome,
        password: data.password,
        email: data.email,
        numeroTelefono: data.numeroTelefono,
        confermaPassword: data.confermaPassword,
        dataDiNascita: data.dataDiNascita,
        genere: data.genere,
        codiceFiscale: data.codiceFiscale,
        citta: data.citta,
        provincia: data.provincia,
        cap: data.cap,
        via: data.via,
        nCivico: data.nCivico
    })

    return (
        <div className="contenitoreRegistrazioneForm">
            <div className="contenitoreEditTextCorta">
                <input type="text" placeholder="Mario" className="registrazioneEditTextCorta" onChange={aggiornaNome} {...register("nome", { required: true })} />
                <error>
                    {errors.nome?.type === "required" && "Name is required"}
                </error>
            </div>
            <div className="contenitoreEditTextCorta">
                <input type="text" placeholder="Rossi" className="registrazioneEditTextCorta" onChange={aggiornaCognome} {...register("cognome", { required: true })} />
                <error>
                    {errors.cognome?.type === "required" && "Cognome is required"}
                </error>
            </div>
            <div className="contenitoreEditTextCorta">
                <input type="text" placeholder="333333333" className="registrazioneEditTextCorta" onChange={aggiornaNtelefono} {...register("numeroTelefono", {
                    required: true,
                    minLength: 13,
                    maxLength: 13,
                    pattern: /^((00|\+)39[\. ]??)??3\d{2}[\. ]??\d{6,7}$/
                })} />
                <error>
                    {errors.numeroTelefono?.type === "minLength" &&
                        "Entered number is less than 13 digits"}
                    {errors.number?.type === "maxLength" &&
                        "Entered number is more than 13 digits"}
                    {errors.numeroTelefono?.type === "required" && "Inserire numero telefono"}
                    {errors.numeroTelefono?.type === "pattern" && "Pattern non corretto"}
                </error>
            </div>
            <div className="contenitoreEditTextCorta">
                <input type="text" placeholder="M|F" className="registrazioneEditTextCorta" onChange={aggiornaGenere} {...register("genere", {
                    required: true,
                    pattern: /^M$|^F$/
                })} />
                <error>
                    {errors.genere?.type === "required" && "Inserire genere"}
                    {errors.genere?.type === "pattern" && "Inserire correttamente"}
                </error>
            </div>
            <div>
                <input type="date" placeholder="Data Nascita" className="registrazioneEditText" max={moment().format("YYYY-MM-DD")} onChange={aggiornaDataNascita}{...register("dataDiNascita", {
                    required: true,
                    validate: (value) => {
                        return value < moment().format("YYYY-MM-DD");
                    }
                })} />
                <error>
                    {errors.dataNascita?.type === "required" && "Inserire data nascita"}
                    {errors.dataDiNascita?.type === "validate" && "Data troppo grande"}
                </error>
            </div>
            <div>
                <input id="codiceFiscale" type="text" placeholder="Codice Fiscale" className="registrazioneEditText" onChange={aggiornaCodiceFiscale}{...register("codiceFiscale", {
                    required: true,
                    pattern: /^[A-Z]{6}[A-Z0-9]{2}[A-Z][A-Z0-9]{2}[A-Z][A-Z0-9]{3}[A-Z]$/,
                    validate:  async (value) => {
                        const data =await  axios.post('http://localhost:8080/getByCodice', value, {
                    headers: { "Content-Type": "text/plain" }
             })
                    console.log((!data.data.codiceFiscale))
                     return !data.data.codiceFiscale
                    }
                })} />
                <error>
                    {errors.codiceFiscale?.type === "required" && "Inserire codice fiscale"}
                    {errors.codiceFiscale?.type === "pattern" && "Inserire correttamente il codice fiscale"}
                    {errors.codiceFiscale?.type === "validate" && "Codice fiscale già presente"}

                </error>
            </div>
            <div>
                <input type="text" placeholder="email@example.ti" className="registrazioneEditText" onChange={aggiornaEmail}{...register("email", {
                    required: true,
                    pattern: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/i,
                    validate:  async (val) => {
                        const pippo =await axios.post('http://localhost:8080/getByEmail', val, {
                    headers: { "Content-Type": "text/plain" }

             })
                    console.log(pippo.data.email)
                     return !pippo.data.email
                    }
                })} />
                <error>
                    {errors.email?.type === "required" && "Email is required"}
                    {errors.email?.type === "pattern" &&
                        "Entered email is in wrong format"}
                    {errors.email?.type === ("validate") && ("Email già presente")}

                </error>
            </div>
            <div>
                <input id="password" type="password" placeholder="Password" className="registrazioneEditText" onChange={aggiornaPassword}
                    {...register("password", {
                        required: true,
                        pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?^#()<>+&.])[A-Za-z\d@$!%*?^#()<>+&.]{8,16}$/i,
                    })} />
                <error>
                    {errors.password?.type === "required" && "Inserire password"}
                    {errors.password?.type === "pattern" &&
                        "Password non rispetta il formato almeno 8 caratteri una maiuscola un simbolo e un numero"}
                </error>
            </div>
            <div>
                <input type="password" placeholder="Conferma password" className="registrazioneEditText" onChange={aggiornaConfermaPassword}
                    {...register("confermaPassword", {
                        required: true,
                        validate: (value) => {
                            const pass = document.getElementById("password").value;

                            return value == pass
                        }
                    })} />
                <error>
                    {errors.confermaPassword?.type === "required" && "Inserire conferma password"}
                    {errors.confermaPassword?.type === "validate" && "Le password non corrispondono"}
                </error>
            </div>
            <div>
                <input type="text" placeholder="Città" className="registrazioneEditText"
                    {...register("citta", {
                        required: true
                    })} />
                <error>
                    {errors.citta?.type === "required" && "Inserire una città"}
                </error>
            </div>
            <div>
                <input type="text" placeholder="Provincia" className="registrazioneEditText"
                    {...register("provincia", {
                        required: true
                    })} />
                <error>
                    {errors.citta?.type === "required" && "Inserire una provincia"}
                </error>
            </div>
            <div>
                <input type="text" placeholder="Via" className="registrazioneEditText"
                    {...register("via", {
                        required: true
                    })} />
                <error>
                    {errors.citta?.type === "required" && "Inserire una via"}
                </error>
            </div>
            <div>
                <input type="text" placeholder="Numero civico" className="registrazioneEditText"
                    {...register("nCivico", {
                        required: true
                    })} />
                <error>
                    {errors.citta?.type === "required" && "Inserire un numero civico"}
                </error>
            </div>
            <div>
                <input type="number" placeholder="CAP" className="registrazioneEditText"
                    {...register("cap", {
                        required: true
                    })} />
                <error>
                    {errors.citta?.type === "required" && "Inserire un cap"}
                </error>
            </div>
            <button className="formButton" onClick={handleSubmit(onSubmit)}>Registrati</button>

        </div>
    );
}
export default RegistrazioneForm;