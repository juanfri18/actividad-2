package src;

import java.util.Random;

/**
 * Generador de contraseñas con distintos niveles de seguridad.
 * Permite generar contraseñas simples o seguras, validar su fuerza y aplicar reglas personalizadas.
 * 
 * @author Tu Nombre
 * @version 1.0
 */
public class GeneradorContrasenas {

    private static final String LETRAS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMEROS = "0123456789";
    private static final String SIMBOLOS = "!@#$%^&*()-_=+[]{};:,.<>?";

    /**
     * Genera una contraseña simple de longitud 8 con solo letras.
     * 
     * @return contraseña simple de 8 caracteres
     */
    public static String generarSimple() {
        return generarConjunto(LETRAS, 8);
    }

    /**
     * Genera una contraseña segura de longitud 12 con letras, números y símbolos.
     * 
     * @return contraseña segura de 12 caracteres
     */
    public static String generarSegura() {
        String conjunto = LETRAS + NUMEROS + SIMBOLOS;
        return generarConjunto(conjunto, 12);
    }

    /**
     * Genera una contraseña personalizada con las reglas indicadas.
     * 
     * @param longitud longitud de la contraseña
     * @param usarLetras incluir letras si es true
     * @param usarNumeros incluir números si es true
     * @param usarSimbolos incluir símbolos si es true
     * @return contraseña generada según reglas
     * @throws IllegalArgumentException si longitud <= 0 o ningún tipo de carácter seleccionado
     */
    public static String generarConReglas(int longitud, boolean usarLetras, boolean usarNumeros, boolean usarSimbolos) {
        if (longitud <= 0) {
            throw new IllegalArgumentException("La longitud debe ser mayor que 0");
        }
        String conjunto = "";
        if (usarLetras) conjunto += LETRAS;
        if (usarNumeros) conjunto += NUMEROS;
        if (usarSimbolos) conjunto += SIMBOLOS;

        if (conjunto.isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar al menos un tipo de carácter");
        }
        return generarConjunto(conjunto, longitud);
    }

    /**
     * Valida la fuerza de una contraseña según longitud y tipos de caracteres.
     * 
     * @param password contraseña a evaluar
     * @return "Débil", "Media" o "Fuerte" según criterios de seguridad
     */
    public static String validarFuerza(String password) {
        int score = 0;
        if (password.length() >= 8) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[0-9].*")) score++;
        if (password.matches(".*[!@#$%^&*()-_=+\\[\\]{};:,.<>?].*")) score++;

        switch (score) {
            case 4: return "Fuerte";
            case 3: return "Media";
            default: return "Débil";
        }
    }

    /**
     * Método auxiliar para generar una contraseña de un conjunto de caracteres.
     * 
     * @param conjunto caracteres a usar
     * @param longitud longitud de la contraseña
     * @return contraseña generada
     */
    private static String generarConjunto(String conjunto, int longitud) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            int idx = random.nextInt(conjunto.length());
            sb.append(conjunto.charAt(idx));
        }
        return sb.toString();
    }

    /**
     * Menú simple para probar las funciones del generador de contraseñas.
     * 
     * @param args no utilizados
     */
    public static void main(String[] args) {
        System.out.println("=== Generador de Contraseñas ===");
        System.out.println("Simple: " + generarSimple());
        System.out.println("Segura: " + generarSegura());
        System.out.println("Con reglas: " + generarConReglas(10, true, true, false));
        System.out.println("Fuerza de 'Abc123!': " + validarFuerza("Abc123!"));
    }
}
