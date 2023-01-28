package edu.neu.coe.info6205.threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of ThreeSum which follows the approach of dividing the solution-space into
 * N sub-spaces where each sub-space corresponds to a fixed value for the middle index of the three values.
 * Each sub-space is then solved by expanding the scope of the other two indices outwards from the starting point.
 * Since each sub-space can be solved in O(N) time, the overall complexity is O(N^2).
 * <p>
 * NOTE: The array provided in the constructor MUST be ordered.
 * * ThreeSum 的实现遵循将解决方案空间划分为
 *   * N个子空间，其中每个子空间对应一个固定值，为三个值的中间索引。
 *   * 然后通过从起点向外扩展其他两个索引的范围来解决每个子空间。
 *   * 由于每个子空间都可以在O(N)时间内求解，所以整体复杂度为O(N^2)。
 *   * 注意：构造函数中提供的数组必须有序。
 */
public class ThreeSumQuadratic implements ThreeSum {
    /**
     * Construct a ThreeSumQuadratic on a.
     * @param a a sorted array.
     */
    public ThreeSumQuadratic(int[] a) {
        this.a = a;
        length = a.length;
    }

    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        for (int i = 0; i < length; i++) triples.addAll(getTriples(i));
        Collections.sort(triples);
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    /**
     * Get a list of Triples such that the middle index is the given value j.
     *获取一个三元组列表，使得中间索引为给定值 j。
     * @param j the index of the middle value.
     * @return a Triple such that
     */
    public List<Triple> getTriples(int j) {
        List<Triple> triples = new ArrayList<>();
        // FIXME : for each candidate, test if a[i] + a[j] + a[k] = 0.
        // END
//        for(int i=0; i<j; i++){
//            int k = Arrays.binarySearch(a, -a[i] - a[j]);
//            if (k >= 0 && k > j) triples.add(new Triple(a[i], a[j], a[k]));
//        }
        //Fanye Sun
        int i = 0, k = a.length - 1;
        while(i < j && k > j){
            int sum = a[i] + a[j] + a[k];
            if(sum < 0){
                i++;
            } else if (sum > 0) {
                k--;
            } else {
                triples.add(new Triple(a[i], a[j], a[k]));
                i++;
                k--;
            }
        }
        return triples;
    }

    private final int[] a;
    private final int length;
}