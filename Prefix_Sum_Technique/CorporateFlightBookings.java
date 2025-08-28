package Prefix_Sum_Technique;

/**
 *
 Code
 1109. Corporate Flight Bookings
 Medium
 Topics
 premium lock icon
 Companies
 There are n flights that are labeled from 1 to n.

 You are given an array of flight bookings bookings, where bookings[i] = [firsti, lasti, seatsi] represents a booking for flights firsti through lasti (inclusive) with seatsi seats reserved for each flight in the range.

 Return an array answer of length n, where answer[i] is the total number of seats reserved for flight i.



 Example 1:

 Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 Output: [10,55,45,25,25]
 Explanation:
 Flight labels:        1   2   3   4   5
 Booking 1 reserved:  10  10
 Booking 2 reserved:      20  20
 Booking 3 reserved:      25  25  25  25
 Total seats:         10  55  45  25  25
 Hence, answer = [10,55,45,25,25]
 Example 2:

 Input: bookings = [[1,2,10],[2,2,15]], n = 2
 Output: [10,25]
 Explanation:
 Flight labels:        1   2
 Booking 1 reserved:  10  10
 Booking 2 reserved:      15
 Total seats:         10  25
 Hence, answer = [10,25]



 Constraints:

 1 <= n <= 2 * 104
 1 <= bookings.length <= 2 * 104
 bookings[i].length == 3
 1 <= firsti <= lasti <= n
 1 <= seatsi <= 10
 */
public class CorporateFlightBookings {
    public static void main(String[] args) {

    }
    public int[] corpFlightBookings(int[][] bookings, int n) {
        /*
        ALGO WE TRAVERSE THE BOOKINGS ARRAY WHICH IS 2D ARRAY FROM THERE WE FIND OUT THE FIRST INDEX AND LAST INDEX FROM Bookings[i][0] and lastindex form Bookings[i][1] and number of seats from booking[i][2]
        we mark ans[firstindex-1]+= boooking[i][0] and also mark last index +1  of that booking with -ve value of the seat
        we are doing because when we will do prefix sum then it will include all the seats and that last index+1 doesnot contain that number of seats so in the first itself we mark that index with the -ve of the seat value so that it doesnot get included during the prefix sum
        what a algo man : Thnink like that for other questions also
        */
        /* Optimize approach  */
        int [] ans = new int [n];
        int firstindex =0;
        int lastindex =0;
        for(int i =0; i<bookings.length;i++){
            firstindex = bookings[i][0];
            lastindex = bookings[i][1];
            ans[firstindex-1]+=bookings[i][2];
            if(lastindex!=n)ans[lastindex]-=bookings[i][2];
        }
        // now just used prefix sum
        for(int i =1; i<n;i++)ans[i]+=ans[i-1];
        return ans;
    /*
    T.C =O(M+N)
    S.C =O(1)
    */

    }








    public int [] brut_force(int [][] bookings , int n){
        /* Algo :Written by me brut force for each booking we find the row and column and traverse the answer array and fill the sum */
        int [] ans = new int [n];
        int firstindex =0;
        int lastindex =0;
        for(int i =0; i<bookings.length;i++){
            firstindex = bookings[i][0];
            lastindex = bookings[i][1];
            for(int j =firstindex-1;j<lastindex;j++)ans[j]+=bookings[i][2];

        }
        return ans;
    /*
    T.C = LET IF THERE ARE N BOOKINGS EACH OF THREE SIZE AND FOR FILLING LET SAY EACH BOOKING RANGE IS GIVEN FROM 1 TO N
    SO T.C =O(N^2)
    but bookings are done by people so boooking array length can be more then number of flights so t.c =o(n*m) where n and m are     large m can be greater then n
    S.C =0(1)
    */
    }




}



