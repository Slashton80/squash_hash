package info.hccis.squash.util;

import info.hccis.squash.entity.SkillType;
import java.util.HashMap;

/**
 * This class will simulate a data access object which could load values from a
 * database to make a more dynamic class. Classes which use this class can
 * obtain information about skill types by using the public methods. Note that
 * the methods and attributes are static so an instance of this class is not
 * needed.
 *
 * @author UPDATE
 * @since 20230601
 * @modified Feb 18, 2024 by Sherri Ashton. Completed the load the skillTypesMap
 * using values from the arrays, the getter which will return the HashMap
 * object, the return the SkillType object associated with the id,and the show
 * each skill type to the console.
 */
public class SkillTypeUtil {

    //**************************************************************************
    //BJM 20230531 See the array data structures below
    //**************************************************************************
    public static final int NUMBER_OF_SKILL_TYPES = 8;
    private static final String[] ASSESSMENT_SKILL_DESCRIPTIONS = {"Forehand Drives", "Backhand Drives",
        "Forehand Volley Sum", "Forehand Volley Max", "Backhand Volley Sum", "Backhand Volley Max",
        "Figure 8 Volley Sum", "Figure 8 Volley Max"};
    private static final int[] ASSESSMENT_SKILL_FACTORS = {15, 15, 10, 5, 10, 5, 10, 5};
    private static final int[] ASSESSMENT_SKILL_MINIMUMS = {1, 1, 0, 0, 0, 0, 0, 0};
    private static final int[] ASSESSMENT_SKILL_MAXIMUMS = {50, 50, 100, 100, 100, 100, 100, 100};

    private static HashMap<Integer, SkillType> skillTypesMap = new HashMap();
    private static int nextId = 1;

    /**
     * load the HashMap with the new SkillType objects using the arrays above.
     *
     * @since 20230601
     * @author UPDATE
     * @modified Feb 18, 2024 by sherri ashton.Implemented a process that
     * populates a HashMap with SkillType.
     */
    public static void load() {
        for (int i = 0; i < NUMBER_OF_SKILL_TYPES; i++) {
            skillTypesMap.put(nextId, new SkillType(
                    nextId,
                    ASSESSMENT_SKILL_DESCRIPTIONS[i],
                    ASSESSMENT_SKILL_MINIMUMS[i],
                    ASSESSMENT_SKILL_MAXIMUMS[i],
                    ASSESSMENT_SKILL_FACTORS[i]
            ));
            nextId++;
        }
    }

    /**
     * Getter which will return the HashMap object
     *
     * @since 20230601
     * @author BJM
     * @modified Feb 18, 2024 by sherri ashton. Provided a way to access the
     * skillTypesMap outside the SkillTypeUtil class by returning skillTypesMap.
     */
    public static HashMap<Integer, SkillType> getSkillTypesMap() {
        return skillTypesMap;
    }

    /**
     * Return the SkillType object associated with the id
     *
     * @param id skillTypeId
     * @return Appropriate SkillType
     * @since 20230601
     * @author BJM
     * @modified Feb 18, 2024 by sherri ashton.Provided a way to access the
     * skillTypesMap from outside the SkillTypeUtil class. By returning the
     * static skillTypesMap directly, any other part of the application that
     * calls getSkillTypesMap() can now retrieve the entire HashMap containing
     * the SkillType objects.
     */
    public static SkillType getSkillType(int id) {
        return skillTypesMap.getOrDefault(id, null);
    }

    /**
     * Show each skill type to the console
     *
     * @since 20230601
     * @author BJM
     * @modified Feb 18, 2024 by sherri ashton.Showing the displaySkillTypes.
     * Used an enhanced for-loop to iterate through all the SkillType objects
     * stored in the skillTypesMap.
     */
    public static void displaySkillTypes() {
        System.out.println("Skill types:");
        for (SkillType current : skillTypesMap.values()) {
            System.out.println("Skill type: " + current.getSkillTypeId());
            System.out.println("Description: " + current.getDescription());
            System.out.println("Valid range: " + current.getMin() + " to " + current.getMax());
            System.out.println("Factor: " + current.getFactor());
            System.out.println();

        }
    }
}
