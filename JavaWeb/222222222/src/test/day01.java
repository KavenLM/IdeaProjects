package test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class day01 {
    public static void main(String[] args) {
        int[] a = {1,2,3,4};
        int[] b = {2,2,3,4};
        int[] intersection = intersection(a, b);
        for (int i = 0; i < intersection.length; i++) {
            System.out.println(intersection[i]);
        }
    }
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for(int i=0 ; i<nums1.length ; i++){
            for(int j=0 ; j<nums2.length ; j++){
                if(nums1[i]==nums2[j]){
                    set.add(nums1[i]);
                }
            }
        }
        Object[] obj = set.toArray();
        int[] res = new int[obj.length];
        for (int i = 0; i < obj.length; i++) {
            res[i] = (int)obj[i];
        }
        return res;
    }
}
