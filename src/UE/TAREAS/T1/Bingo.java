package UE.TAREAS.T1;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Bingo {
    // Global variables
    static Scanner scanner = new Scanner(System.in);
    static boolean lineaCalled = false;
    static boolean bingo = false;

    public static void main(String[] args) {
        // Carton + numeros del jugador
        Set<Integer> numbersFromSystem = new HashSet<>();
        Set<Integer> numbersFromUser = new HashSet<>();

        System.out.print("Por favor introduce 10 números en un rango de 1 <= 99: ");

        /* region - Numeros del usaurio y validaciones */
        while(numbersFromUser.size() < 10){
            playerNumsAndValidations(scanner, numbersFromUser);
        }
        /* endregion */

        /*region - Apuestas del usuario y números del sistema */
        System.out.print("¿En cuál bola crees que cantarás Bingo?: ");
        int bolasPrima = scanner.nextInt();

        int bolas = 0;
        int aciertos = 0;

        // El sistema generará números mientras no se cante bingo y la cantidad de bolas sea <= 99
        while(bolas < 100 && !bingo){
            int randomNumber = (int)(Math.random() * 99) + 1;
            // Añadir número si no está duplicado
            if(numbersFromSystem.add(randomNumber)){
                bolas++;
                if(numbersFromUser.contains(randomNumber)){
                    aciertos++;
                    System.out.printf("Número acertado: %d (Total aciertos: %d\n", randomNumber, aciertos);
                    // Checks
                    lineaAndBingoChecks(aciertos, bolas, numbersFromUser);
                }
            }
        }
        /*endregion*/

        // Chequeo de la apuesta del usuario
        final boolean premio = wonPrima(bolas, bolasPrima);
        if(bingo){
            if(premio){
                System.out.printf("Felicidades! Has acertado también la prima. Premio: %d \n", bolasPrima * 10);
            }
        }else{
            System.out.println("Lo siento! No se ha cantado bingo, no has tenido suerte esta vez.");
        }
        scanner.close();
    }
    // Numeros del jugador y validaciones
    public static void playerNumsAndValidations(Scanner scanner, Set<Integer>numbersFromUser){
        int userNumber = scanner.nextInt();
        if(userNumber < 1 || userNumber > 99){
            System.out.printf("El número %d está fuera de rango\n", userNumber);
        }else if(!numbersFromUser.add(userNumber)){
            System.out.printf("El número %d está duplicado, introduce otro por favor: \n", userNumber);
        }else{
            System.out.printf("El número %d se ha añadido correctamente\n", userNumber);
        }
    }
    // Linea && Bingo checks
    public static void lineaAndBingoChecks(int aciertos, int bolas, Set<Integer> numbersFromUser ){
        // Línea
        if(aciertos == 5 && !lineaCalled){
            System.out.printf("Línea a la bola %d!\n", bolas);
            lineaCalled = true;
        }

        if(aciertos == numbersFromUser.size()){
            System.out.printf("BINGO! a la bola %d\n", bolas);
            bingo = true;
        }
    }
    // Prima check
    public static boolean wonPrima(int bolas, int bolasPrima){
        boolean isTherePrima = false;
        if(bingo){
            if(bolas == bolasPrima) {
                isTherePrima = true;
            }
        }
        return isTherePrima;
    };
}
