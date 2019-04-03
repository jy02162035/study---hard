package generic.genericInterface;

import java.util.ArrayList;

import generic.genericClass.Generic;

public class GenericFruit {
	class Fruit {
		@Override
		public String toString() {
			return "fruit";
		}
	}

	class Apple extends Fruit {
		@Override
		public String toString() {
			return "apple";
		}
	}

	class Person {
		@Override
		public String toString() {
			return "Person";
		}
	}

	// 静态泛型方法
	/**
	 * 如果在类中定义使用泛型的静态方法，需要添加额外的泛型声明（将这个方法定义成泛型方法） 即使静态方法要使用泛型类中已经声明过的泛型也不可以。
	 * 如：public static void show(T t){..},此时编译器会提示错误信息：
	 * "StaticGenerator cannot be refrenced from static context"
	 */
	public static <T> void show_5(T t) {
		System.out.println(t.toString());
	}

	/**
	 * 泛型类<T>
	 * 
	 * @author pengshun.wu
	 *
	 * @param <T>
	 */
	class GenerateTest<T> {
		public void show_1(T t) {
			System.out.println(t.toString());
		}

		// 在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
		public <T> void show_2(T t) {
			System.out.println("*****************使用泛型T*****************");
			System.out.println(t.toString());
		}

		// 在泛型类中定义了一个泛型方法，使用泛型<E>，类型可以与T相同，也可以不同
		// 由于在泛型方法中声明了一个泛型<E>，因此在泛型类中即使没有声明泛型<E>，编译器也可以正确识别泛型<E>
		public <E> void show_3(E t) {
			System.out.println("*****************使用泛型<E>*****************");
			System.out.println(t.toString());
		}

		// 可变参数的泛型方法
		public <F> void show_4(F... args) {
			System.out.println("*****************可变参数的泛型方法*****************");
			for (F f : args) {
				System.out.println(f.toString());
			}
		}
		
		// 泛型上下边界
		public void show_5(Generic<? extends Number> obj){
			System.out.println("*****************泛型上下边界*****************");
			System.out.println(obj.toString());
			System.out.println(obj.getKey());
		}
	}

	public static void main(String[] args) {
		/**
		 * 泛型方法总结 无论何时，如果你能做到，你就该尽量使用泛型方法。也就是说，如果使用泛型方法将整个类泛型化，
		 * 
		 * 那么就应该使用泛型方法。另外对于一个static的方法而已，无法访问泛型类型的参数。
		 * 
		 * 所以如果static方法要使用泛型能力，就必须使其成为泛型方法。
		 */
		GenericFruit genericFruit = new GenericFruit();
		Apple apple = genericFruit.new Apple();
		Person person = genericFruit.new Person();
		GenerateTest<Fruit> generateTest = genericFruit.new GenerateTest<Fruit>();
		// apple是Fruit的子类，所以这里可以
		generateTest.show_1(apple);
		// 编译器会报错，因为泛型类型实参指定的是Fruit，而传入的实参类是Person
		// generateTest.show_1(person);

		// 因为是泛型方法，所以使用这两个方法都可以成功
		generateTest.show_2(apple);
		generateTest.show_2(person);

		// 使用这两个方法也都可以成功
		generateTest.show_3(apple);
		generateTest.show_3(person);

		// 可变参数的泛型方法
		generateTest.show_4("1111", "2222", 3333, 4444.44, new Object(), new ArrayList<String>());
		
		Generic<String> g1 = new Generic<String>("11111");
		Generic<Integer> g2 = new Generic<Integer>(1111);
		Generic<Double> g3 = new Generic<Double>(1111.11);
//		generateTest.show_5(g1);
		generateTest.show_5(g2);
		generateTest.show_5(g3);
	}
}
