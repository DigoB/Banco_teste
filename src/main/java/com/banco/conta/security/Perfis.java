package com.banco.conta.security;


public enum Perfis {
    CLIENTE(1,"ROLE_CLIENT");
    
    private int codigo;
    private String descricao;
    
    private Perfis(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
    
    public int getCodigo() {
        return this.codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public static Perfis toEnum(Integer codigo) {
        if(codigo == null) {
            return null;
        } 

        for(Perfis x : Perfis.values()) {
            if(codigo.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Código Invalido!");
    }



}
