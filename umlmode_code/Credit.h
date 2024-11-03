#ifndef CREDIT_H_HEADER_INCLUDED_98D816C7
#define CREDIT_H_HEADER_INCLUDED_98D816C7
#include "Payment.h"

//##ModelId=653397F3010B
class Credit : public Payment
{
    //##ModelId=653397FE00D8
    int number;
    //##ModelId=6533980B02C0
    String type;
    //##ModelId=6533981402C5
    Date expireDate;
};



#endif /* CREDIT_H_HEADER_INCLUDED_98D816C7 */
