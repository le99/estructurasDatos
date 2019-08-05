package iterators;

import java.util.ArrayList;
import java.util.List;

/**
 * Para más documentación mirar:
 * https://docs.oracle.com/javase/tutorial/java/generics/index.html
 */

public class Main {

	public static void main(String[] args) {
				
		System.out.println("-------------------------------");
		System.out.println("Iterador generico");
		System.out.println("-------------------------------");

		Tupla<String> t = new Tupla<String>();
		t.set1("Hello");
		t.set2("World");
		for(String s: t) {
			System.out.println(s);
		}
						
	}

}
