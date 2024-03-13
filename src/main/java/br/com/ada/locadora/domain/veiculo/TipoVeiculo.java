package br.com.ada.locadora.domain.veiculo;

import java.math.BigDecimal;
public enum TipoVeiculo {
    PEQUENO(new BigDecimal(100)),
    MEDIO(new BigDecimal(150)),
    GRANDE(new BigDecimal(200));

    private BigDecimal precoDiaria;

    TipoVeiculo(BigDecimal precoDiaria) {
        this.precoDiaria=precoDiaria;
    }

    public BigDecimal getPrecoDiaria() {
        return precoDiaria;
    }
}
