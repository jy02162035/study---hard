package hashmap;

import java.util.HashMap;

public class TestHashMap {

	public static void main(String[] args) {
		String str = "8";
		HashMap<Object, Object> map = new HashMap<>();
		map.put("wps", "wupengshun");
		System.out.println(str.hashCode());
		System.out.println(Integer.toBinaryString(str.hashCode()));
		System.out.println("*******************************************************");
		
		System.out.println(str.hashCode() >>> 16);
		System.out.println(Integer.toBinaryString(str.hashCode() >>> 16));
		System.out.println("*******************************************************");
		
		
		System.out.println(str.hashCode() ^ str.hashCode() >>> 16);
		System.out.println(Integer.toBinaryString(str.hashCode() ^ str.hashCode() >>> 16));
		System.out.println("*******************************************************");
		
		
		System.out.println(1 << 4);
		System.out.println(Integer.toBinaryString(1 << 4));
		System.out.println(1 << 30);
		System.out.println(Integer.toBinaryString(1 << 30));
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
		System.out.println(100 << 1);
	}

}
