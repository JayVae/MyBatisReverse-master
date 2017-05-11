package com.cacheserverdeploy.deploy;

import com.filetool.util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;
import static java.lang.System.setOut;


public class GeneticAlg {
	
	static int chromSize;
	static int popSize=20;
	double mutep=0.01;
	double crossp=06;
	int iternum=1000;
	int bestfit=Integer.MAX_VALUE;

	List<MinCostFlow.Edge>[] mincostgraph;
	//List<MinCostFlow.Edge>[] mincostgraph1;
	int netNodeNum;
	Deploy.Cost[] costInfoList;
	int serverCost;

	static int[] chrom = new int[chromSize];
	static int[][] population = new int[popSize][];

	public GeneticAlg(List<MinCostFlow.Edge>[] graph, int netNodeNum, Deploy.Cost[] costInfoList,int serverCost) {
		this.chromSize=netNodeNum;
		this.chrom = new int[netNodeNum];
		this.population = new int[popSize][netNodeNum];
		this.mincostgraph=graph;
		this.netNodeNum=netNodeNum;
		this.costInfoList=costInfoList;
		this.serverCost=serverCost;
	}





	public void Ga(){
		init();

		while(true){
			int[] fit=fitnessCal();
			selectPop(fit);
			evolution();
		}
	}
//初始化需要改进
	public void init(){
		for(int[] pop:population){
			System.out.println(chromSize);
			System.out.println(chrom.length);
			for(int i=0;i<chrom.length;i++){
				pop[i]=(int)(2*Math.random());
				System.out.println(pop[i]);
				System.out.println("----------------------------------");
			}
		}
		
	}
	
	public int[] fitnessCal(){
		List<Integer> serverList=new ArrayList<Integer>();
		int[] fit=new int[popSize];
		List<Integer> bestList=new ArrayList<Integer>();
		for(int i=0;i<popSize;i++){		
			for(int j=0;j<chrom.length;j++){
				if(population[i][j]==1){
					serverList.add(j);
					MinCostFlow.addEdge(mincostgraph,netNodeNum,j,Integer.MAX_VALUE,0);
				}
			}

			String[] res = MinCostFlow.minCostFlow2(mincostgraph, netNodeNum, netNodeNum+1, Integer.MAX_VALUE,costInfoList);
			fit[i]=Integer.parseInt(res[2])+serverList.size()*serverCost;
			if(bestfit>fit[i]){
				bestfit=fit[i];
				bestList=serverList;
			}
			//mincostgraph=mincostgraph1;
		}
		return fit;
	}
	
	public void selectPop(int[] fit){
		Arrays.sort(fit);
		double[] fitvalue=new double[fit.length];
		
		fitvalue[0]=1/(double)fit[fit.length-1];
		double sum=fitvalue[0];
		double[] rand=new double[fit.length];
		rand[0]=Math.random();
		for(int i=1;i<fit.length;i++){
			fitvalue[i]=1/(double)fit[fit.length-1-i]+fitvalue[i-1];
			sum+=fitvalue[i];
			rand[i]=Math.random();
		}
		
		Arrays.sort(rand);
		int newin=0;
		int fitin=0;
		while(newin<fit.length){
			if(rand[newin]<fitvalue[fitin]/sum){
				population[newin]=population[fitin];
				newin=newin+1;
				System.out.println(newin+" "+rand[newin]);
			}else{
				fitin=fitin+1;
				System.out.println(fitin+"--"+fitvalue[fitin]/sum+"--"+sum+"--"+fitvalue[fitin]);
			}
		}
	}
	
	public void evolution(){
		//变异
		for(int i=0;i<popSize;i++){
			if(Math.random()<mutep){
				int mpoint=new Random().nextInt(popSize+1);
				if(population[i][mpoint]==1){
					population[i][mpoint]=0;
				}else{
					population[i][mpoint]=1;
				}
				
			}
		}
		//交叉
		for(int i=0;i<popSize;i=i+2){
			if(Math.random()<mutep){
				int cpoint=new Random().nextInt(popSize+1);
				for(int j=0;j<cpoint;j++){
					int tmp=population[i][j];
					population[i][j]=population[i+1][j];
					population[i+1][j]=tmp;
				}
			}
				
				
		}
	}
}
