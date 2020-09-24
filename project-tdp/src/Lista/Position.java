package Lista;

/**
 * Interface Position que modela el encapsulamiento de una posicion
 * @author Maslein Mart�n
 * @author Borromei Nicol�s
 * Departamento de Cs. e Ing. de la Computaci�n, UNS
 * @param <E> tipo generico del elemento de la posici�n
 */
public interface Position<E> {
	
	/**
	 * Retorna el elemento de tipo generico E que contiene la Posici�n
	 * @return elemento que contiene la posici�n
	 */
	public E element();
}