package Grafo;

import java.util.Iterator;

import Lista.*;
import java.util.logging.*;

/*
 * Clase grafo que representa un grafo dirigido no pesado.
 */

public class Grafo<V>{

	protected PositionList<Vertice<V>> nodos;
	protected PositionList<Arco<V>> arcos;
	private Logger logger;

	public Grafo() {
		nodos = new DoubleLinkedList<Vertice<V>>();
		arcos = new DoubleLinkedList<Arco<V>>();
		if (logger == null) {
            logger = Logger.getLogger(Grafo.class.getName());
            Handler hnd = new ConsoleHandler();
            
            //logger configurado de manera que solo imprima por consola mensajes de solo unos de los niveles definidos.
            hnd.setLevel(Level.WARNING);
            logger.addHandler(hnd);
            logger.setLevel(Level.WARNING);
            
            
            for (Handler h : logger.getParent().getHandlers())
                h.setLevel(Level.OFF);
        }
	}
	
	/*
	 * Inserta el nodo “node” al grafo, si aún no pertenecía a la estructura.
	 */

	public void addNode(V node) {
        if (existeNodo(node) == null) {
            Vertice<V> n = new Vertice<V>(node, null);
            nodos.addLast(n);
            try {
                n.setPosicion(nodos.last());
            } catch (EmptyListException e) {
                logger.severe("La lista de nodos de la estructura esta vacia");
            }
            logger.info("Se agrego el nodo ("+node+").");
        } else {
            logger.warning("El nodo ("+node+") ya existe.");
        }
    }
	
	/*
	 * Inserta con nodos “node1” y “node2”, si aún no existía el arco y ambos parámetros
	 * son nodos pertenecientes a la estructura.
	 */
	
	public void addEdge(V node1, V node2) {
		Vertice<V> n1 = existeNodo(node1);
		Vertice<V> n2 = existeNodo(node2);
        if (n1 == null)
            logger.warning("El nodo ("+node1+") no existe.");
        else if (n2 == null)
            logger.warning("El nodo ("+node2+") no existe.");
        else if (existeArco(node1, node2)) {
            logger.warning("El arco ("+node1+","+node2+") ya existe.");
        } else {
        	Arco<V> a = new Arco<V>(n1,n2);
            arcos.addLast(a);
            try {
				a.setPosicion(arcos.last());
			} catch (EmptyListException e) {
				logger.severe("Se intento agregar un arco de un tipo erroneo");
			}
            logger.info("Se agrego el arco ("+node1+","+node2+").");
        }
    }
	
	/*
	 * Remueve el nodo “node” del grafo, si el parámetro es un nodo de la estructura.
	 */
	
	public void removeNode(V node1) {
		Vertice<V> node = existeNodo(node1);
		if(node != null) {
			try {
				nodos.remove(node.getPosicion());
			} catch (InvalidPositionException e) {
				logger.severe("Se intento eliminar un nodo de un tipo erroneo");
			}
		} else {
			logger.warning("El nodo ("+node1+") no existe.");
		}
		logger.info("Se elimino el nodo ("+node1+").");
		if(node != null) {
			for (Arco<V> a : arcos) {
	            if (a.getPred().element() == node1 || a.getSuces().element() == node1) {
	                    try {
							arcos.remove(a.getPosicion());
						} catch (InvalidPositionException e) {
							logger.severe("Se intento eliminar un arco pero es de un tipo erroneo.");
						}
	                logger.info("Se elimino el arco ("+ a.getPred().element() + ","+ a.getSuces().element() + ").");
	            }
	        }
		}
	}
	
	/*
	 * Remueve el arco entre “node1” y “node2”, si el arco formado por los parámetros pertenecen a la estructura.
	 */
	
	public void removeEdge(V node1, V node2) {
        Vertice<V> n1 = existeNodo(node1);
        Vertice<V> n2 = existeNodo(node2);
        boolean encontre = false;
        Iterator<Arco<V>> it = arcos.iterator();
        if (n1 == null && n2 == null)
            logger.warning("El arco ("+ node1+","+node2 +") no existe.");
        else
        	if (n1 == null)
        		logger.warning("El nodo ("+node1+") no existe.");
        	else 
        		if (n2 == null)
        			logger.warning("El nodo ("+node2+") no existe.");
        		else {
        			while (it.hasNext() && !encontre) {
        				Arco<V> a = it.next();
        				if (a.getPred().equals(n1) && a.getSuces().equals(n2)) {
        					encontre = true;
        					try {
        						arcos.remove(a.getPosicion());
        					} catch (InvalidPositionException e) {
        						logger.severe("Se intento remover un arco pero el mapeo de arcos esta vacio");
        					}
        					logger.info("Se elimino el arco ("+node1+","+node2+").");
        				}
        			}
        		}
    }
	
	/*
	 * Busca el nodo en la lista de nodos con rotulo "node" y lo retorna , si no existe retorna null.
	 */
	
	private Vertice<V> existeNodo(V node) {
        boolean encontre = false;
        Iterator<Vertice<V>> it = nodos.iterator();
        Vertice<V> v = it.hasNext() ? it.next() : null;
        while (v != null && !encontre) {
            if (v.element() == node)
                encontre = true;
            else
            	v = it.hasNext() ? it.next() : null;
        }
        return v;
    }

	/*
	 * Busca el arco en la lista de arcos con vertices "node1" y "node2", si lo encuentra devuelve true
	 * y si no lo encuentra devuelve false.
	 */
	
	private boolean existeArco(V node1, V node2) {
		boolean encontre = false;
		Iterator<Arco<V>> it = arcos.iterator();
		Arco<V> a1 = it.hasNext() ? it.next() : null;
		while(a1 != null && !encontre) {
			if(a1.getPred().element().equals(node1) && a1.getSuces().element().equals(node2))
				encontre = true;
			else
				a1 = it.hasNext() ? it.next() : null;
		}
		return encontre;
	}
}