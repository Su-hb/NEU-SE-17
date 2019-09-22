from queue import  Queue
import random,threading,time

#生产者类
class Producer(threading.Thread):
    def __init__(self, name,queue):
        threading.Thread.__init__(self, name=name)
        self.data=queue

    def run(self):
        for i in range(5):
            print("%s 正在生产，%d 加入到队列 " % (self.getName(), i))
            self.data.put(i)
            time.sleep(random.randrange(10))
        print("%s 结束!" % self.getName())

#消费者类
class Consumer(threading.Thread):
    def __init__(self,name,queue):
        threading.Thread.__init__(self,name=name)
        self.data=queue
    def run(self):
        for i in range(5):
            val = self.data.get()

            print("%s 正在消费. 队列的 %d 被消费" % (self.getName(),val))
            time.sleep(random.randrange(10))
        print("%s 结束!" % self.getName())

def main():
    queue1 = Queue()
    producer1 = Producer('生产者①号',queue1)
    consumer1 = Consumer('消费者①号',queue1)

    producer2 = Producer('生产者②号', queue1)
    consumer2 = Consumer('消费者②号', queue1)
    producer1.start()
    consumer1.start()
    producer2.start()
    consumer2.start()
    producer1.join()
    consumer2.join()
    producer2.join()
    consumer1.join()
    print ('所有线程都结束了！')

if __name__ == '__main__':
    main()
