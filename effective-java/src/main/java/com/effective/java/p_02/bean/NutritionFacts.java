package com.effective.java.p_02.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/7/22 10:05 PM
 * @ProjectName: effc-java
 * @Version: 1.0.0
 */
@ToString
@Data
public class NutritionFacts {

	private int servingSize;

	private int servings;

	private int calories;

	private int fat;

	private int sodium;

	public NutritionFacts(Bulider bulider) {
		servingSize = bulider.servingSize;
		servings = bulider.servings;
		calories = bulider.calories;
		fat = bulider.fat;
		sodium = bulider.sodium;
	}

	public static class Bulider {

		private int servingSize;

		private int servings;

		private int calories;

		private int fat;

		private int sodium;

		public Bulider(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}

		public Bulider calories(int val) {
			calories = val;
			return this;
		}

		public Bulider fat(int val) {
			fat = val;
			return this;
		}

		public Bulider sodium(int val) {
			sodium = val;
			return this;
		}

		public NutritionFacts bulid() {
			return new NutritionFacts(this);
		}
	}
}
