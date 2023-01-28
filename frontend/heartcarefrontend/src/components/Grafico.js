import React, {useEffect, useState, useLayoutEffect, cloneElement} from "react";
import "../css/scheduleStyle.css";
import "../css/style.css";
import "../css/visita-style.css";
import {Chart} from "react-google-charts";
import {useForm} from "react-hook-form";

function Grafico(props) {
    const [isLoaded,setIsLoaded] = useState(true);
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
    //let categoriaSelezionata = arrayCategorie[0];
    let categoria = arrayCategorie[0];
    const [categoriaSelezionata,setCategoriaSelezionata] = useState(categoria);
    let arrayAttributi;
    let arrayValori;
    let misurazioni = JSON.stringify(props.misurazioni);

    Object.keys(props.misurazioni).map((el) => {
        // get degli attributi di una misurazione e li mette in un array
        arrayAttributi = Object.keys(props.misurazioni[el]["misurazione"]);
        arrayAttributi = fromJsonToArray(arrayAttributi);
        arrayAttributi = arrayAttributi.filter(attr => attr !== "id");

        // stampo i valori di una misurazione (con le chiavi)
        // stampo solo i valori della misurazione
        arrayValori = Object.values(props.misurazioni[el]["misurazione"]);
        arrayValori.shift();
    })
    let misurazioniFiltrate;
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
        //filtrate = fromJsonToArray(filtrate);

    }
    //filterByCategoria("Misuratore glicemico");
    let tmpDataGrafico = [];
    const riempiDatiGrafico = () => {
        tmpDataGrafico.push(attributiFiltrati);
        for(let i = 0; i<filtrate.length; i++) {
            tmpDataGrafico.push(filtrate[i])
        }
        console.log(tmpDataGrafico)
    }

    useEffect( () => {
        if(isChange){
            console.log(categoriaSelezionata)
            filterByCategoria(categoriaSelezionata);
            setDataGrafico(tmpDataGrafico);
            riempiDatiGrafico();
            console.log(dataGrafico);
            setIsChange(false);
        }
    }, [isChange])


   // riempiDatiGrafico();

    /*useEffect( () => {
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
    },[Attributi])*/

   const handlerOnChange = (event) => {
       let cat = event.target.value;
       console.log(cat)
       setCategoriaSelezionata(cat);
       setIsChange(true);
       console.log(dataGrafico)
    }

    return (
        <div className="contenitoreGrafico">
            <span className="titoloGrafico">Andamento misurazioni passate:</span>
            {isLoaded &&  <Chart
                className="grafico"
                chartType="Line"
                width="100%"
                height="400px"
                data={dataGrafico}
            /> }
            <select id = "selectCategoria" className="selectMisurazione" onChange={(e) => handlerOnChange(e)}>
                {props.categorie.map( c => {
                    return <option key ={c.id} value ={c.value} >{c}</option>
                })}
            </select>
        </div>
    );
}

export default Grafico;