import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int h = Integer.parseInt(br.readLine());
        String[] nodes = br.readLine().split(" ");
        int n = Integer.parseInt(br.readLine());
        String[] arr1 = br.readLine().split(" ");
        long[] arr = new long[arr1.length];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Long.parseLong(arr1[i]);
        }
        ArrayList<Node> all = new ArrayList<>();
        for(int i = 1; all.size()<n&& i <= (int)(Math.pow(2, h))-1; i++) {
            if(nodes[i-1].equals("_")) {
                all.add(new Node(i,h));
            }
        }
        Collections.sort(all);
        Arrays.sort(arr);
        for(int i = 0; i < n; i++) {
            nodes[all.get(i).x-1] = ""+arr[i];
        }
        String print = Arrays.toString(nodes).replaceAll(",","");
        System.out.println(print.substring(1,print.length()-1));
    }
}
class Node implements Comparable<Node> {
    int x;
    int[] vals;
    public Node(int ind,int height) {
        x = ind;
        vals = new int[height];
        int row = (int) (Math.log(ind)/Math.log(2))+1;
        int hx = ind - (int) Math.pow(2, row-1);
        for(int i = row-1; i > 0;i--) {
            if(hx % 2 == 0) {
                vals[i] = -1;
            }
            else {
                vals[i] = 1;
            }
            hx /=2;
        }
    }
    public int compareTo (Node o) {
        for(int i = 0 ; i < vals.length; i++) {
            if(this.vals[i] < o.vals[i]) {
                return -1;
            }
            if(this.vals[i] > o.vals[i]) {
                return 1;
            }
        }
        return 0;
    }
    public String toString() {
        return Arrays.toString(vals);
    }
}
