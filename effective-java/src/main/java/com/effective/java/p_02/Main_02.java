package com.effective.java.p_02;

import com.effective.java.p_02.bean.Calzone;
import com.effective.java.p_02.bean.NutritionFacts;
import com.effective.java.p_02.bean.NyPizza;
import com.effective.java.p_02.bean.Pizza;
import org.junit.Test;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/7/22 10:12 PM
 * @ProjectName: effc-java
 * @Version: 1.0.0
 */
public class Main_02 {

	@Test
	public void test01() {
		NutritionFacts bulid = new NutritionFacts.Bulider(1, 2).calories(3).sodium(4).fat(5).bulid();
		System.out.println(bulid);
	}

	@Test
	public void test02() {
		NyPizza build = new NyPizza.Bulider(NyPizza.Size.SMALL)
				.addTopping(Pizza.Topping.HAM).addTopping(Pizza.Topping.ONION).build();
		System.out.println(build);

		Calzone calzone = new Calzone.Bulider().sauceInside()
				.addTopping(Pizza.Topping.HAM).build();
		System.out.println(calzone);
	}
}
