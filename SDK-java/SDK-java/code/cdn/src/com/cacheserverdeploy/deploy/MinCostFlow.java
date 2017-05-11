package com.cacheserverdeploy.deploy;

import java.util.*;

/**
 * Created by alanmaxwell on 17-3-27.
 */
public class MinCostFlow {

    static class Edge {
        int to, f, cap, cost, rev,from;

        Edge(int from,int v, int cap, int cost, int rev) {
            this.from=from;
            this.to = v;
            this.cap = cap;
            this.cost = cost;
            this.rev = rev;
        }

        @Override
        public String toString() {
//            return to+"--->";
            return "from:"+from+"  to:"+to+"  flow="+f+"  cost="+cost+"  cap="+cap;
        }
    }

    public static List<Edge>[] createGraph(int n) {
        List<Edge>[] graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        return graph;
    }

    public static void addEdge(List<Edge>[] graph, int s, int t, int cap, int cost) {
        graph[s].add(new Edge(s,t,cap,cost, graph[t].size()));
        graph[t].add(new Edge(t,s,cap,cost, graph[s].size() - 1));
    }

    static void bellmanFord(List<Edge>[] graph, int s, int[] dist) {
        int n = graph.length;
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        boolean[] inqueue = new boolean[n];
        int[] q = new int[n];
        int qt = 0;
        q[qt++] = s;
        for (int qh = 0; (qh - qt) % n != 0; qh++) {
            int u = q[qh % n];
            inqueue[u] = false;
            for (int i = 0; i < graph[u].size(); i++) {
                Edge e = graph[u].get(i);
                if (e.cap <= e.f)
                    continue;
                int v = e.to;
                int ndist = dist[u] + e.cost;
                if (dist[v] > ndist) {
                    dist[v] = ndist;
                    if (!inqueue[v]) {
                        inqueue[v] = true;
                        q[qt++ % n] = v;
                    }
                }
            }
        }
    }

    public static int[] minCostFlow(List<Edge>[] graph, int s, int t, int maxf) {
        int n = graph.length;
        int[] prio = new int[n];
        int[] curflow = new int[n];
        int[] prevedge = new int[n];
        int[] prevnode = new int[n];
        int[] pot = new int[n];

        // bellmanFord invocation can be skipped if edges costs are non-negative
        bellmanFord(graph, s, pot);
        int flow = 0;
        int flowCost = 0;
        while (flow < maxf) {
            PriorityQueue<Long> q = new PriorityQueue<>();
            q.add((long) s);
            Arrays.fill(prio, Integer.MAX_VALUE);
            prio[s] = 0;
            boolean[] finished = new boolean[n];
            curflow[s] = Integer.MAX_VALUE;
            while (!finished[t] && !q.isEmpty()) {
                long cur = q.remove();
                int u = (int) (cur & 0xFFFF_FFFFL);
                int priou = (int) (cur >>> 32);
                if (priou != prio[u])
                    continue;
                finished[u] = true;
                for (int i = 0; i < graph[u].size(); i++) {
                    Edge e = graph[u].get(i);
                    if (e.f >= e.cap)
                        continue;
                    int v = e.to;
                    int nprio = prio[u] + e.cost + pot[u] - pot[v];
                    if (prio[v] > nprio) {
                        prio[v] = nprio;
                        q.add(((long) nprio << 32) + v);
                        prevnode[v] = u;
                        prevedge[v] = i;
                        curflow[v] = Math.min(curflow[u], e.cap - e.f);
                    }
                }
            }
            if (prio[t] == Integer.MAX_VALUE)
                break;
            for (int i = 0; i < n; i++)
                if (finished[i])
                    pot[i] += prio[i] - prio[t];
            int df = Math.min(curflow[t], maxf - flow);
            flow += df;
            for (int v = t; v != s; v = prevnode[v]) {
                Edge e = graph[prevnode[v]].get(prevedge[v]);
                e.f += df;
                graph[v].get(e.rev).f -= df;
                flowCost += df * e.cost;
                System.out.print(v+" -- ");
//                System.out.println(e);
            }
            System.out.println();
        }
        return new int[]{flow, flowCost};
    }


