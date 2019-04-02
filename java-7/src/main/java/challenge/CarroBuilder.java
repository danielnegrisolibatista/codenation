package challenge;

public class CarroBuilder {
	
	private Cor cor;
	private String placa;
	private Motorista motorista;
	
	public Cor getCor() {
		return cor;
	}

	public String getPlaca() {
		return placa;
	}

	public Motorista getMotorista() {
		return motorista;
	}
	
	public static CarroBuilder newInstance() 
    { 
        return new CarroBuilder(); 
    } 
	
	public CarroBuilder withCor(Cor cor) {
		if (cor == null) {
			throw new NullPointerException();
		}
		
		this.cor = cor;
		return this;
	}
	
	public CarroBuilder withPlaca(String placa) {
		if (placa.trim() == "") {
			throw new NullPointerException();
		}
		
		this.placa = placa;
		return this;
	}
	
	public CarroBuilder withMotorista(Motorista motorista) {
		if (motorista == null) {
			throw new NullPointerException();
		}
		
		this.motorista = motorista;
		return this;
	}
	
	public Carro build() 
    { 
		if (getPlaca().trim() == "") {
			throw new NullPointerException();
		}
		
		if (getCor() == null) {
			throw new NullPointerException();
		}
		
		if (getMotorista() == null) {
			throw new EstacionamentoException();
		}
		
    	return new Carro(this);
    } 
}
