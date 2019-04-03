import java.util.ArrayList;
import java.util.List;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        // TODO
        List<List<Character>> coins = new ArrayList<>();
        List<Character> penny = new ArrayList<>();
        for (int j = 0; j < pennies; j++) {
            penny.add('p');
        }
        List<Character> nickel = new ArrayList<>();
        for (int j = 0; j < nickels; j++) {
            nickel.add('n');
        }
        List<Character> dime = new ArrayList<>();
        for (int j = 0; j < dimes; j++) {
            dime.add('d');
        }
        coins.add(penny);
        coins.add(nickel);
        coins.add(dime);
        char[] clock = new char[hoursInDay];
        for (int i = 0; i < hoursInDay; i++) {
            clock[i] = '.';
        }
//        System.out.println(penny);
//        System.out.println(nickel);
//        System.out.println(dime);

        List<char[]> result = new ArrayList<>();

        coinsHelper(pennies, nickels, dimes, 0, clock, coins, result);
        return result;
    }

    public static void coinsHelper(int pennies, int nickels, int dimes, int hour,
                                   char[] clock, List<List<Character>> coins, List<char[]> solutions) {
        if (areZero(coins)) {
//            System.out.println(clock);
            char[] copy = clock.clone();
            solutions.add(copy);
            return;
        }
        for (int i = 0; i < coins.size(); i++) {
//            printCoins(coins);
//            System.out.println(coins.size());
            List<Character> coin = coins.get(i);
            if (!coin.isEmpty()) {
                char single = coin.remove(0);
                if (clock[hour] == '.') {
                    clock[hour] = single;
                    int add = 0;
//                    System.out.println(single);
//                    System.out.println(clock);
                    switch (single) {
                        case 'p':
                            add = 1;
                            break;
                        case 'n':
                            add = 5;
                            break;
                        case 'd':
                            add = 10;
                            break;
                    }
//                if (coin.isEmpty()) {
//                    coins.remove(i);
//                }
                    coinsHelper(pennies, nickels, dimes, (hour + add) % clock.length, clock, coins, solutions);
//                coin.add(single);
                    clock[hour] = '.';
                }
                coin.add(single);
            }


//            }
//            else {
//                coins.remove(i);
//            }

        }
    }

    public static boolean areZero(List<List<Character>> coins) {
        for (List<Character> coin : coins) {
            if (coin.size() != 0) {
                return false;
            }
        }
        return true;
    }
    public static void printCoins(List<List<Character>> coins) {
        for (List<Character> coin :  coins) {
            System.out.println(coin);
//            for (char single : coin) {
//                System.out.print
//            }
        }
    }
}
