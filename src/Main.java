import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Empleado {
    protected String nombre;
    protected int edad;

    public Empleado(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public abstract double calcularSalario();
    public String toString() {

        return "Nombre: " + nombre + ", Edad: " + edad + ", Salario: " + calcularSalario();
    }
}

class EmpleadoPlanta extends Empleado {
    private static final double SALARIO_FIJO = 2100000;

    public EmpleadoPlanta(String nombre, int edad) {
        super(nombre, edad);
    }

    @Override
    public double calcularSalario() {

        return SALARIO_FIJO;
    }
}

class EmpleadoPorHoras extends Empleado {
    private static final double PAGO_POR_HORA = 98000;
    private int horasTrabajadas;

    public EmpleadoPorHoras(String nombre, int edad, int horasTrabajadas) {
        super(nombre, edad);
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public double calcularSalario() {
        return horasTrabajadas * PAGO_POR_HORA;
    }
}

class GestorEmpleados {
    private List<Empleado> empleados = new ArrayList<>();

    public void registrarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void mostrarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Empleado e : empleados) {
                System.out.println(e);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorEmpleados gestor = new GestorEmpleados();
        int opcion;

        do {
            System.out.println("\n--- Menú de Gestión de Empleados ---");
            System.out.println("1. Registrar Empleado de Planta");
            System.out.println("2. Registrar Empleado por Horas");
            System.out.println("3. Mostrar Empleados");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre: ");
                    String nombrePlanta = scanner.nextLine();
                    System.out.print("Ingrese la edad: ");
                    int edadPlanta = scanner.nextInt();
                    gestor.registrarEmpleado(new EmpleadoPlanta(nombrePlanta, edadPlanta));
                    System.out.println("Empleado de Planta registrado con éxito.");
                    break;
                case 2:
                    System.out.print("Ingrese el nombre: ");
                    String nombreHoras = scanner.nextLine();
                    System.out.print("Ingrese la edad: ");
                    int edadHoras = scanner.nextInt();
                    System.out.print("Ingrese las horas trabajadas: ");
                    int horasTrabajadas = scanner.nextInt();
                    gestor.registrarEmpleado(new EmpleadoPorHoras(nombreHoras, edadHoras, horasTrabajadas));
                    System.out.println("Empleado por Horas registrado con éxito.");
                    break;
                case 3:
                    gestor.mostrarEmpleados();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 4);

        scanner.close();
    }
}
