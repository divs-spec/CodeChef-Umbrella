#include <bits/stdc++.h>
using namespace std;

int main() {
    int R, B;
    cin >> R >> B;
    
    int trades = min(R, B);
    
    int greens = trades;
    int remainingR = R - trades;
    int remainingB = B - trades;
    
    int skill = greens * 5 + remainingR * 1 + remainingB * 2;
    
    cout << skill << endl;
    return 0;
}
