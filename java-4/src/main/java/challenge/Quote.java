package challenge;

public class Quote {
	private String actor;

    private String quote;

    public Quote() {
    }

    public Quote(String actor, String quote) {
        this.actor = actor;
        this.quote = quote;
    }

    public String getActor() {
		return this.actor;
	}

	public String getQuote() {
		return this.quote;
	}

    @Override
    public String toString() {
        return "Quote {" +
                		" actor='" + actor + '\'' +
                		", quote='" + quote + '\'' +
                	  " }";
    }

    public String jsonString() {
        return "{\r\n" + "\"actor\":\"" + actor + "\",\r\n" + "\"quote\":\"" + quote + "\"\r\n}";
    }
}
