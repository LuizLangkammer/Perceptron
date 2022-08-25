package fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.Sample;

public class CarEvaluationReader {
	
	
	private String path;
	private int qntIn;
	private int qntOut;
	private int qntSample;
	private Map<String, Integer> buyingMaintMap;
	private Map<String, Integer> doorsMap;
	private Map<String, Integer> personsMap;
	private Map<String, Integer> lugBootMap;
	private Map<String, Integer> safetyMap;
	private Map<String, String> outputMap;
	
	public CarEvaluationReader() {
		
		qntIn = 6;
		qntOut = 4;
		qntSample = 1728;
		path= "car.data";
		
		buyingMaintMap= new HashMap<String,Integer>();
		buyingMaintMap.put("vhigh", 3);
		buyingMaintMap.put("high", 2);
		buyingMaintMap.put("med", 1);
		buyingMaintMap.put("low", 0);
		
		doorsMap= new HashMap<String,Integer>();
		doorsMap.put("2", 0);
		doorsMap.put("3", 1);
		doorsMap.put("4", 2);
		doorsMap.put("5more", 3);
		
		personsMap= new HashMap<String,Integer>();
		personsMap.put("2", 0);
		personsMap.put("4", 1);
		personsMap.put("more", 2);
		
		lugBootMap= new HashMap<String,Integer>();
		lugBootMap.put("small", 0);
		lugBootMap.put("med", 1);
		lugBootMap.put("big", 2);
		
		safetyMap= new HashMap<String,Integer>();
		safetyMap.put("low", 0);
		safetyMap.put("med", 1);
		safetyMap.put("high", 2);
		
		
		
		outputMap = new HashMap<String, String>();
		outputMap.put("unacc", "1 0 0 0");
		outputMap.put("acc", "0 1 0 0");
		outputMap.put("good", "0 0 1 0");
		outputMap.put("vgood", "0 0 0 1");
	}

	public ArrayList<Sample> getBase() throws FileNotFoundException, IOException {

		FileReader arq = new FileReader(path);

		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(arq);
		String read = "";
		
		
		ArrayList<Sample> base = new ArrayList<Sample>();
		double[] in = new double[qntIn];
		double[] out = new double[qntOut];
		for (int i = 0; i < qntSample; i++) {

			read = reader.readLine();
			String[] line = read.split(",");

			in[0] = buyingMaintMap.get(line[0]);
			in[1] = buyingMaintMap.get(line[1]);
			in[2] = doorsMap.get(line[2]);
			in[3] = personsMap.get(line[3]);
			in[4] = lugBootMap.get(line[4]);
			in[5] = safetyMap.get(line[5]);
			
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
