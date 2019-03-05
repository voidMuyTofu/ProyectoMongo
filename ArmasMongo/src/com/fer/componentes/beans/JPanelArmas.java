package com.fer.componentes.beans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.fer.base.Arma;
import com.fer.base.Personaje;

public class JPanelArmas extends JPanel implements ActionListener,MouseListener {
	public JLabel lblNombre;
	public JLabel lblDuracion;
	public JLabel lblNewLabel;
	public JTextField tfNombre;
	public JTextField tfDuracion;
	public JTextField tfAtaque;
	public JBotonesCrud botonesCrud;
	public JScrollPane scrollPane;
	public JList listaArmas;
	public DefaultListModel<Arma> modeloLista;
	
	
	private boolean activo;
	/**
	 * Create the panel.
	 */
	public JPanelArmas() {
		setLayout(null);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(21, 27, 46, 14);
		add(lblNombre);
		
		lblDuracion = new JLabel("Duracion");
		lblDuracion.setBounds(21, 52, 73, 14);
		add(lblDuracion);
		
		lblNewLabel = new JLabel("Ataque");
		lblNewLabel.setBounds(21, 77, 46, 14);
		add(lblNewLabel);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(104, 24, 163, 20);
		add(tfNombre);
		tfNombre.setColumns(10);
		
		tfDuracion = new JTextField();
		tfDuracion.setBounds(104, 49, 163, 20);
		add(tfDuracion);
		tfDuracion.setColumns(10);
		
		tfAtaque = new JTextField();
		tfAtaque.setBounds(104, 74, 163, 20);
		add(tfAtaque);
		tfAtaque.setColumns(10);
		
		botonesCrud = new JBotonesCrud();
		botonesCrud.setBounds(42, 118, 209, 115);
		botonesCrud.addListeners(this);
		add(botonesCrud);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(277, 24, 158, 209);
		add(scrollPane);
		
		modeloLista = new DefaultListModel<Arma>();
		
		listaArmas = new JList();
		scrollPane.setViewportView(listaArmas);
		listaArmas.setModel(modeloLista);
		
		listaArmas.addMouseListener(this);
		modoEdicion(false);
		refrescarLista();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "nuevo":
			
			modoEdicion(true);		
			activo = false;
			
			break;
		case "guardar":
			
			
			String nombre = tfNombre.getText();
			int duracion = Integer.parseInt(tfDuracion.getText());
			int ataque = Integer.parseInt(tfAtaque.getText());
			Arma arma = new Arma();
			arma.setNombre(nombre);
			arma.setAtaque(ataque);
			arma.setDuracion(duracion);
			
			if(!activo) {
				Modelo modelo = new Modelo();
				modelo.guardarArma(arma);
				refrescarLista();
				blanquearCajas();
			}
			else {
				Modelo modelo = new Modelo();
				modelo.guardarArma((Arma) listaArmas.getSelectedValue());
				refrescarLista();
				blanquearCajas();
			}
			break;
		case "eliminar":
			
			Modelo modelo = new Modelo();
			Arma armaEliminar = (Arma) listaArmas.getSelectedValue();
			modelo.eliminarArma(armaEliminar);
			refrescarLista();
			break;
		case "cancelar":
			
			modoEdicion(false);
			
			break;
		case "modificar":
			
			activo = true;
			modoEdicion(true);
			
			break;
		}
	}
	public void modoEdicion(boolean activo) {
		if(activo) {
			botonesCrud.btNuevo.setEnabled(false);
			botonesCrud.btModificar.setEnabled(false);
			botonesCrud.btEliminar.setEnabled(false);
			botonesCrud.btGuardar.setEnabled(true);
			botonesCrud.btCancelar.setEnabled(true);
			
			apagarCajas(true);
			
			
		}else {
			botonesCrud.btNuevo.setEnabled(true);
			botonesCrud.btModificar.setEnabled(false);
			botonesCrud.btEliminar.setEnabled(false);
			botonesCrud.btCancelar.setEnabled(false);
			botonesCrud.btGuardar.setEnabled(false);
			
			apagarCajas(false);
		}
	}
	private void refrescarLista() {
		modeloLista.clear();
		Modelo modelo = new Modelo();
		for (Arma arma : modelo.getArmas()) {
			modeloLista.addElement(arma);
		}
	}
	private void blanquearCajas() {
		tfNombre.setText("");
		tfDuracion.setText("");
		tfAtaque.setText("");
	}
	public void apagarCajas(boolean activo) {
		if (activo) {
			tfNombre.setEditable(true);
			tfDuracion.setEditable(true);
			tfAtaque.setEditable(true);
		}else {
			tfNombre.setEditable(false);
			tfDuracion.setEditable(false);
			tfAtaque.setEditable(false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Arma arma = (Arma) listaArmas.getSelectedValue();
		tfNombre.setText(arma.getNombre());
		tfDuracion.setText(String.valueOf(arma.getDuracion()));
		tfAtaque.setText(String.valueOf(arma.getAtaque()));
		modoEdicion(true);
		botonesCrud.btModificar.setEnabled(true);
		botonesCrud.btEliminar.setEnabled(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
