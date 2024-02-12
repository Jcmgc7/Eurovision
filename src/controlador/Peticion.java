package controlador;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Peticion extends Thread{
	Socket socket;

	public Peticion(Socket socket) {
		this.socket = socket;
	}
	public String funcion(String paises, String rango) {
		String pais = "";
		do {
			int aleatorio = (int)(1+Math.random()*100);
			if (rango.equals("25")) {
				if (aleatorio < 7) {
					pais = "España";
				} else if (aleatorio <= 10) {
					pais = "Alemania";
				}  else if (aleatorio <= 23) {
					pais = "Francia";
				}  else if (aleatorio <= 30) {
					pais = "Italia";
				}  else if (aleatorio <= 37) {
					pais = "Portugal";
				}  else if (aleatorio <= 51) {
					pais = "Reino Unido";
				}  else if (aleatorio <= 61) {
					pais = "Polonia";
				}  else if (aleatorio <= 88) {
					pais = "Pises Bajos";
				}  else if (aleatorio <= 95) {
					pais = "Rumania";
				}  else if (aleatorio <= 100) {
					pais = "Grecia";
				}
			} else if (rango.equals("40")) {
				if (aleatorio < 14) {
					pais = "España";
				} else if (aleatorio <= 20) {
					pais = "Alemania";
				}  else if (aleatorio <= 33) {
					pais = "Francia";
				}  else if (aleatorio <= 40) {
					pais = "Italia";
				}  else if (aleatorio <= 46) {
					pais = "Portugal";
				}  else if (aleatorio <= 58) {
					pais = "Reino Unido";
				}  else if (aleatorio <= 65) {
					pais = "Polonia";
				}  else if (aleatorio <= 81) {
					pais = "Pises Bajos";
				}  else if (aleatorio <= 90) {
					pais = "Rumania";
				}  else if (aleatorio <= 100) {
					pais = "Grecia";
				}
			} else if (rango.equals("65")) {
				if (aleatorio < 5) {
					pais = "España";
				} else if (aleatorio <= 15) {
					pais = "Alemania";
				}  else if (aleatorio <= 27) {
					pais = "Francia";
				}  else if (aleatorio <= 33) {
					pais = "Italia";
				}  else if (aleatorio <= 40) {
					pais = "Portugal";
				}  else if (aleatorio <= 54) {
					pais = "Reino Unido";
				}  else if (aleatorio <= 61) {
					pais = "Polonia";
				}  else if (aleatorio <= 71) {
					pais = "Pises Bajos";
				}  else if (aleatorio <= 88) {
					pais = "Rumania";
				}  else if (aleatorio <= 100) {
					pais = "Grecia";
				}
			} else if (rango.equals("66")) {
				if (aleatorio < 3) {
					pais = "España";
				} else if (aleatorio <= 20) {
					pais = "Alemania";
				}  else if (aleatorio <= 25) {
					pais = "Francia";
				}  else if (aleatorio <= 37) {
					pais = "Italia";
				}  else if (aleatorio <= 49) {
					pais = "Portugal";
				}  else if (aleatorio <= 58) {
					pais = "Reino Unido";
				}  else if (aleatorio <= 65) {
					pais = "Polonia";
				}  else if (aleatorio <= 76) {
					pais = "Pises Bajos";
				}  else if (aleatorio <= 91) {
					pais = "Rumania";
				}  else if (aleatorio <= 100) {
					pais = "Grecia";
				}
			}
		} while(paises.equalsIgnoreCase("pais"));
		return pais;
	}
	public void Escuchar() {
		InputStream IS = null;
		InputStreamReader ISR = null;
		BufferedReader BR = null;
		OutputStream OS = null;
		PrintWriter PW = null;
		try {
			IS = socket.getInputStream();
			ISR = new InputStreamReader(IS);
			BR = new BufferedReader(ISR);
			String pais = BR.readLine();
			String rango = BR.readLine();
			String respuesta = funcion(pais, rango);
			OS = socket.getOutputStream();
			PW = new PrintWriter(OS);
			PW.write(respuesta + "\n");
			PW.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (IS != null) {
				try {
					IS.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (ISR != null) {
				try {
					ISR.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (OS != null) {
				try {
					OS.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (BR != null) {
				try {
					BR.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (PW != null) {
				try {
					PW.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}
