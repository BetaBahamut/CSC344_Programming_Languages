class Node(val data: String){
	var key: String = data;
	var left: Node = null;
	var right: Node = null;
	var typeOf: String = null;

	def setLeft(l: Node){
		left = l;
	}
	def setRight(r: Node){
		right = r;
	}
	def getKey(): String = {
		return key;
	}
	def setKey(k:String){
		key = k;
	}
	def getLeft(): Node = {
		return left;
	}
	def getRight(): Node = {
		return right;
	}
	def isLeaf(): Boolean = {
		if(left ==  null || right == null){
			return true;
		}
		else{
			return false;
		}
	}
	def setType(t:String){
		typeOf = t;
	}
	def getType(): String = {
		return typeOf;
	}
}