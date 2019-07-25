package com.effective.java.p_02.bean;

import lombok.ToString;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/7/22 10:44 PM
 * @ProjectName: effc-java
 * @Version: 1.0.0
 */
@ToString
public abstract class Pizza {

	public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}

	final Set<Topping> toppings;

	abstract static class Bulider<T extends Bulider<T>> {
		//创建一个EnumSet空集合，指定其集合元素是Topping类的枚举值
		EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

		public T addTopping(Topping topping) {
			toppings.add(Objects.requireNonNull(topping));
			return self();
		}

		abstract Pizza build();

		abstract T self();
	}

	Pizza(Bulider<?> bulider) {
		toppings = bulider.toppings.clone();
	}

}
