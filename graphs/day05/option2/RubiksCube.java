import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


// this is our implementation of a rubiks cube. It is your job to use A* or some other search algorithm to write a
// solve() function
public class RubiksCube {

    private BitSet cube;
<<<<<<< HEAD
    private char last;
    private char[] rotats = {'u', 'U', 'R', 'r', 'f', 'F'};

    private class State {
        // Each state needs to keep track of its cost and the previous state
        private RubiksCube cube;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;
        private char last;

        public State(RubiksCube cube, int moves, State prev, char last) {
            this.cube = cube;
            this.moves = moves;
            this.prev = prev;
            this.last = last;
            cost = this.cube.manhattan() + this.moves;
        }

        public int compareTo(State s) {
            return this.cost - s.cost;
        }
    }
    private HashMap<Integer, HashMap<Integer, Integer>> distances;
=======
>>>>>>> 0e429a07dec467f732cb3517dcda0d19a511319c

    // initialize a solved rubiks cube
    public RubiksCube() {
        // 24 colors to store, each takes 3 bits
        cube = new BitSet(24 * 3);
        for (int side = 0; side < 6; side++) {
            for (int i = 0; i < 4; i++) {
                setColor(side * 4 + i, side);
            }
        }
    }

<<<<<<< HEAD
    public void populate_hm(){
        {0,19,22,};

        distances = new HashMap<>();
        for(int i = 0; i < 24; i++){
            HashMap<Integer, Integer> curr_distances = new HashMap<>()
            for(int j = 0; j < 24;j++){

            }
        distances.put(i, curr_distances);

        }
    }

=======
>>>>>>> 0e429a07dec467f732cb3517dcda0d19a511319c
    // initialize a rubiks cube with the input bitset
    private RubiksCube(BitSet s) {
        cube = (BitSet) s.clone();
    }

