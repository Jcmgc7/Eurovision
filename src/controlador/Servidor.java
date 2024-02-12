package controlador;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		ServerSocket servidorEscuchar = null;
		try {
			servidorEscuchar = new ServerSocket(9876);
			System.out.println("Conexion hecha correctamente.");
			while (true) {
				try {
					Socket socket = servidorEscuchar.accept();
					Peticion hilo = new Peticion(socket);
					hilo.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (servidorEscuchar != null) {
				try {
					servidorEscuchar.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}
