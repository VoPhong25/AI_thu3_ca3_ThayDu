package lab4;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import lab4.AStarSearchAlgo.NodeComparatorByHn;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparatorByHn());
		Set<Node> explored = new HashSet<Node>();
		frontier.add(root);

		while (!frontier.isEmpty()) {
			Node temp = frontier.poll();
			explored.add(temp);

			if (temp.getLabel().equals(goal)) {
				return temp;
			} else {
				for (Edge e : temp.getChildren()) {
					Node chill = e.getEnd();

					if (!explored.contains(chill) && !frontier.contains(chill)) {
						chill.setG(temp.getG() + e.getWeight());
						chill.setParent(temp);
						frontier.add(chill);
					} else {
						for (Node nodeInFrontier : frontier)
							if (chill.getH() < nodeInFrontier.getH()) {
								chill.setG(temp.getG() + e.getWeight());
								chill.setParent(temp);
								nodeInFrontier = chill;
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

			if (temp.getLabel().equals(goal)) {
				return temp;
			} else {
				for (Edge e : temp.getChildren()) {
					Node chill = e.getEnd();

					if (!explored.contains(chill) && !frontier.contains(chill)) {
						chill.setG(temp.getG() + e.getWeight());
						chill.setParent(temp);
						frontier.add(chill);
					} else {
						for (Node nodeInFrontier : frontier)
							if (chill.getH() < nodeInFrontier.getH()) {
								chill.setG(temp.getG() + e.getWeight());
								chill.setParent(temp);
								nodeInFrontier = chill;
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
			Double h1 = o1.getH() ;
			Double h2 = o2.getH() ;
			int result = h1.compareTo(h2);
			if (result == 0)
				return o1.getLabel().compareTo(o2.getLabel());
			else
				return result;
		}
	}

	@Override
	public boolean isAdmissibleH(Node root, String goal) {
		// TODO Auto-generated method stub
		return false;
	}
}
