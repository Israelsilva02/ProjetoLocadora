package br.com.ada.locadora.dominio.cliente;

import br.com.ada.locadora.Identificador;

public class CNPJ extends Identificador<String> {

    private final String valor;

    private CNPJ(String valor) {
        this.valor = valor;
        this.validar();
    }

    public static CNPJ criar(String id) {
        return new CNPJ(id);
    }

    public String valor() {
        return valor;
    }

    private void validar() {
        if (valor == null || this.valor.length() != 14) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
    }

    @Override
    public String toString() {
        return "CNPJ{" +
                "valor='" + valor + '\'' +
                '}';
    }
}