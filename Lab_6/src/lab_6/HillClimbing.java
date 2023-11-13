package lab_6;

public class HillClimbing {
	int stepClimbed = 0;
	int randomRestarts = 0;

	public Node execute(Node initialState) {
		Node neighbour = null;
		if (initialState.getH() == 0) {
			return initialState;
		}
		stepClimbed++;
		while (initialState.getH() != 0) {
			neighbour = findBestSub(initialState);
			if (neighbour.getH() >= initialState.getH()) {
				stepClimbed = 0;
				return initialState;
			}
			initialState = neighbour;
		}
		return initialState;
	}

	public Node findBestSub(Node initialState) {
		int i = Integer.MAX_VALUE;
		Node state = new Node();
		for (Node child : initialState.generateAllCandidates()) {
			if (child.getH() < i) {
				i = child.getH();
				state = child;
			}
		}
		return state;
	}

	public Node executeHillClimbingWithRandomRestart(Node initialState) {
		Node state = execute(initialState);
		if (state.getH() == 0) {
			return state;
		}
		while (state.getH() != 0) {
			randomRestarts++;
			state.generateBoard();
			state = execute(state);
		}
		return state;
	}
}
