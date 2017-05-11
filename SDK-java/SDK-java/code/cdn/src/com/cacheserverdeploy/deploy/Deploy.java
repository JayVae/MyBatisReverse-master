package com.cacheserverdeploy.deploy;


import com.filetool.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class Deploy
{
    /**
     * 你需要完成的入口
     * <功能详细描述>
     * @param graphContent 用例信息文件
     * @return [参数说明] 输出结果信息
     * @see [类、类#方法、类#成员]
     */
    public static String[] deployServer(String[] graphContent)
    {
        //获取网络基本信息
        String[] line0=graphContent[0].split(" ");
        int netNodeNum=Integer.parseInt(line0[0]);
        int netLinkNum=Integer.parseInt(line0[1]);
        int costNodeNum=Integer.parseInt(line0[2]);
        int serverCost = Integer.parseInt(graphContent[2]);
        //初始化网络图邻接表
        List<Edge>[] graph=new ArrayList[netNodeNum];
        for (int i=0;i<netNodeNum;i++){
            graph[i]=new ArrayList<Edge>();
        }
        String[] line;

        List<MinCostFlow.Edge>[] mincostgraph=MinCostFlow.createGraph(netNodeNum+2);
        //List<MinCostFlow.Edge>[] mincostgraph1=MinCostFlow.createGraph(netNodeNum+2);
//        List<MinCostFlowBF.Edge>[] mincostbfgraph=MinCostFlowBF.createGraph(netNodeNum+2);

        //生成网络图邻接表
//        for (int i=4;i<netLinkNum+4;i++){
//            line=graphContent[i].split(" ");
//            Edge edge=new Edge(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]),Integer.parseInt(line[3]));
//            Edge edge1=new Edge(Integer.parseInt(line[1]),Integer.parseInt(line[0]),Integer.parseInt(line[2]),Integer.parseInt(line[3]));
//            graph[edge.from].add(edge);
//            graph[edge.to].add(edge1);
//
//        }
        //生成含虚拟消费点和虚拟服务点的网络图
        for (int i=4;i<netLinkNum+4;i++){
            line=graphContent[i].split(" ");
            //最小费用流网络初始化
            MinCostFlow.addEdge(mincostgraph,Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]),Integer.parseInt(line[3]));
            //MinCostFlow.addEdge(mincostgraph1,Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]),Integer.parseInt(line[3]));
//            MinCostFlowBF.addEdge(mincostbfgraph,Integer.parseInt(line[1]),Integer.parseInt(line[0]),Integer.parseInt(line[2]),Integer.parseInt(line[3]));
        }

        int index=0;
        //生成消费节点数组表
        Cost[] costInfoList=new Cost[costNodeNum];
        for (int i=netLinkNum+5;i<netLinkNum+5+costNodeNum;i++){
            line=graphContent[i].split(" ");
            costInfoList[index]=new Cost(Integer.parseInt(line[1]),Integer.parseInt(line[2]));
            index++;
        }

        //添加虚拟服务点
//        for (int i=0;i<costNodeNum;i++){
//            MinCostFlow.addEdge(mincostgraph,netNodeNum,costInfoList[i].netNode,Integer.MAX_VALUE,serverCost);
//            MinCostFlowBF.addEdge(mincostbfgraph,netNodeNum,costInfoList[i].netNode,Integer.MAX_VALUE,serverCost);
//        }


        //添加虚拟消费点
        for (int i=0;i<costNodeNum;i++){
            MinCostFlow.addEdge(mincostgraph,costInfoList[i].netNode,netNodeNum+1,costInfoList[i].require,0);
           // MinCostFlow.addEdge(mincostgraph1,costInfoList[i].netNode,netNodeNum+1,costInfoList[i].require,0);
//            MinCostFlowBF.addEdge(mincostbfgraph,costInfoList[i].netNode,netNodeNum+1,costInfoList[i].require,0);
        }


        new GeneticAlg(mincostgraph,netNodeNum, costInfoList,serverCost).Ga();

/*        int[] a=new int[]{
                4, 11, 17, 20, 22, 25, 30, 36, 37, 46, 49, 54, 56, 57, 60, 61, 66, 69, 72, 81, 83, 85, 89, 97, 103, 106, 108, 114, 123, 126, 128, 131, 135, 137, 139, 147, 149, 155, 159
        };*/

