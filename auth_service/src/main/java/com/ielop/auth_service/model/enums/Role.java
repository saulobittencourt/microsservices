package com.ielop.auth_service.model.enums;

public enum Role {
    USER(1, "USER"), SERVICE(2, "SERVICE");

    private final Integer id;
    private final String nome;

    Role(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return nome;
    }
}