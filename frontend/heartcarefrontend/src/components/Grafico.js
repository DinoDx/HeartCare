import React, {useEffect, useState, useLayoutEffect} from "react";
import "../css/scheduleStyle.css";
import "../css/style.css";
import "../css/visita-style.css";
import {Chart} from "react-google-charts";

function Grafico(props) {
    const [Categoria,setCategoria] = useState("Misuratore di pressione");
    const [Misurazioni,setMisurazioni] = useState([{}]);
    const [Attributi,setAttributi] = useState([]);
    const [isLoaded,setIsLoaded] = useState(false);
    const [data, setData] = useState([]);
    const token = localStorage.getItem("token");
    let copiaMisurazione;

    let config = {
        Accept: "application/json",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
        withCredentials: true,
        Authorization: `Bearer ${token}`,
        "Content-Type" : "application/json"
    };

    const aggiornaAttributi = (Att) => {
        setAttributi(Att);
    };

    const aggiornaMisurazione = (Mis) => {
        setMisurazioni(Mis);
    };

    const aggiornaCategoria = (Cat) => {
        setCategoria(Cat);
    };

    const aggiornaIsLoaded = (bool) => {
        setIsLoaded(bool);
    }

    const aggiornaData = (data) => {
        setData(data);
    }

    useEffect( () => {
        copiaMisurazione = structuredClone(Misurazioni) ;
        copiaMisurazione.forEach( m => {
            delete m.id;
            delete m.dataMisurazione;
            delete m.paziente;
            delete m.dispositivoMedico;
        })
        aggiornaAttributi(Object.keys(copiaMisurazione[0]));
    },[Misurazioni])

    useEffect( () => {
        console.log(Attributi);
        aggiornaData(  [
            Attributi,
            [1, 37.8, 80.8, 41.8],
            [2, 30.9, 69.5, 32.4],
            [3, 25.4, 57, 25.7],
            [4, 11.7, 18.8, 10.5],
            [5, 11.9, 17.6, 10.4],
            [6, 8.8, 13.6, 7.7],
            [7, 7.6, 12.3, 9.6],
            [8, 12.3, 29.2, 10.6],
            [9, 16.9, 42.9, 14.8],
            [10, 12.8, 30.9, 11.6],
            [11, 5.3, 7.9, 4.7],
            [12, 6.6, 8.4, 5.2],
            [13, 4.8, 6.3, 3.6],
            [14, 4.2, 6.2, 3.4],
        ]);
        console.log(Attributi);
        console.log(data);
        console.log(isLoaded);
    },[Attributi])

    const fetchData = async() => {
        const response = await fetch("http://localhost:8080/getMisurazioneCategoria",{
            method : "POST",
            headers : config,
            body : JSON.stringify({
                categoria: Categoria,
                id: 1
            })
        }).then(response => response.json());
        aggiornaMisurazione(response);
    }

    const handlerOnChange = (event) => {
        console.log(isLoaded);
        //aggiornaCategoria(event.target.value);
        fetchData();
        aggiornaIsLoaded(true);
    }

   /* useLayoutEffect(() => {
        //check local token or something
        data = fetchData();
    }, []); */

    return (
        <div className="contenitoreGrafico">
            <span className="titoloGrafico">Andamento misurazioni passate:</span>
            {isLoaded &&  <Chart
                className="grafico"
                chartType="Line"
                width="100%"
                height="400px"
                data={data}
            /> }
            <select id = "selectCategoria" className="selectMisurazione" onChange={(e) => handlerOnChange(e)}>
                {props.categorie.map( c => {
                    return <option key ={c.id} value ={c.value} >{c}</option>
                })}
                <option>ciao</option>
            </select>
            {Object.keys(Misurazioni[0]).length}
        </div>
    );
}

export default Grafico;