package lab4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import lab4.GreedyBestFirstSearchAlgo.NodeComparatorByHn;

public class AStarSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
		Set<Node> explored = new HashSet<Node>();
		frontier.add(root);

		while (!frontier.isEmpty()) {
			Node temp = frontier.poll();
			explored.add(temp);

			if (temp.getLabel().equals(goal))
				return temp;
			else {
				for (Edge e : temp.getChildren()) {
					Node chill = e.getEnd();

					if (!explored.contains(chill) && !frontier.contains(chill)) {
						chill.setG(temp.getG() + e.getWeight());
						chill.setParent(temp);
						frontier.add(chill);
					} else {
						double a1 = chill.getH() + temp.getG() + e.getWeight();
						for (Node nodeInFrontier : frontier) {
							double a2 = nodeInFrontier.getH() + nodeInFrontier.getG();
							if (a1 < a2) {
								chill.setG(temp.getG() + e.getWeight());
								chill.setParent(temp);
								nodeInFrontier = chill;
							}
						}
					}
				}
			}
		}
		return null;

	}

	@Override
	public Node execute(Node root, String start, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
		Set<Node> explored = new HashSet<Node>();
		frontier.add(root);

		while (!frontier.isEmpty()) {
			Node temp = frontier.poll();
			explored.add(temp);

			if (temp.getLabel().equals(goal))
				return temp;
			else {
				for (Edge e : temp.getChildren()) {
					Node chill = e.getEnd();

					if (!explored.contains(chill) && !frontier.contains(chill)) {
						frontier.add(chill);
						chill.setG(temp.getG() + e.getWeight());
						chill.setParent(temp);
					} else {
						double a1 = chill.getH() + temp.getG() + e.getWeight();
						for (Node nodeInFrontier : frontier) {
							double a2 = nodeInFrontier.getH() + nodeInFrontier.getG();
							if (a1 < a2) {
								chill.setG(temp.getG() + e.getWeight());
								chill.setParent(temp);
								nodeInFrontier = chill;
							}
						}
					}
				}
				for (Edge e : temp.getChildren()) {
					Node chill = e.getEnd();
					if (chill.getLabel() == start) {
						chill.setParent(null);
						frontier.clear();
						explored.clear();
						frontier.add(chill);
					}
				}
			}
		}

		return null;

	}

	class NodeComparatorByHn implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			Double h1 = o1.getH() + o1.getG();
			Double h2 = o2.getH() + o2.getG();
			int result = h1.compareTo(h2);
			if (result == 0)
				return o1.getLabel().compareTo(o2.getLabel());
			else
				return result;
		}
	}

	@Override
	public boolean isAdmissibleH(Node root, String goal) {
		boolean result;
		HashSet<Node> nodeList = findNodeList(root);
		for (Node chill : nodeList) {
			Node temp = execute(chill, goal);
			List<String> end = new ArrayList<String>();
			end.add(temp.getLabel());
			Node tmp;
			while ((tmp = temp.getParent()) != null) {

				end.add(tmp.getLabel());
				temp = tmp;
			}
			result = chill.getH() <= (end.size() - 2);
			if (result == false)
				return false;
		}
		return true;
	}

	public HashSet<Node> findNodeList(Node root) {
		HashSet<Node> nodeList = new HashSet<>();
		nodeList.add(root);
		Node start = root;
		List<Node> children = start.getChildrenNodes();
		for (Node chill : children) {
		}
		if (!children.isEmpty()) {
			nodeList.addAll(children);
			for (Node chill : children) {
				findNodeList(chill);
			}
		}
		return nodeList;

	}
}
