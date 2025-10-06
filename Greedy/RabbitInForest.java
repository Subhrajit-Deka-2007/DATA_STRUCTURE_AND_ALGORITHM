package Greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * Rabbits In Forest
 * You have a forest with an unknown number of rabbits. We asked n rabbit "How many rabbits have the same color as you?" and
 * collected the answers in an integer array answers where answers[i] is the answer from the ith rabbit.
 * Given the array answers,return the minimum number of rabbits that could be in the forest.
 * Example 1
 * Input: answers =[1,1,2]
 * Output: 5
 * Explanation:
 * The two rabbits that answered "1" could both be the same color, say red.
 * The rabbit that answered "2" can't be red because its answer was different.
 * Say the third rabbit answered "2", say blue.
 *
 * Then there should be 2 other blue rabbits in the forest that didn't answer into the array . The smallest possible number of rabbits
 * in the forest is therefore 5:3 that answer plus 2 that didn't
 *
 * Example 2
 * Input: answers = Output: 11 [3]
 * Constraints:
 */
public class RabbitInForest {
/**

 Summary of Your Rules for this question
 Rule 1: b > a % b is always true.

 Constraint: b must be a positive number (b > 0).

 Rule 2: If a and k are both multiples of b, then k * a is also a multiple of b.

 Constraint: b must not be zero (b ≠ 0).
 also if (a%b==0) then A is multiple of B .




 Excellent question. That refers to the extra detail I mentioned earlier, which builds directly on top of your second rule.

 Let's call it the "Multiple Squared Rule".

 Here is the idea in short:

 Your Correct Rule: If a and k are multiples of b, then k * a is a multiple of b.

 The b² Rule (More Specific): If a and k are multiples of b, then k * a is also a multiple of b².

 Why is this true?
 Think of what "multiple of b" means. It means the number b is a factor inside both a and k.

 a = n * b  (The number b is inside a)

 k = m * b  (The number b is also inside k)

 So when you multiply them:
 k * a = (m * b) * (n * b)

 If you group the b's together, you get:
 k * a = (m * n) * (b * b)
 k * a = (m * n) * b²

 This proves that the final result is always a perfect multiple of b².

 Simple Example
 Let b = 10. So b² = 100.

 Let a = 30 (a multiple of 10).

 Let k = 50 (a multiple of 10).

 k * a = 30 * 50 = 1500.

 Is 1500 a multiple of b² (100)? Yes, 1500 / 100 = 15.

 So, while your rule that 1500 is a multiple of 10 is correct, the b² rule gives a more precise and powerful conclusion.
 If something is a multiple of 100, it is automatically a multiple of 10.
 */
public static void main(String[] args) {
    // For this question we are using a HashMap <total rabbits in a group, frequency of that type of rabbit of that type >
    /*
    1) frequency map
    2) Iterate over the frequency map
    3)q = val/key
      r =val%key
    4) ans = (q*key)+(r*key)
     */
    int ans =0;
    int [] answers ={3,3,3,3,3,4,4,4,2,2};
    Map<Integer,Integer> freq = new HashMap<>();
    for(int answer : answers)freq.put(answer+1,freq.getOrDefault(answer+1,0)+1);
    // I don't know the above thing discover it okay
    for(int key : freq.keySet()){
        int val = freq.get(key);
        int q = val/key;
        int r = val%key;
        ans+=q*key;
        if(r!=0)ans+=key;
    }
    System.out.println("ans "+ans );
}
/**
 * . answer + 1
 * This is the key we are interested in.
 *
 * Since answer is 3, the key is 4.
 *
 * The code wants to update the count for the number 4.
 *
 * 2. freq.getOrDefault(4, 0)
 * This is the most important part. The getOrDefault method does exactly what its name says:
 *
 * "Get": It first tries to get the value associated with the key 4 from the freq map.
 *
 * "Or Default": If the key 4 is not found in the map (which is true the first time we see it), it doesn't cause an error. Instead, it returns the default value you provided, which is 0.
 *
 * So, the first time the loop runs:
 *
 * Does the map freq contain the key 4? No, it's empty.
 *
 * Therefore, freq.getOrDefault(4, 0) returns 0.
 *
 * 3. ... + 1
 * The code then takes the result from the previous step and adds 1 to it.
 *
 * 0 + 1 equals 1.
 *
 * This 1 is the new, updated count for our key.
 *
 * 4. freq.put(4, 1)
 * Finally, the put method takes the key and the new value and stores them in the map.
 *
 * It puts the key 4 into the map with the value 1.
 *
 * After the first iteration, the map freq looks like this: {4=1}.
 *
 * Walkthrough: The Second Time We See 3
 * Now let's see what happens on the second iteration, when answer is again 3.
 *
 * Key: answer + 1 is still 4.
 *
 * freq.getOrDefault(4, 0):
 *
 * This time, the map freq does contain the key 4.
 *
 * The method "gets" the current value, which is 1.
 *
 * It returns 1. (It ignores the default value because the key was found).
 *
 * ... + 1:
 *
 * 1 + 1 equals 2.
 *
 * freq.put(4, 2):
 *
 * The put method updates the value for the key 4 to 2.
 *
 * The map freq now looks like this: {4=2}.
 */
}
