package repository;

import domain.Pessoa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FormularioRepository {
    static Pessoa pessoa;
    static List<File> arquivos = new ArrayList<>();

    public static File createFile(String nomeArquivo) {
        if (nomeArquivo == null || nomeArquivo.isEmpty()) {
            throw new IllegalArgumentException("O nome do arquivo não pode ser nulo ou vazio.");
        }

        File file = new File(nomeArquivo);
        try {
            boolean isCreated = file.createNewFile();
            System.out.printf("Created %s%n", isCreated);
            if (isCreated) {
                arquivos.add(file);
                System.out.printf("Arquivo adicionado a lista: %s%n", file.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void fileList() {
        if (arquivos.isEmpty()) {
            System.out.println("Nenhum arquivo criado!");
            return;
        }
        arquivos.stream().map(File::getName).forEach(System.out::println);
    }

    public static void fileReader(String nomeArquivo) {
        if (nomeArquivo == null) {
            System.out.println("O arquivo ainda não foi definido!");
            return;
        }

        File file = new File(nomeArquivo);
        if (!file.exists()) {
            System.out.printf("Arquivo {%s} não existe%n", nomeArquivo);
            return;
        }

        try (FileReader fr = new FileReader(nomeArquivo);
             BufferedReader bufferedReader = new BufferedReader(fr)) {
            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                System.out.printf("%s%n", linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteFile(String nomeArquivo) {
        if (nomeArquivo == null || nomeArquivo.isEmpty()) {
            System.out.println("Nome do arquivo inválido");
            return false;
        }

        File file = new File(nomeArquivo);
        boolean exists = file.exists();
        if (exists) {
            boolean isDeleted = file.delete();
            System.out.printf("Deleted %s%n", isDeleted);
            arquivos.remove(file);
        }
        System.out.printf("O arquivo {%s} não existe%n", nomeArquivo);
        return false;
    }

    public static File answersFileQuestions(Pessoa pessoa, String nomeArquivo) {
        if (pessoa == null || nomeArquivo == null || nomeArquivo.isBlank()) {
            throw new IllegalArgumentException("Pessoa ou nome do arquivo inválidos!");
        }

        File fileAnswer = new File(nomeArquivo);
        if (fileAnswer.exists()) {
            System.out.println("Arquivo já existente");
            return fileAnswer;
        }
        try {
            boolean isCreated = fileAnswer.createNewFile();
            System.out.printf("Created file %s%n", isCreated);
            try (FileWriter fw = new FileWriter(fileAnswer)) {

                fw.write("1 - %s%n".formatted(pessoa.getName()));
                fw.write("2 - %s%n".formatted(pessoa.getEmail()));
                fw.write("3 - %s%n".formatted(pessoa.getAge()));
                fw.write("4 - %s%n".formatted(pessoa.getHeight()));

                if (isCreated) {
                    arquivos.add(fileAnswer);
                    System.out.printf("Arquivo adicionado a lista: %s%n", fileAnswer.getName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileAnswer;
    }
}
