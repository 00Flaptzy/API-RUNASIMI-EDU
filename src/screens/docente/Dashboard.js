import React from 'react';
import Sidebar from '../../components/navigation/Sidebar';

const Dashboard = () => {
  return (
    <div style={{ display: 'flex', height: '100vh' }}>
      <Sidebar />
      <div style={{ flex: 1, padding: '40px', background: '#f5f6fa' }}>
        {/* Aquí se renderiza el contenido según la opción seleccionada */}
        <h2>Bienvenido al panel docente</h2>
      </div>
    </div>
  );
};

export default Dashboard;