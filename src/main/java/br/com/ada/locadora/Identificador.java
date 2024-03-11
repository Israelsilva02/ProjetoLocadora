package br.com.ada.locadora;

public abstract class Identificador<T> {
    private T valor;

    public abstract T getValor();
    public abstract String nome();

    @Override
    public String toString() {
        return getValor().toString();
    }
}
