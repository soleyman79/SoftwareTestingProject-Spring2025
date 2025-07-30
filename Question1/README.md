## Interface‑Based Input Domain Model

| Feature (qᵢ)              | Block | Range / Condition   |
| ------------------------- | ----- | ------------------- |
| **q1 – creditScore**      | b1    | creditScore < 0     |
|                           | b2    | creditScore = 0     |
|                           | b3    | creditScore > 0     |
| **q2 – income**           | b1    | income < 0          |
|                           | b2    | income = 0          |
|                           | b3    | income > 0          |
| **q3 – loanAmount**       | b1    | loanAmount < 0      |
|                           | b2    | loanAmount = 0      |
|                           | b3    | loanAmount > 0      |
| **q4 – hasGuarantor**     | b1    | true                |
|                           | b2    | false               |
| **q5 – repaymentMonths**  | b1    | repaymentMonths < 0 |
|                           | b2    | repaymentMonths = 0 |
|                           | b3    | repaymentMonths > 0 |
| **q6 – employmentStable** | b1    | true                |
|                           | b2    | false               |

---

## Functionality‑Based Input Domain Model

Based on page 27 of PowerPoint Ch6, "Modeling can be based on requirements, not implementation." Since we don't have requirements here, the following answer is not correct. However, since it was asked, we provide it based on implementation.

| Feature (qᵢ)               | Block | Range / Condition          | Behavioral Notes / Rule Links |
| -------------------------- | ----- | -------------------------- | ----------------------------- |
| **q7 – creditScore**       | b1    | < 300 or > 850             | invalid                       |
|                            | b2    | 300 – 599                  | always reject                 |
|                            | b3    | 600 – 649                  | candidate Rule 3              |
|                            | b4    | 650 – 699                  | candidate Rules 2 & 3         |
|                            | b5    | 700 – 850                  | candidate Rules 1/2/3         |
| **q8 – income**            | b1    | income < 0                 | invalid                       |
|                            | b2    | 0 – 49,999                 | not referenced by any rule    |
|                            | b3    | 50,000 – 59,999            | candidate Rule 1              |
|                            | b4    | ≥ 60,000                   | candidate Rules 1 & 2         |
| **q9 – loanAmount**        | b1    | ≤ 0                        | invalid                       |
|                            | b2    | 1 – 40,000                 | candidate Rules 1 & 2         |
|                            | b3    | 40,001 – 50,000            | candidate Rule 1              |
|                            | b4    | > 50,000                   | always reject                 |
| **q10 – hasGuarantor**     | b1    | true                       | required for Rule 3           |
|                            | b2    | false                      | Rule 3 impossible             |
| **q11 – repaymentMonths**  | b1    | ≤ 0                        | invalid                       |
|                            | b2    | 1 – 23                     | candidate Rule 3              |
|                            | b3    | ≥ 24                       | Rule 3 impossible         |
| **q12 – employmentStable** | b1    | true                       | required for Rule 2           |
|                            | b2    | false                      | Rule 2 impossible             |
| **q13 – inputValidity**    | b1    | at least one invalid input | function returns `false`      |
|                            | b2    | all inputs valid           |                               |
____

Based on the answer to question one, the ECC and BCC will be as follows (since the requirements for functionally-based IDM have not been given, we do not consider that in our following answers)


| Row Name | Feature (qᵢ)     | Block | Range / Condition   | Example Value |
| -------- | ---------------- | ----- | ------------------- | ------------- |
| **a1**   | creditScore      | b1    | creditScore < 0     |  -1           |
| **a2**   | creditScore      | b2    | creditScore = 0     |  0            |
| **a3**   | creditScore      | b3    | creditScore > 0     |  1            |
| **b1**   | income           | b1    | income < 0          |  -1           |
| **b2**   | income           | b2    | income = 0          |  0            |
| **b3**   | income           | b3    | income > 0          |  1            |
| **c1**   | loanAmount       | b1    | loanAmount < 0      |  -1           |
| **c2**   | loanAmount       | b2    | loanAmount = 0      |  0            |
| **c3**   | loanAmount       | b3    | loanAmount > 0      |  1            |
| **d1**   | hasGuarantor     | b1    | true                |  true         |
| **d2**   | hasGuarantor     | b2    | false               |  false        |
| **e1**   | repaymentMonths  | b1    | repaymentMonths < 0 |  -1           |
| **e2**   | repaymentMonths  | b2    | repaymentMonths = 0 |  0            |
| **e3**   | repaymentMonths  | b3    | repaymentMonths > 0 |  1            |
| **f1**   | employmentStable | b1    | true                |  true         |
| **f2**   | employmentStable | b2    | false               |  false        |

