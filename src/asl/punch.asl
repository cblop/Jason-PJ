// Punch agent

/* Initial beliefs and rules */

//pos(stageLeft).
anger(0).

/* Initial goals */
//!hit(judy).


/* Plans */

+greeting(judy)
	<- .print("Hi, Judy!");
		//say(hello);
		-greeting(judy).

+pos(P) : true
 <- .print("Punch is at ", P);
 	move(P).

+!hit(judy)
	<- //+hitting(judy);
	.send(judy, achieve, take_damage);
	-pos(stageLeft);
	+pos(stageRight);
	hit(judy);
	.print("Punch hits Judy.").
	//do_hitting(judy).

+anger(X) <- .print("Punch's anger level is ", X).

+!question(judy)
	<-
	//-question(judy);
	.print("Question from Judy");
	!get_angry;
	!anger_check.
	
+!get_angry
	<- ?anger(X);
	-anger(X);
	+anger(X + 1).


+!anger_check : anger(X) & X < 3
	<- .print("Punch is happy");
	   .print("Anger:", X).
	


+!anger_check : anger(X) & X < 6
	<- .print("Punch is OK").

+!anger_check : anger(X) & X > 5 & X < 9
	<- .print("Punch is annoyed").
	
	
+!anger_check : anger(X) & X > 8
	<- .print("Punch is angry");
	!hit(judy).

	

