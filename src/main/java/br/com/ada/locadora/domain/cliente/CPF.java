package br.com.ada.locadora.domain.cliente;

import br.com.ada.locadora.Identificador;

public class CPF extends Identificador<String> {

    private String numero;

    public CPF(String valor){
        if (valor == null || valor.length() != 11 || valor.isEmpty()){
            throw new IllegalArgumentException("CPF deve ter 11 digitos");
        }
        this.numero = numero;
    }

    @Override
    public String getValor() {
        return numero;
    }
}
