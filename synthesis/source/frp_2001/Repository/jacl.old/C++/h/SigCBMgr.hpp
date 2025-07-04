#ifndef SIGCBMGR_HPP
#define SIGCBMGR_HPP

#pragma warning (disable:4786 )   // Brain-dead debug format limit.

/*
    .Contains SignatureCallbackMgr

    .Author Jim Jackl-Mochel

    .Date 02.10.96

    .Copyright  This code is in the public domain.

    Revision Information
    ====================
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/

#include <map>
#include <algorithm>

using namespace std;

#include "Callback.hpp"

/*
    .ClassDesc  SignatureCallbackMgr is a class that manages a map of callbacks.

     .ClassNotes
*/

typedef unsigned long   SignatureCallbackID;

template <class OwnerT, class CfgDataT, class CallDataT, class SignatureT>
    class SignatureCallbackMgr
{
 public:


    typedef Callback<OwnerT, CfgDataT, CallDataT>           CallbackType;
    typedef Callback<OwnerT, CfgDataT, CallDataT>::FxnType  FxnType;

    struct SignatureInvoke
    {
        protected:

            CallDataT   _CallData;

        public:

            SignatureInvoke(const CallDataT& CallData)
            {
                _CallData = CallData;
            }

            void operator()(pair<SignatureT, CallbackType>& aSignatureAndCallback)
            {
                aSignatureAndCallback.second.Invoke(_CallData);
            }

    };


 public:

    // Constructors

    SignatureCallbackMgr(void)
    {
    };

    // Destructor

    virtual ~SignatureCallbackMgr(void)
    {
    };

    // Non-static member functions:

    SignatureCallbackID Register(OwnerT* Owner, CfgDataT& CfgData, FxnType CallbackFxn, SignatureT& Signature);
    void InvokeAll(const SignatureT& Signature, const CallDataT& CallData);

 protected:

    multimap<SignatureT, CallbackType>     _Callbacks;     // list of callbacks

 public:

    // The class exceptions

    /*
        .ClassNotes

            Indicates that two many arguments were supplied.
    */


    class IncorrectSignatureError : public range_error
    {
         public:

            IncorrectSignatureError(const std::string& desc) : range_error(desc)
            {
            }
    };
};


template <class OwnerT, class CfgDataT, class CallDataT, class SignatureT>
SignatureCallbackID  SignatureCallbackMgr<OwnerT, CfgDataT, CallDataT, SignatureT>::Register(OwnerT* Owner, CfgDataT& CfgData, FxnType CallbackFxn, SignatureT& Signature)
{
    _Callbacks.insert(multimap<SignatureT, CallbackType>::value_type(Signature, CallbackType(Owner, CfgData, CallbackFxn)));

    return(true);
}

/*
template <class OwnerT, class CfgDataT, class CallDataT, class SignatureT>
void SignatureCallbackMgr<OwnerT, CfgDataT, CallDataT, SignatureT>::Dump(std::ostream& os)
{
    copy(_Callbacks.begin(), _Callbacks.end(), ostream_iterator<multimap<SignatureT, CallbackType>::value_type>(os, "\n"));
}
*/

/*
    .MethodDesc  Invokes all the callbacks in the map associated with Signature

    .MethodNotes

    This is probably not the fastest implementation but it appears to be adequate for
    now and is very easy to implement.
*/

template <class OwnerT, class CfgDataT, class CallDataT, class SignatureT>
void SignatureCallbackMgr<OwnerT, CfgDataT, CallDataT, SignatureT>::InvokeAll(const SignatureT& Signature, const CallDataT& CallData)
{
    if ( _Callbacks.find(Signature) != _Callbacks.end())
    {
        SignatureInvoke Invokator(CallData);

        // Find the Callbacks functions with a signature of Signature

        std::pair<std::multimap<SignatureT, Callback<OwnerT, CfgDataT, CallDataT> >::iterator,
            std::multimap<SignatureT, Callback<OwnerT, CfgDataT, CallDataT> >::iterator>
        range = _Callbacks.equal_range(Signature);

        // Invoke the callback function on each one.

        for_each(range.first, range.second, Invokator);
    }
}

#endif // SIGCBMGR_HPP
