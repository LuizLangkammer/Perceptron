package fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.Sample;

public class VertebralColumnReader {
	
	private Map<String, String> outputMap;
	
	public VertebralColumnReader() {
		outputMap = new HashMap<String, String>();
		outputMap.put("AB", "1 0");
		outputMap.put("NO", "0 1");
	}
	
	public ArrayList<Sample> getBase() throws FileNotFoundException, IOException {

		FileReader arq = new FileReader("column_2c.dat");

		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(arq);
		String read = "";
		int qntIn = 6;
		int qntOut = 2;
		int qntSample = 310;


		ArrayList<Sample> base = new ArrayList<Sample>();
		double[] in = new double[qntIn];
		double[] out = new double[qntOut];
		for (int i = 0; i < qntSample; i++) {

			read = reader.readLine();
			String[] line = read.split(" ");

			for (int j = 0; j < qntIn; j++) {
				in[j] = Double.parseDouble(line[j]);
			}
			
			String[] output = outputMap.get(line[6]).split(" ");
			for (int j = 0; j < qntOut; j++) {
				out[j] = Integer.parseInt(output[j]);
			}
			
			
			base.add(new Sample(in.clone(), out.clone()));

		}

		
		reader.close();
		

		return base;
	}
}
