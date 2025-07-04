h17700
s 00186/00000/00000
d D 1.1 99/11/17 08:10:10 jmochel 2 1
cC
cK47986
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:10:06 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/Strm.hpp
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43653
cPC++/h/Strm.hpp
cRc173b5ff5cb6bb60
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
#ifndef STRM_HPP
#define STRM_HPP

/*
    .Contains Stream Constants

    .Author Jim Jackl-Mochel

    .Date DateGoesHere

    .Copyright  This code is in the public domain.

    Revision Information
    ====================
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/


// Forward declarations

#include <string>

// Local Library Headers

#ifndef EXCEPTIONS_HPP
#include "Exceptions.hpp"
#endif

#ifndef TYPE_HPP
#include "Types.hpp"
#endif

/*
    .ClassDesc

        Describes and contains the Stream constants

    .ClassNotes
*/

class Strm
{
    public:

		  enum Mode {
				Read = 0,
				Write = 1,
				Update = 2,
                Binary = 4,
                Text = 8
		  };

		  enum Service {
				File,
				Pipe,
				Mem
		  };

		  enum AccessOrder {
				Seq,
				Random
		  };

		  enum Fullfillment {
				Open,
				Alloc,
				Free,
				Realloc,
				Close
		  };

          enum Whence {
                Curr,
                Begin,
                End
          };

        /*
            .ClassDesc

                Stream Class Initialize Error

            .ClassNotes
        */

        class PlatformInitializeError : public Exception
        {
            public:

                PlatformInitializeError(std::string& What) : Exception(What)
                {
                }
        };


        /*
            .ClassDesc

                Stream Class Read Error

            .ClassNotes
        */

        class ReadError : public Exception
        {
            public:

                ReadError(std::string& What) : Exception(What)
                {
                }
        };

        /*
            .ClassDesc

                Stream Class Read Error

            .ClassNotes
        */

        class WriteError : public Exception
        {
            public:

                WriteError(std::string& What) : Exception(What)
                {
                }
        };

        /*
            .ClassDesc

                Stream Class Seek Error

            .ClassNotes
        */

        class SeekError : public Exception
        {
            public:

                SeekError(std::string& What) : Exception(What)
                {
                }
        };

        /*
            .ClassDesc

                Stream Class Read Error

            .ClassNotes
        */

        class OpenError : public Exception
        {
            public:

                OpenError(std::string& What) : Exception(What)
                {
                }
        };


        /*
            .ClassDesc

                Stream Class Read Error

            .ClassNotes
        */

        class CloseError : public Exception
        {
            public:

                CloseError(std::string& What) : Exception(What)
                {
                }
        };

};

#endif  // STRM_HPP
E 2
I 1
E 1
