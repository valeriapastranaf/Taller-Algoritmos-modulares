import java.util.Scanner;
public class menu2 {
   
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int op;

        do {
            System.out.println("1. Sistema IPS");
            System.out.println("2. Inventario cafeteria");
            System.out.println("3. Sistema Academico");
            System.out.println("4. Salir");

            op = sc.nextInt();

            switch (op) {
                case 1: SistemaIPS.iniciar(); break;
                case 2: InventarioCafeteria.iniciar(); break;
                case 3: SistemaAcademico.iniciar(); break;
                case 4: System.out.println("Saliendo..."); break;
                default: System.out.println("Opcion no valida.");
            }

        } while (op != 4);
    }
}