---

The test case for ECC is:

| Test Case                    | creditScore | income | loanAmount | hasGuarantor | repaymentMonths | employmentStable | **Expected Output** |
| ---------------------------- | ----------: | -----: | ---------: | ------------ | --------------: | ---------------- | ----------------------- |
| **TC 1** (a1 b1 c1 d1 e1 f1) |          -1 |     -1 |         -1 |  true        |              -1 |  true            | **false**               |
| **TC 2** (a2 b2 c2 d2 e2 f2) |           0 |      0 |          0 |  false       |               0 |  false           | **false**               |
| **TC 3** (a3 b3 c3 d2 e3 f2) |           1 |      1 |          1 |  false       |               1 |  false           | **false**               |


The test case for BCC is:

| Test Case | creditScore | income | loanAmount | hasGuarantor | repaymentMonths | employmentStable | Variation explained | **Expected Output** |
| --------- | ----------: | -----: | ---------: | ------------ | --------------: | ---------------- | ------------------- | ----------------------- |
| **TC 0**  |           1 |      1 |          1 |  true        |               1 |  true            | Base row            | **false**               |
| **TC 1**  |          ‑1 |      1 |          1 |  true        |               1 |  true            | q1 to b1            | **false**               |
| **TC 2**  |           0 |      1 |          1 |  true        |               1 |  true            | q1 to b2            | **false**               |
| **TC 3**  |           1 |     ‑1 |          1 |  true        |               1 |  true            | q2 to b1            | **false**               |
| **TC 4**  |           1 |      0 |          1 |  true        |               1 |  true            | q2 to b2            | **false**               |
| **TC 5**  |           1 |      1 |         ‑1 |  true        |               1 |  true            | q3 to b1            | **false**               |
| **TC 6**  |           1 |      1 |          0 |  true        |               1 |  true            | q3 to b2            | **false**               |
| **TC 7**  |           1 |      1 |          1 |  false       |               1 |  true            | q4 to b2            | **false**               |
| **TC 8**  |           1 |      1 |          1 |  true        |              ‑1 |  true            | q5 to b1            | **false**               |
| **TC 9**  |           1 |      1 |          1 |  true        |               0 |  true            | q5 to b2            | **false**               |
| **TC 10** |           1 |      1 |          1 |  true        |               1 |  false           | q6 to b2            | **false**               |



Because the code didn’t have description and requirements, we didn’t use the Functionality-Based IDM. Based on that, there are 14 test cases for this part of the question. So we added some other test case to meet the 20 test case that is needed.

| Test Case | creditScore |  income | loanAmount | hasGuarantor | repaymentMonths | employmentStable | Expected isEligible | Return path hit                                                                    |
| --------- | ----------: | ------: | ---------: | ------------ | --------------: | ---------------- | ------------------- | ---------------------------------------------------------------------------------- |
| **TC 11** |         700 |  50 000 |     50 000 |  false       |              36 |  false           | **true**            | `creditScore ≥ 700` & `income ≥ 50000` & `loanAmount ≤ 50000`                      |
| **TC 12** |         650 |  60 000 |     40 000 |  false       |              36 |  true            | **true**            | `creditScore ≥ 650` & `income ≥ 60000` & `loanAmount ≤ 40000` & `employmentStable` |
| **TC 13** |         600 |  10 000 |     30 000 |  true        |              12 |  false           | **true**            | `creditScore ≥ 600` & `hasGuarantor` & `repaymentMonths < 24`                      |
| **TC 14** |         620 |  40 000 |     60 000 |  false       |              36 |  false           | **false**           | All conditions fail ⇒ falls through to final `return false`                        |
| **TC 15** |         699 |  70 000 |     45 000 |  false       |              36 |  true            | **false**           | All conditions fail (just below first‑path threshold)                              |
| **TC 16** |         720 |  55 000 |     50 000 |  false       |              24 |  false           | **true**            | Same first‑path branch as TC 11 (≥ 700, ≥ 50000, ≤ 50000)                          |
| **TC 17** |         602 |  20 000 |     80 000 |  true        |              23 |  false           | **true**            | Third‑path branch (`creditScore ≥ 600` & guarantor & `< 24 months`)                |




