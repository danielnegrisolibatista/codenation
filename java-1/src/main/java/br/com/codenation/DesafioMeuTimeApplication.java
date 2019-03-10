package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.*;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private List<Time> timeLista;
	private List<Jogador> jogadorLista;

	public DesafioMeuTimeApplication() {
		timeLista = new ArrayList<Time>();
		jogadorLista = new ArrayList<Jogador>();
	}
	
	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		try {
			Time time = new Time();
			time.setId(id);
			time.setNome(nome);
			time.setDataCriacao(dataCriacao);
			time.setCorUniformePrincipal(corUniformePrincipal);
			time.setCorUniformeSecundario(corUniformeSecundario);
			
			incluirTime(time);
			
		}  catch (IdentificadorUtilizadoException erro) {
			throw new IdentificadorUtilizadoException(
					"Identificador do time já existe", erro);
		}
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		try {
			Jogador jogador = new Jogador();
			jogador.setId(id);
			jogador.setIdTime(idTime);
			jogador.setNome(nome);
			jogador.setDataNascimento(dataNascimento);
			jogador.setNivelHabilidade(nivelHabilidade);
			jogador.setSalario(salario);
			
			incluirJogador(jogador);
			
		} catch (IdentificadorUtilizadoException erro) {
			throw new IdentificadorUtilizadoException(
					"Identificador do jogador já existe", erro);
		} catch (TimeNaoEncontradoException erro) {
			throw new TimeNaoEncontradoException(
					"Time não encontrado", erro);
		}
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		throw new UnsupportedOperationException();
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		throw new UnsupportedOperationException();
	}

	private List<Time> getTimeLista() {
		return timeLista;
	}

	private List<Jogador> getJogadorLista() {
		return jogadorLista;
	}

	private void incluirTime(Time time) {
		if (!existeTime(time)) {
			timeLista.add(time);
		} else {
			throw new IdentificadorUtilizadoException();
		}
	}

	private void incluirJogador(Jogador jogador) {
		if (!existeJogador(jogador)) {
			Time time = new Time(jogador.getIdTime());
			
			if (existeTime(time)) {
				jogadorLista.add(jogador);
			} else {
				throw new IdentificadorUtilizadoException();
			}
		} else {
			throw new TimeNaoEncontradoException();
		}
	}
	
	private boolean existeJogador(Jogador jogador) {
		List<Jogador> jogadorLista = getJogadorLista();
		Jogador existeJogador = null;
		
		if (jogadorLista.size() > 0) {
			existeJogador = jogadorLista.stream()
	                .filter(linha -> linha.getId().equals(linha.getId()))
	                .findAny()
	                .orElse(null);
		}

		return (existeJogador == null);
	}
	
	private boolean existeTime(Time time) {
		List<Time> timeLista = getTimeLista();
		Time existeTime = null;
		
		if (timeLista.size() > 0) {
			existeTime = timeLista.stream()
	                .filter(linha -> linha.getId().equals(time.getId()))
	                .findAny()
	                .orElse(null);
		}

		return (existeTime == null);
	}
}
