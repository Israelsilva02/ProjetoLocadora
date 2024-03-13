package br.com.ada.locadora.domain.cliente;

import br.com.ada.locadora.Identificador;

import java.util.Random;

public class Cliente {
    private final Identificador id;
    private TipoPessoa tipo;
    private String nome;
    private String email;


    public Cliente(Identificador id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        validar();
    }

    private void validar() {
        if (id == null || id.equals("")) {
            throw new IllegalArgumentException("Id não pode ser nulo ou vazio");
        }

        if (nome == null || nome.equals("")) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
        }
    }

    public void alterarNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        this.nome = nome;

    }

    public void alterarEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        this.email = email;
    }

    public Identificador getId() {

        return id;
    }

        public String getNome() {

        return nome;
    }

    public String getEmail() {

        return email;
    }

    public TipoPessoa getTipo() {

        return tipo;
    }
}
