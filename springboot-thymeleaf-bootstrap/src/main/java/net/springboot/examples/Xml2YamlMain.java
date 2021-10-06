package net.springboot.examples;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import liquibase.changelog.ChangeLogParameters;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.exception.LiquibaseException;
import liquibase.parser.ChangeLogParser;
import liquibase.parser.ChangeLogParserFactory;
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.resource.ResourceAccessor;
import liquibase.serializer.ChangeLogSerializer;
import liquibase.serializer.ChangeLogSerializerFactory;
import liquibase.util.file.FilenameUtils;

public class Xml2YamlMain {

    public static void main(String[] args) {
        final String input = "C:\\Users\\wpw\\Documents\\db-changelog-master.xml";
        final String output = "C:\\Users\\wpw\\Documents\\db.changelog-master.yaml";
        ResourceAccessor resourceAccessor = new FileSystemResourceAccessor();
        ChangeLogParser parser = null;
        try {
            parser = ChangeLogParserFactory.getInstance().getParser(FilenameUtils.getName(input), resourceAccessor);
            DatabaseChangeLog changeLog = parser.parse(input, new ChangeLogParameters(), resourceAccessor);

            ChangeLogSerializer serializer = ChangeLogSerializerFactory.getInstance()
                    .getSerializer(FilenameUtils.getExtension(output));
            try (OutputStream ymlOutputstream = Files.newOutputStream(Paths.get(output))) {
                serializer.write(changeLog.getChangeSets(), ymlOutputstream);
            } catch (IOException e) {
                throw new RuntimeException("Unable to write output file " + output, e);
            }
        } catch (LiquibaseException e) {
            throw new RuntimeException("Unable to process liquibase file " + input, e);
        }
    }
}
