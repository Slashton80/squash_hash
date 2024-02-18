package info.hccis.squash.entity;

import info.hccis.squash.util.SkillTypeUtil;
import info.hccis.util.CisUtility;

/**
 * Class represents a squash assessment
 *
 * @author bjmaclean
 * @since 20230518 modified Feb 18, 2024 by Sherri Ashton. Removed the constants
 * and adjusted the calculateScore method and the toString to reflect these
 * changes.
 */
public class Assessment {

    //constants
    public static final String LEVEL_0_DESCRIPTION = "poor";
    public static final String LEVEL_1_DESCRIPTION = "good";
    public static final String LEVEL_2_DESCRIPTION = "great";
    public static final String LEVEL_3_DESCRIPTION = "super";

    public static final int LEVEL_1_AMATEUR = 800;
    public static final int LEVEL_2_AMATEUR = 950;
    public static final int LEVEL_3_AMATEUR = 1100;

    public static final int LEVEL_1_PROFESSIONAL = 900;
    public static final int LEVEL_2_PROFESSIONAL = 1050;
    public static final int LEVEL_3_PROFESSIONAL = 1300;

    //attributes
    private String assessmentDate;
    private String athleteName;
    private String assessorName;
    private String status;

    private int score;

    //**************************************************************************
    //BJM 20230531 See the array data structures below
    //**************************************************************************
    public static final int NUMBER_OF_SKILL_TYPES = 8;
//    public static final String[] ASSESSMENT_SKILL_DESCRIPTIONS = {"Forehand Drives", "Backhand Drives",
//        "Forehand Volley Sum", "Forehand Volley Max", "Backhand Volley Sum", "Backhand Volley Max",
//        "Figure 8 Volley Sum", "Figure 8 Volley Max"};
//    public static final int[] ASSESSMENT_SKILL_FACTORS = {15, 15, 10, 5, 10, 5, 10, 5};
//    public static final int[] ASSESSMENT_SKILL_MINIMUMS = {1, 1, 0, 0, 0, 0, 0, 0};
//    public static final int[] ASSESSMENT_SKILL_MAXIMUMS = {50, 50, 100, 100, 100, 100, 100, 100};

    private int[] scores = new int[NUMBER_OF_SKILL_TYPES];

    private String assessmentCode;
    private static int sequenceNumber;

    public Assessment() {
        this.assessmentDate = "";
        this.athleteName = "";
        this.assessorName = "";
        this.status = "";
    }

    public Assessment(String assessmentDate, String athleteName, String assessorName, String status, int numberOfForehandDrives, int numberOfBackhandDrives, int numberOfForehandVolleySum, int numberOfForehandVolleyMax, int numberOfBackhandVolleySum, int numberOfBackhandVolleyMax, int numberOfFigure8VolleySum, int numberOfFigure8VolleyMax) {
        this.assessmentDate = assessmentDate;
        this.athleteName = athleteName;
        this.assessorName = assessorName;
        this.status = status;
        scores[0] = numberOfForehandDrives;
        scores[1] = numberOfBackhandDrives;
        scores[2] = numberOfForehandVolleySum;
        scores[3] = numberOfForehandVolleyMax;
        scores[4] = numberOfBackhandVolleySum;
        scores[5] = numberOfBackhandVolleyMax;
        scores[6] = numberOfFigure8VolleySum;
        scores[7] = numberOfFigure8VolleyMax;
        calculateScore();
        setAssessmentCode();
    }

    public void getInformation() {
        //Input
        System.out.println(System.lineSeparator() + "----- Enter assessment details -----" + System.lineSeparator());

        assessmentDate = CisUtility.getTodayString("yyyy-MM-dd");

        //BJM 20230523 Using method to get valid entry.
        //**************************************************************************
        // Athlete Name
        //**************************************************************************
        boolean valid;
        do {
            athleteName = CisUtility.getInputString("Athlete name:", 1, 50);
            athleteName = athleteName.trim();
            valid = CisUtility.validateName(athleteName);
            if (!valid) {
                System.out.println("Name must have a first and last name each of which are at least two characters in length");
            }
        } while (!valid);

        athleteName = CisUtility.formatName(athleteName);

        //**************************************************************************
        // Assessor Name
        //**************************************************************************
        do {
            assessorName = CisUtility.getInputString("Assessor name:", 1, 50);
            assessorName = assessorName.trim();
            valid = CisUtility.validateName(assessorName);
            if (!valid) {
                System.out.println("Name must have a first and last name each of which are at least two characters in length");
            }
        } while (!valid);

        assessorName = CisUtility.formatName(assessorName);

        //**************************************************************************
        // Status
        //**************************************************************************
        do {
            status = CisUtility.getInputString("Status (amateur or professional):");
        } while (!(status.equalsIgnoreCase("amateur") || status.equalsIgnoreCase("professional")));

        //**************************************************************************
        // Scores
        //**************************************************************************
        //****************************************************************************************
        //20230523 BJM note using overloaded getInputInt to get valid values from the user.
        //****************************************************************************************
        //20220531 BJM A1 Use the arrays to get the values.  Key point here is that although
        //  this is a bit more complex, it allows a dynamic solution that can work well with 
        //  minimum modification when the skill types change.
        //****************************************************************************************
        for (int counter = 0; counter < NUMBER_OF_SKILL_TYPES; counter++) {
            SkillType skillType = SkillTypeUtil.getSkillType(counter + 1);
            scores[counter] = CisUtility.getInputInt(skillType.getDescription(),
                    skillType.getMin(), skillType.getMax());
        }

        calculateScore();

        setAssessmentCode();

    }

