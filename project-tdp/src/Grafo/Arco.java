package Grafo;

import Lista.Position;

public class Arco<V> {

	protected Position<Arco<V>> posicionEnListaArcos;
	protected Vertice<V> pred, suces;

	public Arco(Position<Arco<V>> posicion) {
		posicionEnListaArcos = posicion;
	}

	public Arco(Vertice<V> n1, Vertice<V> n2) {
		pred = n1;
		suces = n2;
	}

	public Vertice<V> getPred() {
		return pred;
	}

	public Vertice<V> getSuces() {
		return suces;
	}

	public void setPred(Vertice<V> pred) {
		this.pred = pred;
	}

	public void setSuces(Vertice<V> suces) {
		this.suces = suces;
	}

	public void setPosicion(Position<Arco<V>> e) {
		posicionEnListaArcos = e;
	}

	public Position<Arco<V>> getPosicion() {
		return posicionEnListaArcos;
	}
}