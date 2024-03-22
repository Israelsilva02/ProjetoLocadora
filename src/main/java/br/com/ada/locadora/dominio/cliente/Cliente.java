package br.com.ada.locadora.dominio.cliente;

import br.com.ada.locadora.Identificador;


public class Cliente {

    private final Identificador id;
    private final TipoPessoa tipoPessoa;
    private String nome;
    private String email;


    private Cliente(Identificador id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;


        if (id instanceof CPF){
            this.tipoPessoa = TipoPessoa.PF;
        }else{
            this.tipoPessoa = TipoPessoa.PJ;
        }
        validar();
    }

    public static Cliente criar(Identificador id, String nome, String email) {
        return new Cliente(id, nome, email);
    }



    public void alterarNome(String novoNome) {
        this.nome = novoNome;
    }

    public void alterarEmail(String novoEmail) {
        this.email = novoEmail;
    }

    public Identificador id() {
        return id;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }



    private void validar() {
        if (id == null) {
            throw new RuntimeException("ID não pode ser nulo");
        }

        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("Nome não pode ser nulo");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new RuntimeException("Email não pode ser nulo");
        }
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", tipoPessoa=" + tipoPessoa +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
