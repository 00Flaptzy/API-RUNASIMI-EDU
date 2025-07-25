import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const DetalleActividad = () => (
  <View style={styles.container}>
    <Text style={styles.title}>Detalle de actividad</Text>
    {/* Información completa y resultados de estudiantes */}
  </View>
);

const styles = StyleSheet.create({
  container: { flex: 1, padding: 24, backgroundColor: '#777' },
  title: { fontSize: 22, fontWeight: 'bold', color: '#fff', marginBottom: 16 },
});

export default DetalleActividad;