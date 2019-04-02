package challenge;


public class Carro {
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

	public Carro(CarroBuilder builder) {
		this.cor = builder.getCor();
		this.placa = builder.getPlaca();
		this.motorista = builder.getMotorista();
	}
		
	public static CarroBuilder builder() {
		return CarroBuilder.newInstance();
	}	
}
