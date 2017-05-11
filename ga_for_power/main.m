function main()

clear;

clc;

global popsize;

global chromlength;

global pc;

global pm;


% global pop;

popsize = 50; %种群大小
chromlength = 96; %二进制编码长度
pc = 0.6; %交叉概率
pm = 0.001; %变异概率
%pop = initpop(popsize,chromlength); %初始种群
pop = init(popsize,chromlength);
filename='results.xlsx';
besfitplot=zeros(100);
iteration=zeros(100);
l=1;
for i=1:10000

[objvalue] = cal_objvalue(pop); %计算适应度值（函数值）

fitvalue = objvalue;

[bestindividual,bestfit]=best(pop,fitvalue);%寻找最优解

[newpop] = selection(pop,fitvalue); %选择操作

%[newpop] = crossover(newpop,pc); %交叉操作

[newpop] = mutation(newpop,pm); %变异操作

pop = newpop; %更新种群

if(rem(i,100)==1)
   besfitplot(l)=bestfit;
   iteration(l)=i;
   l=l+1;
end

% x2 = binary2decimal(bestindividual);
% 
% 
% 
% x1 = binary2decimal(newpop);
% 
% [y1] = cal_objvalue(newpop);

% if mod(i,10)==0
% 
% figure;
% 
% 
% 
% fplot('10*sin(5*x)+7*abs(x-5)+10',[0 10]);
% 
% hold on;
% 
% title(['迭代次数为 n=' num2str(i)]);
% 
% plot(x1,y1,'*');
% 
% end

end
xlswrite(filename,bestindividual);
Pg1=bestindividual(1:24);
Pg2=bestindividual(25:48);
Pg3=bestindividual(49:72);
Pg4=bestindividual(73:96);

time=[1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20	21	22	23	24
];

figure;

f=plot(time,Pg1,'-o',time,Pg2,'-s',time,Pg3,'-d',time,Pg4,'-x');
xlabel('时段/h');
ylabel('机组出力/MW');
legend('Pg1','Pg2','Pg3','Pg4');
set(f(1),'LineWidth',3);
set(f(2),'LineWidth',3);
set(f(3),'LineWidth',3);
set(f(4),'LineWidth',3);
axis([0 28 0 600]);
set(gca,'XTick',[0:1:28], 'YTick',[0,100,200,300,400,500,600]);

hold
figure

e=plot(iteration,besfitplot,'-o');
xlabel('iteration');
ylabel('convergence');
legend('C2');
set(e(1),'LineWidth',3);
axis([0 1000 0 1]);
set(gca,'XTick',[0:25:1000], 'YTick',[0:0.1:1]);





