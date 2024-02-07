package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import vista.Vista;

public class Controlador implements ActionListener{
	Vista vista = new Vista();
	@Override
	public void actionPerformed(ActionEvent e) {
		SessionFactory SF = null;
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			SF = configuration.buildSessionFactory();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public Controlador(Vista frame) {
		
	}
	public void consultaPais() {
		
	}
}
