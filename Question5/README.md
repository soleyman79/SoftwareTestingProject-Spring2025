# Answer

**(A) Input Grammar in EBNF**

```ebnf
Loan         := CreditScore "," Income "," LoanAmount "," Bool "," Months "," Bool ;

CreditScore  := "3" Digit Digit
              | "4" Digit Digit
              | "5" Digit Digit
              | "6" Digit Digit
              | "7" Digit Digit
              | "80" Digit
              | "81" Digit
              | "82" Digit
              | "83" Digit
              | "84" Digit
              | "850" ;

Income       := "0" | NonZeroDigit { Digit } ;

LoanAmount   := NonZeroDigit { Digit } ;

Months       := NonZeroDigit { Digit } ;

Bool         := "true" | "false" ;

Digit        := "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9" ;
NonZeroDigit := "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9" ;
```
