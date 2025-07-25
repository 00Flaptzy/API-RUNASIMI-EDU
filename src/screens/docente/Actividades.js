import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const Actividades = () => (
  <View style={styles.container}>
    <Text style={styles.title}>Biblioteca de actividades</Text>
    {/* Lista de actividades creadas y disponibles */}
  </View>
);

const styles = StyleSheet.create({
  container: { flex: 1, padding: 24, backgroundColor: '#777' },
  title: { fontSize: 22, fontWeight: 'bold', color: '#fff', marginBottom: 16 },
});

export default Actividades;