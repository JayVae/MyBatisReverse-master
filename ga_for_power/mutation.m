function [newpop] = mutation(pop,pm)

[px,py] = size(pop);

newpop = ones(size(pop));

for i=1:px
    
    newpop(i,:) = pop(i,:);
    
    if(rand<pm)

        mpoint = randi([1,96]);
        
        if(mpoint>=1 && mpoint<=24)
            tmp=randi([-30,30]);
            newpop(i,mpoint) = newpop(i,mpoint)+tmp;
            newpop(i,mpoint+24) = newpop(i,mpoint+24)-tmp;
        end
        if(mpoint>=25 && mpoint<=48)
            tmp=randi([-30,30]);
            newpop(i,mpoint) = newpop(i,mpoint)+tmp;
            newpop(i,mpoint-24) =  newpop(i,mpoint-24)-tmp;
        end
        if(mpoint>=49 && mpoint<=72)
            tmp=randi([-30,30]);
            newpop(i,mpoint) = newpop(i,mpoint)+tmp;
            newpop(i,mpoint-24) =  newpop(i,mpoint-24)-tmp;
        end
        if(mpoint>=73 && mpoint<=96)
            tmp=randi([-30,30]);
            newpop(i,mpoint) = newpop(i,mpoint)+tmp;
        end
    end

end