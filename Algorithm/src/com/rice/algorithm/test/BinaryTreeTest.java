package com.rice.algorithm.test;

import com.rice.algorithm.linear.Queue;
import com.rice.algorithm.tree.BinaryTree;
import org.junit.Test;

/**
 * @author wgz
 * @description
 * @date 2020/12/16 15:02
 */
public class BinaryTreeTest {

    @Test
    public void basicTest() {
        // 创建二叉查找树对象
        BinaryTree<Integer, String> tree = new BinaryTree<>();

        // 测试插入
        tree.put(1, "AntiMaga");
        tree.put(2, "Viper");
        tree.put(3, "Invoker");
        tree.put(4, "Axe");
        tree.put(5, "Luna");
        System.out.println("插入完毕后元素的个数：" + tree.size());
        System.out.println("最大深度：" + tree.maxDepth());

        // 测试获取
        System.out.println("键2对应的元素是：" + tree.get(2));

        // 遍历
        Queue<Integer> treeQueue = tree.layerErgodic();
        treeQueue.forEach(System.out::println);

        // 测试删除
        int delete = 4;

        tree.delete(4);
        System.out.println("删除后的元素个数：" + tree.size());
        System.out.println("删除后该键对应的元素：" + tree.get(4));
        System.out.println("删除后的最大深度：" + tree.maxDepth());

        // 遍历
        treeQueue = tree.layerErgodic();
        treeQueue.forEach(System.out::println);
    }
}
