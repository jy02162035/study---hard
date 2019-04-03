package generic.genericClass;

import org.springsource.loaded.Log;

public class TestGeneric {

	public static void main(String[] args) {
		Generic<Integer> genericInteger = new Generic<Integer>(10000);
		Log.log(genericInteger.getKey()+ "");
		
		Generic<String> genericString = new Generic<String>("wupengshun");
		Log.log(genericString.getKey());

	}

}
