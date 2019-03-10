package challenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
	public String line;
	public String cvsSplitBy;
	public Boolean hasHeader;

	public String getLine() {
		return this.line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getCvssplitby() {
		return this.cvsSplitBy;
	}

	public void setCvssplitby(String cvsSplitBy) {
		this.cvsSplitBy = cvsSplitBy;
	}

	public Boolean getHasheader() {
		return this.hasHeader;
	}

	public void isHasheader(Boolean hasHeader) {
		this.hasHeader = hasHeader;
	}

	public CSVReader() {
		setLine("");
		setCvssplitby(",");
		isHasheader(true);
	}

	public List<Player> GetData() {
		List<Player> players = new ArrayList<Player>();
		String[] playersCsv;
		Player player;
		Integer lineNumber = 1;

		// Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("data.csv").getFile());

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while ((line = br.readLine()) != null) {
				playersCsv = line.split(getCvssplitby());

				if (lineNumber > 1 || !getHasheader()) {
					player = new Player();

					if (!"".equals(playersCsv[0])) {
						player.setId(Integer.parseInt(playersCsv[0]));
					}

					player.setNationality(playersCsv[14]);
					player.setClub(playersCsv[3]);
					player.setFull_name(playersCsv[2]);

					if (!"".equals(playersCsv[17])) {
						float value = Float.parseFloat(playersCsv[17]);
						player.setEur_wage(value);
					} else {
						player.setEur_wage(0.0f);
					}

					if (!"".equals(playersCsv[18])) {
						float value = Float.parseFloat(playersCsv[18]);
						player.setEur_release_clause(value);
					} else {
						player.setEur_release_clause(0.0f);
					}

					if (!"".equals(playersCsv[8])) {
						LocalDate birthDate = LocalDate.parse(playersCsv[8]);
						player.setBirth_date(birthDate);
					}

					if (!"".equals(playersCsv[6])) {
						player.setAge(Integer.parseInt(playersCsv[6]));
					} else {
						player.setAge(0);
					}

					players.add(player);
				}
				lineNumber++;
			}
			return players;
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<Player>();
		}
	}
}