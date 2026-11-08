
import java.util.Scanner;

public class InventarioCafeteria {

    static Scanner sc = new Scanner(System.in);
    static Producto[] p = new Producto[20];
    static double[][] ventas = new double[100][2];
    static int n = 0, v = 0;
    static double total = 0;

     public static void iniciar() { 
        int op;

        do {
            System.out.println("\n1. Registrar Producto\n2. Vender\n3. Actualizar Inventario\n4. Productos Agotados\n5. Total vendido en el dia\n6. Salir");
            op = sc.nextInt();

            switch (op) {
                case 1: registrar(); break;
                case 2: vender(); break;
                case 3: actualizar(); break;
                case 4: agotados(); break;
                case 5: System.out.println("Total: $" + total); break;
                case 6: System.out.println("Hasta pronto."); break;
                default: System.out.println("Opcion invalida.");
            }
        } while (op != 6);
    }

    static void registrar() {
        if (n == 20) {
            System.out.println("Inventario lleno.");
            return;
        }

        System.out.print("Codigo: ");
        String c = sc.next();

        if (buscar(c) != -1) {
            System.out.println("Codigo existente.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.next();
        System.out.print("Precio: ");
        double precio = sc.nextDouble();
        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();

        System.out.println("1.Bebidas 2.Panaderia 3.Snacks");
        int op = sc.nextInt();

        if (precio <= 0 || cantidad < 0 || op < 1 || op > 3) {
            System.out.println("Datos invalidos.");
            return;
        }

        String[] categorias = {"Bebidas", "Panaderia", "Snacks"};
        p[n++] = new Producto(c, nombre, precio, cantidad, categorias[op - 1]);
        System.out.println("Producto registrado.");
    }

    static void vender() {
        System.out.print("Codigo: ");
        int pos = buscar(sc.next());

        if (pos == -1) {
            System.out.println("Producto inexistente.");
            return;
        }

        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();

        if (cantidad <= 0 || cantidad > p[pos].cantidad || v == 100) {
            System.out.println("Venta no permitida.");
            return;
        }

        double subtotal = cantidad * p[pos].precio;
        p[pos].cantidad -= cantidad;
        ventas[v][0] = cantidad;
        ventas[v++][1] = subtotal;
        total += subtotal;

        System.out.println("Venta realizada: $" + subtotal);
    }

    static void actualizar() {
        System.out.print("Codigo: ");
        int pos = buscar(sc.next());

        if (pos == -1) {
            System.out.println("Producto inexistente.");
            return;
        }

        System.out.print("Cantidad a agregar: ");
        int cantidad = sc.nextInt();

        if (cantidad <= 0) {
            System.out.println("Cantidad invalida.");
            return;
        }

        p[pos].cantidad += cantidad;
        System.out.println("Inventario actualizado.");
    }

    static void agotados() {
        boolean hay = false;

        for (int i = 0; i < n; i++) {
            if (p[i].cantidad == 0) {
                System.out.println(p[i].nombre);
                hay = true;
            }
        }

        if (!hay) System.out.println("No hay productos agotados.");
    }

    static int buscar(String codigo) {
        for (int i = 0; i < n; i++)
            if (p[i].codigo.equalsIgnoreCase(codigo)) return i;
        return -1;
    }

    static class Producto {
        String codigo, nombre, categoria;
        double precio;
        int cantidad;

        Producto(String c, String n, double p, int cant, String cat) {
            codigo = c;
            nombre = n;
            precio = p;
            cantidad = cant;
            categoria = cat;
        }
    }
}
