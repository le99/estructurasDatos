package test;

import java.util.ArrayList;

public class Main {

	static int DATOS_A_INSERTAR = 50*1000*1000;
	static int DATOS_A_ELIMAR = 1000;

	public static void main(String[] args) {

		long start;
		long finish;

		start = System.currentTimeMillis();
		ArrayList<Integer> datos = new ArrayList<>();
		for(int n = 0; n < DATOS_A_INSERTAR; n++) {
			datos.add(n);
		}
		finish = System.currentTimeMillis();
		System.out.println("Tiempo para insertar " + DATOS_A_INSERTAR + " datos: " + (finish - start)/1000 + "s");

		start = System.currentTimeMillis();
		for(int n = 0; n < DATOS_A_ELIMAR; n++) {
			datos.remove(datos.size()-1);
		}
		finish = System.currentTimeMillis();
		System.out.println("Tiempo para elminar al final " + DATOS_A_ELIMAR + " datos: " + (finish - start) + "ms");


		start = System.currentTimeMillis();
		for(int n = 0; n < DATOS_A_ELIMAR; n++) {
			datos.remove(0);
		}
		finish = System.currentTimeMillis();
		System.out.println("Tiempo para elminar al comienzo " + DATOS_A_ELIMAR + " datos: " + (finish - start) + "ms");

	}
}
