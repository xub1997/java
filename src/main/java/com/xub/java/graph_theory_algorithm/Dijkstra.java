package com.xub.java.graph_theory_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author xub
 * @Name: Dijkstra
 * @Description: TODO
 * @date 2020/1/9  21:53
 */
public class Dijkstra {
    //无向带权图
    private WeightedGraph G;
    //源点
    private int s;
    //距离
    private int[] dis;
    //是否访问过
    private boolean[] visited;

    private int[] pre;


    private class Node implements Comparable<Node> {

        public int v, dis;

        public Node(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node another) {
            return dis - another.dis;
        }
    }

    public Dijkstra(WeightedGraph G, int s) {

        this.G = G;

        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);

        pre = new int[G.V()];
        Arrays.fill(pre, -1);

        visited = new boolean[G.V()];
        dis[s] = 0;
        pre[s] = s;

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(s, 0));
        while (!pq.isEmpty()) {

            int cur = pq.remove().v;

            if (visited[cur]) continue;

            visited[cur] = true;
            //找出最短路径
            for (int w : G.adj(cur))
                if (!visited[w]) {
                    if (dis[cur] + G.getWeight(cur, w) < dis[w]) {
                        dis[w] = dis[cur] + G.getWeight(cur, w);
                        pq.add(new Node(w, dis[w]));
                        pre[w] = cur;
                    }
                }
        }
    }

    public boolean isConnectedTo(int v) {

        G.validateVertex(v);
        return visited[v];
    }

    public int distTo(int v) {

        G.validateVertex(v);
        return dis[v];
    }

    public Iterable<Integer> path(int t) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        if (!isConnectedTo(t)) {
            return res;
        }

        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        //0 广州  1深圳  2梅州  3佛山  4茂名
        WeightedGraph g = new WeightedGraph("g.txt");
        System.out.println("0 广州  1深圳  2梅州  3佛山  4茂名");
        Dijkstra dij = new Dijkstra(g, 0);
        System.out.println(dij.G);
        System.out.println("广州到其他地方的最短路径：");
        for (int v = 0; v < g.V(); v++) {
            System.out.print(dij.distTo(v) + "-》 ");
        }
        System.out.println();
        System.out.println("广州到茂名的最短路径：");
        System.out.println(dij.path(4));
    }
}
