package br.com.portoseguro.apiporto.model;

public class Usuario {
	int id;
	String nome;
	float celular;
	String email;
	float cpf;
	String senha;
	
	public Usuario() {
		
	}
	
	public Usuario(int id, String nome, float celular, String email, float cpf, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.celular = celular;
		this.email = email;
		this.cpf = cpf;
		this.senha = senha;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public float getCelular() {
		return celular;
	}


	public void setCelular(float celular) {
		this.celular = celular;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public float getCpf() {
		return cpf;
	}


	public void setCpf(float cpf) {
		this.cpf = cpf;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	
}
