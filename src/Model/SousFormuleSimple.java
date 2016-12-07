package Model;

import java.util.ArrayList;

/**
 * Created by Theo on 07/12/2016.
 */
public class SousFormuleSimple extends FormuleBase {
    public FormuleBase getfDroite() {
        return fDroite;
    }

    private FormuleBase fDroite;
    private Operateur op;

    public SousFormuleSimple(Operateur o, FormuleBase f) {
        super();
        fDroite = f;
        op = o;
    }

    public String toString(){
        return "("+op.toString()+" "+fDroite.toString()+")";
    }

    @Override
    public ArrayList<Formule> developper() {


        System.out.println("Développement de la SFS " + toString());

        ArrayList<Formule> enfants = new ArrayList<>();

        if (op == Operateur.NOT) { //cas attendu : NOT( <une sous formule> ), tout autre opérateur est normalement impossible

            if (fDroite instanceof SousFormuleBipartie) { //cas attendu : une sous-formule bipartie (on ne vas pas développer NOT(P) )
                FormuleBase A = ((SousFormuleBipartie) fDroite).getfGauche().copy();
                FormuleBase B = ((SousFormuleBipartie) fDroite).getfDroite().copy();
                Operateur operateur = ((SousFormuleBipartie) fDroite).getOp();

                FormuleBase notA = new SousFormuleSimple(Operateur.NOT, A).copy();
                FormuleBase notB = new SousFormuleSimple(Operateur.NOT, B).copy();

                switch (operateur) {
                    //Pas de cas NOT (double négation, voir partie traitement fDroite est une SousSFormuleSimple)
                    case AND:       // NOT( A AND B ), --> NOT(A) OR NOT(B)
                        enfants.add(new Formule(notA));
                        enfants.add(new Formule(notB));
                        break;
                    case OR:        // NOT(A OR B), --> NOT(A) AND NOT(B)
                        Formule enfant = new Formule(notA);
                        enfant.addComposant(notB);
                        enfants.add(enfant);
                        break;
                    case IMPLIES:   // NOT(A -> B), --> A AND NOT(B)
                        Formule enfant2 = new Formule(A);
                        enfant2.addComposant(notB);
                        enfants.add(enfant2);
                        break;
                }
            } else if (fDroite instanceof SousFormuleSimple) { //seul cas traitable pour une sous-formule simple :
                if (((SousFormuleSimple) fDroite).getOp() == Operateur.NOT) { //la double négation NOT( NOT( A ) )
                    Formule A = new Formule( ((SousFormuleSimple) fDroite).fDroite.copy() );
                    enfants.add(A);
                }
            } else if (fDroite instanceof Proposition) { // rien a développer pour NOT(<proposition>)
                composite.tryClose(this);
                return null;

            }
        }

        if (enfants.size() == 0) {
            System.err.println("Erreur : développement non supporté pour " + this + " (from SFS::developper()");
        }

        return enfants;

    }

    @Override
    public FormuleBase copy() {

        return new SousFormuleSimple(Operateur.NOT, fDroite.copy());
    }

    public Operateur getOp() {
        return op;
    }
}
