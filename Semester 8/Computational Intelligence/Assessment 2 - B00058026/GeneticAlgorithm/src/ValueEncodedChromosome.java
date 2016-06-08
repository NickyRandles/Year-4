// Integer based, value encoded chromosome
// S. Sheridan 27/02/2014
// Institute of Technology Blanchardstown

import java.io.*;
import java.util.*;

public class ValueEncodedChromosome{
		
	private int[] genes;			// Chromosome genes
	private int upper_bound;		// Upper bound for randomising
	private int length;				// Chromosome length
	private int fitness;			// Fitness value for chromosome

	public ValueEncodedChromosome(int length, int upper_bound){
		this.length = length;
		this.upper_bound = upper_bound;
		
		//TODO
		// Initialise fitness to high number. Bad fitness to start with
		fitness = 10;
		// Create the gene array based on length
		genes = new int[length];
		// Initialises the genes to random values between 1 - upper_bound, 30 in this case
		for(int i = 0; i < genes.length; i++){
			int random = (int) (Math.random() * ((upper_bound - 1) + 1));
			genes[i] = random;
		}
	}
	
	// Returns gene value at given locus
	public int getGeneAt(int pos){
		//TODO
		return genes[pos];
	}
	
	// Used for changing a gene value at given locus in chromosome
	public void setGeneAt(int pos, int val){
		//TODO
		genes[pos] = val;
	}
	
	// Will mutate a gene at given locus based on random value between 1 and upper_bound
	public void mutateGeneAt(int pos){
		//TODO
		int random = (int) (Math.random() * ((upper_bound - 1) + 1));
		genes[pos] = random;
	}
	
	// Sets the chromosome length
	public void setLength(int length){
		//TODO
		this.length = length;
	}
	
	// Returns the length of a chromosome
	public int getLength(){
		//TODO
		return length;
	}
	
	// Sets the fitness value for a chromosome
	public void setFitness(int fitness){
		//TODO
		this.fitness = fitness;
	}
	
	// Returns the fitness value for a chromosome
	public int getFitness(){
		//TODO
		return fitness;
	}
	
	// Used to compare chromosomes in order to remove duplicates
	// Compare this chromosome against argument c
	public boolean equals(ValueEncodedChromosome c){
		//TODO
		for(int i = 0; i < c.length; i++){
			if(genes[i] == c.genes[i]){
				return true;
			}
		}
		return false;
	}
	
	// Helps to show chromosome values on screen
	public String toString(){
		//TODO
		String values = "";
		for(int i = 0; i < genes.length; i++){
			values += genes[i] + " ";
		}
		return values;
	}
}