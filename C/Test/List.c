#include <iostream>

#define TRUE 1
#define FALSE 0
#define OK 1
#define ERROR 0
#define INFEASIBLE -1
#define OVERFLOW -2
#define LIST_INIT_SIZE 100
#define LISTINCREMENT 10
#define ElemType int
typedef struct 
{
	ElemType *elem;
	int length;
	int listsize;
} SqList;
int InitList_Sq(SqList &L) 
{
	int* a1,b2,c3;
	int *a2,*b2,*c3;
	L.elem = (ElemType *)malloc(LIST_INIT_SIZE * sizeof(ElemType));
	if (!L.elem)exit(OVERFLOW);
	L.length = 0;
	L.listsize = LIST_INIT_SIZE;
	return OK;
}//InitList_Sq
int ListInsert_Sq(SqList &L, int i, ElemType e) 
{
	if (i<1 || i>L.length + 1) {
		std::cout << "ERROR";
		return ERROR;
	}
	if (L.length >= L.listsize) {
		ElemType *newbase;
		newbase = (ElemType *)realloc(L.elem, (L.listsize + LISTINCREMENT) * sizeof(ElemType));
		if (!newbase)exit(OVERFLOW);
		L.elem = newbase;
		L.listsize += LISTINCREMENT;
	}
	ElemType *q;
	q = &(L.elem[i - 1]);
	ElemType *p;
	for (p = &(L.elem[L.length - 1]); p >= q; --p)
		*(p + 1) = *p;
	*q = e;
	++L.length;
	return OK;
}//ListInsert_Sq
int ListDelete_Sq(SqList &L, int i, ElemType &e) {
	if (i<1 || i>L.length + 1) {
		std::cout << "ERROR";
		return ERROR;
	}
	ElemType *p,*q;
	p = &(L.elem[i - 1]);
	e = *p;
	q = L.elem + L.length - 1;
	for (++p; p <= q; ++p)
		*(p - 1) = *p;
	--L.length;
	return OK;
}//ListDelete_Sq
int LocateElem_Sq(SqList L, ElemType e,int(*compare)(ElemType, ElemType)) {
	int i;
	ElemType *p;
	i = 1;
	p = L.elem;
	while (i <= L.length && !(*compare)(*p++, e))
		++i;
	if (i <= L.length) return i;
	else return 0;
} // LocateElem_Sq
void MergeList_Sq(SqList La, SqList Lb, SqList &Lc) { 
	ElemType *pa, *pb, *pc, *pa_last, *pb_last;
	pa = La.elem;  pb = Lb.elem;
	Lc.listsize = Lc.length = La.length + Lb.length;
	pc = Lc.elem = (ElemType *)malloc(Lc.listsize * sizeof(ElemType));
	if (!Lc.elem)
		exit(OVERFLOW);
	pa_last = La.elem + La.length - 1;
	pb_last = Lb.elem + Lb.length - 1;
	while (pa <= pa_last && pb <= pb_last) {
		if (*pa <= *pb) *pc++ = *pa++;
		else *pc++ = *pb++;
	}
	while (pa <= pa_last) *pc++ = *pa++;
	while (pb <= pb_last) *pc++ = *pb++;
} // MergeList
int compare(ElemType x, ElemType y) {
	if (x == y) {
		return TRUE;
	}
	else {
		return FALSE;
	}
}
int main()
{
	SqList SqList_1;
	SqList SqList_2;
	SqList SqList_3;
	ElemType e;
	InitList_Sq(SqList_1);
	InitList_Sq(SqList_2);
	InitList_Sq(SqList_3);
	ListInsert_Sq(SqList_1, 1, 0);
	ListInsert_Sq(SqList_1, 2, 1);
	ListInsert_Sq(SqList_1, 3, 2);
	ListInsert_Sq(SqList_2, 1, 11);
	ListInsert_Sq(SqList_2, 2, 12);
	ListInsert_Sq(SqList_2, 3, 13);
	std::cout << SqList_1.elem[0] << " " << SqList_1.elem[1] << " " << SqList_1.elem[2] << "\n"<<"Length = " << SqList_1.length << "\n";
	std::cout << "Number 1 is at " << LocateElem_Sq(SqList_1, 1, (*compare)) << "\n";
	ListDelete_Sq(SqList_1, 1,e);
	std::cout << "Deleted = " << e << "\n";
	std::cout << SqList_1.elem[0] << " " << SqList_1.elem[1] << " " << SqList_1.elem[2] << "\n"<<"Length = " << SqList_1.length << "\n"; 
	std::cout << "Number 1 is at " << LocateElem_Sq(SqList_1, 1, (*compare)) << "\n";
	ListInsert_Sq(SqList_1, 3, 3);
	MergeList_Sq(SqList_1, SqList_2, SqList_3);
	std::cout << SqList_3.elem[0] << " " << SqList_3.elem[1] << " " << SqList_3.elem[2] << " " << SqList_3.elem[3] << " " << SqList_3.elem[4] << " " << SqList_3.elem[5] << "\n" << "Length = " << SqList_3.length << "\n";
	return 0;
}