//        MinCostFlow.addEdge(mincostgraph,netNodeNum,0,Integer.MAX_VALUE,0);
//        MinCostFlow.addEdge(mincostgraph,netNodeNum,3,Integer.MAX_VALUE,0);
//        MinCostFlow.addEdge(mincostgraph,netNodeNum,22,Integer.MAX_VALUE,0);
/*        for (int i=0;i<a.length;i++){
            MinCostFlow.addEdge(mincostgraph,netNodeNum,a[i],Integer.MAX_VALUE,0);
        }*/
        /*
        MinCostFlow.addEdge(mincostgraph,netNodeNum,4,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,11,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,17,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,20,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,22,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,25,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,30,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,36,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,37,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,46,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,49,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,54,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,56,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,57,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,60,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,61,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,66,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,69,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,72,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,81,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,83,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,85,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,89,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,97,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,103,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,106,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,108,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,114,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,123,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,126,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,128,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,131,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,135,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,137,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,139,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,147,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,149,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,155,Integer.MAX_VALUE,0);
        MinCostFlow.addEdge(mincostgraph,netNodeNum,159,Integer.MAX_VALUE,0);*/

//
//        MinCostFlowBF.addEdge(mincostbfgraph,netNodeNum,0,Integer.MAX_VALUE,0);
//        MinCostFlowBF.addEdge(mincostbfgraph,netNodeNum,3,Integer.MAX_VALUE,0);
//        MinCostFlowBF.addEdge(mincostbfgraph,netNodeNum,24,Integer.MAX_VALUE,0);




        int minCost=serverCost*costNodeNum;
        /*************************网络参数初始化End************************************/

        /*****************************************************************************/

//        getLowestCost(netNodeNum,0,graph);
//        MinCostFlow.minCostFlow2(mincostgraph, netNodeNum, netNodeNum+1, Integer.MAX_VALUE,costInfoList);
//        int[] res = MinCostFlow.minCostFlow(mincostgraph, netNodeNum, netNodeNum+1, Integer.MAX_VALUE);
//        int flow = res[0];
//        int flowCost = res[1];
//        System.out.println("flow="+flow+"    flow cost = "+flowCost);
//        getPath(mincostgraph);
//        DFS_Path dfs_path=new DFS_Path(mincostgraph);
//        List<MinCostFlow.Edge>[] graph2=dfs_path.creatGraph();
//        getPath(mincostgraph);
//        int[] result=MinCostFlowBF.minCostFlow(mincostbfgraph,netNodeNum,netNodeNum+1,Integer.MAX_VALUE);

        return new String[]{};
    }
    //边
    static class Edge{
        public int from,to,cap,cost,flow;
        Edge(int from,int to,int cap,int cost){
            this.from=from;
            this.to=to;
            this.cap=cap;
            this.cost=cost;
            this.flow=0;
        }
    }
    //消费节点
    static class Cost{
        public int netNode,require;
        public Cost(int netNode,int require){
            this.netNode=netNode;
            this.require=require;
        }
    }
    //求解服务器到各个顶点的最小费用
//    public static int[] getLowestCost(int netNodeNum, int start, List<Edge>[] graph){
//        boolean[] visit=new boolean[netNodeNum];
//        int[] distTo=new int[netNodeNum];
//        int[] num=new int[netNodeNum];
//        Edge[] edgeTo=new Edge[netNodeNum];
//        for (int i=0;i<netNodeNum;i++){
//            distTo[i]=Integer.MAX_VALUE;
//            visit[i]=false;
//        }
//        distTo[start]=0;
//        visit[start]=true;
//        num[start]=1;
//        Queue<Integer> q=new LinkedList<>();
//        q.offer(start);
//        while (!q.isEmpty()){
//            int a=q.poll();
//            visit[a]=false;
//            for (Edge edge:graph[a]){
//                int w=edge.to;
//                if (distTo[w]>distTo[edge.from]+edge.cost){
//                    distTo[w]=distTo[edge.from]+edge.cost;
//                    edgeTo[w]=edge;
//                    if (!visit[w]){
//                        q.offer(w);
//                        visit[w]=true;
//                    }
//                }
//            }
//        }
//        return distTo;
//    }

//    public static int[] getPath(int start,int end,List<Edge> graph){
//
//    }
//    public static int MCMF(List<Edge> graph){
//
//    }

    public static void getPath(List<MinCostFlow.Edge>[] graph){
        for (int i=0;i<graph.length;i++){
            for (MinCostFlow.Edge edge:graph[i]){
                if (edge.f>0) System.out.println(edge);
            }
        }
    }


}
