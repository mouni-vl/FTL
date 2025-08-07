package com.example.fantasy.batch.reader;

import com.example.fantasy.batch.csvRecord.PlayerCsvRecord;
import lombok.extern.slf4j.Slf4j;
import java.nio.charset.StandardCharsets;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;


@Slf4j
public class PlayerCsvReader extends FlatFileItemReader<PlayerCsvRecord> {

    private static final String[] COLUMN_NAMES = new String[]{
            "config", "fmId", "firstName", "secondName", "fullName",
            "permanentClubId", "permanentClubName", "basedClubId", "basedClubName", "loanClubId", "loanClubName",
            "dateOfBirth", "nationalityId", "nationalityName", "secondNationalityId", "secondNationalityName",
            "thirdNationalityId", "thirdNationalityName", "cityOfBirthId", "cityOfBirth",
            "gk", "dl", "dc", "dr", "wbl", "wbr", "dm", "mc", "ml", "mr", "aml", "amc", "amr", "sc",
            "currentAbility", "potentialAbility", "leftFoot", "rightFoot", "nfe", "squadNumber"
    };

    public PlayerCsvReader(Resource resource) {
        setResource(resource);
        setEncoding(StandardCharsets.UTF_8.name());
        setLinesToSkip(1); // Skip header line
        setName("playerCsvReader");

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(COLUMN_NAMES);
        tokenizer.setDelimiter(",");
        tokenizer.setQuoteCharacter('"');
        tokenizer.setStrict(false);

        DefaultLineMapper<PlayerCsvRecord> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new PlayerCsvRecordFieldSetMapper());

        setLineMapper(lineMapper);
    }
}
