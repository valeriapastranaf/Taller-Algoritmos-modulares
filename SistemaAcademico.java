import java.util.Scanner;

public class SistemaAcademico {
static Scanner sc = new Scanner(System.in);
static final int MAX = 25;
static String[] codigos = new String[MAX], nombres = new String[MAX], estados = new String[MAX];
static double[][] notas = new double[MAX][3];
static double[] asistencia = new double[MAX], definitiva = new double[MAX];
static int total = 0;

public static void iniciar() {
int op;
do {
System.out.println("\nSISTEMA ACADEMICO");
System.out.println("1. Registrar estudiante");
System.out.println("2. Mostrar estudiantes");
System.out.println("3. Calcular definitivas");
System.out.println("4. Promedio del grupo");
System.out.println("5. Mejor y peor promedio");
System.out.println("6. Volver");
op = entero("Opcion: ");
switch (op) {
case 1: registrar(); break;
case 2: mostrar(); break;
case 3: calcular(); break;
case 4: promedioGrupo(); break;
case 5: mejorPeor(); break;
case 6: break;
default: System.out.println("Opcion no valida.");
}
} while (op != 6);
}

static void registrar() {
if (total == MAX) { System.out.println("No hay espacio."); return; }
String c = texto("Codigo: ");
while (c.isEmpty() || existe(c)) {
System.out.println("Codigo vacio o ya registrado.");
c = texto("Codigo: ");
}
codigos[total] = c;
nombres[total] = texto("Nombre: ");
while (nombres[total].isEmpty()) {
System.out.println("El nombre no puede estar vacio.");
nombres[total] = texto("Nombre: ");
}
for (int j = 0; j < 3; j++) {
notas[total][j] = decimal("Nota " + (j + 1) + " (0 a 5): ");
while (!Double.isFinite(notas[total][j]) || notas[total][j] < 0 || notas[total][j] > 5) {
System.out.println("La nota debe estar entre 0 y 5.");
notas[total][j] = decimal("Nota " + (j + 1) + ": ");
}
}
asistencia[total] = decimal("Asistencia (0 a 100): ");
while (!Double.isFinite(asistencia[total]) || asistencia[total] < 0 || asistencia[total] > 100) {
System.out.println("La asistencia debe estar entre 0 y 100.");
asistencia[total] = decimal("Asistencia: ");
}
actualizar(total);
total++;
System.out.println("Estudiante registrado.");
}

static boolean existe(String c) {
for (int i = 0; i < total; i++)
if (codigos[i].equalsIgnoreCase(c)) return true;
return false;
}

static void actualizar(int i) {
definitiva[i] = (notas[i][0] + notas[i][1] + notas[i][2]) / 3;
estados[i] = definitiva[i] >= 3 ? "Aprobado" : definitiva[i] >= 2 ? "Habilita" : "Reprueba";
}

static void calcular() {
if (total == 0) { System.out.println("No hay estudiantes."); return; }
for (int i = 0; i < total; i++) {
actualizar(i);
System.out.println(nombres[i] + ": " + String.format("%.2f", definitiva[i]) + " - " + estados[i]);
}
}

static void mostrar() {
if (total == 0) { System.out.println("No hay estudiantes."); return; }
for (int i = 0; i < total; i++)
System.out.println(codigos[i] + " | " + nombres[i] + " | Notas: "
+ notas[i][0] + ", " + notas[i][1] + ", " + notas[i][2]
+ " | Asistencia: " + asistencia[i] + "% | Definitiva: "
+ String.format("%.2f", definitiva[i]) + " | " + estados[i]);
}

static void promedioGrupo() {
if (total == 0) { System.out.println("No hay estudiantes."); return; }
double suma = 0;
for (int i = 0; i < total; i++) suma += definitiva[i];
System.out.println("Promedio del grupo: " + String.format("%.2f", suma / total));
}

static void mejorPeor() {
if (total == 0) { System.out.println("No hay estudiantes."); return; }
int mejor = 0, peor = 0;
for (int i = 1; i < total; i++) {
if (definitiva[i] > definitiva[mejor]) mejor = i;
if (definitiva[i] < definitiva[peor]) peor = i;
}
System.out.println("Mejor: " + nombres[mejor] + " - " + definitiva[mejor]);
System.out.println("Peor: " + nombres[peor] + " - " + definitiva[peor]);
}

static int entero(String mensaje) {
while (true) {
try { System.out.print(mensaje); return Integer.parseInt(sc.nextLine()); }
catch (NumberFormatException e) { System.out.println("Ingrese un entero valido."); }
}
}

static double decimal(String mensaje) {
while (true) {
try { System.out.print(mensaje); return Double.parseDouble(sc.nextLine().replace(",", ".")); }
catch (NumberFormatException e) { System.out.println("Ingrese un numero valido."); }
}
}

static String texto(String mensaje) {
System.out.print(mensaje);
return sc.nextLine().trim();
}
}