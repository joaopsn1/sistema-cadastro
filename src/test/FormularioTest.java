package test;

import service.FormularioService;

import java.io.File;
import java.util.Scanner;

public class FormularioTest {
    private static final Scanner SCANNER = new Scanner(System.in);
    File file = new File("");

    public static void main(String[] args) {
        int op;
        while (true) {
            menu();
            op = Integer.parseInt(SCANNER.nextLine());
            if (op == 0) break;
            switch (op) {
                case 1 -> {
                    formularioMenu();
                    op = Integer.parseInt(SCANNER.nextLine());
                    FormularioService.menu(op);
                }
            }
        }
    }

    public static void menu() {
        System.out.println("Type the number of your operation");
        System.out.println("1. Formulario");
        System.out.println("0. Exit");
    }

    public static void formularioMenu() {
        System.out.println("Type the number of your operation");
        System.out.println("1. Create File");
        System.out.println("2. Delete File");
        System.out.println("3. Read File");
        System.out.println("4. Answer for the questions");
        System.out.println("5. File List");
    }
}
