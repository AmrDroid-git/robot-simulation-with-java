
public class TestRobot {

	public static void main(String[] args) {
		RobotLivraison robot = new RobotLivraison("R2-D1", 0, 0);
		
		try {
            robot.démarrer(); 
        } catch (RobotException e) {
            System.err.println(" Exception capturée (démarrage) : " + e.getMessage());
        }
		
		
		
		try {
			robot.recharger(100);
            robot.démarrer();
            robot.deplacer(200, 200); 
        } catch (RobotException e) {
            System.err.println(" Exception capturée (déplacement) : " + e.getMessage());
        }
		
		
		
		try {
			robot.recharger(100);
            robot.heuresUtilusation = 105;
            robot.verifierMaintenance();
        } catch (MaintenanceRequiseException e) {
            System.err.println(" Exception capturée (maintenance) : " + e.getMessage());
        }
		
		robot.heuresUtilusation=0;
		
		try {
            robot.deconnecter();
            robot.envoyerDonnees("Mara7beeeee");
        } catch (RobotException e) {
            System.err.println(" Exception capturée (connexion) : " + e.getMessage());
        }
		
		
		RobotLivraison robot1 = new RobotLivraison("R2-D2", 0, 0);

        try {
        	
            robot1.recharger(50);
            robot1.démarrer();
            robot1.connecter("SL2");
            robot1.envoyerDonnees("hello les amis :)");
            robot1.chargerColis("Tunis");
            
            robot1.enlivraison = true;
            robot1.FaireLivraison(10, 20); 
            System.out.println("\n" + robot1);
            
            robot1.effectuerTache();
            robot1.arreter();

            System.out.println("\nHistorique des actions:");
            System.out.println(robot1.getHistorique());
            

        } catch (RobotException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

		

	

}

