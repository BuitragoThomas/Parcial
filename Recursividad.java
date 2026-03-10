import java.util.Scanner;

public class Recursividad {
    public static String invertirCadena(String texto) {
		if (texto == null || texto.length() <= 1) {
            return texto;
        }
         return invertirCadena(texto.substring(1)) + texto.charAt(0);
	}

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        System.out.print("Ingrese la cadena de texto que desea invertir: ");
        String original = s.nextLine();

        String invertida = invertirCadena(original);
        System.out.println("Original: " + original);
        System.out.println("Invertida: " + invertida);

        s.close();
    }
}