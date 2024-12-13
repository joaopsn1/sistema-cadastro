package service;

import domain.Pessoa;
import repository.FormularioRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FormularioService {
    static Pessoa pessoa;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static List<String> personList = new ArrayList<>();
    private static List<File> fileList = new ArrayList<>();
    static File cadastroUsuario = new File("usuarioscadastrados.txt");
    static File cadastroArquivos = new File("arquivoscriados.txt");


    public static void menu(int op) {
        switch (op) {
            case 1 -> createFile();
            case 2 -> deleteFile();
            case 3 -> fileReader();
            case 4 -> personRegister();
            case 5 -> personList();
            case 6 -> fileList();
            case 7 -> registerNewQuestion();
            case 8 -> deleteQuestionsCreated();
            case 9 -> searchUsersByName();
        }
    }

    private static void createFile() {
        fileList.add(FormularioRepository.createFile("formulario.txt"));
        salvarArquivosCadastrados();
    }

    private static void deleteFile() {
        System.out.print("Digite o nome do arquivo para remover: ");
        String removeFile = SCANNER.nextLine();
        FormularioRepository.deleteFile(removeFile);
    }

    private static void personList() {
        int i = 1;
        System.out.println("Usuários cadastrados");
        try (FileReader fr = new FileReader(cadastroUsuario);
             BufferedReader bufferedReader = new BufferedReader(fr)) {
            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                System.out.printf("%d - %s%n", i, linha);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void salvarUsuariosCadastrados() {
        try (FileWriter fw = new FileWriter(cadastroUsuario, true);
             BufferedWriter bfw = new BufferedWriter(fw)) {
            for (String name : personList) {
                bfw.write("%s".formatted(name));
            }
            bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileList() {
        int i = 1;
        System.out.println("Arquivos cadastrados");
        try (FileReader fr = new FileReader(cadastroArquivos);
             BufferedReader bufferedReader = new BufferedReader(fr)) {
            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                System.out.printf("%d - %s%n", i, linha);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void salvarArquivosCadastrados() {
        try (FileWriter fw = new FileWriter(cadastroArquivos, true);
             BufferedWriter bfw = new BufferedWriter(fw)) {
            for (File name : fileList) {
                bfw.write("%s".formatted(name));
            }
            bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileReader() {
        System.out.print("Digite o nome do arquivo para ler: ");
        String readFile = SCANNER.nextLine();
        FormularioRepository.fileReader(readFile);
    }

    private static void personRegister() {
        try (FileReader fr = new FileReader("formulario.txt");
             BufferedReader bufferedReader = new BufferedReader(fr)) {
            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                System.out.printf("%s%n", linha);
            }
            String name = SCANNER.nextLine();
            String email = SCANNER.nextLine();
            Integer age = Integer.parseInt(SCANNER.nextLine());
            Double height = Double.parseDouble(SCANNER.nextLine());
            Pessoa pessoa = Pessoa.builder()
                    .name(name)
                    .email(email)
                    .age(age)
                    .height(height)
                    .build();
            personList.add(name);
            salvarUsuariosCadastrados();
            fileList.add(FormularioRepository.personRegister(pessoa, "1-%s.txt".formatted(name.toUpperCase().replaceAll("\\s+", ""))));
            salvarArquivosCadastrados();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void registerNewQuestion() {
        System.out.println("Digite a pergunta para adicioná-la ao formulário");
        String question = SCANNER.nextLine();
        FormularioRepository.registerNewQuestion(question);
    }

    private static void deleteQuestionsCreated() {
        FormularioRepository.deleteQuestionsCreated();
    }

    private static void searchUsersByName() {
        FormularioRepository.searchUsersByName();
    }
}
