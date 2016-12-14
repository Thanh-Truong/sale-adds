package example.solver;

public class PuzzleSolver {
	public PuzzleSolver(int ti, String companies[], int impressions_[],
			int prices_[], int n) {
		m_totalInventory = ti;
		m_companies = companies;
		m_impressions = impressions_;
		m_prices = prices_;
		N = n;
	}
	//-------The following properties get filled from test file------
	// month inventory
	private int m_totalInventory = 0;
	// impressions per campaign
	private int m_impressions[];
	// prices per campaign
	private int m_prices[];
	// number of companies to sell
	private int N;
	// company names
	private String m_companies[];
	
	//-------The following properties are reset for every run------
	// number of campaigns for N company
	private int m_campaigns[];
	private int m_solution[];
	private int m_maxCampaigns[];
	// time to stop execution
	private long m_endTime = 0;
	
	
	private void initializeRun(){
		// m_solution
		// 		(0-N-1)th stores contributed values of each item
		//  	 Nth stores the maximum values
		m_solution = new int [N + 1];
		m_campaigns = new int[N];
		m_maxCampaigns = new int [N];
		for (int i = 0; i < N ; i ++){
			m_campaigns[i] = 0;
			m_solution[i] = 0;
			m_maxCampaigns[i] = (int) m_totalInventory / m_impressions[i];
			// Optimization 1: Skip non-profit customer
			if (m_prices[i] == 0) {
				m_maxCampaigns[i] = 0;
			}
		}
		m_solution[N] = 0;
		// should stop solver within a minute
		m_endTime = System.currentTimeMillis() + 59*1000;
	}
	private String displayResult() {
		String output = "<br>";
		String separator = ",";
		int totalImpressions = 0;
		int impressions = 0;
		// <customer>,<number of campaigns to sell>,<total impressions for
		// customer>,<total revenue for customer>
		for (int i = 0; i < N; i++) {
			impressions = m_solution[i] * m_impressions[i];
			totalImpressions += impressions;
			output += m_companies[i] + separator 
				 	+ (m_solution[i]) + separator
					+ impressions + separator 
					+ (m_solution[i] * m_prices[i]) + "<br>";
		}
		// <total number of impressions>,<total revenue>
		output += totalImpressions + separator + m_solution[N];
		return output;
	}
	// Driver
	public String run() {
		initializeRun();
		solver(0);
		return displayResult();
	}
	private void solver(int item) {
		int start = 0;
		int step = 1;
		// Optimization 2: A very simple optimization to let solver quickly 
		// converge to the optimized solution
		if (m_maxCampaigns[item] >= 25000000) {
			start = step = 10000;
		}
		for(int i = start; i <= m_maxCampaigns[item] && System.currentTimeMillis() < m_endTime; i += step) {
			m_campaigns[item] = i;
			if (item < N - 1) {
				solver(item +  1);
			}else {
				int totalValue = 0;
				int totalImpressions = 0;
				for(int j = 0; j < N; j ++){
					totalValue += m_campaigns[j] * m_prices[j];
					totalImpressions += m_campaigns[j] * m_impressions[j];
				}
				// found solution
				if (totalImpressions <= m_totalInventory){
					if (totalValue > m_solution[N]) {
						for(int k = 0; k < N; k ++){
							m_solution[k] = m_campaigns[k];
						}
						m_solution[N] = totalValue;
					}
				}
			}
		}
	}
}
