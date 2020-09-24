package Lista;
import java.util.Iterator;

/**
 * Lista Doblemente Enlazada de tipo generico E, con referencia a su longitud, al primer y ultimo elemento
 * @author Maslein Martín
 * @author Borromei Nicolás
 * Departamento de Cs. e Ing. de la Computación, UNS
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
	 * Consulta si la lista está vacía.
	 * @return boolean verdadero si la lista está vacía, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Devuelve la posición del primer elemento de la lista. 
	 * @return Posición del primer elemento de la lista.
	 * @throws EmptyListException si la lista está vacía.
	 */
	public Position<E> first() throws EmptyListException {
		if (size == 0)
			throw new EmptyListException("No existe primer elemento.");
		return head.getNext();
	}

	/**
	 * Devuelve la posición del último elemento de la lista. 
	 * @return Posición del último elemento de la lista.
	 * @throws EmptyListException si la lista está vacía.
	 * 
	 */
	public Position<E> last() throws EmptyListException {
		if (size == 0)
			throw new EmptyListException("No existe ultimo elemento.");
		return tail.getPrev();
	}

	/**
	 * Devuelve la posición del elemento siguiente a la posición pasada por parámetro.
	 * @param p Posición a obtener su elemento siguiente.
	 * @return Posición del elemento siguiente a la posición pasada por parámetro.
	 * @throws InvalidPositionException si el posición pasada por parámetro es inválida o la lista está vacía.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde al último elemento de la lista.
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
	 * Devuelve la posición del elemento anterior a la posición pasada por parámetro.
	 * @param p Posición a obtener su elemento anterior.
	 * @return Posición del elemento anterior a la posición pasada por parámetro.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o la lista está vacía.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde al primer elemento de la lista.
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
	 * Inserta un elemento antes de la posición pasada como parámetro.
	 * @param p Posición en cuya posición anterior se insertará el elemento pasado por parámetro. 
	 * @param elem Elemento a insertar antes de la posición pasada como parámetro.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.
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
	 * Inserta un elemento luego de la posición pasada por parámatro.
	 * @param p Posición en cuya posición siguiente se insertará el elemento pasado por parámetro.
	 * @param e Elemento a insertar luego de la posición pasada como parámetro.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.
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
	 * Establece el elemento en la posición pasados por parámetro. Reemplaza el elemento que se encontraba anteriormente en esa posición y devuelve el elemento anterior.
	 * @param p Posición a establecer el elemento pasado por parámetro.
	 * @param e Elemento a establecer en la posición pasada por parámetro.
	 * @return E Elemento anterior.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.	 
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
	 * Remueve el elemento que se encuentra en la posición pasada por parámetro.
	 * @param p Posición del elemento a eliminar.
	 * @return E element Elemento removido.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.
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
	 * Devuelve una colección iterable de posiciones.
	 * @return Una colección iterable de posiciones.
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
	 * Castea la posición p pasada por parámetro a un Dnode
	 * @param p Posición a castear
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