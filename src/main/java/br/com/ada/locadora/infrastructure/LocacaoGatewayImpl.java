package br.com.ada.locadora.infrastructure;

import br.com.ada.locadora.domain.cliente.Cliente;
import br.com.ada.locadora.domain.locacao.Locacao;
import br.com.ada.locadora.domain.locacao.LocacaoGateway;
import br.com.ada.locadora.domain.veiculo.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class LocacaoGatewayImpl implements LocacaoGateway {
    private List<Locacao> locacoes = new ArrayList<>();

    @Override
    public void inserir(Locacao locacao) {
        locacoes.add(locacao);
    }

    @Override
    public void atualizar(Integer codigoLocacao, Locacao locacao) {
        Locacao locacaoAlterar=buscarLocacaoCodigo(codigoLocacao);
        locacoes.remove(locacaoAlterar);
        locacoes.add(locacao);
    }

    @Override
    public Locacao buscarLocacaoCodigo(Integer codigoLocacao) {
        for (Locacao l:locacoes) {
            if(l.getLocacaoId().getValor().equals(codigoLocacao)){
                return l;
            }
        }
        throw new IllegalArgumentException("Locação não encontrada");
    }

    @Override
    public List<Locacao> listarLocacoes() {
        return locacoes;
    }
}
