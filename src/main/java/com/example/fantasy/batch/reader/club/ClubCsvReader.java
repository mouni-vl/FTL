package com.example.fantasy.batch.reader.club;

import com.example.fantasy.batch.csvRecord.ClubCsvRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

import java.nio.charset.StandardCharsets;

@Slf4j
public class ClubCsvReader extends FlatFileItemReader<ClubCsvRecord> {

    private static final String[] COLUMN_NAMES = new String[]{
            "Config","ID", "FM ID", "Name", "Short Name", "6 Letter Name", "3 Letter Name", "Alt 3 Letter Name",
            "Footballing Nation ID", "Footballing Nation Name", "Country ID", "Country Name",
            "City ID", "City Name", "Division ID", "Division Name",
            "Reputation", "Likely Finishing Group", "Captain ID", "Captain Name",
            "Vice Captain ID", "Vice Captain Name", "CA16", "Extinct", "NFE", "Official Hashtag #",
            "Year Founded", "First Nickname", "Titlebar FG Colour", "Titlebar BG Colour",
            "StadiumFM ID", "Stadium ID", "Stadium Name", "Max Attendance"
    };


    public ClubCsvReader(Resource resource) {
        setResource(resource);
        setEncoding(StandardCharsets.UTF_8.name());
        setLinesToSkip(1); // Skip header line
        setName("clubCsvReader");

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(COLUMN_NAMES);
        tokenizer.setDelimiter(",");
        tokenizer.setQuoteCharacter('"');
        tokenizer.setStrict(false);

        DefaultLineMapper<ClubCsvRecord> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new ClubCsvRecordFieldSetMapper());

        setLineMapper(lineMapper);
    }

}
