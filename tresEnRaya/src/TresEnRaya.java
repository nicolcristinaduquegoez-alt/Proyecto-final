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
                
                String input = JOptionPane.showInputDialog("Seleccione una opción:");
                if(input == null){
                    System.out.println();
                    System.out.println("Salió con exito, vuelva pronto!");
                    break;   // Si el usuario cancela el input, sale del programa.
                }

                menu = Integer.parseInt(input);

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
                            System.out.println();
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
                        System.out.println("\n=====COMO COLOCAR TU JUGADA=====");
                        System.out.println("El tablero tiene posiciones de 0 al 2 distribuidas en filas y columnas");
                        System.out.println();
                        System.out.println("       Col 0  Col 1  Col 2");
                        System.out.println("Fila 0 [0,0]  [0,1]  [0,2]");
                        System.out.println("Fila 1 [1,0]  [1,1]  [1,2]");
                        System.out.println("Fila 2 [2,0]  [2,1]  [2,2]");
                        System.out.println();
                        System.out.println("Ejemplo: para el centro ingresa fila=1, columna=1");
                        System.out.println("         para la esquina superior izquierda fila=0, columna=0");
                        System.out.println();
                        System.out.println("NOTA: si todas las casillas quedan llenas y no hay ganador se declara EMPATE");
                        break;

                    case 4:

                        salir = true;
                        System.out.println();
                        System.out.println("Salió con exito, vuelva pronto!");    
                        break;
                
                    default:
                        System.out.println();
                        System.out.println("Opcion invalida");
                }
                
            } catch (Exception e) {
                System.out.println();
                System.out.println("Ingrese un numero valido");
            }
        }
         
    }

    // Funcion principal
    
    public static void iniciarPartida() {    // No necesita recibir nada porque ya la misma funcion pide los nombres y controla todo lo del juego
 
        do {
            jugador1 = JOptionPane.showInputDialog("Ingrese el nombre del jugador 1");
            if(jugador1 == null){
                System.out.println();
                System.out.println("Partida cancelada.");
            return;   // Si el usuario cancela el input, sale de la función iniciarPartida y vuelve al menú de inicio.
            }
            if (jugador1.trim().isEmpty()) {
                System.out.println();
                System.out.println("El nombre no puede estar vacío, intente de nuevo.");
            }
        } while (jugador1.trim().isEmpty());  // El ciclo se repite hasta que el usuario ingrese un nombre no vacío para el jugador 1

        do{
            jugador2 = JOptionPane.showInputDialog("Ingrese el nombre del jugador 2");
            if(jugador2 == null){
                System.out.println();
                System.out.println("Partida cancelada.");
            return;   // Si el usuario cancela el input, sale de la función iniciarPartida y vuelve al menú de inicio.
            }
            if (jugador2.trim().isEmpty()) {
                System.out.println();
                System.out.println("El nombre no puede estar vacío, intente de nuevo.");
            }
        } while (jugador2.trim().isEmpty());

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
                    String nombreActual = (jugadorActual == 'X') ? jugador1 : jugador2;    // Ternario para mostrar el nombre del jugador actual
                    
                    String inputFila;
                    do{ 
                        inputFila = JOptionPane.showInputDialog("Turno de "+ nombreActual + "\nIngrese la fila (0-2):");
                        if(inputFila == null ){
                            System.out.println();
                            System.out.println("Partida cancelada");
                            return;   // Si el usuario cancela el input, sale de la función iniciarPartida y vuelve al menú de inicio.
                        }
                        try{
                            fila = Integer.parseInt(inputFila);
                            if (fila < 0 || fila > 2) {
                                System.out.println();
                                System.out.println("Error: La fila debe ser un numero entre 0 y 2.");
                                fila = -1; // Reinicia fila para que el ciclo continue
                            }
                        } catch (NumberFormatException e) {
                            System.out.println();
                            System.out.println("Error: '" + inputFila + "' no es un numero valido, intente de nuevo.");
                            fila = -1; // Reinicia fila para que el ciclo continue
                        }
                    }while (fila == -1); // El ciclo se repite hasta que el usuario ingrese un numero valido para la fila

                    String inputColumna;
                    do{
                        inputColumna = JOptionPane.showInputDialog("Turno de "+ nombreActual + "\nIngrese la columna (0-2):");
                        if(inputColumna == null){
                            System.out.println();
                            System.out.println("Partida cancelada");
                            return;   // Si el usuario cancela el input, sale de la función iniciarPartida y vuelve al menú de inicio.
                        }
                        try{
                            columna = Integer.parseInt(inputColumna);
                            if (columna < 0 || columna > 2) {
                                System.out.println();
                                System.out.println("Error: La columna debe ser un numero entre 0 y 2.");
                                columna = -1; // Reinicia columna para que el ciclo continue
                            }
                        } catch (NumberFormatException e) {
                            System.out.println();
                            System.out.println("Error: '" + inputColumna + "' no es un numero valido, intente de nuevo.");
                            columna = -1; // Reinicia columna para que el ciclo continue
                        }
                    }while (columna == -1); // El ciclo se repite hasta que el usuario ingrese un numero valido para la columna

                    if (validarMovimiento(tablero, fila, columna)) {       // Llama a la funcion para validar el movimiento
                        movimientoValido = true;     // Si es valido el while termina
                    } else {
                        System.out.println("Movimiento invalido");
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

    public static char cambiarJugador (char jugadorActual){

        if (jugadorActual == 'X') {
            return 'O';    // Si el jugador actual es x, el siguiente turno es o
        } else {
            return 'X';    // Si el jugador actual es o, el siguiente turno es x
        }
    }

    public static boolean verificarGanador(char[][] tablero, char jugadorActual){

        //Verificar filas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == jugadorActual && tablero[i][1] == jugadorActual && tablero[i][2] == jugadorActual) {
                return true;    // Si hay una fila completa del mismo simbolo, devuelve true, hay ganador
            }  
        }
        
        //Verificar columnas
        for (int j = 0; j < 3; j++) {
            if (tablero[0][j] == jugadorActual && tablero[1][j] == jugadorActual && tablero[2][j] == jugadorActual) {
                return true;    // Si hay una columna completa del mismo simbolo, devuelve true, hay ganador
            }  
        }

        //Verificar diagonal principal
        if (tablero[0][0] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][2] == jugadorActual) {
            return true;    // Si hay una diagonal completa del mismo simbolo, devuelve true, hay ganador
        }

        //Verificar diagonal inversa
        if (tablero[0][2] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][0] == jugadorActual) {
            return true;    // Si hay una diagonal completa del mismo simbolo, devuelve true, hay ganador
        }
        return false;   // Si no hay ganador, devuelve false
    }

}