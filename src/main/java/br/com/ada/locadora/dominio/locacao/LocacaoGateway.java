package br.com.ada.locadora.dominio.locacao;


import java.util.List;

public interface LocacaoGateway {

    void salvar(Locacao locacao);

    void atualizar(Integer id, Locacao locacao);

    Locacao buscarPorId(Integer id);

    List<Locacao> buscarTodos();
}
