package main;

import java.util.Random;

public class Perceptron {
	
	private double ni;
	private int qntIn;
	private int qntOut;
	private double[][] w;
	
	
	public Perceptron(double ni, int qntIn, int qntOut) {
		
		this.qntIn = qntIn;
		this.qntOut = qntOut;
		this.ni = ni;
		
		Random random = new Random();
		
		w = new double[qntIn+1][qntOut];
		
		
		for(int i=0;i<qntIn;i++) {
			for(int j=0;j<qntOut;j++) {
				w[i][j] = (random.nextDouble(0.6)-0.3);
			}
		}
	}
	
	public double[] learn(double[] xIn, double[] y, double ni) {
		
		
		double[] x = new double[xIn.length+1];
		for(int i=0;i<xIn.length;i++) {
			x[i]=xIn[i];
		}
		x[x.length-1] = 1;
		
		double[] out = new double[y.length];
		double u=0;
		for(int j=0; j<out.length;j++) {
			u=0;
			for(int i=0;i<x.length;i++) {
				u += w[i][j]*x[i];
			}
			out[j] = sig(u);
		}
		
		for(int i=0;i<qntIn+1;i++) {
			for(int j =0; j<qntOut;j++) {
				
				w[i][j] += ni*(y[j] - out[j])*x[i];
				
			}
		}
		
		return out;
		
	}
	
	public double[] exec(double[] xIn, double[] y) {
		double[] x = new double[xIn.length+1];
		for(int i=0;i<xIn.length;i++) {
			x[i]=xIn[i];
		}
		x[x.length-1] = 1;
		
		double[] out = new double[y.length];
		double u=0;
		for(int j=0; j<out.length;j++) {
			u=0;
			for(int i=0;i<x.length;i++) {
				u += w[i][j]*x[i];
			}
			out[j] = sig(u);
		}
		return out;
	}
	
	private double sig(double u) {
		return 1/(1+Math.exp(-u));
	}
	

}
