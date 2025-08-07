package Question3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoanRequestManagerTest {
    @Test
    void testApprovedFirstCondition() {
        LoanRequestManager manager = new LoanRequestManager(
            750, // creditScore
            60000, // income
            30000, // loanAmount
            false, // hasGuarantor
            12, // repaymentMonths
            false // employmentStable
        );
        assertEquals("APPROVED", manager.evaluateRequest());
    }

    @Test
    void testApprovedSecondCondition() {
        LoanRequestManager manager = new LoanRequestManager(
            660,
            65000,
            35000,
            false,
            36,
            true
        );
        assertEquals("APPROVED", manager.evaluateRequest());
    }

    @Test
    void testApprovedThirdCondition() {
        LoanRequestManager manager = new LoanRequestManager(
            620,
            30000,
            60000,
            true,
            12,
            false
        );
        assertEquals("APPROVED", manager.evaluateRequest());
    }

    @Test
    void testRejectedLowCreditNoGuarantor() {
        LoanRequestManager manager = new LoanRequestManager(
            500,
            50000,
            20000,
            false,
            36,
            false
        );
        assertEquals("REJECTED_LOW_CREDIT", manager.evaluateRequest());
    }

    @Test
    void testPendingCase() {
        LoanRequestManager manager = new LoanRequestManager(
            630,
            30000,
            60000,
            false,
            36,
            false
        );
        assertEquals("PENDING", manager.evaluateRequest());
    }

    @Test
    void testUpdateRequestChangesDecision() {
        LoanRequestManager manager = new LoanRequestManager(
            630,
            30000,
            30000,
            false,
            36,
            false
        );
        assertEquals("PENDING", manager.evaluateRequest());

        manager.updateRequest(750, 60000); // Update credit score and income
        assertEquals("APPROVED", manager.evaluateRequest());
    }
}
