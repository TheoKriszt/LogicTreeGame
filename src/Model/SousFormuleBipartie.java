package Model;

import java.util.ArrayList;

import static Model.Operateur.NOT;

/**
 * Created by Theo on 07/12/2016.
 */
public class SousFormuleBipartie extends FormuleBase {
    public FormuleBase getfDroite() {
        return fDroite;
    }

    private FormuleBase fDroite, fGauche;
    private Operateur op;

    public SousFormuleBipartie(FormuleBase fGauche,  Operateur op,  FormuleBase fDroite) {
        super();
        this.fDroite = fDroite;
        this.fGauche = fGauche;
        this.op = op;
    }

    public String toString(){
        return "("+fGauche.toString()+" "+op.toString()+" "+fDroite.toString()+")";
    }

    @Override
    public ArrayList<Formule> developper(){
        System.out.println("Développement de la SFB " + toString());

        ArrayList<Formule> enfants = new ArrayList<>();

        FormuleBase A = fGauche.copy();
        FormuleBase B = fDroite.copy();


        switch (op){
            case AND:
                Formule enfant = new Formule(A);
                enfant.addComposant(B);
                enfants.add(enfant);
                break;
            case OR: // Se scinde en deux branches enfants
                enfants.add( new Formule(A));
                enfants.add( new Formule(B));
                break;
            case IMPLIES: // (A -> B) --> NOT(A) OR B
                enfants.add( new Formule( new SousFormuleSimple(NOT, A) ) );
                enfants.add( new Formule(B));
                break;
        }

        if(enfants.size() == 0){
            System.err.println("Erreur : développement non supporté pour " + this);
        }

        return enfants;
    }

    @Override
    public FormuleBase copy() {
        return new SousFormuleBipartie(fGauche.copy(), op, fDroite.copy());
    }

    public FormuleBase getfGauche() {
        return fGauche;
    }

    public Operateur getOp() {
        return op;
    }
}
