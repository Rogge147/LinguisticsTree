package treeofLife;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Builder {

	private static final String ROOT_IDENTIFIER = "0";

	private static String fileLocation = "C:\\\\Users\\\\uyrca0a\\\\Documents\\\\workspace-sts-3.9.4.RELEASE\\\\JankyLilJavaTools\\\\src\\\\treeofLife\\\\chunky.txt";

	public static void main(String... args) {
		List<String[]> data = processInput(fileLocation);
		Node root = findRoot(data);
		Map<Node, List<Node>> nodeMap = createNodeMap(root);
		addChildren(data, nodeMap, root);
	}

	private static Map<Node, List<Node>> createNodeMap(Node root) {
		Map<Node, List<Node>> nodeMap = new HashMap<Node, List<Node>>();

		List<Node> rootNodes = new ArrayList<>();
		rootNodes.add(root);
		nodeMap.put(null, rootNodes);

		return nodeMap;
	}

	private static List<String[]> processInput(String fileLocation) {
		List<String[]> lines = new ArrayList<String[]>();
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileLocation);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line.split("\\s"));
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO If there are duplicates, we might need a Node.count
		return lines;
	}

	private static Node findRoot(List<String[]> data) {
		for (String[] valuePair : data) {
			if (ROOT_IDENTIFIER.equals(valuePair[1])) {
				Node root = new Node(valuePair[0]);
				return root;
			}
		}
		// If we didn't find the root, throw an exception.
		throw new IllegalArgumentException("No root pair could be found within the given data.");
	}

	private static void addChildren(List<String[]> data, Map<Node, List<Node>> nodeMap, Node parent) {
		StringBuilder out = new StringBuilder();

		Node child;
		for (String[] valuePair : data) {

			if (parent.getValue().equals(valuePair[1])) {
				String childValue = valuePair[0];
				out.append(childValue + " ");

				child = new Node(valuePair[0], parent);
				nodeMap.get(parent).add(child);
				data.remove(valuePair);
				if (!data.isEmpty()) {
					addChildren(data, nodeMap, child);
				} else {
					System.out.println("All done!");
				}
			}

		}
	}

}
