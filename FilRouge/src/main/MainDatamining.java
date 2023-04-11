package main;

import java.util.Set;

import blockworld.BWDatamining;
import datamining.Apriori;
import datamining.AssociationRule;
import datamining.BooleanDatabase;
import datamining.BruteForceAssociationRuleMiner;
import datamining.Itemset;

public class MainDatamining {

	public static void main(String[] args) {

		BWDatamining bwD = new BWDatamining(5, 5);
		System.out.println("********************  Test Frequency  *********************");
		// Generating database
		BooleanDatabase database = bwD.generateDatabase(10000);
		// Instantiate apriori and launching extract to get frequent itemsets
		Apriori apriori = new Apriori(database);
		Set<Itemset> items = apriori.extract((float) 2 / 3);
		// Print result
		for (Itemset item : items) {
			System.out.println(item);
		}
		System.out.println();
		System.out.println("********************  Test Rule  *********************");
		// Instantiate associationRuleMiner and launching extract to get rules
		BruteForceAssociationRuleMiner asso = new BruteForceAssociationRuleMiner(database);
		Set<AssociationRule> rules = asso.extract((float) 2 / 3, (float) 2 / 3);
		// Print result
		for (AssociationRule rule : rules) {
			System.out.println(rule);
		}
	}

}
