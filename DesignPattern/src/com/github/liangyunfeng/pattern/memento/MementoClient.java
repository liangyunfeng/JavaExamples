package com.github.liangyunfeng.pattern.memento;

public class MementoClient {
	public static void main(final String[] args) {
		final PlayerOriginator player = new PlayerOriginator(100, 100, 100);
		System.out.print("player's original attributes are ");
		player.showState();

		final Caretaker taker = new Caretaker();
		taker.setMemento(player.createMemento());

		player.setVitality(70);
		player.setAggressivity(60);
		player.setDefencivity(20);

		System.out
				.print("after player fight with Boss, player's attributes are ");
		player.showState();

		// reset player's attribute
		player.setMemento(taker.getMemento());
		System.out.print("after resetting, player's attributes are ");
		player.showState();
	}
}

class Memento {
	private int vitality;
	private int aggressivity;
	private int defencivity;

	public int getVitality() {
		return vitality;
	}

	public void setVitality(final int vitality) {
		this.vitality = vitality;
	}

	public int getAggressivity() {
		return aggressivity;
	}

	public void setAggressivity(final int aggressivity) {
		this.aggressivity = aggressivity;
	}

	public int getDefencivity() {
		return defencivity;
	}

	public void setDefencivity(final int defencivity) {
		this.defencivity = defencivity;
	}
}

class PlayerOriginator {
	private int vitality; // 生命力
	private int aggressivity; // 攻击力
	private int defencivity; // 防御力

	public PlayerOriginator(final int vitality, final int aggressivity,
			final int defencivity) {
		this.vitality = vitality;
		this.aggressivity = aggressivity;
		this.defencivity = defencivity;
	}

	public int getVitality() {
		return vitality;
	}

	public void setVitality(final int vitality) {
		this.vitality = vitality;
	}

	public int getAggressivity() {
		return aggressivity;
	}

	public void setAggressivity(final int aggressivity) {
		this.aggressivity = aggressivity;
	}

	public int getDefencivity() {
		return defencivity;
	}

	public void setDefencivity(final int defencivity) {
		this.defencivity = defencivity;
	}

	public Memento createMemento() {
		final Memento memento = new Memento();
		memento.setVitality(this.vitality);
		memento.setAggressivity(this.aggressivity);
		memento.setDefencivity(this.defencivity);
		return memento;
	}

	public void setMemento(final Memento memento) {
		this.vitality = memento.getVitality();
		this.aggressivity = memento.getAggressivity();
		this.defencivity = memento.getDefencivity();
	}

	public void showState() {
		System.out.print("vitality:" + this.vitality);
		System.out.print("; aggressivity:" + this.aggressivity);
		System.out.println("; defencivity:" + this.defencivity);
	}
}

class Caretaker {
	private Memento memento;

	public Memento getMemento() {
		return memento;
	}

	public void setMemento(final Memento memento) {
		this.memento = memento;
	}
}
