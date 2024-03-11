package br.com.ada.locadora.infrastructure;

import br.com.ada.locadora.domain.cliente.Cliente;
import br.com.ada.locadora.domain.cliente.ClienteGateway;

import java.util.List;

public class ClienteRepositorioLista implements ClienteGateway {
    @Override
    public void cadastrar(Cliente cliente) {

    }

    @Override
    public void atualizar(String id, Cliente cliente) {

    }

    @Override
    public Cliente buscarPorId(String id) {
        return null;
    }

    @Override
    public List<Cliente> listarRTodos() {
        return null;
    }
}
