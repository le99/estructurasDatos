package generics;

public class MetodosGenericos {
	public static <K, V> void imprimirTupla(Tupla<K, V> tupla) {
		System.out.println("{");
		System.out.println("\tvalor1:" + tupla.get1() + ",");
		System.out.println("\tvalor2:" + tupla.get2());
		System.out.println("}");
	}
}
