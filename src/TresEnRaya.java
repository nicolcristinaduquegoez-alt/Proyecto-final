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

        while (!ganador) {

            int fila = -1;  // Porque las posiciones validas del tablero son 0,1,2 entonces -1 es que no se ha ingresado nada
            int columna = -1;

            boolean jugadaValida = false;

            while (!jugadaValida) {

                try {
                    
                    if (jugadorActual == 'X') {

                        System.out.println("\nTurno de " + jugador1);
                        
                    } else {

                        System.out.println("\nTurno de " + jugador2);
                    }

                    fila = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la fila (0-2)"));
                    columna = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la columna (0-2)"));


                } catch (Exception e) {
                    System.out.println("Debe ingresar numeros validos");
                }
                
            }



            
        }
    }
}