// Environment code for project pjagents

import jason.asSyntax.*;
import jason.environment.*;

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
    
    public Env() {
    	System.out.println("Env constructor");
    }

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
    	//SettingsDialogue.main(args);
		new Thread(){
			public void run() {
                PApplet.main(new String[] { punchjudy.PuppetShow.class.getName() });
			}
		}.start();

		// We need to wait for the show to start
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	startAgents(args);

    }
    
    
    public void startAgents(String[] args) {
    	System.out.println("Env is started");
    	System.out.println(SettingsDialogue.settingsMap);
        super.init(args);
        //addPercept(Literal.parseLiteral("percept(demo)"));
        
        if (SettingsDialogue.settingsMap.get("punch") == 1) {
        	addPercept("punch", Literal.parseLiteral("pos(stageLeft)"));
        	addPercept("punch", Literal.parseLiteral("anger(" + SettingsDialogue.settingsMap.get("punchanger")  +")"));
        }
        if (SettingsDialogue.settingsMap.get("judy") == 1) {
        	addPercept("judy", Literal.parseLiteral("pos(stageLeft)"));
        	addPercept("judy", Literal.parseLiteral("health(" + SettingsDialogue.settingsMap.get("judyhealth")  +")"));
        }
        if (SettingsDialogue.settingsMap.get("joey") == 1) {
        	addPercept("joey", Literal.parseLiteral("pos(stageLeft)"));
        	addPercept("joey", Literal.parseLiteral("happy(" + SettingsDialogue.settingsMap.get("joeyhappy")  +")"));
        }
    	
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        //logger.info("executing: "+action+", but not implemented!");
    	String functor = action.getFunctor();
    	System.out.println("Agent name: " + agName);
    	List<Term> values = action.getTerms();
    	String valuef = values.get(0).toString();
    	//System.out.println("Value: " + action.getTerm(0));
    	System.out.println("Functor: " + action.getFunctor());
    	
    	if (functor.equals("move")) {
    		System.out.println(values.get(0).toString());
    		PuppetShow.addEvent(new MoveEvent(agName, 0, 10, valuef));
    	}
    	
    	if (functor.equals("say")) {
    		System.out.println(values.get(0).toString());
    		PuppetShow.addEvent(new SpeakEvent(agName, 10, 20, valuef));
    		//PunchJudy.addEvent(new SpeakEvent(agName, 150, 20, "hello"));
    		//PunchJudy.addEvent(new SpeakEvent(agName, 150, 20, "Hey hey hey"));
    	}
    	
    	if (functor.equals("hit")) {
    		System.out.println("hit: ");
    		if (valuef.equals("judy")) {
    			System.out.println("judy");
    			//PunchJudy.addEvent(new MoveEvent(agName, 10, 20, "stageRight"));
    			PuppetShow.addEvent(new SpeakEvent(agName, 10, 20, "laugh"));
    			PuppetShow.addEvent(new AnimEvent(agName, 10, 20, "hit"));
    		}
    	}
    	
        return true;
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
