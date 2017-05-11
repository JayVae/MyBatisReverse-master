package com.mcmf.ga;

import com.filetool.util.Constants;
import com.filetool.util.MathUtil;
import com.ga.Fitness;
import com.graph.Graph;
import com.graph.Node;
import com.mcmf.MCMF_SPFA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xjtu_yjw on 2017/3/19.
 */
public class MyFitness implements Fitness<MyVector, Integer> {

    private final int factor = 100;

    private Graph graph;

    private int service_cost;

    private int consumer_cap;

    private int max_flow;

    private int min_cost;

    public MyFitness(Graph graph) {
        this.graph = graph;
        service_cost = graph.getService_cost();
        consumer_cap = graph.getTotal_consumer_cap();
    }


    @Override
    public Integer calculate(MyVector chromosome) {

        calculate_cost_flow(chromosome);

        List<Integer> indexs = MathUtil.index_one(chromosome.vector);

        int s_cost = indexs.size() * service_cost;

        int path_cost = min_cost;
        int total_cost = s_cost + path_cost + (consumer_cap - max_flow) * factor;

        return total_cost;
    }

    private void calculate_cost_flow(MyVector chromosome) {
        graph.update_service_arrinode(chromosome.vector);
        String key = MathUtil.proper_value(chromosome.vector);
        int[] ret;
        if (graph.searched_map.get(key) != null) {
            ret = graph.searched_map.get(key);
        } else {

            List<Node> nodes = graph.getVisual_service_node().getArriNodes();
            int total_out_cap = 0;
            for (Node node : nodes) {
                total_out_cap += node.getOut_cap();
            }
           /* if (total_out_cap < graph.getTotal_consumer_cap()) {
                ret = new int[]{0, Constants.MAX_VALUE};
                graph.searched_map.put(key, ret);
            } else {
                MCMF_SPFA mcmf_spfa = new MCMF_SPFA(graph,true);
                ret = mcmf_spfa.min_cost_max_flow_adj();
                graph.searched_map.put(key, ret);

            }*/
            MCMF_SPFA mcmf_spfa = new MCMF_SPFA(graph,true);
            ret = mcmf_spfa.min_cost_max_flow_adj();
            graph.searched_map.put(key, ret);

        }
        max_flow = ret[0];
        min_cost = ret[1];
    }

}
