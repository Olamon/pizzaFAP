package window.search;

import java.util.Vector;

public class IngredientsHelper {
	static int INGR_NUM = 6;
	
	public static String getIngredientName(int ingredient){
		String res;
		switch(ingredient){
			case 1: res = "ser"; break;
			case 2: res = "szynka"; break;
			case 4: res = "pieczarki"; break;
			case 8: res = "kukurydza"; break;
            case 16: res = "salami"; break;
            case 32: res = "ananas"; break;
			default: res = "";
		}
		return res;
	}
	
	public static Vector<String> getIngredients(int sklad){
		Vector<String> res = new Vector<>();
		int power = 1;
		for(int i=0; i<INGR_NUM; i++){
			if(!getIngredientName(power & sklad).equals("")){
				res.add(getIngredientName(power & sklad));
			}
			power*=2;
		}
		return res;
	}

    public static Vector<String> getAllIngredients()
    {
        return getIngredients(Integer.MAX_VALUE);
    }
	
	public static int translateToInt(boolean[] ingr){
		int power = 1;
		int res = 0;
		for(int i=0; i<INGR_NUM; i++){
			if(ingr[i]) res += power;
			power*=2;
		}
		return res;
	}
}
