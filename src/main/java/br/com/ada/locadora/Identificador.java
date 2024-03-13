package br.com.ada.locadora;

public abstract class Identificador<T> {
    private T valor;

    public abstract T getValor();

    @Override
    public String toString() {

        return getValor().toString();
    }
}
