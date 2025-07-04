h31847
s 00045/00000/00000
d D 1.1 99/11/17 08:46:56 jmochel 2 1
cC
cK57337
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:46:52 jmochel 1 0
c BitKeeper file e:/jacl/C++/src/system/FolderIterUnitTest.cpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43873
cPC++/src/system/FolderIterUnitTest.cpp
cRe43cf4ef5cb6bb65
cZ-05:00
c______________________________________________________________________
e
u
U
f e 0
f x 33
t
T
I 2
/*
    @doc

    .Contains: <???>

    .Author: Jim Jackl-Mochel

    .Date: <???>

    .Copyright:  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:31 $
*/

#include <algorithm>
#include <vector>

#include <iostream>

#include "FolderIter.hpp"

using namespace std;

void WalkFolder(FolderSpec& base)
{
    for (FolderIter current(base); current != FolderIter(); ++current)
    {
        if (*current != "." && *current != "..")
        {
          CommonSpec spec = *current;
          cout << spec.GetName() << endl;
        }
    }
}

int main(int argCnt, char *argValue[])
{
    WalkFolder(FolderSpec(argCnt > 1 ? argValue[1] : "."));

    return(EXIT_SUCCESS);    
}
E 2
I 1
E 1
