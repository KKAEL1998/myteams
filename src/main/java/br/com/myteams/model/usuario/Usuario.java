package br.com.myteams.model.usuario;

import br.com.myteams.exception.NegocioException;
import br.com.myteams.model.endereco.Endereco;

import java.util.Date;

public class Usuario
{

    private String cpf;

    private String nome;

    private String sobrenome;

    private Date dataNascimento;

    private String email;

    private String senha;

    private String telefone;

    private TipoUsuario tipoUsuario;

    private Endereco endereco;

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getSobrenome()
    {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome)
    {
        this.sobrenome = sobrenome;
    }

    public Date getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public TipoUsuario getTipoUsuario()
    {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario)
    {
        this.tipoUsuario = tipoUsuario;
    }

    public Endereco getEndereco()
    {
        return endereco;
    }

    public void setEndereco(Endereco endereco)
    {
        this.endereco = endereco;
    }

    public void elevarAAdmin() throws NegocioException
    {
        if (tipoUsuario == TipoUsuario.ADMINISTRADOR)
        {
            throw new NegocioException("Essa usuário já é um administrador");
        }
        tipoUsuario = TipoUsuario.ADMINISTRADOR;
    }

}
