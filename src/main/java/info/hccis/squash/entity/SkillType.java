package info.hccis.squash.entity;

/**
 * Stub class to be completed
 */
public class SkillType {

    private int skillTypeId;
    private String description;
    private int min;
    private int max;
    private int factor;

    // Constructors
    public SkillType() {
        // Default constructor
    }

    public SkillType(int sequenceNumber, String description, int min, int max, int factor) {
        this.skillTypeId = sequenceNumber;
        this.description = description;
        this.min = min;
        this.max = max;
        this.factor = factor;
    }

    // Getters and Setters
    public int getSkillTypeId() {
        return skillTypeId;
    }

    public void setSkillTypeId(int sequenceNumber) {
        this.skillTypeId = sequenceNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Skill type: " + skillTypeId + System.lineSeparator()
                + "Description: " + description + System.lineSeparator()
                + "Valid range: " + min + " to " + max + System.lineSeparator()
                + "Factor: " + factor + System.lineSeparator();
    }
}
