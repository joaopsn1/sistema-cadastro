package service;

import domain.Pessoa;
import repository.FormularioRepository;

import java.io.File;
import java.util.Scanner;

public class FormularioService {
    static File file;
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void menu(int op) {
        switch (op) {
            case 1 -> createFile();
            case 2 -> deleteFile();
            case 3 -> fileReader();
            case 4 -> answersFileQuestions();
            case 5 -> answersFileReader();
        }
    }

    private static void createFile() {
        FormularioRepository.createFile();
    }

    private static void deleteFile() {
        FormularioRepository.deleteFile();
    }

    private static void fileReader() {
        FormularioRepository.fileReader();
    }

    private static void answersFileQuestions() {
        System.out.println("Type the name");
        String name = SCANNER.nextLine();
        System.out.println("Type the email");
        String email = SCANNER.nextLine();
        System.out.println("Type the age");
        Integer age = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Type the height");
        Double height = Double.parseDouble(SCANNER.nextLine());
        Pessoa pessoa = Pessoa.builder()
                .name(name)
                .email(email)
                .age(age)
                .height(height)
                .build();
        FormularioRepository.answersFileQuestions(pessoa);
    }

    private static void answersFileReader() {
        FormularioRepository.answersFileReader();
    }
}
