package br.com.ada.locadora.domain.locacao;

import br.com.ada.locadora.Identificador;

public class LocacaoID extends Identificador<Integer> {
    private static Integer id = 0;
    private Integer numero;

    public LocacaoID() {
        LocacaoID.id++;
        this.numero = LocacaoID.id;
    }

    @Override
    public Integer getValor() {
        //retorna numero
        return numero;
    }
}
