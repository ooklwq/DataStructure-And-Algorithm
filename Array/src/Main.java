

public class Main {

    public static void main(String[] args) {
//	    int[] arr = new int[10];
//	    arr[0] = 1;
//	    System.out.println(arr[0]);
        Array arr = new Array(10);
        for(int i=0;i<8;i++){
            arr.addLast(i);
        }
        System.out.println(arr.toString());
        arr.addLast(10);
        System.out.println(arr.toString());
    }
}
