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
import persistencias.PorcentajesRangoedad;
import persistencias.Votos_Cantantes;
import persistencias.PaisesVotos;
import persistencias.Paises_Votos;

@SuppressWarnings("deprecation")
public class Controlador implements ActionListener{
	Vista vista = new Vista();
	ArrayList<Paises_Votos> PV = new ArrayList();
	ArrayList<Votos_Cantantes> VC = new ArrayList();
	@Override
	public void actionPerformed(ActionEvent e) {
		SessionFactory SF = null;
		try {
				Controlador helper = new Controlador(vista);
				Configuration configuration = new Configuration();
				configuration.configure("hibernate.cfg.xml");
				SF = configuration.buildSessionFactory();
			if (e.getSource() == this.vista.votos) {
				helper.consultaPais(SF);
			} 
			if (e.getSource() == this.vista.ganador) {
				helper.sacarGnador(SF);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public Controlador(Vista frame) {
		vista=frame;
		this.vista.votos.addActionListener(this);
		this.vista.ganador.addActionListener(this);
	}
	public void sacarGnador(SessionFactory SF) {
		Session sesion = null;
		try {
			rellenarCantantes();
			sesion = SF.getCurrentSession();
			sesion.beginTransaction();
			Query query = sesion.createQuery("FROM PaisesVotos");
			List<PaisesVotos> PV2 = query.list();
			for (int i = 0; i < PV2.size(); i++) {
				for (int j = 0; j < VC.size(); j++) {
					if (PV2.get(i).getPrimerPuesto().equalsIgnoreCase(VC.get(j).getPais())) {
						VC.get(j).setVotos(VC.get(j).getVotos() + 15);
					}
					if (PV2.get(i).getSegundoPuesto().equalsIgnoreCase(VC.get(j).getPais())) {
						VC.get(j).setVotos(VC.get(j).getVotos() + 10);
					}
					if (PV2.get(i).getTerceroPuesto().equalsIgnoreCase(VC.get(j).getPais())) {
						VC.get(j).setVotos(VC.get(j).getVotos() + 8);
					}
				}
			}
			Collections.sort(VC, Comparator.comparingInt(Votos_Cantantes::getVotos).reversed());
			for (int j = 0; j < VC.size(); j++) {
				System.out.println(VC.get(j).toString());
			}
			sesion.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (sesion != null) {
				sesion.getTransaction().rollback();
			}
		} finally {
			if (sesion == null) {
				try {
					sesion.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	public void consultaPais(SessionFactory SF) {
		Session sesion = null;
		int votantes;
		int votos;
		String paisvoto;
		try {
			InetSocketAddress direcion = new InetSocketAddress("localhost", 9876);
			sesion = SF.getCurrentSession();
			sesion.beginTransaction();
			Query query = sesion.createQuery("FROM PorcentajesRangoedad");
			List<PorcentajesRangoedad> PR = query.list();
			rellenar();
			for (int i = 0; i < PR.size(); i++) {
				votantes = PR.get(i).getRango1825();
				votos = (PR.get(i).getTotalHabitantes() * votantes/100)/500000;
				votantes(PR.get(i).getNombrePais(), votos, "25");
				votantes = PR.get(i).getRango2640();
				votos = (PR.get(i).getTotalHabitantes() * votantes/100)/500000;
				votantes(PR.get(i).getNombrePais(), votos, "40");
				votantes = PR.get(i).getRango4165();
				votos = (PR.get(i).getTotalHabitantes() * votantes/100)/500000;
				votantes(PR.get(i).getNombrePais(), votos, "65");
				votantes = PR.get(i).getRangoMas66();
				votos = (PR.get(i).getTotalHabitantes() * votantes/100)/500000;
				votantes(PR.get(i).getNombrePais(), votos, "66");
				Collections.sort(PV, Comparator.comparingInt(Paises_Votos::getVotos).reversed());
				PaisesVotos pv = new PaisesVotos();
				pv.setPais(PR.get(i).getNombrePais());
				pv.setPrimerPuesto(PV.get(0).getNombre());
				pv.setSegundoPuesto(PV.get(1).getNombre());
				pv.setTerceroPuesto(PV.get(2).getNombre());
				sesion.saveOrUpdate(pv);
				for (int j = 0; j < PV.size(); j++) {
					PV.get(j).setVotos(0);
				}
			}
			sesion.getTransaction().commit();
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
	private void votantes(String nombre, int votos, String rango) throws IOException {
		InetSocketAddress direcion;
		String pais = "";
		for (int i = 0; i < votos; i++) {
			direcion = new InetSocketAddress("localhost", 9876);
			Socket socket = new Socket();
			socket.connect(direcion);
			PrintWriter PW = new PrintWriter(socket.getOutputStream());
			PW.write(nombre + "\n");
			PW.write(rango + "\n");
			PW.flush();
			InputStreamReader ISR = new InputStreamReader(socket.getInputStream());
			BufferedReader BR = new BufferedReader(ISR);
			pais = BR.readLine();
			for (int j = 0; j < PV.size(); j++) {
				if (PV.get(j).getNombre().equalsIgnoreCase(pais)) {
					PV.get(j).setVotos(PV.get(j).getVotos()+1);
				}
			}
		}
	}
	public void rellenar() {
		PV.add( new Paises_Votos("Espania", 0));
		PV.add( new Paises_Votos("Alemania", 0));
		PV.add( new Paises_Votos("Francia", 0));
		PV.add( new Paises_Votos("Italia", 0));
		PV.add( new Paises_Votos("Portugal", 0));
		PV.add( new Paises_Votos("Reino Unido", 0));
		PV.add( new Paises_Votos("Polonia", 0));
		PV.add( new Paises_Votos("Paises Bajos", 0));
		PV.add( new Paises_Votos("Rumania", 0));
		PV.add( new Paises_Votos("Grecia", 0));
	}
	public void rellenarCantantes() {
		VC.add(new Votos_Cantantes("Joen", "Espania", 0));
		VC.add(new Votos_Cantantes("Hilda", "Alemania", 0));
		VC.add(new Votos_Cantantes("Bastian", "Francia", 0));
		VC.add(new Votos_Cantantes("Gianmarco", "Italia", 0));
		VC.add(new Votos_Cantantes("Matilde", "Portugal", 0));
		VC.add(new Votos_Cantantes("Hanna", "Reino Unido", 0));
		VC.add(new Votos_Cantantes("Anka", "Polonia", 0));
		VC.add(new Votos_Cantantes("Dennis", "Paises Bajos", 0));
		VC.add(new Votos_Cantantes("Amalia", "Rumania", 0));
		VC.add(new Votos_Cantantes("Orelle", "Grecia", 0));
	}
	
}
