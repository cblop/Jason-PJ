import jason.JasonException;
import jason.infra.centralised.RunCentralisedMAS;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SettingsDialogue extends JFrame implements ActionListener {
//public class SettingsDialogue extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel mainPanel;
	JPanel settingsPanel;
	JPanel charPanel;
	JButton startbutton;
	JLabel sceneLabel;
	JLabel agentLabel;
	JLabel punchAngerLabel;
	JCheckBox audienceCheck;
	JCheckBox punchCheck;
	JCheckBox judyCheck;
	JCheckBox joeyCheck;
	JSlider numScenes;
	JSlider punchAngerLevel;
    Env env = new Env();
	
	public SettingsDialogue() {
		mainPanel = new JPanel(new GridLayout(0,1));
		settingsPanel = new JPanel(new FlowLayout());
		charPanel = new JPanel(new FlowLayout());
		numScenes = new JSlider(JSlider.HORIZONTAL, 1, 6, 1);
		punchAngerLevel = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
		punchAngerLevel.setMajorTickSpacing(1);
		punchAngerLevel.setSnapToTicks(true);
		punchAngerLevel.setPaintTicks(true);
		punchAngerLevel.setPaintLabels(true);
		numScenes.setMajorTickSpacing(1);
		numScenes.setSnapToTicks(true);
		numScenes.setPaintTicks(true);
		numScenes.setPaintLabels(true);

		startbutton = new JButton("Start the show!");
		startbutton.addActionListener(this);
		sceneLabel = new JLabel("Number of scenes:");
		agentLabel = new JLabel("Characters:");
		punchAngerLabel = new JLabel("Punch's anger level:");
		punchCheck = new JCheckBox("Punch");
		punchCheck.setSelected(true);
		judyCheck = new JCheckBox("Judy");
		judyCheck.setSelected(true);
		joeyCheck = new JCheckBox("Joey");
		audienceCheck = new JCheckBox("Enable audience participation");
		
		//settingsPanel.add(agentLabel);
		settingsPanel.add(sceneLabel);
		settingsPanel.add(numScenes);
		settingsPanel.add(audienceCheck);
		settingsPanel.setBorder(BorderFactory.createTitledBorder("Scene"));
		charPanel.add(punchCheck);
		charPanel.add(judyCheck);
		charPanel.add(joeyCheck);
		charPanel.add(punchAngerLabel);
		charPanel.add(punchAngerLevel);
		charPanel.setBorder(BorderFactory.createTitledBorder("Characters"));
		mainPanel.add(settingsPanel);
		mainPanel.add(charPanel);
		mainPanel.add(startbutton);
		this.add(mainPanel);
	}
	
	public static void main(String[] args) {
		SettingsDialogue settingsGui = new SettingsDialogue();
		settingsGui.setTitle("Punch and Judy settings");
		settingsGui.setSize(300,600);
		settingsGui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		settingsGui.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == startbutton) {
			//env.init(arguments);
        new Thread(){
        public void run() {
			try {
				RunCentralisedMAS.main(new String[]{"pjagents.mas2j"});
			} catch (JasonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        }.start();
		}
	}

}
