package Model;

import javax.swing.*;
import java.util.*;

import static Model.Operateur.*;

/**
 * Instance principale du jeu
 * Created by Theo on 16/11/2016.
 */
public class LogicTreeGame implements Observer{
    private Random randomGenerator = new Random();
    private ArrayList<FormuleBase> formules = new ArrayList<>(); // liste des formules du sujet
    private Formule formule; // la formule sélectionnée
    long startTime = System.currentTimeMillis();
    private static String rules = "Ecrire ici les règles du jeu... Mais plus tard. A la fin du développement d'une branche, cliquer sur une proposition qui rend la clause invalide fermera la branche";

    private void initQuestions(){
        Proposition a = new Proposition('A');
        Proposition b = new Proposition('B');
        Proposition c = new Proposition('C');
        Proposition p = new Proposition('P');
        Proposition q = new Proposition('Q');
        Proposition r = new Proposition('R');
        Proposition s = new Proposition('S');
        Proposition t = new Proposition('T');
        Proposition u = new Proposition('U');
        Proposition v = new Proposition('V');
        Proposition w = new Proposition('W');


        SousFormuleSimple notA = new SousFormuleSimple(Operateur.NOT, a);
        SousFormuleSimple notB = new SousFormuleSimple(Operateur.NOT, b);
        SousFormuleSimple notC = new SousFormuleSimple(Operateur.NOT, c);
        SousFormuleSimple notP = new SousFormuleSimple(Operateur.NOT, p);
        SousFormuleSimple notQ = new SousFormuleSimple(Operateur.NOT, q);
        SousFormuleSimple notR = new SousFormuleSimple(Operateur.NOT, r);
        SousFormuleSimple notS = new SousFormuleSimple(Operateur.NOT, s);
        SousFormuleSimple notT = new SousFormuleSimple(Operateur.NOT, t);
        SousFormuleSimple notU = new SousFormuleSimple(Operateur.NOT, u);
        SousFormuleSimple notV = new SousFormuleSimple(Operateur.NOT, v);
        SousFormuleSimple notW = new SousFormuleSimple(Operateur.NOT, w);


        //1
        FormuleBase pImpQ = new SousFormuleBipartie(p, IMPLIES, q);
        FormuleBase pImpQImpQ = new SousFormuleBipartie(pImpQ, IMPLIES, q);
        FormuleBase pImpPImpQImpQ = new SousFormuleBipartie(p, IMPLIES, pImpQImpQ);
        System.out.println("Formule un : " + pImpPImpQImpQ);


        //2
        FormuleBase notPImpQ = new SousFormuleBipartie(notP, IMPLIES, q);
        FormuleBase notQImpNotPImpQ = new SousFormuleBipartie(notQ, IMPLIES, notPImpQ);
        FormuleBase pImpNotQImpNotPImpQ = new SousFormuleBipartie(p, IMPLIES, notQImpNotPImpQ);

        //3
        SousFormuleBipartie qImpP = new SousFormuleBipartie(q, IMPLIES, p);
        FormuleBase notPImpNotQ = new SousFormuleBipartie(notP, IMPLIES, notQ);
        FormuleBase notPImpNotQImpQImpP = new SousFormuleBipartie(notPImpNotQ, IMPLIES, qImpP);

        //4
        FormuleBase pImpQImpR = new SousFormuleBipartie(pImpQ, IMPLIES, r);
        FormuleBase pAndPImpQ = new SousFormuleBipartie(p, AND, pImpQ);
        FormuleBase pAndPImpQAndpImpQImpR = new SousFormuleBipartie(pAndPImpQ, AND, pImpQImpR);
        FormuleBase pAndQ = new SousFormuleBipartie(p, AND, q);
        FormuleBase pAndQAndR = new SousFormuleBipartie(pAndQ, AND, r);
        FormuleBase pAndPImpQAndpImpQImpRImpPAndQAndR = new SousFormuleBipartie(pAndPImpQAndpImpQImpR, IMPLIES, pAndQAndR);

        //5
        SousFormuleBipartie qImpR = new SousFormuleBipartie(q, IMPLIES, r);
        SousFormuleBipartie pImpR = new SousFormuleBipartie(p, IMPLIES, r);
        FormuleBase qImpRImpPImpR = new SousFormuleBipartie(qImpR, IMPLIES, pImpR);
        FormuleBase pImpQImpQImpRImpPImpR = new SousFormuleBipartie(pImpQ, IMPLIES, qImpRImpPImpR);

        //6
        SousFormuleBipartie sAndP = new SousFormuleBipartie(s, AND, p);
        SousFormuleBipartie qAndR = new SousFormuleBipartie(q, AND, r);
        SousFormuleBipartie sAndPImpQAndR = new SousFormuleBipartie(sAndP, IMPLIES, qAndR);
        SousFormuleBipartie notROrNotQ = new SousFormuleBipartie(notR, OR, notQ);
        SousFormuleBipartie sAndPImpQAndRAndnotROrNotQ = new SousFormuleBipartie(sAndPImpQAndR, AND, notROrNotQ);
        SousFormuleBipartie sAndPImpQAndRAndnotROrNotQAndP = new SousFormuleBipartie(sAndPImpQAndRAndnotROrNotQ, AND, p);
        SousFormuleBipartie sAndPImpQAndRAndnotROrNotQAndPImpS = new SousFormuleBipartie(sAndPImpQAndRAndnotROrNotQAndP, IMPLIES, notS);

        //7
        SousFormuleBipartie rAndS = new SousFormuleBipartie(r, AND, s);
        SousFormuleBipartie rAndSImpP = new SousFormuleBipartie(rAndS, IMPLIES, p);
        SousFormuleBipartie tImpR = new SousFormuleBipartie(t, IMPLIES, r);
        SousFormuleBipartie sAndT = new SousFormuleBipartie(s, AND, t);
        SousFormuleBipartie rAndSImpPAndTImpR = new SousFormuleBipartie(rAndSImpP, AND, tImpR);
        SousFormuleBipartie rAndSImpPAndTImpRAndSAndT = new SousFormuleBipartie(rAndSImpPAndTImpR, AND, sAndT);
        SousFormuleBipartie pImpQAndRAndSImpPAndTImpRAndSAndT = new SousFormuleBipartie(pAndQ, AND, rAndSImpPAndTImpRAndSAndT);
        SousFormuleBipartie pImpQAndRAndSImpPAndTImpRAndSAndTImpQ = new SousFormuleBipartie(pImpQAndRAndSImpPAndTImpRAndSAndT, IMPLIES, q);

        //8
        SousFormuleBipartie pImpImpQImpPImpR = new SousFormuleBipartie(pImpQ, IMPLIES, pImpR);
        //SousFormuleBipartie pImpQImpR = new SousFormuleBipartie(p, IMPLIES, qImpR); //deja déclaré ? Todo : Verifier exxactitude
        SousFormuleBipartie pImpQImpRImpPImpImpQImpPImpR = new SousFormuleBipartie(pImpQImpR, IMPLIES, pImpImpQImpPImpR);

        //9
        SousFormuleBipartie notQImpP = new SousFormuleBipartie(notQ, IMPLIES, p);
        SousFormuleBipartie notQImpPImpP = new SousFormuleBipartie(notQImpP, IMPLIES, p);
        SousFormuleBipartie qImpPImpNotQImpPImpP = new SousFormuleBipartie(qImpP, IMPLIES, notQImpPImpP);

        //10
        SousFormuleBipartie pImpROrQImpR = new SousFormuleBipartie(pImpR, OR, qImpR);
        //SousFormuleBipartie pImpQImpR = new SousFormuleBipartie(p, IMPLIES, qImpR); //TODO : check if bonne declaration
        SousFormuleBipartie pImpQImpRImpPImpROrQImpR = new SousFormuleBipartie(pImpQImpR, IMPLIES, pImpROrQImpR);

        //11
        pImpQImpR = new SousFormuleBipartie(p, IMPLIES, qImpR);
        SousFormuleBipartie qImpRImpPImpQImpR = new SousFormuleBipartie(qImpR, IMPLIES, pImpQImpR);
        SousFormuleBipartie pImpROrQImpRImpPImpQImpR = new SousFormuleBipartie(pImpR, OR, qImpRImpPImpQImpR);

        //12
        SousFormuleBipartie pImpQAndQImpP = new SousFormuleBipartie(pImpQ, AND, qImpP);
        SousFormuleBipartie pAndNotQ = new SousFormuleBipartie(p, AND, notQ);
        SousFormuleBipartie notPAndQ = new SousFormuleBipartie(notP, AND, q);
        SousFormuleBipartie pImpQAndQImpPOrpAndNotQ = new SousFormuleBipartie(pImpQAndQImpP, OR, pAndNotQ);
        SousFormuleBipartie pImpQAndQImpPOrpAndNotQOrNotPAndQ = new SousFormuleBipartie(pImpQAndQImpPOrpAndNotQ, OR, notPAndQ);

        //13
        SousFormuleBipartie aAndB = new SousFormuleBipartie(a, AND, b);
        SousFormuleSimple notAAndB = new SousFormuleSimple(NOT, aAndB);
        SousFormuleBipartie notAOrNotB = new SousFormuleBipartie(notA, OR, notB);
        SousFormuleBipartie notAAndBImpnotAOrNotB = new SousFormuleBipartie(notAAndB, IMPLIES, notAOrNotB);

        //14
        SousFormuleBipartie notAOrNotBImpNotAAndB = new SousFormuleBipartie(notAOrNotB, IMPLIES, notAAndB);

        //15
        SousFormuleBipartie aOrB = new SousFormuleBipartie(a, OR, b);
        SousFormuleBipartie aOrNotB = new SousFormuleBipartie(a, OR, notB);
        SousFormuleBipartie aOrBOrC = new SousFormuleBipartie(aOrB, OR, c);
        SousFormuleBipartie aOrBOrNotC = new SousFormuleBipartie(aOrB, OR, notC);
        SousFormuleBipartie aOrBOrNotCAndaOrBOrC = new SousFormuleBipartie(aOrBOrNotC, AND, aOrBOrC);
        SousFormuleBipartie aOrBOrNotCAndaOrBOrCAndAOrNotB = new SousFormuleBipartie(aOrBOrNotCAndaOrBOrC, AND, aOrNotB);
        SousFormuleBipartie aOrBOrNotCAndaOrBOrCAndAOrNotBImpA = new SousFormuleBipartie(aOrBOrNotCAndaOrBOrCAndAOrNotB, IMPLIES, a);

        //16
        SousFormuleBipartie wImpV = new SousFormuleBipartie(w, IMPLIES, v);
        SousFormuleBipartie tImpV = new SousFormuleBipartie(t, IMPLIES, v);
        SousFormuleBipartie wOrT = new SousFormuleBipartie(w, OR, t);
        SousFormuleBipartie uImpWOrT = new SousFormuleBipartie(u, IMPLIES, wOrT);
        SousFormuleBipartie uAndWImpv = new SousFormuleBipartie(u, AND, wImpV);
        SousFormuleBipartie uAndWImpvAndTImpV = new SousFormuleBipartie(uAndWImpv, AND, tImpV);
        SousFormuleBipartie uAndWImpvAndTImpVAnduImpWOrT = new SousFormuleBipartie(uAndWImpvAndTImpV, AND, uImpWOrT);
        SousFormuleBipartie uAndWImpvAndTImpVAnduImpWOrTImpV = new SousFormuleBipartie(uAndWImpvAndTImpVAnduImpWOrT, IMPLIES, v);

        //17
        SousFormuleBipartie tImpNotR = new SousFormuleBipartie(t, IMPLIES, notR);
        pAndPImpQ = new SousFormuleBipartie(p, AND, pImpQ);
        pImpQImpR = new SousFormuleBipartie(pImpQ, IMPLIES, r);
        SousFormuleBipartie pAndPImpQAndPImpQImpR = new SousFormuleBipartie(pAndPImpQ, AND, pImpQImpR);
        SousFormuleBipartie pAndPImpQAndPImpQImpRImpPAndQAndR = new SousFormuleBipartie(pAndPImpQAndPImpQImpR, IMPLIES, pAndQAndR);
        SousFormuleBipartie pAndPImpQAndPImpQImpRImpPAndQAndRAndTImpNotR = new SousFormuleBipartie(pAndPImpQAndPImpQImpRImpPAndQAndR, AND, tImpNotR);
        SousFormuleBipartie rOrPAndPImpQAndPImpQImpRImpPAndQAndRAndTImpNotR = new SousFormuleBipartie(r, OR, pAndPImpQAndPImpQImpRImpPAndQAndRAndTImpNotR);

        //18
        SousFormuleBipartie qImpNotP = new SousFormuleBipartie(q, IMPLIES, notP);
        SousFormuleBipartie pOrQImpNotP = new SousFormuleBipartie(p, OR, qImpNotP);
        pAndPImpQAndPImpQImpR = new SousFormuleBipartie(pAndPImpQ, AND, pImpQImpR);
        pAndPImpQAndPImpQImpRImpPAndQAndR = new SousFormuleBipartie(pAndPImpQAndPImpQImpR, IMPLIES, pAndQAndR);
        SousFormuleBipartie pOrQImpNotPOrPAndPImpQAndPImpQImpRImpPAndQAndR = new SousFormuleBipartie(pOrQImpNotP, OR, pAndPImpQAndPImpQImpRImpPAndQAndR);

        //19
        SousFormuleBipartie notQImpNotP = new SousFormuleBipartie(notQ, IMPLIES, notP);
        SousFormuleSimple notNotQImpNotP = new SousFormuleSimple(NOT, notQImpNotP);
        SousFormuleBipartie notNotQImpNotPOrNotQ = new SousFormuleBipartie(notNotQImpNotP, OR, notQ);
        SousFormuleBipartie notNotQImpNotPOrNotQOrQ= new SousFormuleBipartie(notNotQImpNotPOrNotQ, OR, q);
        pImpQImpR = new SousFormuleBipartie(p, IMPLIES, qImpR);
        SousFormuleBipartie qImpROrQImpR = new SousFormuleBipartie(qImpR, OR, qImpR);
        SousFormuleBipartie pImpQImpRImpQImpROrQImpR = new SousFormuleBipartie(pImpQImpR, IMPLIES, qImpROrQImpR);
        SousFormuleBipartie pImpQImpRImpQImpROrQImpRImpNotNotQImpNotPOrNotQOrQ = new SousFormuleBipartie(pImpQImpRImpQImpROrQImpR, IMPLIES, notNotQImpNotPOrNotQOrQ);

        //20
        SousFormuleBipartie sImpNotT = new SousFormuleBipartie(s, IMPLIES, notT);
        SousFormuleBipartie tAndSImpNotT = new SousFormuleBipartie(t, AND, sImpNotT );
        SousFormuleBipartie sAndPImpQAndRAndNotROrNotQ = new SousFormuleBipartie(sAndPImpQAndR, AND, notROrNotQ);
        SousFormuleBipartie sAndPImpQAndRAndNotROrNotQAndP = new SousFormuleBipartie(sAndPImpQAndRAndNotROrNotQ, AND, p);
        SousFormuleBipartie sAndPImpQAndRAndNotROrNotQAndPAndTAndSImpNotT = new SousFormuleBipartie(sAndPImpQAndRAndNotROrNotQAndP, AND, tAndSImpNotT);
        SousFormuleBipartie sAndPImpQAndRAndNotROrNotQAndPAndTAndSImpNotTImpNotS = new SousFormuleBipartie(sAndPImpQAndRAndNotROrNotQAndPAndTAndSImpNotT, IMPLIES, notS);



        FormuleBase un = pImpPImpQImpQ;
        FormuleBase deux = pImpNotQImpNotPImpQ;
        FormuleBase trois = notPImpNotQImpQImpP;
        FormuleBase quatre = pAndPImpQAndpImpQImpRImpPAndQAndR;
        FormuleBase cinq = pImpQImpQImpRImpPImpR;
        FormuleBase six = sAndPImpQAndRAndnotROrNotQAndPImpS;
        FormuleBase sept = pImpQAndRAndSImpPAndTImpRAndSAndTImpQ;
        FormuleBase huit = pImpQImpRImpPImpImpQImpPImpR;
        FormuleBase neuf = qImpPImpNotQImpPImpP;
        FormuleBase dix = pImpQImpRImpPImpROrQImpR;
        FormuleBase onze = pImpROrQImpRImpPImpQImpR;
        FormuleBase douze = pImpQAndQImpPOrpAndNotQOrNotPAndQ;
        FormuleBase treize = notAAndBImpnotAOrNotB;
        FormuleBase quatorze = notAOrNotBImpNotAAndB;
        FormuleBase quinze = aOrBOrNotCAndaOrBOrCAndAOrNotBImpA;
        FormuleBase seize = uAndWImpvAndTImpVAnduImpWOrTImpV;
        FormuleBase dixsept = rOrPAndPImpQAndPImpQImpRImpPAndQAndRAndTImpNotR;
        FormuleBase dixhuit = pOrQImpNotPOrPAndPImpQAndPImpQImpRImpPAndQAndR;
        FormuleBase dixneuf = pImpQImpRImpQImpROrQImpRImpNotNotQImpNotPOrNotQOrQ;
        FormuleBase vingt = sAndPImpQAndRAndNotROrNotQAndPAndTAndSImpNotTImpNotS;



        formules.add(un);
        formules.add(deux);
        formules.add(trois);
        formules.add(quatre);
        formules.add(cinq);
        formules.add(six);
        formules.add(sept);
        formules.add(huit);
        formules.add(neuf);
        formules.add(dix);
        formules.add(onze);
        formules.add(douze);
        formules.add(treize);
        formules.add(quatorze);
        formules.add(quinze);
        formules.add(seize);
        formules.add(dixsept);
        formules.add(dixhuit);
        formules.add(dixneuf);
        formules.add(vingt);

    }

    public LogicTreeGame(){
        initQuestions();
        chooseRandomFormula();
    }

    /**
     * @return une formule aléatoire parmi celles disponibles
     * On renvoie le contraire de cette formule afin d'en développer l'arbre
     */
    public Formule chooseRandomFormula(){

        int index = randomGenerator.nextInt(formules.size());
        System.out.println("Formule random : " + formules.get(index));
        formule = new Formule(new SousFormuleSimple(
                NOT, formules.get(index))
        );
        return formule;
    }

    public Formule getFormule() {
        return formule;
    }

    public static String getRules() {
        return rules;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Formule && ((Formule) o).isInvalid()){

            long elapsedTime = ((new Date()).getTime() - startTime)/1000;
            JOptionPane msg = new JOptionPane();
            msg.showMessageDialog(null,
                    "Bravo ! Toutes les branches ont été fermées : la formule est donc valide !\n" +
                    "La partie a duré " + elapsedTime + " secondes\n" +
                    "Il a fallu " + Formule.nbCoups() + " coups pour trouver la solution",
                    "Bravo",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
