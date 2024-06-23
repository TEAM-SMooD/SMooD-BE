package yeinyeonha.SMooD;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import yeinyeonha.SMooD.service.SqlBatchInsertService;

@RequiredArgsConstructor
@Component
public class SqlBatchInsertRunner implements CommandLineRunner {
    private static SqlBatchInsertService sqlBatchInsertService;

    @Override
    public void run(String... args) throws Exception {
        sqlBatchInsertService.batchInsertFromSqlFile("sql/data.sql");
    }
}
