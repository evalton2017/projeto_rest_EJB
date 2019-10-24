package br.com.projeto.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import br.com.projeto.enums.usuario.Tipo;

@Entity
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=64, nullable=false, unique=true)
	private String login;
	
	@XmlTransient
	@Column(length=128, nullable=false)
	private String senha;
	
	@Column(length=64, nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private boolean ativo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_registro", nullable=false, updatable=false)
	private Date dataRegistro;
	
	@Enumerated(EnumType.STRING)
	@Column(length=16, nullable=false)
	private Tipo tipo;
	
	public Usuario() {
		
	}

	public Usuario(String login, String senha, String nome, boolean ativo, Date dataRegistro, Tipo tipo) {
		super();
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.ativo = ativo;
		this.dataRegistro = dataRegistro;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", nome=" + nome + ", ativo=" + ativo + ", dataRegistro="
				+ dataRegistro + ", tipo=" + tipo + "]";
	}

}
