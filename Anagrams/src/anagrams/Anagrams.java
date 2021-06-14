package anagrams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;

public class Anagrams {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		String word = args[1].toLowerCase();
		char[] wordArray = word.toCharArray();
		BufferedReader readDict = null;
		String currentLine;
		List<String> dictionary = new ArrayList<>();
		List<String> reducedDict = new ArrayList<>();
		List<String> anagrams = new ArrayList<>();
		
		LinkedHashMap<Character, Integer> letterMap = new LinkedHashMap<Character, Integer>();
		
		for(int i=0; i< word.length() ; i++) {				//Fill L-HashMap for letter : #instances
			int counter = 0;
			for(int j=0; j<word.length(); j++) {
				if(word.charAt(j) == wordArray[i]){ 
					counter++;
				}	
			}
			letterMap.put(word.charAt(i), counter);
		}	
		
		try {
			readDict = new BufferedReader(new FileReader("C:\\Users\\Sam\\Desktop\\Current_Scripts\\CSCE156\\Anagrams\\american"));
			
			while((currentLine = readDict.readLine()) != null) {
				dictionary.add(currentLine.toLowerCase());
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (readDict != null)
					readDict.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
		for(String s: dictionary) {					//Limit dictionary to n-length words
			if(s.length() == n) {
				reducedDict.add(s);
			}
		}
		
		for(String s: reducedDict) {					
			int matchCounter = 0;					//resets for each new word
			for(int i=0; i<s.length(); i++) {					
				int occurenceCounter = 0;						//resets for each letter
				for(int j=0; j<s.length(); j++) {
					if(s.charAt(i) == s.charAt(j)) {
						occurenceCounter++;
					}
				}
				if(letterMap.get(s.charAt(i)) != null)
					if(occurenceCounter <= letterMap.get(s.charAt(i))) {		//compare occurences
						matchCounter++;
					}
			}
			if(matchCounter == n) {
				anagrams.add(s);
			}
		}
		
		
		for(String s: anagrams) {
			System.out.println(s);
		}
		 

	}

}
