#ifndef CALLBACK_HPP
#define CALLBACK_HPP

/*
    @doc

    .Contains Callback Class Definition

    .Author Jim Jackl-Mochel

    .Date 03.18.94

    .Copyright  This code is in the public domain.

    Revision Information 
    ==================== 
    $Author: jmochel $
    $Revision: 1.1.1.1 $
    $Date: 1998/06/12 16:36:29 $
*/

/*
    @ClassDesc

        Callback class is a callback function object

    @ClassNotes

        Callback encapsulates the function to be called as well as tye configuration 
        data top be provided to it.

*/

template <class OwnerT, class CfgDataT, class CallDataT>
class Callback
{
    // @Access Public

    public:

        // @MemberDesc Typedef of the function being called

        typedef bool (*FxnType)(OwnerT* Owner, CfgDataT& CfgData, CallDataT& CallData);

    // @Access Public

    public:

        // @MemberDesc Constructor

        Callback(void) : _Owner(0), _CfgData(), _Fxn(0)
        {
        }

        // @MemberDesc Constructor

        Callback(const Callback&  ACallback)
        {
            _CfgData = ACallback._CfgData;
            _Fxn = ACallback._Fxn;
            _Owner = ACallback._Owner;
        }

        // @MemberDesc Constructor

        Callback(OwnerT* Owner,CfgDataT& CfgData, FxnType Fxn)
        {
            _Owner = Owner;
            _CfgData = CfgData;
            _Fxn = Fxn;
        }

        // @MemberDesc Assignment operator

        Callback& operator = (const Callback& ACallback)
        {
            // Check for assignment to self

            if ( this == &ACallback)
            {
                return(*this);
            }

            // Handle the assignment

            _CfgData = ACallback._CfgData;
            _Fxn = ACallback._Fxn;
            _Owner = ACallback._Owner;

            return(*this);
        }

        
        // @MemberDesc Destructor

        virtual ~Callback(void)
        {
        }

        // @MemberDesc Equality operator
        
        bool operator == ( const Callback& ACallback) const
        {
            if (( _Fxn == ACallback._Fxn ) && ( _CfgData == ACallback._CfgData ) && ( _Owner == ACallback._Owner) )
            {
                return(true);
            }

            return(false);
        }

        // @MemberDesc Equality operator

        bool operator == ( Callback& ACallback) const
        {
            if (( _Fxn == ACallback._Fxn ) && ( _CfgData == ACallback._CfgData ) && ( _Owner == ACallback._Owner) )
            {
                return(true);
            }

            return(false);
        }

        // @MemberDesc less than operator

        bool operator < (const Callback& ACallback) const
        {

            return(false);
        }

        // @MemberDesc Invokes the Callback object's function.

        bool Invoke(CallDataT& CallData)
        {
            return(_Fxn( _Owner, _CfgData, CallData));
        }

    // @Access Private

    private:

        // @MemberDesc The object that owns the callback chain.

        OwnerT*     _Owner;

        // @MemberDesc Configuration data for the callback function 

        CfgDataT    _CfgData;

        // @MemberDesc The callback function 

        FxnType     _Fxn;
};

#endif // CALLBACK_HPP
