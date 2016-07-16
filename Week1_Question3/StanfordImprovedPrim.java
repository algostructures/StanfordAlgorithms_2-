import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
public class StanfordImprovedPrim {
	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter pw;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("D:/Coding/Stanford_Algo_Input/Week1/Graph.txt"));
		int v = readInt();
		int e = readInt();
		
		vertex[] graph = new vertex[v+1];
		int edges[][] = new int[e][3];
		for(int i = 0; i < e; i++){
			for(int j = 0; j < 3; j++){
				edges[i][j] = readInt();
			}
		}
		
		HashMap<Key, Integer> hm = new HashMap<Key, Integer>();
		for(int i = 0; i < e; i++){
			hm.put(new Key(edges[i][0], edges[i][1]), edges[i][2]);
		}
		
		
		for(int i = 1; i < v+1; i++){
			graph[i] = new vertex(i);
		}
		for(int i = 0; i < e; i++){
			graph[edges[i][0]].ar.add(edges[i][1]);
			graph[edges[i][1]].ar.add(edges[i][0]);
		}
		
		graph[1].key = 0;
		PriorityQueue<vertex> Q = new PriorityQueue<vertex>(v, new Comparator<vertex>() {
	        public int compare(vertex v1, vertex v2) {
	            return Integer.valueOf(v1.key).compareTo(v2.key);
	        }
	    });
		
		for(int i = 1; i < v+1; i++){
			Q.add(graph[i]);
		}
		int weight = 0;
		boolean visited[] = new boolean[v+1];
		while(!Q.isEmpty()){
			vertex u = Q.poll();
			visited[u.val] = true;
			//System.out.println(u.val);
			for(int ve : u.ar){
				boolean one = hm.containsKey(new Key(u.val, ve));
				Key ks = null;
				if(one){
					ks = new Key(u.val, ve);
				}
				else{
					ks = new Key(ve, u.val);
				}
				if(!visited[ve] && hm.get(ks) < graph[ve].key ){
					Q.remove(graph[ve]);
					graph[ve].key = hm.get(ks);
					Q.add(graph[ve]);
				}
			}
		}
		
		for(int i = 1; i < v+1; i++){
			if(graph[i].key != Integer.MAX_VALUE)
				weight += graph[i].key;
			else
				System.out.println(graph[i].val);
		}
		System.out.println(weight);
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
class vertex {
	int key;
	int parent;
	int val;
	ArrayList<Integer> ar;
	vertex(int val){
		this.val = val;
		key = Integer.MAX_VALUE;
		ar = new ArrayList<Integer>();
	}
}
class Key {

    private final int x;
    private final int y;

    public Key(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return x == key.x && y == key.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

}
