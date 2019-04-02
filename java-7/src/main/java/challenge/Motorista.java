package challenge;

public class Motorista {	
	private String nome;
	private int idade;
	private int pontos;
	
	private String habilitacao;

	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}

	public int getPontos() {
		return pontos;
	}

	public String getHabilitacao() {
		return habilitacao;
	}

	public Motorista(MotoristaBuilder builder) {
		this.nome = builder.getNome();
		this.idade = builder.getIdade();
		this.pontos = builder.getPontos();
		this.habilitacao = builder.getHabilitacao();
	}
	
	public static MotoristaBuilder builder() {
		return MotoristaBuilder.newInstance();
	}
}
