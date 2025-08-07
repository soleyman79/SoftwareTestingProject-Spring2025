import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoanTest {

    // Tests for isEligible method in LoanApproval
    @Test
    void testIsEligible_Path_1_6() {
        assertFalse(LoanApproval.isEligible(0, 0, 0, false, 0, true));
    }

    @Test
    void testIsEligible_Path_1_2_5() {
        assertTrue(LoanApproval.isEligible(800, 55000, 45000, true, 2, true));
    }

    @Test
    void testIsEligible_Path_1_2_3_5() {
        assertTrue(LoanApproval.isEligible(660, 60001, 35000, true, 3, true));
    }

    @Test
    void testIsEligible_Path_1_2_3_4_5() {
        assertTrue(LoanApproval.isEligible(620, 62000, 40000, true, 20, true));
    }

    @Test
    void testIsEligible_Path_1_2_3_4_6() {
        assertFalse(LoanApproval.isEligible(620, 62000, 40000, false, 25, false));
    }

    // Tests for evaluateRequest method in LoanRequestManager
    @Test
    void testEvaluateRequest_Path_1_2() {
        LoanRequestManager req = new LoanRequestManager(1, 800, 55000, 45000, true, 2, true);
        assertEquals("APPROVED", req.getStatus());
    }

    @Test
    void testEvaluateRequest_Path_1_3_5() {
        LoanRequestManager req = new LoanRequestManager(2, 550, 55000, 45000, true, 2, true);
        assertEquals("PENDING", req.getStatus());
    }

    @Test
    void testEvaluateRequest_Path_1_3_4() {
        LoanRequestManager req = new LoanRequestManager(3, 550, 55000, 45000, false, 2, false);
        assertEquals("REJECTED_LOW_CREDIT", req.getStatus());
    }
}
