package br.com.ada.locadora.domain.cliente;

import java.util.List;

public interface ClienteGateway {
    public void cadastrar(Cliente cliente);

    public void atualizar(String id, Cliente cliente);

    Cliente buscarPorId(String id);

    List<Cliente> listarRTodos();
}
