package generics.contenedor;

public class ContenedorGenerico<K> implements IContenedorGenerico<K>{
	private K valor;
	
	public K get() {		
		return valor;
	}
	
	public void set(K valor) {
		this.valor = valor;
	}
}
