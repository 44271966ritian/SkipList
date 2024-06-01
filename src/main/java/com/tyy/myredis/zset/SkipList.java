package com.tyy.myredis.zset;


import java.util.Arrays;

/*
* TODO 手写一个简易跳表
* */
public class SkipList {

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        for (int i = 0; i < 24; i++) {
            skipList.add(i);
        }
        SkipList.Node node = skipList.get(22);
        System.out.println("node = " + node);
        skipList.delete(22);
        skipList.queryAll();
        SkipList.Node node1 = skipList.get(22);
        System.out.println("node = " + node1);
        skipList.queryAll();
    }

    //跳表索引的最大高度为16
    private static final int MAX_LEVEL = 16;

    //每个节点添加一层索引高度的概率为二分之一
    private static final float PROB = 0.5f;

    private Node h = new Node();

    public SkipList(){}

    //默认情况下高度为1，即只有自己一个节点
    private int levelCount = 1;
    class Node{
        private int data = -1;
        private Node [] forwards = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", maxLevel=" + maxLevel +
                    '}';
        }
    }

    public void add(int value){
        //随机生成高度
        int level = randomLevel();

        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;


        //创建一个node数组，用于记录小于当前value的最大值
        //0 1 2 3
        //分别是三个级别的后继节点
//        Node[] maxOfMinArr = new Node[level];
//        for (int i = 0; i < level; i++) {
//            maxOfMinArr[i] = h;
//        }

        //获取node
        Node p= h;

        //从高到低插入
        for (int i = level-1; i >= 0 ; i--) {
            while (p.forwards[i]!=null&&p.forwards[i].data<value){
                p = p.forwards[i];
            }
            //这个还不是最终结果，这个只是找到了各个级别索引上的前驱节点
//            maxOfMinArr[i]=p;
            newNode.forwards[i] = p.forwards[i];
            p.forwards[i] = newNode;
        }

        //更新前驱节点的后继节点为当前接待你newNode
//        for (int i = 0; i < level; i++) {
//            newNode.forwards[i] = maxOfMinArr[i].forwards[i];
//            maxOfMinArr[i].forwards[i] = newNode;
//        }

        //如果当前newNode高度大于跳表最大高度则更新levelCount
        if(levelCount<level){
            levelCount = level;
        }

    }

    /*
    * TODO 跳表不是一个非常严谨的结构
    *  要求就是尽量保证，每一级节点个数是上一级节点个数的二分之一
    *  例如，16个节点的跳表，高度为log16-1=3
    *  三级节点个数分别为:8，4，2
    *  该方法每次取一个随机数，随机数大小在0到1，如果大于0.5，也就是在二分之一的另一边，就升一级
    * */
    private int randomLevel(){
        int level = 1;
        while (Math.random()>PROB && level<MAX_LEVEL){
            level++;
        }
        return level;
    }

    public Node get(int value){
        //先拿头节点
        Node p = h;

        //找小于value的最大值
        for (int i = levelCount-1; i >=0 ; i--) {
            while (p.forwards[i]!=null&&p.forwards[i].data<value){
                p=p.forwards[i];
            }
        }

        //相等直接返回
        if(p.forwards[0]!=null&&p.forwards[0].data==value){
            return p.forwards[0];
        }

        return null;
    }

    public void delete(int val){
        Node p = h;

        for (int i = levelCount-1; i >=0 ; i--) {
            while (p.forwards[i]!=null&&p.forwards[i].data<val){
                p=p.forwards[i];
            }
            //和get不一样的地方就是，get只要找到最底层就可以，但是delete要把每一层都删掉，比较像add
            //删除不可以乱删，因为这次删除是不知道高度的，同时还可能存在value重复的节点
//            Node t = p;
//            while (t!=null&&t.forwards[i]!=null&&t.forwards[i].data==val){
//                t.forwards[i]=t.forwards[i].forwards[i];
//                //如果还有value重复的节点，继续删除
//                t=t.forwards[i];
//            }

            if(p.forwards[i]!=null&&p.forwards[i].data==val){
                p.forwards[i]=p.forwards[i].forwards[i];
            }
        }


        //遍历除了最底层以外的索引，出现空的就降级
        while (levelCount>1&&h.forwards[levelCount-1]==null){
            levelCount--;
        }

    }

    public void queryAll(){
        Node p = h;
        while (p.forwards[0]!=null){
            System.out.println(p.forwards[0]);
            p=p.forwards[0];
        }
    }

}

