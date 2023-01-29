import React, {useEffect, useState, useLayoutEffect, cloneElement} from "react";
import "../css/scheduleStyle.css";
import "../css/style.css";
import "../css/visita-style.css";
import {Chart} from "react-google-charts";

function Grafico(props) {
    const [dataGrafico,setDataGrafico] = useState([]);
    const [isChange,setIsChange] = useState(false);

    const fromJsonToArray = (categorieNonJsonate) => {
        let arr = JSON.stringify(categorieNonJsonate);
        arr = arr.replace("[","")
        arr = arr.replace("]","")
        arr = arr.replaceAll("\"","")
        arr = arr.split(",")
        return arr;
    }

    let arrayCategorie = fromJsonToArray(props.categorie);
    let categoria = arrayCategorie[0];
    const [categoriaSelezionata,setCategoriaSelezionata] = useState(categoria);
    let attributiFiltrati = [];
    let filtrate = [];

    const filterByCategoria = (categoria) => {
        filtrate = [];
        attributiFiltrati = [];
            Object.keys(props.misurazioni).map(index =>{
                if(props.misurazioni[index]["categoria"] === categoria){
                    filtrate.push(Object.values(props.misurazioni[index]["misurazione"]));
                    attributiFiltrati = Object.keys(props.misurazioni[index]["misurazione"])
                }
            }
        )
        attributiFiltrati.shift();
            filtrate.map((misurazione) =>{
                misurazione.shift();
            })

    }
    let tmpDataGrafico = [];
    const riempiDatiGrafico = () => {
        tmpDataGrafico.push(attributiFiltrati);
        for(let i = 0; i<filtrate.length; i++) {
            tmpDataGrafico.push(filtrate[i])
        }
    }

    useEffect( () => {
        if(isChange){
            filterByCategoria(categoriaSelezionata);
            setDataGrafico(tmpDataGrafico);
            riempiDatiGrafico();
            setIsChange(false);
        }
    }, [isChange])

   const handlerOnChange = (event) => {
       let cat = event.target.value;
       setCategoriaSelezionata(cat);
       setIsChange(true);
    }

    return (
            <div className="contenitoreGrafico">
                <span className="titoloGrafico">Andamento misurazioni passate:</span>
                <Chart
                    className="grafico"
                    chartType="Line"
                    width="100%"
                    height="400px"
                    data={dataGrafico}
                />
                <select id = "selectCategoria" className="selectMisurazione" onChange={(e) => handlerOnChange(e)}>
                    {props.categorie.map( c => {
                        return <option key ={c.id} value ={c.value} >{c}</option>
                    })}
                </select>
            </div>
    );
}

export default Grafico;