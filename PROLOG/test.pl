isType('squa',square).
isType('rect',rectangle).
isType('not1',notsquare).
isType('not2',notrectangle).
extends(square,rectangle).

returnType(area(A),int):-
	isType(A,rectangle).
returnType(area(A),int):-
	isType(A,B),
	extends(B,rectangle).
returnType(sArea(A),int):-
	isType(A,square).


area(A):- isType(A,square).
area(A):- isType(A,rectangle).
sArea(A):- isType(A,square).

infer(A,X):-
	isType(A,X).

infer(A,X):-
	isType(A,B),
	extends(B,X).

infer(A,X):-
	returnType(A,X).
	


	



