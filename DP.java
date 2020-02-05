//solving problem with Dynamic programming
// Golhaye Ghorbat Shomare 9
// Tahere Fahimi - Sina Farahani - Mahdi Saber

import java.util.ArrayList;
import java.util.Scanner;
public class DP {
    private int k;  //number of cities
    private int n;  //number of months
    private static String path="" ;    //the saved path
    private int[][] cost;   // save the cost of steps in each move
    private int[][] relocatingCost= {{0,20,15},{20,0,10},{15,10,0}};
    //if you want to use user's input, uncomment this part
//    private int[][] getRelocatingCost;


    public DP(int startCity,int startMonth){
        this.k = startCity;
        this.n = startMonth;

//        String[][] inputs = new String[k][n+1];
        String[][] inputs = {{"NY","8", "3", "10", "43", "15", "48", "5", "40", "20", "30", "28", "24"},
                {"LA", "18", "1", "35", "18", "10", "19", "18", "10", "8", "5" ,"8" ,"20"},
                {"DEN", "40", "5", "8", "13", "21", "12", "4", "27", "25", "10", "5", "15"}};

        //if you want to use user's data, uncomment this part...
//        relocatingCost = new int[k][k];
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter name of cities :");
//        for (int i = 0; i < k; i++) {
//            for (int j = 0; j <= n; j++) {
//                inputs[i][j] = scanner.next();
//            }
//        }
//        System.out.println("Enter relocation Cost :");
        for (int i = 0; i < k ; i++) {
            for (int j = 0; j < k ; j++) {
                if (i != j) {
                    System.out.printf("F[" + i + j + "] : ");
                    System.out.println(relocatingCost[i][j]);
//                    relocatingCost[i][j] = scanner.nextInt();
                } else
                    // the cost of relocating from one city to that city is 0!
                    relocatingCost[i][j] =0 ;

            }
        }
        path = "";

        cost = new int[k][n];   //put cost of the operating in each city as the known value
        for (int i = 0; i < k; i++)
            cost[i][0] = Integer.valueOf(inputs[i][1]);


        //search for the best answer recursively
        for (int month = 1;  month< n; month++) {
            for (int city = 0; city < k; city++) {
                cost[city][month] = Integer.valueOf(inputs[city][month+1]) + findMinWay(city, month).get(0);
                path = path +"   "+ inputs[findMinWay(city, month).get(1)][0] ;
            }
        }

        int minCost = cost[k - 1][n - 1];
        for (int p = 0 ; p < k ; p++){
            if (cost[p][n-1] < minCost){
                minCost = cost[p][n-1];
            }
        }

        System.out.println("the minimum cost is : ");
        System.out.println(minCost);
        System.out.println("the path to the minimum cost is :");
        System.out.println(path);
    }


    public ArrayList<Integer> findMinWay(int i , int j){
        ArrayList<Integer> out = new ArrayList<>();
        int [] arr = new int[k];
        int z = 0;
        int x = 0;
        int min = cost[z][j-1]+ relocatingCost[z][i];
        for ( z = 0; z < k; z++){
            arr[z] = cost[z][j-1]+ relocatingCost[z][i];
            if (arr[z] < min) {
                min = arr[z];
                x = z;
            }
        }
        out.add(min);
        out.add(x);
        return out;
    }

    public static void main(String[] args) {
        new DP(3,12);
    }
}

