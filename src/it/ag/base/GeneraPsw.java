package it.ag.base;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 * @author Andrea Gatta    04/07/2023
 * <br>
 * <p><b>AG    04/07/2023</b> Classe per generazione di file con lista di password che contengono 
 * 								tutte le combinazioni possibili di un array di char</p>
 */

public class GeneraPsw {

	static PrintWriter printWriter;
	
	/**
	 * Funzione ricorsiva che aggiunge/toglie caratteri fino al raggiungimento di len
	 */
	static void generate(char[] arr, int i, String s, int len) throws IOException{
		if (i == 0){ //ho raggiunto len
			//			System.out.println(s);
			printWriter.println(s);
			return;
		}

		for (int j = 0; j < len; j++){
			//Crea una nuova stringa con il prossimo carattere
			//e chiama generate fino a che la stringa non raggiunge .length = len
			String appended = s + arr[j];
			generate(arr, i - 1, appended, len);
		}

		return;
	}

	/**
	 * Funzione per generare tutte le combinazioni possibili di password
	 */
	static void crack(char[] arr, int len) throws IOException {
		// chiamata per tutte le possibili lunghezze delle password
		//se so per certo che la passoword è lunga almeno 8 posso inizializzare i = 8
		for (int i = 1; i <= len; i++) {
			generate(arr, i, "", len);
		}
	}

	public static void main(String[] args)
	{
		char arr[] = {'p','r','O'}; //caratteri per le possibili combinazioni
		try {
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			String fileName = "C:\\PasswordsGenerate\\psw_"+ts.toString().replace(":", "-")+".txt";
			File log = new File(fileName);
			if (log.createNewFile()) {
				System.out.println("Creo file");
			}
			FileWriter fileWriter = new FileWriter(log.getAbsolutePath());
			printWriter = new PrintWriter(fileWriter);
			int len = arr.length;
			crack(arr, len);
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();	
		}
	}

}