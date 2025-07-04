#ifndef CBMGR_HPP
#define CBMGR_HPP

/*
    .Contains CallbackMgr Class Definition

    .Author Jim Jackl-Mochel

    .Date 12.16.94

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:29 $
*/
    
#pragma warning ( disable:4786 ) // Brain-dead debug format

#include <vector>

#ifndef CALLBACK_HPP
#include "Callback.hpp"
#endif

/*
    .ClassDesc  CallbackMgr is a class that manages a chain of callbacks.

     .ClassNotes
*/

template <class OwnerT, class CfgDataT, class CallDataT>
class CallbackMgr
{
    public:

        typedef unsigned long                                   CallBackID;
        typedef Callback<OwnerT, CfgDataT, CallDataT>           CallbackType;
        typedef Callback<OwnerT, CfgDataT, CallDataT>::FxnType  FxnType;
        
    protected:

        struct InvokeT
        {
            protected:

                CallDataT   _CallData;

            public:

                InvokeT(CallDataT& CallData)
                {
                    _CallData = CallData;
                }

                void operator()(CallbackType& aCallback)
                {
                    aCallback.Invoke(_CallData);
                }
        };


    public:

        // Constructors

        CallbackMgr(void)
        {
        };

        // Destructor

        virtual ~CallbackMgr(void)
        {};

        // Non-static member functions:

        CallBackID Register(OwnerT* Owner, CfgDataT& CfgData, FxnType Fxn)
        {   
            _Callbacks.push_back( CallbackType(Owner, CfgData, Fxn) );

            return(true);
        }

        void InvokeAll(CallDataT& CallData)
        {
            InvokeT Invokator(CallData);

            for_each(_Callbacks.begin(), _Callbacks.end(), Invokator);
        }

    protected:

    std::vector<CallbackType>   _Callbacks;
 };





#endif // CBMGR_HPP

