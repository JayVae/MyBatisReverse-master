function [pop]=init(popsize,chromlength)
D=[1067.528352	1028.554565	1016.072549	1009.874773	982.184994	1007.999654	1066.737403	1182.626269	1215.594217	1202.589107	1197.525761	1210.540024	1114.850688	1165.273689	1213.457764	1243.627634	1239.041122	1231.812427	1220.279806	1213.474786	1152.883619	1158.921441	1117.867934	1072.406881
];
time=[1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20	21	22	23	24
];
pop=zeros(popsize,chromlength);

pop=zeros(50,96);
pop1=zeros(1,24);
pop2=zeros(1,24);
pop3=zeros(1,24);
pop4=zeros(1,24);

for j=1:50
    for i=1:24
        if i==1
            pop1(i)= 560/1780*D(i)*randi([9,11])*0.1;
            pop3(i)= 310/1780*D(i)*randi([9,11])*0.1;
            pop4(i)= 200/1780*D(i)*randi([9,11])*0.1;
            pop2(i)= D(i)-pop1(i)-pop4(i)-pop3(i);
        else
            tmp=(D(i)-D(i-1))/3;
            pop1(i)=pop1(i-1)+tmp*randi([8,12])*0.1;
            pop3(i)=pop3(i-1)+tmp*randi([8,12])*0.1;
            pop4(i)=pop4(i-1)+tmp*randi([8,12])*0.1;
            pop2(i)= D(i)-pop1(i)-pop4(i)-pop3(i);
        end
    end
    pop(j,:)=[pop1,pop2,pop3,pop4];
%     if(mod(j,5)==0)
%         figure
% 
%         f=plot(time,pop1,'-o',time,pop2,'-s',time,pop3,'-d',time,pop4,'-x');
%         xlabel('时段/h');
%         ylabel('机组出力/MW');
%         legend('Pg1','Pg2','Pg3','Pg4');
%         set(f(1),'LineWidth',3);
%         set(f(2),'LineWidth',3);
%         set(f(3),'LineWidth',3);
%         set(f(4),'LineWidth',3); 
%     end
end
% A=[373.6595875	364.1936534	351.3003984	355.8701597	346.4042256	354.7277194	407.7695914	612.7560259	610.8791597	597.2514787	606.8806186	616.5913613	525.2777386	583.3789891	607.1254272	617.488993	629.4846163	562.8150634	507.4883107	481.7018006	432.4136611	431.2712208	407.7695914	387.6952829
% ];
% B=[692.8023022	663.3333845	663.7570932	652.9957477	601.4723257	571.8198823	623.5080664	824.705986	824.9178404	825.5534034	810.8659758	814.1564949	725.6887295	802.1477527	826.5372541	846.3134187	861.5609883	791.3770864	780.8746876	794.2109689	756.4475368	763.6217671	733.3808531	699.5528238
% ];
% D=[1067.528352	1028.554565	1016.072549	1009.874773	948.8244278	927.4741493	1032.308935	1438.899474	1437.232797	1424.227687	1419.164341	1432.178604	1252.217434	1386.912269	1435.096344	1465.266214	1492.53665	1355.546342	1289.651361	1277.188682	1190.050059	1196.087881	1142.291595	1088.335355
% ];
% G1=ones(1,24);
% %G2=ones(1,24);
% G3=260.*ones(1,24);
% G4=180.*ones(1,24);
% for i=1:24
%    if(A(i)<510)
%        G3(i)=310/510*A(i);
%        G4(i)=200/510*A(i);
%    else 
%         G3(i)=310*0.1*randi([8,10]);
%         G4(i)=200*0.1*randi([8,10]);
%    end
% end
% 
% for i=1:24
%        G1(i)=560/1270*B(i);
%        %G2(i)=(D(i)-G3(i)*0.955-G4(i)*0.945)/0.968-G1(i);
%        %G2(i)=650/1130*B(i);
% end
% 
% 
% for i=1:50
%     for j=1:24    
%             pop1(j)=G1(j)+randi([-30,30]);
%             while(pop1(j)<150 || pop1(j)>560)
%                 pop1(j)=G1(j)+randi([-30,30]);
%             end
%     
%             pop3(j)=G3(j)+randi([-20,20]);%会不会局部早熟
%             while(pop3(j)<100 || pop3(j)>310)
%                 pop3(j)=G3(j)+randi([-40,20]);
%             end
%     
%     
%             pop4(j)=G4(j)+randi([-25,25]);%会不会局部早熟
%             while(pop4(j)<0 || pop4(j)>200)
%                 pop4(j)=G4(j)+randi([-50,25]);
%             end
%             
%             pop2(j)=(D(j)-pop3(j)*0.955-pop4(j)*0.945)/0.968-pop1(j);
%             while( pop2(j)>710 )
%                 tmp=pop2(j);
%                 pop2(j)=710-randi([50,120]);
%                 pop1(j)=pop1(j)+tmp-pop2(j);
%             end
%     end
%     pop(i,:)=[pop1,pop2,pop3,pop4];
% end