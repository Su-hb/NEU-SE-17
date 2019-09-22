import networkx as nx  # 复杂网络需要用的包

import matplotlib.pyplot as plt  # 画图用

from pylab import *
mpl.rcParams['font.sans-serif'] = ['SimHei']




# Sample graph
G = nx.Graph()
f = open("./src/data.txt","r",encoding="utf_8_sig")
labels = {}
lines = f.readlines()
for line in lines:
    line = line.rstrip("\n")
    eles = line.split(" ")
    G.add_edge(eles[0],eles[1])
    labels[(eles[0],eles[1])]=eval(eles[2])
pos=nx.spring_layout(G)
nx.draw(G, pos,alpha=0.5, with_labels=True,node_size = 900)
nx.draw_networkx_edge_labels(G,pos,edge_labels=labels,font_size=15)
plt.savefig("./src/11.png")
plt.show()
""""
G.add_edge(0,1)
G.add_edge(1,2)
G.add_edge(2,3)
G.add_edge(1,3)

labels = {(0,1):'国际法天', (2,3):'第三方爱上',(1,3):'傲娇的丝氨酸'}

pos=nx.spring_layout(G)

nx.draw(G, pos,alpha=0.5, with_labels=True)
nx.draw_networkx_edge_labels(G,pos,edge_labels=labels,font_size=15)


plt.show()"""
