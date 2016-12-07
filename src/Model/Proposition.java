package Model;

import java.util.ArrayList;

/**
 * Proposition (par abus), en réalité "littéral positif"
 * ex. A, P, Q, etc.
 * contre-ex. NOT(A) qui est un littéral négatif, fait appel à l'Operateur NOT
 */
public class Proposition extends FormuleBase {

    private char prop;

    public Proposition(char c) {
        prop = c;
    }


    public String toString(){
        return "" + prop;
    }

    /**
     * @return une proposition fausse
     */
    public static Proposition contradiction(){
        Proposition fausse = new Proposition('⊥');
        return fausse;
    }


    @Override
    public ArrayList<Formule> developper() {
        composite.tryClose(this);
        return null;
    }

    @Override
    public FormuleBase copy() {
        return new Proposition(prop);
    }
}
