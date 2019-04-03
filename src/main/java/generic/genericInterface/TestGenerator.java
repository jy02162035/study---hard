package generic.genericInterface;

// https://www.cnblogs.com/coprince/p/8603492.html
//TODO
public class TestGenerator {

	public class Generic<T> {
		private T key;

		public Generic(T key) {
			this.key = key;
		}

		public T getKey() {
			return this.key;
		}
	}

	public static void main(String[] args) {
		// outterClass and innerClass study************
		InnerClass innerClass = new TestGenerator().new InnerClass("wps niubi");
		InnerStaticClass innerStaticClass = new TestGenerator.InnerStaticClass("wps niubi niubi");
		System.out.println(innerClass.getKey());
		System.out.println(innerStaticClass.getKey());
		// outterClass and innerClass study************

		TestGenerator testGenerator = new TestGenerator();
		Generic<String> generic = testGenerator.new Generic<String>("wupengshun niubihonghong");
		System.out.println(testGenerator.showKeyName(generic));

		// System.out.println(testGenerator.showKeyName(null));

	}

	/**
	 * 这才是一个真正的泛型方法。 首先在public与返回值之间的<T>必不可少，这表明这是一个泛型方法，并且声明了一个泛型T
	 * 这个T可以出现在这个泛型方法的任意位置. 泛型的数量也可以为任意多个 如：public <T,K> K showKeyName(Generic
	 * <T> container){ ... }
	 */
	public <T> T showKeyName(Generic<T> container) {
		return container.getKey();
	}

	public class InnerClass {
		private String key;

		public InnerClass(String key) {
			this.key = key;
		}

		public String getKey() {
			return this.key;
		}
	}

	public static class InnerStaticClass {
		private String key;

		public InnerStaticClass(String key) {
			this.key = key;
		}

		public InnerStaticClass() {
		}

		public String getKey() {
			return this.key;
		}
	}

}
