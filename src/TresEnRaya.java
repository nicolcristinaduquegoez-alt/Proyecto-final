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
                
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
         
    }
}