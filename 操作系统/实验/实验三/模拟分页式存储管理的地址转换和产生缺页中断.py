PAGE_MEMORY = 7
import queue
class page:
    def __init__(self,pageNumber,Tag,MainMemory,location,change = 0):
        self.pageNumber = pageNumber
        self.Tag = Tag
        self.MainMemort = MainMemory
        self.change = change
        self.Location = location
    def pagenoFlaut(self):
        if(self.Tag == 1):
            return True
        return False
class job:
    def __init__(self,op,pageNumber,unit):
        self.op = op
        self.pageNumber = pageNumber
        self.unit = unit
    def getRealLocation(self,pages):
        if (pages[self.pageNumber-1].pagenoFlaut()):
            return 128*pages[self.pageNumber-1].MainMemort+int(self.unit)
        else:
            return ("*"+str(self.pageNumber))
def printPages(pages):
    print("页号  标志  主存块号  是否改变  硬盘地址")
    for i in pages:
        print(i.pageNumber,"   ",i.Tag,"   ",i.MainMemort,"     ",i.change,"   ",i.Location)
def init(pages,jobs):

    pages.append(page(0, 1, 5, "011"))
    pages.append(page(1, 1, 8, "012"))
    pages.append(page(2, 1, 9, "013"))
    pages.append(page(3, 1, 1, "021"))
    pages.append(page(4, 0, None, "022"))
    pages.append(page(5, 0, None, "023"))
    pages.append(page(6, 0, None, "121"))


    jobs.append(job("+", 0, "070"))
    jobs.append(job("+", 1, "050"))
    jobs.append(job(" ", 2, "015"))
    jobs.append(job("存", 3, "021"))
    jobs.append(job("取", 0, "056"))
    jobs.append(job(" ", 6, "040"))
    jobs.append(job("移位", 4, "053"))
    jobs.append(job("+", 5, "023"))
    jobs.append(job("存", 1, "037"))
    jobs.append(job("取", 2, "078"))
    jobs.append(job("+", 4, "001"))
    jobs.append(job("存", 6, "084"))
def FIFO(pages,jobs):
    que = queue.Queue(maxsize=4)
    for j in jobs:
        #请求序列为 0 1 2 3 0 6 4 5 1 2 4 6
        #页表大小：7
        if(pages[j.pageNumber].pagenoFlaut()):
            if(not que.full()):
                que.put(j.pageNumber)
        else:
            if(que.full()):
                tmppageNumber = que.get()
                pages[tmppageNumber].Tag = 0
                pages[j.pageNumber].MainMemort = pages[tmppageNumber].MainMemort
                pages[j.pageNumber].change = 1
                que.put(j.pageNumber)
        myque = que.__dict__
        print(myque['queue'])
        printPages(pages)


def main():
    #页表和指令序列初始化
    pages = []
    jobs = []
    init(pages,jobs)

    #获取物理地址
    print("##############获取物理地址###############")
    for j in jobs:
        local = j.getRealLocation(pages)
        print(local)
    print("##############FIFO###############")
    FIFO(pages,jobs)
if __name__ == '__main__':
    main()