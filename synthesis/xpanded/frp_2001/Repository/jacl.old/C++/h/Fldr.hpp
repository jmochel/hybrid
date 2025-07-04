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
