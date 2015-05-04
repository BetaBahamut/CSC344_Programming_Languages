import scala.io.StdIn;

object TestDriver {
   def main(args: Array[String]) {
   	  val tree = new ExpressionTree();
   	  //tree.buildTree("Plus(Var(j),Var(x))");
   	  tree.buildTree("Times(Plus(Var(\"a\"), Var(\"b\")), Minus(Var(\"a\"), Const(1)))");
   	  tree.buildTree("Times(Plus(Var(\"a\"), Var(\"b\")), Minus(Var(\"a\"), Minus(Var(\"a\"), Var(\"Y\")))");
      println("Input The expression you want evaluated: ");

      //var input = StdIn.readLine();
      //println("Input was: " + input);
      
      

    }
}