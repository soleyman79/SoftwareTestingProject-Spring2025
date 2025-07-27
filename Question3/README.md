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

``` prolog
P1 = a & b & c & d & e
P2 = f & g & h
P3 = i & j & k & l
P4 = m & n & o
P = {P1, P2, P3, P4}
```

## Section B

### Clause Coverage

**Clause Coverage** (CC): For  all p in P for each c in Cp, TR contains two requirements: c evaluates to true, and c evaluates to false.

Here is the corresponding row number for each predicate.

- #Row for P1 = {9, 24}
  - a = T, b = F, c = T, d = T, e = T
  - a = F, b = T, c = F, d = F, e = F
- #Row for P2 = {1, 32}
  - a = T, b = F, c = T, d = T, e = T
  - a = F, b = T, c = F, d = F, e = F
- #Row for P3 = {1, 32}
  - a = T, b = F, c = T, d = T, e = T
  - a = F, b = T, c = F, d = F, e = F
- #Row for P4 = {1, 32}
  - a = T, b = F, c = T, d = T, e = T
  - a = F, b = T, c = F, d = F, e = F
- #Row for P5 = {1, 32}
  - a = T, b = F, c = T, d = T, e = T
  - a = F, b = T, c = F, d = F, e = F

Based on these rows, the corresponding test case values are as follows (× = don't care):

| Test Case | creditScore | income | loanAmount | hasGuarantor  | repaymentMonths  | employmentStable  |
|:---------:|:-----------:|:------:|:----------:|:-------------:|:----------------:|:-----------------:|
| TC1 (P1)  | 500         | 1000   | 10000      | true (×)      | 24               | true (×)          |
| TC2 (P1)  | 200         | -100   | -1000      | true (×)      | 0                | true (×)          |
| TC3 (P2)  | 500         | 1000   | 10000      | true (×)      | 24               | true (×)          |
| TC4 (P2)  | 500         | 1000   | 10000      | true (×)      | 24               | true (×)          |
| TC5 (P3)  | 500         | 1000   | 10000      | true (×)      | 24               | true (×)          |
| TC6 (P3)  | 500         | 1000   | 10000      | true (×)      | 24               | true (×)          |
| TC7 (P4)  | 500         | 1000   | 10000      | true (×)      | 24               | true (×)          |
| TC8 (P4)  | 500         | 1000   | 10000      | true (×)      | 24               | true (×)          |
| TC9 (P5)  | 500         | 1000   | 10000      | true (×)      | 24               | true (×)          |
| TC10 (P5) | 500         | 1000   | 10000      | true (×)      | 24               | true (×)          |
