package champollion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChampollionJUnitTest {
    
	Enseignant untel;
	UE uml, java;
        Salle s;
        Intervention i1, i2;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");	
                s = new Salle(24);
                i1 = new Intervention(8, 2, false, 8, TypeIntervention.TP, s, uml);
                i2 = new Intervention(12, 4, false, 10, TypeIntervention.TD, s, java);
	}
	

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}
	
	@Test
	public void testAjouteHeures() {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

                // 20h TD pour UML
                untel.ajouteEnseignement(uml, 0, 20, 0);
                
		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");		
		
	}
        
        @Test
	public void testHeuresPrevues() {
                // 20h CM 20h TD 20h TP pour UML
		untel.ajouteEnseignement(uml, 20, 20, 20);
                
                // 20h CM 20h TD 20h TP pour java
                untel.ajouteEnseignement(java, 20, 20, 20);

		assertEquals(130, untel.heuresPrevues(),
                        "L'enseignant doit avoir 130 heures prévues");

	}
        
        @Test
	public void testEnSousService() throws Exception {

		untel.ajouteEnseignement(uml, 20, 20, 20);
		untel.ajouteEnseignement(java, 20, 20, 20);

		untel.ajouteIntervention(i1);
		untel.ajouteIntervention(i2);

		assertTrue(untel.enSousService(), "L'enseignant doit etre en sous service. Il n'effectu que 6h au lieu de 130");
	}
	
        @Test
	public void testHeuresPrevuesUE() {
                // 20h CM 20h TD 20h TP pour UML
		untel.ajouteEnseignement(uml, 20, 20, 20);

		assertEquals(65, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit avoir 65 heures prévues pour l'UE 'uml'");

	}
        
        @Test
	public void testAjouteEnseignement(){
		untel.ajouteEnseignement(uml,0,10,0);

		assertFalse(untel.getListeService().isEmpty(),
                        "L'enseignement n'a pas été ajoutée");
	}
        
        @Test
	public void testAjouteIntervention(){
            untel.ajouteIntervention(i1);
            assertFalse(untel.getIntervention().isEmpty(),
                        "L'intervention n'a pas été ajoutée");
	}
        
        @Test
        public void testRestAPlanifier(){
            // 20h CM 20h TD 20h TP pour UML
            untel.ajouteEnseignement(uml, 20, 20, 20);
            untel.ajouteIntervention(i1);
            
            assertEquals(18, untel.restAPlanifier(uml, TypeIntervention.TP),
                        "Il doit rester 18h à plannifier pour l'UE uml");
	}
}
