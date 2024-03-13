package br.com.ada.locadora.domain.cliente;

import br.com.ada.locadora.Identificador;

public class ClienteID extends Identificador<Integer> {
    private static Integer id = 0;
    private Integer numero;

    public ClienteID() {
        ClienteID.id++;
        this.numero=ClienteID.id;
    }


    @Override
    public Integer getValor() {
        return numero;
    }

    @Override
    public String nome() {
        return "ClienteID";
    }
}
