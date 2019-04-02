package challenge;

public class MotoristaBuilder {
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

	public static MotoristaBuilder newInstance() 
    { 
        return new MotoristaBuilder(); 
    } 
	
	public MotoristaBuilder withNome(String nome) {
		if (nome.trim() == "") {
			throw new NullPointerException();
		}
		
		this.nome = nome;
		return this;
	}
	
	public MotoristaBuilder withIdade(int idade) {
		if (idade < 0) {
			throw new IllegalArgumentException();
		}
		
		this.idade = idade;
		return this;
	}
	
	public MotoristaBuilder withPontos(int pontos) {
		if (pontos < 0) {
			throw new IllegalArgumentException();
		}
		
		this.pontos = pontos;
		return this;
	}
	
	public MotoristaBuilder withHabilitacao(String habilitacao) {
		if (habilitacao.trim() == "") {
			throw new NullPointerException();
		}
		
		this.habilitacao = habilitacao;
		return this;
	}
	
    public Motorista build() 
    { 		
    	if (getNome().trim() == "" || getHabilitacao().trim() == "") {
			throw new NullPointerException();
		}
    	
    	if (getIdade() >= 0 && getIdade() < 18) {
			throw new EstacionamentoException();
		}
    	
    	if (getPontos() >= 20) {
			throw new EstacionamentoException();
		}
    	
        return new Motorista(this); 
    } 
}
