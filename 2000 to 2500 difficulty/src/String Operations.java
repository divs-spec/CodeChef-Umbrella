import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		try{
		      Scanner scan=new Scanner(System.in);
        int t= scan.nextInt();
        while(t>0){
          String a=scan.next();
            HashSet<ArrayList<Integer>> s=new HashSet<>();
            for(int i=0;i<a.length();i++){
                int count=0,even=0,odd=0;
                for(int j=i;j<a.length();j++){
                    if(a.charAt(j)=='1'){
                        count++;
                    }
                    else{
                        if(count%2==0){
                            even++;
                        }
                        else{
                            odd++;
                        }
                    }
                    int len=j-i+1;
                    ArrayList<Integer> x=new ArrayList<>();
                    x.add(len);
                    x.add(even);
                    x.add(odd);
                    s.add(x);
                }
            }
            System.out.println(s.size());
            t--;
        }
		}
		catch(Exception e){
		    return;
		}
	}
}
