package com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

class FileReader {
    public static Lines readFileLinesAfterHeader(String fileName) {
        try {
            return new Lines(new FileReader(Paths.get(fileName)).skipHeader());
        } catch (IOException exception) {
            throw new CannotReadEmployeesException(
                String.format("cannot read file = '%s'", Paths.get(fileName).toAbsolutePath()),
                exception
            );
        }
    }

    private final Path path;

    private FileReader(Path path) {
        this.path = path;
    }

    private Iterator<String> skipHeader() throws IOException {
        return skipHeader(readFile());
    }

    private Iterator<String> readFile() throws IOException {
        List<String> lines = Files.readAllLines(path);
        return lines.iterator();
    }

    private Iterator<String> skipHeader(Iterator<String> iterator) {
        iterator.next();
        return iterator;
    }
}