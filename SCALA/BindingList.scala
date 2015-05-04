import scala.io.StdIn;
class BindingList(){
	var list:Array[binding] = new Array[binding](10);
	var count: Int = 0;

	def resetBinding(t: Int): Int = { 
		list = new Array[binding](10);
		return(1);
	}
	def xyz(){
		println("does nothing");
	}
    	
    

	def set(t: ExpressionTree1): ExpressionTree1 = t match {
    	case Plus(l, r)=> Plus(set(l),set(r));
    	case Minus(l, r)=> Minus(set(l),set(r));
    	case Times(l, r)=> Times(set(l),set(r));
    	case Divide(l, r)=> Divide(set(l),set(r));
    	case Var(n) => sub(Var(n));
		case Const(v) => Const(v);
		case _ => Var("problema");

    }
    def sub(t: ExpressionTree1): ExpressionTree1 = {
    	//println("What do");
    	for(c <- list){
    		//if(c != null)println("what the hell " + c.before);
    		if(c != null && c.before == t) return c.after;
    	}
    	//println("What do1");
    	return createBinding(t);
    }
    def createBinding(t: ExpressionTree1): ExpressionTree1 = {
    	print("What do you want to bind " + t.toString + " to: ");
		val input = StdIn.readLine();
		if(input == ""){
			val temp: binding = new binding(t,t);
			list(count) = temp;
			count = count + 1;
			return(t);
		}
		else{
			val temp: binding = new binding(t,Const(input.toInt));
			list(count) = temp;
			count = count + 1;
			return(Const(input.toInt));
		}
    }
    class binding(b: ExpressionTree1,a: ExpressionTree1){
    	var before: ExpressionTree1 = b;
    	var after: ExpressionTree1 = a;

    }
}