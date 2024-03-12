package br.com.ada.locadora.domain.veiculo;

import br.com.ada.locadora.Identificador;

public class VeiculoID extends Identificador<Integer> {
    private static Integer id = 0;
    private Integer numero;

    public VeiculoID() {
        VeiculoID.id++;
        this.numero = VeiculoID.id;
    }

    @Override
    public Integer getValor() {
        return numero;
    }


    @Override
    public String nome() {
        return "VeiculoID";
    }
}
