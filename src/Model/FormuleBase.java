package Model;

import java.util.ArrayList;

/**
 * Created by Theo on 07/12/2016.
 */
public abstract class FormuleBase {
    protected Formule composite;

    /**
     * Le composite demande de se décomposer, don de générer ses enfants
     * Comme les enfants peuvent être de forme conjonctive (A AND B), on génère une liste de Formules
     * S'il y a un fils (droit) c'est une conjonction
     * S'il y a deux fils (gauche et droit) c'est une disjonction
     * une fois reprise par le composite, il faut alors que le composite s'intègre ses fils fraichement renvoyés
     * et enfin que chaque frère de la formuleBase traitée soit RECOPIé dans chacun des fils
     */
    public abstract ArrayList<Formule> developper();

    public abstract FormuleBase copy();

    public void setComposite(Formule f){
        composite = f;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof FormuleBase)) return false;
        return this.toString().equals(o.toString());
    }

}
