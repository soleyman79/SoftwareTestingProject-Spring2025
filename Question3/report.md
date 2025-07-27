# Question 3

## Section A

The program's **logical model** is structured as follows:

```
(~isInputValid & result = false) |
(creditScore >= 700 & income >= 50000 & loanAmount <= 50000 & result=true) |
(creditScore >= 650 & income >= 60000 & loanAmount <= 40000 & employmentStable = true & result = true) |
(creditScore >= 600 & hasGuarantor = true & repaymentMonths < 24 & result = true)
```

The `isValidInput` is defined as follows:

```
isValidInput=
    creditScore >= 300 &
    creditScore <= 850 &
    income >= 0 &
    loanAmount > 0 &
    repaymentMonths > 0
```

The following are the **renamed clauses** in this function:

| Clause | Condition                |
|--------|--------------------------|
| a      | creditScore >= 300       |
| b      | creditScore <= 850       |
| c      | income >= 0              |
| d      | loanAmount > 0           |
| e      | repaymentMonths > 0      |
| f      | creditScore >= 700       |
| g      | income >= 50000          |
| h      | loanAmount <= 50000      |
| i      | creditScore >= 650       |
| j      | income >= 60000          |
| k      | loanAmount <= 40000      |
| l      | employmentStable = true  |
| m      | creditScore >= 600       |
| n      | hasGuarantor = true      |
| o      | repaymentMonths < 24     |

Finally, The `isEligible` function contains the following **predicates**:

```
P1 = a & b & c & d & e
P2 = f & g & h
P3 = i & j & k & l
P4 = m & n & o
```