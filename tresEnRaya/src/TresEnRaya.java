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

                switch (menu){
                    case 1:
                        iniciarPartida();
                        break;
                    case 2:
                        System.out.println("\n=====MARCADOR=====");
                    
                            if(jugador1 !=null && jugador2 != null){
                                System.out.println(jugador1 + ": " + marcadorJugador1);
                                System.out.println(jugador2 + ": " + marcadorJugador2);
                            } else{
                                System.out.println("Aun no se ha jugado ninguna partida");   
                            }
                        break;
                case 3: System.out.println("\n=====INSTRUCCIONES=====");
                        System.out.println("El juego es Tres en Raya");
                        System.out.println("Jugador 1 usa X");
                        System.out.println("Jugador 2 usa O");
                        System.out.println("Gana quien complete una fila, columna o diagonal con sus símbolos");
                        System.out.println("Debe ingresar fila y columnna entre 0 y 2");
                        break;
                    case 4:
                        salir = true;
                        System.out.println("Programa finalizado. ¡Gracias por jugar!");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
                
            } catch (Exception e) {
                System.out.println("Debe ingresar un número válido");
            }
        }  
    }

public static void iniciarPartida(){
    
    jugador1 = JOptionPane.showInputDialog("Ingrese el nombre del jugador 1");
    jugador2 = JOptionPane.showInputDialog("Ingrese el nombre del jugador 2");

    char[][] tablero = {
        {' ',' ',' '},
        {' ',' ',' '},
        {' ',' ',' '}
    };

    char jugadorActual = 'X';
    boolean ganador = false;

    while(!ganador && !tableroLleno(tablero)){

        mostrarTablero(tablero);

        int fila = -1;
        int columna = -1;

        boolean movimientoValido = false;

        while(!movimientoValido){
            try{
                if(jugadorActual == 'X'){
                    System.out.println("\nTurno de " + jugador1);
                } else{
                    System.out.println("\nTurno de " + jugador2);
                }

                fila = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la fila (0-2):"));
                columna = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la columna (0-2):"));

                if(validarMovmiento(tablero, fila, columna)){
                    movimientoValido = true;
                }else{
                    System.out.println("Movimiento Invalido");
                }
            } catch (Exception e){
                System.out.println("Debe ingresar números válidos");
            }
        }

        tablero[fila][columna] = jugadorActual;

        if (verificarGanador(tablero, jugadorActual)){
            mostrarTablero(tablero);
            if(jugadorActual == 'X'){
                System.out.println("\nGanador: " + jugador1);
                marcadorJugador1++;
            } else{
                System.out.println("\nGanador: " + jugador2);
                marcadorJugador2++;
            }
            ganador = true;
        }else{
            jugadorActual = cambiarJugador(jugadorActual);
        }
    }
}
}