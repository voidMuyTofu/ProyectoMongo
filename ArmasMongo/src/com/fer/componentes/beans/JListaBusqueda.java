package com.fer.componentes.beans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.fer.Arma;
import com.fer.Modelo;

public class JListaBusqueda extends JPanel implements ActionListener{
	public JButton btAnadirArma;
	public JScrollPane scrollPane;
	public JList<Arma> listaArmas;
	public JTextField tfBusquedaArma;
	public JButton btBuscar;
	public JComboPlus<Arma> jcArmas;
	public DefaultListModel<Arma> modeloLista;
	public JButton btEliminar;

	public JListaBusqueda() {
		setLayout(null);
		
		btAnadirArma = new JButton("+");
		btAnadirArma.setBounds(111, 10, 41, 23);
		add(btAnadirArma);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 181, 193);
		add(scrollPane);
		
		listaArmas = new JList<>();
		scrollPane.setViewportView(listaArmas);
		modeloLista = new DefaultListModel<>();
		listaArmas.setModel(modeloLista);
		
		tfBusquedaArma = new JTextField();
		tfBusquedaArma.setBounds(10, 246, 131, 20);
		add(tfBusquedaArma);
		tfBusquedaArma.setColumns(10);
		
		btBuscar = new JButton("New button");
		btBuscar.setBounds(151, 245, 41, 23);
		add(btBuscar);
		
		jcArmas = new JComboPlus<>();
		jcArmas.setBounds(10, 10, 97, 23);
		add(jcArmas);
		
		btEliminar = new JButton("-");
		btEliminar.setBounds(151, 10, 40, 23);
		add(btEliminar);
		
		inicializar();
	}
	public void refrescarLista(List<Arma> list) {
		modeloLista.clear();
		for(Arma arma : list) {
			modeloLista.addElement(arma);
		}
	}
	public void inicializar() {
		Modelo modelo = new Modelo();
		List<Arma> armas = modelo.getArmasLibres();
		jcArmas.inicializar(armas);
		
		btAnadirArma.addActionListener(this);
		btAnadirArma.setActionCommand("+");
		btEliminar.addActionListener(this);
		btEliminar.setActionCommand("-");
		btBuscar.addActionListener(this);
		btBuscar.setActionCommand("buscar");
		
		
	}
	public List<Arma> getListadoArmas() {
		List<Arma> armas = new ArrayList<>();
		for(int i = 0; i < modeloLista.size();i++) {
			armas.add((Arma) modeloLista.getElementAt(i));
		}
		return armas;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Arma armaSeleccionada;
		
		switch(e.getActionCommand()){
		case "+":
			armaSeleccionada = jcArmas.getDatoSeleccionado();
			if(armaSeleccionada == null)
				return;
			if(modeloLista.contains(armaSeleccionada))
				return;
			
			modeloLista.addElement(armaSeleccionada);
			
			break;
		case "-":
			armaSeleccionada = listaArmas.getSelectedValue();
			if(armaSeleccionada == null)
				return;
			modeloLista.removeElement(armaSeleccionada);
			armaSeleccionada.setPersonaje(null);
			Modelo modelo = new Modelo();
			modelo.guardarArma(armaSeleccionada);
			jcArmas.addItem(armaSeleccionada);
			
			break;
		case "buscar":
			String busqueda = tfBusquedaArma.getText();
			List<Object> armasListadas = Arrays.asList(modeloLista.toArray());
			for(Object arma : armasListadas) {
				if(busqueda.equals(arma.toString())){
					modeloLista.clear();
					modeloLista.addElement((Arma) arma);
				}
			}
			break;
		}
	}
}
