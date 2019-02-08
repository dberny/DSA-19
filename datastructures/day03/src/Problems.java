import java.util.*;
//import java.util.LinkedList;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        LinkedList<Integer> l = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            int current = A[i];
            while (!l.isEmpty() && current < l.peekLast() && count < k) {
                l.removeLast();
                count++;
            }
            if (l.size() < A.length-k) {
                l.addLast(current);
            }
        }
        return l;
    }


    public static boolean isPalindrome(Node n) {
        Node head = n;
        Node current = n;
        int size = 0;
        while (current!= null) {
            current = current.next;
            size++;
        }
        if (size < 2) {
            return true;
        }
        int count = 1;
        Node head2 = null;
        current = head;

        Node prev = null;
        Node next = null;
        while (count < size/2) {
            next = current.next;


            current.next = prev;
            prev = current;
            current = next;
            count++;
        }
        Node head1 = current;
        head2 = current.next;
        head1.next = prev;
        if (size%2 == 1) {
            head2 = head2.next; // if num of nodes is odd, get to second half
        }


        for (int i = 0; i < size/2; i++) {
            if (head1.val != head2.val) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return true;
    }

    public static String infixToPostfix(String s) {
        String output = "";
        LinkedList<Character> operations = new LinkedList<>();
        for (int i = 0; i < s.length(); i+=2) {
            if (Character.isDigit(s.charAt(i))) {
                output += s.charAt(i);
                output += " ";
            }
            else if (s.charAt(i) == ')') {
                while (!operations.isEmpty()) {
                    output += operations.pop();
                    output += " ";
                }
            }
            else if (s.charAt(i) == '%' || s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*') {
                operations.push(s.charAt(i));
            }
        }
        return output.substring(0, output.length()-1);
    }

}
