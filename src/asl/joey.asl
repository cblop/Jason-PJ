// Joey agent

/* Initial beliefs and rules */

pos(stageRight).
//health(5).

/* Initial goals */
// Taunt Punch. Must first greet him and ask questions.
//!question(punch).

/* Plans */

+pos(P) : true
	<- .print("Joey is at ", P);
		move(P).

+!question(punch) : health(X) & X <= 0
	<- !die.
	
+!die
	<- .print("Joey is dead.");
	-pos(stageRight);
	+pos(offstageLeft);
	.send(narrative, achieve, endScene).
	
+!question(punch) : health(X) & X > 0
	<- .print("Joey asks Punch a question.");
	.send(punch, achieve, question(joey));
	say(wassup);
	.wait(3000);
	!question(punch).
	
+!greet(punch)
	<- .send(punch, tell, greeting(joey));
	.wait(3000);
	say(hello);
	.print("Hi, Punch").

+!take_damage
	<- ?health(X);
	.print("Joey's health is ", X, ".");
	.send(punch, tell, ouch(joey));
	-health(X);
	+health(X - 1).


	
