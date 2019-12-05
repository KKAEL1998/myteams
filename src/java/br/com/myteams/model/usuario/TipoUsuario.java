package br.com.myteams.model.usuario;

public enum TipoUsuario {
    
    CLIENTE(1), ADMINISTRADOR(2);
        
    private int valor;

    private TipoUsuario(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
    public static TipoUsuario get(int valor) {
        for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
            if (tipoUsuario.getValor() == valor) {
                return tipoUsuario;
            }
        }
        throw new IllegalArgumentException("Valor Invalido para " + TipoUsuario.class.getName());
    }
}
