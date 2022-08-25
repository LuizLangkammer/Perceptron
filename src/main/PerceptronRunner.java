package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import fileReader.*;



public class PerceptronRunner {

	public static void main(String[] args) throws IOException {
		
		
		
		//Parameters
		String basePath = "robo-base.txt";
		double ni = 0.001;
		int ages = 100000;
	
		System.out.println(basePath);
		
		ArrayList<Sample> base = null;
		try{
			base = new CarEvaluationReader().getBase(); 
		}catch(FileNotFoundException e) {
			System.out.println("Arquivo não encontrado");
		}catch(IOException e) {
			System.out.println("Falha na leitura do arquivo");
		}
		
		if(base==null) {
			System.out.println("Encerrando...");
			System.exit(0);;
		}
		
		Perceptron perceptron = new Perceptron(ni,base.get(0).getInDimension(),base.get(0).getOutDimension());
		
		double ageError = 0;
		double sampleError = 0;

		for(int i=0;i<ages;i++) {
			ageError=0;
			for(int j=0; j<base.size();j++) {
				sampleError=0;
				double[] output = perceptron.learn(base.get(j).getInput(), base.get(j).getOutput(), ni);
				for(int o=0;o<output.length;o++) {
					sampleError+= Math.abs(output[o]-base.get(j).getOutput()[o]);
				}
				ageError+=sampleError;
			}
			System.out.println("Época "+i+":    "+ageError);
		}
		
		int soma=0;
		for(int j=0; j<base.size();j++) {
			
			double[] output = perceptron.exec(base.get(j).getInput(), base.get(j).getOutput());
			for(int i=0;i<base.get(j).getInput().length;i++) {
				System.out.print(base.get(j).getInput()[i]+"    ");
			}
			System.out.print("| ");
			for(int o=0;o<base.get(j).getOutput().length;o++) {
				System.out.print(base.get(j).getOutput()[o]+" ");
			}
			System.out.print("| ");
			boolean hit=true;
			for(int o=0;o<output.length;o++) {
				System.out.print(output[o]+" ");
				if(Math.round(output[o])!=base.get(j).getOutput()[o]) {
					hit=false;
				}
				
			}
			if(hit) {
				soma++;
			}
			System.out.println("   HIT- " + hit);
			System.out.println();
		}
		
		System.out.println("Porcentagem de acerto: "+((double)soma/base.size())*100+"%");
		System.out.println("Erro da ultima época:    "+ageError);
		
		
	}
	
	

}
