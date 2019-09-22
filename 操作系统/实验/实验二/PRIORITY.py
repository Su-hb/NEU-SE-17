class PCB:
    def __init__(self,PName,NeedTime,Priority = 0):
        self.PName = PName
        self.NeedTime = NeedTime
        self.Priority  = Priority
        self.statue = "ready"
        self.waitingTime = 0
        self.CPUtime  = 0
    def isExit(self):
        #判断是否要执行完
        if(self.NeedTime == 0):
            return True
    def afterRun(self):
        #执行操作 所需时间和优先级都减一
        self.NeedTime -= 1
        self.Priority -= 1
        if(self.isExit()):
            self.statue = "finish"
        else:
            self.statue = "working"
    def waiting(self):
        #等待操作 等待的时间加一
        self.waitingTime += 1

def run(time,PCBList):
    #运行操作
    PCBList.sort(key=lambda x: x.Priority, reverse=True)
    flag = True
    for i in PCBList:
        if(i.statue == "finish"):
            continue
        elif(i.statue == "ready" and flag):
            flag = False
            i.afterRun()
        else:
            if(i.statue == "working"):
                i.statue = "ready"
            i.waiting()
    PCBList.sort(key=lambda x: x.PName, reverse=False)
    printList(time,PCBList)

def judge(PCBList):
    #判断是否所有PCB均执行完
    flag = False
    for i in PCBList:
        if(i.NeedTime>0):
            flag = True
    return flag
def printList(time,PCBList):
    #打印操作
    print("CPUTIME:",time)
    print("NAME\tNEEDTIME\tPRIORITY\tSTATE")
    PCBList.sort(key=lambda x: x.PName, reverse=False)
    for i in  PCBList:
        print(i.PName,"\t\t",i.NeedTime,"\t\t\t",i.Priority,"\t\t",i.statue)

def printWaitingTime(PCBList):
    #打印PCB队列所需的等待时间
    PCBList.sort(key=lambda x: x.PName, reverse=False)
    for i in PCBList:
        print(i.PName, "\t \t ", i.waitingTime)

def main():
    PPP = []
    numOfPcb = int(input("请输入你想输入的进程数："))
    for i in range(numOfPcb):
        name = input("第%d的进程的名字："%(i+1))
        time = input("第%d的进程的所需时间："%(i+1))
        prirotity = input("第%d的进程的优先级："%(i+1))
        p = PCB(name,int(time),int(prirotity))
        PPP.append(p)


    print("OUTPUT  OF  PRIORITY：")
    tmptime = 0
    while(judge(PPP)):
        run(tmptime,PPP)
        tmptime+=1

    print("NAME\tWaitingTime")
    printWaitingTime(PPP)


if __name__ == '__main__':
    main()