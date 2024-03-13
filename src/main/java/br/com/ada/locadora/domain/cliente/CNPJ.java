package br.com.ada.locadora.domain.cliente;

import br.com.ada.locadora.Identificador;

public class CNPJ extends Identificador<String> {

    private final String numero;

    public CNPJ(String valor) {
        if (valor == null || valor.length() != 14 || valor.isEmpty()) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
        this.numero = valor;
    }

    @Override
    public String getValor() {
        return numero;
    }
}
