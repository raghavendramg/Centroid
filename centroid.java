import java.util.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;
import java.util.Formatter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

class centroid {
	
	public ArrayList examples;
	public ArrayList attributes;

	public static void main(String[] args) throws IOException
	 {
		DecimalFormat df=new DecimalFormat("#.#####");   //formatting decimal into 5 digit place
		df.setRoundingMode(RoundingMode.CEILING);
		String input=args[0];
		String input1=args[1];
		Scanner sc = new Scanner(new File(input));
		String text1 = sc.nextLine();
		int count=0;
		ArrayList<Double> classifer=new ArrayList<Double>();
		StringTokenizer st = new StringTokenizer(text1,",");
		while(st.hasMoreTokens())
		{	
			String manny=st.nextToken(",");
			double val=Double.parseDouble(manny);
		  	classifer.add(val);
			count++;
		}
		sc.close();
		
		
		//Reading the training data from file
		Scanner scan = new Scanner(new File(input));
		ArrayList<ArrayList<Double>> hypothesis=new ArrayList<ArrayList<Double>>();
		for(int i=0;i<count;i++)
		{
			ArrayList<Double> ls = new ArrayList<Double>();
			hypothesis.add(ls);
		}
		
		while(scan.hasNextLine())    //Reading the training data
		 {
			String text2 = scan.nextLine();
			StringTokenizer tommy = new StringTokenizer(text2, ",");
			for (int r = 0; r < count; r++) 
			{
				String zidane=tommy.nextToken(",");
				double valuable=Double.parseDouble(zidane);
				hypothesis.get(r).add(valuable);

			}
		 }
		
		//calculating the min and max number of class labels
		double min_classlabel=Collections.min(classifer);
		double max_classlabel=Collections.max(classifer);
		ArrayList<ArrayList<Double>> Class_centroid=new ArrayList<ArrayList<Double>>();
		for(int i=(int) min_classlabel;i<(int) max_classlabel+1;i++)
		{
			ArrayList<ArrayList<Double>> Class_division=new ArrayList<ArrayList<Double>>();
			for(int j=0;j<hypothesis.size();j++)
			{
				if(i==hypothesis.get(j).get(0))
				{
					Class_division.add(hypothesis.get(j));
				}
			}
			
			//Caclulating the centroid 
			ArrayList<Double> individual=new ArrayList<Double>();
			for(int j=1; j<Class_division.get(0).size();j++)
			{
				double sum=0.0;
				for(int m=0;m<Class_division.size();m++)
				{
					sum=sum + Class_division.get(m).get(j);
		
				}
				double gem=sum/Class_division.size();
				double add_gem=Double.parseDouble(df.format(gem));
				individual.add(add_gem);
			}
			Class_centroid.add(individual);
		}
		
		// Reading the Test DATA
		Scanner cum = new Scanner(new File(input1));
		String texting = cum.nextLine();
		int counting=0;
		StringTokenizer stringing = new StringTokenizer(texting,",");
		while(stringing.hasMoreTokens()){
			stringing.nextElement();
			counting++;
		}
		cum.close();
		//System.out.println(counting);
		ArrayList<ArrayList<Double>> hypo=new ArrayList<ArrayList<Double>>();
		for(int i=0;i<counting;i++)
		{
			ArrayList<Double> ls = new ArrayList<Double>();
			hypo.add(ls);
		}

		Scanner scan_one = new Scanner(new File(input1));
		while(scan_one.hasNextLine())    //Reading the training data
		 {
			String text4 = scan_one.nextLine();
			StringTokenizer stamp = new StringTokenizer(text4, ",");
			for (int r = 0; r < counting; r++) 
			{
				String man=stamp.nextToken(",");
				double value=Double.parseDouble(man);
			  	hypo.get(r).add(value);
			}
		 }
	
		ArrayList<Double> total_accuracy=new ArrayList<Double>();
		System.out.println("The class labels are :");
		for(int b=0;b<hypo.size();b++)
		{
			//System.out.printf("%d",hypo.size());
			ArrayList<Double> distance=new ArrayList<Double>();
			ArrayList<ArrayList<Double>> final_value=new ArrayList<ArrayList<Double>>();
			distance=test_classifier(Class_centroid,hypo.get(b));
			double actual_distance=Collections.min(distance);
		 	int index=distance.indexOf(actual_distance);
			System.out.println(index+1);
		}
		System.out.println();
	}
	public static ArrayList<Double> test_classifier(ArrayList<ArrayList<Double>> train, ArrayList<Double> test)
		{
			DecimalFormat df=new DecimalFormat("#.#####");   //formatting decimal into 5 digit place
			df.setRoundingMode(RoundingMode.CEILING);
			ArrayList<Double> distance=new ArrayList<Double>();
			for(int y=0;y<train.size();y++)
			{
				double straight=0.0;
				for(int m=0;m<train.get(0).size();m++)
				{
					double eugin=Math.pow((train.get(y).get(m)-test.get(m)), 2);
					straight=straight+eugin;
				}
				double SquareRoot =(double) Math.sqrt(straight);
				double standard=Double.parseDouble(df.format(SquareRoot));
				distance.add(standard);
				
			}
			return distance;
		}
			
}


