import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const NombresFrutas = () => (
  <View style={styles.container}>
    <Text style={styles.title}>Actividad 12: Nombres de frutas</Text>
    {/* Imágenes y nombres de frutas */}
  </View>
);

const styles = StyleSheet.create({
  container: { flex: 1, padding: 24, backgroundColor: '#fff' },
  title: { fontSize: 20, fontWeight: 'bold', color: '#333', marginBottom: 16 },
});

export default NombresFrutas;