package com.fer.componentes.beans;

import javax.swing.JPanel;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JBotonesCrud extends JPanel {
	public JButton btNuevo;
	public JButton btModificar;
	public JButton btGuardar;
	public JButton btEliminar;
	public JButton btCancelar;

	public JBotonesCrud() {
		setLayout(null);
		
		btNuevo = new JButton("Nuevo");
		btNuevo.setBounds(10, 11, 89, 23);
		add(btNuevo);
		
		btModificar = new JButton("Modificar");
		btModificar.setBounds(10, 45, 89, 23);
		add(btModificar);
		
		btGuardar = new JButton("Guardar");
		btGuardar.setBounds(109, 11, 89, 23);
		add(btGuardar);
		
		btEliminar = new JButton("Eliminar");
		btEliminar.setBounds(109, 45, 89, 23);
		add(btEliminar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setBounds(59, 79, 89, 23);
		add(btCancelar);
		
		setActionCommands();

	}
	public void addListeners(ActionListener listener) {
		btNuevo.addActionListener(listener);
		btCancelar.addActionListener(listener);
		btEliminar.addActionListener(listener);
		btGuardar.addActionListener(listener);
		btModificar.addActionListener(listener);
	}
	
	public void setActionCommands() {
		btNuevo.setActionCommand("nuevo");
		btCancelar.setActionCommand("cancelar");
		btEliminar.setActionCommand("eliminar");
		btGuardar.setActionCommand("guardar");
		btModificar.setActionCommand("modificar");
	}

}
