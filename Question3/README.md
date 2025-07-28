# Question 3

## Section A

The program's **logical model** is structured as follows:

``` java
(~isInputValid & result = false) |
(creditScore >= 700 & income >= 50000 & loanAmount <= 50000 & result=true) |
(creditScore >= 650 & income >= 60000 & loanAmount <= 40000 & employmentStable = true & result = true) |
(creditScore >= 600 & hasGuarantor = true & repaymentMonths < 24 & result = true)
```

The `isValidInput` is defined as follows:

``` java
isValidInput =
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

``` text
P1 = a & b & c & d & e
P2 = f & g & h
P3 = i & j & k & l
P4 = m & n & o
P = {P1, P2, P3, P4}
```

## Section B

### Clause Coverage

*Clause Coverage* (CC): For  all p in P for each c in Cp, TR contains two requirements: c evaluates to true, and c evaluates to false.

Here is the corresponding row number for each predicate.

- #Row for P1 = {9, 24}
  - a = T, b = F, c = T, d = T, e = T
  - a = F, b = T, c = F, d = F, e = F
- #Row for P2 = {1, 9}
  - f = T, g = T, h = T
  - f = F, g = F, h = F
- #Row for P3 = {6, 11}
  - i = T, j = F, k = T, l = F
  - i = F, j = T, k = F, l = T
- #Row for P4 = {2, 7}
  - m = T, n = T, o = F
  - m = F, n = F, o = T

Based on these rows, the corresponding test case values are as follows (× = don't care):

| Test Case | creditScore | income | loanAmount | hasGuarantor  | repaymentMonths  | employmentStable  |
|:---------:|:-----------:|:------:|:----------:|:-------------:|:----------------:|:-----------------:|
| TC1 (P1)  | 500         | 1000   | 10000      | true (×)      | 24               | true (×)          |
| TC2 (P1)  | 200         | -100   | -1000      | true (×)      | 0                | true (×)          |
| TC3 (P2)  | 700         | 50000  | 50000      | true (×)      | 24 (×)           | true (×)          |
| TC4 (P2)  | 600         | 40000  | 40000      | true (×)      | 24 (×)           | true (×)          |
| TC5 (P3)  | 650         | 30000  | 30000      | true (×)      | 24 (×)           | false             |
| TC6 (P3)  | 600         | 60000  | 45000      | true (×)      | 24 (×)           | true              |
| TC7 (P4)  | 600         | 100 (×)| 10000 (×)  | true          | 30               | true (×)          |
| TC8 (P4)  | 100         | 100 (×)| 10000 (×)  | false         | 12               | true (×)          |

### Correlated Active Clause Coverage

*Correlated Active Clause Coverage* (CACC): For each p in P and each major clause ci in Cp, choose minor clauses cj,  j != i, so that ci determines p.
TR has two requirements for each ci:

- ci evaluates to true
- ci evaluates to false.  

The values chosen for the minor clauses cj must cause p to be true for one value of the major clause ci and false for the other.

In this question, all predicates consist of a series of operands connected by AND operators.
Therefore, each clause is active when all other clauses in the predicate are true.
Below is the corresponding test requirement for each predicate.

- #Row for P1 = {1, 2, 3, 5, 9, 17}
  - a = T, b = T, c = T, d = T, e = T
  - a = T, b = T, c = T, d = T, **e = F**
  - a = T, b = T, c = T, **d = F**, e = T
  - a = T, b = T, **c = F**, d = T, e = T
  - a = T, **b = F**, c = T, d = T, e = T
  - **a = F**, b = T, c = T, d = T, e = T
- #Row for P2 = {1, 2, 3, 5}
  - f = T, g = T, h = T
  - f = T, g = T, **h = F**
  - f = T, **g = F**, h = T
  - **f = F**, g = T, h = T
- #Row for P3 = {1, 2, 3, 5, 9}
  - i = T, j = T, k = T, l = T
  - i = T, j = T, k = T, **l = F**
  - i = T, j = T, **k = F**, l = T
  - i = T, **j = T**, k = T, l = T
  - **i = T**, j = T, k = T, l = T
- #Row for P4 = {1, 2, 3, 5, 9}
  - m = T, n = T, o = T
  - m = T, n = T, **o = F**
  - m = T, **n = F**, o = T
  - **m = F**, n = T, o = T

Based on these rows, the corresponding test case values are as follows (× = don't care):

| Test Case | creditScore | income | loanAmount | hasGuarantor  | repaymentMonths  | employmentStable  |
|:---------:|:-----------:|:------:|:----------:|:-------------:|:----------------:|:-----------------:|
| TC1 (P1)  | 500         | 1000   | 10000      | true (×)      | 24               | true (×)          |
| TC2 (P1)  | 200         | 1000   | 10000      | true (×)      | 24               | true (×)          |
| TC3 (P1)  | 900         | 1000   | 10000      | true (×)      | 24               | true (×)          |
| TC4 (P1)  | 500         | -10    | 10000      | true (×)      | 24               | true (×)          |
| TC5 (P1)  | 500         | 1000   | 0          | true (×)      | 24               | true (×)          |
| TC6 (P1)  | 500         | 1000   | 10000      | true (×)      | 0                | true (×)          |
| TC7 (P2)  | 700         | 50000  | 50000      | true (×)      | 24 (×)           | true (×)          |
| TC8 (P2)  | 600         | 50000  | 50000      | true (×)      | 24 (×)           | true (×)          |
| TC9 (P2)  | 700         | 40000  | 50000      | true (×)      | 24 (×)           | true (×)          |
| TC10 (P2) | 700         | 50000  | 60000      | true (×)      | 24 (×)           | true (×)          |
| TC11 (P3) | 700         | 50000  | 60000      | true (×)      | 24 (×)           | true (×)          |

| i      | creditScore >= 650       |
| j      | income >= 60000          |
| k      | loanAmount <= 40000      |
| l      | employmentStable = true  |
