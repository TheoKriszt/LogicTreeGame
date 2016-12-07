package View;

import Model.LogicTreeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Theo on 07/12/2016.
 */
public class GameFrame extends JFrame {
    private LogicTreeGame ltg;
    private JPanel content = new JPanel(new BorderLayout(5, 5));
    private JScrollPane scrollPane = new JScrollPane(content,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JButton randomButton = new JButton("Changer la formule");

    public GameFrame(LogicTreeGame l){

        super("LogicTreeGame");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ltg = l;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
        setPreferredSize(new Dimension(1000, 700));
        setMinimumSize(new Dimension(400, 200));

        setContentPane(scrollPane);
        //scrollPane.add(content);
        content.add(randomButton, BorderLayout.NORTH);
        content.add( new FormuleView(l.getFormule()), BorderLayout.CENTER );


        addListeners();

        revalidate();
        setVisible(true);

    }

    private void addListeners() {
        ltg.getFormule().addObserver(ltg);
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ltg.getFormule().deleteObservers();
                ltg.chooseRandomFormula();
                ltg.getFormule().addObserver(ltg);
                new FormuleView(ltg.getFormule());
                content.removeAll();
                content.add(randomButton, BorderLayout.NORTH);
                content.add(ltg.getFormule().getView(), BorderLayout.CENTER);
                content.revalidate();
            }
        });

    }

}
