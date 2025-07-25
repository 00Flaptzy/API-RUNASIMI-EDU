import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const Numeros = () => (
  <View style={styles.container}>
    <Text style={styles.title}>Actividad: Aprendemos los números</Text>
    {/* Ejercicios interactivos con números */}
  </View>
);

const styles = StyleSheet.create({
  container: { flex: 1, padding: 24, backgroundColor: '#fff' },
  title: { fontSize: 20, fontWeight: 'bold', color: '#333', marginBottom: 16 },
});

export default Numeros;