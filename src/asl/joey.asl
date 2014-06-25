// Joey agent

/* Initial beliefs and rules */

//pos(stageRight).
//health(5).
//sceneStart.

/* Initial goals */
// Taunt Punch. Must first greet him and ask questions.
!greet(punch).
!question(punch).

/* Plans */

+sceneStart
	<- ?startPos(X);
	   ?startHappy(Y);
	   move(X);
	   +pos(X);
	   +happy(Y);
	   !question(punch);
	   -sceneStart.

+pos(P) : true
	<- .print("Joey is at ", P);
		move(P).
	
+!question(punch) : happy(X) & X > 0
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



	
