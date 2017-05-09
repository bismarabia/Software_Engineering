package assignment3;

import java.util.Arrays;

public class Statistics {

    static float average(float[] array){

        float sum=0;
        for (float anArray : array) sum += anArray;

        return sum/array.length;
    }

    public static float median(float[] array){
        Arrays.sort(array);
        if (array.length%2 == 1)
            return array[array.length/2];
        return (array[array.length/2-1]+array[array.length/2])/2;

    }

    public static float stddev(float[] array){
        float avg = average(array);
        float sum = 0;
        for (float value : array)
            sum += Math.pow((value - avg), 2);
        return (float) Math.sqrt(sum/(array.length-1));
    }

    public static void main(String[] arg){
        ClassStats classStats = new ClassStats("E://Statistics.java");
        //System.out.println(classStats.method_average());
            System.out.println(stddev(new float[]  {1, 2, 3, 4} ));
    }
}