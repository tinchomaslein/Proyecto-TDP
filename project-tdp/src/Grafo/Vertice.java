package Grafo;

import Lista.Position;

public class Vertice<V>{

	protected V rotulo;
	protected Position<Vertice<V>> posicionEnListaVertices;

	public Vertice(V v) {
		rotulo = v;
	}

	public Vertice(V rotulo, Position<Vertice<V>> posicion) {
		posicionEnListaVertices = posicion;
		this.rotulo = rotulo;
	}

	public void setPosicion(Position<Vertice<V>> e) {
		posicionEnListaVertices = e;
	}

	public void setElement(V rotulo) {
		this.rotulo = rotulo;
	}

	public Position<Vertice<V>> getPosicion() {
		return posicionEnListaVertices;
	}

	public V element() {
		return rotulo;
	}
}