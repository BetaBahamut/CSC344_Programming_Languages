import scala.io.StdIn;

object TestDriver2 {
    var current: String = _;
    def main(args: Array[String]) {
      var bindingSet : BindingList = new BindingList();
   	  val tree : ExpressionTree1 = Plus(Plus(Const(7),Const(7)),Plus(Var("x"),Const(7)));
   	  val tree2: ExpressionTree1 = buildTree("Plus(Plus(Const(7),Const(7)),Plus(Const(7),Const(7)))");
   	  var temp1 : ExpressionTree1 = null;
   	  // var tree3: ExpressionTree1 = buildTree("Times(Plus(Var(\"a\"), Var(\"b\")), Minus(Var(\"a\"), Const(1)))");
   	  // val tree4: ExpressionTree1 = Plus(Plus(Const(7),Const(7)),Plus(Var("x"),Const(0)));
   	  // var tree5: ExpressionTree1 = Divide(Minus(Var("x"),Const(0)),Plus(Const(7),Const(0)));
   	  // var tree6: ExpressionTree1 = Times(Minus(Var("x"),Const(0)),Divide(Const(0),Var("g")));
   	  // var tree7: ExpressionTree1 = null
   	  //tree2 = buildTree("Plus(Plus(Const(7),Const(7)),Plus(Const(7),Const(7)))");
   	  //tree5 = opt(tree5);
   	  //println(eval(tree5));
   	  //tree6 = opt(tree6);
   	  //tree3 = bindingSet.set(tree3);
   	  //println(tree3);
   	 //println(eval(tree3));


   	 // X = 2 Z = 8 Y = Y
   	 var p1 = Plus(Var("x"),Times(Var("x"),Minus(Var("y"),Divide(Var("z"),Const(2)))));
   	/* println(p1);
   	 temp1 = opt(bindingSet.set(p1));
   	 println(temp1);
   	 println(rootEval(temp1));
   	 bindingSet = new BindingList();*/

   	 // X = 2 Z = 8
   	 var p2 = Plus(Minus(Var("z"),Const(2)),Times(Var("x"),Const(5)));
   	 /*println(p2);
   	 temp1 = opt(bindingSet.set(p2));
   	 println(rootEval(temp1));
   	 bindingSet = new BindingList();*/

   	 // println(rootEval(opt(bindingSet.set(Times(Plus(Var("a"), Var("b")), Minus(Var("a"), Const(1)))))))
   	 // println(rootEval(opt(bindingSet.set(tree))));

   	 var p3 = Plus(Var("b"),Minus(Var("a"),Var("b")));
   	 var p4 = Times(p1,p2);
   	 //println(rootEval(opt(bindingSet.set(p4))))
   	/* println(p3);
   	 temp1 = opt(bindingSet.set(p3));
   	 println(temp1);
   	 println(rootEval(temp1));
   	 bindingSet = new BindingList();*/

   	 // X = 0
   	 // temp1 = Times(p1,p2);
   	 // temp1 = opt(bindingSet.set(temp1));
   	 // println(rootEval(temp1));
   	 // bindingSet = new BindingList();

   	 var sentinel: Boolean = true;
   	 //bindingSet.resetBinding(3);
   	 while(sentinel){
   	 	bindingSet = new BindingList();
   	 	print("Enter an Equation: ");
   		var input = StdIn.readLine();
   		println();
   		if(input != "exit" && input != ""){
   			var pre = bindingSet.set(buildTree(input));
   			var temp = rootEval(opt(pre))
      		println("\n\n" + pre + " = \n" + temp + "\n\n");     		
      	}
      	else sentinel = false;
   	 }
   	  // println(tree6);
   	  // println(eval(opt(bindingSet.set(Plus(Var("j"),Minus(Const(0),Var("j")))))));
   	  //println(Var("n") == Var("n"));
   	  

   	  


   	  
      
      

    }
    def rootEval(t: ExpressionTree1): String = t match {
    	case Plus(l, r)=> 
    	try{ (eval(l).toInt + eval(r).toInt).toString;}
    	catch{case e: NumberFormatException => eval(l) + " + " + eval(r)}

    	case Minus(l, r)=> 
    	try{ (eval(l).toInt - eval(r).toInt).toString;}
    	catch{case e: NumberFormatException => eval(l) + " - " + eval(r)}

    	case Times(l, r)=> 
    	try{ (eval(l).toInt * eval(r).toInt).toString;}
    	catch{case e: NumberFormatException => eval(l) + " * " + eval(r)}

    	case Divide(l, r)=> 
    	try{ (eval(l).toInt / eval(r).toInt).toString;}
    	catch{case e: NumberFormatException => eval(l) + " / " + eval(r)}
   		
		case Var(n) => n.toString
		case Const(v) => v.toString

    }
    def eval(t: ExpressionTree1): String = t match {
    	case Plus(l, r)=> 
    	try{ (eval(l).toInt + eval(r).toInt).toString;}
    	catch{case e: NumberFormatException => "(" + eval(l) + " + " + eval(r) + ")"}

    	case Minus(l, r)=> 
    	try{ (eval(l).toInt - eval(r).toInt).toString;}
    	catch{case e: NumberFormatException => "(" + eval(l) + " - " + eval(r) + ")"}

    	case Times(l, r)=> 
    	try{ (eval(l).toInt * eval(r).toInt).toString;}
    	catch{case e: NumberFormatException => "(" + eval(l) + " * " + eval(r) + ")"}

    	case Divide(l, r)=> 
    	try{ (eval(l).toInt / eval(r).toInt).toString;}
    	catch{case e: NumberFormatException => "(" + eval(l) + " / " + eval(r) + ")"}
   		
		case Var(n) => n.toString
		case Const(v) => v.toString

    }
    def buildTree(exp: String) : ExpressionTree1 = {
		current = exp;
		return breakDown();

	}

