//输出1~20000内的所有素数，按每行5个打印出来
public class code3 {
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        int count = 0;
        for (int i = 1; i <= 20000; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
                count++;
                if (count % 5 == 0) {
                    System.out.println();
                }
            }
        }
    }
}
