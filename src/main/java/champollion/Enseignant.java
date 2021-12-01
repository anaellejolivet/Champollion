package champollion;

import static champollion.TypeIntervention.CM;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Enseignant extends Personne {

    public ArrayList<ServicePrevu> listeService = new ArrayList<>();
    public ArrayList<Intervention> intervention = new ArrayList<>();

    // TODO : rajouter les autres méthodes présentes dans le diagramme UML
    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures
     * équivalent TD" Pour le calcul : 1 heure de cours magistral vaut 1,5 h
     * "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut
     * 0,75h "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet
     * enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        // TODO: Implémenter cette méthode
        int heuresTotales = 0;
        for (ServicePrevu s : listeService) {
            heuresTotales = (int) Math.round(heuresTotales + (s.getVolumeTP() * 0.75) + (s.getVolumeCM() * 1.5) + s.getVolumeTD());
        }
        return heuresTotales;

    }

    public boolean enSousService() {
        int heuresPrevues = this.heuresPrevues();
        int heuresIntervention = 0;
        for (Intervention inter : intervention) {
            if (!inter.getAnnulee()) {
                heuresIntervention += inter.getDuree();
            }
        }
        return heuresPrevues > heuresIntervention;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE
     * spécifiée en "heures équivalent TD" Pour le calcul : 1 heure de cours
     * magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent
     * TD" 1 heure de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet
     * enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        // TODO: Implémenter cette méthode
        int heuresTotales = 0;
        for (ServicePrevu s : listeService) {
            if (s.getUe() == ue) {
                heuresTotales = heuresPrevues();
            }
        }
        return heuresTotales;

    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        // TODO: Implémenter cette méthode
        ServicePrevu s = new ServicePrevu(volumeCM, volumeTD, volumeTP, ue);
        listeService.add(s);

    }

    public void ajouteIntervention(Intervention inter) {
        intervention.add(inter);
    }

    public int restAPlanifier(UE ue, TypeIntervention type) {
        int typeInterVol = 0;
        for (ServicePrevu s : listeService) {
            if (s.getUe().equals(ue)) {

                if (TypeIntervention.CM.equals(type)) {
                    typeInterVol = s.getVolumeCM();
                }
                if (TypeIntervention.TP.equals(type)) {
                    typeInterVol = s.getVolumeTP();
                }
                if (TypeIntervention.TD.equals(type)) {
                    typeInterVol = s.getVolumeTD();
                }
            }
            for (Intervention i : intervention) {
                if (i.getType().equals(type) && i.getUe().equals(ue)) {
                    typeInterVol -= i.getDuree();
                }

            }
        }
        return typeInterVol;
    }

    public ArrayList<ServicePrevu> getListeService() {
        return listeService;
    }

    public ArrayList<Intervention> getIntervention() {
        return intervention;
    }

}
