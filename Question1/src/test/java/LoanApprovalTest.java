import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Full ECC, BCC and additional‑path test‑suite for
 * LoanApproval.isEligible(...) :contentReference[oaicite:1]{index=1}
 *
 * ─ 18 rows total (TC 0 – TC 17)
 * ─ JUnit 5 @ParameterizedTest to keep code compact
 * ─ Each row supplies inputs **plus** the expected outcome,
 *   so one method can assert both true and false cases.
 */
public class LoanApprovalTest {

    @ParameterizedTest(name = "TC{index}: expect {6}")
    @CsvSource({
        /* ----------  ECC suite (TC1‑TC3)  ---------- */
        // credit,income,loan,guarantor,months,stable,expected
        "-1,-1,-1,true,-1,true,false",   // TC1
         "0,0,0,false,0,false,false",    // TC2
         "1,1,1,false,1,false,false",    // TC3

        /* ----------  BCC suite (TC0‑TC10) ---------- */
         "1,1,1,true,1,true,false",      // TC0  base row (invalid: loan=1 but credit<300 guard)
        "-1,1,1,true,1,true,false",      // TC1  q1 b1
         "0,1,1,true,1,true,false",      // TC2  q1 b2
         "1,-1,1,true,1,true,false",     // TC3  q2 b1
         "1,0,1,true,1,true,false",      // TC4  q2 b2
         "1,1,-1,true,1,true,false",     // TC5  q3 b1
         "1,1,0,true,1,true,false",      // TC6  q3 b2
         "1,1,1,false,1,true,false",     // TC7  q4 b2
         "1,1,1,true,-1,true,false",     // TC8  q5 b1
         "1,1,1,true,0,true,false",      // TC9  q5 b2
         "1,1,1,true,1,false,false",     // TC10 q6 b2

        /* ----------  Extra path‑coverage (TC11‑TC17) ---------- */
        "700,50000,50000,false,36,false,true",   // TC11  first TRUE branch
        "650,60000,40000,false,36,true,true",    // TC12  second TRUE branch
        "600,10000,30000,true,12,false,true",    // TC13  third TRUE branch
        "620,40000,60000,false,36,false,false",  // TC14  valid but ineligible
        "699,70000,45000,false,36,true,false",   // TC15  boundary just below first TRUE branch
        "720,55000,50000,false,24,false,true",   // TC16  first TRUE branch again
        "602,20000,80000,true,23,false,true"     // TC17  third TRUE branch again
    })
    void fullSuite(int creditScore,
                   int income,
                   int loanAmount,
                   boolean hasGuarantor,
                   int repaymentMonths,
                   boolean employmentStable,
                   boolean expectedIsEligible) {

        boolean actual = LoanApproval.isEligible(
                creditScore, income, loanAmount,
                hasGuarantor, repaymentMonths, employmentStable);

        assertEquals(expectedIsEligible, actual,
                     () -> String.format("Inputs: [%d,%d,%d,%b,%d,%b]",
                         creditScore, income, loanAmount,
                         hasGuarantor, repaymentMonths, employmentStable));
    }
}