	def breakDown() : ExpressionTree1 = {
        	if(current.startsWith("Plus(")){
        		current = current.substring(5);
        		val temp : ExpressionTree1 = Plus(breakDown(),breakDown())
				return temp;
        	}
        	else if(current.startsWith("Minus(")){
        		current = current.substring(6);
        		val temp : ExpressionTree1 = Minus(breakDown(),breakDown())
				return temp;
        	}
        	else if(current.startsWith("Times(")){
        		current = current.substring(6);
        		val temp : ExpressionTree1 = Times(breakDown(),breakDown())
				return temp;
        	}
        	else if(current.startsWith("Divide(")){
        		current = current.substring(7);
        		val temp : ExpressionTree1 = Divide(breakDown(),breakDown())
				return temp;
        	}
        	else if(current.startsWith("Var(\"")){
        		var count: Int = 0;
        		current = current.substring(5);
        		while(current.charAt(count) != '\"'){
          			count = count + 1;
        		}
        		val temp: ExpressionTree1 = Var(current.substring(0,count));
        		current = current.substring(count + 2 );
        		return temp;
        	}
        	else if(current.startsWith("Const(")){
        		var count: Int = 0;
        		current = current.substring(6);
        		while(current.charAt(count) != ')'){
          			count = count + 1;
        		}
        		val temp : ExpressionTree1 = Const(current.substring(0,count).toInt);
        		current = current.substring(count + 2);
        		return temp;
        	}
        	else if(current.startsWith(",")||current.startsWith(")") ){
        		current = current.substring(1);
        		current = current.trim();
        		return breakDown();
        	}
        	else{
        		println(current);
        		println("Serious problem");
        		return null;
        	}
        	return null;
        	
        }
    def opt(t: ExpressionTree1) : ExpressionTree1 = t match{
    	case Const(v) => Const(v);
    	case Var(n) => Var(n);
    	case Plus(l, r)=> optimizer(Plus(opt(l),opt(r)));
    	case Minus(l, r)=>optimizer(Minus(opt(l),opt(r)));
    	case Times(l, r)=>optimizer(Times(opt(l),opt(r)));
    	case Divide(l, r)=>optimizer(Divide(opt(l),opt(r)));
    }
    def optimizer(t : ExpressionTree1) : ExpressionTree1 = t match{
    	case Plus(Const(l), Const(r)) => Const(l+r);
    	case Minus(Const(l), Const(r)) => Const(l-r);
    	case Times(Const(l), Const(r)) => Const(l*r);
    	case Divide(Const(l), Const(r)) => Const(l/r);
    	case Plus(l, Const(0)) => l;
    	case Plus(Const(0),r) => r;
    	case Minus(l,Const(0)) => l;
    	case Minus(l,r) =>
    		if(l == r)Const(0);
    		else Minus(l,r);
    	// case Minus(Var(n),Var(j)) =>
    	// 	if(n.equals(j))Const(0);
    	// 	else Minus(Var(n),Var(j));
    	case Plus(Var(n),Minus(Const(0),Var(j))) =>
    		if(n.equals(j))Const(0);
    		else Minus(Var(n),Minus(Const(0),Var(j)));
    	case Times(l,Const(1)) => l;
    	case Times(Const(1),r) => r;
    	case Times(l,Const(0)) => Const(0);
    	case Times(Const(0),r) => Const(0);
    	case Divide(Const(0),r) =>Const(0);
    	case Divide(l,Const(1)) => l;
    	case Divide(l,r) =>
    		if(l==r)Const(1);
    		else Divide(l,r);
    	
    	/*case Divide(Var(n),Var(j)) =>
    		if(n.equals(j))Const(1);
    		else Divide(Var(n),Var(j));*/
    	case _ => t;
    	
    }
    	



    	// if(l == Const(0)) r;
    	// else if(r == Const(0)){ l;}
    	// //else if(false);
    	// else Plus(l,r);


    
}