    /**
     * Create the assessment code based on class attributes.
     *
     * @since 20230524
     * @author BJM
     */
    public String setAssessmentCode() {
        String temp = athleteName.substring(0, 2);
        temp += assessorName.substring(0, 2);

        if (status.equalsIgnoreCase("Amateur")) {
            temp += "1";
        } else {
            temp += "2";
        }

        sequenceNumber++;

        //Pad the code with 00 if needed.
        if (sequenceNumber < 10) {
            temp += "00";
        } else if (sequenceNumber < 100) {
            temp += "0";
        }

        temp += sequenceNumber;
        assessmentCode = temp;
        return temp.toLowerCase();
    }

    /**
     * Calculate the score based on their results
     *
     * @since 20230518
     * @author BJM
     * @modified Feb 18, 2024 by Sherri Ashton. Modified it so that the
     * calculation gets each SkillType object using
     * SkillTypeUtil.getSkillType(counter +1).
     */
    public int calculateScore() {

        //Calculate the total score
        //****************************************************************************************
        //20220531 BJM A1 Use the arrays to calculate the score.  Key point here is that although
        //  this is a bit more complex, it allows a dynamic solution that can work well with 
        //  minimum modification when the skill types change.
        //****************************************************************************************
        for (int counter = 0; counter < scores.length; counter++) {
            SkillType skillType = SkillTypeUtil.getSkillType(counter + 1);
            if (skillType != null) {
                score += scores[counter] * skillType.getFactor();
            } else {
                System.out.println("SkillType with ID " + (counter + 1) + " not found.");
            }
        }

        return score;

    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(String assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
        setAssessmentCode();
    }

    public String getAssessorName() {
        return assessorName;
    }

    public void setAssessorName(String assessorName) {
        this.assessorName = assessorName;
        setAssessmentCode();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        setAssessmentCode();
    }

    public int getScore() {
        return score;
    }

    /**
     * Gets a description based on the status and score
     *
     * @since 20230519
     * @author BJM
     */
    public String getScoreDescription() {
        String description = "";

        int level1 = 0, level2 = 0, level3 = 0, level4 = 0;

        //Set the level threshold based on status
        switch (status.toLowerCase()) {
            case "amateur":
                level1 = LEVEL_1_AMATEUR;
                level2 = LEVEL_2_AMATEUR;
                level3 = LEVEL_3_AMATEUR;
                break;
            case "professional":
                level1 = LEVEL_1_PROFESSIONAL;
                level2 = LEVEL_2_PROFESSIONAL;
                level3 = LEVEL_3_PROFESSIONAL;
                break;
        }

        //Set the description based on the levels set from the switch.
        if (score < level1) {
            description = LEVEL_0_DESCRIPTION;
        } else if (score < level2) {
            description = LEVEL_1_DESCRIPTION;
        } else if (score < level3) {
            description = LEVEL_2_DESCRIPTION;
        } else {
            description = LEVEL_0_DESCRIPTION;
        }

        return description;
    }

    public void display() {
        System.out.println(this.toString());
    }

    /**
     * @modified Feb 18, 2024 by Sherri Ashton. Used StringBuilder to build the
     * scoreDetails. Adapted for more dynamic functioning using SkillTypeUtil.
     */
    @Override
    public String toString() {
        StringBuilder scoreDetails = new StringBuilder();

        //**********************************************************************
        //BJM 20230531 Use the arrays that contain the score values.  
        //  Note that this is added to the output String below.
        //**********************************************************************
        for (int counter = 0; counter < scores.length; counter++) {
            SkillType skillType = SkillTypeUtil.getSkillType(counter + 1);
            if (skillType != null) {
                scoreDetails.append(skillType.getDescription()).append(": ").append(scores[counter]).append(System.lineSeparator());
            }
        }

        String output = System.lineSeparator() + "----- Assessment details (" + assessmentCode + ") -----" + System.lineSeparator()
                + "Assessment Date: " + assessmentDate + System.lineSeparator()
                + "Athlete Name: " + athleteName + System.lineSeparator()
                + "Assessor Name: " + assessorName + System.lineSeparator()
                + "Status: " + status + System.lineSeparator()
                + System.lineSeparator()
                + "Details:" + System.lineSeparator()
                + scoreDetails
                + System.lineSeparator()
                + "Score: " + score + "(" + getScoreDescription() + ")" + System.lineSeparator();

        return output;
    }

}
