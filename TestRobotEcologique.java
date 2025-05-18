
public class TestRobotEcologique {
	public static void main(String[] args) {
        System.out.println("=== Test des robots écologiques ===\n");
        
        // Test RobotRecycleur
        RobotRecycleur recycleur = new RobotRecycleur("ECO-R1", 0, 0);
        System.out.println("Robot créé: " + recycleur);
        
        try {
            recycleur.recharger(100);
            recycleur.démarrer();
            recycleur.switchToRenewableEnergy("Solaire");
            
            recycleur.recyclerMateriau("plastique", 30);
            recycleur.recyclerMateriau("verre", 15);
            recycleur.deplacer(20, 15);
            
            System.out.println("\nEmpreinte carbone: " + recycleur.calculateCarbonFootprint());
            System.out.println(recycleur.getRecyclingStats());
            
            recycleur.recyclerDechets();
            System.out.println("\nAprès recyclage des déchets: " + recycleur);
            
        } catch (RobotException | EnergySourceException e) {
            System.err.println("Erreur: " + e.getMessage());
        }
        
        // Test RobotEnvironnemental
        RobotEnvironnemental environnemental = new RobotEnvironnemental("ECO-E1", 5, 5);
        System.out.println("\n=== Test Robot Environnemental ===");
        System.out.println("Robot créé: " + environnemental);
        
        try {
            environnemental.recharger(100);
            environnemental.démarrer();
            environnemental.switchToRenewableEnergy("Eolien");
            
            environnemental.connecter("EcoNet");
            
            // Prendre plusieurs relevés à différents endroits
            environnemental.prendreRelevé();
            environnemental.deplacer(10, 20);
            environnemental.deplacer(30, 15);
            environnemental.deplacer(5, 25);
            
            System.out.println("\nEmpreinte carbone: " + environnemental.calculateCarbonFootprint());
            System.out.println(environnemental.getEnvironmentalReport());
            
        } catch (RobotException | EnergySourceException e) {
            System.err.println("Erreur: " + e.getMessage());
        }
    }
}
