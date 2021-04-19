package test;

import java.util.Arrays;

public class day02 {
    public static void main(String[] args) {
        int [] A = {0,1,4,4,2};
        boolean flag = validMountainArray(A);
        System.out.println(flag);
    }
    public static boolean validMountainArray(int[] A) {
        int[] B = Arrays.copyOf(A,A.length);
        Arrays.sort(B);
        int mark = 0;
        if(A==null){
            return false;
        }
        if(A.length>2 && A.length<=10000){
            for (int i = 0; i < A.length-1; i++) {
                if(A[i]==B[B.length-1]){
                    if(i==0 || i==A.length-1){
                        return false;
                    }
                    mark=i;
                    break;
                }
            }
            System.out.println(mark);
            for(int i=0 ; i < mark ; i++){
                if(A[i]>=A[i+1]){
                    return false;
                }
            }
            for(int i=A.length-1 ; i > mark ; i--){
                if(A[i]>=A[i-1]){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
