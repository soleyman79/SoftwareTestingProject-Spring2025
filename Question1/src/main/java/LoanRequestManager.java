public class LoanRequestManager {
    private int requestId;
    private int creditScore;
    private int income;
    private int loanAmount;
    private boolean hasGuarantor;
    private int repaymentMonths; 
    private boolean employmentStable;
    private String status;

    public LoanRequestManager(int requestId, int creditScore, int income, int loanAmount, boolean hasGuarantor, int repaymentMonths, boolean employmentStable) {
        this.requestId = requestId;
        this.creditScore = creditScore;
        this.income = income;
        this.loanAmount = loanAmount;
        this.hasGuarantor = hasGuarantor;
        this.repaymentMonths = repaymentMonths;
        this.employmentStable = employmentStable;
        this.status = evaluateRequest();
    }

    private String evaluateRequest() {
        if (LoanApproval.isEligible(creditScore, income, loanAmount, hasGuarantor, repaymentMonths, employmentStable)) {
            return "APPROVED";
        } else if (creditScore < 600 && !hasGuarantor) {
            return "REJECTED_LOW_CREDIT";
        } else {
            return "PENDING";
        }
    }

    public String getStatus() {
        return status;
    }

    public void updateRequest(int newCreditScore, int newIncome) {
        this.creditScore = newCreditScore;
        this.income = newIncome;
        this.status = evaluateRequest();
    }
}