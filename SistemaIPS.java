import java.util.Scanner;

public class SistemaIPS {
static Scanner sc = new Scanner(System.in);
static final int MAX = 30, CUPOS = 10;
static String[] doc = new String[MAX], nom = new String[MAX], tipo = new String[MAX], estado = new String[MAX];
static int[] edad = new int[MAX], prioridad = new int[MAX];
static int total = 0, ocupados = 0;

public static void iniciar() {
int op;
do {
System.out.println("\nSISTEMA IPS");
System.out.println("1. Registrar paciente");
System.out.println("2. Listar pacientes");
System.out.println("3. Buscar por documento");
System.out.println("4. Contar por tipo de atencion");
System.out.println("5. Paciente con mayor prioridad");
System.out.println("6. Consultar cupos");
System.out.println("7. Volver");
op = entero("Opcion: ");
switch (op) {
case 1: registrar(); break;
case 2: listar(); break;
case 3: buscar(); break;
case 4: contar(); break;
case 5: mayorPrioridad(); break;
case 6: cupos(); break;
case 7: break;
default: System.out.println("Opcion no valida.");
}
} while (op != 7);
}

static void registrar() {
if (total == MAX) { System.out.println("No hay espacio."); return; }
String d = texto("Documento: ");
while (d.isEmpty() || existe(d)) {
System.out.println("Documento vacio o ya registrado.");
d = texto("Documento: ");
}
doc[total] = d;
nom[total] = texto("Nombre completo: ");
while (nom[total].isEmpty()) {
System.out.println("El nombre no puede estar vacio.");
nom[total] = texto("Nombre completo: ");
}
edad[total] = entero("Edad: ");
while (edad[total] <= 0) {
System.out.println("Edad no valida.");
edad[total] = entero("Edad: ");
}
System.out.println("1. Urgencias 2. Consulta general 3. Prioritaria");
int t = entero("Tipo de atencion: ");
while (t < 1 || t > 3) {
System.out.println("Seleccione 1, 2 o 3.");
t = entero("Tipo de atencion: ");
}
tipo[total] = new String[]{"Urgencias", "Consulta general", "Prioritaria"}[t - 1];
prioridad[total] = entero("Prioridad (1 a 5, 1 es mayor): ");
while (prioridad[total] < 1 || prioridad[total] > 5) {
System.out.println("La prioridad debe estar entre 1 y 5.");
prioridad[total] = entero("Prioridad: ");
}
if (ocupados < CUPOS) {
estado[total] = "Atencion inmediata";
ocupados++;
} else estado[total] = "En espera";
total++;
System.out.println("Paciente registrado.");
}

static boolean existe(String d) {
for (int i = 0; i < total; i++)
if (doc[i].equalsIgnoreCase(d)) return true;
return false;
}

static void listar() {
if (total == 0) { System.out.println("No hay pacientes."); return; }
for (int i = 0; i < total; i++)
System.out.println(doc[i] + " | " + nom[i] + " | " + edad[i] + " | " + tipo[i]
+ " | Prioridad: " + prioridad[i] + " | " + estado[i]);
}

static void buscar() {
String d = texto("Documento a buscar: ");
for (int i = 0; i < total; i++) {
if (doc[i].equalsIgnoreCase(d)) {
System.out.println(nom[i] + " | " + tipo[i] + " | Prioridad: " + prioridad[i] + " | " + estado[i]);
return;
}
}
System.out.println("Paciente no encontrado.");
}

static void contar() {
int[][] resumen = new int[3][2];
for (int i = 0; i < total; i++) {
int fila = tipo[i].equals("Urgencias") ? 0 : tipo[i].equals("Consulta general") ? 1 : 2;
int col = estado[i].equals("Atencion inmediata") ? 0 : 1;
resumen[fila][col]++;
}
String[] tipos = {"Urgencias", "Consulta general", "Prioritaria"};
for (int i = 0; i < 3; i++)
System.out.println(tipos[i] + ": " + (resumen[i][0] + resumen[i][1])
+ " (inmediata: " + resumen[i][0] + ", espera: " + resumen[i][1] + ")");
}

static void mayorPrioridad() {
if (total == 0) { System.out.println("No hay pacientes."); return; }
int pos = 0;
for (int i = 1; i < total; i++)
if (prioridad[i] < prioridad[pos]) pos = i;
System.out.println("Mayor prioridad: " + nom[pos] + " - Nivel " + prioridad[pos]);
}

static void cupos() {
System.out.println("Cupos disponibles: " + (CUPOS - ocupados));
System.out.println("Cupos ocupados: " + ocupados);
}

static int entero(String mensaje) {
while (true) {
try { System.out.print(mensaje); return Integer.parseInt(sc.nextLine()); }
catch (NumberFormatException e) { System.out.println("Ingrese un entero valido."); }
}
}

static String texto(String mensaje) {
System.out.print(mensaje);
return sc.nextLine().trim();
}
}