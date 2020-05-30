package br.com.myteams.model.categoria;

public class Categoria
{
    private Long id;

    private String nome;

    public Categoria()
    {
    }

    public Categoria(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }
}
