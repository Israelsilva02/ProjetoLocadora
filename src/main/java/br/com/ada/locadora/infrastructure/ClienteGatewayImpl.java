package br.com.ada.locadora.infrastructure;

import br.com.ada.locadora.Identificador;
import br.com.ada.locadora.domain.cliente.Cliente;
import br.com.ada.locadora.domain.cliente.ClienteGateway;

import java.util.ArrayList;
import java.util.List;

public class ClienteGatewayImpl implements ClienteGateway {
    private List<Cliente> clientes = new ArrayList<>();


    @Override
    public void cadastrar(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public void atualizar(Identificador id, Cliente cliente) {
        Cliente clienteRemover = buscarClientePorId(id);
        clientes.remove(clienteRemover);
        clientes.add(cliente);
    }

    @Override
    public Cliente buscarClientePorId(Identificador id) {
        for (Cliente cliente : clientes) {

            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        throw new IllegalArgumentException("Cliente não encontrado");
    }

    @Override
    public List<Cliente> listarTodos() {
        return clientes;
    }
}
