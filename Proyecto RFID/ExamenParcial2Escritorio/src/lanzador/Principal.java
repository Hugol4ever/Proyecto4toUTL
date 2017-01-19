
package lanzador;

import controlador.PrincipalController;

/**
 *
 * @author hugol
 */

public class Principal {

    public static void main(String[] args) {
        new PrincipalController(new vistas.Principal()).iniciarVista();
    }

}