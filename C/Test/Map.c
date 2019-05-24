#include <stdio.h>
#include <string.h>
#include <malloc.h>
#define N 5
#define MAX 50
typedef struct A{
    int adjvex;
    struct A* nextArc;
}Arc;
typedef struct node{
    char data[N];
    Arc* firstArc;
}Node;
typedef struct graph{
    Node vex[MAX];
    int numv;
    int nume;
}Graph;

int getIndex(Graph G,char s[]){
    for(int i = 0; i < G.numv; i++){
        if(strcmp(G.vex[i].data,s) == 0)
            return i;
    }
    return -1;
}

void create(Graph &G){
    printf("输入顶点和弧的个数:\n");
    scanf("%d%d",&G.numv,&G.nume);
    printf("输入顶点信息:\n");
    for(int i = 0; i < G.numv; i++)
        scanf("%s",G.vex[i].data);
    ///初始化顶点数组
    for(int i = 0; i < G.numv; i++)
        G.vex[i].firstArc = NULL;   
    printf("输入边的信息:\n");
    char s[N],e[N];
    int u,v;
    for(int i = 0; i < G.nume; i++){                  //建立边表 
        scanf("%s%s",s,e);               //读入边（Vi，Vj）的顶点对序号
        u = getIndex(G,s);
        v = getIndex(G,e);
        Arc* p = (Arc*)malloc(sizeof(Arc));           //生成边表结点 
        p->adjvex = v;             //邻接点序号为v
        p->nextArc = NULL;          //边表置为空表
        p->nextArc = G.vex[u].firstArc;
        G.vex[u].firstArc = p;   //将新结点*p插入顶点Vi的边表头部 
        Arc* t = (Arc*)malloc(sizeof(Arc));
        t->nextArc = NULL;
        t->adjvex = u;              //邻接点序号为u
        t->nextArc = G.vex[v].firstArc;    //将新结点*t插入顶点Vj的边表头部
        G.vex[v].firstArc = t;
    }
}

void output(Graph G){                  //输出邻接矩阵 
    Arc* p;
    for(int i = 0; i < G.numv; i++){
        p = G.vex[i].firstArc;
        printf("%4s",G.vex[i].data);
        while(p != NULL){
            printf("%4s",G.vex[p->adjvex].data);
            p = p->nextArc;
        }
        printf("\n");
    }
}

///深度优先遍历 
int visit[2*MAX];
void dfs(Graph G,int s){
    Arc* p = G.vex[s].firstArc;
    if(!visit[s]){
        printf("%4s",G.vex[s].data);
        visit[s] = 1;
    }
    while(p != NULL){
        if(!visit[p->adjvex])
            dfs(G,p->adjvex);
        p = p->nextArc;
    }
}

///广度优先遍历 
int Q[2*MAX],F=0,R=0;
int visit_Q[MAX];
void bfs(Graph G,int s){
    printf("%4s",G.vex[s].data);
    visit_Q[s] = 1;				  //初始化标志向量
    Arc* p = G.vex[s].firstArc;
    while(p != NULL){               
        Q[R++] = p->adjvex;
        p = p->nextArc;
    }
    while(F < R){
        int node = Q[F++];
        if(!visit_Q[node]){
            printf("%4s",G.vex[node].data);
            visit_Q[node] = 1;
            p = G.vex[node].firstArc;             //取Vi的边表头指针
            while(p != NULL){                      //依次搜索Vi的邻接点Vj
                if(!visit_Q[p->adjvex])            //若Vj未访问过 
                    Q[R++] = p->adjvex;             //访问过的Vj入队 
                p = p->nextArc;                     //找Vi的下一个邻接点
            }
        }
    }
}

	int main(){
   		 Graph G;
    	 create(G);
		 printf("输出邻接矩阵:\n");
         printf("\n");
   		 output(G);
   		 printf("深度优先遍历:\n");
         printf("\n");;
   		 dfs(G,0);
   		 printf("\n广度优先遍历:\n");
         printf("\n");
  		 bfs(G,0);
   		 return 0;
}