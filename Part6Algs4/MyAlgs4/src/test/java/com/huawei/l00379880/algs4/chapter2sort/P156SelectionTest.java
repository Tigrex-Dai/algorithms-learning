package com.huawei.l00379880.algs4.chapter2sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * P156Selection Tester.
 *
 * @author liang shan guang
 * @datetime 01/01/2018
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P156SelectionTest {

    @Test
    public void testComparable() {
        List<DogComparble> list = new ArrayList<>();
        list.add(new DogComparble(10, "DogC"));
        list.add(new DogComparble(5, "DogB"));
        list.add(new DogComparble(7, "DogD"));
        list.add(new DogComparble(6, "DogA"));
        DogComparble[] a = new DogComparble[list.size()];
        // 把list转换到数组中
        list.toArray(a);
        // 按照age还是按照name不能灵活指定,只能通过修改DogComparable里的compareTo()方法来实现
        System.out.println("*****************排序前****************");
        System.out.println(Arrays.toString(a));
        System.out.println("*****************排序后****************");
        P156Selection.sort(a);
        System.out.println(Arrays.toString(a));
    }


    @Test
    public void testComparator() {
        List<Dog> list = new ArrayList<>();
        list.add(new Dog(10, "DogC"));
        list.add(new Dog(5, "DogB"));
        list.add(new Dog(7, "DogD"));
        list.add(new Dog(6, "DogA"));
        Dog[] a = new Dog[list.size()];
        // 把list转换到数组中
        list.toArray(a);
        System.out.println("*****************排序前****************");
        System.out.println(Arrays.toString(a));
        System.out.println("*****************按照年龄排序后****************");
        P156Selection.sort(a, new DogComparatorByAge());
        System.out.println(Arrays.toString(a));
        System.out.println("*****************按照名称排序后****************");
        P156Selection.sort(a, new DogComparatorByName());
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void testMain() {
        Integer[] a = {3, 2, 6, 5, 7, 4, 9, 8, 0, 1};
        System.out.println("原数组:");
        P153CommenFuns.show(a);
        System.out.println("排序前3个:");
        P156Selection.sort(a, 0, 2);
        P153CommenFuns.show(a);
        System.out.println("排序后3个:");
        P156Selection.sort(a, a.length - 3, a.length - 1);
        P153CommenFuns.show(a);
        // 升序排列
        System.out.println("升序排列:");
        P156Selection.sort(a);
        P153CommenFuns.show(a);
        // 降序排列
        System.out.println("降序排列:");
        P153CommenFuns.reverseArray(a);
        P153CommenFuns.show(a);
    }
}
