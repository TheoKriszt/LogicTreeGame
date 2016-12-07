package View;

import Model.Formule;
import Model.FormuleBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Theo on 07/12/2016.
 */
public class FormuleView extends JPanel {
    private Formule formule;
    private JPanel topPanel = new JPanel(new BorderLayout(5, 0));

    public FormuleView(Formule f){
        setLayout(new BorderLayout(5, 0));
        System.out.println("Nouvelle vue de \n" + f.toString());
        setBorder(BorderFactory.createLineBorder(Color.black));
        formule = f;
        f.setView(this);
        add(topPanel, BorderLayout.NORTH);

        JButton b;
        JPanel p = topPanel;

        for (FormuleBase compo : formule.getComposants()){
            b = new JButton(compo.toString());
            p.add(b, BorderLayout.NORTH);
            JPanel temp = new JPanel(new BorderLayout(5, 0));
            p.add(temp, BorderLayout.CENTER);
            p = temp;
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    formule.addChildren(compo, compo.developper());
                }
            });
        }
        revalidate();
    }

    public void showInvalid(){
        setBackground(Color.red);
    }


}
