package br.com.ada.locadora.domain.locacao;

import br.com.ada.locadora.domain.cliente.Cliente;
import br.com.ada.locadora.domain.veiculo.Veiculo;

import java.util.List;

public interface LocacaoGateway {
    public void inserir(Locacao locacao);

    public void atualizar(Integer codigoLocacao, Locacao locacao);

    public Locacao buscarLocacaoCodigo(Integer codigoLocacao);

    List<Locacao> listarLocacoes();

}
