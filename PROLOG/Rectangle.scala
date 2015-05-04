class Rectangle(w: Int, l: Int){
	var width: Int = 0;
	var length: Int = 0;

	def area(): Int =
    	width * length;


}
class Square(s: Int) extends Rectangle(s, s){
	def squareArea(): Int =
    	length * 2;

}


