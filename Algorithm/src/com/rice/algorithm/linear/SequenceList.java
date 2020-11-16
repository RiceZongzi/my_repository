package com.rice.algorithm.linear;

import java.util.Iterator;

/**
 * @author wgz
 * @description
 *      v1.0 基础API实现
 *      v1.1 实现ForEach遍历
 *      v1.2 实现可变长度
 * @date 2020/11/13 16:40
 */
public class SequenceList<T> implements Iterable<T>{

    /** 存储元素的Array */
    private T[] eles;

    /** 记录当前顺序表中的元素个数 */
    private int n;

    /**
     * 构造方法
     * @author wgz
     * @date 2020/11/13
     * @param capacity 线性表容量
     */
    public SequenceList(int capacity) {
        // 初始化数组
        this.eles = (T[]) new Object[capacity];
        // 初始化长度
        this.n = 0;
    }

    /**
     * 将一个线性表置为空表
     * @author wgz
     * @date 2020/11/13
     */
    public void clear() {
        n = 0;
    }

    /**
     * 判断当前线性表是否为空表
     * @author wgz
     * @date 2020/11/13
     * @return boolean
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 获取线性表的长度
     * @author wgz
     * @date 2020/11/13
     * @return int
     */
    public int length() {
        return n;
    }

    /**
     * 获取指定位置的元素
     * @author wgz
     * @date 2020/11/13
     * @param i 索引
     * @return T
     */
    public T get(int i) {
        if (i < 0 || i >= n) {
            throw new RuntimeException("该索引不存在元素！");
        }
        return eles[i];
    }

    /**
     * 向线型表中添加元素t
     * @author wgz
     * @date 2020/11/13
     * @param t T类型元素
     */
    public void insert(T t) {
        // 元素已经放满了数组，需要扩容
        if (n == eles.length) {
//            throw new RuntimeException("当前表已满");
            resize(2 * eles.length);
        }
        eles[n++] = t;
    }

    /**
     * 根据参数newSize，重置eles的大小
     * @author wgz
     * @date 2020/11/16
     * @param newSize 新size
     */
    public void resize(int newSize){
        // 定义一个临时数组，指向原数组
        T[] temp = eles;
        // 创建新数组
        eles = (T[]) new Object[newSize];
        // 把原数组的数据拷贝到新数组即可
        if (n >= 0) {
            System.arraycopy(temp, 0, eles, 0, n);
        }
    }

    /**
     * 在i索引处插入元素t
     * @author wgz
     * @date 2020/11/13
     * @param i 索引
     * @param t 元素
     */
    public void insert(int i,T t) {
        // 元素已经放满了数组，需要扩容
        if (i == eles.length) {
//            throw new RuntimeException("当前表已满");
            resize(2 * eles.length);
        }
        if (i < 0 || i > n) {
            throw new RuntimeException("插入的位置不合法");
        }
        // 把i位置空出来，i位置及其后面的元素依次向后移动一位
        if (n - i >= 0) {
            System.arraycopy(eles, i, eles, i + 1, n - i);
        }
        // 把t放到i位置处
        eles[i] = t;
        // 元素数量+1
        n++;
    }

    /**
     * 删除指定索引i处的元素，并返回该元素
     * @author wgz
     * @date 2020/11/13
     * @param i 索引
     * @return T
     */
    public T remove(int i) {
        if (i < 0 || i > n - 1) {
            throw new RuntimeException("该索引不存在元素！");
        }
        // 记录i位置处的元素
        T result = eles[i];
        // 把i位置后面的元素都向前移动一位
        if (n - 1 - i >= 0) {
            System.arraycopy(eles, i + 1, eles, i, n - 1 - i);
        }
        // 当前元素数量-1
        n--;

        // 当元素已经不足数组大小的1/4，则重置数组的大小
        if (n < eles.length/4){
            resize(eles.length/2);
        }

        return result;
    }

    /**
     * 查找t元素第一次出现的位置
     * @author wgz
     * @date 2020/11/13
     * @param t T
     * @return int
     */
    public int indexOf(T t) {
        if(t == null) {
            throw new RuntimeException("查找的元素不合法");
        }
        for (int i = 0; i < n; i++) {
            if (eles[i].equals(t)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator {

        private int cusor;

        public SIterator(){
            this.cusor = 0;
        }

        @Override
        public boolean hasNext() {
            return cusor < n;
        }

        @Override
        public Object next() {
            return eles[cusor++];
        }
    }
}
