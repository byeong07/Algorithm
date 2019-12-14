package algorithm;
import java.util.LinkedList;

import java.util.Queue;
import java.util.Scanner;
public class Q2146 {
    public static final int[] dx = {0, 0, 1, -1};
    public static final int[] dy = {1, -1, 0, 0};
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] a = new int[n][n]; //입력받은 배열
        int[][] d = new int[n][n]; //거리
        int[][] g = new int[n][n]; //섬 그룹
        for (int i=0; i<n; i++) //입력 받기
            for (int j=0; j<n; j++) 
                a[i][j] = scan.nextInt();
        int cnt = 0; //섬을 표시할 변수 (섬에 ID를 매기는 것)
        
		// 섬에 ID를 정하기
		// -> 방문하지 않았고, 섬이면 BFS를 돌린다
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
            	
                if (a[i][j] == 1 && g[i][j] == 0) { 
                    Queue<Dot> q = new LinkedList<Dot>();
                    g[i][j] = ++cnt; //g를 초기화
                    q.add(new Dot(i, j));
                    while (!q.isEmpty()) {
                        Dot p = q.remove();
                        int x = p.x;
                        int y = p.y;
                        for (int k=0; k<4; k++) {
                            int nx = x+dx[k];
                            int ny = y+dy[k];
                            if (0 <= nx && nx < n && 0 <= ny && ny < n) 
                                if (a[nx][ny] == 1 && g[nx][ny] == 0) {
                                    q.add(new Dot(nx, ny));
                                    g[nx][ny] = cnt; //while문 안에서 돌기때문에 인접한 다른 곳들도 1이면 같은 숫자로 초기화 
                                }
                        }
                    }
                }
            }
        }

        Queue<Dot> q = new LinkedList<Dot>(); //큐 재생성
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                d[i][j] = -1; //d에 우선 -1넣어둔다.
                if (a[i][j] == 1) { 
                    q.add(new Dot(i,j)); //1인곳을 q에 넣어주고
                    d[i][j] = 0; //섬을 0으로 표시
                }
            }
        }
        
		// 2. 만약 그 섬에 visited가 있다는 건, id가 있다는 것 -> '섬'이라는 것
		// 섬에서 bfs를 돌려서 다른 섬에 도착.
		// 다리 만드는 핵심 코드
        while (!q.isEmpty()) {
            Dot p = q.remove();
            int x = p.x;
            int y = p.y;
            for (int k=0; k<4; k++) {
                int nx = x+dx[k];
                int ny = y+dy[k];
                if (0 <= nx && nx < n && 0 <= ny && ny < n) 
                    if (d[nx][ny] == -1) { //섬이 아닌곳이면
                        d[nx][ny] = d[x][y] + 1; //거리를 1증가
                        g[nx][ny] = g[x][y]; //섬의 영역도 섬을 표시한 같은 숫자로 확장시킨다. 
                        q.add(new Dot(nx,ny)); //bfs
                    }
            }
        }
        int ans = -1; 
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                for (int k=0; k<4; k++) {
                    int x = i+dx[k];
                    int y = j+dy[k];
                    if (0 <= x && x < n && 0 <= y && y < n) 
                        if (g[i][j] != g[x][y])  //인접한 섬의 영역이 다를 때
                            if (ans == -1 || ans > d[i][j] + d[x][y])  //인접한거리+현재거리 최소값 찾기
                                ans = d[i][j] + d[x][y];
                }
            }
        }
        System.out.println(ans);
    }
}
class Dot {
    int x;
    int y;
    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }
}