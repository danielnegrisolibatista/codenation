package challenge;

public class CriptografiaCesariana implements Criptografia {
    @Override
    public String criptografar(String texto) {
    	validaTexto(texto);
    	
    	return criptografarOuDescriptografarTexto(texto, true);
    }

    @Override
    public String descriptografar(String texto) {
    	validaTexto(texto);
    	
    	return criptografarOuDescriptografarTexto(texto, false);
    }
        
    private void validaTexto(String texto) {
    	if (texto == null) {
    		throw new NullPointerException();
    	}
    	
    	if (texto.isEmpty()) {
    		throw new IllegalArgumentException();
    	}
    }
    
    private String criptografarOuDescriptografarTexto(String texto, boolean criptografar) {
    	StringBuilder novoTexto = new StringBuilder();

    	int parametroCriptografia = 3;
    	if (!criptografar) {
    		parametroCriptografia *= -1;
    	}
    	
    	for (char letra : texto.toCharArray())
    	{
    		if (Character.isWhitespace(letra)) {
    			novoTexto.append(' ');
    		} else if (Character.isDigit(letra)) {
    			novoTexto.append((char) Character.toLowerCase(letra));
    		} else {
    			novoTexto.append((char) (Character.toLowerCase(letra) + parametroCriptografia));
    		}
    	}
    	
    	return novoTexto.toString();
    }
}
