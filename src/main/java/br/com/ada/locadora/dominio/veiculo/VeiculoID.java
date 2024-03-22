package br.com.ada.locadora.dominio.veiculo;

import br.com.ada.locadora.Identificador;

public class VeiculoID extends Identificador<String> {

    private final String valor;

    private VeiculoID(String valor) {
        this.valor = valor;
    }

    public static VeiculoID criar(String valor) {

        return new VeiculoID(valor);
    }

    @Override
    public String valor() {
        return valor;
    }

    @Override
    public String toString() {
        return "VeiculoID{" +
                "valor='" + valor + '\'' +
                '}';
    }
}

