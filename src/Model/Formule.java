package Model;

import View.FormuleView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Theo on 07/12/2016.
 */
public class Formule  extends Observable{
    private static int nbCoups = 1;
    private FormuleView view;
    private boolean developped = false; //permet de ne se développer qu'une fois par niveau
    private boolean isInvalid = false;
    private Formule pere = null;

    public FormuleView getView() {
        return view;
    }

    public void setView(FormuleView v){
        view = v;
    }

    public Formule getfDroite() {
        return fDroite;
    }

    public ArrayList<FormuleBase> getComposants() {
        return composants;
    }

    public Formule getfGauche() {
        return fGauche;
    }

    private ArrayList<FormuleBase> composants = new ArrayList<>();
    private Formule fGauche, fDroite;

    public void addComposant(FormuleBase f){
        composants.add(f);
        f.setComposite(this);
    }

    public Formule(FormuleBase f) {
        addComposant(f);
    }

    private ArrayList<FormuleBase> getBrothers(FormuleBase pivot){
        ArrayList<FormuleBase> freres = new ArrayList<>();
        for (FormuleBase f : composants){
            if (!f.equals(pivot)){
                freres.add(f);
            }
        }
        return freres;
    }

    /**
     *
     * @param pivot la formule qui vient d'être développée
     * @param fList le retour du développement de la formuleBase
     */
    public void addChildren(FormuleBase pivot, ArrayList<Formule> fList){
        nbCoups++;

        if (!developped && fList != null){
            developped = true;
        }else {
            System.err.println("Formule déjà développée : ne rien faire");
            return;
        }


        switch (fList.size()){
            case 2: // deux enfants : disjonction
                fGauche = fList.get(0);
                fDroite = fList.get(1);

                break;
            case 1: // un enfant droit : conjonction
                fDroite = fList.get(0);


                break;
            default:
                System.err.println("Bizarre, on a vu passer " + fList.size() + "enfants");
        }

        //Maintenant ajouter les freres
        ArrayList<FormuleBase> freres = getBrothers(pivot);

        for (FormuleBase f : freres){
            if (fGauche != null){
                fGauche.addComposant(f.copy());
            }
            if (fDroite != null){
                fDroite.addComposant(f.copy());
            }
        }

        if (fGauche != null){
            fGauche.setPere(this);
            FormuleView vg = new FormuleView(fGauche);
            view.add(vg, BorderLayout.WEST);
        }
        if (fDroite != null){
            fDroite.setPere(this);
            FormuleView vd = new FormuleView(fDroite);
            view.add(vd, BorderLayout.EAST);
        }

        view.revalidate();

    }

    public String toString(){
        String ret = "";
        for (FormuleBase f : composants){
            ret += "*AND* " + f.toString() + "\n";
        }
        return ret;
    }

    /**
     * Quand signalé invalide, on demande à son père de vérifier ses enfants
     * Si toutes ses branches enfants sont fermées, on se ferme à notre tour
     */
    private void spreadInvalidity(){
        //Pour donner un effet de propagation
        boolean d = false, g = false;
        if (fDroite != null && fDroite.isInvalid) d = true;
        if (fGauche != null && fGauche.isInvalid) g = true;

        if (
                (g && d)
                || ((g || d) && (fGauche == null || fDroite == null))
                ){
            isInvalid = true;
            view.showInvalid();
            setChanged();
            notifyObservers();
            if (pere != null) pere.spreadInvalidity();
        }

    }

    /**
     * Analyse ses membres et tente de fermer sa branche courante
     */
    public void tryClose(FormuleBase f) {
        //f est une proposition ou une proposition contraire
        Proposition p;
        if (f instanceof SousFormuleSimple){
            p = (Proposition)((SousFormuleSimple) f).getfDroite().copy();
        }else {
            p = (Proposition) f.copy();
        }

        SousFormuleSimple contraire = new SousFormuleSimple(Operateur.NOT, p.copy());
        boolean sameSeen = false, reverseSeen = false;

        for (FormuleBase compo : composants){
            if (compo.equals(contraire)){
                reverseSeen = true;
            }
            if (compo.equals(p)){
                sameSeen = true;
            }
        }

        if (sameSeen && reverseSeen){
            System.out.println("La formule de base passe rouge");
            isInvalid = true;
            close();
            view.revalidate();
            view.showInvalid();
            spreadInvalidity();
            pere.spreadInvalidity();
        }

    }

    /**
     * Ferme la branche et pose le symbole contradiction en bas de la formule
     */
    private void close() {
        Formule son = new Formule(Proposition.contradiction());
        new FormuleView(son);
        son.isInvalid = true;
        son.view.showInvalid();

        view.add(son.getView(), BorderLayout.CENTER);
    }

    public boolean isInvalid() {
        return isInvalid;
    }

    public void setPere(Formule pere) {
        this.pere = pere;
    }

    public static int nbCoups() {
        return nbCoups;
    }
}
