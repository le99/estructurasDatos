package generics.arreglo2;

public interface IArreglo2Ordenado<K extends Comparable<K>> {
	
	K get(int pos);
	void add(K v);
	void remove(int pos);
	
}
