class ExpressionTree() {
	var current: String = _;
	var root: Node = _;

	def buildTree(exp: String){
		root = null;
		current = exp;
		root = breakDown();
		println(printTree(root));
		println(eval(root));
	}

	def breakDown() : Node = {
        	if(current.startsWith("Plus(")){
        		println("plus");
        		val temp = new Node("+");
        		current = current.substring(5);
        		val temp1: Node = breakDown()
        		val temp2: Node = breakDown()
        		temp.setLeft(temp1);
        		println("after");
				temp.setRight(temp2);
				println("after2");
				return temp;
        	}
        	else if(current.startsWith("Minus(")){
        		println("minus");
        		val temp = new Node("-");
        		current = current.substring(6);
        		val temp1: Node = breakDown()
        		val temp2: Node = breakDown()
        		temp.setLeft(temp1);
				temp.setRight(temp2);
				return temp;
        	}
        	else if(current.startsWith("Times(")){
        		println("times");
        		val temp = new Node("*");
        		current = current.substring(6);
        		val temp1: Node = breakDown()
        		println("before");
        		val temp2: Node = breakDown()
        		temp.setLeft(temp1);
        		println("doug");
				temp.setRight(temp2);
				println("after33");
				return temp;
        	}
        	else if(current.startsWith("Divide(")){
        		println("div");
        		val temp = new Node("/");
        		current = current.substring(7);
        		val temp1: Node = breakDown()
        		val temp2: Node = breakDown()
        		temp.setLeft(temp1);
				temp.setRight(temp2);
				return temp;
        	}
        	else if(current.startsWith("Var(\"")){
        		println("Var found");
        		var count: Int = 0;
        		current = current.substring(5);
        		while(current.charAt(count) != '\"'){
          			count = count + 1;
        		}
        		val temp: Node = new Node(current.substring(0,count));
        		current = current.substring(count + 2 );
        		println(current);
        		return temp;
        	}
        	else if(current.startsWith("Const(")){
        		println("const found");
        		var count: Int = 0;
        		current = current.substring(6);
        		while(current.charAt(count) != ')'){
          			count = count + 1;
        		}
        		val temp: Node = new Node(current.substring(0,count));
        		current = current.substring(count + 3);
        		return temp;
        	}
        	else if(current.startsWith(",")||current.startsWith(")") || current.startsWith("\"") ){
        		println(", found");
        		current = current.substring(1);
        		current = current.trim();
        		return breakDown();
        	}
        	else{
        		println("Serious problem");
        		return null;
        	}
        	return null;
        	
        }
    def printTree(n:Node) : String = {
    	println("Debug");
    	if(n.isLeaf){
    		println("in if");
    		return n.getKey();
    	}
    	else{
    		return ("(" + printTree(n.getLeft()) + " " + n.getKey() + " " + printTree(n.getRight()) + ")")
    	}
    	
    }
    def eval(n:Node) : String = {
    	if(n.getType.equals("op")){
    		if(n.getKey.equals("+")){
    			if(n.getLeft.getType.equals("Const") && n.getRight.getType.equals("Const")){
    				var l: Int =Integer.parseInt(eval(n.getLeft()));
    				var r: Int =Integer.parseInt(eval(n.getRight()));
    				var t: Int =(l + r);
    				return (t.toString);
    			}
    			else{
    				return (eval(n.getLeft()) + " + " + eval(n.getLeft()));
    			}
    		}
    		else if(n.getKey.equals("-")){
    			if(n.getLeft.getType.equals("Const") && n.getRight.getType.equals("Const")){
    				return (eval(n.getLeft()).toInt - eval(n.getLeft()).toInt).toString;
    			}
    			else{
    				return (eval(n.getLeft()) + " - " + eval(n.getLeft()));
    			}
    		}
    		else if(n.getKey.equals("*")){
    			if(n.getLeft.getType.equals("Const") && n.getRight.getType.equals("Const")){
    				return (eval(n.getLeft()).toInt * eval(n.getLeft()).toInt).toString;
    			}
    			else{
    				return (eval(n.getLeft()) + " * " + eval(n.getLeft()));
    			}
    		}
    		else{
    			if(n.getLeft.getType.equals("Const") && n.getRight.getType.equals("Const")){
    				return (eval(n.getLeft()).toInt / eval(n.getLeft()).toInt).toString;
    			}
    			else{
    				return (eval(n.getLeft()) + " / " + eval(n.getLeft()));
    			}
    		}
    	}
    	else if(n.getType.equals("var")){
    		return n.getKey();
    	}
    	else{
    		return n.getKey();
    	}
    	
    }
}