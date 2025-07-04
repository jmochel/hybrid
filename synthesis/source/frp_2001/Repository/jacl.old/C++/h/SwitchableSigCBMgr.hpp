#ifndef SWITCHABLESIGCBMGR_HPP
#define SWITCHABLESIGCBMGR_HPP


/*
    @doc

    .Contains Declaration of SwitchableSigCBMgr

    .Author Jim Jackl-Mochel

    .Date <???>

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:30 $
*/

#pragma message("SwitchableSigCBMgr.hpp : Check API Returns")
#pragma message("SwitchableSigCBMgr.hpp : Check API Exceptions")
#pragma message("SwitchableSigCBMgr.hpp : Check API Exception Specifications")
#pragma message("SwitchableSigCBMgr.hpp : Check Comments")

#pragma warning (disable:4786 )   // Brain-dead debug format limit.

#include <map>
#include <algorithm>

using namespace std;


// JACL Library Headers

#ifndef TYPES_HPP
#include "Types.hpp"
#endif

#ifndef UTIL_HPP
#include "Util.hpp"
#endif

#ifndef CALLBACK_HPP
#include "Callback.hpp"
#endif

// Signature Wrapper.

template <class SigT>
struct SignatureWrapper
{
    public:

        SigT        _Signature;
        bool        _Requested;

    public:

        SignatureWrapper() :
          _Signature(), _Requested(false)
        {
        }

        SignatureWrapper(SigT signature, bool requested = false) :
            _Signature(signature), _Requested(requested)
        {
        }

        SignatureWrapper& operator = (const SignatureWrapper& signatureWrapper)
        {
            _Signature = signatureWrapper._Signature;
            _Requested = signatureWrapper._Requested;

            return(*this);
        }

        bool operator < (const SignatureWrapper& signatureWrapper) const
        {
            if ( _Requested == signatureWrapper._Requested )
            {
                return(_Signature  < signatureWrapper._Signature);
            }
            
            return(true);
        }

};


/*
	@ClassDesc

        SwitchableSigCBMgr is a manager for selectively activatible callback functions 
*/

template <class OwnerT, class CfgDataT, class CallDataT, class SignatureT>
class SwitchableSigCBMgr
{
    // @Access  Public

    public:

        // Typedefs, enums

        typedef Callback<OwnerT, CfgDataT, CallDataT>           CallbackT;
        typedef Callback<OwnerT, CfgDataT, CallDataT>::FxnType  FxnT;


        typedef SignatureWrapper<SignatureT> SignatureWrapperT;

        struct SignatureInvoke
        {
            protected:

                CallDataT   _CallData;

            public:

                SignatureInvoke(const CallDataT& callData)
                {
                    _CallData = callData;
                }

                void operator()(pair<SignatureWrapperT, CallbackT>& signatureWrapperAndCallback)
                {
                    signatureWrapperAndCallback.second.Invoke(_CallData);
                }

        };

        // @MemberDesc    Void Constructor

        SwitchableSigCBMgr();

        // @MemberDesc    Constructor

        SwitchableSigCBMgr(OwnerT* owner): _Owner(owner), _Callbacks()
        {
        };

        // @MemberDesc    Destructor

        virtual ~SwitchableSigCBMgr()
        {
        };

        // Non-static member functions:

        void Register(SignatureT& signature, CfgDataT& cfgData, FxnT callbackFxn);
        void Request(SignatureT& signature, CfgDataT& cfgData, FxnT callbackFxn);
        void InvokeAll(const SignatureT& signature, const CallDataT& callData);
        void UnRequest(SignatureT& signature, CfgDataT& cfgData, FxnT callbackFxn);
        void UnRegister(SignatureT& signature, CfgDataT& cfgData, FxnT callbackFxn);

    // @Access Protected

    protected:

       // @MemberDesc Sets the Default values for the objects data members

	    void SetDefaults(void);
        
    // @Access Protected

    protected:

        // @MemberDesc      Map of signature,callback pairs.

        std::multimap<SignatureWrapperT,CallbackT>  _Callbacks;

        // @MemberDesc      Owner of the callback manager

        OwnerT*  _Owner;
};

/*
    @MethodDesc

*/

template <class OwnerT, class CfgDataT, class CallDataT, class SignatureT>
void SwitchableSigCBMgr<OwnerT, CfgDataT, CallDataT, SignatureT>::Register(SignatureT& signature, CfgDataT& cfgData, FxnT callbackFxn)
{
    _Callbacks.insert(multimap<SignatureWrapperT, CallbackT>::value_type(SignatureWrapperT(signature), CallbackT(_Owner, cfgData, callbackFxn)));
}

/*
    @MethodDesc

*/

template <class OwnerT, class CfgDataT, class CallDataT, class SignatureT>
void SwitchableSigCBMgr<OwnerT, CfgDataT, CallDataT, SignatureT>::Request(SignatureT& signature, CfgDataT& cfgData, FxnT callbackFxn)
{
}

/*
    @MethodDesc

*/

template <class OwnerT, class CfgDataT, class CallDataT, class SignatureT>
void SwitchableSigCBMgr<OwnerT, CfgDataT, CallDataT, SignatureT>::InvokeAll(const SignatureT& signature, const CallDataT& callData)
{
    if ( _Callbacks.find(SignatureWrapperT(signature,true)) != _Callbacks.end())
    {
        SignatureInvoke Invokator(callData);

        // Find the Callbacks functions with a signature of Signature

        std::pair<std::multimap<SignatureWrapperT, Callback<OwnerT, CfgDataT, CallDataT> >::iterator,
            std::multimap<SignatureWrapperT, Callback<OwnerT, CfgDataT, CallDataT> >::iterator>
        range = _Callbacks.equal_range(SignatureWrapperT(signature,true));

        // Invoke the callback function on each one.

        for_each(range.first, range.second, Invokator);
    }
}

/*
    @MethodDesc

*/

template <class OwnerT, class CfgDataT, class CallDataT, class SignatureT>
void SwitchableSigCBMgr<OwnerT, CfgDataT, CallDataT, SignatureT>::UnRequest(SignatureT& signature, CfgDataT& cfgData, FxnT callbackFxn)
{
}

/*
    @MethodDesc

*/

template <class OwnerT, class CfgDataT, class CallDataT, class SignatureT>
void SwitchableSigCBMgr<OwnerT, CfgDataT, CallDataT, SignatureT>::UnRegister(SignatureT& signature, CfgDataT& cfgData, FxnT callbackFxn)
{
}


template <class OwnerT, class CfgDataT, class CallDataT, class SignatureT>
SwitchableSigCBMgr<OwnerT, CfgDataT, CallDataT, SignatureT>::SwitchableSigCBMgr()
{
	SetDefaults();
}


/*
    @MethodDesc
        
          Sets the class to the default state.

    @MethodNotes
        
        <???>

*/

template <class OwnerT, class CfgDataT, class CallDataT, class SignatureT>
void SwitchableSigCBMgr<OwnerT, CfgDataT, CallDataT, SignatureT>::SetDefaults(void)
{
    _Owner = 0;
}


#endif
