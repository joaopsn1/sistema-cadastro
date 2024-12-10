package repository;

import domain.Pessoa;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FormularioRepository {
    static Pessoa pessoa;
    static File file = new File("formulario.txt");
    static String fileName = "";
    static File answerFile;

    public static File createFile() {
        try {
            boolean isCreated = file.createNewFile();
            System.out.printf("Created %s%n", isCreated);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void fileReader() {
        try (FileReader fr = new FileReader(file)) {
            int i;
            while ((i = fr.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File deleteFile() {
        boolean exists = file.exists();
        if (exists) {
            boolean isDeleted = file.delete();
            System.out.printf("Deleted %s%n", isDeleted);
        }
        return file;
    }

    public static void answersFileQuestions(Pessoa pessoa) {
        fileName = pessoa.getName();
        answerFile = new File("1 - %s.txt".formatted(fileName.toUpperCase()));
        try (FileWriter fw = new FileWriter(answerFile)) {
            boolean isCreated = answerFile.createNewFile();
            System.out.printf("Created file %s%n", isCreated);
            fw.write("1 - %s%n".formatted(pessoa.getName()));
            fw.write("2 - %s%n".formatted(pessoa.getEmail()));
            fw.write("3 - %s%n".formatted(pessoa.getAge()));
            fw.write("4 - %s%n".formatted(pessoa.getHeight()));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void answersFileReader() {
        if (answerFile == null) {
            System.out.println("O arquivo ainda não foi definido!");
            return;
        }
        try (FileReader fr = new FileReader(answerFile.getAbsolutePath())) {
            int i;
            while ((i = fr.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
