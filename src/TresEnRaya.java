import javax.swing.JOptionPane;

public class TresEnRaya{

    static String jugador1;      // El static se usa para que estas variables pertenezcan a la clase completa y no a un objeto específico
    static String jugador2;

    static int marcadorJugador1 = 0;
    static int marcadorJugador2 = 0;

    public static void main(String[] args) {
        int menu = 0;
        boolean salir = false;

        while (!salir) {

            System.out.println("\n======== MENU PRINCIPAL ========");
            System.out.println("1. Iniciar partida");
            System.out.println("2. Ver marcador");
            System.out.println("3. Instrucciones");
            System.out.println("4. Salir");
            
            try {
                
                menu = Integer.parseInt(JOptionPane.showInputDialog("Seleccione una opción:"));

                switch (menu) {
                    case 1:

                        iniciarPartida();
                        break;

                    case 2:

                        System.out.println("\n======== MARCADOR ========");
                        
                        if (jugador1 != null && jugador2 != null) {   // Verifica si jugador 1 y 2 tienen un nombre y sino esta vacio, imprimir nombres y puntajes de cada uno

                            System.out.println(jugador1 + " : " + marcadorJugador1);
                            System.out.println(jugador2 + " : " + marcadorJugador2);

                        } else {
                            System.out.println("Aun no hay nombres o no se ha jugado ninguna partida");
                        }
                        break;

                    case 3:

                        System.out.println("\n======== INSTRUCCIONES ========");    
                        System.out.println("Bienvenido al juego tres en raya, las instrucciones son simples: ");
                        System.out.println("Los jugadores se alternan turnos");
                        System.out.println("Jugador 1 usa X");
                        System.out.println("Jugador 2 usa O");
                        System.out.println("Gana el jugador que complete una fila, columna o diagonal");
                        System.out.println("Nota: si todas las casillas quedan llenas y no hay ganador se declara empate");
                        break;

                    case 4:

                        salir = true;
                        System.out.println("Salió con exito, vuelva pronto!");    
                
                    default:
                        System.out.println("Opcion invalida");
                }
                
            } catch (Exception e) {
                System.out.println("Ingrese un numero valido");
            }
        }
         
    }

    // Funcion principal
    
    public static void iniciarPartida() {    // No necesita recibir nada porque ya la misma funcion pide los nombres y controla todo lo del juego
 
        jugador1 = JOptionPane.showInputDialog("Ingrese el nombre del jugador 1");
        jugador2 = JOptionPane.showInputDialog("Ingrese el nombre del jugador 2");

        char[][] tablero = {

            { ' ' , ' ' , ' ' },
            { ' ' , ' ' , ' ' },
            { ' ' , ' ' , ' ' }

        };
        
        char jugadorActual = 'X';
        boolean ganador = false;

        while (!ganador && !tableroLleno(tablero)) {     // Ciclo principal

            mostrarTablero(tablero);

            int fila = -1;      // Porque las posiciones validas del tablero son 0,1,2 entonces -1 es que no se ha ingresado nada
            int columna = -1;

            boolean movimientoValido = false;

            while (!movimientoValido) {

                try {
                    
                    if (jugadorActual == 'X') {    // Si el jugador actual usa x...

                        System.out.println("\nTurno de " + jugador1);
                        
                    } else {
                        System.out.println("\nTurno de " + jugador2);
                    }

                    fila = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la fila (0-2)"));
                    columna = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la columna (0-2)"));

                    if (validarMovimiento(tablero, fila, columna)) {       // Llama a la funcion para validar el movimiento

                        movimientoValido = true;     // Si es valido el while termina

                    } else {
                        System.out.println("Movimiento invalido");
                    }

                } catch (Exception e) {
                    System.out.println("Debe ingresar numeros validos");
                }

            }
            
            tablero[fila][columna] = jugadorActual;      // Guarda la jugada en la matriz

            if (verificarGanador(tablero, jugadorActual)) {

                mostrarTablero(tablero);     // Si hay ganador muestra el tablero actualizado

                if (jugadorActual == 'X') {     // En todo el if se verifica quien ganó

                    System.out.println("\nGanador: " + jugador1);
                    marcadorJugador1++;

                } else {
                    System.out.println("\nGanador: " + jugador2);
                    marcadorJugador2++;
                }

                ganador = true;    // Finalizar el juego, ya hay ganador, el primer while termina

            } else {
                jugadorActual = cambiarJugador(jugadorActual);     // Si nadie ganó todavia, se cambia el turno
            }
        }

        if (!ganador) {    // Si no hubo ganador 

            mostrarTablero(tablero);
            System.out.println("\nEl juego terminó en empate");
            
        }
    }

    // Funciones "secundarias"

    public static void mostrarTablero(char[][] tablero) {    // Funcion que muestra el tablero actualizado con cada jugada

        System.out.println();

        for (int i = 0; i < 3; i++) {    // Recorre las filas de la matriz

            for (int j = 0; j < 3; j++) {    // Recorre las columnas

                System.out.print(" " + tablero[i][j] + " ");    // Muestra lo que tien cada casilla  EJ: si tablero[0][1] = 'x', imprime x

                if (j < 2) {     // si la columna es la ultima hacer |, que separa jugadas
                    System.out.print("|");
                }
            }

            System.out.println();

            if (i < 2) {     // Separa jugadas
                System.out.println("-----------");
            }
        }
    }

    public static boolean validarMovimiento(char[][] tablero, int fila, int columna) {    // Funcion para validar si fila y columna estan entre 0 y 2, y si ya esta llena la casilla

        if (fila < 0 || fila > 2 || columna < 0 || columna > 2) {

            return false;       // Devuelve movimiento invalido
        }

        if (tablero[fila][columna] != ' ') {      // Verifica si la casilla esta llena

            return false;  
        }
        return true;      // Si pasa las anteriores dos validaciones entonces movimiento valido
    }

    public static boolean tableroLleno(char[][] tablero) {     // Funcion que verifica si el tablero esta lleno

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (tablero[i][j] == ' ') {  

                    return false;     // Si hay casilla vacia entonces el tablero no esta lleno, devuelve false
                }
            }
        }
        return true;     // Y si no hay espacios vacios el tablero esta lleno, devuelve true
    }

}