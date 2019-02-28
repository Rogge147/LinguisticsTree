package treeofLife;

public class Node {

	private String value;
	private Node parent;

	public Node(String value) {
		this.value = value;
	}

	public Node(String value, Node parent) {
		this(value);
		this.parent = parent;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public String getParentValue() {
		return parent.getValue();
	}

}
