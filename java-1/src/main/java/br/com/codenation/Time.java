package br.com.codenation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Time {
	private Long id;
	private String nome;
	private LocalDate dataCriacao;
	private String corUniformePrincipal;
	private String corUniformeSecundario;
	private Jogador capitao;
	private List<Jogador> jogadorLista;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getCorUniformePrincipal() {
		return corUniformePrincipal;
	}
	public void setCorUniformePrincipal(String corUniformePrincipal) {
		this.corUniformePrincipal = corUniformePrincipal;
	}
	public String getCorUniformeSecundario() {
		return corUniformeSecundario;
	}
	public void setCorUniformeSecundario(String corUniformeSecundario) {
		this.corUniformeSecundario = corUniformeSecundario;
	}
	public Jogador getCapitao() {
		return capitao;
	}
	public void setCapitao(Jogador capitao) {
		this.capitao = capitao;
	}
	public List<Jogador> getJogadorLista() {
		return jogadorLista;
	}
	public void setJogadorLista(Jogador jogador) {
		this.jogadorLista.add(jogador);
	}
	
	public Time() {
		this.jogadorLista = new ArrayList<Jogador>();
	}
	
	public Time(Long id) {
		this.id = id;
		this.jogadorLista = new ArrayList<Jogador>();
	}

	
}
