package lab4;

public class TestAStar {
	public static void main(String[] args) {
		Node s = new Node("S", 6);
		Node b = new Node("B", 4);
		Node a = new Node("A", 4);
		Node c = new Node("C", 4);
		Node d = new Node("D", 3.5);
		Node e = new Node("E", 1);
		Node f = new Node("F", 1);
		Node g = new Node("G", 0);
		
		s.addEdge(b, 3);
		s.addEdge(a, 2);
		a.addEdge(c, 3);
		b.addEdge(d, 3);
		b.addEdge(c, 1);
		c.addEdge(e, 3);
		c.addEdge(d, 1);
		d.addEdge(f, 2);
		f.addEdge(g, 1);
		e.addEdge(g, 2);
		
//		IInformedSearchAlgo aStar = new AStarSearchAlgo();
//		Node res = aStar.execute(s, g.getLabel());
//		System.out.println(NodeUtils.printPath(res));
		
//		IInformedSearchAlgo aStar1 = new AStarSearchAlgo();
//		Node res1 = aStar1.execute(s, a.getLabel(), g.getLabel());
//		System.out.println(NodeUtils.printPath(res1));
//		
		
//    	IInformedSearchAlgo greedy = new GreedyBestFirstSearchAlgo();
//		Node ree = greedy.execute(s, g.getLabel());
//		System.out.println(NodeUtils.printPath(ree));
		
//		IInformedSearchAlgo greedy1 = new GreedyBestFirstSearchAlgo();
//		Node ree1 = greedy1.execute(s, a.getLabel(), g.getLabel());
//		System.out.println(NodeUtils.printPath(ree1));
//		
		IInformedSearchAlgo aStar2 = new GreedyBestFirstSearchAlgo();
		boolean ree2 = aStar2.isAdmissibleH(s,  g.getLabel());
		System.out.println((ree2));
	}
}
