import java.util.Scanner;

public class losGuaduales {

    static Scanner sc = new Scanner(System.in);

    static void mostrarMenu() {
        System.out.println("===== LOS GUADUALES - CONTROL DE AGUA =====");
        System.out.println("1. Registrar consumo de un apartamento");
        System.out.println("2. Consultar factura de un apartamento");
        System.out.println("3. Ver reporte general del conjunto");
        System.out.println("4. Salir");
        System.out.print("Opción: ");
    }

    static void registrarConsumo(double[] consumos) {
        int apto;
        double consumo;

        System.out.print("Apartamento (1-6): ");
        apto = sc.nextInt();

        while (apto < 1 || apto > 6) {
            System.out.print("Inválido. Apartamento (1-6): ");
            apto = sc.nextInt();
        }

        System.out.print("Consumo en m³: ");
        consumo = sc.nextDouble();

        while (consumo <= 0) {
            System.out.print("Debe ser mayor que 0: ");
            consumo = sc.nextDouble();
        }

        consumos[apto - 1] = consumo;
        System.out.println("Consumo registrado correctamente.");
    }

    static void consultarFactura(double[] consumos) {
        int apto;

        System.out.print("Apartamento (1-6): ");
        apto = sc.nextInt();

        while (apto < 1 || apto > 6) {
            System.out.print("Inválido. Apartamento (1-6): ");
            apto = sc.nextInt();
        }

        if (consumos[apto - 1] == 0)
            System.out.println("El apartamento " + apto + " no tiene consumo registrado");
        else
            imprimirFactura(apto, consumos[apto - 1]);
    }

    static void imprimirFactura(int apartamento, double consumo) {
        double tarifa = consumo <= 10 ? 3000 : consumo <= 20 ? 4500 : 6000;
        double valor = consumo * tarifa;
        double subtotal = 12000 + valor;
        double recargo = consumo > 25 ? subtotal * 0.10 : 0;
        double total = subtotal + recargo;

        System.out.println("\n========== FACTURA ==========");
        System.out.println("Apartamento: " + apartamento);
        System.out.println("Consumo: " + consumo + " m³");
        System.out.println("Tarifa: $" + tarifa + " por m³");
        System.out.println("Valor consumo: $" + valor);
        System.out.println("Cargo fijo: $12000");
        System.out.println("Recargo: $" + recargo);

        if (consumo > 25)
            System.out.println("ALERTA: consumo excesivo");

        System.out.println("TOTAL A PAGAR: $" + total);
    }

    static void generarReporte(double[] consumos) {
        double total = 0, mayor = 0;
        int registrados = 0, excesivos = 0, aptoMayor = 0;

        for (int i = 0; i < consumos.length; i++) {
            if (consumos[i] > 0) {
                total += consumos[i];
                registrados++;

                if (consumos[i] > mayor) {
                    mayor = consumos[i];
                    aptoMayor = i + 1;
                }

                if (consumos[i] > 25)
                    excesivos++;
            }
        }

        if (registrados == 0) {
            System.out.println("Aún no hay consumos registrados");
            return;
        }

        System.out.println("\n========= REPORTE GENERAL =========");
        System.out.println("Consumo total: " + total + " m³");
        System.out.println("Consumo promedio: " + total / registrados + " m³");
        System.out.println("Mayor consumo: Apartamento " + aptoMayor +
                " (" + mayor + " m³)");
        System.out.println("Apartamentos sin registro: " + (6 - registrados));
        System.out.println("Consumo excesivo: " + excesivos);
    }

    public static void main(String[] args) {
        double[] consumos = new double[6];
        int opcion;

        do {
            mostrarMenu();
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> registrarConsumo(consumos);
                case 2 -> consultarFactura(consumos);
                case 3 -> generarReporte(consumos);
                case 4 -> System.out.println("Programa finalizado.");
                default -> System.out.println("Opción inválida");
            }
        }
        while (opcion != 4);
    }
}

