package Lista;
import java.util.Iterator;

/**
 * Lista Doblemente Enlazada de tipo generico E, con referencia a su longitud, al primer y ultimo elemento
 * @author Maslein Mart�n
 * @author Borromei Nicol�s
 * Departamento de Cs. e Ing. de la Computaci�n, UNS
 * @param <E> Tipo generico de la lista
 */
public class DoubleLinkedList<E> implements PositionList<E> {
	
	/**
	 * referencia al primer elemento
	 */
	protected DNode<E> head;
	/**
	 * referencia al segundo elemento
	 */
	protected DNode<E> tail;
	/**
	 * referencia a la longitud del atributo
	 */
	protected int size;

	/**
	 * Constructor inicializa la lista vacia
	 * Enlaza head y tail (el siguiente de head es el tail, y el anterior del tail es head)
	 */
	public DoubleLinkedList() {
		head = new DNode <E> (null, null, null);
		tail = new DNode <E> (null, head, null);
		head.setNext(tail);
		size = 0;
	}

	/**
	 * Consulta la cantidad de elementos de la lista.
	 * @return int cantidad de elementos de la lista.
	 */
	public int size() {
		return size;
	}

	/**
	 * Consulta si la lista est� vac�a.
	 * @return boolean verdadero si la lista est� vac�a, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Devuelve la posici�n del primer elemento de la lista. 
	 * @return Posici�n del primer elemento de la lista.
	 * @throws EmptyListException si la lista est� vac�a.
	 */
	public Position<E> first() throws EmptyListException {
		if (size == 0)
			throw new EmptyListException("No existe primer elemento.");
		return head.getNext();
	}

	/**
	 * Devuelve la posici�n del �ltimo elemento de la lista. 
	 * @return Posici�n del �ltimo elemento de la lista.
	 * @throws EmptyListException si la lista est� vac�a.
	 * 
	 */
	public Position<E> last() throws EmptyListException {
		if (size == 0)
			throw new EmptyListException("No existe ultimo elemento.");
		return tail.getPrev();
	}

