function [objvalue]=cal_objvalue(pop)
objvalue=zeros(50,1);
for x=1:50
    for y=1:24
        objvalue(x)=objvalue(x)+4.528-5.094*pop(x,y)+4.586*pop(x,y)*pop(x,y);%+0.0001*exp(8*pop(x,y));
    end
    for y=25:48
        objvalue(x)=objvalue(x)+4.091-5.554*pop(x,y)+6.490*pop(x,y)*pop(x,y);%+0.0002*exp(3*pop(x,y));
    end
    for y=49:72
        objvalue(x)=0.3*objvalue(x)+0.7*0.5*(0.482*pop(x,y)*pop(x,y)+79.7*pop(x,y)+487+350*pop(x,y));
    end
    for y=73:96
        objvalue(x)=objvalue(x)+0.7*0.5*(540*pop(x,y));
    end
     objvalue(x)=1/objvalue(x);
end
