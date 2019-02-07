package battle;

public class SpidermanBattler {
	
	int health;
	int maxHealth;
	float oldHealth;
	int damage;
	int healAmount;
	boolean protect;
	int move;
	String moveName;
	String battleText;
	boolean healthChange;
	boolean isAttacking;
	boolean attackWorked;
	boolean attackBoost;

	public SpidermanBattler () {
		this.maxHealth = 100;
		this.healAmount = 20;
		this.protect = false;
		this.health = 100;
		this.oldHealth = health;
		this.isAttacking = false;
		this.attackWorked = true;
		this.attackBoost = false;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getHealth() {
		return health;
	}
	
	public void takeDamage (int damageAmount) {
		this.health -= damageAmount;
		if (health < 0) {
			health = 0;
		}
	}

	public void Heal() {
		this.health += healAmount;
		if (health > 100) {
			health = 100;
		}
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public boolean isProtect() {
		return protect;
	}

	public void setProtect(boolean protect) {
		this.protect = protect;
	}

	public int getMove() {
		return move;
	}

	public void setMove(int move) {
		this.move = move;
		if (!isAttacking) {
			if (move == 1) {
				setBattleText("Select your choice of attack.");
			}
			if (move == 2) {
				setBattleText("You used heal. \nYou regained HP.");
				Heal();
			}
			if (move == 3) {
				setBattleText("You used protect.");
				setProtect(true);
			}
		}
		if (isAttacking) {
			if (move == 1) {		
				if (Math.random() <= .10) {
					setBattleText("The move backfired. \nYou lost HP.");
					setAttackWorked(false);
					takeDamage(75);
				}
				else {
					setBattleText("You used eviscerate.");
					setDamage (90);
				}
			}
			if (move == 2) {

					setBattleText("You showed him your Spidermon pellets.");
					
					if (isAttackBoost()) {
						setDamage(40);
						setAttackBoost(false);
					}
					else {
						setDamage(20);
						
					}

			}
			if (move == 3) {
				setBattleText("Showing him your Spidermon pellets \nwill deal double damage next turn!");
				setAttackBoost(true);
				setDamage(0);
			}
		}
		
	}

	public String getMoveName() {
		return moveName;
	}

	public void setMoveName(String moveName) {
		this.moveName = moveName;
	}
	
	public String getBattleText() {
		return battleText;
	}

	public void setBattleText(String battleText) {
		this.battleText = battleText;
	}

	public float getOldHealth() {
		return oldHealth;
	}

	public void setOldHealth(float change) {
		this.oldHealth += change;
	}
	
	public void resetHealth() {
		this.health = 100;
		this.oldHealth = 100f;
	}

	public boolean isHealthChange() {
		return healthChange;
	}

	public void setHealthChange(boolean healthChange) {
		this.healthChange = healthChange;
	}

	public boolean isAttacking() {
		return isAttacking;
	}

	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}

	public boolean AttackWorked() {
		return attackWorked;
	}

	public void setAttackWorked (boolean attackWorked) {
		this.attackWorked = attackWorked;
	}

	public boolean isAttackBoost() {
		return attackBoost;
	}

	public void setAttackBoost(boolean attackBoost) {
		this.attackBoost = attackBoost;
	}

}
