package br.com.ada.locadora.infrastructure;

import br.com.ada.locadora.Identificador;
import br.com.ada.locadora.dominio.cliente.Cliente;
import br.com.ada.locadora.dominio.cliente.ClienteGateway;

import java.util.ArrayList;
import java.util.List;

public class ClienteGatewayImpl implements ClienteGateway {
    List<Cliente> clientes = new ArrayList<>();

    @Override
    public void salvar(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public void atualizar(Cliente cliente) {
        clientes.remove(cliente);
        clientes.add(cliente);
    }

    @Override
    public Cliente buscarPorId(String id) {

        for (Cliente cliente : clientes) {
            if (id.equals(String.valueOf(cliente.id().valor()))) {
                return cliente;
            }
        }

        throw new RuntimeException("Cliente não encontrado");
    }

    @Override
    public List<Cliente> buscarTodos() {
        return clientes;
    }
}
