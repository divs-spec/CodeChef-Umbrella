import java.io.*;

public class Main
{
    public static double func(double x , double b , double c){
        return (x * x + b * x + c)/ Math.sin(x);
    }
    
	public static void main (String[] args) throws Exception
	{
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        while (t -- > 0){
            String input[] = br.readLine().split(" ");

            double b = Double.parseDouble(input[0]);
            double c = Double.parseDouble(input[1]);

            double l = 0;
            double r = Math.PI/2;

            while (r - l > 0.000001){
                double m1 = l + (r - l)/3;
                double m2 = r - (r - l)/3;

                double fm1 = func(m1 , b , c);
                double fm2 = func(m2 , b , c);

                if(fm1 > fm2){
                    l = m1;
                } else if(fm1 < fm2){
                    r = m2;
                } else {
                    l = m1;
                    r = m2;
                }
            }
            double min = func(l , b , c);
            System.out.println(min);
        }

	}
}
