package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

class PlatoNoEncontradoException extends Exception {
    public PlatoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        HashMap<String, Double> Menu = new HashMap<>();
        ArrayList<String> pedidoUsuario = new ArrayList<>();

        Menu.put("almuerzo tradicional", 12.000);
        Menu.put("almuerzo ejecutivo", 15.000);
        Menu.put("especial del dia", 20.000);
        Menu.put("consome de pescado", 10.000);
        Menu.put("bandeja paisa", 25.000);
        Menu.put("cazuela de frijoles", 25.000);
        Menu.put("pechuga gratinada", 30.000);
        Menu.put("jugo del dia", 1.000);

        System.out.println("Menu Restaurante <3");
        for (String plato : Menu.keySet()) {
            System.out.println(plato + " " + Menu.get(plato));
        }

        Double total = 0.0;
        System.out.println("Ingrese su pedido (Al final del pedido ingrese la palabra FIN)");

        while (true) {
            try {
                System.out.print("Plato: ");
                String pedido = scanner.nextLine().toLowerCase();

                if (pedido.equalsIgnoreCase("Fin")) {
                    break;
                }
                if (pedido.isEmpty() || pedido.matches("\\d+")) {
                    throw new java.util.InputMismatchException("Por favor ingrese un nombre de plato.");
                }
                if (!Menu.containsKey(pedido)) {
                    throw new PlatoNoEncontradoException("El plato no existe en el menú.");
                }


                total += Menu.get(pedido);
                pedidoUsuario.add(pedido);
                System.out.println(pedido + ". Producto agregado");

            } catch (PlatoNoEncontradoException e) {
                System.out.println("Producto invalido. " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Texto invalido. " + e.getMessage());
            }


        }

        ArrayList<String> platosPedidos = new ArrayList<>();
        platosPedidos.add("Almuerzo tradicional");
        platosPedidos.add("Almuerzo ejecutivo");
        platosPedidos.add("Especial del dia");
        platosPedidos.add("Consome de pescado");
        platosPedidos.add("Bandeja Paisa");
        platosPedidos.add("Cazuela de frijoles");
        platosPedidos.add("Pechuga gratinada");
        platosPedidos.add("Jugo del dia");


        System.out.println("Subtotal a pagar es: " + total);
        System.out.println("USTED HA COMPRADO");
        for (String plato : pedidoUsuario) {
            System.out.println("Se ha comprado " + plato + " " + Menu.get(plato));
        }
        Double totalDescuento;
        Double descuento;
        if (total > 100.000) {
            descuento = (total * 15 / 100);
            totalDescuento = total - descuento;
            System.out.println("Gracias a su gran compra se le ha hecho un descuentro del 15%");
            System.out.println("El precio total de su compra es de: " + totalDescuento);
        }
        if (total < 99.000 && total >= 50.000) {
            descuento = (total * 10 / 100);
            totalDescuento = total - descuento;
            System.out.println("Por su compra obten un descuentro del 10%");
            System.out.println("El total de su compra es de: " + totalDescuento);
        }
        pedidoUsuario.stream()
                .collect(java.util.stream.Collectors.groupingBy(p -> p, java.util.stream.Collectors.counting()))
                .entrySet()
                .stream()
                .max(java.util.Map.Entry.comparingByValue())
                .ifPresent(entry ->
                        System.out.println(" El plato más pedido fue:" + entry.getKey()));

    }
}


















