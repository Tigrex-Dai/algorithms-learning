package com.huawei.l00379880.algs4.chapter2sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * P182Quick Tester.
 *
 * @author liang shan guang
 * @datetime 01/02/2018
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P182QuickTest {
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
        P182Quick.sort(a, new DogComparatorByAge());
        System.out.println(Arrays.toString(a));
        System.out.println("*****************按照名称排序后****************");
        P182Quick.sort(a, new DogComparatorByName());
        System.out.println(Arrays.toString(a));
    }

    /**
     * Description:
     */
    @Test
    public void testMain() {
        Integer[] a = {3, 2, 6, 5, 7, 4, 9, 8, 0, 1};
        System.out.println("原数组:");
        System.out.println(Arrays.toString(a));
        System.out.println("排前5个:");
        P182Quick.sort(a, 0, 4);
        System.out.println("排后结果:");
        System.out.println(Arrays.toString(a));
        System.out.println("排后5个:");
        P182Quick.sort(a, a.length - 5, a.length - 1);
        System.out.println("排后结果:");
        System.out.println(Arrays.toString(a));
        System.out.println("升序:");
        P182Quick.sort(a);
        System.out.println(Arrays.toString(a));
        System.out.println("降序:");
        P153CommenFuns.reverseArray(a);
        System.out.println(Arrays.toString(a));
    }
} 
