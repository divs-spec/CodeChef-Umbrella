import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while(t-- > 0){
            int n = sc.nextInt();
            int w = sc.nextInt();
            
            int size = ((int)Math.pow(2,n+1))-1;
            int res[] = new int[size];
            
            int some = (int)(Math.pow(2,n));
            
            int st = size - (int)(Math.pow(2,n));
            
            res[st] = w;
            int i= st+1;
            
            while(true){
                if(i == size){
                    break;
                }
                
                int someNo = w+1;
                
                while(i < size && someNo <= some){
                    res[i++] = someNo;
                    someNo++;
                }  
                
                if(w == 1){
                    break;
                }
                someNo = 1;
                while(i < size && someNo < w){
                    res[i++] = someNo;
                    someNo++;
                }
            }
            
            for(i = st-1;i>=0;i--){
                
                if(res[2*i+2] == w || res[2*i+1] == w){
                    if(w == Math.min(res[2*i+2] , res[2*i+1])){
                        res[i] = Math.min(res[2*i+2],res[2*i+1]);
                    }
                    else{
                        res[i] = Math.max(res[2*i+2],res[2*i+1]);
                    }
                    continue;
                }
                res[i] = Math.min(res[2*i+2],res[2*i+1]);
            }
            
            for(int j : res){
                System.out.print(j + " ");
            }
            System.out.println();
        }
	}
}
