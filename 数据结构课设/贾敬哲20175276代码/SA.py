"""
    :author:贾敬哲
    :time: 2019/1/20
"""
import random
import numpy as np
import math

num_city=13#城市总数
initial_t=120#初始温度
lowest_t=0.001#最低温度
M=150#当连续多次都不接受新的状态，开始改变温度
iteration=500#设置迭代次数
#景点信息
name = ["观云台","飞流瀑","狮子山","一线天","仙云石","仙武湖","朝日峰","红叶亭","九曲桥","北门","碧水亭","花卉园","碧水潭"]

dis_mat = [[0,1,999,33,999,999,999,999,999,999,999,999,14],
[1,0,2,999,24,999,999,999,999,999,999,999,999],
[999,2,0,3,999,999,999,999,999,22,20,999,999],
[33,999,3,0,4,999,999,999,999,999,999,999,999],
[999,24,999,4,0,5,999,999,999,999,999,999,999],
[999,999,999,999,5,0,999,999,6,999,999,999,999],
[999,999,999,999,999,999,0,21,999,999,9,11,999],
[999,999,999,999,999,999,21,0,999,999,999,12,13],
[999,999,999,999,999,6,999,999,0,7,999,999,999],
[999,999,22,999,999,999,999,999,7,0,8,999,999],
[999,999,20,999,999,999,9,999,999,8,0,999,999],
[999,999,999,999,999,999,11,12,999,999,999,0,999],
[14,999,999,999,999,999,999,13,999,999,999,999,0]]
#==========================================
#计算所有路径对应的距离
def cal_newpath(dis_mat,path):
    dis=0
    for j in range(num_city-1):
        dis=dis_mat[path[j]][path[j+1]]+dis
    dis=dis_mat[path[12]][path[0]]+dis#回家
    return dis
#==========================================
#点对点距离矩阵
#dis_mat=distance_p2p_mat()
#初始路径
path=list(range(13))
#初始距离
dis=cal_newpath(dis_mat,path)
#初始温度
t_current=initial_t

while (t_current>lowest_t):#外循环，改变温度
    count_m=0#M的计数
    count_iter=0#迭代次数计数
    while (count_m<M and count_iter<iteration):#内循环，连续多次不接受新的状态或者是迭代多次,跳出内循环        
        i=0
        j=0
        while(i==j):#防止随机了同一城市
            i=random.randint(1,12)
            j=random.randint(1,12)
        path_new=path.copy()
        path_new[i],path_new[j]=path_new[j],path_new[i]#任意交换两个城市的位置,产生新解
        #计算新解的距离
        dis_new=cal_newpath(dis_mat,path_new)
        #求差
        dis_delta=dis_new-dis
        #取0-1浮点随机数
        rand=random.random()
        #计算指数函数的值
        exp_d=math.exp(-dis_delta/t_current)
        #选择
        if dis_delta<0:
            path=path_new
            dis=dis_new
        elif exp_d>rand:
            path=path_new
            dis=dis_new    
        else:
            count_m=count_m+1
        count_iter=count_iter+1
    t_current=0.99*t_current#改变温度
#外循环结束
dis_min=dis
path_min=path
print('最短距离：',dis_min)
print('最短路径：',end=' ')
for i in path_min:
    print(name[i],end=' ')





