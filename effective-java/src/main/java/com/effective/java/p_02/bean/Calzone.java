package com.effective.java.p_02.bean;

import lombok.ToString;

/**
 * @Author: WangKun
 * @Description: 酱汁是否外置型披萨
 * @Date: Created in 2019/7/23 10:42 PM
 * @ProjectName: effective-java
 * @Version: 1.0.0
 */
@ToString
public class Calzone extends Pizza {

	private final boolean sauceInside;

	public static class Bulider extends Pizza.Bulider<Bulider> {

		private boolean sauceInside = false;

		public Bulider sauceInside() {
			sauceInside = true;
			return this;
		}

		@Override
		public Calzone build() {
			return new Calzone(this);
		}

		@Override
		Bulider self() {
			return this;
		}
	}

	private Calzone(Bulider bulider) {
		super(bulider);
		sauceInside = bulider.sauceInside;
	}

	@Override
	public String toString() {
		return "Calzone with " + toppings + "sauceInside:" + sauceInside;
	}
}
