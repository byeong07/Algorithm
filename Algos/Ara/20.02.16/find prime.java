//프로그래머스 완전탐색 소수찾기
//https://programmers.co.kr/learn/courses/30/lessons/42839

/*  접근법
  1 string -> char list
  2 char로 만들 수 있는 모든 순열 조합 뽑기 (중복으로 진행, 기본순열로 진행 할 시 중복 문자 제거됌)
  ex) p(5,3) = 5*4*3 = {'1',2,3,4,5},{1,'2',3,4,5},{1,2,'3',4,5} = 1,2,3 pick
  3 소수인건 check
  4 check primelist size return
  ------full scan search------
  */

import java.util.*;

public class FindSosu {
    public int solution(String numbers) {
        char[] list = numbers.toCharArray();
        int[] combArr = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            combArr[i] = Integer.parseInt(String.valueOf(list[i]));
        }
        Set<Integer> primeList = new HashSet<Integer>();
        for (int i = 1; i <= list.length; i++) {
            perm(list, 0, i, primeList);
        }

        System.out.println(primeList);

        return primeList.size();
    }
    //순열구하기 ~.~.perm(배열,,pick k, k 담을배열
    public void perm(char[] arr, int depth, int k, Set primeList) {
        if (depth == k) { 
            //  depth 가 k일때 사이클, 출력
            print(arr, k, primeList);
            return;
        } else {
            //swap()으로 depth, i  replace // depth+1해서 reroll
            for (int i = depth; i < arr.length; i++) {
                swap(arr, i, depth);
                perm(arr, depth + 1, k, primeList); 
                swap(arr, i, depth);
            }
        }
    }
    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public void print(char[] arr, int k, Set primeList) {
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < k; i++) {
            System.out.print(arr[i]);
            a.append(arr[i]);
        }
        System.out.println();
        isSosu(primeList, a);
    } 
    //time complexity  O(N^2)
    private void isSosu(Set primeList, StringBuilder a) {
        int b = Integer.parseInt(String.valueOf(a)); //parsing
        boolean sosu = true;
        if (b <= 1) {
            return;
        }
        for (int j = 2; j <= Math.sqrt(b); j++) { //소수 거르기
            if (b % j == 0) {
                sosu = false;
                break;
            }
        }
        if (sosu) {
            primeList.add(b);
        }
    }
}
