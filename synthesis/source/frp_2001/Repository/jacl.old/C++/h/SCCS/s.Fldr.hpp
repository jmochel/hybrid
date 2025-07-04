h61614
s 00092/00000/00000
d D 1.1 99/11/17 08:08:32 jmochel 2 1
cC
cK26510
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:08:29 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/Fldr.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43643
cPC++/h/Fldr.hpp
cR873d827f5cb6bb60
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
#ifndef FLDR_HPP
#define FLDR_HPP

/*
    .Contains Folder Constants

    .Author Jim Jackl-Mochel

    .Copyright  This code is in the public domain.

    Revision Information
    ====================
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


// Local Library Headers

#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

#ifndef TYPE_HPP
#include "Types.hpp"
#endif

/*
    .ClassDesc

        Describes and contains the Reporter constants
*/

class Fldr
{
    public:

        // Definition of the places a message can be sent

		typedef enum 
        {
				Folder = 1,
				Stream = 2,
                Recurse = 4,
                End = 8
        } Type;

        typedef ULong  RetrievalMask;
};

/*
    .ClassDesc

        Folder exception class
*/

class FolderError : public DomainError
{
    public:

        FolderError(std::string& Desc) : DomainError(Desc)
        {
        }

        virtual ~FolderError()
        {
        }
};

/*
    .ClassDesc

        Folder initialization failure error
*/


class FolderInitError : public FolderError
{
    public:

        FolderInitError(std::string& Desc) : FolderError(Desc)
        {
        }

        virtual ~FolderInitError()
        {
        }
};


#endif  // FLDR_HPP
E 2
I 1
E 1
