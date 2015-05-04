 (setq p1 '(+ x (* x (- y (/ z 2)))))
 (setq p2 '(+ (- z 2) (* x 5)))
 (setq p3 '(+ 1 a))
 (setq p4 '(+ (- 3 2) (* 2 5)))
 (setq p5 '(+ x (- 0 x)))
 ; (setq q1 (evalexp '( (x 2) (z 8) ) p1))
 ; (setq q2 (evalexp '( (x 2) (z 8)) p2))
 ; (setq q3 (evalexp '( (x 0) ) (mulexp p1 p2)))




 (defun addexp (e1 e2) (list '+ e1 e2))
 (defun subexp (e1 e2) (list '- e1 e2))
 (defun mulexp (e1 e2) (list '* e1 e2))
 (defun divexp (e1 e2) (list '/ e1 e2))

 ;(defun evalexp ())


 (defun deep-subst (old new l)
  (cond
   ((null l) 
     nil
   )
   ((listp (car l))
   	;(progn (print "tester1") (print old) (print new) (print l))  
    (cons (deep-subst old new (car l)) (deep-subst old new (cdr l)))
   )
   ((eq old (car l))
    ;(progn (print "tester2") (print old) (print new) (print l))  
    (cons new (deep-subst old new (cdr l)))
   )
   (T
    ;(progn (print "tester3") (print old) (print new) (print l))   
    (cons (car l) (deep-subst old new (cdr l)))
   )
  )
)

; (defun relist-bindings (bindinglist)
; 	(cond
; 	  ((null l) nil)
; 	  (listp (car bindinglist)
; 		)

(defun subst-bindings (exp bindinglist)
  (cond 
    ( (null bindinglist) 
       exp )
    (T 
    	;(progn (print "Test") (print (nth 0 (car bindinglist))) (print (nth 1 (car bindinglist))) )
    	;(deep-subst (nth 0 (car bindinglist)) (nth 1 (car bindinglist)) exp) (subst-bindings exp (cdr bindinglist)))
        (deep-subst (nth 0 bindinglist) (nth 1 bindinglist) (subst-bindings exp (subseq bindinglist 2))))
  		;(deep-subst (nth 0 (car bindinglist)) (nth 1 (car bindinglist)) exp) (subst-bindings exp (cdr bindinglist)))
    ))

(defun simplify-triple(op left-arg right-arg)
  (cond
    ( (and (numberp left-arg) (numberp right-arg))
      (eval (list op left-arg right-arg)))
    ( (and (eq op '+)(eq left-arg 0))
    	right-arg)
    ( (and (eq op '+)(eq right-arg 0))
    	left-arg)
	( (and (eq op '-) (eq right-arg 0))
    	left-arg)
	( (and (eq op '-) (eq right-arg left-arg))
    	0)
	( (and (eq op '*) (or(eq left-arg 0)(eq right-arg 0)))
    	0)
	( (and (eq op '*)(eq left-arg 1))
    	right-arg)
	( (and (eq op '*)(eq right-arg 1))
    	left-arg)
	( (and (eq op '/) (eq right-arg left-arg))
    	1)
	( (and (eq op '/) (eq right-arg 1))
    	left-arg)
	( (and (eq op '/) (eq left-arg 0))
    	0)
	( (and(and (eq op '+)(listp right-arg)) (and (eq (nth 0 right-arg) '-)(eq (nth 1 right-arg) 0)(eq (nth 2 right-arg) left-arg)))
    	0)
    (T
      (list op left-arg right-arg))))




(defun simplify (exp)
  (cond
    ( (listp exp) 
    	(simplify-triple (simplify(nth 0 exp)) (simplify(nth 1 exp)) (simplify(nth 2 exp))))
    (T exp)
  )
)



(defun evalexp (bindings exp) (simplify(subst-bindings exp (reduce #'append bindings))))


 (setq q1 (evalexp '( (x 2) (z 8) ) p1))
 (setq q2 (evalexp '( (x 2) (z 8)) p2))
 (setq q3 (evalexp '( (x 0) ) (mulexp p1 p2)))
 (setq q4 (evalexp '() p5))
