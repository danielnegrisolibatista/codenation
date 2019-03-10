package challenge;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
	public List<Player> allPlayers;
	
	public List<Player> getAllPlayers() {
		return allPlayers;
	}

	public void setAllPlayers(List<Player> allPlayers) {
		this.allPlayers = allPlayers;
	}

	public Main() {
		CSVReader csv = new CSVReader();
		setAllPlayers(csv.GetData());
	}
	
	
	public static void main(String[] args) {
		Main man = new Main();
		
		System.out.println("Q1 - Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo?");
		System.out.println("Count all nationality: " + man.q1());
		
		System.out.println("");
		System.out.println("Q2 - Quantos clubes (coluna `club`) diferentes existem no arquivo?");
		System.out.println("Count all club: " + man.q2());
		
		System.out.println("");
		System.out.println("Q3 - Liste o nome completo (coluna `full_name`) dos 20 primeiros jogadores.");
		List<String> q3 = man.q3();
		q3.forEach(player ->
		 	System.out.println("Full_name: " + player)
		);
		
		System.out.println("");
		System.out.println("Q4 - Quem s„o os top 10 jogadores que possuem as maiores cl·usulas de rescis„o?");
		List<String> q4 = man.q4();
		q4.forEach(player ->
		 	System.out.println("Full_name: " + player)
		);
		
		System.out.println("");
		System.out.println("Q5 - Quem s„o os 10 jogadores mais velhos (use como critÈrio de desempate o campo `eur_wage`)?");
		List<String> q5 = man.q5();
		q5.forEach(player ->
		 	System.out.println("Full_name: " + player)
		);
		
		System.out.println("");
		System.out.println("Q6 - Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves s„o as idades e os valores a contagem.");
		Map<Integer, Integer> q6 = man.q6();

		for (Map.Entry<Integer, Integer> entry : q6.entrySet()) {
			System.out.println("age: " + entry.getKey() + " = " + entry.getValue());
		}
		
	}

	// Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo?
	public int q1() {
		try {
			List<Player> allPlayers = getAllPlayers();
			List<String> q1 = allPlayers.stream().map(s -> new String(s.getNationality())).collect(Collectors.toList());

			Long countNationality = q1.stream().distinct().count();
			return countNationality.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	// Quantos clubes (coluna `club`) diferentes existem no arquivo?
	// Obs: Existem jogadores sem clube.
	public int q2() {
		try {
			List<Player> allPlayers = getAllPlayers();
			List<String> q2 = allPlayers.stream().map(s -> new String(s.getClub())).filter(value -> !"".equals(value))
					.collect(Collectors.toList());

			Long countClub = q2.stream().distinct().count();
			return countClub.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	// Liste o nome completo (coluna `full_name`) dos 20 primeiros jogadores.
	public List<String> q3() {
		try {
			List<Player> allPlayers = getAllPlayers();
			List<Player> q3 = allPlayers.stream().collect(Collectors.toList());

			List<String> players = q3.stream().limit(20).map(s -> new String(s.getFull_name()))
					.collect(Collectors.toList());

			return players;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Quem s„o os top 10 jogadores que possuem as maiores cl√°usulas de rescis√£o?
	// (utilize as colunas `full_name` e `eur_release_clause`)
	public List<String> q4() {
		try {
			List<Player> allPlayers = getAllPlayers();
			List<Player> q4 = allPlayers.stream().sorted(Comparator.comparing(Player::getEur_release_clause).reversed())
					.collect(Collectors.toList());

			List<String> players = q4.stream().limit(10).map(s -> new String(s.getFull_name()))
					.collect(Collectors.toList());

			return players;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Quem s√£o os 10 jogadores mais velhos (use como crit√©rio de desempate o
	// campo `eur_wage`)?
	// (utilize as colunas `full_name` e `birth_date`)
	public List<String> q5() {
		try {
			List<Player> allPlayers = getAllPlayers();
			List<Player> q5 = allPlayers.stream()
					.sorted(Comparator.comparing(Player::getBirth_date).thenComparing(Player::getEur_wage))
					.collect(Collectors.toList());

			List<String> players = q5.stream().limit(10).map(s -> new String(s.getFull_name()))
					.collect(Collectors.toList());

			return players;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Conte quantos jogadores existem por idade. Para isso, construa um mapa onde
	// as chaves s√£o as idades e os valores a contagem.
	// (utilize a coluna `age`)
	public Map<Integer, Integer> q6() {
		try {
			List<Player> allPlayers = getAllPlayers();

			Map<Integer, Integer> q6 = allPlayers.stream()
					.collect(Collectors.toMap(Player::getAge, s -> 1, Integer::sum));

			return q6;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
