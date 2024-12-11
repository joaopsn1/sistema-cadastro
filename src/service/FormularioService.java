package service;

import domain.Pessoa;
import repository.FormularioRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FormularioService {
    static Pessoa pessoa;
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void menu(int op) {
        switch (op) {
            case 1 -> createFile();
            case 2 -> deleteFile();
            case 3 -> fileReader();
            case 4 -> answersFileQuestions();
            case 5 -> fileList();
        }
    }

    private static void createFile() {
        FormularioRepository.createFile("formulario.txt");
    }

    private static void deleteFile() {
        System.out.print("Digite o nome do arquivo para remover: ");
        String removeFile = SCANNER.nextLine();
        FormularioRepository.deleteFile(removeFile);
    }

    private static void fileList() {
        FormularioRepository.fileList();
    }

    private static void fileReader() {
        System.out.print("Digite o nome do arquivo para ler: ");
        String readFile = SCANNER.nextLine();
        FormularioRepository.fileReader(readFile);
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
        FormularioRepository.answersFileQuestions(pessoa, "1 - %s.txt".formatted(name.toUpperCase()));
    }
}
