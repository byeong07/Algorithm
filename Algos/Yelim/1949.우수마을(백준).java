package Boj;

import java.util.ArrayList;
import java.util.Scanner;

// N개의 마을로 이루어진 나라
// A -> B 로 갈 수 있다면 B-> A로 갈 수 있다
// 모든 마을은 연결되어 있다
// 두 마을 사이에 직접 잇는 길이 있을 때, 두 마을을 '인접해있다'고 표현

// 규칙 1. 우수 마을 끼리는 서로 인접 x
// 규칙 2. 우수 마을 x 이면 우수 마을 O 와 인접해 있어야 함
// o x o x o x o x
// x o x o x o x o 

// o x x o x o x o 
// .... 

// 목적 - 우수 마을 주민 수의 총 합이 최대가 되도록 우수 마을 선정
public class Q1949 {
	
	static int N; 
	static int[] cost; 
	static boolean[] visited;
	static boolean[][] map;
	
	@SuppressWarnings("unchecked")
	static ArrayList<Integer>[] adjArr = new ArrayList[10001]; // 마을 수 만큼 인접한 쌍 담을 ArrayList
	
	static int dfs(int pos, boolean v) {	// pos = 마을 번호 (position)	
		int result=0;
		
		visited[pos] = true; // 이 마을은 방문했다는 표시로 true로 바꿔놓는다
		
		for (int i=0; i<adjArr[pos].size(); i++) { // (1,2) , (1,6) 이렇게 1에 관해서 2개의 길이 있을 수도 있으니깐 반복문 돌림
			int next = adjArr[pos].get(i); // (1,2)   -- 1과 인접한 마을들에 대해서 검사를 할 것임
			if(visited[next]) // 2가 이미 방문했던 마을이라면 그 마을은 검사를 다 끝낸 마을인거니까 pass
				continue; 
			if (v) {	// 현재 기준 마을인 1이 우수마을인 경우는, (v=true로 넘어온 경우)
				result = result + dfs(next,false); // 그 다음 이어져있는 마을은 무조건 우수마을이 아니여야 하므로 false로 넘겨줘야함
			} else {	 // 현재 기준 마을인 1이 우수 마을이 아닌 경우는,  그 다음 인접한 마을이 1. 우수마을인 경우와 x o   /  2. 아닌 경우 x  x => 이렇게 2가지 경우가 있으므로
				result = result + Math.max(dfs(next,false), dfs(next,true) + cost[next]); // dfs를 돌려 두 가지 경우의 수 중 최대값을 추출한다 
			}
		}
		// back trace
		visited[pos] = false;
		return result; 
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 마을 수 ( 10,000 이하) 
		cost = new int[N+1]; // 마을 별 주민 수 저장 (인덱스 1부터 저장할것임) 인접한 마을 나타낼 때 번호 맞추기 위해서 (1 2)
		visited = new boolean[N+1]; // 마을 수대로 방문한 곳인지 아닌지 표시 (방문했으면 true 아니면 false)
		map = new boolean[N+1][N+1];
		
		for(int i=1; i<N+1; i++) { 
			cost[i] = sc.nextInt(); // 인덱스 1부터 마을 별 주민 수 저장
			adjArr[i] = new ArrayList<Integer>(); // 인접한 마을 쌍을 저장할 객체 생성도 해줌 
		}
		
		// 쌍 넣어줌
		for(int i=0; i<N-1; i++) { // N-1개의 길이 있다고 했으니깐
			int x=sc.nextInt();
			int y= sc.nextInt();
			
			adjArr[x].add(y); // 1의 입장에서는 (1,2) 입력했으면 
 			adjArr[y].add(x); // 2의 입장에도 1을 저장해준다 (2,1)      
		}
		
		// 첫번째 마을이 우수마을이 아닐 경우 dfs(1,false)
		// 첫번째 마을이 우수마을일 경우 dfs(1,true) + cost[1]     
		//      -- cost[1] 더해준 이유는 첫번째 마을이 우수마을이기 때문에 첫번째 마을의 주민 수를 더해준 것임
		int answer = Math.max(dfs(1,false), dfs(1,true) + cost[1]); 
		System.out.println(answer);
	}
	
}
