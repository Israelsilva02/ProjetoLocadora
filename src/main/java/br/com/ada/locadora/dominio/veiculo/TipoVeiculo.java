package br.com.ada.locadora.dominio.veiculo;

import java.math.BigDecimal;

public enum TipoVeiculo {
    PEQUENO(100),
    MEDIO(150),
    GRANDE(200);

    private final BigDecimal valor;

    private TipoVeiculo(double valor) {
        this.valor = BigDecimal.valueOf(valor);
    }

    public BigDecimal valor() {
        return valor;
    }
}
