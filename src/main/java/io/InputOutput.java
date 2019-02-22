package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class InputOutput {
    public static LinkedList<String> load(String pathString) {
        Path path = Paths.get(pathString);
        LinkedList<String> lines = new LinkedList<>();
        try {
            Files.lines(path)
                .peek(line -> System.out.println(line))
                .forEach(line -> lines.add(line));
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(lines);
        return lines;
    }

    public static void export(List<String> output, String pathString) {
        Path path = Paths.get(pathString);
        try {
            Files.write(path, output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
