package generics;

/**
 * Para más documentación mirar:
 * https://docs.oracle.com/javase/tutorial/java/generics/index.html
 */

public class Main {

	public static void main(String[] args) {
		
		System.out.println("-------------------------------");
		System.out.println("Uso de un ContenedorObjeto");
		System.out.println("-------------------------------");

		ContenedorObjeto c = new ContenedorObjeto();
		c.set("Algun contenido");
		//Es necesario usar un cast cuando queramos recuperar los objetos
		String valor = (String)c.get();
		System.out.println("Cast exitoso");
		System.out.println("Valor: " + valor);
		System.out.println();

		try {
			int v = (int)c.get();
		}
		catch (Exception e){
			System.out.println("El cast Falló:");
			e.printStackTrace();
		}
		
		
		System.out.println("-------------------------------");
		System.out.println("Uso de un Contenedor<String>");
		System.out.println("-------------------------------");

		Contenedor<String> cs = new Contenedor<>();
		cs.set("Otro contenido");
		valor = cs.get();
		System.out.println("No se necseito hacer un cast");
		System.out.println("Valor: " + valor);

		
		System.out.println("-------------------------------");
		System.out.println("Uso de una Tupla<String, Integer>");
		System.out.println("-------------------------------");
		
		Tupla<String, Integer> t = new Tupla<>();
		t.set1("Valor 1");
		t.set2(new Integer(45));
		String valor1 = t.get1();
		Integer valor2 = t.get2();
		System.out.println("Valores de la tupla");
		System.out.println("valor1: "+ valor1);
		System.out.println("valor2: "+ valor2);

		System.out.println("-------------------------------");
		System.out.println("Uso del metodo Generico imprimirTupla");
		System.out.println("-------------------------------");
		MetodosGenericos.imprimirTupla(t);
		
		
		System.out.println("-------------------------------");
		System.out.println("Uso de ContenedorNumero");
		System.out.println("(Integer, Double, Float son sublases de Number)");
		System.out.println("-------------------------------");
		
		
		System.out.println("No se puede crear ContenedorNumero<String>");
		//ContenedorNumero<String> cn = new ContenedorNumero<String>();
		
		System.out.println("Se puede crear un contenedor ContenedorNumero<Number> ");
		ContenedorNumero<Number> cn1 = new ContenedorNumero<>();
		cn1.set(new Integer(32));
		Number v3 = cn1.get();
		System.out.println("Valor: " + v3);
		
		System.out.println("Se puede crear un contenedor ContenedorNumero<Integer> ");
		ContenedorNumero<Integer> cn2 = new ContenedorNumero<>();
		cn2.set(new Integer(32));
		Integer v4 = cn2.get();
		System.out.println("Valor: " + v4);

		System.out.println("Se puede crear un contenedor ContenedorNumero<Double> ");
		ContenedorNumero<Double> cn3 = new ContenedorNumero<>();
		cn3.set(new Double(32.0));
		Double v5 = cn3.get();
		System.out.println("Valor: " + v5);
		
	}

}
