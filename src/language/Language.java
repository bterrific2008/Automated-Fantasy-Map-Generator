package language;

import java.util.Arrays;
import java.util.Random;

public class Language {

	private char[] consonants = "ptkmnsl".toCharArray(),
			vowels = "aeiou".toCharArray(),
			finals = "mn".toCharArray(),
			sibilants = "s".toCharArray(),
			liquids = "rl".toCharArray();
	private String[] morph;
	private Random r = new Random();

	final String[] sylCombo = {"CVC", "CVvC", "CVVc", "CVc", "CV", "VC", "CVF",
			"cVC", "CVf", "ClVC", "ClVF", "sCVC", "sCVc", "cVF", "cVc", "cVf", 
			"clVC", "CVlC", "cVlC", "cVLc", "RANDOM"};
	final int CVC = 0, CVvC = 1, CVVc = 2, CVc = 3, CV = 4, VC = 5, CVF = 6, cVC = 7, 
			CVf = 8, ClVC = 9, ClVF = 10, sCVC = 11, sCVc = 12, cVF = 13, cVc = 14, 
			cVf = 15, clVC = 16, CVlC = 17, cVlC = 18, cVLc = 19, RANDOM = 20;

	public String generateSyllable(int type){
		if(type == RANDOM)
			type = r.nextInt(sylCombo.length-1);
		String syl = "";
		String order = sylCombo[type];
		for(int i = 0; i<order.length(); i++){
			char a = order.charAt(i);
			switch(a){
			case 'C': 
				syl += c();
				break;
			case 'c':
				if(r.nextBoolean()){syl += c();};
				break;
			case 'V':
				syl += v();
				break;
			case 'v':
				if(r.nextBoolean()){syl += v();};
				break;
			case 'F':
				syl += f();
				break;
			case 'f':
				if(r.nextBoolean()){syl += f();};
				break;
			case 'S':
				syl += s();
				break;
			case 's':
				if(r.nextBoolean()){syl += s();};
				break;
			case 'L':
				syl += l();
				break;
			case 'l':
				if(r.nextBoolean()){syl += l();};
				break;
			}
		}
//		System.out.println(syl +order);
		if(checkForDouble(syl))
			return generateSyllable(type);
		
		return syl;
	}
	
	public String generateWord(){
		String word = "";
		word += getRandomMorph();
		int numSyl = r.nextInt(10);
		if(numSyl < 2){
			word += getRandomMorph();
				
		}
		else if(numSyl < 7){
			word += getRandomMorph();
			word += getRandomMorph();
			
		}
		else if(numSyl < 9){
			word += getRandomMorph();
			word += getRandomMorph();
			word += getRandomMorph();
			
		}
		else if(numSyl < 11){
			word += getRandomMorph();
			word += getRandomMorph();
			word += getRandomMorph();
			word += getRandomMorph();
		}
		return word;
	}
	
	public String[] generateSyllableSet(int size){
		String[] set = new String[size];
		for(int i = 0; i<size;i++)
			set[i] = generateSyllable(CVC);
		return set;
	}

	public String[] generateSyllableSet(int size, int type){
		String[] set = new String[size];
		for(int i = 0; i<size;i++)
			set[i] = generateSyllable(type);
		if(morph!=null)
			morph = addToArray(morph, set);
		else
			morph = set;
		return set;
	}

	private String getRandomMorph(){
		int rand = r.nextInt(morph.length);
		return morph[rand];
	}
	
	private boolean checkForDouble(String syl){
		char n = syl.charAt(0);
		for(int i = 1; i<syl.length();i++){
			if(syl.charAt(i) == n){
				return true;
			}
			n = syl.charAt(i);
		}
		return false;
	}
	
	private String v(){
		return ""+vowels[r.nextInt(vowels.length)];
	}

	private String c(){
		return ""+consonants[r.nextInt(consonants.length)];
	}

	private String f(){
		return ""+finals[r.nextInt(finals.length)];
	}

	private String s(){
		return ""+sibilants[r.nextInt(sibilants.length)];
	}

	private String l(){
		return ""+liquids[r.nextInt(liquids.length)];
	}
	
	public String[] getMorphSet(){
		return morph;
	}
	
	private String[] addToArray(String[] a, String[] b){
		String[] c = new String[a.length+b.length];
		for(int i = 0; i<a.length; i++){
			c[i] = a[i];
		}
		for(int i = 0; i<b.length; i++){
			c[i+a.length-1] = b[i];
		}
		return c;
	}

	public String capitalizeLetter(String s){
		return (""+s.charAt(0)).toUpperCase()+s.substring(1);
	}
	
	public static void main(String args[]){
		Language l = new Language();
		l.generateSyllableSet(24,l.RANDOM);
		String[] set = l.getMorphSet();
		for(int i = 0; i<set.length; i++){
			System.out.print(set[i]+" / ");
		}
		System.out.println();
		System.out.println(l.capitalizeLetter(l.generateWord())+ " " + l.generateWord()+" "+l.generateWord());
		System.out.println(l.capitalizeLetter(l.generateWord())+ " " + l.generateWord()+" "+l.generateWord());
		System.out.println(l.capitalizeLetter(l.generateWord())+ " " + l.generateWord()+" "+l.generateWord());
	}
}
