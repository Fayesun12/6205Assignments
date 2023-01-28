package edu.neu.coe.info6205.threesum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Implementation of ThreeSum which follows the approach of dividing the solution-space into
 * N sub-spaces where each sub-space corresponds to a fixed value for the middle index of the three values.
 * Each sub-space is then solved by expanding the scope of the other two indices outwards from the starting point.
 * Since each sub-space can be solved in O(N) time, the overall complexity is O(N^2).
 * <p>
 * The array provided in the constructor MUST be ordered.
 * * ThreeSum 的实现遵循将解决方案空间划分为
 *   * N个子空间，其中每个子空间对应一个固定值，为三个值的中间索引。
 *   * 然后通过从起点向外扩展其他两个索引的范围来解决每个子空间。
 *   * 由于每个子空间都可以在O(N)时间内求解，所以整体复杂度为O(N^2)。
 *   * <p>
 *   * 必须对构造函数中提供的数组进行排序。
 */
public class ThreeSumQuadraticWithCalipers implements ThreeSum {
    /**
     * Construct a ThreeSumQuadratic on a.
     *
     * @param a a sorted array.
     */
    public ThreeSumQuadraticWithCalipers(int[] a) {
        this.a = a;
        length = a.length;
    }

    /**
     * Get an array or Triple containing all of those triples for which sum is zero.
     *
     * @return a Triple[].
     */
    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        Collections.sort(triples); // ???
        for (int i = 0; i < length - 2; i++)
            //calipers
            triples.addAll(calipers(a, i, Triple::sum));
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    /**
     * Get a set of candidate Triples such that the first index is the given value i.
     * Any candidate triple is added to the result if it yields zero when passed into function.
     *
     * @param a        a sorted array of ints.
     * @param i        the index of the first element of resulting triples.
     * @param function a function which takes a triple and returns a value which will be compared with zero.
     * @return a List of Triples.
     * * 获取一组候选三元组，使得第一个索引为给定值 i。
     *       * 如果任何候选三元组在传递给函数时产生零，则将其添加到结果中。
     *       *
     *       * @param a 一个排序的整数数组。 原数组
     *       * @param i 结果三元组的第一个元素的索引。 循环int i = 0
     *       * @param function 一个接受三元组并返回一个值的函数，该值将与零进行比较。输入triple和sum
     *       * @return 三元组列表。
     */
    public static List<Triple> calipers(int[] a, int i, Function<Triple, Integer> function) {
        List<Triple> triples = new ArrayList<>();
        //Triple triple = new Triple();
        // FIXME : use function to qualify triples and to navigate otherwise.
        // FIXME : 使用函数限定三元组并以其他方式导航。
        //function.apply(triple);
//        for (int j = i + 1; j < a.length; j++) {
//            for(int k = j+1; k < a.length; k++){
//                Triple triple = new Triple(a[i], a[j], a[k]);
//                int m = function.apply((triple));
//                if(m == 0){
//                    triples.add(triple);
//                }
//            }
//        } Fanye Sun
        int j = i + 1, k = a.length - 1;
        while(j < k){
            int sum = function.apply((new Triple(a[i], a[j], a[k])));
            if(sum < 0){
                while(j < k && a[j] == a[++j]);
            } else if (sum > 0) {
                while(j < k && a[k] == a[--k]);
            } else {
                triples.add(new Triple(a[i], a[j], a[k]));
                while(j < k && a[j] == a[++j]);
                while(j < k && a[k] == a[--k]);
            }
        }


        // END 
        return triples;
    }

    private final int[] a;
    private final int length;
}
