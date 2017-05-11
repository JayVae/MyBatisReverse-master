function [newpop]=crossover(pop,pc)

[px,py]=size(pop);

newpop = zeros(size(pop));

for i=1:2:px-1

if(rand<pc)

    %cpoint = round(rand*py);
    cpoint=randi([1,96]);
%newpop(i,:) = [pop(i,1:cpoint),pop(i+1,cpoint+1:py)];
%newpop(i+1,:) = [pop(i+1,1:cpoint),pop(i,cpoint+1:py)];
    if(cpoint>=1 && cpoint<=24)
        newpop(i,:) = [pop(i,1:cpoint-1),pop(i+1,cpoint),pop(i,cpoint+1:cpoint+23),pop(i,cpoint+24)-pop(i,cpoint)+pop(i+1,cpoint),pop(i,cpoint+25:py)];
        newpop(i+1,:)=[pop(i+1,1:cpoint-1),pop(i,cpoint),pop(i+1,cpoint+1:cpoint+23),pop(i+1,cpoint+24)-pop(i+1,cpoint)+pop(i,cpoint),pop(i+1,cpoint+25:py)];
    end
    if(cpoint>=25 && cpoint<=48)
        newpop(i,:) = [pop(i,1:cpoint-25),pop(i,cpoint-24)+pop(i+1,cpoint)-pop(i,cpoint),pop(i,cpoint-23:cpoint-1),pop(i+1,cpoint),pop(i,cpoint+1:py)];
        newpop(i+1,:) = [pop(i+1,1:cpoint-25),pop(i+1,cpoint-24)+pop(i,cpoint)-pop(i+1,cpoint),pop(i+1,cpoint-23:cpoint-1),pop(i,cpoint),pop(i+1,cpoint+1:py)];
    end
    if(cpoint>=49 && cpoint<=72)
        newpop(i,:) = [pop(i,1:cpoint-25),pop(i,cpoint-24)+pop(i+1,cpoint)-pop(i,cpoint),pop(i,cpoint-23:cpoint-1),pop(i+1,cpoint),pop(i,cpoint+1:py)];
        newpop(i+1,:) = [pop(i+1,1:cpoint-25),pop(i+1,cpoint-24)+pop(i,cpoint)-pop(i+1,cpoint),pop(i+1,cpoint-23:cpoint-1),pop(i,cpoint),pop(i+1,cpoint+1:py)];
    end
    if(cpoint>=73 && cpoint<=96)
        newpop(i,:) = [pop(i,1:cpoint-49),pop(i,cpoint-48)+pop(i+1,cpoint)-pop(i,cpoint),pop(i,cpoint-47:cpoint-1),pop(i+1,cpoint),pop(i,cpoint+1:py)];
        newpop(i+1,:) = [pop(i+1,1:cpoint-49),pop(i+1,cpoint-48)+pop(i,cpoint)-pop(i+1,cpoint),pop(i+1,cpoint-47:cpoint-1),pop(i,cpoint),pop(i+1,cpoint+1:py)];
    end
else

    newpop(i,:)=pop(i,:);

    newpop(i+1,:)=pop(i+1,:);

end

end