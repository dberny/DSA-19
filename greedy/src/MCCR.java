import java.util.*;


public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            int total = G.numberOfV();
            int[] keys = new int[total];
            boolean[] included = new boolean[total];

            // init values of each key
            for (int i = 1; i < total; i++) {
                keys[i] = Integer.MAX_VALUE;
            }
            keys[0] = 0;

            for (int i = 0; i < total; i++) {
                int min = minKeyIndex(keys, included);
                included[min]=true;
                // find shortest edge
                for (Edge edge : G.edges(min)) {
                    int vert = edge.other(min);
                    if (!included[vert] && edge.weight() < keys[vert]) {
                        keys[vert] = edge.weight();
                    }
                }

            }
            int roadWeight = 0;
            for (int val : keys) {
                roadWeight += val;
            }
            return roadWeight;
        }

        public static int minKeyIndex(int[] keys, boolean[] included) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < keys.length; i++) {
                if (!included[i] && keys[i] < min) {
                    min = keys[i];
                    index = i;
                }
            }
            return index;
        }

    }

