package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class InputOutput {
    public static LinkedList<String> load(String pathFile) {
        Path path = Paths.get(pathFile);
        try {
            return Files.lines(path)
                .peek(line -> System.out.println(line))
                .collect(Collectors.toCollection(() -> new LinkedList<>()));
        } catch (IOException e) {
            System.out.println(e);
        }
        return new LinkedList<>();
    }

/*    public static List<LinkedList<String>> loadAll(String pathFolder) {
        Path path = Paths.get(pathFolder);
        try {
            return Files.list(path)
                .filter(p -> !Files.isDirectory(p))
                .peek(p -> System.out.println(p))
                .map(p -> Files
                                .lines(p)
                                .peek(line -> System.out.println(line))
                                .collect(Collectors.toCollection(() -> new LinkedList<>())))
                .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e);
        }
        return new LinkedList<>();
    }*/


    public static void export(List<String> output, String pathString) {
        Path path = Paths.get(pathString);
        try {
            Files.write(path, output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
