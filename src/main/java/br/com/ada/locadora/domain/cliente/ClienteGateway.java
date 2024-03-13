package br.com.ada.locadora.domain.cliente;

import br.com.ada.locadora.Identificador;

import java.util.List;

public interface ClienteGateway {
    public void cadastrar(Cliente cliente);

    public void atualizar(Identificador id, Cliente cliente);

    Cliente buscarClientePorId(Identificador id);

    List<Cliente> listarTodos();
}
