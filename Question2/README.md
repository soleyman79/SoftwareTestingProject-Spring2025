# Answer

---

## (A) Draw the Control Flow Graph (CFG)

Based on *Chapter 7, Page 27*, "A CFG models all executions of a method by describing control structures".

So each method needs a seprate CFG.

Since each method may have distinct logic and execution paths, **a separate CFG is required for each method** in this question.

There are two graphs for part two, one is simplified but still valid as a CFG. Another is the complete CFG.

---

### Method: `evaluateRequest`

**Edges:**
- 1 → 2  
- 1 → 3  
- 3 → 4  
- 3 → 5  

**Start Node:** 1  
**End Nodes:** 2, 4, 5

---

### Method: `isEligible`

**Edges:**
- 1 → 2  
- 1 → 6  
- 2 → 3  
- 2 → 5  
- 3 → 4  
- 3 → 5  
- 4 → 5  
- 4 → 6  

**Start Node:** 1  
**End Nodes:** 5, 6

---

## (B) Extract Test Paths for Coverage Criteria

---

### Method: `evaluateRequest`

#### Node Coverage (NC)
- [1, 2]  
- [1, 3, 5]  
- [1, 3, 4]

#### Edge Coverage (EC)
- [1, 2]  
- [1, 3, 5]  
- [1, 3, 4]

#### Prime Path Coverage (PPC)
- [1, 2]  
- [1, 3, 4]  
- [1, 3, 5]

---

### Method: `isEligible`

#### Node Coverage (NC)
- [1, 2, 3, 4, 6]  
- [1, 2, 3, 4, 5]

#### Edge Coverage (EC)
- [1, 6]  
- [1, 2, 5]  
- [1, 2, 3, 5]  
- [1, 2, 3, 4, 6]  
- [1, 2, 3, 4, 5]

#### Prime Path Coverage (PPC)
- [1, 6]  
- [1, 2, 5]  
- [1, 2, 3, 5]  
- [1, 2, 3, 4, 5]  
- [1, 2, 3, 4, 6]


---
---
---
## Part C

### Test Paths for `isEligible` Method

| Test Path         | Coverage Type | creditScore | income | loanAmount | hasGuarantor | repaymentMonths | employmentStable | Expected Output |
|------------------|----------------|-------------|--------|------------|---------------|------------------|-------------------|------------------|
| [1, 6]           | EC, PPC        | 0           | 0      | 0          | False         | 0                | True              | false            |
| [1, 2, 5]        | EC, PPC        | 800         | 55000  | 45000      | True          | 2                | True              | true             |
| [1, 2, 3, 5]     | EC, PPC        | 660         | 60001  | 35000      | True          | 3                | True              | true             |
| [1, 2, 3, 4, 5]  | NC, EC, PPC    | 620         | 62000  | 40000      | True          | 20               | True              | true             |
| [1, 2, 3, 4, 6]  | NC, EC, PPC    | 620         | 62000  | 40000      | False         | 25               | False             | false            |


---

### Test Paths for `evaluateRequest` Method

| Test Path        | Coverage Type | creditScore | income | loanAmount | hasGuarantor | repaymentMonths | employmentStable | Expected Output         |
|------------------|----------------|-------------|--------|------------|---------------|------------------|-------------------|--------------------------|
| [1, 2]           | NC, EC, PPC    | 800         | 55000  | 45000      | True          | 2                | True              | APPROVED                 |
| [1, 3, 5]        | NC, EC, PPC    | 550         | 55000  | 45000      | True          | 2                | True              | PENDING                  |
| [1, 3, 4]        | NC, EC, PPC    | 550         | 55000  | 45000      | False         | 2                | False             | REJECTED_LOW_CREDIT      |


___
___
___

## (D) Identify and Analyze Infeasible Paths

After analyzing the control flow graphs and logic of the two methods (`evaluateRequest` and `isEligible`), **no infeasible paths** were found.

All test paths generated for Node Coverage, Edge Coverage, and Prime Path Coverage are **feasible** — meaning there exists at least one combination of input values that can traverse each path during execution.
