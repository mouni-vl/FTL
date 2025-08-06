package com.example.fantasy.batch.reader;

import com.example.fantasy.batch.csvRecord.PlayerCsvRecord;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;


public class PlayerCsvReader extends FlatFileItemReader<PlayerCsvRecord> {

    public PlayerCsvReader(Resource resource) {
        setResource(resource);
        setLinesToSkip(1); // skip header

        DefaultLineMapper<PlayerCsvRecord> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(
                "config", "fmId", "firstName", "secondName", "fullName",
                "permanentClubId", "permanentClubName", "basedClubId", "basedClubName",
                "loanClubId", "loanClubName", "dateOfBirth", "nationalityId", "nationalityName",
                "secondNationalityId", "secondNationalityName", "thirdNationalityId", "thirdNationalityName",
                "cityOfBirthId", "cityOfBirth", "gk", "dl", "dc", "dr", "wbl", "wbr", "dm", "mc",
                "ml", "mr", "aml", "amc", "amr", "sc", "currentAbility", "potentialAbility",
                "leftFoot", "rightFoot", "nfe", "squadNumber"
        );
        lineMapper.setLineTokenizer(tokenizer);

        lineMapper.setFieldSetMapper(new PlayerCsvRecordFieldSetMapper());

        setLineMapper(lineMapper);
    }
}
