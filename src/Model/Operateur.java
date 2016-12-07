package Model;

/**
 * Enumeration des différents opérateurs supportés
 */
public enum Operateur {

    //NOT ("NOT"),
    NOT ("¬"),
    //IMPLIES ("IMPLIES"),
    IMPLIES ("→"),
    //OR ("OR"),
    OR ("∨"),
    //AND ("AND");
    AND ("∧");// TODO : vérifier si nécessaire (a remplacer par une liste de propositions "formules" ?)


    private final String representation;

    Operateur(String r) {
        representation = r;
    }

    public String toString(){
        return representation;
    }
}
