import "./App.css";
import "./css/style.css";
import React from "react";
import Login from "./pages/Login";
import AppShell from "./AppShell";
import {BrowserRouter, Navigate, Outlet, Route, Routes} from "react-router-dom";
import HomeMedico from "./pages/HomeMedico";
import Pazienti from "./pages/Pazienti";
import Schedules from "./pages/Schedules";
import Profilo from "./pages/Profilo";
import About from "./pages/About";
import Menu from "./components/Menu";

function App() {

    const AuthenticatedRoute = () => {
        return (
            !localStorage.getItem("utente") ? <Navigate to={"/Login"} /> : <AppShell> <Menu/> <Outlet/> </AppShell>
        )
    }

    const AppRoutes = () => {
        return (
            <Routes>
                <Route path="Login" element={<Login />} />
                <Route path="/" element={<AuthenticatedRoute />}>
                    <Route path="HomeMedico" element={<AppShell><HomeMedico /></AppShell>} />
                    <Route path="Schedules" element={<AppShell><Schedules /></AppShell>} />
                    <Route path="Pazienti" element={<AppShell><Pazienti /></AppShell>} />
                    <Route path="Profilo" element={<AppShell><Profilo /></AppShell>} />
                    <Route path="About" element={<AppShell><About /></AppShell>} />
                </Route>
            </Routes>
        );
    }

    return (
        <div className="sfondoPagina contenitorePaginaHomePage">
            <BrowserRouter>
                <AppRoutes />
            </BrowserRouter>
        </div>
    );

}

export default App;
