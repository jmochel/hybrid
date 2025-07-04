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
