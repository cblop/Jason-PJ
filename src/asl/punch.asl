// Punch agent

/* Initial beliefs and rules */

//pos(stageLeft).
//anger(0).
//sceneStart.

/* Initial goals */
//!hit(judy).
!anger(0).


/* Plans */

-annoyance(Y)
	<- //?mood(X);
	   //-mood(X);
	   ?anger(X);
	   !hit(stop);
	   +anger(0).
	   //+mood(happy).

+sceneStart
	<- ?startPos(X);
	   ?startAnger(Y);
	   move(X);
	   +mood(happy);
	   +pos(X);
	   +anger(Y);
	   -sceneStart.
	   
+sceneEnd : true
	<- move(offstageLeft).
	   

+greeting(X)
	<- .print("Hi, ", X);
		say(greeting);
		-greeting(X).

+pos(P) : true
 <- .print("Punch is at ", P);
 	move(P).
 	
+!hit(X) : X == stop
	<- hit(X);
	   .print("Punch stops hitting").

+!hit(X) : X == judy
	<- //+hitting(judy);
	-pos(stageLeft);
	//move(stageCentre);
	+pos(stageCentre);
	hit(X);
	say(angry);
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
	+anger_rising;
	!anger_check(1).
	
+dead(X) : annoyance(Y) & X == Y
	<- .print("Punch believes ", X, " has died.");
	-annoyance(Y).
	   
	
+anger_rising
	<- ?anger(X);
	?mood(Y);
	-anger(X);
	say(Y);
	+anger(X + 1);
	-anger_rising.


+!anger_check(Y) : anger(X) & X < 4 
	<- .print("Punch is happy");
		!hit(stop);
	   .print("Anger:", X).
	


+!anger_check(_) : anger(X) & X > 3 & X < 6
	<- .print("Punch is OK");
	   -mood(happy);
		!hit(stop);
	   +mood(OK).

+!anger_check(_) : anger(X) & X > 5 & X < 9
	<- .print("Punch is annoyed");
	   -mood(OK);
	   +mood(annoyed).
	
	
+!anger_check(_) : anger(X) & X > 8
	<- ?victim(Y); 
	.print("Punch is angry");
	-mood(annoyed);
	+mood(angry);
	!hit(Y).

	

