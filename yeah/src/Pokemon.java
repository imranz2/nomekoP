public class Pokemon {
    private String name;
    private int health;
    private String move1;
    private String move2;
    private String move3;
    private int dmg1;
    private int dmg2;
    private int dmg3;
    private String type;

    public Pokemon(String name, String type, int health, String move1, int dmg1, String move2, int dmg2, String move3, int dmg3){
        this.name = name;
        this.health = health;
        this.move1 = move1;
        this.move2 = move2;
        this.move3 = move3;
        this.dmg1 = dmg1;
        this.dmg2 = dmg2;
        this.dmg3 = dmg3;
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public String getMove1() {
        return move1;
    }

    public String getMove2() {
        return move2;
    }

    public String getMove3() {
        return move3;
    }

    public String getName() {
        return name;
    }
}
