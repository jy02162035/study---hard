package statictest;

/**
 * 功能描述：首先执行静态变量t2和t3的实例化走构造函数，之后会执行static的静态语句块的执行，最后走main方法
 *
 * <p> 创建时间：2019/01/23 13:51:50 </p> 
 * <p> 作者：小D课堂</p>
 */
//构造块public B(XXX)     t2     statictest.B@368102c8
//构造块public B(XXX)     t3     statictest.B@1963006a
//构造块public B(XXX)     t1     statictest.B@7fbe847c
//静态块
//构造块public B(XXX)     t     statictest.B@41975e01
public class B {
	
	public B() {
		System.out.println("构造块public B()     " + this.toString());
	}

	public B(String str) {
		System.out.println("构造块public B(XXX)     " + str + "     " + this.toString());
	}

	public static B t1;
	public static B t2 = new B("t2");
	public static B t3 = new B("t3");

//	{
//		System.out.println("构造块{}         " + this);
//	}

	static {
		t1 = new B("t1");
		System.out.println("静态块");
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		B t = new B("t");
		System.exit(0);
	}
}