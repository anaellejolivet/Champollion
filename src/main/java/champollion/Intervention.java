/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package champollion;

/**
 *
 * @author anaellejolivet
 */
public class Intervention {
    private int debut;
    private int duree;
    private boolean annulee;
    private int heureDebut;
    private TypeIntervention type;
    private Salle salle;
    private UE ue;

    public Intervention(int debut, int duree, boolean annulee, int heureDebut, TypeIntervention type, Salle salle, UE ue) {
        this.debut = debut;
        this.duree = duree;
        this.annulee = annulee;
        this.heureDebut = heureDebut;
        this.type = type;
        this.salle = salle;
        this.ue = ue;
    }

    public int getDuree() {
        return duree;
    }

    public TypeIntervention getType() {
        return type;
    }
    
    public boolean getAnnulee(){
        return annulee;
    }

    public UE getUe() {
        return ue;
    }
    
    
}
