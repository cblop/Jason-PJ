// Judy agent

/* Initial beliefs and rules */

pos(stageRight).
//health(5).

/* Initial goals */
// Taunt Punch. Must first greet him and ask questions.
//!question(punch).

/* Plans */

+pos(P) : true
	<- .print("Judy is at ", P);
		move(P).

+!question(punch) : health(X) & X <= 0
	<- !die.
	
+!die
	<- .print("Judy is dead.");
	-pos(stageRight);
	+pos(offstageLeft);
	.send(narrative, achieve, endScene).
	
+!question(punch) : health(X) & X > 0
	<- .print("Judy asks Punch a question.");
	.send(punch, achieve, question(judy));
	say(wassup);
	.wait(3000);
	!question(punch).
	
+!greet(punch)
	<- .send(punch, tell, greeting(judy));
	.wait(3000);
	say(hello);
	.print("Hi, Punch").

+!take_damage
	<- ?health(X);
	.print("Judy's health is ", X, ".");
	.send(punch, tell, ouch(judy));
	-health(X);
	+health(X - 1).


	