    public static String[] minCostFlow2(List<Edge>[] graph, int s, int t, int maxf, Deploy.Cost[] costs) {
        String result="";

        int n = graph.length;
        int[] prio = new int[n];
        int[] curflow = new int[n];
        int[] prevedge = new int[n];
        int[] prevnode = new int[n];
        int[] pot = new int[n];

        // bellmanFord invocation can be skipped if edges costs are non-negative
        bellmanFord(graph, s, pot);
        int flow = 0;
        int flowCost = 0;
        while (flow < maxf) {
            PriorityQueue<Long> q = new PriorityQueue<>();
            q.add((long) s);
            Arrays.fill(prio, Integer.MAX_VALUE);
            prio[s] = 0;
            boolean[] finished = new boolean[n];
            curflow[s] = Integer.MAX_VALUE;
            while (!finished[t] && !q.isEmpty()) {
                long cur = q.remove();
                int u = (int) (cur & 0xFFFF_FFFFL);
                int priou = (int) (cur >>> 32);
                if (priou != prio[u])
                    continue;
                finished[u] = true;
                for (int i = 0; i < graph[u].size(); i++) {
                    Edge e = graph[u].get(i);
                    if (e.f >= e.cap)
                        continue;
                    int v = e.to;
                    int nprio = prio[u] + e.cost + pot[u] - pot[v];
                    if (prio[v] > nprio) {
                        prio[v] = nprio;
                        q.add(((long) nprio << 32) + v);
                        prevnode[v] = u;
                        prevedge[v] = i;
                        curflow[v] = Math.min(curflow[u], e.cap - e.f);
                    }
                }
            }
            if (prio[t] == Integer.MAX_VALUE)
                break;
            for (int i = 0; i < n; i++)
                if (finished[i])
                    pot[i] += prio[i] - prio[t];
            int df = Math.min(curflow[t], maxf - flow);
            flow += df;
            StringBuilder stringBuilder=new StringBuilder();
//            Stack<Integer> result=new Stack<>();
            int flow2=0;
            Stack stack=new Stack();
            for (int v = t; v != s; v = prevnode[v]) {

                Edge e = graph[prevnode[v]].get(prevedge[v]);
                e.f += df;
                graph[v].get(e.rev).f -= df;
                flowCost += df * e.cost;
                stack.push(v);
//                if (v==graph.length-1){
//                    stack.pop();
////                    int temp= (int) stack.peek();
////                    for (int j=0;j< costs.length;j++){
////                        if (costs[j].netNode==temp){
//////                            stack.push(temp);
////                            stack.push(j);
////                        }
////                    }
//                }
//                stringBuilder.append(v);
//                stringBuilder.append(" ");
//                stringBuilder.toString();
//                System.out.print(v+" -- ");
            }
//            int temp= (int) stack.pop();
//            for (int j=0;j<costs.length;j++){
//                if (costs[j].netNode==temp){
//                    stack.push(temp);
//                    stack.push(j);
//                }
//            }
            while (stack.size()>1){
                int temp= (int) stack.pop();
                    if (stack.size()==1){
                        for (int j=0;j<costs.length;j++){
                            if (temp==costs[j].netNode){
                                stringBuilder.append(temp+" ");
                                temp=j;
                            }
                        }
                    }
                stringBuilder.append(temp);
                stringBuilder.append(" ");
            }
            result=result+stringBuilder+df+"\n";
//            System.out.println(stringBuilder+""+df);
        }

        System.out.println("maxflow="+flow+"  flowcost="+flowCost);
        int sumFlow=0;
        for (int j=0;j<costs.length;j++){
            sumFlow+=costs[j].require;
        }
        if (sumFlow!=flow){
            System.out.println("流量不符合");
            return new String[]{"流量不符合",String.valueOf(flow),String.valueOf(flowCost)};
        }
        System.out.println(result);
//        return new int[]{flow, flowCost};
        String[] res=new String[3];
        res[0]=result;
        return new String[]{result,String.valueOf(flow),String.valueOf(flowCost)};
    }
}
