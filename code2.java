import java.util.Scanner;

public class code2 {
    public static void main(String[] args) {
        int[][] arr = {{1,-2,3,5,-1},{1,-2,3,-8,5,1},{1,-2,3,-2,5,1}};
        for(int i=0;i<arr.length;i++){
            int n=arr[i].length;
            int[] ins=new int[n+1];
            ins[0]=0;
            for(int j=1;j<=n;j++){
                ins[j]=arr[i][j-1]+ins[j-1];
            }
            int maxis=0;
            for(int l=1;l<=n;l++){
                for(int r=l;r<=n;r++){
                    int i1=ins[r]-ins[l-1];
                    if(i1 >maxis){
                        maxis= i1;
                    }
                }
            }
            System.out.printf("第%d个示例数组的最大子数组和为：%d\n",i+1,maxis);
        }
        System.out.println("请输入数组的长度:");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr2=new int[n];
        System.out.println("请输入数组的元素:");
        for(int i=0;i<n;i++){
            arr2[i]=sc.nextInt();
        }
        int[] ins2=new int[n+1];
        ins2[0]=0;
        for(int j=1;j<=n;j++){
            ins2[j]=arr2[j-1]+ins2[j-1];
        }
        int maxis =0;
        for(int l=1;l<=n;l++) {
            for (int r = l; r <= n; r++) {
                int i1 = ins2[r] - ins2[l - 1];
                if (i1 > maxis) {
                    maxis = i1;
                }
            }
        }
        System.out.printf("输入数组的最大子数组和为：%d\n", maxis);
    }
}
