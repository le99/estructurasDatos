package generics.arreglo2;


/**
 * 
 * Estructura donde el elemento en la posicion 0 simpre tiene el elemento mas grande segun el metodo compareTo
 * @param <K>
 */
public class Arreglo2Ordenado<K extends Comparable<K>> implements IArreglo2Ordenado<K> {

	
	private K[] arreglo;
	
	public Arreglo2Ordenado() {
		arreglo = (K[]) new Comparable[2];
	}
	
	@Override
	public K get(int pos) {
		if(pos > 1 || pos < 0) {
			throw new IndexOutOfBoundsException("Indice invalido");
		}
		return arreglo[pos];
	}

	/**
	 * Agrega al arreglo si hay espacio
	 */
	@Override
	public void add(K v) {
		if(arreglo[0] == null) {
			arreglo[0] = v;
		}
		else if(arreglo[1] == null) {
			if(arreglo[0].compareTo(v) > 0) {
				arreglo[1] = v;
			}
		}
		else {
			//No hay espacio, no se va ha hacer nada
		}
	}

	@Override
	public void remove(int pos) {
		if(pos > 1 || pos < 0) {
			throw new IndexOutOfBoundsException("Indice invalido");
		}
		arreglo[pos] = null;
		if(pos == 0) {
			arreglo[0] = arreglo[1];
		}
	}
	
	
	
	

}
