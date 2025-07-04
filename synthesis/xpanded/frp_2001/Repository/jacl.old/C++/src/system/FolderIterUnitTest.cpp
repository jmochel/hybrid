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
