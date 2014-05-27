// Narrative agent

/* Initial beliefs and rules */



/* Initial goals */

!start.

/* Plans */

+!start
	<- .print("Scene starts");
	.send(punch, tell, pos(stageLeft));
	//.send(punch, tell, anger(0));
	.send(judy, tell, health(5));
	.send(judy, achieve, greet(punch));
	.send(judy, achieve, question(punch)).
	
+!endScene
	<- .print("Scene ends").
