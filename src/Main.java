import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {290, 270, 250};
    public static int[] heroesDamage = {25, 15, 10};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic"};
    public static int roundNumber = 0;
    public static int medicHealth = 600;
    public static int medicDamage = 0;


    public static void main(String[] args) {
        showStatistics();
        while (!isGameOver()) {
            round();
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void round() {
        roundNumber++;
        chooseBossDefence();
        bossAttacks();
        heroesAttack();
        medicHelps();
        showStatistics();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void heroesAttack() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (bossDefence == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    damage = damage * coeff;
                    System.out.println("Critical damage: " + heroesAttackType[i] + " " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }

    public static void medicHelps (){
        for (int s = 0; s < heroesHealth.length; s++) {
            if (heroesHealth[s] < 100 && medicHealth > 100) {
                int health;
                String role;
                Random random = new Random();
                int give = random.nextInt(medicHealth);
                health = heroesHealth[s] + give;
                heroesHealth[s] = heroesHealth[s] + health;
                if (s==0){
                    role = "Physical";
                }else if (s==1){
                    role = "Magical";
                }else {
                    role = "Kinetic";
                }
                System.out.println("Medic gives " + health + " number of health to the " + role );
                break;

            }

        }
    }

    public static void bossAttacks() {
        if (medicHealth > 0) {
            if (medicHealth - bossDamage < 0) {
                medicHealth = 0;
            } else {
                medicHealth = medicHealth - bossDamage;
            }
        }
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }

        }
    }
    public static void showStatistics() {
        System.out.println("ROUND " + roundNumber + " ----------------");
        /*String defence;
        if(bossDefence == null) {
            defence = "None";
        } else {
            defence = bossDefence;
        }*/
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage
                + " defence: " + (bossDefence == null ? "None" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] +
                    " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
        }
        System.out.println("Medic health: " + medicHealth + " damage " + medicDamage );
    }
}
