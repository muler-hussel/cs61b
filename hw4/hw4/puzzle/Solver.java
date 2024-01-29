package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState ws;
        private int move;
        private SearchNode pre;

        public SearchNode(WorldState ws, int move, SearchNode pre) {
            this.ws = ws;
            this.move = move;
            this.pre = pre;
        }
        @Override
        public int compareTo(SearchNode o) {
            return this.move + this.ws.estimatedDistanceToGoal() - o.move - o.ws.estimatedDistanceToGoal();
        }
    }
    /*Constructor which solves the puzzle, computing
    everything necessary for moves() and solution() to
    not have to solve the problem again. Solves the
    puzzle using the A* algorithm. Assumes a solution exists.*/
    private MinPQ<SearchNode> node = new MinPQ<>();
    private List<WorldState> bms;
    private int totalMove;

    private void getAnswer(SearchNode goal) {
        totalMove = goal.move;
        bms = new ArrayList<>();
        SearchNode p = goal;
        while (p != null) {
            bms.add(p.ws);
            p = p.pre;
        }
    }
    public Solver(WorldState initial) {
        node.insert(new SearchNode(initial, 0, null));
        while (true) {
            SearchNode ret = node.delMin();
            if (ret.ws.isGoal()) {
                getAnswer(ret);
                return;
            } else {
                for (WorldState neighbor : ret.ws.neighbors()) {
                    if (ret.pre == null || !neighbor.equals(ret.pre.ws)) {
                        node.insert(new SearchNode(neighbor, ret.move + 1, ret));
                    }
                }
            }
        }
    }
    /*Returns the minimum number of moves to solve the puzzle starting
    at the initial WorldState.*/
    public int moves() {
        return totalMove;
    }
   /*Returns a sequence of WorldStates from the initial WorldState
    to the solution.*/
    public Iterable<WorldState> solution() {
        List<WorldState> ans = new ArrayList<>();
        for (int i = totalMove; i >= 0; i--) {
            ans.add(bms.get(i));
        }
        return ans;
    }
}
