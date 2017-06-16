package game.entity;


/**
 * Player state with position, health, and other
 * */
public class PlayerState {
    private UserPosition position;
    private boolean isAlive;
    private Double money;
    private Double experience;
    private Integer level;
    private Integer power;

    private Double dodgeChance;


    public UserPosition getPosition() {
        return position;
    }

    public void setPosition(UserPosition position) {
        this.position = position;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Double getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(Double dodgeChance) {
        this.dodgeChance = dodgeChance;
    }
}
