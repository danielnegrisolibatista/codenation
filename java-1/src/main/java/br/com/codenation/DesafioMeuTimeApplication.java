package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.*;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private List<Time> timeLista;

	public DesafioMeuTimeApplication() {
		timeLista = new ArrayList<Time>();
	}
	
	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if (getTimePorId(id) != null) {
			throw new IdentificadorUtilizadoException();
		}
		
		Time time = new Time();
		time.setId(id);
		time.setNome(nome);
		time.setDataCriacao(dataCriacao);
		time.setCorUniformePrincipal(corUniformePrincipal);
		time.setCorUniformeSecundario(corUniformeSecundario);

		timeLista.add(time);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if (getJogadorPorId(id) != null) {
			throw new IdentificadorUtilizadoException();
		}
		
		Time time = getTimePorId(idTime);
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}
		
		Jogador jogador = new Jogador();
		jogador.setId(id);
		jogador.setIdTime(idTime);
		jogador.setNome(nome);
		jogador.setDataNascimento(dataNascimento);
		jogador.setNivelHabilidade(nivelHabilidade);
		jogador.setSalario(salario);
		
		time.setJogadorLista(jogador);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador jogador = getJogadorPorId(idJogador);
		
		if (jogador == null) {
			throw new JogadorNaoEncontradoException();
		}
		
		Time time = getTimePorId(jogador.getIdTime());
		
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}
		
		time.setCapitao(jogador);
	}
	

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Time time = getTimePorId(idTime);
		
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}
		
		Jogador capitao = time.getCapitao();
		
		if (capitao == null) {
			throw new CapitaoNaoInformadoException();
		}
		
		return capitao.getId();
	}
	

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		Jogador jogador = getJogadorPorId(idJogador);
		
		if (jogador == null) {
			throw new JogadorNaoEncontradoException();
		}
		
		return jogador.getNome();
	}
	

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		Time time = getTimePorId(idTime);
		
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}
		
		return time.getNome();
	}
	

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		Time time = getTimePorId(idTime);
		
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}
		
		List<Long> jogadores = time.getJogadorLista()
				.stream()
				.sorted(Comparator.comparing(Jogador::getId))
				.map(jogador -> new Long(jogador.getId()))
				.collect(Collectors.toList());
		
		if (jogadores.size() > 0) {
			return jogadores;
		} else {
			return new ArrayList<Long>();
		}
	}
	

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Time time = getTimePorId(idTime);
		
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}
		
		Jogador maxPorHabilidade = time.getJogadorLista()
			      .stream()
			      .max(Comparator.comparing(Jogador::getNivelHabilidade))
			      .orElseThrow(NoSuchElementException::new);
		
		if (maxPorHabilidade == null) {
			throw new JogadorNaoEncontradoException();
		}
		
		return maxPorHabilidade.getId();
	}
	

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		Time time = getTimePorId(idTime);
		
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}
		
		List<Jogador> ordenaIdadeJogadoresLista = time.getJogadorLista()
				.stream()
				.sorted(Comparator.comparing(Jogador::getDataNascimento))
				.collect(Collectors.toList());
		
		if (ordenaIdadeJogadoresLista.size() == 0) {
			throw new JogadorNaoEncontradoException();
		}
		
		Jogador jogador = ordenaIdadeJogadoresLista
				.stream()
				.findFirst()
				.orElse(null);

		return jogador.getId();
	}
	

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		List<Long> timeLista = getTimeLista()
				.stream()
				.sorted(Comparator.comparing(Time::getId))
				.map(time -> new Long(time.getId()))
				.collect(Collectors.toList());
		
		if (timeLista != null) {
			return timeLista;
		} else {
			return new ArrayList<Long>();
		}
	}
	

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		Time time = getTimePorId(idTime);
		
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}
		
		List<Jogador> ordenaSalarioJogadoresLista = time.getJogadorLista()
				.stream()
				.sorted(Comparator.comparing(Jogador::getSalario).reversed())
				.collect(Collectors.toList());
		
		if (ordenaSalarioJogadoresLista.size() == 0) {
			throw new JogadorNaoEncontradoException();
		}
		
		Jogador jogador = ordenaSalarioJogadoresLista
				.stream()
				.findFirst()
				.orElse(null);

		return jogador.getId();
	}
	

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		List<Jogador> todosJogadoresLista = getJogadorLista();
		Jogador jogador = null;
		
		if (todosJogadoresLista.size() > 0) {
			jogador = todosJogadoresLista
					.stream()
					.filter(linha -> Long.compare(linha.getId(), idJogador) == 0)
	                .findAny()
	                .orElse(null);
		}
		
		if (jogador == null) {
			throw new JogadorNaoEncontradoException();
		}

		return jogador.getSalario();
	}
	

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		List<Jogador> todosJogadoresLista = getJogadorLista();
		
		if (todosJogadoresLista.size() == 0) {
			new ArrayList<Long>();
		}
				
		List<Jogador> ordenaMelhoresJogadoresLista = todosJogadoresLista
			.stream()
			.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed())
			.collect(Collectors.toList());

		List<Long> topJogadoresLista = ordenaMelhoresJogadoresLista
				.stream()
				.limit(top)
				.map(s -> new Long(s.getId()))
				.collect(Collectors.toList());
		
		return topJogadoresLista;
	}
	

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Time timeCasa = getTimePorId(timeDaCasa);
		if (timeCasa == null) {
			throw new TimeNaoEncontradoException();
		}
		
		Time timeFora = getTimePorId(timeDeFora);
		if (timeFora == null) {
			throw new TimeNaoEncontradoException();
		}
		
		if (timeCasa.getCorUniformePrincipal() == timeFora.getCorUniformePrincipal()) {
			return timeFora.getCorUniformeSecundario();
		} else {
			return timeFora.getCorUniformePrincipal();
		}
	}
	

	private List<Time> getTimeLista() {
		return timeLista;
	}
	
	private List<Jogador> getJogadorLista() {
		List<Time> timeLista = getTimeLista();
		
		List<Jogador> jogadorLista = new ArrayList<Jogador>();
		for (Time time : timeLista) {
			if (time.getJogadorLista().size() > 0) {
				jogadorLista.addAll(time.getJogadorLista());
			}
		}
		
		return jogadorLista;
	}
	
	
	private Time getTimePorId(Long idTime) {
		List<Time> timeLista = getTimeLista();
		Time time = null;
		
		if (timeLista.size() > 0) {
			time = timeLista
					.stream()
					.filter(linha -> Long.compare(linha.getId(), idTime) == 0)
	                .findAny()
	                .orElse(null);
		}
		
		return time;
	}

	private String dadosValidosParaIncluirTime(Time time) {
		String mensagemDeErro = "";
		
		if (time == null) {
			return "Nenhum dado foi informado";
		}
		
		if (time.getId() == null) {
			mensagemDeErro += "Id; ";
		}
		
		if (time.getNome().isEmpty() || time.getNome() == null) {
			mensagemDeErro += "Nome; ";
		}
		
		if (time.getDataCriacao() == null) {
			mensagemDeErro += "Data de Criação; ";
		}
		
		if (time.getCorUniformePrincipal().isEmpty() || time.getCorUniformePrincipal() == null) {
			mensagemDeErro += "Cor do uniforme principal não foi informado; ";
		}
		
		if (time.getCorUniformeSecundario().isEmpty() || time.getCorUniformeSecundario() == null) {
			mensagemDeErro += "Cor do uniforme secundário não foi informado; ";
		}
			
		return mensagemDeErro;
	}
	

	
	private Jogador getJogadorPorId(Long idJogador) {
		List<Jogador> jogadorLista = getJogadorLista();
		Jogador jogador = null;
		
		if (jogadorLista.size() > 0) {
			jogador = jogadorLista
					.stream()
					.filter(linha -> Long.compare(linha.getId(), idJogador) == 0)
	                .findAny()
	                .orElse(null);
		}
		

		return jogador;
	}

	
	
}
