package br.com.ada.locadora.service;

import br.com.ada.locadora.Identificador;
import br.com.ada.locadora.domain.cliente.Cliente;
import br.com.ada.locadora.domain.cliente.ClienteGateway;

import java.util.List;

public class ClienteServico {
    private ClienteGateway clienteGateway;

    public ClienteServico(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }
    public void incluir(Identificador id,
                        String nome,
                        String email){

        Cliente cliente = new Cliente(id, nome, email);

        clienteGateway.cadastrar(cliente);

    }
    public void alterar(Identificador id,
                        String nome,
                        String email){

        Cliente cliente = clienteGateway.buscarClientePorId(id);

        cliente.alterarNome(nome);
        cliente.alterarEmail(email);


        clienteGateway.atualizar(id, cliente);

    }

    public Cliente localizarCliente(Identificador id){

        Cliente cliente = clienteGateway.buscarClientePorId(id);

        return cliente;
    }

    public List<Cliente> listarClientes(){
        return clienteGateway.listarTodos();
    }
}