	/**
	 * Devuelve la posici�n del elemento siguiente a la posici�n pasada por par�metro.
	 * @param p Posici�n a obtener su elemento siguiente.
	 * @return Posici�n del elemento siguiente a la posici�n pasada por par�metro.
	 * @throws InvalidPositionException si el posici�n pasada por par�metro es inv�lida o la lista est� vac�a.
	 * @throws BoundaryViolationException si la posici�n pasada por par�metro corresponde al �ltimo elemento de la lista.
	 */
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		if (p == null)
			throw new InvalidPositionException("Posicion invalida.");
		DNode<E> n = checkPosition(p);
		if (n == tail.getPrev())
			throw new BoundaryViolationException("No existe siguiente elemento.");
		return n.getNext();
	}

	/**
	 * Devuelve la posici�n del elemento anterior a la posici�n pasada por par�metro.
	 * @param p Posici�n a obtener su elemento anterior.
	 * @return Posici�n del elemento anterior a la posici�n pasada por par�metro.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o la lista est� vac�a.
	 * @throws BoundaryViolationException si la posici�n pasada por par�metro corresponde al primer elemento de la lista.
	 */
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		if (p == null)
			throw new InvalidPositionException("Posicion invalida.");
		DNode<E> n = checkPosition(p);
		if (n == head.getNext())
			throw new BoundaryViolationException("No existe previo elemento.");
		return n.getPrev();
	}

	/**
	 * Inserta un elemento al principio de la lista.
	 * @param elem Elemento a insertar al principio de la lista.
	 */
	public void addFirst(E elem) {
		DNode <E> n = new DNode <E> (elem, head, head.getNext());
		head.getNext().setPrev(n);
		head.setNext(n);
		size++;
	}
	
	/**
	 * Inserta un elemento al final de la lista.
	 * @param elem Elemento a insertar al final de la lista.
	 */
	public void addLast(E elem) {
		DNode <E> n = new DNode <E> (elem, tail.getPrev(), tail);
		tail.getPrev().setNext(n);
		tail.setPrev(n);
		size++;
	}
	
	/**
	 * Inserta un elemento antes de la posici�n pasada como par�metro.
	 * @param p Posici�n en cuya posici�n anterior se insertar� el elemento pasado por par�metro. 
	 * @param elem Elemento a insertar antes de la posici�n pasada como par�metro.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.
	 */
	public void addBefore(Position <E> p, E elem) throws InvalidPositionException {
		if (p == null)
			throw new InvalidPositionException("Posicion invalida.");
		DNode <E> n = checkPosition(p);
		DNode <E> m = new DNode <E> (elem, n.getPrev(), n);
		n.getPrev().setNext(m);
		n.setPrev(m);
		size++;
	}
	
	/**
	 * Inserta un elemento luego de la posici�n pasada por par�matro.
	 * @param p Posici�n en cuya posici�n siguiente se insertar� el elemento pasado por par�metro.
	 * @param e Elemento a insertar luego de la posici�n pasada como par�metro.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.
	 */
	public void addAfter(Position <E> p, E e) throws InvalidPositionException {
		if (p == null)
			throw new InvalidPositionException("Posicion invalida.");
		DNode <E> n = checkPosition(p);
		DNode <E> m = new DNode <E> (e, n, n.getNext());
		n.getNext().setPrev(m);
		n.setNext(m);		
		size++;
	}
	
	/**
	 * Establece el elemento en la posici�n pasados por par�metro. Reemplaza el elemento que se encontraba anteriormente en esa posici�n y devuelve el elemento anterior.
	 * @param p Posici�n a establecer el elemento pasado por par�metro.
	 * @param e Elemento a establecer en la posici�n pasada por par�metro.
	 * @return E Elemento anterior.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.	 
	 */
	public E set(Position <E> p ,E e) throws InvalidPositionException {
	 	if (size == 0) 
	 		throw new InvalidPositionException("No existe elemento para setear.");
	 	if (p == null)
			throw new InvalidPositionException("Posicion invalida.");
	 	DNode <E> n = checkPosition(p);
	 	E toReturn = p.element();
	 	n.setElement(e);
	 	return toReturn;
	}
	
	/**
	 * Remueve el elemento que se encuentra en la posici�n pasada por par�metro.
	 * @param p Posici�n del elemento a eliminar.
	 * @return E element Elemento removido.
	 * @throws InvalidPositionException si la posici�n es inv�lida o la lista est� vac�a.
	 */	
	public E remove (Position <E> p) throws InvalidPositionException {
		if (size == 0)
			throw new InvalidPositionException("No existe elemento para remover.");
		if (p == null)
			throw new InvalidPositionException("Posicion invalida.");
		DNode <E> n = checkPosition(p);
		E toReturn = p.element();
		n.getNext().setPrev(n.getPrev());
		n.getPrev().setNext(n.getNext());
		size--;
		return toReturn;
	}

	/**
	 * Devuelve un un iterador de todos los elementos de la lista.
	 * @return Un iterador de todos los elementos de la lista.
	 */
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	/**
	 * Devuelve una colecci�n iterable de posiciones.
	 * @return Una colecci�n iterable de posiciones.
	 */
	public Iterable<Position<E>> positions() {
		PositionList <Position<E>> L = new DoubleLinkedList <Position <E>> ();
		if (!isEmpty()) {
			Position <E> r = head.getNext();
			while (r != tail.getPrev()) {
				L.addLast(r);
				try {
					r = next(r);
				} catch (InvalidPositionException | BoundaryViolationException e) {
					System.out.println(e);
				}
			}
			L.addLast(r);
		}
		return L;
	}
	
	/**
	 * Castea la posici�n p pasada por par�metro a un Dnode
	 * @param p Posici�n a castear
	 * @return la posicion casteada a dnode
 	 * @throws InvalidPositionException si el casteo no se pudo realizar
	 */
	private DNode<E> checkPosition(Position<E> p) throws InvalidPositionException {
		DNode<E> toReturn = null;
		try {
			toReturn = (DNode<E>) p;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("No fue posible el casteo.");
		}
		return toReturn;
	}
}