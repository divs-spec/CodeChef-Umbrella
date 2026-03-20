#include<bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>
using namespace std;
using namespace __gnu_pbds;

#define int long long
#define pi 3.14159265358979323846
#define inf 4e18
#define ninf -4e18
int mod=998244353;
#define endl "\n"
#define unordered_map map
#define unordered_set set

typedef tree<int, null_type, less<int>, rb_tree_tag, tree_order_statistics_node_update> pbds;

bool comp(pair<int, int> &a, pair<int, int> &b){
    if(a.second==b.second) return a.first<b.first;
    return a.second<b.second;
}

void solve(){
    int n;
    cin>>n;
    int arr[n];
    unordered_set<int> st;
    for(int i=0;i<n;i++){
        cin>>arr[i];
        if(arr[i]!=-1) st.insert(arr[i]);
    }
    vector<vector<int>> dp(n+1, vector<int>(n+1, 0));
    for(int i=1;i<=n;i++){
        int curr=0;
        int possible=0;
        if(arr[i-1]!=-1) st.erase(arr[i-1]);
        for(int j=1;j<=n;j++){
            if(st.find(j)==st.end()) possible++;
            if(i>0) curr=(curr+dp[i-1][j])%mod;
            if(i==1 and arr[i-1]==-1 and st.find(j)==st.end()) dp[i][j]=1%mod;
            
            else if(i==1 and arr[i-1]!=-1 and arr[i-1]==j) dp[i][j]=1%mod;
            else if(arr[i-1]==-1 and st.find(j)==st.end() and possible>=i) dp[i][j]=curr%mod;
            else if(arr[i-1]!=-1 and j>arr[i-1]) dp[i][j]=dp[i-1][j]%mod;
            else if(arr[i-1]!=-1 and j==arr[i-1]) dp[i][j]=(curr-dp[i-1][j]+mod)%mod;
        }
        for(int j=1;j<max(i, arr[i-1]);j++) dp[i][j]=0;
        // for(int j=1;j<=n;j++) cout<<dp[i][j]<<" ";
        // cout<<endl;
    }
    int ans=0;
    cout<<dp[n][n]%mod<<endl;
}

signed main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    
    int t;
    cin>>t;
    while(t--) solve();
}
