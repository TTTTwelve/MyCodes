package quickFindUn;

/**
 * Created by jiaqiwu on 2017/12/10.
 */
public class QuickFindUn {

    private int id[];
    private int sz[];

    public QuickFindUn(int N){
        id = new int[N];
        for(int i = 0;i < N; i++){
            id[i] = i;
            sz[i] = 1;
        }
//        for(int num : id){
//            System.out.println(num);
//        }
    }

    public int root(int i ){
        while (i != id[i]){
//            id[i] = id[id[i]]; //path-compression
            i =id[i];
        }
        return i;
    }


    public boolean connected(int p,int q){
//        return id[p] == id[q];  //quickFind

        return root(p)==root(q); //quickUnion
    }

    public void union(int p,int q){

//  quickFind
//        int pid = id[p];
//        int qid = id[q];
//        for(int i = 0;i < id.length;i++){
//            if(id[i] == pid){
//                id[i] = qid;
//            }
//        }

//  quickUnion
//        int i = root(p);
//        int j = root(q);
//        id[i] = j;

//  weight-quickUnion
        int i = root(p);
        int j = root(q);

        if(i==j){
            return;
        }else if(sz[i] < sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }else{
            id[j] = i;
            sz[i] += sz[j];
        }

    }
}
