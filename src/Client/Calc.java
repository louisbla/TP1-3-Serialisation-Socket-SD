package Client;

public class Calc {
    public int add(String a, String b){
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);
        return x + y;
    }
    public int mult(String a, String b){
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);
        return x * y;
    }
    public Integer div(String a, String b){
        int x = Integer.parseInt(a);
        Integer y = Integer.parseInt(b);
        if(y != 0)
        	return x / y;
        else
        	return null;
    }
    public int remove(String a, String b){
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);
        return x - y;
    }
}
