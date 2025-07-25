import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const Alumnos = () => (
  <View style={styles.container}>
    <Text style={styles.title}>Lista de estudiantes</Text>
    {/* Búsqueda, filtros y lista de alumnos */}
  </View>
);

const styles = StyleSheet.create({
  container: { flex: 1, padding: 24, backgroundColor: '#777' },
  title: { fontSize: 22, fontWeight: 'bold', color: '#fff', marginBottom: 16 },
});

export default Alumnos;