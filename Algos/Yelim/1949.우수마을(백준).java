package Boj;

import java.util.ArrayList;
import java.util.Scanner;

// N���� ������ �̷���� ����
// A -> B �� �� �� �ִٸ� B-> A�� �� �� �ִ�
// ��� ������ ����Ǿ� �ִ�
// �� ���� ���̿� ���� �մ� ���� ���� ��, �� ������ '�������ִ�'�� ǥ��

// ��Ģ 1. ��� ���� ������ ���� ���� x
// ��Ģ 2. ��� ���� x �̸� ��� ���� O �� ������ �־�� ��
// o x o x o x o x
// x o x o x o x o 

// o x x o x o x o 
// .... 

// ���� - ��� ���� �ֹ� ���� �� ���� �ִ밡 �ǵ��� ��� ���� ����
public class Q1949 {
	
	static int N; 
	static int[] cost; 
	static boolean[] visited;
	static boolean[][] map;
	
	@SuppressWarnings("unchecked")
	static ArrayList<Integer>[] adjArr = new ArrayList[10001]; // ���� �� ��ŭ ������ �� ���� ArrayList
	
	static int dfs(int pos, boolean v) {	// pos = ���� ��ȣ (position)	
		int result=0;
		
		visited[pos] = true; // �� ������ �湮�ߴٴ� ǥ�÷� true�� �ٲ���´�
		
		for (int i=0; i<adjArr[pos].size(); i++) { // (1,2) , (1,6) �̷��� 1�� ���ؼ� 2���� ���� ���� ���� �����ϱ� �ݺ��� ����
			int next = adjArr[pos].get(i); // (1,2)   -- 1�� ������ �����鿡 ���ؼ� �˻縦 �� ����
			if(visited[next]) // 2�� �̹� �湮�ߴ� �����̶�� �� ������ �˻縦 �� ���� �����ΰŴϱ� pass
				continue; 
			if (v) {	// ���� ���� ������ 1�� ��������� ����, (v=true�� �Ѿ�� ���)
				result = result + dfs(next,false); // �� ���� �̾����ִ� ������ ������ ��������� �ƴϿ��� �ϹǷ� false�� �Ѱ������
			} else {	 // ���� ���� ������ 1�� ��� ������ �ƴ� ����,  �� ���� ������ ������ 1. ��������� ���� x o   /  2. �ƴ� ��� x  x => �̷��� 2���� ��찡 �����Ƿ�
				result = result + Math.max(dfs(next,false), dfs(next,true) + cost[next]); // dfs�� ���� �� ���� ����� �� �� �ִ밪�� �����Ѵ� 
			}
		}
		// back trace
		visited[pos] = false;
		return result; 
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // ���� �� ( 10,000 ����) 
		cost = new int[N+1]; // ���� �� �ֹ� �� ���� (�ε��� 1���� �����Ұ���) ������ ���� ��Ÿ�� �� ��ȣ ���߱� ���ؼ� (1 2)
		visited = new boolean[N+1]; // ���� ����� �湮�� ������ �ƴ��� ǥ�� (�湮������ true �ƴϸ� false)
		map = new boolean[N+1][N+1];
		
		for(int i=1; i<N+1; i++) { 
			cost[i] = sc.nextInt(); // �ε��� 1���� ���� �� �ֹ� �� ����
			adjArr[i] = new ArrayList<Integer>(); // ������ ���� ���� ������ ��ü ������ ���� 
		}
		
		// �� �־���
		for(int i=0; i<N-1; i++) { // N-1���� ���� �ִٰ� �����ϱ�
			int x=sc.nextInt();
			int y= sc.nextInt();
			
			adjArr[x].add(y); // 1�� ���忡���� (1,2) �Է������� 
 			adjArr[y].add(x); // 2�� ���忡�� 1�� �������ش� (2,1)      
		}
		
		// ù��° ������ ��������� �ƴ� ��� dfs(1,false)
		// ù��° ������ ��������� ��� dfs(1,true) + cost[1]     
		//      -- cost[1] ������ ������ ù��° ������ ��������̱� ������ ù��° ������ �ֹ� ���� ������ ����
		int answer = Math.max(dfs(1,false), dfs(1,true) + cost[1]); 
		System.out.println(answer);
	}
	
}
