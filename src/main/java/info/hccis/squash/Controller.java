package info.hccis.squash;

import info.hccis.squash.entity.Assessment;
import info.hccis.squash.util.SkillTypeUtil;
import info.hccis.util.CisUtility;
import java.util.ArrayList;

/**
 * Assignment #3 (Squash Skills)
 *
 * @author bjmaclean
 * @since 20230517
 * @modified Feb 18, 2024 by Sherri Ashton.Finished the add a new assessment and
 * show all assessments.
 */
public class Controller {

    private static ArrayList<Assessment> assessments = new ArrayList();

    public static void main(String[] args) {

        //Ensure that the skill types are loaded
        SkillTypeUtil.load();

        CisUtility.displayDesign();

        System.out.println("Welcome to the CIS Squash Skills app.");

        Assessment assessment1 = new Assessment("2023-05-18", "John Adams", "John Power", "amateur", 1, 2, 3, 4, 5, 6, 7, 8);
        assessments.add(assessment1);

        final String MENU = System.lineSeparator()
                + "------------------------------------" + System.lineSeparator()
                + "----       CIS MENU             ----" + System.lineSeparator()
                + "----         A-Add              ----" + System.lineSeparator()
                + "----         B-Show             ----" + System.lineSeparator()
                + "----         C-Show Skill Types ----" + System.lineSeparator()
                + "----         X-Exit             ----" + System.lineSeparator()
                + "------------------------------------" + System.lineSeparator();

        String option = "";
        do {
            option = CisUtility.getInputString(MENU);

            switch (option.toUpperCase()) {
                case "A":
                    add();
                    break;
                case "B":
                    show();
                    break;
                case "C":
                    SkillTypeUtil.displaySkillTypes();
                    break;
                case "X":
                    System.out.println("Thanks for using the program, have a nice day.");
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        } while (!option.equalsIgnoreCase("X"));

    }

    /**
     * Add a new Assessment
     *
     * @since 20230601
     * @author BJM
     * @modified Feb 18, 2024 by Sherri Ashton. Created a new instance of the
     * Assessment Class and created a method to get the info so that the
     * assessment info is stored in the collection of assessment ArrayList.
     */
    public static void add() {

        Assessment newAssessment = new Assessment();

        newAssessment.getInformation();

        assessments.add(newAssessment);

        System.out.println("Assessment added!");

    }

    /**
     * Show all assessments
     *
     * @since 20230601
     * @author BJM
     * @modified Feb 18, 2024 by Sherri Ashton. Implemented a control flow that
     * checks if there are any assessments to show and either informs the user
     * if there are none or displays each assessment's details.
     */
    public static void show() {

        if (assessments.isEmpty()) {
            System.out.println("No assessments available.");
        } else {
            for (Assessment current : assessments) {
                current.display();
            }
        }
    }
}
