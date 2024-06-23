package yeinyeonha.SMooD.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class SqlBatchInsertService {
    private final JdbcTemplate jdbcTemplate;

    public void batchInsertFromSqlFile(String resourcePath) throws IOException {
        List<String> sqlStatements = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource(resourcePath).getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                sqlStatements.add(line.trim());
            }
        }

        Pattern pattern = Pattern.compile("INSERT INTO (\\w+) \\((.+?)\\) VALUES \\((.+?)\\);");

        String currentTable = null;
        String currentColumns = null;
        List<Object[]> batchArgs = new ArrayList<>();

        for (String sql : sqlStatements) {
            Matcher matcher = pattern.matcher(sql);
            if (matcher.matches()) {
                String table = matcher.group(1);
                String columns = matcher.group(2);
                String values = matcher.group(3);

                if (currentTable == null) {
                    currentTable = table;
                    currentColumns = columns;
                }

                if (!currentTable.equals(table)) {
                    // Perform batch insert for the previous table
                    String insertSql = String.format("INSERT INTO %s (%s) VALUES (%s)", currentTable, currentColumns, generatePlaceholders(currentColumns));
                    jdbcTemplate.batchUpdate(insertSql, batchArgs);

                    // Reset for the new table
                    currentTable = table;
                    currentColumns = columns;
                    batchArgs = new ArrayList<>();
                }

                batchArgs.add(values.split(","));
            }
        }

        // Perform the final batch insert
        if (!batchArgs.isEmpty()) {
            String insertSql = String.format("INSERT INTO %s (%s) VALUES (%s)", currentTable, currentColumns, generatePlaceholders(currentColumns));
            jdbcTemplate.batchUpdate(insertSql, batchArgs);
        }
    }

    private String generatePlaceholders(String columns) {
        int count = columns.split(",").length;
        String[] placeholders = new String[count];
        for (int i = 0; i < count; i++) {
            placeholders[i] = "?";
        }
        return String.join(",", placeholders);
    }
}
