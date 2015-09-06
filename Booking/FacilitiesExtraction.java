import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class FacilitiesExtraction {
	static public void main(String []args){
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		s.nextLine();
		String[] facilities = new String[N];
		for(int i = 0; i<N; i++){
			facilities[i] = s.nextLine();
		}
		StringBuilder sb = new StringBuilder();
		while(s.hasNextLine()){
			sb.append(s.nextLine());
		}
		StringBuilder patStr = new StringBuilder();

		String prefix = "";
		for(String f : facilities){
			patStr.append(prefix);
			prefix = "|";
			patStr.append("(");
				patStr.append(f.replace(" ","\\ "));
				patStr.append(")");
			}

			// Pattern pt = Pattern.compile(patStr.toString(), Pattern.CASE_INSENSITIVE);
			// Matcher m = pt.matcher(sb.toString());
			LinkedHashSet<String> set = new LinkedHashSet<String>();
			allMatches(sb.toString(),patStr.toString(),set, patStr.toString());
			String[] res = set.toArray(new String[set.size()]);
			Arrays.sort(res);
			for(String e : res){
				System.out.println(e);
			}
		}

		public static void allMatches(String text, String regex, Set s, String pat){
			Matcher m = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(text);
			int end = text.length();
			for (int i = 0; i < end; ++i){
				for (int j = i + 1; j <= end; ++j){
					m.region(i, j);
					if (m.find()){
						String found = m.group();
						Matcher m2 = Pattern.compile(found, Pattern.CASE_INSENSITIVE).matcher(pat.replace("\\ ", " "));
						if(m2.find()){
							s.add(m2.group());
						}
					}   
				}   
			}   
		}
	}