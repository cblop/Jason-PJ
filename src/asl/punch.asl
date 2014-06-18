// Punch agent

/* Initial beliefs and rules */

//pos(stageLeft).
//anger(0).
sceneStart.

/* Initial goals */
//!hit(judy).
!mood(happy).


/* Plans */

-annoyance
	<- ?mood(X);
	   -mood(X);
	   +mood(happy).

+sceneStart
	<- ?startPos(X);
	   ?startAnger(Y);
	   +pos(X);
	   +anger(Y);
	   -sceneStart.
	   

+greeting(X)
	<- .print("Hi, ", X);
		say(hello);
		-greeting(X).

+pos(P) : true
 <- .print("Punch is at ", P);
 	move(P).

+!hit(X)
	<- //+hitting(judy);
	-pos(stageLeft);
	//move(stageCentre);
	+pos(stageCentre);
	hit(X);
	.send(X, achieve, take_damage);
	.print("Punch hits ", X).
	//do_hitting(judy).

+anger(X) <- .print("Punch's anger level is ", X).

+!question(X)
	<- 
	//-question(judy);
	.print("Question from ", X);
	+victim(X);
	+annoyance(X);
	+anger_rising(1);
	!anger_check.
	
+anger_rising(Y)
	<- ?anger(X);
	-anger(X);
	+anger(X + Y);
	-anger_rising(Y).

	
+!anger_check(Y)
	<- .print("Anger").

+!anger_check(Y) : anger(X) & X < 4 
	<- .print("Punch is happy");
	   .print("Anger:", X).
	


+!anger_check : anger(X) & X > 3 & X < 6
	<- .print("Punch is OK");
	   -mood(happy);
	   +mood(OK).

+!anger_check : anger(X) & X > 5 & X < 9
	<- .print("Punch is annoyed");
	   -mood(OK);
	   +mood(annoyed).
	
	
+!anger_check : anger(X) & X > 8
	<- ?victim(Y); 
	.print("Punch is angry");
	-mood(annoyed);
	+mood(angry);
	!hit(Y).

	

