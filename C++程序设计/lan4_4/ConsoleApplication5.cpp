template<class T> class List {
public:
	List();  //构造函数
	void Add(T&);  //在Link表头添加新结点
	void Remove(T&);  //在Link中删除含有特定值的元素
	T* Find(T&);  //查找含有特定值的结点
	void PrintList();  // 打印输出整个链表
	~List();
protected:
	struct Node {
		Node* pNext;
		T* pT;
	};
	Node* pFirst;
	template<class T>
	void Add();
	//链首结点指针
};
template<class T>
void List<T>::Add(T & elem) {
	elem
}