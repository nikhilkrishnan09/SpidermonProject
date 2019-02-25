package battle;

public class Enemy {
	
	//All fields and parameters for the enemy object
	int health;
	int damage;
	int healAmount;
	int maxHealth;
	float oldHealth;
	String move1;
	String move2;
	int move1Damage;
	int move2Damage;
	String battleText;
	int move;
	boolean attackWorked;
	
	//Constructor for an enemy object, able to be varied for different types of enemies
	public Enemy(int maxHealth, int healAmount, String move1, String move2, int move1Damage, int move2Damage) {
		this.maxHealth = maxHealth;
		this.healAmount = healAmount;
		this.health = maxHealth;
		this.oldHealth = health;
		this.move1 = move1;
		this.move2 = move2;
		this.move1Damage = move1Damage;
		this.move2Damage = move2Damage;
		attackWorked = false;
		this.battleText = "Working please";
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}

	public int getHealth() {
		return health;
	}
	
	public void setAttackWorked(boolean attackWorked) {
		this.attackWorked = attackWorked;
	}
	
	public boolean didAttackWork() {
		return attackWorked;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public void takeDamage (int damageAmount) {
		this.health -= damageAmount;
	}
	
	public float getOldHealth() {
		return oldHealth;
	}

	public void setOldHealth(float change) {
		this.oldHealth += change;
	}
	
	public String getBattleText() {
		return battleText;
	}

	public void setBattleText(String battleText) {
		this.battleText = battleText;
	}

	public int getMove() {
		return this.move;
	}
	
	public void Heal() {
		this.health += healAmount;
		if (health > 100) {
			health = 100;
		}
	}

	//Controls the damage output and result based on the move type and the protect status
	//of the player
	public void setMove(int move, boolean protect) {
		this.move = move;
		
		if (move == 1 && !protect) {
			setBattleText("The enemy used " + move1 + ".\nYou took damage.");
			
			setDamage(move1Damage);
			setAttackWorked(true);
		}
		if (move == 1 && protect) {
			setBattleText("The enemy used " + move1 + ".\nBut it failed.");
			
			setDamage(0);
			setAttackWorked(false);
		}
		if (move == 2 && !protect) {
			setBattleText("The enemy used " + move2 + ".\nYou took damage");
			
			setDamage(move2Damage);
			setAttackWorked(true);
		}
		if (move == 2 && protect) {
			setBattleText("The enemy used " + move2 + ".\nBut it failed.");
			
			setDamage(0);
			setAttackWorked(false);
		}
		if (move == 3) {
			setBattleText("The enemy recovered HP.");
			setAttackWorked(false);
		}
		
	}
	
	
}
