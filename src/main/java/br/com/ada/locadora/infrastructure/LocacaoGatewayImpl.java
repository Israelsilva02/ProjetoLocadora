package br.com.ada.locadora.infrastructure;

import br.com.ada.locadora.dominio.locacao.Locacao;
import br.com.ada.locadora.dominio.locacao.LocacaoGateway;
import br.com.ada.locadora.dominio.veiculo.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class LocacaoGatewayImpl implements LocacaoGateway {
    List<Locacao> locacaos = new ArrayList<>();

    @Override
    public void salvar(Locacao locacao) {
        locacaos.add(locacao);
    }

    @Override
    public void atualizar(Integer id, Locacao locacao) {
        locacaos.remove(locacao);
        locacaos.add(locacao);
    }


    @Override
    public Locacao buscarPorId(Integer id) {
        for (Locacao locacao : locacaos) {
            if (locacao.getId().valor().equals(id)) {
                return locacao;
            }
        }
        throw new RuntimeException("Locacao não encontrado");
    }

    @Override
    public List<Locacao> buscarTodos() {
        return locacaos;
    }
}
