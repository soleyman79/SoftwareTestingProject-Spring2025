public class LoanApproval {
    public static boolean isEligible(int creditScore, int income, int loanAmount, boolean hasGuarantor, int repaymentMonths, boolean employmentStable) {
        if (!isInputValid(creditScore, income, loanAmount, repaymentMonths)) return false;
        if (creditScore >= 700 && income >= 50000 && loanAmount <= 50000) return true;
        if (creditScore >= 650 && income >= 60000 && loanAmount <= 40000 && employmentStable) return true;
        if (creditScore >= 600 && hasGuarantor && repaymentMonths < 24) return true;
        return false;
    }

    private static boolean isInputValid(int creditScore, int income, int loanAmount, int repaymentMonths) {
        return creditScore >= 300 && creditScore <= 850
            && income >= 0
            && loanAmount > 0
            && repaymentMonths > 0;
    }
}

