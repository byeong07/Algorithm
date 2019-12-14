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
        int[][] a = new int[n][n]; //�Է¹��� �迭
        int[][] d = new int[n][n]; //�Ÿ�
        int[][] g = new int[n][n]; //�� �׷�
        for (int i=0; i<n; i++) //�Է� �ޱ�
            for (int j=0; j<n; j++) 
                a[i][j] = scan.nextInt();
        int cnt = 0; //���� ǥ���� ���� (���� ID�� �ű�� ��)
        
		// ���� ID�� ���ϱ�
		// -> �湮���� �ʾҰ�, ���̸� BFS�� ������
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
            	
                if (a[i][j] == 1 && g[i][j] == 0) { 
                    Queue<Dot> q = new LinkedList<Dot>();
                    g[i][j] = ++cnt; //g�� �ʱ�ȭ
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
                                    g[nx][ny] = cnt; //while�� �ȿ��� ���⶧���� ������ �ٸ� ���鵵 1�̸� ���� ���ڷ� �ʱ�ȭ 
                                }
                        }
                    }
                }
            }
        }

        Queue<Dot> q = new LinkedList<Dot>(); //ť �����
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                d[i][j] = -1; //d�� �켱 -1�־�д�.
                if (a[i][j] == 1) { 
                    q.add(new Dot(i,j)); //1�ΰ��� q�� �־��ְ�
                    d[i][j] = 0; //���� 0���� ǥ��
                }
            }
        }
        
		// 2. ���� �� ���� visited�� �ִٴ� ��, id�� �ִٴ� �� -> '��'�̶�� ��
		// ������ bfs�� ������ �ٸ� ���� ����.
		// �ٸ� ����� �ٽ� �ڵ�
        while (!q.isEmpty()) {
            Dot p = q.remove();
            int x = p.x;
            int y = p.y;
            for (int k=0; k<4; k++) {
                int nx = x+dx[k];
                int ny = y+dy[k];
                if (0 <= nx && nx < n && 0 <= ny && ny < n) 
                    if (d[nx][ny] == -1) { //���� �ƴѰ��̸�
                        d[nx][ny] = d[x][y] + 1; //�Ÿ��� 1����
                        g[nx][ny] = g[x][y]; //���� ������ ���� ǥ���� ���� ���ڷ� Ȯ���Ų��. 
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
                        if (g[i][j] != g[x][y])  //������ ���� ������ �ٸ� ��
                            if (ans == -1 || ans > d[i][j] + d[x][y])  //�����ѰŸ�+����Ÿ� �ּҰ� ã��
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