import java.util.*;
import java.lang.*;
import java.io.*;

class CodeChef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
      Scanner sc = new Scanner(System.in);
      int t = sc.nextInt();
      while(t-->0)
      {
          int n = sc.nextInt();
          int a[] = new int[n];
          for(int i=0;i<n;i++) a[i] = sc.nextInt();
          HashSet<Integer> set = new HashSet<>();
          Stack<Integer> s = new Stack<>();
          for(int i=0;i<n;i++)
          {
              while(!s.isEmpty()&&a[i]>a[s.peek()])
              {
                  int d = a[i]-a[s.pop()];
                  set.add(d);
              }
              if(!s.isEmpty()) {
                  set.add(a[s.peek()]-a[i]);
              }
              s.push(i);
          }
          System.out.println(set.size());
      }
	}
}
