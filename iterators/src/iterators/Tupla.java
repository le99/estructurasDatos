package iterators;

import java.util.ArrayList;
import java.util.Iterator;

public class Tupla<T> implements Iterable<T>{
	private T v1;
	private T v2;
	
	public void set1(T v1) {
		this.v1 = v1;
	}
	
	public T get1() {
		return v1;
	}
	
	public void set2(T v2) {
		this.v2 = v2;
	}
	
	public T get2() {
		return v2;
	}

	@Override
	public Iterator<T> iterator() {
		Iterator<T> iter = new TupleIterator<T>(this);
		return iter;
	}
	
	private class TupleIterator<T> implements Iterator<T>{

		private int elementoSiguiente = 1;
		private Tupla<T> tupla;
		
		public TupleIterator(Tupla t) {
			this.tupla = t;
		}
		@Override
		public boolean hasNext() {
			return (elementoSiguiente <=2 );
		}

		@Override
		public T next() {
			T siguiente;
			if(elementoSiguiente == 1) {
				siguiente = tupla.v1;
			}
			else if(elementoSiguiente == 2) {
				siguiente = tupla.v2;
			}
			else {
				siguiente = null;
			}
			elementoSiguiente = elementoSiguiente + 1;
			return siguiente;
		}
		
	}
	
	
}
