import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from '../screens/home/Home';
import WebCom from '../screens/auth/webcom';
import LoginAlumno from '../screens/auth/alumno/loginalumno';
import RegistroAlumno from '../screens/auth/alumno/registroalumno';
import LoginDocente from '../screens/auth/docente/logindocente';
import RegistroDocente from '../screens/auth/docente/registrodocente';
import Dashboard from '../screens/docente/Dashboard';
import Inicio from '../screens/docente/Inicio';
import Actividades from '../screens/docente/Actividades';
import Alumnos from '../screens/docente/Alumnos';
import Perfil from '../screens/docente/Perfil';


const AppRouter = () => (
  <Router>
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/webcom" element={<WebCom />} />
      <Route path="/loginalumno" element={<LoginAlumno />} />
      <Route path="/registroalumno" element={<RegistroAlumno />} />
      <Route path="/logindocente" element={<LoginDocente />} />
      <Route path="/registrodocente" element={<RegistroDocente />} />
      <Route path="/docente/dashboard" element={<Dashboard />} />
      <Route path="/docente/inicio" element={<Inicio />} />
      <Route path="/docente/actividades" element={<Actividades />} />
      <Route path="/docente/alumnos" element={<Alumnos />} />  
     <Route path="/docente/perfil" element={<Perfil />} />
      
      

    </Routes>
  </Router>
);

export default AppRouter;
