package br.com.ada.locadora.dominio.cliente;

import br.com.ada.locadora.Identificador;

public class CPF extends Identificador<String> {

    private final String valor;

    private CPF(String valor) {
        this.valor = valor;
        this.validate();
    }

    public static CPF criar(String id) {
        return new CPF(id);
    }

    public String valor() {
        return valor;
    }

    private void validate() {
        if (valor == null || valor.length() != 11) {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    @Override
    public String toString() {
        return "CPF{" +
                "valor='" + valor + '\'' +
                '}';
    }
}
