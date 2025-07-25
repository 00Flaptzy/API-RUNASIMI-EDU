import React, { useState } from 'react';
import { View, Text, TouchableOpacity, Image, StyleSheet } from 'react-native';
// Usar navegación web-compatible como alternativa
import { MdExpandLess, MdExpandMore } from 'react-icons/md';

const docente = {
  nombre: 'PABLO ELEAZAR',
  apellido: 'ATAUCUSI ROMERO',
  avatar: 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNDAiIGhlaWdodD0iNDAiIHZpZXdCb3g9IjAgMCA0MCA0MCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPGNpcmNsZSBjeD0iMjAiIGN5PSIyMCIgcj0iMjAiIGZpbGw9IiM2NjY2NjYiLz4KPHN2ZyB4PSI4IiB5PSI4IiB3aWR0aD0iMjQiIGhlaWdodD0iMjQiIHZpZXdCb3g9IjAgMCAyNCAyNCIgZmlsbD0id2hpdGUiPjxwYXRoIGQ9Ik0xMiAyQzEzLjEgMiAxNCAyLjkgMTQgNEMxNCA1LjEgMTMuMSA2IDEyIDZDMTAuOSA2IDEwIDUuMSAxMCA0QzEwIDIuOSAxMC45IDIgMTIgMlpNMTIgMTNWMjJIMTBWMTNIMTJaIi8+PC9zdmc+Cjwvc3ZnPg==',
};

const grados = ['Grado 2°', 'Grado 3°', 'Grado 4°'];

// Componente Icon personalizado
const Icon = ({ IconComponent, size = 24, color = '#fff' }) => (
  <div style={{ display: 'flex', alignItems: 'center' }}>
    <IconComponent size={size} color={color} />
  </div>
);

const Sidebar = ({ onNavigate }) => {
  const [showGrados, setShowGrados] = useState(false);

  // Función de navegación personalizada que no depende de React Navigation
  const handleNavigation = (route, params = {}) => {
    if (onNavigate) {
      onNavigate(route, params);
    } else {
      // Fallback: puedes usar window.location o history API
      console.log(`Navigating to: ${route}`, params);
    }
  };

  return (
    <View style={styles.sidebar}>
      {/* Header Docente */}
      <View style={styles.header}>
        <Image 
          source={{ uri: docente.avatar }} 
          style={styles.avatar}
          defaultSource={{ uri: docente.avatar }}
        />
        <View>
          <Text style={styles.nombre}>{docente.nombre}</Text>
          <Text style={styles.apellido}>{docente.apellido}</Text>
        </View>
      </View>

      {/* Opciones de menú */}
      <View style={styles.menu}>
        <TouchableOpacity 
          style={styles.menuItem} 
          onPress={() => handleNavigation('InicioDocente')}
        >
          <Text style={styles.menuText}>Inicio</Text>
        </TouchableOpacity>

        <TouchableOpacity 
          style={styles.menuItem} 
          onPress={() => setShowGrados(!showGrados)}
        >
          <Text style={styles.menuText}>Alumnos</Text>
          <Icon 
            IconComponent={showGrados ? MdExpandLess : MdExpandMore} 
            size={24} 
            color="#fff" 
          />
        </TouchableOpacity>
        
        {showGrados && (
          <View style={styles.subMenu}>
            {grados.map((grado) => (
              <TouchableOpacity 
                key={grado} 
                style={styles.subMenuItem} 
                onPress={() => handleNavigation('Alumnos', { grado })}
              >
                <Text style={styles.subMenuText}>{grado}</Text>
              </TouchableOpacity>
            ))}
          </View>
        )}

        <TouchableOpacity 
          style={styles.menuItem} 
          onPress={() => handleNavigation('ActividadesDocente')}
        >
          <Text style={styles.menuText}>Actividades</Text>
        </TouchableOpacity>

        <TouchableOpacity 
          style={styles.menuItem} 
          onPress={() => handleNavigation('Salir')}
        >
          <Text style={styles.menuText}>Salir</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  sidebar: {
    width: 220,
    backgroundColor: '#444',
    flex: 1,
    paddingTop: 20,
    borderTopRightRadius: 16,
    borderBottomRightRadius: 16,
    elevation: 4,
  },
  header: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: 16,
    borderBottomWidth: 1,
    borderBottomColor: '#333',
  },
  avatar: {
    width: 40,
    height: 40,
    borderRadius: 20,
    marginRight: 12,
  },
  nombre: {
    color: '#fff',
    fontWeight: 'bold',
    fontSize: 16,
  },
  apellido: {
    color: '#fff',
    fontSize: 14,
  },
  menu: {
    marginTop: 24,
  },
  menuItem: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    padding: 18,
    backgroundColor: '#555',
    marginBottom: 8,
    borderRadius: 8,
  },
  menuText: {
    color: '#fff',
    fontSize: 18,
  },
  subMenu: {
    backgroundColor: '#666',
    borderRadius: 8,
    marginLeft: 16,
    marginBottom: 8,
  },
  subMenuItem: {
    padding: 14,
    borderBottomWidth: 1,
    borderBottomColor: '#888',
  },
  subMenuText: {
    color: '#fff',
    fontSize: 16,
  },
});

export default Sidebar;