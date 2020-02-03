package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;



/**
 * @author one
 *	保存文件类 —— 选择保存路径
 */
public class SaveFile {
	
	TreeSet<String> wordsLisk;
	private int count = 0;
	
	public void saveFile(File srcFile,File tarFile) {
		
		WordExtractImpl wordExtractImpl = new WordExtractImpl();
		wordExtractImpl.extractFile(srcFile);
		wordsLisk = wordExtractImpl.getWordsList();
		try (
				FileWriter fWriter = new FileWriter(tarFile);
				BufferedWriter writer = new BufferedWriter(fWriter);
				){
			for(String str : wordsLisk) {
				writer.write(str);
				writer.append('\n');
			}
			writer.append('\n');
			writer.write("Words total:	" + "<" + wordsLisk.size() + ">\n");
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



//	--------------------------------------------------
	public int getCount() {
		return wordsLisk.size();
	}

	public void setCount(int count) {
		this.count = count;
	}
//	--------------------------------------------------
}
