// Environment code for project pjagents

import jason.asSyntax.*;
import jason.environment.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import processing.core.PApplet;
import punchjudy.AnimEvent;
import punchjudy.MoveEvent;
import punchjudy.PuppetShow;
import punchjudy.SpeakEvent;

public class Env extends Environment {

    //private Logger logger = Logger.getLogger("pjagents."+env.class.getName());
    //final PunchJudy pj = new PunchJudy();
	
	public Thread animThread;
	public PApplet pde;
    private String[] allAgents = {"punch", "judy", "joey"};

    public Env() {
    	System.out.println("Env constructor");
    }

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
    	//SettingsDialogue.main(args);
		animThread = new Thread(){
		PApplet pde = new PApplet();
			public void run() {
                pde.main(new String[] { punchjudy.PuppetShow.class.getName() });
			}
		};
		
		animThread.start();

		// We need to wait for the show to start
		try {
			animThread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        super.init(args);


    	startAgents();

    }
    
    
    public void startAgents() {
    	System.out.println("Env is started");
    	//System.out.println(SettingsDialogue.settingsMap);
    	
        //addPercept(Literal.parseLiteral("percept(demo)"));
        List<String> agents = new ArrayList<String>();
        
        for (String ag : allAgents) {
                if (SettingsDialogue.settingsMap.get(ag) == 1) {
                        agents.add(ag);
                }
        }
    	
    	for (String ag : agents.subList(0, 2)) {
    		if (ag.equals("punch")) {
    			System.out.println(SettingsDialogue.settingsMap.get("punchanger"));
    			addPercept(ag, Literal.parseLiteral("startPos(stageLeft)."));
    			addPercept(ag, Literal.parseLiteral("startAnger(" + SettingsDialogue.settingsMap.get("punchanger") + ")."));
    			addPercept(ag, Literal.parseLiteral("sceneStart."));
    		}
    		else if (ag.equals("judy")){
    			addPercept(ag, Literal.parseLiteral("startPos(stageRight)."));
    			addPercept(ag, Literal.parseLiteral("startHealth(" + SettingsDialogue.settingsMap.get("judyhealth") + ")."));
    			addPercept(ag, Literal.parseLiteral("sceneStart."));
    		}
    		else if (ag.equals("joey")){
    			addPercept(ag, Literal.parseLiteral("startPos(stageRight)."));
    			addPercept(ag, Literal.parseLiteral("startHappy(" + SettingsDialogue.settingsMap.get("joeyhappy") + ")."));
    			addPercept(ag, Literal.parseLiteral("sceneStart."));
    		}
    	}
        
    }
    
    void updatePercepts (String[] agents, Literal newPercept) {
    	for (String ag : agents) {
    		addPercept(ag, newPercept);
    	}
    	
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        //logger.info("executing: "+action+", but not implemented!");
    	String functor = action.getFunctor();
    	//System.out.println("Agent name: " + agName);
    	List<Term> values = action.getTerms();
    	String valuef = values.get(0).toString();
    	//System.out.println("Value: " + action.getTerm(0));
    	//System.out.println("Functor: " + action.getFunctor());
    	removePercept(agName, Literal.parseLiteral("sceneStart."));
    	
    	// This is not ideal. Should just have one move event that takes two parameters:
    	// start time and duration
    	if (functor.equals("place")) {
    		PuppetShow.addEvent(new MoveEvent(agName, 0, 0, valuef));
    	}
    	
    	if (functor.equals("move")) {
    		//System.out.println(values.get(0).toString());
    		PuppetShow.addEvent(new MoveEvent(agName, 10, 10, valuef));
    	}
    	
    	if (functor.equals("say")) {
    		//System.out.println(values.get(0).toString());
    		PuppetShow.addEvent(new SpeakEvent(agName, 10, 10, valuef));
    		//PunchJudy.addEvent(new SpeakEvent(agName, 150, 20, "hello"));
    		//PunchJudy.addEvent(new SpeakEvent(agName, 150, 20, "Hey hey hey"));
    	}
    	
    	if (functor.equals("hit")) {
    		//System.out.println("hit: ");
    		if (valuef.equals("judy")) {
    			System.out.println("Punch should be hitting judy");
    			//PunchJudy.addEvent(new MoveEvent(agName, 10, 20, "stageRight"));
    			PuppetShow.addEvent(new SpeakEvent(agName, 10, 20, "laugh"));
    			PuppetShow.addEvent(new AnimEvent(agName, 10, 20, "hit"));
    		}
    		if (valuef.equals("stop")) {
    			System.out.println("Punch stops his hitting");
    			PuppetShow.addEvent(new AnimEvent(agName, 10, 20, "rest"));
    		}
    	}
    	
    	if (functor.equals("die")) {
    		System.out.println(agName + " has died.");
			PuppetShow.addEvent(new AnimEvent(agName, 10, 10, "dead"));
			SettingsDialogue.settingsMap.put(agName, 0);
			updatePercepts(allAgents, Literal.parseLiteral("dead(judy)"));
			endScene();
    	}
    	
        return true;
    }
    
    private void startScene(String[] characters) {
    	
    }
    
    private void endScene() {
			System.out.println("Scene has ended.");
			int scenesLeft = SettingsDialogue.settingsMap.get("scenes");
			if (scenesLeft > 1) {
				System.out.println("Next scene");
				//startScene();
				try {
					animThread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				addPercept("punch", Literal.parseLiteral("sceneEnd"));
				addPercept("judy", Literal.parseLiteral("sceneEnd"));
				addPercept("joey", Literal.parseLiteral("sceneEnd"));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				startAgents();

			}
			else {
				System.out.println("End of show");
				stop();
				//pde.exit();
				//animThread.interrupt();
				
				// This exits the program. Probably don't want this.
				// You'd instead want to fade to black.
				//System.exit(0);
			}
    	
    }
    

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
