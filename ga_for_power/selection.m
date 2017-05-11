function [newpop] = selection(pop,fitvalue)

%构造轮盘

[px,py]=size(pop);

totalfit = sum(fitvalue);

p_fitvalue = fitvalue/totalfit;

p_fitvalue = cumsum(p_fitvalue);%概率求和排序

%-------

ms = sort(rand(px,1));%从小到大排列

fitin = 1;

newin = 1;

while newin<=px

if (ms(newin))<p_fitvalue(fitin)

newpop(newin,:)=pop(fitin,:);

newin=newin+1;

else fitin=fitin+1;

end

end


