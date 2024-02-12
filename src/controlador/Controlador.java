package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.hibernate.query.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vista.Vista;
import persistencia.PaisesVotos;
import persistencia.Paises_Votos;
import persistencia.PorcentajesRangoedad;

@SuppressWarnings("deprecation")
public class Controlador implements ActionListener{
	Vista vista = new Vista();
	ArrayList<Paises_Votos> PV = new ArrayList();
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vista.btnNewButton) {
			SessionFactory SF = null;
			try {
				Controlador helper = new Controlador(vista);
				Configuration configuration = new Configuration();
				configuration.configure("hibernate.cfg.xml");
				SF = configuration.buildSessionFactory();
				helper.consultaPais(SF);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	public Controlador(Vista frame) {
		vista=frame;
		this.vista.btnNewButton.addActionListener(this);
	}
	public void consultaPais(SessionFactory SF) {
		Session sesion = null;
		int votantes;
		int votos;
		String paisvoto;
		try {
			InetSocketAddress direcion = new InetSocketAddress("loclahost", 9876);
			sesion = SF.getCurrentSession();
			sesion.beginTransaction();
			Query query = sesion.createQuery("FROM PorcentajesRangoedad");
			List<PorcentajesRangoedad> PR = query.list();
			for (int i = 0; i < PR.size(); i++) {
				rellenar();
				votantes = PR.get(i).getRango1825();
				votos = (PR.get(i).getTotalHabitantes() * votantes/100)/500000;
				paisvoto = votantes(PR, i, votos, "25");
				for (int j = 0; j < PV.size(); j++) {
					if (PV.get(j).getNombre().equalsIgnoreCase(paisvoto)) {
						PV.get(j).setVotos(PV.get(j).getVotos()+1);
					}
				}
				votantes = PR.get(i).getRango2640();
				votos = (PR.get(i).getTotalHabitantes() * votantes/100)/500000;
				paisvoto = votantes(PR, i, votos, "40");
				for (int j = 0; j < PV.size(); j++) {
					if (PV.get(j).getNombre().equalsIgnoreCase(paisvoto)) {
						PV.get(j).setVotos(PV.get(j).getVotos()+1);
					}
				}
				votantes = PR.get(i).getRango4165();
				votos = (PR.get(i).getTotalHabitantes() * votantes/100)/500000;
				paisvoto = votantes(PR, i, votos, "65");
				for (int j = 0; j < PV.size(); j++) {
					if (PV.get(j).getNombre().equalsIgnoreCase(paisvoto)) {
						PV.get(j).setVotos(PV.get(j).getVotos()+1);
					}
				}
				votantes = PR.get(i).getRangoMas66();
				votos = (PR.get(i).getTotalHabitantes() * votantes/100)/500000;
				paisvoto = votantes(PR, i, votos, "66");
				for (int j = 0; j < PV.size(); j++) {
					if (PV.get(j).getNombre().equalsIgnoreCase(paisvoto)) {
						PV.get(j).setVotos(PV.get(j).getVotos()+1);
					}
				}
				Collections.sort(PV, Comparator.comparingInt(Paises_Votos::getVotos).reversed());
				for (int j = 0; j < PV.size(); j++) {
					System.out.println(PV.get(i).toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			sesion.getTransaction().rollback();
		} finally  {
			if (sesion != null) {
				try {
					sesion.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	private String votantes(List<PorcentajesRangoedad> PR, int i, int votos, String rango) throws IOException {
		InetSocketAddress direcion;
		String pais = "";
		for (int j = 0; j < votos; j++) {
			direcion = new InetSocketAddress("loclahost", 9876);
			Socket socket = new Socket();
			socket.connect(direcion);
			PrintWriter PW = new PrintWriter(socket.getOutputStream());
			PW.write(PR.get(i).getNombrePais() + "\n");
			PW.write(rango + "\n");
			PW.flush();
			InputStreamReader ISR = new InputStreamReader(socket.getInputStream());
			BufferedReader BR = new BufferedReader(ISR);
			pais = BR.readLine();
		}
		return pais;
	}
	public void rellenar() {
		PV.add( new Paises_Votos("España", 0));
		PV.add( new Paises_Votos("Alemania", 0));
		PV.add( new Paises_Votos("Francia", 0));
		PV.add( new Paises_Votos("Italia", 0));
		PV.add( new Paises_Votos("Portugal", 0));
		PV.add( new Paises_Votos("Reino Unido", 0));
		PV.add( new Paises_Votos("Polonia", 0));
		PV.add( new Paises_Votos("Países Bajos", 0));
		PV.add( new Paises_Votos("Rumanía", 0));
		PV.add( new Paises_Votos("Grecia", 0));
	}
}
