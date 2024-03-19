package br.com.ada.locadora.dominio.cliente;

import br.com.ada.locadora.Identificador;

import java.util.List;

public interface ClienteGateway {

    void salvar(Cliente cliente);

    void atualizar(Cliente cliente);

    Cliente buscarPorId(String id);

    List<Cliente> buscarTodos();
}

