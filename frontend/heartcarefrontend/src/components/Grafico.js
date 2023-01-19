import { Chart } from "react-google-charts";

export function Grafico(){

    return(
        <Chart>
        chartType="PieChart"
        data={[["Age", "Weight"], [4, 5.5], [8, 12]]}
        width="20%"
        height="40px"
        </Chart>
    )
}