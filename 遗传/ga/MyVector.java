package com.mcmf.ga;

import com.ga.Chromosome;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by xjtu_yjw on 2017/3/19.
 */
public class MyVector implements Chromosome<MyVector>,Cloneable{


    private static final Random random = new Random();
    public   int[] vector ;

    public int mutate_rate = 5;

    public MyVector(int length) {
        vector = new int[length];
    }

    @Override
    public List<MyVector> crossover(MyVector other) {
        MyVector thisClone = this.clone();
        MyVector otherClone = other.clone();
      /*  MyVector new_vector = new MyVector(this.vector.length);
        for (int i = 0; i < this.vector.length; i++) {
            if (thisClone.vector[i] != otherClone.vector[i]) {
                new_vector.vector[i] = 1;
            }
        }
        return Arrays.asList(new_vector);*/

        int index = random.nextInt(this.vector.length - 1);
        for (int i = index; i < this.vector.length; i++) {
            int temp = thisClone.vector[i];
            thisClone.vector[i] = otherClone.vector[i];
            otherClone.vector[i] = temp;
        }

        return Arrays.asList(thisClone, otherClone);
    }

    @Override
    public MyVector mutate() {

        MyVector result = this.clone();
        for (int i = 0; i < mutate_rate; i++) {
            int index = random.nextInt(this.vector.length);

            if (result.vector[index] == 1)
                result.vector[index] = 0;
            else
                result.vector[index] = 1;
        }

        return result;
    }

    @Override
    protected MyVector clone() {
        MyVector clone = new MyVector(this.vector.length);
        System.arraycopy(this.vector, 0, clone.vector, 0, this.vector.length);
        return clone;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.vector);
    }


}
