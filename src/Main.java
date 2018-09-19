import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
	String[] romans = { "m", "cm", "d", "c", "xc", "l", "x", "ix", "v", "i" };
	int[] romanValues = { 1000, 900, 500, 100, 90, 50, 10, 9, 5, 1 };
	
	ArrayList<String> alias = new ArrayList<String>();
	ArrayList<String> val_alias = new ArrayList<String>();
	
	int Roman_Value_int(char c) {
		switch(c) {
		case 'i':
			return 1;
		case 'v':
			return 5;
		case 'x':
			return 10;
		case 'l':
			return 50;
		case 'c':
			return 100;
		case 'd':
			return 500;
		case 'm':
			return 1000;
		default:
			return -1;
		}
	}
	
	int RomanToInt(String str) {
		String string = str.toLowerCase();
		int result = 0;
		int length = string.length();
		
		for(int i = 0; i < length; i++) {
			int val_f = Roman_Value_int(string.charAt(i));
			
			if(val_f > -1) {
				if (i+1 < length) {
					int val_L = Roman_Value_int(string.charAt(i+1));
					if(val_f >= val_L) {
						result = result + val_f;
					} else {
						result = result + val_L - val_f;
						i++;
					}
				} else {
					result = result + val_f;
					i++;
				}
			} else {
				return 0;
			}	
		}
		return result;
	}
	
	String IntToRoman(int val) {
		
		String result = "";
		 
		for (int i = 0; i < romanValues.length; i++) {
			int toRoman = val / romanValues[i];
			if (toRoman == 0) 
				continue;
			
			if(toRoman == 4 && i > 0) {
				result += romans[i] + romans[i - 1];
			} else {
				result += new String(new char[toRoman]).replace("\0",romans[i]);
			}
			
			val = val % romanValues[i];
		}
		
		return result;
	}
	
	void Handler(String strs) {
		String[] strArr = strs.toLowerCase().split(" ");
		int length = strArr.length;
		int is_idx = -1;
		String is = "is";
		
		for(int i = 0; i<length; i++) {
			if(!Objects.equals(strArr[i], is)) {
			} else {
				is_idx = i;
			}
		}
		
		if(is_idx > -1) {
			switch (is_idx) {
			case 0:
				System.out.println("Error Input");
				
			case 1:
				if(length == 3) {
					Declare_X(strArr);
				} else {
					System.out.println("Error Input");
				}
				break;
				
			default:
				if(length > 3) {
					Calculate_X(strArr);
				} else {
					System.out.println("Error Input");
				}
				break;
			}
		} else {
			System.out.println("Wrong Input");
		}
	}
	
	void Declare_X(String[] strs) {
		if(alias.size() > 0) {
			if(alias.indexOf(strs[0]) > -1) {
				System.out.println(strs[0] + "IS ALREADY DEFINED");
			} else {
				alias.add(strs[0]);
				val_alias.add(strs[2]);
			}
		} else {
			alias.add(strs[0]);
			val_alias.add(strs[2]);
		}
	}
	
	void Calculate_X(String[] strs) {
		int length = strs.length;
		if(Objects.equals(strs[0], new String("how"))) {
			String resv = "";
			for(int i = 0; i<length; i++) {
				if(Objects.equals(strs[i], new String("how")) ||
						Objects.equals(strs[i], new String("many")) ||
						Objects.equals(strs[i], new String("much")) ||
						Objects.equals(strs[i], new String("credits")) ||
						Objects.equals(strs[i], new String("is")) ||
						Objects.equals(strs[i], new String("?"))) {
				} else {
				
				if(alias.indexOf(strs[i]) > -1) {
					if(Objects.equals(strs[i], new String("silver"))){
						resv += ":" + val_alias.get(alias.indexOf(strs[i]));
					} else if(Objects.equals(strs[i], new String("iron"))) {
						resv += ":" + val_alias.get(alias.indexOf(strs[i]));
					} else {
						resv += val_alias.get(alias.indexOf(strs[i]));
					}
				} else {
					System.out.println("ERROR: UNDEFINED "+ strs[i]);
					return;
				}
			}
			}
			
			if(!resv.isEmpty()) {
				int calculate = 0;
				if(resv.contains(":")) {
					String[] arr = resv.split(":");
					if(arr.length > 1) {
						for(int i = 0; i<arr.length; i++) {
							if(isNumeric(arr[i])) {
								if(calculate > 0) {
									calculate *= Integer.parseInt(arr[i]);
								} else {
									calculate += Integer.parseInt(arr[i]);
								}
							} else {
								if(calculate > 0) {
									calculate *= RomanToInt(arr[i]);
								} else {
									calculate += RomanToInt(arr[i]);
								}
							}
						}
					} else {
						if(isNumeric(arr[0])) {
							calculate = Integer.parseInt(arr[0]);
						} else {
							calculate = RomanToInt(arr[0]);
						}
					}
				} else {
					calculate = RomanToInt(resv);
				}

				System.out.println(calculate + " Credits");
			}
		}
		else {
			String res = "";
			int idx_undefined = -1;
			int val_expect = 0;
			
			for(int i = 0; i<length; i++) {
				
				if(Objects.equals(strs[i], new String("is")) ||
						Objects.equals(strs[i], new String("credits"))) {
				} else {
				
				if(!isNumeric(strs[i])) {
					if(alias.indexOf(strs[i]) > -1) {
						res += val_alias.get(alias.indexOf(strs[i]));
					} else {
						idx_undefined = i;
					}
				} else {
					val_expect = Integer.parseInt(strs[i]);
				}
			}
			}
			
			if(val_expect > 0) {
				int toCalculate = RomanToInt(res);
				if (toCalculate > 0) {
					if(idx_undefined > -1) {
						if(Objects.equals(strs[idx_undefined], new String("silver"))){
							val_expect = val_expect / toCalculate;
							val_alias.add(IntToRoman(val_expect));
						} else if(Objects.equals(strs[idx_undefined], new String("iron"))) {
							val_expect = val_expect / toCalculate;
							val_alias.add(Integer.toString(val_expect));
						} else {
							val_expect = val_expect - toCalculate;
							val_alias.add(IntToRoman(val_expect));
						}
						alias.add(strs[idx_undefined]);
				} else {
					System.out.println("Calculate Roman Error");
				}
			} else {
				System.out.println("Calculate Error");
			}
		}
		}
	}
	
	boolean isNumeric(String str)
	{
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0);
	  formatter.parse(str, pos);
	  
	  return str.length() == pos.getIndex();
	}
	
	public static void main(String Args[]){
		Main mn = new Main();
		System.out.println("EXAMPLE USAGE: \r\n\n"
				+ "glob is I\r\n" + 
				"prok is V\r\n" + 
				"pish is X\r\n" + 
				"tegj is L\r\n" + 
				"\r\n" + 
				"glob glob Silver is 34 Credits\r\n" + 
				"glob prok Gold is 57800 Credits\r\n" + 
				"pish pish Iron is 3910 Credits\r\n" + 
				"how much is pish tegj glob glob ?\r\n" + 
				"how many Credits is glob prok Silver ?\r\n" + 
				"how many Credits is glob prok Gold ?\r\n" + 
				"how many Credits is glob prok Iron ?\r\n"
				+ "==============================================\n\n");
		
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		while(true) {
			String str = reader.nextLine();
			mn.Handler(str);
		}
	}
}
