package info.hccis.squash.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests which will test the calculateScore method.
 * @author bjmaclean
 * @since 20230519
 */
public class AssessmentJUnitTest {
    
    public AssessmentJUnitTest() {
        setUpClass();
    }
    
    @BeforeAll
    public static void setUpClass() {
        System.out.println("setUpClass running");
    }
    
    @AfterAll
    public static void tearDownClass() {
        System.out.println("tearDownClass running");
    }
    
    @BeforeEach
    public void setUp() {
        System.out.println("setUp is running");
    }
    
    @AfterEach
    public void tearDown() {
        System.out.println("tearDown running");
    }

    // @Test
     public void testCalculateScore_all_zero() {
         Assessment assessment = new Assessment(null, null, null, null, 0, 0, 0, 0, 0, 0, 0, 0);
         int score = assessment.calculateScore();
         assertEquals(0.0, score);
     }

    // @Test
     public void testCalculateScore_maria() {
         Assessment assessment = new Assessment("2022-08-22","Maria","BJ MacLean","amateur", 11,5,14,78,6,59,0,0);
         int score = assessment.calculateScore();
         assertEquals(1125, score);
     }

     public void testCalculateScore_joe() {
         Assessment assessment = new Assessment("2022-08-11","Joe","BJ MacLean","professional", 5,7,4,36,5,38,2,28);
         int score = assessment.calculateScore();
         assertEquals(800, score);
     }
     

}
