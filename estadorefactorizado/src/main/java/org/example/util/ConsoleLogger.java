package org.example.util;

public class ConsoleLogger implements Logger {
    @Override
    public void log(String mensaje) {
        System.out.println(mensaje);
    }
}
