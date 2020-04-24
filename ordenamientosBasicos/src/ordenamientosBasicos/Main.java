package ordenamientosBasicos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeMap;


public class Main {
	
	
	public static void main(String[] args) {
		
		Dinero lista1 [] = {new Dinero(10, 5), new Dinero(4, 5)}; 
		Dinero lista2 [] = {new Dinero(10, 5), new Dinero(4, 5)}; 
		Dinero lista3 [] = {new Dinero(10, 2), new Dinero(4, 5)}; 

		
		//Ordenar con .compareTo()
		Arrays.sort(lista1);
		System.out.println(Arrays.toString(lista1));

		//Ornder con Comparator
		Arrays.sort(lista2, new Dinero.ComparadorDolar());
		System.out.println(Arrays.toString(lista2));

		Arrays.sort(lista3, new Dinero.ComparadorCentavos());
		System.out.println(Arrays.toString(lista3));

		
		TreeMap<Integer, Dinero> treeOrdenadoPorDolar = new TreeMap<>();
		treeOrdenadoPorDolar.put(12, new Dinero(12,2));
		treeOrdenadoPorDolar.put(10, new Dinero(10,1));
		treeOrdenadoPorDolar.put(11, new Dinero(11,0));
		
		System.out.println();
		System.out.println("--- Ordenar por Dolar---");
		for(int k: treeOrdenadoPorDolar.keySet()) {
			System.out.println(treeOrdenadoPorDolar.get(k));
		}
		
		TreeMap<Integer, Dinero> treeOrdenadoPorCentavo = new TreeMap<>();
		treeOrdenadoPorCentavo.put(2, new Dinero(12,2));
		treeOrdenadoPorCentavo.put(1, new Dinero(10,1));
		treeOrdenadoPorCentavo.put(0, new Dinero(11,0));
		
		System.out.println();
		System.out.println("--- Ordenar por Centavo---");
		for(int k: treeOrdenadoPorCentavo.keySet()) {
			System.out.println(treeOrdenadoPorCentavo.get(k));
		}
		
		TreeMap<Dinero, String> treeCompuesto = new TreeMap<>(new Dinero.ComparadorDolar());
		treeCompuesto.put(new Dinero(12, 1), "1");
		treeCompuesto.put(new Dinero(10, 3), "2");
		treeCompuesto.put(new Dinero(11, 9), "3");
		
		System.out.println();
		System.out.println("--- Ordenar por Dolar---");
		for(Dinero k: treeCompuesto.keySet()) {
			System.out.println(treeCompuesto.get(k));
		}

		
	}
	

}
