
package controlador;

import java.awt.BorderLayout;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.Impresiones;
import vistas.Principal;
import vistas.RegistroParticipantes;

/**
 *
 * @author hugol
 */

public class PrincipalController implements ActionListener {
    
    private Principal principal;
    
    public void iniciarVista() {
        this.principal.setVisible(true);
        this.principal.setTitle("Conferencias TICs");
        this.principal.setExtendedState(MAXIMIZED_BOTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.principal.getBtnImpresiones() == e.getSource()) {
            Impresiones imp = new Impresiones();
            new ImpresionesController(imp).iniciarVista();
            this.principal.getpDesktop().removeAll();
            this.principal.getpDesktop().add(imp, BorderLayout.CENTER);
            this.principal.getpDesktop().repaint();
            this.principal.pack();
        } else {
            RegistroParticipantes regp = new RegistroParticipantes();
            new RegistroParticipantesController(regp).iniciarVista();
            this.principal.getpDesktop().removeAll();
            this.principal.getpDesktop().add(regp, BorderLayout.CENTER);
            this.principal.getpDesktop().repaint();
            this.principal.pack();
        }
        this.principal.setExtendedState(MAXIMIZED_BOTH);
    }

    public PrincipalController(Principal principal) {
        this.principal = principal;
        
        this.principal.getBtnImpresiones().addActionListener(this);
        this.principal.getBtnRegistro().addActionListener(this);
    }

}