    // creates a copy of the rubics cube
    public RubiksCube(RubiksCube r) {
        cube = (BitSet) r.cube.clone();
    }

    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;
        return other.cube.equals(cube);
    }

    /**
     * return a hashCode for this rubik's cube.
     *
     * Your hashCode must follow this specification:
     *   if A.equals(B), then A.hashCode() == B.hashCode()
     *
     * Note that this does NOT mean:
     *   if A.hashCode() == B.hashCode(), then A.equals(B)
     */
    @Override
    public int hashCode() {
        return cube.hashCode();
    }

    public boolean isSolved() {
        return this.equals(new RubiksCube());
    }


    // takes in 3 bits where bitset.get(0) is the MSB, returns the corresponding int
    private static int bitsetToInt(BitSet s) {
        int i = 0;
        if (s.get(0)) i |= 4;
        if (s.get(1)) i |= 2;
        if (s.get(2)) i |= 1;
        return i;
    }

    // takes in a number 0-5, returns a length-3 bitset, where bitset.get(0) is the MSB
    private static BitSet intToBitset(int i) {
        BitSet s = new BitSet(3);
        if (i % 2 == 1) s.set(2, true);
        i /= 2;
        if (i % 2 == 1) s.set(1, true);
        i /= 2;
        if (i % 2 == 1) s.set(0, true);
        return s;
    }

    // index from 0-23, color from 0-5
    private void setColor(int index, int number) {
        BitSet colorBitset = intToBitset(number);
        for (int i = 0; i < 3; i++)
            cube.set(index * 3 + i, colorBitset.get(i));
    }


    // index from 0-23, returns a number from 0-5
    private int getColor(int index) {
        return bitsetToInt(cube.get(index * 3, (index + 1) * 3));
    }

    // given a list of rotations, return a rubik's cube with the rotations applied
    public RubiksCube rotate(List<Character> c) {
        RubiksCube rub = this;
        for (char r : c) {
            rub = rub.rotate(r);
        }
        return rub;
    }

    public Iterable<RubiksCube> neighbors() {
        LinkedList<RubiksCube> cubes = new LinkedList<>();
        RubiksCube newcube1 =  new RubiksCube(cube);
        newcube1.rotate('U');
        newcube1.last = 'U';
        cubes.add(newcube1);
        RubiksCube newcube2 = new RubiksCube(cube);
        newcube2.rotate('u');
        newcube2.last = 'u';
        cubes.add(newcube2);
        RubiksCube newcube3 = new RubiksCube(cube);
        newcube3.rotate('R');
        newcube3.last = 'R';
        cubes.add(newcube3);
        RubiksCube newcube4 = new RubiksCube(cube);
        newcube4.rotate('r');
        newcube4.last = 'r';
        cubes.add(newcube4);
        RubiksCube newcube5 = new RubiksCube(cube);
        newcube5.rotate('F');
        newcube5.last = 'F';
        cubes.add(newcube5);
        RubiksCube newcube6 = new RubiksCube(cube);
        newcube6.rotate('f');
        newcube6.last = 'f';
        cubes.add(newcube6);
        return cubes;
    }


    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        int[] faceFrom = null;
        int[] faceTo = null;
        int[] sidesFrom = null;
        int[] sidesTo = null;
        // colors move from the 'from' variable to the 'to' variable
        switch (c) {
            case 'u': // clockwise
            case 'U': // counterclockwise
                faceFrom = new int[]{0, 1, 2, 3};
                faceTo = new int[]{1, 2, 3, 0};
                sidesFrom = new int[]{4, 5, 8, 9, 17, 16, 21, 20};
                sidesTo = new int[]{21, 20, 4, 5, 8, 9, 17, 16};
                break;
            case 'r':
            case 'R':
                faceFrom = new int[]{8, 9, 10, 11};
                faceTo = new int[]{9, 10, 11, 8};
                sidesFrom = new int[]{6, 5, 2, 1, 17, 18, 13, 14};
                sidesTo = new int[]{2, 1, 17, 18, 13, 14, 6, 5};
                break;
            case 'f':
            case 'F':
                faceFrom = new int[]{4, 5, 6, 7};
                faceTo = new int[]{5, 6, 7, 4};
                sidesFrom = new int[]{3, 2, 8, 11, 14, 15, 23, 20};
                sidesTo = new int[]{8, 11, 14, 15, 23, 20, 3, 2};
                break;
            default:
                System.out.println(c);
                assert false;
        }
        // if performing a counter-clockwise rotation, swap from and to
        if (Character.isUpperCase(c)) {
            int[] temp;
            temp = faceFrom;
            faceFrom = faceTo;
            faceTo = temp;
            temp = sidesFrom;
            sidesFrom = sidesTo;
            sidesTo = temp;
        }
        RubiksCube res = new RubiksCube(cube);
        for (int i = 0; i < faceFrom.length; i++) res.setColor(faceTo[i], this.getColor(faceFrom[i]));
        for (int i = 0; i < sidesFrom.length; i++) res.setColor(sidesTo[i], this.getColor(sidesFrom[i]));
        return res;
    }

    // returns a random scrambled rubik's cube by applying random rotations
    public static RubiksCube scrambledCube(int numTurns) {
        RubiksCube r = new RubiksCube();
        char[] listTurns = getScramble(numTurns);
        for (int i = 0; i < numTurns; i++) {
            r= r.rotate(listTurns[i]);
        }
        return r;
    }

    public static char[] getScramble(int size){
        char[] listTurns = new char[size];
        for (int i = 0; i < size; i++) {
            switch (ThreadLocalRandom.current().nextInt(0, 6)) {
                case 0:
                    listTurns[i] = 'u';
                    break;
                case 1:
                    listTurns[i] = 'U';
                    break;
                case 2:
                    listTurns[i] = 'r';
                    break;
                case 3:
                    listTurns[i] = 'R';
                    break;
                case 4:
                    listTurns[i] = 'f';
                    break;
                case 5:
                    listTurns[i] = 'F';
                    break;
            }
        }
        return listTurns;
    }

    public int manhattan() {
        int cost = 0;
        for (int i = 0; i < 24; i++) {
            int color = getColor(i);
            int correct = i/4;
            if (color == correct) {
                continue;
            }
            if (Math.abs(color-correct) == 3) {
                cost += 2;
            }
            else {
                cost += 1;
            }
        }
        return cost/8;
    }



    // return the list of rotations needed to solve a rubik's cube
    public List<Character> solve() {
        // TODO
        HashMap<RubiksCube, State> visited = new HashMap<>();
        PriorityQueue<State> states = new PriorityQueue<>(State::compareTo);
        State initState = new State(new RubiksCube(cube), 0, null, '-');
        states.add(initState);
        visited.put(initState.cube, initState);
        LinkedList<Character> moves = new LinkedList<>();

        while(!states.isEmpty()) {
//            System.out.println(states);
            State current = states.remove();
            if (current.cube.isSolved()) {
                State solutionState = visited.get(current.cube);


                moves.addFirst(solutionState.last);
                while(solutionState.prev != null) {
//                    System.out.println(solutionState.cost);

                    solutionState = visited.get(solutionState.prev.cube);
                    moves.addFirst(solutionState.last);
                }

                moves.remove(0);
//                System.out.println(moves);
                return moves;
            }
//            Iterable<RubiksCube> neighbours = current.cube.neighbors();
            for (char rot : rotats) {
                RubiksCube newcube = new RubiksCube(current.cube);
                newcube = newcube.rotate(rot);
                State state = new State(new RubiksCube(newcube), current.moves+1, current, rot);
//                if (visited.containsKey(state.cube)) {
//                    if (visited.get(newcube).moves > state.moves) {
//                        visited.put(newcube, state);
//                    }
//                }
                if (!visited.containsKey(state.cube)) {
                    visited.put(state.cube, state);
                    states.add(state);
                }
            }
        }
        return new ArrayList<>();
    }
}