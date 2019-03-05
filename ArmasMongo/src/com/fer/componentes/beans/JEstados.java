package com.fer.componentes.beans;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JEstados extends JPanel {
	public JLabel lbEstado;

	/**
	 * Create the panel.
	 */
	public JEstados() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lbEstado = new JLabel("");
		add(lbEstado);

	}
	public void setMensajeConfirmacion(String mensaje) {
		lbEstado.setForeground(Color.BLACK);
		lbEstado.setText(mensaje);
	}
	public void setMensajeError(String mensaje) {
		lbEstado.setForeground(Color.RED);
		lbEstado.setText(mensaje);
	}

}
