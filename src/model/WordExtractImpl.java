package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;



/**
 * @author one
 *	单词提取类
 */
public class WordExtractImpl {
	
	TreeSet<String> wordsList;

	public void extractFile(File srcFile) {
		
		wordsList = new TreeSet<String>();
		
		/**	筛选后缀	*/
		String[] suffixList = {
			"s","ed","ly","er","or","tion","ity","ment","ive","less","ing","ful",
			"en","ious","ness","ible","es"
		};
		
		try (
				FileReader fReader = new FileReader(srcFile);
				BufferedReader reader = new BufferedReader(fReader);
				){
			
			/** 将单词转换为小写后存在wordsList(Set)中，以取消重复*/
			String line = null;
			while((line = reader.readLine()) != null) {
				String[] temp = new String[line.split(" ").length];
				temp = line.split(" ");
				
				for(String str : temp) {
					
					for(int i = 0; i < suffixList.length; i++) {
						if(str.contains(suffixList[i]) && (str.length() > 4)) {
							str = str.substring(1, str.length() - suffixList[i].length());
						}
					}

					for(int i = 0; i < str.length(); i++) {
						if(str.toCharArray()[i] < 97 || str.toCharArray()[i] > 122) {
							str.toCharArray()[i] = '\n';
							str = str.toCharArray().toString();
						}
					}
					if(str.length() > 3) {
						wordsList.add(str.toLowerCase());
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public TreeSet<String> getWordsList() {
		return wordsList;
	}

	public void setWordsList(TreeSet<String> wordsList) {
		this.wordsList = wordsList;
	}
}
