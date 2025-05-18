import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class SimulationRobotsEcoSwing extends fenetre {

    private JComboBox<String> robotSelector;
    private JTextArea logArea;
    private DrawingPanel drawingPanel;
    private ArrayList<Robot> robots = new ArrayList<>();
    private Robot selectedRobot = null;

    public SimulationRobotsEcoSwing() {
        setTitle("Simulation de Robots Écologiques (Swing)");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initRobots();
        initUI();
    }

    private void initRobots() {
        robots.add(new RobotLivraison("LIVR-1", 100, 100));
        robots.add(new RobotRecycleur("RECY-1", 200, 150));
        robots.add(new RobotEnvironnemental("ENV-1", 300, 200));
        robots.add(new RobotSolaire("SOL-1", 400, 250));
    }

    private void initUI() {
        setLayout(new BorderLayout());

        drawingPanel = new DrawingPanel();
        drawingPanel.setPreferredSize(new Dimension(800, 600));
        add(drawingPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(12, 1, 5, 5));
        controlPanel.setPreferredSize(new Dimension(200, 0));

        robotSelector = new JComboBox<>();
        for (Robot robot : robots) {
            robotSelector.addItem(robot.id);
        }
        robotSelector.addActionListener(e -> selectRobot());

        JButton startBtn = new JButton("Démarrer");
        startBtn.addActionListener(e -> startRobot());

        JButton stopBtn = new JButton("Arrêter");
        stopBtn.addActionListener(e -> stopRobot());

        JButton moveBtn = new JButton("Déplacer");
        moveBtn.addActionListener(e -> moveRobot());

        JButton taskBtn = new JButton("Tâche");
        taskBtn.addActionListener(e -> doTask());

        JButton rechargeBtn = new JButton("Recharger");
        rechargeBtn.addActionListener(e -> rechargeRobot());

        JButton connectBtn = new JButton("Connecter");
        connectBtn.addActionListener(e -> connectRobot());

        JButton renewableBtn = new JButton("Énergie Renouvelable");
        renewableBtn.addActionListener(e -> switchToRenewable());

        JButton carbonBtn = new JButton("Empreinte Carbone");
        carbonBtn.addActionListener(e -> calculateCarbon());

        controlPanel.add(new JLabel("Sélection du robot:"));
        controlPanel.add(robotSelector);
        controlPanel.add(startBtn);
        controlPanel.add(stopBtn);
        controlPanel.add(moveBtn);
        controlPanel.add(taskBtn);
        controlPanel.add(rechargeBtn);
        controlPanel.add(connectBtn);
        controlPanel.add(renewableBtn);
        controlPanel.add(carbonBtn);

        add(controlPanel, BorderLayout.EAST);

        logArea = new JTextArea(6, 40);
        logArea.setEditable(false);
        add(new JScrollPane(logArea), BorderLayout.SOUTH);
    }

    private void selectRobot() {
        String selectedId = (String) robotSelector.getSelectedItem();
        for (Robot r : robots) {
            if (r.id.equals(selectedId)) {
                selectedRobot = r;
                log("Robot sélectionné : " + r.id);
                break;
            }
        }
    }

    private void startRobot() {
        try {
            selectedRobot.démarrer();
            log(selectedRobot.id + " démarré.");
            drawingPanel.repaint();
        } catch (RobotException e) {
            log("Erreur: " + e.getMessage());
        }
    }

    private void stopRobot() {
        selectedRobot.arreter();
        log(selectedRobot.id + " arrêté.");
        drawingPanel.repaint();
    }

    private void moveRobot() {
        int x = (int)(Math.random() * 700);
        int y = (int)(Math.random() * 500);
        try {
            selectedRobot.deplacer(x, y);
            log("Déplacé vers (" + x + "," + y + ")");
            drawingPanel.repaint();
        } catch (RobotException e) {
            log("Erreur de déplacement: " + e.getMessage());
        }
    }

    private void doTask() {
        try {
            selectedRobot.effectuerTache();
            log("Tâche effectuée.");
            drawingPanel.repaint();
        } catch (RobotException e) {
            log("Erreur de tâche: " + e.getMessage());
        }
    }

    private void rechargeRobot() {
        selectedRobot.recharger(30);
        log("" + selectedRobot.id + " rechargé (30%).");
        drawingPanel.repaint();
    }

    private void connectRobot() {
        if (selectedRobot instanceof Connectable) {
            try {
                ((Connectable) selectedRobot).connecter("EcoNet");
                log(selectedRobot.id + " connecté à EcoNet.");
            } catch (RobotException e) {
                log("Erreur de connexion: " + e.getMessage());
            }
        }
    }

    private void switchToRenewable() {
        if (selectedRobot instanceof Ecological) {
            try {
                ((Ecological) selectedRobot).switchToRenewableEnergy("Solaire");
                log(selectedRobot.id + " utilise énergie solaire.");
            } catch (EnergySourceException e) {
                log("Erreur source d'énergie: " + e.getMessage());
            }
        }
    }

    private void calculateCarbon() {
        if (selectedRobot instanceof Ecological) {
            double empreinte = ((Ecological) selectedRobot).calculateCarbonFootprint();
            log("Empreinte carbone de " + selectedRobot.id + " : " + String.format("%.2f", empreinte));
        }
    }

    private void log(String msg) {
        logArea.append(msg + "\n");
    }

    class DrawingPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Robot r : robots) {
                g.setColor(r.enMarche ? Color.GREEN : Color.RED);
                g.fillOval(r.x, r.y, 30, 30);
                g.setColor(Color.BLACK);
                g.drawString(r.id, r.x, r.y - 5);
                g.drawString("" + r.energie + "%", r.x, r.y + 45);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimulationRobotsEcoSwing().setVisible(true));
    }
}
