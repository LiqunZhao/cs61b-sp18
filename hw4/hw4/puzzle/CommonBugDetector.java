package hw4.puzzle;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by hug. See https://goo.gl/MVL8up for details on these puzzles.
 */
public class CommonBugDetector {

    public static class CommonBugPuzzleState implements WorldState {
        private char name;

        public CommonBugPuzzleState() {
            name = 's';
        }

        private CommonBugPuzzleState(char n) {
            name = n;
        }

        @Override
        public int estimatedDistanceToGoal() {
            if (name == 'g') {
                return 0;
            }
            if (name == 'x') {
                return 4;
            }
            return 1;
        }

        @Override
        public Iterable<WorldState> neighbors() {
            switch (name) {
                case ('s'):
                    return createWorldStateList(List.of('a', 'x'));
                case ('a'):
                    return createWorldStateList(List.of('b'));
                case ('b'):
                case ('x'):
                    return createWorldStateList(List.of('c'));
                case ('c'):
                    return createWorldStateList(List.of('d'));
                case ('d'):
                    return createWorldStateList(List.of('e'));
                case ('e'):
                    return createWorldStateList(List.of('g'));
                default:
                    return null;
            }
        }

        private static List<WorldState> createWorldStateList(List<Character> lc) {
            List<WorldState> lws = new ArrayList<>();
            for (char c : lc) {
                lws.add(new CommonBugPuzzleState(c));
            }
            return lws;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            CommonBugPuzzleState that = (CommonBugPuzzleState) o;
            return name == that.name;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }


    public static void main(String[] args) {
        CommonBugPuzzleState cbps = new CommonBugPuzzleState();
        Solver s = new Solver(cbps);
        System.out.println("s.moves() should be 5, and your s.moves() is: " + s.moves());

        AlphabetEasyPuzzle aep = new AlphabetEasyPuzzle('a');
        Solver s3 = new Solver(aep);
        System.out.println(
                "s3.cnt should be approximately 25, and your s3.cnt is: "
                        + s3.searchedCount());
    }

}
