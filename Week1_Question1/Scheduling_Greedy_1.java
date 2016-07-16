import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
public class Scheduling_Greedy_1 {
	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter pw;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("D:/Coding/Stanford_Algo_Input/Week1/Jobs.txt"));
		
		
		int max = readInt();
		job[] j = new job[max];
		for(int i = 0; i < max; i++){
			int w = readInt();
			int l = readInt();
			j[i] = new job(w, l);
		}
		Arrays.sort(j);	
		int cTime = 0;
		long wSum = 0L;
		for(int i = 0; i < max; i++){
			cTime += j[i].length;
			wSum += j[i].weight * cTime + 0L;
		}	
		System.out.println(wSum);
	}
	
	public static int modpow(int value , int power, int mod){
	    int e = 1;
	   
	    for (int i = 0; i < power; i++) {
	         e = ((e * value) % mod);
	            
	    }
	        
	        return e;
	}

	public static int readInt() {
		return Integer.parseInt(nextToken());
	}

	public static long readLong() {
		return Long.parseLong(nextToken());
	}

	public static double readDouble() {
		return Double.parseDouble(nextToken());
	}

	public static String nextToken() {
		while(st == null || !st.hasMoreTokens())	{
			try {
				if(!br.ready())	{
					pw.close();
					System.exit(0);
				}
				st = new StringTokenizer(br.readLine());
			}
			catch(IOException e) {
				System.err.println(e);
				System.exit(1);
			}
		}
		return st.nextToken();
	}

	public static String readLine()	{
		st = null;
		try {
			return br.readLine();
		}
		catch(IOException e) {
			System.err.println(e);
			System.exit(1);
			return null;
		}
	}

}
class job implements Comparable<job>{
	int weight;
	int length;
	int val;
	
	job (int weight, int length){
		this.weight = weight;
		this.length = length;
		this.val = this.weight - this.length;
	}

	@Override
	public int compareTo(job other) {
		if(this.val != other.val){
			return Integer.valueOf(other.val).compareTo(val);
		}
		else{
			return Integer.valueOf(other.weight).compareTo(weight);
		}
	}	
}

