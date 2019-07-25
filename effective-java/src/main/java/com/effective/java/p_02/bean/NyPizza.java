package com.effective.java.p_02.bean;

import lombok.ToString;

import java.util.Objects;

/**
 * @Author: WangKun
 * @Description: 经典纽约风味披萨
 * @Date: Created in 2019/7/22 11:31 PM
 * @ProjectName: effc-java
 * @Version: 1.0.0
 */
@ToString
public class NyPizza extends Pizza {

	public enum Size {SMALL, MEDIUM, LARCE}

	private final Size size;

	public static class Bulider extends Pizza.Bulider<Bulider> {
		private final Size size;

		public Bulider(Size size) {
			this.size = Objects.requireNonNull(size);
		}

		@Override
		public NyPizza build() {
			return new NyPizza(this);
		}

		@Override
		Bulider self() {
			return this;
		}
	}

	private NyPizza(Bulider bulider) {
		super(bulider);
		size = bulider.size;
	}

	@Override
	public String toString() {
		return "New York Pizza with " + toppings + "size:" + size;
	}
}
