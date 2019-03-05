package com.fer.componentes.beans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


import com.fer.base.Arma;


import com.fer.base.Personaje;
import javax.swing.JButton;

public class JPanelPersonajes extends JPanel implements ActionListener, MouseListener{
	public JBotonesCrud botonesCrud;
	public JLabel lbNombre;
	public JLabel lbDescripcion;
	public JTextField tfNombre;
	public JTextField tfDescripcion;
	public JLabel lbVida;
	public JLabel lbAtaque;
	public JTextField tfVida;
	public JTextField tfAtaque;
	public JScrollPane scrollPane;
	public JComboPlus comboArmas;
	public JListaBusqueda listaBusqueda;
	public JList listaPersonajes;
	public DefaultListModel<Personaje> modeloLista;
	
	private boolean activo;
	public JButton btRecuperar;
	public JButton btEliminarTodo;
	
	Personaje pers;

	/**
	 * Create the panel.
	 */
	public JPanelPersonajes() {
		setLayout(null);
		
		
		
		botonesCrud = new JBotonesCrud();
		botonesCrud.setBounds(23, 179, 216, 110);
		add(botonesCrud);
		
		lbNombre = new JLabel("Nombre");
		lbNombre.setBounds(10, 11, 46, 14);
		add(lbNombre);
		
		lbDescripcion = new JLabel("Descripcion");
		lbDescripcion.setBounds(10, 36, 79, 14);
		add(lbDescripcion);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(99, 8, 140, 20);
		add(tfNombre);
		tfNombre.setColumns(10);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setBounds(99, 33, 140, 20);
		add(tfDescripcion);
		tfDescripcion.setColumns(10);
		
		lbVida = new JLabel("Vida");
		lbVida.setBounds(10, 59, 46, 14);
		add(lbVida);
		
		lbAtaque = new JLabel("Ataque");
		lbAtaque.setBounds(10, 84, 46, 14);
		add(lbAtaque);
		
		tfVida = new JTextField();
		tfVida.setBounds(99, 56, 140, 20);
		add(tfVida);
		tfVida.setColumns(10);
		
		tfAtaque = new JTextField();
		tfAtaque.setBounds(99, 81, 140, 20);
		add(tfAtaque);
		tfAtaque.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 109, 229, 61);
		add(scrollPane);
		
		modeloLista = new DefaultListModel<>();
		
		listaPersonajes = new JList();
		scrollPane.setViewportView(listaPersonajes);
		listaPersonajes.setModel(modeloLista);
		
		listaBusqueda = new JListaBusqueda();
		listaBusqueda.setBounds(249, 8, 204, 281);
		add(listaBusqueda);
		
		btRecuperar =new JButton("Recuperar ultimo borrado");
		
		btRecuperar.setBounds(23, 308, 198, 23);
		add(btRecuperar);
		btRecuperar.setActionCommand("recuperar");
		btRecuperar.addActionListener(this);
		
		btEliminarTodo = new JButton("Eliminar Todo");
		
		btEliminarTodo.setBounds(244, 308, 198, 23);
		add(btEliminarTodo);
		btEliminarTodo.setActionCommand("eliminar todo");
		btEliminarTodo.addActionListener(this);
		
		inicializar();
		refrescarLista();
		modoEdicion(false);
	}

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "nuevo":
			
			modoEdicion(true);		
			activo = false;
			
			break;
		case "guardar":
			
			String nombre = tfNombre.getText();
			String descripcion = tfDescripcion.getText();
			int ataque = Integer.parseInt(tfAtaque.getText());
			int vida = Integer.parseInt(tfVida.getText());
			
			if(!activo) {
				Personaje personaje = new Personaje();
				personaje.setNombre(nombre);
				personaje.setDescripcion(descripcion);
				personaje.setVida(vida);
				personaje.setAtaque(ataque);
				List<Arma> armas = listaBusqueda.getListadoArmas();
				personaje.setArmas(armas);
				Modelo modelo = new Modelo();
				modelo.guardarPersonaje(personaje);
				refrescarLista();
			}
			else {
						
				pers.setNombre(nombre);
				pers.setDescripcion(descripcion);
				pers.setVida(vida);
				pers.setAtaque(ataque);
				List<Arma> listaArmas = listaBusqueda.getListadoArmas();
				pers.setArmas(listaArmas);
				Modelo modelo = new Modelo();
				modelo.guardarPersonaje(pers);
				refrescarLista();
			}
			
			break;
		case "eliminar":
			
			Personaje pers= (Personaje) listaPersonajes.getSelectedValue();
			Modelo modelo = new Modelo();
			modelo.eliminarPersonaje(pers);
			refrescarLista();
			
			break;
		case "cancelar":
			
			modoEdicion(false);
			blanquearCajas();
			
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
	public void apagarCajas(boolean activo) {
		if (activo) {
			tfNombre.setEditable(true);
			tfVida.setEditable(true);
			tfDescripcion.setEditable(true);
			tfAtaque.setEditable(true);
		}else {
			tfNombre.setEditable(false);
			tfVida.setEditable(false);
			tfDescripcion.setEditable(false);
			tfAtaque.setEditable(false);
		}
	}
	private void cargar (Personaje pers) {
		tfNombre.setText(pers.getNombre());
		tfDescripcion.setText(pers.getDescripcion());
		tfAtaque.setText(String.valueOf(pers.getAtaque()));
		tfVida.setText(String.valueOf(pers.getVida()));	
	}
	private void blanquearCajas() {
		tfNombre.setText("");
		tfDescripcion.setText("");
		tfAtaque.setText("");
		tfVida.setText("");
	}
	private void inicializar() {
		botonesCrud.addListeners(this);
		listaPersonajes.addMouseListener(this);
	}
	
	public void modificar(Personaje personaje, ArrayList<Arma> armas) {
		Session sesion = HibernateUtil.getCurrentSession();
		sesion.beginTransaction();
		armas.clear();
		sesion.save(personaje);
		for (Arma arma : armas) {
			arma.setPersonaje(personaje);
			sesion.save(arma);
		}
		sesion.getTransaction().commit();
		sesion.close();
	}

	private void refrescarLista() {
		modeloLista.clear();
		Modelo modelo = new Modelo();
		for (Personaje personaje : modelo.getPersonajes()) {
			modeloLista.addElement(personaje);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == listaPersonajes) {
			pers = (Personaje) listaPersonajes.getSelectedValue();
			cargar(pers);
			modoEdicion(false);
			listaBusqueda.refrescarLista(pers.getArmas());
			botonesCrud.btNuevo.setEnabled(false);
			botonesCrud.btCancelar.setEnabled(true);
			botonesCrud.btEliminar.setEnabled(true);
			botonesCrud.btModificar.setEnabled(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
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
