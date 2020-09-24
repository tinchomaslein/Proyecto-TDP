package Lista;

/**
 * Interface Position que modela el encapsulamiento de una posicion
 * @author Maslein Martín
 * @author Borromei Nicolás
 * Departamento de Cs. e Ing. de la Computación, UNS
 * @param <E> tipo generico del elemento de la posición
 */
public interface Position<E> {
	
	/**
	 * Retorna el elemento de tipo generico E que contiene la Posición
	 * @return elemento que contiene la posición
	 */
	public E element();
}