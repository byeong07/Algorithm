#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;
bool cmp(pair<int,int>a,pair<int,int>b){
	if(a.first==b.first){
		return a.second<b.second;
	}
	else{
		return a.first<b.first;
	}
}
int main(){
	int tc,input;
	vector<int>org_arr;
	vector<pair<int,int> >idx_arr;
	cin>>tc;
	for(int idx=0;idx<tc;idx++){
		cin>>input;
		idx_arr.push_back(make_pair(input,idx));
		org_arr.push_back(input);

	}
	sort(idx_arr.begin(),idx_arr.end(),cmp);
	
	
	for(int org_idx=0;org_idx<tc;org_idx++){
		for(int idx=0;idx<tc;idx++){
			if(org_arr[org_idx]==idx_arr[idx].first&&idx_arr[idx].second!=-1){
				idx_arr[idx].second=-1;
			
				cout<<idx<<" ";
				break;
			}
		}
		
	}
}
