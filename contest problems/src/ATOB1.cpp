#include <bits/stdc++.h>
using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int t;
    cin >> t;
    while(t--){
        int n;
        cin >> n;
        string A, B;
        cin >> A >> B;
        
        if(A == B){
            cout << 0 << "\n";
            continue;
        }
        
        // A is constant (all same char)
        bool a_const = (*min_element(A.begin(),A.end()) == 
                        *max_element(A.begin(),A.end()));
        // B is constant
        bool b_const = (*min_element(B.begin(),B.end()) == 
                        *max_element(B.begin(),B.end()));
        
        cout << (a_const || b_const ? 1 : 0) << "\n";
    }
    return 0;
}
