package Lista;

/**
 * Clase DNode, implementa Position y guarda un elemento y dos referencias a nodos (anterior y siguiente)
 * @author Maslein Martín
 * @author Borromei Nicolás
 * Departamento de Cs. e Ing. de la Computación, UNS
 * @param <E> tipo genérico del nodo
 */
public class DNode<E> implements Position<E> {
	
	/**
	 * Referencia a los nodos next (Siguiente) y prev (anterior)
	 */
	private DNode<E> next, prev;
	
	/**
	 * elemento de tipo generico E contenido en el nodo
	 */
	private E element;
	
	/**
	 * Constructor vacío
	 */
	public DNode() {
		next = null;
		prev = null;
		element = null;
	}
	
	/**
	 * Constructor completo, crea un nodo con un elemento y asigna las referencias de los nodos anterior y siguiente
	 * @param e Elemento a guardar en el nodo
	 * @param p Nodo previo a guardar como referencia (prev)
	 * @param n Nodo siguiente a guardar como referencia (next)
	 */
	public DNode(E e, DNode<E> p, DNode<E> n) {
		element = e;
		prev = p;
		next = n;
	}
	
	/**
	 * Retorna el nodo previo al que hacia referencia prev
	 * @return  El nodo anterior
	 */
	public DNode<E> getPrev() {
		return prev;
	}
	
	/**
	 * Retorna el nodo siguiente mediante la referencia guardada
	 * @return el nodo siguiente
	 */
	public DNode<E> getNext() {
		return next;
	}
	
	/**
	 * Retorna el elemento de tipo generico del nodo
	 * @return E elemento del nodo
	 */
	public E element() {
		return element;
	}
	
	/**
	 * Guarda una referencia (prev) al nodo pasado por parametro, si ya tenia una referencia asignada esta se reemplaza
	 * @param n El nodo que se guarda como referencia de anterior
	 */
	public void setPrev(DNode<E> n) {
		prev = n;
	}
	
	/**
	 * Guarda una referencia (next) al nodo pasado por parametro, si ya tenia una referencia asignada esta se reemplaza
	 * @param n El nodo que se guarda como referencia de siguiente
	 */
	public void setNext(DNode<E> n) {
		next = n;
	}
	
	/**
	 * Inserta un elemento en el nodo, si el nodo ya tenia elemento lo reemplaza
	 * @param e elemento a insertar
	 */
	public void setElement(E e) {
		element = e;
	}
}