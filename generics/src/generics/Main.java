package generics;

/**
 * Para más documentación mirar:
 * https://docs.oracle.com/javase/tutorial/java/generics/index.html
 */

public class Main {

	public static void main(String[] args) {
		
		System.out.println("-------------------------------");
		System.out.println("Uso de un contenedor de objetos");
		System.out.println("-------------------------------");

		ContenedorObjeto c = new ContenedorObjeto();
		c.set("Algun contenido");
		//Es necesario usar un cast cuando queramos recuperar los objetos
		String valor = (String)c.get();
		System.out.println("Cast exitoso: " + valor);
		
		try {
			int v = (int)c.get();
		}
		catch (Exception e){
			System.out.println("El cast Falló:");
			e.printStackTrace();
		}
		
	}

}
