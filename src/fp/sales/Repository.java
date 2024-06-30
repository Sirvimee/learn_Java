package fp.sales;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Repository {

    private static final String FILE_PATH = "src/fp/sales/sales-data.csv";

    public List<Entry> getEntries() {
        try {
            return Files.lines(Paths.get(FILE_PATH))
                    .skip(1)
                    .map(this::toEntry)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Entry toEntry(String line) {
        String[] parts = line.split("\t");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(parts[0], formatter);
        String state = parts[1].trim();
        String productId = parts[2].trim();
        String category = parts[3].trim();
        double sales = Double.parseDouble(parts[5].trim().replace(",", "."));

        return new Entry(date, state, productId, category, sales);
    }

}
