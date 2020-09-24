package Lista;
import java.util.*;

/**
 * Clase ElementIterator genera un Iterador con los elementos de una lista de posiciones
 * @author Maslein Martín
 * @author Borromei Nicolás
 * Departamento de Cs. e Ing. de la Computación, UNS
 * @param <E> Tipo genérico E
 */
public class ElementIterator<E> implements Iterator<E> {
	
	/**
	 * Lista de posiciones a iterar
	 */
	protected PositionList<E> list;
	
	/**
	 * Un cursor (posicion) que apunta a un elemento de la lista
	 */
	protected Position<E> cursor;
	
	/**
	 * Constructor
	 * Recibe una lista por parametro y la guarda en el atributo list, también inicializa el atributo cursor en el primer elemento de la lista
	 * Si la lista esta vacía el cursor no se podría inicializar por lo tanto captura la excepcion (EmptyListException)
	 * @param l Una lista de posiciones
	 */
	public ElementIterator(PositionList <E> l) {
		list=l;
		if(list.isEmpty())
			cursor=null;
		else
			try {
				cursor=list.first();
			} catch (EmptyListException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Retorna true si hay un elemento a leer en la lista, es decir cuando el cursor no es una referencia nula
	 * @return boolean true si hay un elemento a leer en la lista, falso caso contrario
	 */
	public boolean hasNext() {
		return cursor!=null;
	}

	/**
	 * Retorna el elemento al que apunta el cursor en la lista, y si hay mas elementos mueve el cursor a la posicion siguiente
	 * @return E elemento siguiente en la lista
	 */
	public E next() throws NoSuchElementException {
		if(cursor==null)
			throw new NoSuchElementException("Error: No hay siguiente");
		E toReturn= cursor.element();
		try {
			cursor= (cursor==list.last())? null: list.next(cursor);
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

}