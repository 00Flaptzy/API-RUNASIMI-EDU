import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const LoginAlumno = () => {
  const [dni, setDni] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  return (
    <div style={styles.background}>
      <div style={styles.container}>
        <div style={styles.leftPanel}>
          <img src="https://cdn-icons-png.flaticon.com/512/1946/1946488.png" alt="Logo" style={styles.logo} />
        </div>
        <div style={styles.rightPanel}>
          <form style={styles.form}>
            <label style={styles.label}>DNI:</label>
            <input
              type="text"
              value={dni}
              onChange={e => setDni(e.target.value)}
              style={styles.input}
              placeholder="DNI"
            />
            <label style={styles.label}>Contraseña:</label>
            <input
              type="password"
              value={password}
              onChange={e => setPassword(e.target.value)}
              style={styles.input}
              placeholder="Contraseña"
            />
            <div style={styles.buttonGroup}>
              <button type="submit" style={styles.button}>Entrar</button>
              <button type="button" style={styles.button} onClick={() => navigate('/registroalumno')}>Registrate</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

const styles = {
  background: {
    width: '100vw',
    height: '100vh',
    backgroundImage: 'url(https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=1200&q=80)',
    backgroundSize: 'cover',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  container: {
    display: 'flex',
    background: '#333',
    borderRadius: '30px',
    boxShadow: '0 8px 32px 0 rgba(31, 38, 135, 0.37)',
    overflow: 'hidden',
    width: '800px',
    maxWidth: '95vw',
    minHeight: '400px',
  },
  leftPanel: {
    flex: 1,
    background: 'rgba(0,0,0,0.2)',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    padding: '40px',
  },
  logo: {
    width: '250px',
    height: '250px',
    objectFit: 'contain',
    borderRadius: '20px',
    background: 'rgba(255,255,255,0.1)',
    boxShadow: '0 4px 16px rgba(0,0,0,0.2)',
  },
  rightPanel: {
    flex: 1,
    background: '#666',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    padding: '40px',
  },
  form: {
    width: '100%',
    maxWidth: '350px',
    display: 'flex',
    flexDirection: 'column',
    gap: '18px',
  },
  label: {
    color: 'white',
    fontSize: '18px',
    marginBottom: '4px',
    fontWeight: 'bold',
  },
  input: {
    padding: '10px',
    fontSize: '16px',
    borderRadius: '8px',
    border: 'none',
    marginBottom: '10px',
    outline: 'none',
  },
  buttonGroup: {
    display: 'flex',
    gap: '16px',
    marginTop: '10px',
  },
  button: {
    background: 'black',
    color: 'white',
    border: 'none',
    borderRadius: '8px',
    padding: '10px 24px',
    fontSize: '18px',
    cursor: 'pointer',
    fontWeight: 'bold',
  },
};

export default LoginAlumno;
