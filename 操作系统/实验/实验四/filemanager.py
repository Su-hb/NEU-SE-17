import os
import random

root = "pythontest"+str(random.randint(0,999999999))
os.system("mkdir %s"%root)
os.chdir("./%s"%root)


while(1):
    print("###File Manager###")
    print("1.创建文件")
    print("2.进入文件")
    print("3.退出当前文件")
    print("4.删除文件")
    print("5.目录列表")
    print("6.返回主目录 并打印树")
    print("7.退出系统")


    method = eval(input("输入操作:  "))

    if(method == 1):
        name = input("需创建文件的名字： ")
        os.mkdir(name)
        print("创建成功")
    if(method == 2):
        name = input("需进入的文件夹的名字： ")
        os.chdir(name)
        print("成功进入%s"%name)
    if(method == 3):
        os.chdir(os.pardir)
        print("退出当前文件")
    if(method == 4):
        name = input("需要删除的文件夹的名字：")
        os.remove(name)
        print("删除成功")
    if(method == 5):
        os.system("ls")
    if(method == 6):
        os.chdir("./%s"%root)
        os.system("tree")
    if(method == 7):
        print("退出当前系统")
        break