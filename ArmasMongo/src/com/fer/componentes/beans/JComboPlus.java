package com.fer.componentes.beans;

import java.util.List;

import javax.swing.JComboBox;



public class JComboPlus<T> extends JComboBox<T>{

	private List<T> datos;
	
	
	public JComboPlus() {
		super();
	}
	public void inicializar(List<T> datos) {
		this.datos = datos;
		listar();
	}
	public void refrescar() {
		limpiar();
		listar();
	}
	public void limpiar() {
		removeAllItems();
	}
	public void listar() {
		if(datos == null)
			return;
		
		for(T dato: datos)
			addItem(dato);
	}
	public T getDatoSeleccionado() {
		T dato = (T) getSelectedItem();
		return dato;
	}
	
}
