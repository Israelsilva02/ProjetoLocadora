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

    public void atualizar(Cliente cliente) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).id().equals(cliente.id())) {
                clientes.set(i, cliente);
                return; // Cliente encontrado e atualizado, encerra o método
            }
        }
        // Se o cliente não for encontrado na lista, lança uma exceção
        throw new RuntimeException("Cliente não encontrado");
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
